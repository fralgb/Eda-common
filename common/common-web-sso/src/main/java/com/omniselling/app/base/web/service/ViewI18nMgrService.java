package com.omniselling.app.base.web.service;

import java.util.List;
import java.util.Map;

import com.omniselling.app.base.web.model.ViewI18nResAppNameAndKey;
import com.omniselling.app.base.web.model.ViewI18nResourceCondition;
import com.omniselling.app.base.web.model.ViewI18nResourceKey;
import com.omniselling.app.base.web.model.ViewImportI18nResource;
import com.omniselling.app.base.web.model.ViewLang;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;

/**
 * i18n 管理接口
 * 
 * @author baby
 *
 */
public interface ViewI18nMgrService
{
    /**
     * 根据筛选条件，获取i18n列表
     * 
     * @param viewI18nResourceCondition
     *            查询i18n 条件信息
     * @param basePerPageInfo
     * @return 查询成功时，返回list<ViewI18nResource> 集合，查询失败时，返回错误信息
     */
    public BaseResponse<Map<String, String>> listI18n(
            ViewI18nResourceCondition viewI18nResourceCondition, BaseOperatorInfo baseOperatorInfo);
    
    /**
     * 
     * @param resourceCondition
     * @param baseOperatorInfo
     * @return
     */
    public BaseResponse<BasePageDetail<ViewI18nResourceKey>> listI18nResKeys(ViewI18nResourceCondition resourceCondition,
            BaseOperatorInfo baseOperatorInfo);

    /**
     * 保存i18n
     * 
     * @param viewI18nRes
     *            保存i18n信息
     * @param basePerPageInfo
     * @return 保存成功时：返回true；保存失败时：返回false
     */
    public BaseResponse<Boolean> createI18nRes(List<ViewI18nResourceKey> viewI18nRes, BaseOperatorInfo baseOperatorInfo);

    /**
     * 删除i18n
     * 
     * @param vI18nResCond
     *            i18n删除条件model
     * @param baseOperatorInfo
     * @return 成功时：返回true；失败时：返回false
     */
    public BaseResponse<Boolean> deleteI18n(List<ViewI18nResAppNameAndKey> vI18nResCond, BaseOperatorInfo baseOperatorInfo);

    /**
     * 更新i18n
     * 
     * @param viewI18nRes
     *            更新i18n信息
     * @param basePerPageInfo
     * @return 更新成功时：返回true；更新失败时：返回false
     */
    public BaseResponse<Boolean> updateI18nRes(List<ViewI18nResourceKey> viewI18nRes, BaseOperatorInfo baseOperatorInfo);

    /**
     * 获取语言列表
     * 
     * @param basePerPageInfo
     * @return
     */
    public BaseResponse<List<ViewLang>> fetchLang(BaseOperatorInfo baseOperatorInfo);

    /**
     * 导入i18n
     * 
     * @param basePerPageInfo
     * @return 倒入成功，返回true；倒入失败，返回false
     */
    public BaseResponse<Boolean> importI18n(ViewImportI18nResource vImportI18nResource,BaseOperatorInfo baseOperatorInfo);

    /**
     * 获得i18n 模板
     * 
     * @return String 查询成功，返回string字符；查询失败，返回错误信息
     */
    public BaseResponse<String> downloadI18nTemplate(String downloadPath,BaseOperatorInfo baseOperatorInfo);

    /**
     * 根据key获取i18n信息
     * 
     * @param resourceKey
     * @param basePerPageInfo
     * @return
     */
    public BaseResponse<ViewI18nResourceKey> getI18n(ViewI18nResAppNameAndKey vI18nResourceCond,
            BaseOperatorInfo operatorInfo);
    
    /**
     * 获得页面i18n资源新的的json字符串
     * @param i18nResCond
     * @param baseOperatorInfo
     * @return
     */
    public BaseResponse<String> listPageI18n(ViewI18nResourceCondition i18nResCond,
            BaseOperatorInfo baseOperatorInfo);

}
