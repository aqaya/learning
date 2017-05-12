package com.wujun.learning.commom.factory;

import com.wujun.learning.commom.exceptions.CommonRuntimeException;

public class CheckUtils {
	public static void notNull(String exceptionMsg, Object ...objects){
		for (int i = 0; i < objects.length; i++) {
			if(objects[i] == null)throw new CommonRuntimeException(exceptionMsg);
		}
	}
	
	public static void ge(Double value, Double valueToCompare, String exceptionMsg){
		notNull(exceptionMsg, value, valueToCompare);
		if(value < valueToCompare)throw new CommonRuntimeException(exceptionMsg);
	}
	//rb3
}
