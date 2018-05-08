package com.omniselling.microservice;

import com.omniselling.common.ErrorCodeEnum;
import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.protocol.OmniException;

/**
 * Created by xslong on 16/8/20.
 */
public class MicroServiceException extends OmniException {

	private static final long serialVersionUID = -1289253591305412950L;

	public MicroServiceException(String message) {
		super(CommonErrorCode.SYSTEM_ERROR, message);
	}

	public MicroServiceException(String message, Throwable cause) {
		super(CommonErrorCode.SYSTEM_ERROR, message);
	}

	public MicroServiceException(ErrorCodeEnum codeEnum, String message) {
		super(codeEnum, message);
	}

	public MicroServiceException(ErrorCodeEnum codeEnum, String message, Throwable cause) {
		super(codeEnum, message);
	}

}
