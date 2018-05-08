package com.omniselling.common.biz.auditlog.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import com.omniselling.common.biz.auditlog.AuditableModel;
import com.omniselling.common.biz.auditlog.AuditablePersistencyService;
import com.omniselling.common.biz.auditlog.IBizAuditLogService;
import com.omniselling.common.biz.auditlog.NonAuditableField;
import com.omniselling.common.enumeration.OmniEventAction;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BizBaseModel;
import com.omniselling.common.model.OmniEventLog;
import com.omniselling.common.model.OmniEventLogCondition;

public class BizAuditLogServiceImpl<M extends BizBaseModel<I>, I>
        implements IBizAuditLogService<M, I>
{
    //TODO, no impl
    private AuditablePersistencyService persistentOp;

    @Override
    public String getDomain()
    {
        return IBizAuditLogService.DOMAIN_NAME;
    }

    @Override
    public final void logEvent(OmniEventAction action, I modelId, String waitingFor, String result, String note,
            BaseOperatorInfo operator)
    {
        logEvent(action, modelId, waitingFor, result, null, note, operator);
    }

    @Override
    public final Collection<OmniEventLog> listLogByConditon(OmniEventLogCondition cond)
    {
        Collection<OmniEventLog> eventLogs = persistentOp.listByCondition(cond);
        return eventLogs;
    }

    @Override
    public final void logEvent(OmniEventAction action, M model, String waitingFor, String result, String note,
    		BaseOperatorInfo operator)
    {
        String logData = convertModelToLogData(model);
        logEvent(action, model.getId(), waitingFor, result, logData, note, operator);

    }

    private final void logEvent(OmniEventAction action, I modelId, String waitingFor, String result, String logData,
            String note, BaseOperatorInfo operator)
    {
        OmniEventLog eventLog = new OmniEventLog();
        eventLog.setAction(action);
        eventLog.setBusinessNum("" + modelId);
        eventLog.setCreatedBy(operator.getOperatorId());
        eventLog.setCreatedDate(new Date());
        eventLog.setLogData(logData);
        eventLog.setNote(note);
        eventLog.setResultEnum(result);
        eventLog.setWaitEventEnum(waitingFor);
        eventLog.setGuid(UUID.randomUUID().toString());
        persistentOp.createModelTx(eventLog, operator);

    }

    protected String convertModelToLogData(M model)
    {
        String res = null;
        @SuppressWarnings("rawtypes")
        Class<? extends BizBaseModel> clz = model.getClass();
        if (clz.isAnnotationPresent(AuditableModel.class))
        {
            Field[] fields = clz.getDeclaredFields();
            res = "{";
            for (Field field : fields)
            {
                if (field.isAnnotationPresent(NonAuditableField.class))
                {
                    continue;
                }
                field.setAccessible(true);
                res += "\"" + field.getName() + "\":\"";
                try
                {
                    res += getFieldValue(field.getName(), model) + "\",\n";
                }
                catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException
                        | IntrospectionException e)
                {
                    e.printStackTrace();
                    res += "\",\n";
                }

            }
            res += "}";
        }

        return res;

    }

    private Object getFieldValue(String field, Object object)
            throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field, object.getClass());
        Method readMethod = propertyDescriptor.getReadMethod();
        return readMethod.invoke(object, new Object[0]);
    }

    public void setPersistentOp(AuditablePersistencyService persistentOp)
    {
		this.persistentOp = persistentOp;
	}

}
