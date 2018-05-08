package com.omniselling.common.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * @author xslong
 * @version 创建时间：Nov 22, 2016 5:00:29 PM
 */

public class ApplicationMetadata {

	private String name;
	private String description;
	private String type;
	private String port;
	private String host;
	private String path;

	private static ApplicationMetadata instance = new ApplicationMetadata();

	private ApplicationMetadata() {
		Properties prop = new Properties();
		InputStream in = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("config/metadata.properties");
		try {
			prop.load(in);
			this.name = prop.getProperty("application.name");
			this.description = prop.getProperty("application.description");
			this.type = prop.getProperty("application.type");
			this.port = prop.getProperty("application.port");
			this.host = prop.getProperty("application.host");
			this.path = prop.getProperty("application.path");

			if (this.host == null || this.host.trim().length() == 0)
				this.host = getHostName();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getHostName() {
		String hostname = "Unknown";
		try {
			InetAddress addr;
			addr = InetAddress.getLocalHost();
			hostname = addr.getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			throw new OmniException("Hostname can not be resolved",e);
		}
		return hostname;
	}

	public static ApplicationMetadata instance() {
		if (instance == null) {
			synchronized (ApplicationMetadata.class) {
				if (instance == null)
					instance = new ApplicationMetadata();
			}
		}
		return instance;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}

	public String getPort() {
		return port;
	}

	public String getHost() {
		return host;
	}

	public String getPath() {
		return path;
	}

}
