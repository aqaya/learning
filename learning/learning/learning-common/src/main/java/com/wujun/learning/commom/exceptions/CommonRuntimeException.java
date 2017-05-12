package com.wujun.learning.commom.exceptions;

public class CommonRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5121475041850621312L;
	//rb1
	public CommonRuntimeException() {
		super();
	}
	
	public CommonRuntimeException(String msg){
		super(msg);
	}
	
	public CommonRuntimeException(Throwable t){
		super(t);
	}
	
	public CommonRuntimeException(String msg,Throwable t){
		super(msg, t);
	}
}
