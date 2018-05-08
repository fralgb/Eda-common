package com.omniselling.app.base.web.service;

import java.util.List;

import com.omniselling.app.base.web.model.ViewAppResource;
import com.omniselling.app.base.web.model.ViewAppResourceCondition;
import com.omniselling.app.base.web.model.ViewEnumSpec;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;

/**
 * 应用资源service
 * 
 * @author Administrator
 *
 */
public interface ViewAppResMgrService
{
    /**
     * 根据条件查询 应用资源信息
     * 
     * @param importRequest
     *            查询条件，按照资源名，资源类型，资源路径，应用名 进行查询
     * @return 查询成功，返回list集合，查询失败，返回错信息
     */
    public BaseResponse<BasePageDetail<ViewAppResource>> listAppResources(
            ViewAppResourceCondition viewAppResourceCondition, BaseOperatorInfo operatorInfo);

    /**
     * 查询资源父节点
     * 
     * @param applicationName
     *            应用资源名称
     * @param operatorInfo
     * @return 查询成功，返回List<ViewAppResource>集合或者 null，查询失败，返回错误信息
     */
    public BaseResponse<List<ViewAppResource>> listAllMenuResources(String applicationName,
            BaseOperatorInfo operatorInfo);

    /**
     * 根据条件查询 应用资源信息
     * 
     * @param importRequest
     *            查询条件，按照资源名，资源类型，资源路径，应用名 进行查询
     * @return 查询成功，返回list集合，查询失败，返回错信息
     */
    public BaseResponse<List<ViewAppResource>> listAllAppResources(ViewAppResourceCondition viewAppResourceCondition,
            BaseOperatorInfo operatorInfo);

    /**
     * 保存应用资源信息
     * 
     * @param importRequest
     *            需要保存菜单资源信息
     * @return 保存成功，返回true ，保存失败，返回false
     */
    public BaseResponse<Boolean> saveAppResource(ViewAppResource viewAppResource, BaseOperatorInfo operatorInfo);

    /**
     * 删除应用资源信息
     * 
     * @param appResoueceId
     *            删除菜单资源id
     * @return 删除成功，返回true ，删除失败，返回false
     */
    public BaseResponse<Boolean> deleteAppResource(Long appResoueceId, BaseOperatorInfo operatorInfo);

    /**
     * 查询应用资源类型
     * 
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<ViewEnumSpec>> listAppResourceTypes(BaseOperatorInfo operatorInfo);

    /**
     * 根据id查询应用资源信息
     * 
     * @param appResoueceId
     * @param operatorInfo
     * @return
     */
    public BaseResponse<ViewAppResource> listAppResourceById(Long appResoueceId, BaseOperatorInfo operatorInfo);
}
