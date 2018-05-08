package com.omniselling.microservice.client;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.omniselling.common.ResponseCodeEnum;
import com.omniselling.common.protocol.RequestWrapper;
import com.omniselling.common.protocol.ResponseWrapper;
import com.omniselling.microservice.MicroSerivceRequestData;
import com.omniselling.microservice.MicroServiceException;
import com.omniselling.microservice.MicroServiceHelper;

/**
 * Http 协议调用微服务
 * Created by xslong on 16/8/21.
 */
public class RemoteServiceHttpExecutor implements RemoteServiceExecutor {

	private static final Logger logger = LoggerFactory.getLogger(RemoteServiceHttpExecutor.class);

	private HttpClientBuilder httpClientBuilder;
	private String serviceUrl;

	private ObjectMapper objectMapper = new ObjectMapper();

	final static String DEFAULT_CHARSET = "UTF-8";
	
	final static String CONTENT_TYPE = "application/json";

	public RemoteServiceHttpExecutor(String serviceUrl, HttpClientBuilder httpClientBuilder) {
		this.serviceUrl = serviceUrl;
		this.httpClientBuilder = httpClientBuilder;
		this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	final static String ERROR_MSG =
		"\n----------------------------"
        	+"\nurl: %s"
        	+"\nserverId: %s"
        	+"\ninfo: %s"
        	+"\n----------------------------";

	public String buildErrorMessage(String url, String serviceId, String message){
	    return String.format(ERROR_MSG, url, serviceId, message) ;
	}
	
	@Override
	public Object postRequest(String serviceName, String serviceId, Object[] args, Type returnType, Map<String,Class<?>> genericMap) {
		MicroSerivceRequestData requestData = new MicroSerivceRequestData();
		// requestData.setVersion(version);
		requestData.setRequestId(UUID.randomUUID().toString());
		requestData.setServiceName(serviceName);
		requestData.setServiceId(serviceId);
		requestData.setArguments(MicroServiceHelper.toJsonAarry(args));
		RequestWrapper<MicroSerivceRequestData> clientRequest = new RequestWrapper<MicroSerivceRequestData>(
				requestData);
		try {
			String response = this.execute(this.serviceUrl, clientRequest);
			TypeFactory typeFactory = objectMapper.getTypeFactory();
			JavaType responseWrapperType = null;
			if(returnType == Void.TYPE){
				responseWrapperType = typeFactory.constructType(ResponseWrapper.class);
			}else{
				JavaType dataType = MicroServiceHelper.resolveType(returnType, genericMap, typeFactory);
				responseWrapperType = typeFactory.constructParametricType(ResponseWrapper.class, dataType);
			}
			ResponseWrapper<?> serverResponse = objectMapper.readValue(response, responseWrapperType);
			if (serverResponse != null) {
				if (ResponseCodeEnum.SUCCESS.toString().equals(serverResponse.getCode()))
					return serverResponse.getData();
				throw new MicroServiceException(buildErrorMessage(this.serviceUrl, serviceId, ToStringBuilder.reflectionToString(serverResponse,ToStringStyle.MULTI_LINE_STYLE)));
			}
		} catch (MicroServiceException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw new MicroServiceException(buildErrorMessage(this.serviceUrl, serviceId, e.getMessage()));
		}
		throw new MicroServiceException("");
	}

	public String execute(String serviceUrl, RequestWrapper<MicroSerivceRequestData> request) {
		try {
			HttpClient httpClient = httpClientBuilder.build();
			ObjectMapper mapper = new ObjectMapper();
			String body = mapper.writeValueAsString(request);
			logger.debug("post : [serviceUrl :{} , body : {}]", serviceUrl, body);
			StringEntity entity = new StringEntity(body, DEFAULT_CHARSET);
			entity.setContentEncoding(DEFAULT_CHARSET);
			entity.setContentType(CONTENT_TYPE);
			HttpPost httpPost = new HttpPost(serviceUrl);
			httpPost.setEntity(entity);
			long t1 = System.currentTimeMillis();
			HttpResponse httpResponse = httpClient.execute(httpPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			long used = System.currentTimeMillis() - t1;
			String reponseString = EntityUtils.toString(httpResponse.getEntity(), DEFAULT_CHARSET);
			logger.debug("response :[used :{}ms, statusCode :{}, serviceUrl :{}, body : {}]", used, statusCode, serviceUrl, reponseString);
			if (HttpStatus.SC_OK == statusCode) {
				return reponseString;
			} else {
			    logger.error("response :[used :{}ms, statusCode :{}, serviceUrl :{}, body : {}]", used, statusCode, serviceUrl, reponseString);
			    String info = "response status code is " + statusCode;
			    throw new MicroServiceException(buildErrorMessage(this.serviceUrl, request.getData().getServiceId(), info));
			}
		} catch (MicroServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new MicroServiceException(buildErrorMessage(this.serviceUrl, request.getData().getServiceId(), e.getMessage()));
		}
	}

}
