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

/**
 * 子账户应用资源管理
 * 
 * @author wlq
 *
 */
public interface ViewSubAccountResMgrService
{
    /**
     * 查询主账号户下子账户对应的应用资源
     * 
     * @param vAccResCond
     * @param baseOperatorInfo TODO
     * @return
     */
    public BaseResponse<BasePageDetail<ViewAccountAppResource>> listSubAccAppRes(
            ViewAccountAppResCondition vAccResCond, BaseOperatorInfo baseOperatorInfo);

    /**
     * 查询主账号户对应的应用资源
     * 
     * @param vAccResCond
     * @param baseOperatorInfo
     *            TODO
     * @return
     */
    public BaseResponse<BasePageDetail<ViewAccountAppResource>> listMainAccAppRes(
            ViewAccountAppResCondition vAccResCond, BaseOperatorInfo baseOperatorInfo);

    /**
     * 删除子帐号对应的资源
     * 
     * @param accResRelIds
     * @param baseOperatorInfo
     *            TODO
     * @return
     */
    public BaseResponse<Boolean> deleteSubAccAppRes(List<Long> accResRelIds, BaseOperatorInfo baseOperatorInfo);

    /**
     * 添加子账户应用资源
     * 
     * @param vSubAccAppResAccess
     * @param operatorInfo TODO
     * @return
     */
    public BaseResponse<Boolean> addSubAccAppRes(ViewAcountRefAppResource vAccRefAppRes, BaseOperatorInfo operatorInfo);

    /**
     * 查询主账户下所有子账户
     * 
     * @param loginIdLike
     * @return
     */
    public BaseResponse<List<ViewAccount>> listSubAccounts(String loginIdLike, BaseOperatorInfo baseOperatorInfo);

    /**
     * 查询应用资源类型
     * @param operatorInfo TODO
     * 
     * @return
     */
    public BaseResponse<List<ViewEnumSpec>> listAppResTypes(BaseOperatorInfo operatorInfo);
}
