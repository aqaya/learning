package com.wujun.learning.commom.exceptions;

public class CommonRuntimeException extends RuntimeException {


	/**
	 * Test git 1
	 * Test git 2
	 * Test git 3
	 * Test git 4
	 */
	private static final long serialVersionUID = 5121475041850621312L;
	
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
