package com.wujun.learning.controller.base;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.wujun.learning.commom.exceptions.CommonRuntimeException;

public abstract class BaseController {
	
	protected String getBytesFromRequestReader(HttpServletRequest request) {
		StringBuilder builder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = request.getReader();
			char[] charBuffer = new char[512];
			int bytesRead;
			while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
				builder.append(charBuffer, 0, bytesRead);
			}
		} catch (IOException ex) {
			return "";
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					return "";
				}
			}
		}
		return builder.toString();
	}
	
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
				e.printStackTrace();
			} else {
				rr.setMessage("系统忙,请稍后");
				e.printStackTrace();
			}
		}

		return rr;

	}
}
