package com.omniselling.ms.core.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.omniselling.common.util.report.FieldNameAndHeaderMapping;
import com.omniselling.common.util.report.Reportable;
/**
 * 字段和表头绑定类
 * wlq
 * */
public class I18nResourceForImport implements Reportable
{
    /**应用名*/
    private String applicationName;
    /**资源key*/
    private String resourceKey;
    /**中文，英语，波兰语*/
    private String resourceValueZh;
    private String resourceValueEn;
    private String resourceValuePl;
    
    private FieldNameAndHeaderMapping fieldNameAndHeaderMapping = null;
    @Override
    public FieldNameAndHeaderMapping fetchFieldNameAndHeaderMapping()
    {
        if (fieldNameAndHeaderMapping == null)
        {
            fieldNameAndHeaderMapping = new FieldNameAndHeaderMapping();
            Map<String, String> fieldNameHeaderMap = new LinkedHashMap<>();
            fieldNameHeaderMap.put("applicationName", "applicationName(应用名称)");
            fieldNameHeaderMap.put("resourceKey", "resourceKey(资源键值)");
            fieldNameHeaderMap.put("resourceValueZh", "ZH_CN(中文)");
            fieldNameHeaderMap.put("resourceValueEn", "EN_US(英文)");
            fieldNameHeaderMap.put("resourceValuePl", "PL_PL(波兰语)");
            fieldNameAndHeaderMapping.setFieldNameCsvHeaderMap(fieldNameHeaderMap);
        }
        return fieldNameAndHeaderMapping;
    }

    public String getApplicationName()
    {
        return applicationName;
    }
    public void setApplicationName(String applicationName)
    {
        this.applicationName = applicationName;
    }

    public String getResourceValueZh()
    {
        return resourceValueZh;
    }

    public void setResourceValueZh(String resourceValueZh)
    {
        this.resourceValueZh = resourceValueZh;
    }

    public String getResourceValueEn()
    {
        return resourceValueEn;
    }

    public void setResourceValueEn(String resourceValueEn)
    {
        this.resourceValueEn = resourceValueEn;
    }

    public String getResourceValuePl()
    {
        return resourceValuePl;
    }

    public void setResourceValuePl(String resourceValuePl)
    {
        this.resourceValuePl = resourceValuePl;
    }

    public String getResourceKey()
    {
        return resourceKey;
    }
    public void setResourceKey(String resourceKey)
    {
        this.resourceKey = resourceKey;
    }

    public FieldNameAndHeaderMapping getFieldNameAndHeaderMapping()
    {
        return fieldNameAndHeaderMapping;
    }

    public void setFieldNameAndHeaderMapping(FieldNameAndHeaderMapping fieldNameAndHeaderMapping)
    {
        this.fieldNameAndHeaderMapping = fieldNameAndHeaderMapping;
    }
    
}
