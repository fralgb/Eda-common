package com.omniselling.common.model;

public class ServerResponse<T> extends BaseResponse<T>
{
    /**
     * 
     */
    private static final long serialVersionUID = 1900950806548099927L;
    
    private String nonceToken; // against CRSF

    public String getNonceToken()
    {
        return nonceToken;
    }

    public void setNonceToken(String nonceToken)
    {
        this.nonceToken = nonceToken;
    }
}
