package com.wujun.learning.dao;

import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class PagingCriteriaSearchCallback<T> implements HibernateCallback<T> {

	private DetachedCriteria criteria;

	private String pageIndex = "";

	private String pageSize = "";

	/**
	 * 
	 */
	private static final int DEF_SIZE = 20;

	public PagingCriteriaSearchCallback() {

	}

	public PagingCriteriaSearchCallback(DetachedCriteria criteria) {

		this.criteria = criteria;

	}

	@SuppressWarnings("unchecked")
	@Override
	public T doInHibernate(Session session) throws HibernateException, SQLException {

		return (T) doPaging(criteria.getExecutableCriteria(session), pageIndex, pageSize).list();
	}

	private Criteria doPaging(Criteria criteria, String pageIndex, String pageSize) {

		int index = 1;
		int size = DEF_SIZE;
		try {
			size = Integer.parseInt(pageSize);
		} catch (Exception e) {
			size = DEF_SIZE;
		}
		try {
			index = Integer.parseInt(pageIndex);
		} catch (Exception e) {
			index = 1;
		}

		if (index < 1) {
			index = 1;
		}

		if (size < 1) {
			size = DEF_SIZE;
		}
		// setup the page size
		criteria.setMaxResults(size);
		criteria.setFirstResult((index - 1) * Integer.valueOf(size));
		return criteria;
	}

	public PagingCriteriaSearchCallback setCriteria(DetachedCriteria criteria) {
		this.criteria = criteria;
		return this;
	}

	public PagingCriteriaSearchCallback setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
		return this;
	}

	public PagingCriteriaSearchCallback setPageSize(String pageSize) {
		this.pageSize = pageSize;
		return this;
	}
}
