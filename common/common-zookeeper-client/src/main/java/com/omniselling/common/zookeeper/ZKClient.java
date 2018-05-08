package com.omniselling.common.zookeeper;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.framework.api.GetDataBuilder;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.omniselling.common.protocol.OmniException;

/**
 * 
 * @author xslong
 */
public class ZKClient
{

    static final Logger logger = LoggerFactory.getLogger(ZKClient.class);

    private CuratorFramework client;

    public ZKClient(String zkAddress)
    {
        this(zkAddress, 3000, 10);
    }

    public ZKClient(String zkAddress, int baseSleepTimeMs, int maxRetries)
    {
        client = CuratorFrameworkFactory.newClient(zkAddress, new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries));
        client.getConnectionStateListenable().addListener(new ConnectionStateListener()
        {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState)
            {
                logger.info("State Listener :{}", newState);
            }
        });
        client.start();
        
    }

    /**
     * 创建node
     * 
     * @param nodeName
     * @param value
     * @return
     */
    public boolean createNode(String nodeName, String value)
    {
        boolean suc = false;
        try
        {
            Stat stat = getClient().checkExists().forPath(nodeName);
            if (stat == null)
            {
                String opResult = null;
                if (Strings.isNullOrEmpty(value))
                {
                    opResult = getClient().create().creatingParentsIfNeeded().forPath(nodeName);
                }
                else
                {
                    opResult = getClient().create().creatingParentsIfNeeded().forPath(nodeName,
                            value.getBytes(Charsets.UTF_8));
                }
                suc = Objects.equal(nodeName, opResult);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return suc;
    }

    public boolean createEphemeralNode(String nodeName, String value)
    {
        try
        {
            Stat stat = getClient().checkExists().forPath(nodeName);
            if (stat != null)
            {
                deleteNode(nodeName);
            }
            getClient().create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath(nodeName, value.getBytes(Charsets.UTF_8));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 更新节点
     * 
     * @param nodeName
     * @param value
     * @return
     */
    public boolean updateNode(String nodeName, String value)
    {
        boolean suc = false;
        try
        {
            Stat stat = getClient().checkExists().forPath(nodeName);
            if (stat != null)
            {
                Stat opResult = getClient().setData().forPath(nodeName, value.getBytes(Charsets.UTF_8));
                suc = opResult != null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return suc;
    }

    /**
     * 节点是否存在
     * 
     * @param nodeName
     * @return
     */
    public boolean exists(String nodeName)
    {
        try
        {
            Stat stat = getClient().checkExists().forPath(nodeName);
            return stat != null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new OmniException("ZK error");
        }

    }

    /**
     * 删除节点
     * 
     * @param nodeName
     */
    public void deleteNode(String nodeName)
    {
        try
        {
            getClient().delete().deletingChildrenIfNeeded().forPath(nodeName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 找到指定节点下所有子节点的名称与值
     * 
     * @param node
     * @return
     */
    public Map<String, String> listChildrenDetail(String node)
    {
        Map<String, String> map = Maps.newHashMap();
        try
        {
            GetChildrenBuilder childrenBuilder = getClient().getChildren();
            List<String> children = childrenBuilder.forPath(node);
            GetDataBuilder dataBuilder = getClient().getData();
            if (children != null)
            {
                for (String child : children)
                {
                    String propPath = ZKPaths.makePath(node, child);
                    map.put(child, new String(dataBuilder.forPath(propPath), Charsets.UTF_8));
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 列出子节点的名称
     * 
     * @param node
     * @return
     */
    public List<String> listChildren(String node)
    {
        List<String> children = Lists.newArrayList();
        try
        {
            children = getClient().getChildren().forPath(node);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return children;
    }

    public String getData(String node)
    {
        try
        {
            byte[] data = getClient().getData().forPath(node);
            if (data == null)
                return null;
            return new String(data, Charsets.UTF_8);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 增加监听
     * 
     * @param node
     * @param isSelf
     *            true 为node本身增加监听 false 为node的子节点增加监听
     * @throws Exception
     */
    public void addWatch(String node, boolean isSelf) throws Exception
    {
        if (isSelf)
        {
            getClient().getData().watched().forPath(node);
        }
        else
        {
            getClient().getChildren().watched().forPath(node);
        }
    }

    /**
     * 增加监听
     * 
     * @param node
     * @param isSelf
     *            true 为node本身增加监听 false 为node的子节点增加监听
     * @param watcher
     * @throws Exception
     */
    public void addWatch(String node, boolean isSelf, Watcher watcher) throws Exception
    {
        if (isSelf)
        {
            getClient().getData().usingWatcher(watcher).forPath(node);
        }
        else
        {
            getClient().getChildren().usingWatcher(watcher).forPath(node);
        }
    }

    /**
     * 增加监听
     * 
     * @param node
     * @param isSelf
     *            true 为node本身增加监听 false 为node的子节点增加监听
     * @param watcher
     * @throws Exception
     */
    public void addWatch(String node, boolean isSelf, CuratorWatcher watcher) throws Exception
    {
        if (isSelf)
        {
            getClient().getData().usingWatcher(watcher).forPath(node);
        }
        else
        {
            getClient().getChildren().usingWatcher(watcher).forPath(node);
        }
    }

    /**
     * 销毁资源
     */
    public void destory()
    {
        if (client != null)
        {
            client.close();
        }
    }

    /**
     * 获取client
     * 
     * @return
     */
    public CuratorFramework getClient()
    {
        return client;
    }

}
