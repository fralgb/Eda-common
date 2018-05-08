package com.omniselling.common.v5service.client;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.CollectionUtils;

/**
 * 监听zookeeper上面注册的微服务，随机选择可用的服务进行调用
 * 支持多个微服务
 * @author xslong
 * @time Nov 21, 2016 5:47:49 PM
 */
public class RemoteV5ServiceZookeeperExecutor implements RemoteV5ServiceExecutor {

	private String msRoot;
	private String connectString;
	private int sessionTimeout;

	private HttpClientBuilder httpClientBuilder;

	private ZooKeeper zooKeeper;

	private Map<String, List<String>> services = new ConcurrentHashMap<>();
	
	private Map<String, RemoteV5ServiceHttpExecutor> httpExecutors = new ConcurrentHashMap<>();
	
	public RemoteV5ServiceZookeeperExecutor(String msRoot, String connectString,
			int sessionTimeout) {
		this(null, msRoot, connectString, sessionTimeout);
	}

	public RemoteV5ServiceZookeeperExecutor(HttpClientBuilder httpClientBuilder, String msRoot,
			String connectString, int sessionTimeout) {
		this.httpClientBuilder = httpClientBuilder == null ? HttpClientBuilder.create() : httpClientBuilder;
		this.msRoot = msRoot;
		this.connectString = connectString;
		this.sessionTimeout = sessionTimeout;
	}

	public void init() throws IOException {
		zooKeeper = new ZooKeeper(this.connectString, this.sessionTimeout, null);
	}

	private void loadService(String serviceName) {
		String msServicePath = this.msRoot + serviceName;
		try {
			List<String> childs = zooKeeper.getChildren(msServicePath, new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					loadService(serviceName);
					try {
						zooKeeper.getChildren(msServicePath, this);
					} catch (KeeperException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			List<String> urls = new ArrayList<String>();
			for (String path : childs) {
				Stat stat = zooKeeper.exists(msServicePath, false);
				if (stat != null) {
					String url = new String(zooKeeper.getData(msServicePath + "/" + path, false, stat));
					urls.add(url);
				}
			}
			services.put(serviceName, urls);
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String findServieUrl(String serviceName) {
		List<String> list = services.get(serviceName);
		if(CollectionUtils.isEmpty(list)){
			loadService(serviceName);
			list = services.get(serviceName);
		}
		if(CollectionUtils.isEmpty(list))
			throw new RuntimeException("Can not find '" + serviceName + "' service");
		int index = ThreadLocalRandom.current().nextInt(0, list.size());
		try {
			return list.get(index);
		} catch (IndexOutOfBoundsException e) {
			return findServieUrl(serviceName);
		}
	}

	private RemoteV5ServiceHttpExecutor getHttpExecutor(String serviceUrl) {
		RemoteV5ServiceHttpExecutor httpExecutor = httpExecutors.get(serviceUrl);
		if (httpExecutor == null) {
			synchronized (this) {
				httpExecutor = httpExecutors.get(serviceUrl);
				if (httpExecutor == null)
					httpExecutor = new RemoteV5ServiceHttpExecutor(httpClientBuilder, serviceUrl);
				httpExecutors.put(serviceUrl, httpExecutor);
			}
		}
		return httpExecutor;
	}

	@Override
	public Object postRequest(String serviceName, Method method, Object[] args) {
		String serviceUrl = findServieUrl(serviceName);
		if (serviceUrl == null)
			throw new RuntimeException("Can not find '" + serviceName + "' service");
		RemoteV5ServiceHttpExecutor httpExecutor = getHttpExecutor(serviceUrl);
		return httpExecutor.postRequest(serviceName, method, args);
	}

}
