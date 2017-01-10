package com.wujun.learning.web.context;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.wujun.learning.commom.factory.MyLogFactory;

/**
 * 
 * MySimpleMappingExceptionResolver,DragonSoft Bravo MDM 
 * @author William
 * @date 2014年9月15日
 *
 */
public class MySimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
	
	

	/** The ERROR. */
	private static Logger ERROR = MyLogFactory.getError();
	
	
	/**
	 * 
	 */
	@Override
	protected void logException(Exception ex, HttpServletRequest request) {

		request.setAttribute("mvcerr", ex.getMessage());
		ERROR.error(this.getClass().getSimpleName(), ex);

	}

}
