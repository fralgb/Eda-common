package com.omniselling.common.protocol;

import com.omniselling.common.ResponseCodeEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by xslong on 16/1/30.
 */
public @ToString class ResponseWrapper<T> {

	// 返回的数据
	private @Getter @Setter T data;

	private @Getter @Setter String time;

	private @Getter @Setter String code;

	private @Getter @Setter String message;
	
	public ResponseWrapper() {
	}

	public ResponseWrapper(ResponseCodeEnum e) {
		this(e, e.toString(), null);
	}

	public ResponseWrapper(ResponseCodeEnum e, T data) {
		this(e, e.toString(), data);
	}

	public ResponseWrapper(ResponseCodeEnum e, String message, T data) {
		this.code = e.toString();
		this.message = message;
		this.data = data;
		this.time = ProtocolTime.currentTime();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ResponseWrapper build(ResponseCodeEnum e) {
		return new ResponseWrapper(e, null);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ResponseWrapper build(ResponseCodeEnum e, Object data) {
		return new ResponseWrapper(e, data);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ResponseWrapper build(ResponseCodeEnum e, String message) {
		return new ResponseWrapper(e, message, null);
	}

}
