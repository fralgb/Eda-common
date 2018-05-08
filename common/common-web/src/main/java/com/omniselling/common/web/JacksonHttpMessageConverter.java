package com.omniselling.common.web;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.omniselling.common.protocol.ProtocolTime;

/**
 * 根据架构协议对Controller返回的responseBody进行封装 
 * Created by xslong on 16/1/31.
 */
public class JacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter {

	final static Logger logger = LoggerFactory.getLogger(JacksonHttpMessageConverter.class);

	public void init() {
		//解析JSON时忽略未知的字段
		getObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		getObjectMapper().setDateFormat(new SimpleDateFormat(ProtocolTime.DATEFORMAT));
	}

	/*
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		if (object instanceof ResponseWrapper) {
			super.writeInternal(object, type, outputMessage);
		}else if(object instanceof Map && ((Map)object).containsKey("exception")){
			ResponseWrapper responseWrapper = new ResponseWrapper(CommonErrorCode.SYSTEM_ERROR);
			super.writeInternal(responseWrapper, responseWrapper.getClass(), outputMessage);
		} else {
			ResponseWrapper responseWrapper = new ResponseWrapper(ResponseCodeEnum.SUCCESS, object);
			super.writeInternal(responseWrapper, responseWrapper.getClass(), outputMessage);
		}

	}
	*/
}
