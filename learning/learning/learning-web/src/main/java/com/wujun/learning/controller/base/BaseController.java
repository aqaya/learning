package com.wujun.learning.controller.base;

import com.wujun.learning.commom.exceptions.CommonRuntimeException;

public abstract class BaseController {
	
	public static interface Taker {

		void process(ResultResponse rr);
		
	}
	
	public ResultResponse processSimple(ResultResponse rr,Taker taker) {

		try {

			taker.process(rr);

		} catch (Exception e) {

			rr.setResult(false);
			if (e.getClass().equals(CommonRuntimeException.class)) {
				rr.setMessage(e.getMessage());
			} else {
				rr.setMessage("系统忙,请稍后");
			}
		}

		return rr;

	}
}
