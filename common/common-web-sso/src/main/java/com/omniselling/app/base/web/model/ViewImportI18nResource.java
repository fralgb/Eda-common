package com.omniselling.app.base.web.model;

import org.springframework.web.multipart.MultipartFile;

public class ViewImportI18nResource
{
    /** 导入的数据 */
    private MultipartFile file;

    public MultipartFile getFile()
    {
        return file;
    }

    public void setFile(MultipartFile file)
    {
        this.file = file;
    }
    
    @Override
    public String toString()
    {
        return "ViewImportBillingConfig [file=" + file + "]";
    }
}
