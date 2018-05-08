package com.omniselling.common;

/**
 * 定义可以响应给客户端的代码
 * @author xslong
 * @version 创建时间：Nov 24, 2016 5:48:31 PM
 * 
 */

public interface ResponseCodeEnum extends Enumeration{
	
	ResponseCodeEnum SUCCESS = new ResponseCodeEnum(){
		
		final static String code = "SUCCESS";
		@Override
		public String name() {
			return code;
		}

        @Override
        public int ordinal()
        {
            return 0;
        }
        
        public String toString() {
        	return code;
        };
	};
}
