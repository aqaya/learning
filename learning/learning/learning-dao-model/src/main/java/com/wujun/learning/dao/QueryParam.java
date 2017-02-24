/**
 * 
 */
package com.wujun.learning.dao;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

public class QueryParam {

	private Map<String, Object> values = Maps.newHashMap();

	private QueryParam() {

	}

	/**
	 * 
	 * @return
	 */
	public static QueryParam instance() {
		QueryParam qp = new QueryParam();
		return qp;
	}

	/**
	 * 
	 * @param key
	 * @param val
	 * @return
	 */
	public QueryParam add(String key, Object val) {
		values.put(key, val);
		return this;
	}

	public Set<String> keys() {

		return values.keySet();
	}

	public Object getVal(String key) {

		return values.get(key);
	}

}
