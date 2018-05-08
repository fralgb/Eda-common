package com.omniselling.common.protocol;

import com.omniselling.common.ErrorCodeEnum;

/**
 * Created by xslong on 16/2/19.
 * */
public class SecurityException extends OmniException {
	
	private static final long serialVersionUID = 1L;

	public SecurityException() {
		super();
	}

	public SecurityException(String message) {
		super(message);
	}

	public SecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public SecurityException(ErrorCodeEnum codeEnum) {
		super(codeEnum);
	}

	public SecurityException(ErrorCodeEnum codeEnum, String message) {
		super(codeEnum, message);
	}

	public SecurityException(ErrorCodeEnum codeEnum, Throwable cause) {
		super(codeEnum, cause);
	}
}
