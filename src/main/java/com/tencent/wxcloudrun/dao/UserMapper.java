package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

  User queryById(@Param("id") Integer id);

  Integer insert(User user);

  Integer delete(@Param("id") Integer id);

  Integer update(User user);
}
