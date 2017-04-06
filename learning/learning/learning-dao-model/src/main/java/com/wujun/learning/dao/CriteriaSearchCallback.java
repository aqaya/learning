package com.wujun.learning.dao;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

@SuppressWarnings("deprecation")
public class CriteriaSearchCallback<T> implements HibernateCallback<T> {

	private DetachedCriteria criteria;

	public CriteriaSearchCallback() {
	}

	public CriteriaSearchCallback(DetachedCriteria criteria) {
		this.criteria = criteria;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T doInHibernate(Session session) throws HibernateException, SQLException {

		return (T) criteria.getExecutableCriteria(session).list();
	}

	public CriteriaSearchCallback<T> setCriteria(DetachedCriteria criteria) {
		this.criteria = criteria;
		return this;
	}
}
