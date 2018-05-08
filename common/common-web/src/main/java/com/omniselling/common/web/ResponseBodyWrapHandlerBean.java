package com.omniselling.common.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.omniselling.common.ResponseCodeEnum;
import com.omniselling.common.enumeration.CommonErrorCode;
import com.omniselling.common.enumeration.LanguageCode;
import com.omniselling.common.model.ErrorInfo;
import com.omniselling.common.model.ErrorInfo.CATEGORY;
import com.omniselling.common.model.ErrorInfo.SEVERITY;
import com.omniselling.common.model.ServerResponse;
import com.omniselling.common.protocol.ResponseWrapper;

/** 
 * 
 * 包装ResponseBody
 * @author xslong 
 * @version 创建时间：Jan 9, 2017 10:42:09 PM 
 * 
*/

public class ResponseBodyWrapHandlerBean implements InitializingBean
{

	@Autowired
	private RequestMappingHandlerAdapter adapter;

	@Override
	public void afterPropertiesSet() throws Exception
	{
		List<HandlerMethodReturnValueHandler> newHandlers = new ArrayList<>(adapter.getReturnValueHandlers());
		for (int i = 0, len = newHandlers.size(); i < len; i++)
		{
			if (newHandlers.get(i) instanceof RequestResponseBodyMethodProcessor)
			{
				newHandlers.set(i, new ResponseBodyWrapHandler(newHandlers.get(i)));
				break;
			}
		}
		adapter.setReturnValueHandlers(newHandlers);
	}

	static class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler
	{

		private final HandlerMethodReturnValueHandler delegate;

		public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate)
		{
			this.delegate = delegate;
		}

		@Override
		public boolean supportsReturnType(MethodParameter returnType)
		{
			return delegate.supportsReturnType(returnType);
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void handleReturnValue(Object returnValue, MethodParameter returnType,
				ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception
		{
			//需要注意：要兼容旧版的前端协议
			ServerResponse serverResponse;
			if (returnValue instanceof ServerResponse)
			{
				serverResponse = (ServerResponse) returnValue;
			} else if (returnValue instanceof ResponseWrapper)
			{
				ResponseWrapper responseWrapper = (ResponseWrapper) returnValue;
				serverResponse = new ServerResponse<>();
				serverResponse.setData(responseWrapper.getData());
				if (!ResponseCodeEnum.SUCCESS.name().equals(responseWrapper.getCode()))
				{
					ErrorInfo errorInfo = new ErrorInfo();
					errorInfo.setCode(responseWrapper.getCode());
					errorInfo.setDesc(responseWrapper.getMessage());
					errorInfo.setCategory(CATEGORY.UNDETERMINED);
					errorInfo.setLanguage(LanguageCode.EN_US);
					errorInfo.setSeverity(SEVERITY.ERROR);
					serverResponse.addError(errorInfo);
				}
			} else if (returnValue instanceof Map && ((Map<?, ?>) returnValue).containsKey("exception"))
			{
				serverResponse = new ServerResponse<>();
				ErrorInfo errorInfo = new ErrorInfo();
				errorInfo.setCode(CommonErrorCode.SYSTEM_ERROR.name());
				errorInfo.setDesc(CommonErrorCode.SYSTEM_ERROR.name());
				errorInfo.setCategory(CATEGORY.UNDETERMINED);
				errorInfo.setLanguage(LanguageCode.EN_US);
				errorInfo.setSeverity(SEVERITY.ERROR);
				serverResponse.addError(errorInfo);
			} else
			{
				serverResponse = new ServerResponse<>();
				serverResponse.setData(returnValue);
			}
			delegate.handleReturnValue(serverResponse, returnType, mavContainer, webRequest);
		}

	}
}