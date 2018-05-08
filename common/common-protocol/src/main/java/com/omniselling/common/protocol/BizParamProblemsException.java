package com.omniselling.common.protocol;

import com.omniselling.common.ErrorCodeEnum;

/**
 * 参数错误异常
 * Created by xslong on 16/2/2.
 */
public class BizParamProblemsException extends OmniException {


	private static final long serialVersionUID = 1L;

	public BizParamProblemsException() {
		super();
	}

	public BizParamProblemsException(String message) {
		super(message);
	}

	public BizParamProblemsException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizParamProblemsException(ErrorCodeEnum codeEnum) {
		super(codeEnum);
	}

	public BizParamProblemsException(ErrorCodeEnum codeEnum, String message) {
		super(codeEnum, message);
	}

	public BizParamProblemsException(ErrorCodeEnum codeEnum, Throwable cause) {
		super(codeEnum, cause);
	}
}
