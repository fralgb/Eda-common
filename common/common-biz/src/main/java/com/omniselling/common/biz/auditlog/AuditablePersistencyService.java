package com.omniselling.common.biz.auditlog;

import java.util.Collection;

import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.OmniEventLog;
import com.omniselling.common.model.OmniEventLogCondition;

/**
 * TODO: find an actual persistency solution to this
 * @author Atomic
 *
 */
public interface AuditablePersistencyService
{

    Collection<OmniEventLog> listByCondition(OmniEventLogCondition cond);

    void createModelTx(OmniEventLog eventLog, BaseOperatorInfo operator);

}
