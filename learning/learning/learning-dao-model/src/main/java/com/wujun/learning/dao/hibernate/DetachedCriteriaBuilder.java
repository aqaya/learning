package com.wujun.learning.dao.hibernate;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.FetchMode;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.*;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.Collection;

public class DetachedCriteriaBuilder extends SerialCloneable {

	private static final long serialVersionUID = 302675591862720250L;

	private DetachedCriteria detachedCriteria;

	private ProjectionList plist = Projections.projectionList();

	public DetachedCriteriaBuilder setDefaultTrans() {
		this.detachedCriteria.setResultTransformer(RootEntityResultTransformer.INSTANCE);
		return this;
	}

	public DetachedCriteriaBuilder setMapTrans() {

		this.detachedCriteria.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		return this;
	}

	public DetachedCriteriaBuilder setBeanTran(Class resultClas) {
		this.detachedCriteria.setResultTransformer(new AliasToBeanResultTransformer(resultClas));
		return this;
	}

	private DetachedCriteriaBuilder(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}

	public static DetachedCriteriaBuilder instance(DetachedCriteria detachedCriteria) {

		return new DetachedCriteriaBuilder(detachedCriteria);
	}

	public static DetachedCriteriaBuilder instance(Class<?> clazz) {

		return new DetachedCriteriaBuilder(DetachedCriteria.forClass(clazz));
	}

	public static DetachedCriteriaBuilder instance(Class<?> clazz, String alias) {

		return new DetachedCriteriaBuilder(DetachedCriteria.forClass(clazz, alias));
	}

	private boolean isNotEmptyString(Object obj) {

		return !(null == obj
				|| (String.class.equals(obj.getClass()) && StringUtils.isEmpty(String.valueOf(obj).trim())));

	}

	public DetachedCriteriaBuilder addEq(String property, Object value) {

		if (isNotEmptyString(value)) {
			detachedCriteria.add(Property.forName(property).eq(value));

		}
		return this;
	}

	public DetachedCriteriaBuilder addEq(String property, Object value, Collection ignores) {

		if (isNotEmptyString(value)) {

			if (CollectionUtils.isEmpty(ignores) || !ignores.contains(value)) {
				addEq(property, value);
			}

		}

		return this;
	}

	public DetachedCriteriaBuilder addNoEmptyFilterEq(String property, Object value) {

		if (null != value && !value.getClass().equals(String.class)) {
			return addEq(property, value);
		}

		String tagetval = "";
		if (StringUtils.isNotBlank((String) value)) {
			tagetval = (String) value;
		}

		detachedCriteria.add(Property.forName(property).eq(tagetval));

		return this;
	}

	public DetachedCriteriaBuilder resultTrans(ResultTransformer reTransformer) {
		this.detachedCriteria.setResultTransformer(reTransformer);
		return this;
	}

	public DetachedCriteriaBuilder addNe(String property, Object value) {

		if (isNotEmptyString(value)) {
			this.detachedCriteria.add(Property.forName(property).ne(value));
		}
		return this;
	}

	public DetachedCriteriaBuilder addLt(String property, Object value) {

		if (isNotEmptyString(value)) {
			detachedCriteria.add(Property.forName(property).lt(value));
		}

		return this;
	}

	public DetachedCriteriaBuilder addLe(String property, Object value) {

		if (isNotEmptyString(value)) {
			detachedCriteria.add(Property.forName(property).le(value));
		}

		return this;
	}

	public DetachedCriteriaBuilder addGt(String property, Object value) {

		if (isNotEmptyString(value)) {
			detachedCriteria.add(Property.forName(property).gt(value));
		}
		return this;
	}

	public DetachedCriteriaBuilder addGe(String property, Object value) {

		if (isNotEmptyString(value)) {
			detachedCriteria.add(Property.forName(property).ge(value));
		}

		return this;
	}

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

	public DetachedCriteriaBuilder addIn(String property, Object[] values) {

		if (ArrayUtils.isNotEmpty(values)) {
			detachedCriteria.add(Property.forName(property).in(values));
		}
		return this;
	}

	public DetachedCriteriaBuilder addIn(String property, Collection<?> values) {

		if (!CollectionUtils.isEmpty(values)) {
			detachedCriteria.add(Property.forName(property).in(values));
		}

		return this;
	}

	public DetachedCriteriaBuilder addIsNull(String property) {

		this.detachedCriteria.add(Property.forName(property).isNull());
		return this;
	}

	public DetachedCriteriaBuilder addIsNotNull(String property) {

		this.detachedCriteria.add(Property.forName(property).isNotNull());
		return this;
	}

	public DetachedCriteriaBuilder leftJoin(String property, String alias) {
		this.detachedCriteria.createAlias(property, alias, JoinType.LEFT_OUTER_JOIN);
		return this;
	}

	public DetachedCriteriaBuilder innerJoin(String property, String alias) {
		this.detachedCriteria.createAlias(property, alias, JoinType.INNER_JOIN);
		return this;
	}

	public DetachedCriteriaBuilder createAliasInnerJoin(String property, String alias) {

		this.detachedCriteria.createAlias(property, alias);

		return this;
	}

	public DetachedCriteriaBuilder fetch(String property) {

		this.detachedCriteria.setFetchMode(property, FetchMode.JOIN);
		return this;
	}

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

	public DetachedCriteriaBuilder addPropertyNotEq(String propertyName, String otherPropertyName) {

		this.detachedCriteria.add(Restrictions.neProperty(propertyName, otherPropertyName));
		return this;
	}

	public DetachedCriteriaBuilder setGetProperty(String property) {

		this.detachedCriteria.setProjection(Projections.property(property));
		return this;
	}

	public DetachedCriteriaBuilder addAscOrder(String property) {

		this.detachedCriteria.addOrder(Order.asc(property));
		return this;
	}

	public DetachedCriteriaBuilder addDescOrder(String property) {

		this.detachedCriteria.addOrder(Order.desc(property));
		return this;

	}

	public DetachedCriteriaBuilder addLikeAny(String property, String value) {

		if (StringUtils.isNotBlank(value)) {
			// String like="%"+value.trim()+"%";
			this.detachedCriteria.add(Property.forName(property).like(value.trim(), MatchMode.ANYWHERE));
		}

		return this;
	}

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

	public DetachedCriteriaBuilder addLikeStart(String property, String value) {

		if (StringUtils.isNotBlank(value)) {

			this.detachedCriteria.add(Property.forName(property).like(value.trim(), MatchMode.START));
		}

		return this;
	}

	public DetachedCriteriaBuilder withExists(DetachedCriteria criteria) {
		// criteria.setProjection(Projections.)

		this.detachedCriteria.add(Subqueries.exists(criteria));

		// this.detachedCriteria.add(Subqueries.i)

		return this;
	}

	public DetachedCriteriaBuilder withNotExists(DetachedCriteria criteria) {
		this.detachedCriteria.add(Subqueries.notExists(criteria));
		return this;
	}

	public DetachedCriteriaBuilder withPropertyIn(String propertyName, DetachedCriteria criteria) {
		this.detachedCriteria.add(Subqueries.propertyIn(propertyName, criteria));
		return this;
	}

	public DetachedCriteriaBuilder withIn(Object value, DetachedCriteria criteria) {

		this.detachedCriteria.add(Subqueries.in(value, criteria));
		return this;
	}

	public DetachedCriteriaBuilder addLikeEnd(String property, String value) {

		if (StringUtils.isNotBlank(value)) {
			this.detachedCriteria.add(Property.forName(property).like(value.trim(), MatchMode.END));
		}

		return this;
	}

	public DetachedCriteriaBuilder addOrLike(String propertyOne, Object valueOne, String propertyTwo, Object valueTwo) {
		if (isNotEmptyString(valueOne)) {
			this.detachedCriteria.add(Restrictions.or(Restrictions.like(propertyOne, "%" + valueOne + "%"),
					Restrictions.like(propertyTwo, "%" + valueTwo + "%")));
		}

		return this;
	}

	public DetachedCriteriaBuilder addOr(String property, Object lValue, Object rValue) {
		this.detachedCriteria
				.add(Restrictions.or(Restrictions.eq(property, lValue), Restrictions.eq(property, rValue)));
		return this;
	}

	public DetachedCriteriaBuilder addOr(String propertyOne, Object valueOne, String propertyTwo, Object valueTwo) {
		this.detachedCriteria
				.add(Restrictions.or(Restrictions.eq(propertyOne, valueOne), Restrictions.eq(propertyTwo, valueTwo)));
		return this;
	}

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

	public DetachedCriteriaBuilder setProjection(Projection pj) {

		plist.add(pj);
		this.detachedCriteria.setProjection(plist);
		return this;
	}

	public DetachedCriteriaBuilder setProjection(Projection pj, String alias) {
		if (StringUtils.isNotBlank(alias)) {
			plist.add(Projections.alias(pj, alias));
			this.detachedCriteria.setProjection(plist);
		} else {
			setProjection(pj);
		}
		return this;
	}

	public DetachedCriteriaBuilder groupBy(String proName) {

		plist.add(Projections.groupProperty(proName));
		this.detachedCriteria.setProjection(plist);
		return this;
	}

	public DetachedCriteriaBuilder groupBy(String proName, String alias) {
		plist.add(Projections.alias(Projections.groupProperty(proName), alias));
		this.detachedCriteria.setProjection(plist);
		return this;
	}

	public DetachedCriteriaBuilder addDTOTransform(Class<?> clazz) {
		this.detachedCriteria.setResultTransformer(Transformers.aliasToBean(clazz));
		return this;
	}

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
			// close out
			return in.readObject();
		} catch (Exception e) {
			return new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(bout,out);
		}
	}
}