package com.omniselling.common.biz.operation;

import com.omniselling.common.ErrorCodeEnum;
import com.omniselling.common.protocol.OmniException;

/** 
 * @author xslong 
 * @version 创建时间：Jan 3, 2017 3:18:00 PM 
 * 
*/

public class PersistentException extends OmniException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PersistentException(){
		super();
	}

	public PersistentException(String message) {
		super(message);
	}
	
	public PersistentException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public PersistentException(ErrorCodeEnum codeEnum) {
		super(codeEnum);
	}
}
