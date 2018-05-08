package com.omniselling.app.base.web.model;

public class ViewAddressInfo
{
    private Long addressId;
    private String name;
    private String companyName;
    private String phone;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String postalCode;
    private String city;
    private String state;
    private String stateFullName;
    private String country;
    private String countryLocalFmt;

    public Long getAddressId()
    {
        return addressId;
    }

    public void setAddressId(Long addressId)
    {
        this.addressId = addressId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getStateFullName()
    {
        return stateFullName;
    }

    public void setStateFullName(String stateFullName)
    {
        this.stateFullName = stateFullName;
    }

    public String getAddressLine1()
    {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1)
    {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2()
    {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2)
    {
        this.addressLine2 = addressLine2;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCountryLocalFmt()
    {
        return countryLocalFmt;
    }

    public void setCountryLocalFmt(String countryLocalFmt)
    {
        this.countryLocalFmt = countryLocalFmt;
    }

}
