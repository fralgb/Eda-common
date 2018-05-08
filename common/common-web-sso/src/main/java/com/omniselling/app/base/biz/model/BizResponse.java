package com.omniselling.app.base.biz.model;

import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ErrorInfo;

public class BizResponse<T> extends BaseResponse<T>
{

    public ErrorInfo getErrorInfo()
    {
        return (getErrors() == null || getErrors().size() == 0) ? null : getErrors().get(0);
    }

}
