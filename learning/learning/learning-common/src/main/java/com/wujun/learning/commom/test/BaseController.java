package com.wujun.learning.commom.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
		void process(RestResult rr);
	}

	public static void main(String[] args) {
		BigDecimal a = new BigDecimal(1.559);
		System.out.println("down="+a.setScale(1,BigDecimal.ROUND_HALF_DOWN).doubleValue()+"   up="+a.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue());



		BigDecimal b1 = new BigDecimal(1.5555);
		BigDecimal b2 = new BigDecimal(3);

		System.out.println(b1.setScale(1,BigDecimal.ROUND_HALF_DOWN));

		System.out.println(b1.setScale(10,BigDecimal.ROUND_DOWN).divide(b2, RoundingMode.CEILING));
	}
}

