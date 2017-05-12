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
	//rb2
	public CommonRuntimeException(String msg){
		super(msg);
	}
	//rb3
	public CommonRuntimeException(Throwable t){
		super(t);
	}
	//rb4
	public CommonRuntimeException(String msg,Throwable t){
		super(msg, t);
	}
	
}
