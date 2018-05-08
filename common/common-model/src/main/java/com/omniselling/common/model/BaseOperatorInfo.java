package com.omniselling.common.model;

import java.io.Serializable;

/**
 * 操作账号信息
 * 用于前端请求参数传递
 *
 */
public class BaseOperatorInfo implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 3134163670526052187L;

    /**
     * 操作账号id
     */
    private Long operatorId;
    
    /**
     * 账号类型
     * MAIN, SUB
     */
    private String accountType;
    
    /**
     * 主账号ID
     */
    private Long mainAccountId;

    /**
     * 账号配置的每一页记录数
     */
    private Integer rowsPerPage;
    
    /**
     * 操作语言
     */
    private String language;

    public String getAccountType()
    {
        return accountType;
    }

    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }

    public Long getOperatorId()
    {
        return operatorId;
    }

    public void setOperatorId(Long operatorId)
    {
        this.operatorId = operatorId;
    }

    public Integer getRowsPerPage()
    {
        return rowsPerPage;
    }

    public void setRowsPerPage(Integer rowsPerPage)
    {
        this.rowsPerPage = rowsPerPage;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public Long getMainAccountId()
    {
        return mainAccountId;
    }

    public void setMainAccountId(Long mainAccountId)
    {
        this.mainAccountId = mainAccountId;
    }

}
