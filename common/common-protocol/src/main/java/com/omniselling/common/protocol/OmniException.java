package com.omniselling.common.protocol;

import com.omniselling.common.ErrorCodeEnum;

/**
 * @author xslong
 * @version 创建时间：Nov 22, 2016 2:48:14 PM
 * 
 */

public class OmniException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorCodeEnum codeEnum;

	public OmniException() {
		super();
	}

	public OmniException(String message) {
		super(message);
	}

	public OmniException(String message, Throwable cause) {
		super(message, cause);
	}

	public OmniException(ErrorCodeEnum codeEnum) {
		super(codeEnum.name());
		this.codeEnum = codeEnum;
	}

	public OmniException(ErrorCodeEnum codeEnum, String message) {
		super(message);
		this.codeEnum = codeEnum;
	}

	public OmniException(ErrorCodeEnum codeEnum, Throwable cause) {
		super(cause);
		this.codeEnum = codeEnum;
	}

	public OmniException(ErrorCodeEnum codeEnum, String message, Throwable cause) {
		super(message, cause);
		this.codeEnum = codeEnum;
	}

	public ErrorCodeEnum getCodeEnum() {
		return codeEnum;
	}

	public void setCodeEnum(ErrorCodeEnum codeEnum) {
		this.codeEnum = codeEnum;
	}

    @Override
    public String getMessage()
    {
        return this.codeEnum + ": " + super.getMessage();
    }

}
