package com.wujun.learning.dao.mybatis;

import java.util.Collections;
import java.util.List;

public abstract class AbstractMybatisMapper<D,Q> extends MySqlSessionMapperSupport implements MybatisBaseMapper<D,Q> {

    protected abstract String getNamespace();

    @Override
    public void insert(D d) {
        if (null != d) {
            getSqlSession().insert(getNamespace() + "insert", d);
        }
    }

    @Override
    public Integer updateById(D d) {
        if (null == d) {
            return 0;
        }
        return getSqlSession().update(getNamespace() + "updateById", d);
    }

    @Override
    public Integer deleteById(Long id) {
        if (null == id || 0L >= id) {
            return 0;
        }
        return getSqlSession().update(getNamespace() + "deleteById", id);
    }

    @Override
    public List<D> query(Q q) {
        if (null == q) {
            return Collections.emptyList();
        }
        return getSqlSession().selectList(getNamespace() + "query", q);
    }

    @Override
    public Integer count(Q q) {
        if (null == q) {
            return 0;
        }
        return getSqlSession().selectOne(getNamespace() + "count", q);
    }

    @Override
    public D queryById(Long id) {
        if (null == id || 0L >= id) {
            return null;
        }
        return getSqlSession().selectOne(getNamespace() + "queryById", id);
    }

}
