package com.omniselling.app.base.web.service;

import java.util.List;

import com.omniselling.app.base.web.model.ViewAccountPubResCondition;
import com.omniselling.app.base.web.model.ViewAccountPubResource;
import com.omniselling.app.base.web.model.ViewAccountRefPubResource;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;

/**
 * 账号公共资源权限管理页面view service
 *
 */
public interface ViewAccPubResMgrService
{
    
    /**
     * 删除公共资源权限
     * 
     * @param accPubResPermitIds
     *            账户公共资源权限编号
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> deleteAccountPubRes(List<Long> accPubResPermitIds, BaseOperatorInfo operatorInfo);

    /**
     * 添加账户公共资源权限
     * 
     * @param viewResource
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> addAccountPubRes(ViewAccountRefPubResource viewResource,
            BaseOperatorInfo operatorInfo);

    /**
     * 查询账号公共资源
     * 
     * @param condition
     * @param operatorInfo
     * @return
     */
    public BaseResponse<BasePageDetail<ViewAccountPubResource>> listAccountPubRes(ViewAccountPubResCondition condition,
            BaseOperatorInfo operatorInfo);
}
