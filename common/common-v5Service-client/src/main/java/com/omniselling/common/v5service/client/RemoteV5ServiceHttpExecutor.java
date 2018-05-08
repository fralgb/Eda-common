package com.omniselling.common.v5service.client;

import java.io.IOException;
import java.lang.reflect.Method;

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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * @author xslong
 * @version 创建时间：Nov 18, 2016 5:22:03 PM
 */

public class RemoteV5ServiceHttpExecutor implements RemoteV5ServiceExecutor {
	
	static final Logger logger = LoggerFactory.getLogger(RemoteV5ServiceHttpExecutor.class);

	static final String DEFAULT_CHARSET = "UTF-8";
	static final String CONTENT_TYPE = "application/json";
	private String serviceUrl;

	private HttpClientBuilder httpClientBuilder;
	
	private ObjectMapper objectMapper = new ObjectMapper();

	public RemoteV5ServiceHttpExecutor(String serviceUrl) {
		this(null, serviceUrl);
	}

	public RemoteV5ServiceHttpExecutor(HttpClientBuilder httpClientBuilder, String serviceUrl) {
		this.httpClientBuilder = httpClientBuilder == null ? HttpClientBuilder.create() : httpClientBuilder;
		this.serviceUrl = serviceUrl;
		this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Override
	public Object postRequest(String serviceName, Method method, Object[] args) {
		V5Service v5ServiceMethod = method.getAnnotation(V5Service.class);
		if (v5ServiceMethod == null)
			return null;
		String url = serviceUrl + v5ServiceMethod.path();
		String response = this.execute(url, args[0]);
		if (response == null)
			return null;
		TypeFactory typeFactory = objectMapper.getTypeFactory();
		try
		{
			Object r = objectMapper.readValue(response, typeFactory.constructType(method.getGenericReturnType()));
			return r;
		} catch (IOException e)
		{
			e.printStackTrace();
			throw new V5ServiceException(e.getMessage());
		}
	}

	public String execute(String serviceUrl, Object arg) {
		try {
			String paramString = objectMapper.writeValueAsString(arg);
			logger.debug("serviceUrl :{}, request: {}", serviceUrl ,paramString);
			HttpClient httpClient = httpClientBuilder.build();
			StringEntity entity = new StringEntity(paramString, DEFAULT_CHARSET);
			entity.setContentEncoding(DEFAULT_CHARSET);
			entity.setContentType(CONTENT_TYPE);

			HttpPost httpPost = new HttpPost(serviceUrl);
			httpPost.setEntity(entity);
			long t1 = System.currentTimeMillis();
			HttpResponse httpResponse = httpClient.execute(httpPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			long used = System.currentTimeMillis() - t1;
			String reponseString = EntityUtils.toString(httpResponse.getEntity(), DEFAULT_CHARSET);
			logger.debug("url :{}, used:{}, statusCode :{}, response : {}", new Object[]{ serviceUrl, used, statusCode, reponseString});
			if (HttpStatus.SC_OK == statusCode) {
				return reponseString;
			} else {
	            logger.error("serviceUrl :{}, request: {}", serviceUrl ,paramString);
	            logger.error("url :{}, used:{}, statusCode :{}, response : {}", new Object[]{ serviceUrl, used, statusCode, reponseString});
	            
				throw new V5ServiceException(reponseString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
