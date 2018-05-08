package com.omniselling.common.protocol;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 交互协议统一的时间格式
 * @author xslong
 * @version 创建时间：Nov 23, 2016 10:59:27 AM
 */

public class ProtocolTime {
	
	public final static String DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	
	public static String currentTime() {
		SimpleDateFormat parser = new SimpleDateFormat(DATEFORMAT);
		return parser.format(new Date());
	}

}
