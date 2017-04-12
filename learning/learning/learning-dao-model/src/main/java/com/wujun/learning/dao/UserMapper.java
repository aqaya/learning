package com.wujun.learning.dao;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wujun.learning.model.User;

@Mapper
public interface UserMapper extends BaseMapper<User>{
}
