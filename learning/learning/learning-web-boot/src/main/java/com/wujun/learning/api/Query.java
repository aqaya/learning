package com.wujun.learning.api;

import java.util.List;

public class Query {
	List<String> names;

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	@Override
	public String toString() {
		return "Query [names=" + names + "]";
	}
}
