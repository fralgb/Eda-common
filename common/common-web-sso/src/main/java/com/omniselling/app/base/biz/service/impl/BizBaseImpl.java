package com.omniselling.app.base.biz.service.impl;

import com.omniselling.common.model.BaseOperatorInfo;

public class BizBaseImpl
{

    protected Boolean validBaseOperatorInfo(BaseOperatorInfo operatorInfo)
    {
        if (operatorInfo == null || operatorInfo.getOperatorId() == null || operatorInfo.getLanguage() == null
                || "".equals(operatorInfo.getLanguage()))
        {
            return false;
        }

        return true;
    }
}
