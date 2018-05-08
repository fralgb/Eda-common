package com.omniselling.microservice.service;

import com.omniselling.microservice.RegistInfo;

/** 
 * 定义微服务可否注册，并返回连接信息
 * 主要是微服务可能支持多种协议，每种协议连接方式不同
 * @author xslong 
 * @version 创建时间：Nov 23, 2016 2:21:57 PM 
 * 
*/

public interface MicroServiceRegistrable {

	RegistInfo registInfo();
}
