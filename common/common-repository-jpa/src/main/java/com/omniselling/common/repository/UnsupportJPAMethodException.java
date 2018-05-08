package com.omniselling.common.repository;

public class UnsupportJPAMethodException extends RuntimeException
{
    private static final long serialVersionUID = 2120639873557412281L;

    public UnsupportJPAMethodException()
    {
    }

    public UnsupportJPAMethodException(String msg)
    {
        super(msg);
    }

}
