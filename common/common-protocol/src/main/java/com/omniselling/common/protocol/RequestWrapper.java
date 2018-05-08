package com.omniselling.common.protocol;

import java.io.Serializable;

/**
 * Created by xslong on 16/1/30.
 */
public class RequestWrapper<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//发起请求的时间
    private String time;
    
    //请求的数据
    private T data;

    public RequestWrapper(){
    	this.time = ProtocolTime.currentTime();
    }

    public RequestWrapper(T data){
        this.data = data;
        this.time = ProtocolTime.currentTime();
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}


