package com.omniselling.common.protocol;

import com.omniselling.common.ErrorCodeEnum;

/**
 * Created by xslong on 16/1/31.
 */
public class BizException extends OmniException {

	private static final long serialVersionUID = 1L;

	public BizException() {
		super();
	}

	public BizException(String message) {
		super(message);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(ErrorCodeEnum codeEnum) {
		super(codeEnum);
	}

	public BizException(ErrorCodeEnum codeEnum, String message) {
		super(codeEnum, message);
	}

	public BizException(ErrorCodeEnum codeEnum, Throwable cause) {
		super(codeEnum, cause);
	}

    public BizException(ErrorCodeEnum codeEnum, String msg, Throwable cause)
    {
        super(codeEnum, msg, cause);
    }

}
