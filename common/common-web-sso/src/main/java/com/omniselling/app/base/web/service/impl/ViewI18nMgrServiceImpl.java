package com.omniselling.app.base.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.omniselling.app.base.biz.service.BizI18nResourceService;
import com.omniselling.app.base.common.OmniConstant;
import com.omniselling.app.base.web.model.ViewI18nPageResource;
import com.omniselling.app.base.web.model.ViewI18nResAppNameAndKey;
import com.omniselling.app.base.web.model.ViewI18nResourceCondition;
import com.omniselling.app.base.web.model.ViewI18nResourceItem;
import com.omniselling.app.base.web.model.ViewI18nResourceKey;
import com.omniselling.app.base.web.model.ViewImportI18nResource;
import com.omniselling.app.base.web.model.ViewLang;
import com.omniselling.app.base.web.service.ViewI18nMgrService;
import com.omniselling.common.BizEnumSpec;
import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.model.BaseOperatorInfo;
import com.omniselling.common.model.BasePageDetail;
import com.omniselling.common.model.BaseResponse;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.model.ErrorInfo.SEVERITY;
import com.omniselling.common.util.ErrorInfoBuilder;
import com.omniselling.common.util.ExcelUtil;
import com.omniselling.common.util.StringUtils;
import com.omniselling.ms.core.model.I18nResLangAndKeys;
import com.omniselling.ms.core.model.I18nResource;
import com.omniselling.ms.core.model.I18nResourceCond;
import com.omniselling.ms.core.model.I18nResourceForImport;
import com.omniselling.ms.core.model.I18nResourceKey;
import com.omniselling.ms.core.model.I18nResourceKeyCond;
import com.omniselling.ms.core.model.I18nResourceValue;

public class ViewI18nMgrServiceImpl implements ViewI18nMgrService
{
    private BizI18nResourceService bizI18nResourceService;

    public void setBizI18nResourceService(BizI18nResourceService bizI18nResourceService)
    {
        this.bizI18nResourceService = bizI18nResourceService;
    }

    @Override
    public BaseResponse<Map<String, String>> listI18n(ViewI18nResourceCondition vI18nResCond,
            BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<Map<String, String>> baseResponse = new BaseResponse<Map<String, String>>();
        Map<String, String> map = new HashMap<String, String>();
        if (vI18nResCond.getApplicationName() == null || vI18nResCond.getLanguageCode() == null)
        {
            baseResponse.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseResponse;
        }
        I18nResourceCond i18nResCond = new I18nResourceCond();
        BeanUtils.copyProperties(vI18nResCond, i18nResCond);

        BaseResponse<BasePageDetail<I18nResource>> bizRes = bizI18nResourceService.listI18nRes(i18nResCond,
                baseOperatorInfo);
        if (bizRes.hasError())
        {
            baseResponse.addErrors(bizRes.getErrors());
            return baseResponse;
        }
        if (bizRes.getData() == null || bizRes.getData().getDatas().size() == 0
                || bizRes.getData().getDatas().isEmpty())
        {
            baseResponse.setData(map);
        }

        for (I18nResource i18nRes1 : bizRes.getData().getDatas())
        {
            map.put(i18nRes1.getResourceKey(), i18nRes1.getResourceValue());
        }
        baseResponse.setData(map);

        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> createI18nRes(List<ViewI18nResourceKey> viewI18nResource,
            BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<Boolean>();
        if (viewI18nResource == null || viewI18nResource.isEmpty())
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseRes;
        }

        // 校验字段长度和是否为空
        for (ViewI18nResourceKey viewI18nRes : viewI18nResource)
        {
            baseRes = viewI18nRes.validate(baseOperatorInfo.getLanguage());
            if (baseRes.hasError())
            {
                return baseRes;
            }
        }
        List<I18nResource> i18nResCondList = new ArrayList<>();
        for (ViewI18nResourceKey vI18nRes : viewI18nResource)
        {
            for (ViewI18nResourceItem vI18nResItem : vI18nRes.getI18nResourceItems())
            {
                I18nResource i18nResource = new I18nResource();
                i18nResource.setApplicationName(vI18nRes.getApplicationName());
                i18nResource.setLanguageCode(vI18nResItem.getI18nCode());
                i18nResource.setResourceKey(vI18nResItem.getResourceKey());
                i18nResource.setResourceValue(vI18nResItem.getResourceValue());
                i18nResCondList.add(i18nResource);
            }
        }
        BaseResponse<Boolean> bizRes = bizI18nResourceService.createI18nRes(i18nResCondList, baseOperatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        baseRes.setData(bizRes.getData());
        return baseRes;
    }

    public BaseResponse<ViewI18nResourceKey> getI18nResById(Long resourceId, BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<ViewI18nResourceKey> baseRes = new BaseResponse<ViewI18nResourceKey>();
        if (resourceId == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseRes;
        }
        BaseResponse<I18nResource> bizRes = bizI18nResourceService.getI18nResById(resourceId, baseOperatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        I18nResource i18nResource = bizRes.getData();
        ViewI18nResourceKey vI18nRes = new ViewI18nResourceKey();
        vI18nRes.setResourceKey(i18nResource.getResourceKey());
        List<ViewI18nResourceItem> vI18nResItemList = new ArrayList<>();
        ViewI18nResourceItem vI18nResItem = new ViewI18nResourceItem();
        vI18nResItem.setI18nCode(i18nResource.getLanguageCode());
        vI18nResItem.setResourceKey(i18nResource.getResourceKey());
        vI18nResItem.setResourceValue(i18nResource.getResourceValue());
        vI18nResItem.setResoureGroup(null);
        vI18nResItemList.add(vI18nResItem);

        vI18nRes.setI18nResourceItems(vI18nResItemList);
        baseRes.setData(vI18nRes);
        return baseRes;
    }

    public BaseResponse<List<I18nResource>> listI18nResByLangAndKeys(I18nResLangAndKeys resLangAndKeys,
            BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<List<I18nResource>> baseRes = new BaseResponse<List<I18nResource>>();
        if (resLangAndKeys.getResourceKeys() == null || resLangAndKeys.getResourceKeys().isEmpty())
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseRes;
        }

        BaseResponse<List<I18nResource>> bizRes = bizI18nResourceService.listI18nResByLangAndKeys(resLangAndKeys,
                baseOperatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        baseRes.setData(bizRes.getData());
        return baseRes;
    }

    public BaseResponse<List<I18nResource>> listI18nResByIds(List<Long> resourceIds, BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<List<I18nResource>> baseRes = new BaseResponse<List<I18nResource>>();
        if (resourceIds == null || resourceIds.isEmpty())
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseRes;
        }

        BaseResponse<List<I18nResource>> bizRes = bizI18nResourceService
                .listI18nResByIds(resourceIds, baseOperatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        baseRes.setData(bizRes.getData());
        return baseRes;
    }

    @Override
    public BaseResponse<Boolean> updateI18nRes(List<ViewI18nResourceKey> viewI18nResource,
            BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<Boolean>();
        if (viewI18nResource == null || viewI18nResource.isEmpty())
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseRes;
        }

        // 校验字段长度和是否为空
        for (ViewI18nResourceKey viewI18nRes : viewI18nResource)
        {
            baseRes = viewI18nRes.validate(baseOperatorInfo.getLanguage());
            if (baseRes.hasError())
            {
                return baseRes;
            }
        }
        List<I18nResource> i18nResCondList = new ArrayList<>();
        for (ViewI18nResourceKey vI18nRes : viewI18nResource)
        {
            for (ViewI18nResourceItem vI18nResItem : vI18nRes.getI18nResourceItems())
            {
                I18nResource i18nResource = new I18nResource();
                if (vI18nRes.getApplicationName() != null && !"".equals(vI18nRes.getApplicationName()))
                {
                    i18nResource.setApplicationName(vI18nRes.getApplicationName());
                }
                i18nResource.setLanguageCode(vI18nResItem.getI18nCode());
                i18nResource.setResourceKey(vI18nResItem.getResourceKey());
                i18nResource.setResourceValue(vI18nResItem.getResourceValue());
                i18nResCondList.add(i18nResource);
            }
        }
        BaseResponse<Boolean> bizRes = bizI18nResourceService.updateI18nRes(i18nResCondList, baseOperatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        baseRes.setData(bizRes.getData());
        return baseRes;
    }

    @Override
    public BaseResponse<ViewI18nResourceKey> getI18n(ViewI18nResAppNameAndKey vI18nResourceCond,
            BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<ViewI18nResourceKey> baseRes = new BaseResponse<ViewI18nResourceKey>();
        if (vI18nResourceCond == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseRes;
        }
        I18nResourceCond i18nResCond = new I18nResourceCond();
        i18nResCond.setApplicationName(vI18nResourceCond.getApplicationName());
        i18nResCond.setResourceKey(vI18nResourceCond.getResourceKey());
        BaseResponse<BasePageDetail<I18nResource>> bizRes = bizI18nResourceService.listI18nRes(i18nResCond,
                baseOperatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        BasePageDetail<I18nResource> perI18nRes = bizRes.getData();
        List<I18nResource> i18nResList = perI18nRes.getDatas();

        ViewI18nResourceKey vI18nRes = new ViewI18nResourceKey();
        for (I18nResource i18nRes : i18nResList)
        {
            if (i18nRes.getResourceKey().equals(vI18nRes.getResourceKey()))
            {
                continue;
            }
            vI18nRes.setApplicationName(i18nRes.getApplicationName());
            vI18nRes.setResourceKey(i18nRes.getResourceKey());
            vI18nRes.setResoureGroup(i18nRes.getResourceKey());
            List<ViewI18nResourceItem> vI18nResItemList = new ArrayList<>();
            for (I18nResource i18nResource : i18nResList)
            {
                ViewI18nResourceItem vI18nResItem = new ViewI18nResourceItem();
                vI18nResItem.setI18nCode(i18nResource.getLanguageCode());
                vI18nResItem.setResourceKey(i18nResource.getResourceKey());
                vI18nResItem.setResourceValue(i18nResource.getResourceValue());
                vI18nResItem.setResoureGroup(i18nResource.getResourceKey());
                vI18nResItemList.add(vI18nResItem);
            }
            vI18nRes.setI18nResourceItems(vI18nResItemList);
        }
        baseRes.setData(vI18nRes);
        return baseRes;
    }

    @Override
    public BaseResponse<List<ViewLang>> fetchLang(BaseOperatorInfo baseOperatorInfo)
    {

        BaseResponse<List<ViewLang>> baseResponse = new BaseResponse<>();
        List<ViewLang> list = new ArrayList<>();
        BaseResponse<List<BizEnumSpec>> bizBaseResponse = bizI18nResourceService.listLanguageTypes(baseOperatorInfo);
        if (bizBaseResponse.hasError())
        {
            baseResponse.addErrors(bizBaseResponse.getErrors());
            return baseResponse;
        }
        if (bizBaseResponse.hasData() && bizBaseResponse.getData().size() > 0)
        {
            for (BizEnumSpec enumSpec : bizBaseResponse.getData())
            {
                ViewLang viewLang = new ViewLang();
                viewLang.setI18nCode(enumSpec.getCode());
                viewLang.setI18nCodeLabel(enumSpec.getLabel());
                list.add(viewLang);
            }
            baseResponse.setData(list);
        }

        return baseResponse;
    }

    @Override
    public BaseResponse<Boolean> deleteI18n(List<ViewI18nResAppNameAndKey> vI18nResList, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<Boolean>();
        if (vI18nResList == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage())));
            return baseRes;
        }
        List<I18nResLangAndKeys> resAppNameAndKeysList = transToI18nResLangAndKeys(vI18nResList);
        for (I18nResLangAndKeys resLangAndKeys : resAppNameAndKeysList)
        {
            BaseResponse<Boolean> bizRes = bizI18nResourceService.deleteI18nResByLangAndKeys(resLangAndKeys,
                    operatorInfo);
            if (bizRes.hasError())
            {
                baseRes.addErrors(bizRes.getErrors());
                return baseRes;
            }
        }
        baseRes.setData(true);
        return baseRes;
    }

    private List<I18nResLangAndKeys> transToI18nResLangAndKeys(List<ViewI18nResAppNameAndKey> vI18nResList)
    {
        List<I18nResLangAndKeys> resNameAndKeysList = new ArrayList<>();
        Map<String, I18nResLangAndKeys> langAndKeysMap = new HashMap<String, I18nResLangAndKeys>();
        for (ViewI18nResAppNameAndKey viewAppNameAndKey : vI18nResList)
        {
            String applicationName = viewAppNameAndKey.getApplicationName();
            I18nResLangAndKeys i18nResLangAndKeys = langAndKeysMap.get(applicationName);
            if (i18nResLangAndKeys == null)
            {
                i18nResLangAndKeys = new I18nResLangAndKeys();
                i18nResLangAndKeys.setApplicationName(applicationName);
                i18nResLangAndKeys.setResourceKeys(new ArrayList<String>());
                resNameAndKeysList.add(i18nResLangAndKeys);
                langAndKeysMap.put(applicationName, i18nResLangAndKeys);
            }
            List<String> resourceKeys = i18nResLangAndKeys.getResourceKeys();
            resourceKeys.add(viewAppNameAndKey.getResourceKey());
        }
        return resNameAndKeysList;
    }

    @Override
    public BaseResponse<String> listPageI18n(ViewI18nResourceCondition i18nResCond, BaseOperatorInfo baseOperatorInfo)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BaseResponse<Boolean> importI18n(ViewImportI18nResource vImportI18nResource, BaseOperatorInfo operatorInfo)
    {
        BaseResponse<Boolean> baseRes = new BaseResponse<Boolean>();
        // 参数验证
        if (vImportI18nResource == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "viewImportI18nResource is null "));
            return baseRes;
        }
        MultipartFile multipartFile = vImportI18nResource.getFile();
        // 判断导入文件类型
        String suffix = multipartFile.getOriginalFilename().toLowerCase();
        if (!suffix.endsWith(".xlsx"))
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()), "The file type error"));
            baseRes.setData(false);
            return baseRes;
        }
        // 转换上传文件为xlsx 2007
        List<I18nResourceForImport> xlsList = null;
        try
        {
            if (multipartFile.getOriginalFilename() != null)
            {
                ExcelUtil<I18nResourceForImport> excelUtil = new ExcelUtil<I18nResourceForImport>();
                xlsList = excelUtil.readReportFile(I18nResourceForImport.class, multipartFile.getInputStream());
            }
        }
        catch (Exception e)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, e, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "upload :readReportFile Error"));
            return baseRes;
        }
        if (xlsList == null || xlsList.size() == 0 || xlsList.size() > 5000)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.NO_DATA, ErrorInfo.SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(operatorInfo.getLanguage()),
                    "upload : readContent null "));
            return baseRes;
        }
        // 将导入的数据转化为ms需要的数据
        List<I18nResource> i18nResources = convertI18nResource(xlsList);
        BaseResponse<Boolean> bizRes = bizI18nResourceService.updateI18nRes(i18nResources, operatorInfo);
        baseRes.setData(bizRes.getData());
        return baseRes;
    }

    @Override
    public BaseResponse<String> downloadI18nTemplate(String downloadPath, BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<String> baseRes = new BaseResponse<>();
        String path = downloadPath + OmniConstant.I18nImportTemplate;
        baseRes.setData(path);
        return baseRes;
    }

    @Override
    public BaseResponse<BasePageDetail<ViewI18nResourceKey>> listI18nResKeys(
            ViewI18nResourceCondition resourceCondition, BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<BasePageDetail<ViewI18nResourceKey>> baseRes = new BaseResponse<BasePageDetail<ViewI18nResourceKey>>();
        if (resourceCondition == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseRes;
        }
        I18nResourceKeyCond i18nResourceKeyCond = new I18nResourceKeyCond();
        BeanUtils.copyProperties(resourceCondition, i18nResourceKeyCond);
        i18nResourceKeyCond.setOffset(resourceCondition.getOffset());
        i18nResourceKeyCond.setRowsPerPage(resourceCondition.getRowsPerPage());
        BaseResponse<BasePageDetail<I18nResourceKey>> res = bizI18nResourceService.listI18nResourceKeys(
                i18nResourceKeyCond, baseOperatorInfo);
        if (res.hasError())
        {
            baseRes.addErrors(res.getErrors());
            return baseRes;
        }

        if (!res.hasData())
        {
            return baseRes;
        }

        BasePageDetail<I18nResourceKey> pageDetail = res.getData();
        BasePageDetail<ViewI18nResourceKey> viewDetail = new BasePageDetail<ViewI18nResourceKey>();
        viewDetail.setTotal(pageDetail.getTotal());
        baseRes.setData(viewDetail);
        if (pageDetail.getDatas() == null || pageDetail.getDatas().isEmpty())
        {
            return baseRes;
        }

        // 转换成viewmodel
        List<ViewI18nResourceKey> viewI18nResourceKeyList = new ArrayList<ViewI18nResourceKey>();
        List<I18nResourceKey> resourceKeyList = pageDetail.getDatas();
        for (I18nResourceKey i18nResourceKey : resourceKeyList)
        {
            ViewI18nResourceKey viewI18nResourceKey = new ViewI18nResourceKey();
            viewI18nResourceKey.setApplicationName(i18nResourceKey.getApplicationName());
            viewI18nResourceKey.setResourceKey(i18nResourceKey.getResourceKey());
            List<ViewI18nResourceItem> i18nResourceItems = new ArrayList<ViewI18nResourceItem>();
            viewI18nResourceKey.setI18nResourceItems(i18nResourceItems);
            List<I18nResourceValue> resourceValues = i18nResourceKey.getResourceValues();
            if (resourceValues == null || resourceValues.isEmpty())
            {
                continue;
            }
            for (I18nResourceValue i18nResourceValue : resourceValues)
            {
                ViewI18nResourceItem viewI18nResourceItem = new ViewI18nResourceItem();
                viewI18nResourceItem.setI18nCode(i18nResourceValue.getLanguageCode());
                viewI18nResourceItem.setResourceKey(i18nResourceKey.getResourceKey());
                viewI18nResourceItem.setResourceValue(i18nResourceValue.getResourceValue());
                i18nResourceItems.add(viewI18nResourceItem);
            }
            viewI18nResourceKeyList.add(viewI18nResourceKey);
        }

        viewDetail.setDatas(viewI18nResourceKeyList);
        baseRes.setData(viewDetail);
        viewDetail.setOffset(resourceCondition.getOffset());
        viewDetail.setRowsPerPage(resourceCondition.getRowsPerPage());
        return baseRes;
    }

    public BaseResponse<List<ViewI18nPageResource>> listI18nPageResource(ViewI18nResourceCondition viewCondition,
            BaseOperatorInfo baseOperatorInfo)
    {
        BaseResponse<List<ViewI18nPageResource>> baseRes = new BaseResponse<List<ViewI18nPageResource>>();
        if (viewCondition == null)
        {
            baseRes.addError(ErrorInfoBuilder.build(CommonErrorCode.PARAM_ERROR, SEVERITY.ERROR,
                    ErrorInfo.CATEGORY.VIEW, LanguageCode.getByValue(baseOperatorInfo.getLanguage())));
            return baseRes;
        }
        I18nResourceCond i18nResCond = new I18nResourceCond();
        BeanUtils.copyProperties(viewCondition, i18nResCond);
        BaseResponse<BasePageDetail<I18nResource>> bizRes = bizI18nResourceService.listI18nRes(i18nResCond,
                baseOperatorInfo);
        if (bizRes.hasError())
        {
            baseRes.addErrors(bizRes.getErrors());
            return baseRes;
        }
        BasePageDetail<I18nResource> perI18nRes = bizRes.getData();
        List<I18nResource> i18nResList = perI18nRes.getDatas();

        return baseRes;
    }

    private List<I18nResource> convertI18nResource(List<I18nResourceForImport> xlsList)
    {
        List<I18nResource> i18nResources = new ArrayList<>();
        for (I18nResourceForImport i18nResForImport : xlsList)
        {
            if (!StringUtils.isEmpty(i18nResForImport.getResourceValueZh()))
            {
                I18nResource i18nResource_ZH = new I18nResource();
                i18nResource_ZH.setApplicationName(i18nResForImport.getApplicationName());
                i18nResource_ZH.setResourceKey(i18nResForImport.getResourceKey());
                i18nResource_ZH.setLanguageCode(LanguageCode.ZH_CN.name());
                i18nResource_ZH.setResourceValue(i18nResForImport.getResourceValueZh());
                i18nResources.add(i18nResource_ZH);
            }
            if (!StringUtils.isEmpty(i18nResForImport.getResourceValueEn()))
            {
                I18nResource i18nResource_EN = new I18nResource();
                i18nResource_EN.setApplicationName(i18nResForImport.getApplicationName());
                i18nResource_EN.setResourceKey(i18nResForImport.getResourceKey());
                i18nResource_EN.setLanguageCode(LanguageCode.EN_US.name());
                i18nResource_EN.setResourceValue(i18nResForImport.getResourceValueEn());
                i18nResources.add(i18nResource_EN);
            }
            if (!StringUtils.isEmpty(i18nResForImport.getResourceValuePl()))
            {
                I18nResource i18nResource_PL = new I18nResource();
                i18nResource_PL.setApplicationName(i18nResForImport.getApplicationName());
                i18nResource_PL.setResourceKey(i18nResForImport.getResourceKey());
                i18nResource_PL.setLanguageCode(LanguageCode.PL_PL.name());
                i18nResource_PL.setResourceValue(i18nResForImport.getResourceValuePl());
                i18nResources.add(i18nResource_PL);
            }
        }
        return i18nResources;
    }

}
