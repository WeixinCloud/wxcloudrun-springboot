package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.UserMapper;
import com.tencent.wxcloudrun.model.User;
import com.tencent.wxcloudrun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  final UserMapper userMapper;

  public UserServiceImpl(@Autowired UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public Optional<User> getUserById(Integer id) {
    return Optional.ofNullable(userMapper.queryById(id));
  }

  @Override
  public Boolean removeById(Integer id) {
    Integer effectedRows = userMapper.delete(id);
    return effectedRows == 1;
  }

  @Override
  public Boolean updateById(User user) {
    return userMapper.update(user) == 1;
  }

  @Override
  public Boolean create(User user) {
    return userMapper.insert(user) == 1;
  }
}
