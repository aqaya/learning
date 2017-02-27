/**
 * 
 */
package com.wujun.learning.commom.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ResultResponse implements Serializable {

	private static final long serialVersionUID = -6523642903881237794L;

	private Boolean result = true;

	private String msg = "操作成功!";

	private Map<String, Object> data = new HashMap<String, Object>();

	/**
	 * 
	 * @return
	 */
	public static ResultResponse instance() {
		ResultResponse rr = new ResultResponse(true);
		return rr;
	}

	public ResultResponse(boolean result) {
		super();
		this.result = result;
	}

	public ResultResponse() {
		super();
	}

	public Boolean getResult() {
		return result;
	}

	public ResultResponse setResult(Boolean result) {
		this.result = result;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public ResultResponse setMessage(String message) {
		this.msg = message;
		return this;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public void addAttribute(String key,Object value){
		this.data.put(key, value);
	}

	public Object getAttribute(String key){
		return this.data.get(key);
	}

}
