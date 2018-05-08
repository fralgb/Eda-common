package com.omniselling.microservice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 微服务实例启动时注册的信息
 * @author xslong
 * @time Nov 22, 2016 3:58:30 PM
 */
public class RegistInfo {

	private String name;
	private String serviceName;
	private String path;
	private String registTime;

	public String getRegistTime() {
		return registTime;
	}

	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name=").append(name).append("\n");
		sb.append("serviceName=").append(serviceName).append("\n");
		sb.append("path=").append(path).append("\n");
		sb.append("registTime=").append(registTime);
		return sb.toString();
	}

	public static RegistInfo fromString(String str) {
		if (str == null)
			return null;
		try {
			Properties properties = new Properties();
			properties.load(new ByteArrayInputStream(str.getBytes()));
			RegistInfo p = new RegistInfo();
			p.setName(properties.getProperty("name"));
			p.setServiceName(properties.getProperty("serviceName"));
			p.setPath(properties.getProperty("path"));
			p.setRegistTime(properties.getProperty("registTime"));
			return p;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((serviceName == null) ? 0 : serviceName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistInfo other = (RegistInfo) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (serviceName == null) {
			if (other.serviceName != null)
				return false;
		} else if (!serviceName.equals(other.serviceName))
			return false;
		return true;
	}
	
	

}
