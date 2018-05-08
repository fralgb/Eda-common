package com.omniselling.app.base.biz.service;

import java.util.List;

import com.omniselling.common.BizEnumSpec;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.ms.core.model.I18nResLangAndKeys;
import com.omniselling.ms.core.model.I18nResource;
import com.omniselling.ms.core.model.I18nResourceCond;
import com.omniselling.ms.core.model.I18nResourceKey;
import com.omniselling.ms.core.model.I18nResourceKeyCond;

/**
 * 
 * i18n国际化资源服务
 *
 */
public interface BizI18nResourceService
{

    /**
     * 
     * @param i18nResources
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> createI18nRes(List<I18nResource> i18nResources, BaseOperatorInfo operatorInfo);

    /**
     * 
     * @param cond
     * @param operatorInfo
     * @return
     */
    public BaseResponse<BasePageDetail<I18nResource>> listI18nRes(I18nResourceCond cond, BaseOperatorInfo operatorInfo);

    /**
     * 
     * @param keyCond
     * @param operatorInfo
     * @return
     */
    public BaseResponse<BasePageDetail<I18nResourceKey>> listI18nResourceKeys(I18nResourceKeyCond keyCond,
            BaseOperatorInfo operatorInfo);

    /**
     * 
     * @param resourceId
     * @param operatorInfo
     * @return
     */
    public BaseResponse<I18nResource> getI18nResById(Long resourceId, BaseOperatorInfo operatorInfo);

    /**
     * 
     * @param resourceIds
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<I18nResource>> listI18nResByIds(List<Long> resourceIds, BaseOperatorInfo operatorInfo);

    /**
     * 
     * @param resLangAndKeys
     * @param operatorInfo
     * @return
     */
    public BaseResponse<List<I18nResource>> listI18nResByLangAndKeys(I18nResLangAndKeys resLangAndKeys,
            BaseOperatorInfo operatorInfo);

    /**
     * 
     * @param resLangAndKeys
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> deleteI18nResByLangAndKeys(I18nResLangAndKeys resLangAndKeys,
            BaseOperatorInfo operatorInfo);

    /**
     * 
     * @param i18nResources
     * @param operatorInfo
     * @return
     */
    public BaseResponse<Boolean> updateI18nRes(List<I18nResource> i18nResources, BaseOperatorInfo operatorInfo);

    /**
     * 获取国际化语言类型
     * 
     * @param baseOperatorInfo
     * @return
     */
    public BaseResponse<List<BizEnumSpec>> listLanguageTypes(BaseOperatorInfo baseOperatorInfo);
}
