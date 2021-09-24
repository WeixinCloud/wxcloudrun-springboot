package com.tencent.cloudbaserun.service;

import com.tencent.cloudbaserun.model.User;

import java.util.Optional;

public interface UserService {

  Optional<User> getUserById(Integer id);

  Boolean removeById(Integer id);

  Boolean updateById(User user);

  Boolean create(User user);
}
