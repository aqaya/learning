/**
 * 
 */
package com.wujun.learning.web.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wujun.learning.commom.factory.MyLogFactory;

public class AuthSSOInterceptor extends HandlerInterceptorAdapter {

	Logger debug = MyLogFactory.getDebug();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

		debug.debug("AuthSSOInterceptor: RequestURI : " + request.getRequestURI() + ", Query string : " + request.getQueryString());
		
		return true;
	}

}
