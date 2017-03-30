package com.wujun.learning.commom.test;

public class BaseController {
	RestResult process(RestResult rr,Taker taker){
		try{
			taker.process(rr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rr;
	}
	
	public interface Taker{
		public void process(RestResult rr);
	}
	
}

