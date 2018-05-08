package com.omniselling.common.repository;

public class UnsupportJPAModelQueryException extends RuntimeException
{
    private static final long serialVersionUID = 2120639873557412281L;

    public UnsupportJPAModelQueryException()
    {
    }

    public UnsupportJPAModelQueryException(String msg)
    {
        super(msg);
    }

}
