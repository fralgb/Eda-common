package com.omniselling.common.protocol;

import com.omniselling.common.ErrorCodeEnum;

public class OmniCheckedException extends Exception
{

	private static final long serialVersionUID = 1L;

	private ErrorCodeEnum codeEnum;

	public OmniCheckedException() {
		super();
	}

	public OmniCheckedException(String message) {
		super(message);
	}

	public OmniCheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public OmniCheckedException(ErrorCodeEnum codeEnum) {
		super(codeEnum.name());
		this.codeEnum = codeEnum;
	}

	public OmniCheckedException(ErrorCodeEnum codeEnum, String message) {
		super(message);
		this.codeEnum = codeEnum;
	}

	public OmniCheckedException(ErrorCodeEnum codeEnum, Throwable cause) {
		super(cause);
		this.codeEnum = codeEnum;
	}

	public OmniCheckedException(ErrorCodeEnum codeEnum, String message, Throwable cause) {
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
