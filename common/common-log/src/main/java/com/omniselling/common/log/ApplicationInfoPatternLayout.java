package com.omniselling.common.log;

import ch.qos.logback.classic.PatternLayout;

/**
 * @author xslong
 * @version 创建时间：Nov 28, 2016 3:30:09 PM
 * 
 */

public class ApplicationInfoPatternLayout extends PatternLayout {


	static {
		PatternLayout.defaultConverterMap.put("appName", ApplicationNameConverter.class.getName());
		PatternLayout.defaultConverterMap.put("hostname", HostnameConverter.class.getName());
	}

}
