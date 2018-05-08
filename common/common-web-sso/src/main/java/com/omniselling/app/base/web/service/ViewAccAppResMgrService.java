package com.omniselling.app.base.web.service;

import java.util.List;

import com.omniselling.app.base.web.model.ViewAccount;
import com.omniselling.app.base.web.model.ViewAccountAppResCondition;
import com.omniselling.app.base.web.model.ViewAccountAppResource;
import com.omniselling.app.base.web.model.ViewAcountRefAppResource;
import com.omniselling.app.base.web.model.ViewEnumSpec;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;

public interface ViewAccAppResMgrService
{

    /**
     * 查询账号资源权限信息
     * 
     * @param viewAccountAppResCondition
     *            根据多条件进行查询
     * @param operatorInfo
     * @return 查询成功，返回ViewAccountAppResource集合或者null，查询失败，返回错误信息
     */
    BaseResponse<BasePageDetail<ViewAccountAppResource>> listAccountAppRes(
            ViewAccountAppResCondition viewAccountAppResCondition, BaseOperatorInfo operatorInfo);

    /**
     * 根据accAppresIds删除账号资源权限信息
     * 
     * @param accAppresIds
     *            账号资源权限id
     * @param operatorInfo
     * @return 删除成功，返回true，删除失败，返回false
     */
    BaseResponse<Boolean> deleteAccountAppRes(List<Long> accAppresIds, BaseOperatorInfo operatorInfo);

    /**
     * 添加账号资源权限信息
     * 
     * @param viewAccount
     *            账号资源权限信息
     * @param operatorInfo
     * @return 添加成功，返回true，添加失败，返回false
     */
    BaseResponse<Boolean> addAccountAppRes(ViewAcountRefAppResource viewAccount, BaseOperatorInfo operatorInfo);
    
    /**
     * 查询应用资源权限类型
     * 
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<ViewEnumSpec>> listPermitTypes(BaseOperatorInfo operatorInfo);

    /**
     * 获取主账号
     * 
     * @param operatorInfo
     * @param loginIdLike TODO
     * @return
     */
    BaseResponse<List<ViewAccount>> fetchAccount(BaseOperatorInfo operatorInfo, String loginIdLike);

}
