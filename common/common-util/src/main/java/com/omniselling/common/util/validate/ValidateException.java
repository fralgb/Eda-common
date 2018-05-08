package com.omniselling.common.util.validate;

import com.omniselling.common.protocol.BizException;

/**
 * @author xslong
 *
 */
public class ValidateException extends BizException
{
    /**
     * 
     */
    private static final long serialVersionUID = 4705003405382723672L;

    public ValidateException(String message){
        super(message);
    }
}
