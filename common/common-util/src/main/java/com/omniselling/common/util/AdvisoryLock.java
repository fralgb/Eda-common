package com.omniselling.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.protocol.OmniException;

/**
 * WARNING: This lock util is implemented for MySQL/MariaDB only! Won't work on other database
 * @author Atomic
 *
 */
public class AdvisoryLock
{
    private static DataSource dataSource = null;
    private static final int ACQUIRE_BLOCKING_WAIT_SEC = 60;
    private static final Logger logger = LoggerFactory.getLogger(AdvisoryLock.class);
    private static Map<UUID, Connection> map = new HashMap<>();
    private static Map<UUID, String> lockMap = new HashMap<>();
    private static void initDataSource()
    {
        if (dataSource == null)
        {
            synchronized (AdvisoryLock.class)
            {
                //NOTE: must have a primary datasource or single datasource available
                dataSource = SpringContextUtils.getBean(DataSource.class);
            }
        }
    }

    public static UUID lock(String lockContext)
    {
        initDataSource();
        final UUID uuid = UUID.randomUUID();
        try
        {
            Connection con = dataSource.getConnection();
            doLock(con, lockContext);
            map.put(uuid, con);
            lockMap.put(uuid, lockContext);
        }
        catch (SQLException e)
        {
            throw new OmniException("Unable to get connection for lock=" + lockContext, e);
        }
        return uuid;

    }

    public static void releaseLock(UUID uuid)
    {
        Connection con = map.get(uuid);
        String lockContext = lockMap.get(uuid);
        if (con == null)
        {
            throw new OmniException(CommonErrorCode.SYSTEM_ERROR, "Cannot find lock with uuid=" + uuid);
        }
        map.remove(uuid);
        lockMap.remove(lockContext);
        doReleaseLock(con, lockContext);
    }
    /**
     * Note: For MySQL the advisory lock only applies to the current connection, if the connection terminates, it will
     * release the lock automatically.
     * If you try to lock multiple times on the same lockContext, for the same connection, you need to release multiple
     * times, it won't deadlock since version 5.7.5, please consult:
     * <a href="http://dev.mysql.com/doc/refman/5.7/en/miscellaneous-functions.html#function_get-lock">
     * http://dev.mysql.com/doc/refman/5.7/en/miscellaneous-functions.html#function_get-lock</a>
     */
    static protected void doLock(Connection con, final String lockContext) throws SQLException
    {
        logger.debug("Trying to acquire db lock for '{}'", lockContext);
        PreparedStatement stmt = con.prepareStatement("select get_lock(?,?)");
        stmt.setString(1, lockContext);
        stmt.setInt(2, ACQUIRE_BLOCKING_WAIT_SEC);
        try
        {
            final ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                final int lockResult = rs.getInt(1);
                if (lockResult == 1)
                {
                    // success
                    return;
                }

                final String errorMsgPrefix = "error acquire lock(" + lockContext + "," + ACQUIRE_BLOCKING_WAIT_SEC
                        + "): ";
                if (rs.wasNull())
                {
                    throw new SQLException(errorMsgPrefix + "unknown");
                }
                else if (lockResult == 0)
                {
                    // timeout
                    throw new SQLException(errorMsgPrefix + "timeout");
                }
            }
            // something else must be horribly wrong
            throw new SQLException(
                    "Please check your version of MySQL, to make sure it supports get_lock() & release_lock()");
        }
        finally
        {
            closeStatement(stmt);
        }
    }

    protected static void doReleaseLock(Connection con, final String lockContext)
    {
        logger.debug("Trying to release db lock for '{}'", lockContext);
        PreparedStatement stmt = null;
        try
        {
            stmt = con.prepareStatement("select release_lock(?)");
            stmt.setString(1, lockContext);

            final ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                final int releaseLockResult = rs.getInt(1);
                if (releaseLockResult == 1)
                {
                    // success
                    return;
                }

                final String errorMsgPrefix = "error release_lock(" + lockContext + "): ";
                if (rs.wasNull())
                {
                    throw new SQLException(errorMsgPrefix + "doesn't exist");
                }
                else if (releaseLockResult == 0)
                {
                    // failed release the lock
                    throw new SQLException(errorMsgPrefix + "not current connection's lock");
                }
            }
            // something else must be horribly wrong
            throw new SQLException(
                    "Please check your version of MySQL, to make sure it supports get_lock() & release_lock()");
        }
        catch (SQLException e)
        {
            logger.error("release_lock failed", e);
        }
        finally
        {
            if (stmt != null)
            {
                closeStatement(stmt);
            }
        }
    }

    private static void closeStatement(Statement stmt)
    {
        if (stmt != null)
        {
            try
            {
                stmt.close();
            }
            catch (SQLException ex)
            {
                logger.debug("Could not close JDBC statement", ex);
            }
            catch (Throwable ex)
            {
                logger.debug("Unexpected exception on closing JDBC statement", ex);
            }
        }

    }
}
