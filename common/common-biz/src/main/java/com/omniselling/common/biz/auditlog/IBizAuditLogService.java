package com.omniselling.common.biz.auditlog;

import java.util.Collection;

import com.omniselling.common.biz.operation.IBizDomainOperation;
import com.omniselling.common.enumeration.OmniEventAction;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BizBaseModel;
import com.omniselling.common.model.OmniEventLog;
import com.omniselling.common.model.OmniEventLogCondition;

public interface IBizAuditLogService<M extends BizBaseModel<I>, I>
        extends IBizDomainOperation
{
    static String DOMAIN_NAME = "AuditLog";

    /**
     * 通用log事件, 不是OmniEvent来的
     * @param action
     * @param businessNum
     * @param waitingFor
     * @param result
     * @param note - optional 
     * @param operatorId
     * @return
     */
    void logEvent(OmniEventAction action, I modelId, String waitingFor, String result, String note, BaseOperatorInfo operator);

    /**
     * 根據bizModel的{@link AuditableField}去記錄log
     * @param action
     * @param model
     * @param note TODO
     * @param operatorId TODO
     * @return
     */
    void logEvent(OmniEventAction action, M model, String waitingFor, String result, String note, BaseOperatorInfo operator);

    /**
     * 查詢eventlog
     * @param cond
     * @return
     */
    Collection<OmniEventLog> listLogByConditon(OmniEventLogCondition cond);

}
