package com.omniselling.app.base.biz.service;

import java.util.List;

import com.omniselling.common.BizEnumSpec;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.ms.core.model.AppResource;
import com.omniselling.ms.core.model.AppResourceCond;

/**
 * 应用资源服务
 *
 */
public interface BizAppResourceService
{

    /**
     * 根据多条件(appName,resourceName...)查询应用资源信息
     * 
     * @param cond
     *            查询条件 cond
     * @param operatorInfo
     *            前端请求参数model
     * @return 操作成功，返回BasePageDetail<AppResource> 集合，操作失败，返回 null
     */
    public BaseResponse<BasePageDetail<AppResource>> listAppResources(AppResourceCond cond,
            BaseOperatorInfo operatorInfo);

    /**
     * 查询应用资源信息
     * 
     * @param cond
     *            查询条件 cond
     * @param operatorInfo
     *            前端请求参数model
     * @return 操作成功，返回List<AppResource> 集合，操作失败，返回 null
     */
    public BaseResponse<List<AppResource>> listAllAppResources(AppResourceCond cond, BaseOperatorInfo operatorInfo);

    /**
     * 查询父节点应用资源信息
     * 
     * @param applicationName
     *            应用资源名称
     * @param operatorInfo
     * @return 查询成功，返回List<AppResource>集合或者 null对象，查询失败，返回error
     */
    public BaseResponse<List<AppResource>> listAllMenuResources(String applicationName, BaseOperatorInfo operatorInfo);

    /**
     * 创建应用系统资源信息 AppResource model
     * 
     * @param appResource
     *            创建参数
     * @param operatorInfo
     *            前端请求参数model
     * @return 操作成功，数据库记录id
     */
    public BaseResponse<Long> createAppResource(AppResource appResource, BaseOperatorInfo operatorInfo);

    /**
     * 修改系统资源信息
     * 
     * @param appResource
     *            修改信息
     * @param operatorInfo
     *            前端请求参数model
     * @return 操作成功，返回true，操作失败，返回 false
     */
    public BaseResponse<Boolean> updateAppResource(AppResource appResource, BaseOperatorInfo operatorInfo);

    /**
     * 根据应用资源 id 获取资源信息
     * 
     * @param appResourceId
     *            应用资源主键id
     * @param operatorInfo
     *            前端请求参数model
     * @return 操作成功，AppResource对象，操作失败，返回 null
     */
    public BaseResponse<AppResource> getAppResourceById(Long appResourceId, BaseOperatorInfo operatorInfo);

    /**
     * 根据多个 应用资源 id ,查询系统资源信息(查询应用资源)
     * 
     * @param appResourceIds
     *            资源id集合
     * @param operatorInfo
     *            前端请求参数model
     * @return 操作成功，List<AppResource>集合，操作失败，返回 null
     */
    public BaseResponse<List<AppResource>> listAppResourceByIds(List<Long> appResourceIds, BaseOperatorInfo operatorInfo);

    /**
     * 通过应用资源名称、资源名称，查询应用资源信息
     * 
     * @param applicationName
     *            应用资源名称
     * @param appResourceNames
     *            资源名称
     * @param operatorInfo
     *            前端请求参数model
     * @return 操作成功，List<AppResource>集合，操作失败，返回 null
     */
    public BaseResponse<List<AppResource>> listAppResourceByNames(String applicationName,
            List<String> appResourceNames, BaseOperatorInfo operatorInfo);

    /**
     * 根据资源id列表移除应用系统资源信息
     * 
     * @param appResourceIds
     *            应用资源id
     * @param operatorInfo
     *            前端请求参数model
     * @return 操作成功，List<AppResource>集合，操作失败，返回 null
     */
    public BaseResponse<Boolean> removeAppResources(List<Long> appResourceIds, BaseOperatorInfo operatorInfo);

    /**
     * 查询应用资源类型
     * 
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<BizEnumSpec>> listResourceTypes(BaseOperatorInfo operatorInfo);

    /**
     * 根据id查询应用资源信息
     * 
     * @param appResoueceId
     * @return
     */
    public BaseResponse<AppResource> listAppResourceById(Long appResoueceId, BaseOperatorInfo operatorInfo);
}
