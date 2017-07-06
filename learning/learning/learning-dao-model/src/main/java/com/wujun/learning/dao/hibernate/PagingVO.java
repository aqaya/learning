package com.wujun.learning.dao.hibernate;

import java.util.List;

public class PagingVO {
	
	private enum SqlOrder{
		desc, asc
	}

	private String pageIndex;

	private String pageSize;

	/**
	 * aa,asc|bb,desc
	 */
	private String orderName;

	private SqlOrder order;

	private List<?> details;

	private Long count;

	private Long pageCount;

	public PagingVO() {
		this.pageIndex = "1";
		this.pageSize = 20 + "";
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public SqlOrder getOrder() {
		return order;
	}

	public void setOrder(SqlOrder order) {
		this.order = order;
	}

	public List<?> getDetails() {
		return details;
	}

	public void setDetails(List<?> details) {
		this.details = details;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
		if (pageSize != null && this.count != null) {
			Long size = Long.parseLong(pageSize);
			if (size > 0) {
				this.pageCount = (count / size) + ((count % size) == 0 ? 0 : 1);
			}
		}
	}

	public Long getPageCount() {
		return pageCount;
	}

	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}

}
