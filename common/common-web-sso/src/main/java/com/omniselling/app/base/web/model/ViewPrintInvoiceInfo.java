package com.omniselling.app.base.web.model;

public class ViewPrintInvoiceInfo
{
    String hstNumber;
    String website;
    String logoUrl;
    ViewAddressInfo addrInfo;

    public String getHstNumber()
    {
        return hstNumber;
    }

    public void setHstNumber(String hstNumber)
    {
        this.hstNumber = hstNumber;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public String getLogoUrl()
    {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl)
    {
        this.logoUrl = logoUrl;
    }

    public ViewAddressInfo getAddrInfo()
    {
        return addrInfo;
    }

    public void setAddrInfo(ViewAddressInfo addrInfo)
    {
        this.addrInfo = addrInfo;
    }

}
