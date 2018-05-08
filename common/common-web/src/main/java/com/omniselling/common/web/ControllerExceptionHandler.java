package com.omniselling.common.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.protocol.BizParamProblemsException;
import com.omniselling.common.protocol.OmniException;
import com.omniselling.common.protocol.ResponseWrapper;

/**
 * 捕获所在Controller还未处理的异常 Created by xslong on 16/1/31.
 */
@EnableWebMvc
@ControllerAdvice
public class ControllerExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(OmniException.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseWrapper handleBizException(HttpServletRequest request, HttpServletResponse response,
			OmniException exception) throws IOException {
		ResponseWrapper responseWrapper = new ResponseWrapper(CommonErrorCode.SYSTEM_ERROR, null);
		responseWrapper.setMessage(exception.getMessage());
		if (exception instanceof BizParamProblemsException)
			responseWrapper.setCode(CommonErrorCode.PARAM_ERROR.name());
		logger.error(exception.getMessage(), exception);
		return responseWrapper;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseWrapper handleException(HttpServletRequest request, HttpServletResponse response,
			Exception exception) throws IOException {
		ResponseWrapper responseWrapper = new ResponseWrapper(CommonErrorCode.SYSTEM_ERROR, exception.getMessage(),
				null);
		logger.error(exception.getMessage(), exception);
		return responseWrapper;
	}

}
