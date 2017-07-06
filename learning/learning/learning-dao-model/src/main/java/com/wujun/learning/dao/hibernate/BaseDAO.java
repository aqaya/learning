package com.wujun.learning.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.poi.ss.formula.functions.Count;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.google.common.collect.Lists;

@Transactional
public abstract class BaseDAO<T> {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Class<T> type;

    private int flushCount = 20;

    /**
     * Instantiates a new base dao.
     */
    public BaseDAO() {

        injectType();
    }

    @SuppressWarnings("unchecked")
    protected void injectType() {
        Class<?> c = getClass();

        java.lang.reflect.Type type = c.getGenericSuperclass();

        if (type instanceof ParameterizedType) {
            java.lang.reflect.Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.type = (Class<T>) parameterizedType[0];
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public boolean isExist(Serializable id) {

        T obj = selectById(id);
        return obj != null;

    }

    public T selectById(Serializable id) {

        Session session = getCurrentSession();
        Object object = session.get(type, id);
        return (T) object;

    }

    public List<T> selectAll() {

        Session session = getCurrentSession();
        Criteria criteria = session.createCriteria(type);
        return criteria.list();

    }

    public List<T> selectAll(boolean isDelete) {

        DetachedCriteriaBuilder builder = DetachedCriteriaBuilder.instance(type);

        if (isInheritedPrivateFields(type, "delete")) {
            builder.addEq("delete", isDelete);
        }

        builder.addAscOrder(getEntityIdName());
        return select(builder);

    }

    private boolean isInheritedPrivateFields(Class<?> type, String fieldName) {
        List<Field> result = Lists.newArrayList();

        Class<?> i = type;
        while (i != null && i != Object.class) {
            result.add(FieldUtils.getDeclaredField(i, fieldName));
            i = i.getSuperclass();
        }

        return CollectionUtils.isNotEmpty(result);
    }

    public Long countAll() {

        final String entityName = sessionFactory.getClassMetadata(type).getEntityName();
        final String HQL_COUNT_ALL = String.format("select count(*) from %s", entityName);
        Session session = getCurrentSession();
        Query query = session.createQuery(HQL_COUNT_ALL);
        List list = query.list();

        return CollectionUtils.isEmpty(list) ? 0 : (Long) list.get(0);

    }

    public List<T> select(DetachedCriteriaBuilder builder) {
        return selectE(builder);
    }

    public <E> List<E> selectE(DetachedCriteriaBuilder builder) {

        CriteriaSearchCallback<List<E>> callback = new CriteriaSearchCallback<List<E>>();
        callback.setCriteria(builder.getDetachedCriteria());

        List<E> list = Lists.newArrayList();
        try {
            list = callback.doInHibernate(getCurrentSession());
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public <E> List<E> selectE(HibernateCallback<List<E>> callback) {

        List<E> list = Lists.newArrayList();
        try {
            list = callback.doInHibernate(getCurrentSession());
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public T selectTopOne(DetachedCriteriaBuilder builder) {
        List<T> list = selectTopN(builder, 1);
        return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
    }

    public Object selectTopOne(String hql, Object[] objs) {

        List<Object> list = select(hql, objs);
        return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
    }

    public <E> List<E> select(String hql, Object... objs) {
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);

        if (ArrayUtils.isNotEmpty(objs)) {
            String[] namedParameters = query.getNamedParameters();
            if (ArrayUtils.isEmpty(namedParameters)) {
                int i = 0;
                for (Object obj : objs) {
                    query.setParameter(i++, obj);
                }
            } else {
                List<String> namedParams = Arrays.asList(namedParameters);
                Collections.sort(namedParams);
                int i = 0;
                for (Object obj : objs) {
                    if (obj instanceof Collection) {
                        if (namedParams == null || namedParams.size() == 0) {
                            throw new HibernateException("HQL includes list, but there is no named parameter set!");
                        }
                        Collection<?> vals = (Collection<?>) obj;
                        query.setParameterList(namedParams.get(i++), vals);
                    } else {
                        query.setParameter(namedParams.get(i++), obj);
                    }
                }
            }

        }
        return (List<E>) query.list();
    }

    public List<T> selectTopN(DetachedCriteriaBuilder builder, int n) {

        PagingVO param = new PagingVO();
        param.setPageIndex("1");
        param.setPageSize(Integer.toString(n));
        List<T> res = selectPaging(builder, param);
        return res;
    }

    public <E> E selectTopE(DetachedCriteriaBuilder builder) {

        List<E> res = selectTopNE(builder, 1);
        return CollectionUtils.isEmpty(res) ? null : res.get(0);
    }

    public <E> List<E> selectTopNE(DetachedCriteriaBuilder builder, int n) {
        PagingVO param = new PagingVO();
        param.setPageIndex("1");
        param.setPageSize(Integer.toString(n));
        return (List<E>) selectPaging(builder, param);
    }

    List<T> selectPaging(DetachedCriteriaBuilder builder, PagingVO page) {

        builder.setOrder(page.getOrderName());
        DetachedCriteria criteria = builder.getDetachedCriteria();

        PagingCriteriaSearchCallback<List<T>> searchCallback = new PagingCriteriaSearchCallback<>(criteria);
        searchCallback.setPageIndex(page.getPageIndex()).setPageSize(page.getPageSize());

        List<T> list = Lists.newArrayList();
        try {
            list = searchCallback.doInHibernate(getCurrentSession());
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    List<T> selectList(DetachedCriteriaBuilder builder) {

        DetachedCriteria criteria = builder.getDetachedCriteria();
        // criteria.setResultTransformer(resultTransformer)
        CriteriaSearchCallback<List<T>> csc = new CriteriaSearchCallback<>(criteria);
        try {
            return csc.doInHibernate(getCurrentSession());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public T querySingle(DetachedCriteriaBuilder builder) {

        builder.getDetachedCriteria().setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<T> mes = select(builder);

        if (CollectionUtils.isNotEmpty(mes)) {
            return mes.get(0);
        } else {
            return null;
        }
    }

    public <E> List<E> selectPagingE(DetachedCriteriaBuilder builder, PagingVO page) {

        PagingCriteriaSearchCallback<List<E>> searchCallback = new PagingCriteriaSearchCallback<>(
                builder.getDetachedCriteria());
        searchCallback.setPageIndex(page.getPageIndex()).setPageSize(page.getPageSize());
        List<E> list = Lists.newArrayList();
        try {
            list = searchCallback.doInHibernate(getCurrentSession());
        } catch (HibernateException | SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public PagingVO selectPagingVO(DetachedCriteriaBuilder queryBuilder, PagingVO page,
                                   DetachedCriteriaBuilder countBuilder) {

        page.setDetails(selectPaging(queryBuilder, page));
        page.setCount(count(countBuilder));
        return page;
    }

    public PagingVO selectPagingVO(DetachedCriteriaBuilder queryBuilder, PagingVO page) {

        DetachedCriteriaBuilder countBuilder = (DetachedCriteriaBuilder) queryBuilder.clone();
        page.setCount(count(countBuilder));
        page.setDetails(selectPaging(queryBuilder, page));
        return page;
    }

    public Long count(DetachedCriteriaBuilder builder) {

        // Projections.sqlProjection(sql, columnAliases, types)

        builder.setProjection(Projections.rowCount());
        List<Long> counts = selectE(builder);
        return CollectionUtils.isEmpty(counts) ? 0L : counts.get(0);

    }

    public Long count(DetachedCriteriaBuilder builder, boolean setProjectionForCurrentBuilder) {

        if (setProjectionForCurrentBuilder) {
            return count(builder);
        } else {
            DetachedCriteriaBuilder temp = (DetachedCriteriaBuilder) builder.clone();
            temp.setProjection(Projections.rowCount());
            List<Long> counts = selectE(builder);
            return CollectionUtils.isEmpty(counts) ? 0L : counts.get(0);
        }
    }

    public Number sum(DetachedCriteriaBuilder builder, String property) {

        builder.setProjection(Projections.sum(property));
        List<Number> sums = selectE(builder);

        return CollectionUtils.isEmpty(sums) ? 0 : sums.get(0);
    }

    public Serializable save(T t) {
        checkMutable();
        Session session = getCurrentSession();
        return session.save(t);
    }

    public void delete(T t) {
        checkMutable();
        Session session = getCurrentSession();
        session.delete(t);
    }

    public void deleteById(Serializable id) {

        checkMutable();
        Session session = getCurrentSession();
        Object object = session.get(type, id);
        session.delete(object);

    }

    public void update(T t) {
        checkMutable();
        Session session = getCurrentSession();
        session.update(t);
    }

    public void saveOrUpdate(T t) {
        checkMutable();
        Session session = getCurrentSession();
        session.saveOrUpdate(t);
    }

    public T merge(T t) {
        checkMutable();
        Session session = getCurrentSession();
        return (T) session.merge(t);
    }

    public void saveOrUpdateBatch(Collection<T> ts) {
        saveOrUpdateBatch(ts, flushCount);
    }

    public void saveOrUpdateBatch(Collection<T> ts, int flushCount) {

        if (CollectionUtils.isNotEmpty(ts)) {
            for (T t : ts) {
                this.saveOrUpdate(t);
            }
        }
    }

    public int bulkUpdate(String hql, Object... objects) {

        Query query = this.getCurrentSession().createQuery(hql);
        if (ArrayUtils.isNotEmpty(objects)) {
            int i = 0;
            for (Object obj : objects) {

                query.setParameter(i++, obj);

            }
        }
        return query.executeUpdate();
    }

    public int bulkUpdate(String hql, QueryParam param) {

        Query query = this.getCurrentSession().createQuery(hql);

        Set<String> keys = param.keys();
        for (String key : keys) {
            Object val = param.getVal(key);
            if (null != val) {
                if (val instanceof Collection) {
                    query.setParameterList(key, (Collection) val);
                } else if (val instanceof Object[]) {
                    query.setParameterList(key, (Object[]) val);
                } else {
                    query.setParameter(key, val);
                }
            }
        }
        return query.executeUpdate();

    }

    public void deleteBatch(Collection<T> ts) {

        deleteBatch(ts, flushCount);
    }

    public void deleteBatch(Collection<T> ts, int flushCount) {

        if (CollectionUtils.isNotEmpty(ts)) {
            for (T t : ts) {
                this.delete(t);
            }
        }
    }

    public void flushAndClear() {

        Session session = getCurrentSession();
        session.flush();
    }

    private void checkMutable() {
        boolean mutable = getSessionFactory().getClassMetadata(this.type).isMutable();

        if (!mutable) {
            throw new RuntimeException("Try to save immutable enity !");
        }
    }

    private String getEntityIdName() {

        return getSessionFactory().getClassMetadata(this.type).getIdentifierPropertyName();
    }

}
