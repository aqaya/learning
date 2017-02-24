package com.wujun.learning.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.RootEntityResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.util.CollectionUtils;

public class DetachedCriteriaBuilder extends SerialCloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 302675591862720250L;

	/** The detached criteria. */
	private DetachedCriteria detachedCriteria;

	private ProjectionList plist = Projections.projectionList();

	public DetachedCriteriaBuilder setDefaultTrans() {
		this.detachedCriteria.setResultTransformer(RootEntityResultTransformer.INSTANCE);
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public DetachedCriteriaBuilder setMapTrans() {

		this.detachedCriteria.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return this;
	}

	@SuppressWarnings("rawtypes")
	public DetachedCriteriaBuilder setBeanTran(Class resultClas) {
		this.detachedCriteria.setResultTransformer(new AliasToBeanResultTransformer(resultClas));
		return this;
	}

	/**
	 * 
	 * @param detachedCriteria
	 */
	private DetachedCriteriaBuilder(DetachedCriteria detachedCriteria) {

		this.detachedCriteria = detachedCriteria;
		// this.detachedCriteria.setResultTransformer(Criteria.)

	}

	/**
	 * instance class with model type
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	public static DetachedCriteriaBuilder instance(DetachedCriteria detachedCriteria) {

		return new DetachedCriteriaBuilder(detachedCriteria);
	}

	/**
	 * instance class
	 * 
	 * @param clazz
	 * @return
	 */
	public static DetachedCriteriaBuilder instance(Class<?> clazz) {

		return new DetachedCriteriaBuilder(DetachedCriteria.forClass(clazz));
	}

	/**
	 * instance class with model type & alias
	 * 
	 * @param clazz
	 * @param alias
	 * @return
	 */
	public static DetachedCriteriaBuilder instance(Class<?> clazz, String alias) {

		return new DetachedCriteriaBuilder(DetachedCriteria.forClass(clazz, alias));
	}

	private boolean isNotEmptyString(Object obj) {

		return !(null == obj
				|| (String.class.equals(obj.getClass()) && StringUtils.isEmpty(String.valueOf(obj).trim())));

	}

	/**
	 * equal expression sql ==
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public DetachedCriteriaBuilder addEq(String property, Object value) {

		if (isNotEmptyString(value)) {
			detachedCriteria.add(Property.forName(property).eq(value));

		}
		return this;
	}

	/**
	 * 
	 * @param property
	 * @param value
	 * @param ignores
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DetachedCriteriaBuilder addEq(String property, Object value, Collection ignores) {

		if (isNotEmptyString(value)) {

			if (CollectionUtils.isEmpty(ignores) || !ignores.contains(value)) {
				addEq(property, value);
			}

		}

		return this;
	}

	/**
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public DetachedCriteriaBuilder addNoEmptyFilterEq(String property, Object value) {

		if (null != value && !value.getClass().equals(String.class)) {
			return addEq(property, value);
		}

		String tagetval = "";
		if (StringUtils.isBlank((String) value)) {

		} else {
			tagetval = (String) value;
		}

		detachedCriteria.add(Property.forName(property).eq(tagetval));

		return this;
	}

	public DetachedCriteriaBuilder resultTrans(ResultTransformer reTransformer) {
		// this.trans = reTransformer;
		this.detachedCriteria.setResultTransformer(reTransformer);
		return this;
	}

	/**
	 * not equal expression sql !=
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public DetachedCriteriaBuilder addNe(String property, Object value) {

		if (isNotEmptyString(value)) {
			this.detachedCriteria.add(Property.forName(property).ne(value));
		}
		return this;
	}

	/**
	 * low than sql <
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public DetachedCriteriaBuilder addLt(String property, Object value) {

		if (isNotEmptyString(value)) {
			detachedCriteria.add(Property.forName(property).lt(value));
		}

		return this;
	}

	/**
	 * low than or equal sql <=
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public DetachedCriteriaBuilder addLe(String property, Object value) {

		if (isNotEmptyString(value)) {
			detachedCriteria.add(Property.forName(property).le(value));
		}

		return this;
	}

	/**
	 * great than sql >
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public DetachedCriteriaBuilder addGt(String property, Object value) {

		if (isNotEmptyString(value)) {
			detachedCriteria.add(Property.forName(property).gt(value));
		}
		return this;
	}

	/**
	 * great than or equal sql >=
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public DetachedCriteriaBuilder addGe(String property, Object value) {

		if (isNotEmptyString(value)) {
			detachedCriteria.add(Property.forName(property).ge(value));
		}

		return this;
	}

	/**
	 * between from & to sql between
	 * 
	 * @param property
	 * @param from
	 * @param to
	 * @return
	 */
	public DetachedCriteriaBuilder addBetween(String property, Object from, Object to) {

		if (null != from && null != to) {
			detachedCriteria.add(Property.forName(property).between(from, to));
		}

		if (null != from && null == to) {
			addGt(property, from);
		}

		if (null == from && null != to) {
			addLe(property, to);
		}

		return this;
	}

	/**
	 * sql in
	 * 
	 * @param property
	 * @param values
	 * @return
	 */
	public DetachedCriteriaBuilder addIn(String property, Object[] values) {

		if (ArrayUtils.isNotEmpty(values)) {
			detachedCriteria.add(Property.forName(property).in(values));
		}
		return this;
	}

	/**
	 * sql in
	 * 
	 * @param property
	 * @param values
	 * @return
	 */
	public DetachedCriteriaBuilder addIn(String property, Collection<?> values) {

		if (!CollectionUtils.isEmpty(values)) {
			detachedCriteria.add(Property.forName(property).in(values));
		}

		return this;
	}

	/**
	 * add is null
	 * 
	 * @param property
	 * @return
	 */
	public DetachedCriteriaBuilder addIsNull(String property) {

		this.detachedCriteria.add(Property.forName(property).isNull());
		return this;
	}

	/**
	 * add is not null
	 * 
	 * @param property
	 * @return
	 */
	public DetachedCriteriaBuilder addIsNotNull(String property) {

		this.detachedCriteria.add(Property.forName(property).isNotNull());
		return this;
	}

	/**
	 * left join
	 * 
	 * @param property
	 * @param alias
	 * @return
	 */
	public DetachedCriteriaBuilder leftJoin(String property, String alias) {
		this.detachedCriteria.createAlias(property, alias, JoinType.LEFT_OUTER_JOIN);
		return this;
	}

	/**
	 * inner join
	 * 
	 * @param property
	 * @param alias
	 * @return
	 */
	public DetachedCriteriaBuilder innerJoin(String property, String alias) {
		this.detachedCriteria.createAlias(property, alias, JoinType.INNER_JOIN);
		return this;
	}

	/**
	 * inner join
	 * 
	 * @param property
	 * @param alias
	 * @return
	 */
	public DetachedCriteriaBuilder createAliasInnerJoin(String property, String alias) {

		this.detachedCriteria.createAlias(property, alias);

		return this;
	}

	/**
	 * 
	 * @param property
	 * @return
	 */
	public DetachedCriteriaBuilder fetch(String property) {

		this.detachedCriteria.setFetchMode(property, FetchMode.JOIN);
		return this;
	}

	/**
	 * 
	 * @param propertyName
	 * @param otherPropertyName
	 * @return
	 */
	public DetachedCriteriaBuilder addPropertyEq(String propertyName, String otherPropertyName) {

		this.detachedCriteria.add(Restrictions.eqProperty(propertyName, otherPropertyName));

		return this;
	}

	public DetachedCriteriaBuilder ltProperty(String propertyName, String otherPropertyName) {
		this.detachedCriteria.add(Restrictions.ltProperty(propertyName, otherPropertyName));
		return this;
	}

	public DetachedCriteriaBuilder gtProperty(String propertyName, String otherPropertyName) {
		this.detachedCriteria.add(Restrictions.gtProperty(propertyName, otherPropertyName));
		return this;
	}

	/**
	 * 
	 * @param propertyName
	 * @param otherPropertyName
	 * @return
	 */
	public DetachedCriteriaBuilder addPropertyNotEq(String propertyName, String otherPropertyName) {

		this.detachedCriteria.add(Restrictions.neProperty(propertyName, otherPropertyName));
		return this;
	}

	/**
	 * set property
	 * 
	 * @param property
	 * @return
	 */
	public DetachedCriteriaBuilder setGetProperty(String property) {

		this.detachedCriteria.setProjection(Projections.property(property));
		return this;
	}

	/**
	 * add asc order
	 * 
	 * @param property
	 * @return
	 */
	public DetachedCriteriaBuilder addAscOrder(String property) {

		this.detachedCriteria.addOrder(Order.asc(property));
		return this;
	}

	/**
	 * add desc order
	 * 
	 * @param property
	 * @return
	 */
	public DetachedCriteriaBuilder addDescOrder(String property) {

		this.detachedCriteria.addOrder(Order.desc(property));
		return this;

	}

	/**
	 * add like case like %value%
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public DetachedCriteriaBuilder addLikeAny(String property, String value) {

		if (StringUtils.isNotBlank(value)) {
			// String like="%"+value.trim()+"%";
			this.detachedCriteria.add(Property.forName(property).like(value.trim(), MatchMode.ANYWHERE));
		}

		return this;
	}

	/**
	 * 
	 * @param property
	 * @param value
	 * @param caseSensitive
	 * @return
	 */
	public DetachedCriteriaBuilder addLikeAny(String property, String value, boolean caseSensitive) {

		if (StringUtils.isNotBlank(value)) {

			if (caseSensitive) {
				this.detachedCriteria.add(Property.forName(property).like(value.trim(), MatchMode.ANYWHERE));
			} else {
				this.detachedCriteria.add(Restrictions.like(property, value, MatchMode.ANYWHERE).ignoreCase());
			}

		}

		return this;
	}

	/**
	 * add like case like value%
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public DetachedCriteriaBuilder addLikeStart(String property, String value) {

		if (StringUtils.isNotBlank(value)) {

			this.detachedCriteria.add(Property.forName(property).like(value.trim(), MatchMode.START));
		}

		return this;
	}

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public DetachedCriteriaBuilder withExists(DetachedCriteria criteria) {
		// criteria.setProjection(Projections.)

		this.detachedCriteria.add(Subqueries.exists(criteria));

		// this.detachedCriteria.add(Subqueries.i)

		return this;
	}

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public DetachedCriteriaBuilder withNotExists(DetachedCriteria criteria) {
		this.detachedCriteria.add(Subqueries.notExists(criteria));
		return this;
	}

	/**
	 * 
	 * @param propertyName
	 * @param criteria
	 * @return
	 */
	public DetachedCriteriaBuilder withPropertyIn(String propertyName, DetachedCriteria criteria) {
		this.detachedCriteria.add(Subqueries.propertyIn(propertyName, criteria));
		return this;
	}

	/**
	 * 
	 * @param value
	 * @param criteria
	 * @return
	 */
	public DetachedCriteriaBuilder withIn(Object value, DetachedCriteria criteria) {

		this.detachedCriteria.add(Subqueries.in(value, criteria));
		return this;
	}

	/**
	 * add like case like %value
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public DetachedCriteriaBuilder addLikeEnd(String property, String value) {

		if (StringUtils.isNotBlank(value)) {
			this.detachedCriteria.add(Property.forName(property).like(value.trim(), MatchMode.END));
		}

		return this;
	}

	/**
	 * addOrLike valueOne==valueTwo
	 * 
	 * @param propertyOne
	 * @param valueOne
	 * @param propertyTwo
	 * @param valueTwo
	 * @return
	 */
	public DetachedCriteriaBuilder addOrLike(String propertyOne, Object valueOne, String propertyTwo, Object valueTwo) {
		if (isNotEmptyString(valueOne)) {
			this.detachedCriteria.add(Restrictions.or(Restrictions.like(propertyOne, "%" + valueOne + "%"),
					Restrictions.like(propertyTwo, "%" + valueTwo + "%")));
		}

		return this;
	}

	/**
	 * add or
	 * 
	 * @param property
	 * @param lValue
	 * @param rValue
	 * @return
	 */
	public DetachedCriteriaBuilder addOr(String property, Object lValue, Object rValue) {
		this.detachedCriteria
				.add(Restrictions.or(Restrictions.eq(property, lValue), Restrictions.eq(property, rValue)));
		return this;
	}

	/**
	 * add or
	 * 
	 * @param propertyOne
	 * @param valueOne
	 * @param propertyTwo
	 * @param valueTwo
	 * @return
	 */
	public DetachedCriteriaBuilder addOr(String propertyOne, Object valueOne, String propertyTwo, Object valueTwo) {
		this.detachedCriteria
				.add(Restrictions.or(Restrictions.eq(propertyOne, valueOne), Restrictions.eq(propertyTwo, valueTwo)));
		return this;
	}

	/**
	 * 
	 * @param property
	 * @return
	 */
	public DetachedCriteriaBuilder addIsNullOrEmpty(String property) {

		Disjunction dis = Restrictions.disjunction();

		dis.add(Restrictions.isNull(property));
		dis.add(Restrictions.eq(property, ""));

		this.detachedCriteria.add(dis);

		return this;

	}

	/**
	 * get detached criteria
	 * 
	 * @return the detached criteria
	 */
	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	/**
	 * set projection
	 * 
	 * @param pj
	 * @return
	 */
	public DetachedCriteriaBuilder setProjection(Projection pj) {

		plist.add(pj);
		this.detachedCriteria.setProjection(plist);
		return this;
	}

	/**
	 * set projection
	 * 
	 * @param pj
	 * @return
	 */
	public DetachedCriteriaBuilder setProjection(Projection pj, String alias) {
		if (StringUtils.isNotBlank(alias)) {
			plist.add(Projections.alias(pj, alias));
			this.detachedCriteria.setProjection(plist);
		} else {
			setProjection(pj);
		}
		return this;
	}

	/**
	 * 
	 * @param proName
	 * @return
	 */
	public DetachedCriteriaBuilder groupBy(String proName) {

		plist.add(Projections.groupProperty(proName));
		this.detachedCriteria.setProjection(plist);
		return this;
	}

	/**
	 * 
	 * @param proName
	 * @param alias
	 * @return
	 */
	public DetachedCriteriaBuilder groupBy(String proName, String alias) {
		plist.add(Projections.alias(Projections.groupProperty(proName), alias));
		this.detachedCriteria.setProjection(plist);
		return this;
	}

	public DetachedCriteriaBuilder addDTOTransform(Class<?> clazz) {
		this.detachedCriteria.setResultTransformer(Transformers.aliasToBean(clazz));
		return this;
	}

	/**
	 * set order
	 * 
	 * @param orderName
	 * @return
	 */
	public DetachedCriteriaBuilder setOrder(String orderName) {
		if (StringUtils.isNotBlank(orderName)) {
			for (String order : orderName.split("\\|")) {
				String[] orderField = order.split(",");
				if (orderField.length == 1) {
					this.addAscOrder(orderField[0]);
				}
				if (orderField.length == 2) {
					if (StringUtils.lowerCase(orderField[1]).equals("asc")) {
						this.addAscOrder(orderField[0]);
					} else {
						this.addDescOrder(orderField[0]);
					}
				}

			}
		}
		return this;
	}
}

class SerialCloneable implements Cloneable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7417445648006480911L;

	public Object clone() {

		ByteArrayOutputStream bout = null;
		ObjectOutputStream out = null;

		try {
			bout = new ByteArrayOutputStream();
			out = new ObjectOutputStream(bout);
			out.writeObject(this);
			// close bout
			ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
			ObjectInputStream in = new ObjectInputStream(bin);
			Object ret = in.readObject();
			// close out
			return ret;
		} catch (Exception e) {
			return new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(bout,out);
		}
	}
}