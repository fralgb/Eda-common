package com.omniselling.common.log;

import com.omniselling.common.protocol.ApplicationMetadata;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author xslong
 * @version 创建时间：Nov 28, 2016 3:29:27 PM
 * 
 */

public class ApplicationNameConverter extends ClassicConverter {

	ApplicationMetadata appMetadata = ApplicationMetadata.instance();

	private final static String UNKOWN = "Unkown";

	@Override
	public String convert(ILoggingEvent event) {
		if (appMetadata != null)
			return appMetadata.getName();
		return UNKOWN;
	}

}
