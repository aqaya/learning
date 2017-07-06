package com.wujun.learning.dao.mybatis;

import com.wujun.learning.model.User;
import com.wujun.learning.model.UserQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserMapper extends AbstractMybatisMapper<User,UserQuery>{

    @Override
    protected String getNamespace() {
        return "User.";
    }
}
