package com.tencent.cloudbaserun.service.impl;

import com.tencent.cloudbaserun.dao.UserMapper;
import com.tencent.cloudbaserun.model.User;
import com.tencent.cloudbaserun.service.UserService;
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
