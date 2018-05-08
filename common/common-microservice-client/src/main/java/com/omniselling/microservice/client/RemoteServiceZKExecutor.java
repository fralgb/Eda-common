package com.omniselling.microservice.client;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.utils.ZKPaths;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.omniselling.common.zookeeper.ZKClient;
import com.omniselling.microservice.MicroSerivceConstant;
import com.omniselling.microservice.MicroServiceException;
import com.omniselling.microservice.RegistInfo;

/**
 * 监听zookeeper上面注册的微服务节点，可以支持多个不同微服务
 * 
 * @author xslong
 * @time Nov 24, 2016 3:26:11 PM
 */
public class RemoteServiceZKExecutor implements RemoteServiceExecutor {

	private static final Logger logger = LoggerFactory.getLogger(RemoteServiceZKExecutor.class);

	private ZKClient zkClient;

	private HttpClientBuilder httpClientBuilder;

	private Map<String, List<RegistInfo>> services = new ConcurrentHashMap<>();

	private Map<String, RemoteServiceHttpExecutor> httpExecutors = new ConcurrentHashMap<>();

	private Object lock = new Object();

	public RemoteServiceZKExecutor(ZKClient zkClient, HttpClientBuilder httpClientBuilder) {
		this.zkClient = zkClient;
		this.httpClientBuilder = httpClientBuilder;
	}

	@SuppressWarnings("resource")
	public void init() {
		// 监听已有服务
		/*
		List<String> serviceList = zkClient.listChildren(MicroSerivceConstant.SERVICE_ROOT_PATH);
		
		if (serviceList != null) {
			for (String serviceName : serviceList) {
				//addServicePathListener(serviceName);
				// loadService(serviceName);
			}
		}
		*/
		// 监听根路径，有新的服务注册时也进行监听
		PathChildrenCache servieRootCache = new PathChildrenCache(zkClient.getClient(),MicroSerivceConstant.SERVICE_ROOT_PATH, false);
		try
		{
			servieRootCache.start();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new MicroServiceException(e.getMessage(), e);
		}
		servieRootCache.getListenable().addListener(new PathChildrenCacheListener() {
			@SuppressWarnings("incomplete-switch")
			@Override
			public void childEvent(CuratorFramework paramCuratorFramework, PathChildrenCacheEvent event)
					throws Exception {
				String servicePath = event.getData().getPath();
				logger.info("the  service root path '{}' has changed :{} {}", MicroSerivceConstant.SERVICE_ROOT_PATH,
						event.getType(), servicePath);
				String serviceName = ZKPaths.getNodeFromPath(event.getData().getPath());
				switch (event.getType()) {
				case CHILD_ADDED: {
					addServicePathListener(serviceName);
				}
				case CHILD_UPDATED: {
					loadService(serviceName);
					break;
				}
				case CHILD_REMOVED: {
					services.remove(serviceName);
					break;
				}
				}
			}
		});
	}

	@SuppressWarnings("resource")
	private void addServicePathListener(String serviceName) {
		logger.info("init '{}' listener", serviceName);
		try {
			PathChildrenCache serviePathCache = new PathChildrenCache(zkClient.getClient(),
					MicroSerivceConstant.SERVICE_ROOT_PATH + "/" + serviceName, false);
			serviePathCache.start();
			logger.info("'{}' service has {} nodes", serviceName, serviePathCache.getCurrentData().size());
			serviePathCache.getListenable().addListener(new PathChildrenCacheListener() {
				@SuppressWarnings("incomplete-switch")
				@Override
				public void childEvent(CuratorFramework paramCuratorFramework, PathChildrenCacheEvent event)
						throws Exception {
					String nodePath = event.getData().getPath();
					String nodeName = ZKPaths.getNodeFromPath(event.getData().getPath());
					logger.info("the '{}' service has changed :{} {}", serviceName, event.getType(), nodePath);
					switch (event.getType()) {
					case CHILD_ADDED: {
						RegistInfo registInfo = RegistInfo.fromString(zkClient.getData(nodePath));
						if (registInfo != null)
							addServiceNode(serviceName, registInfo);
						break;
					}
					case CHILD_UPDATED: {
						RegistInfo registInfo = RegistInfo.fromString(zkClient.getData(nodePath));
						if (registInfo != null)
							addServiceNode(serviceName, registInfo);
						break;
					}
					case CHILD_REMOVED: {
						removeServiceNode(serviceName, nodeName);
						break;
					}
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addServiceNode(String serviceName, RegistInfo ri) {
		if (ri == null)
			return;
		logger.info("'{}' service add node :{} path :{}", serviceName, ri.getName(), ri.getPath());
		synchronized (lock) {
			List<RegistInfo> list = services.get(ri.getServiceName());
			if (list == null) {
				list = Collections.synchronizedList(new ArrayList<RegistInfo>());
				list.add(ri);
				services.put(ri.getServiceName(), list);
			} else {
				int index = list.indexOf(ri);
				if (index == -1) {
					list.add(ri);
				} else {
					list.set(index, ri);
				}
			}
		}
	}

	private void removeServiceNode(String serviceName, String node) {
		if (node == null)
			return;
		logger.info("'{}' service remove node :{}", serviceName, node);
		synchronized (lock) {
			List<RegistInfo> list = services.get(serviceName);
			if (list != null) {
				for (Iterator<RegistInfo> e = list.iterator(); e.hasNext();) {
					if (e.next().getName().equals(node))
						e.remove();
				}
			}
		}
	}

	/**
	 * 随机选择一个可用的服务节点
	 * 
	 * @param serviceName
	 * @return
	 */
	public RegistInfo getServiceNode(String serviceName) {
		List<RegistInfo> list = services.get(serviceName);
		if (list == null) {
			loadService(serviceName);
			list = services.get(serviceName);
		}
		if (list == null)
			return null;
		int size = list.size();
		if (size == 0)
			return null;
		int index = ThreadLocalRandom.current().nextInt(0, size);
		try {
			RegistInfo ri = list.get(index);
			return ri;
		} catch (IndexOutOfBoundsException e) {
			return getServiceNode(serviceName);
		}
	}

	/**
	 * 根据serviceName从ZK上面读取可用的服务
	 * 
	 * @param serviceName
	 */
	private void loadService(String serviceName) {
		String msServicePath = MicroSerivceConstant.SERVICE_ROOT_PATH + "/" + serviceName;
        logger.info("loadService '{}'", msServicePath);
		List<String> childs = this.zkClient.listChildren(msServicePath);
		if (childs == null)
			return;
		services.remove(serviceName);
		for (String path : childs) {
			String data = zkClient.getData(msServicePath + "/" + path);
			RegistInfo ri = RegistInfo.fromString(data);
			if (ri == null)
				continue;
			addServiceNode(serviceName, ri);
		}
	}

	private RemoteServiceHttpExecutor getHttpExecutor(String serviceUrl) {
		RemoteServiceHttpExecutor httpExecutor = httpExecutors.get(serviceUrl);
		if (httpExecutor == null) {
			synchronized (this) {
				httpExecutor = httpExecutors.get(serviceUrl);
				if (httpExecutor == null)
					httpExecutor = new RemoteServiceHttpExecutor(serviceUrl, httpClientBuilder);
				httpExecutors.put(serviceUrl, httpExecutor);
			}
		}
		return httpExecutor;
	}

	@Override
	public Object postRequest(String serviceName, String serviceId, Object[] args, Type returnType, Map<String,Class<?>> genericMap) {
		RegistInfo ci = getServiceNode(serviceName);
		if (ci == null)
			throw new MicroServiceException("service not available : " + serviceName);
		RemoteServiceHttpExecutor httpExecutor = getHttpExecutor(ci.getPath());
		return httpExecutor.postRequest(serviceName, serviceId, args, returnType , genericMap);
	}

}
