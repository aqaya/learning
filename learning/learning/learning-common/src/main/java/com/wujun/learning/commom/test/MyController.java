package com.wujun.learning.commom.test;

public class MyController extends BaseController {
	public RestResult doIt(){
		return process(new RestResult(), new Taker() {
			
			@Override
			public void process(RestResult rr) {
			}
		});
	}
}
