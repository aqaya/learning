package com.wujun.learning.dao.mybatis;

import java.util.List;

public interface MybatisBaseMapper<D,Q> {

    void insert(D d);

    Integer updateById(D d);

    Integer deleteById(Long id);

    List query(Q q);

    Integer count(Q q);

    Object queryById(Long id);
}
