package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.CreateUserRequest;
import com.tencent.wxcloudrun.dto.UpdateUserRequest;
import com.tencent.wxcloudrun.model.User;
import com.tencent.wxcloudrun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

  final UserService userService;

  public UserController(@Autowired UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  ApiResponse create(@RequestBody CreateUserRequest request) {

    User user = new User();
    user.setAge(request.getAge());
    user.setName(request.getName());
    user.setEmail(request.getEmail());
    user.setPhone(request.getPhone());
    user.setDescription(request.getDescription());
    user.setCreateTime(LocalDateTime.now());
    user.setUpdateTime(LocalDateTime.now());

    return userService.create(user) ? ApiResponse.ok() : ApiResponse.error("创建用户失败");
  }

  @DeleteMapping("/{id}")
  ApiResponse delete(@PathVariable Integer id) {
    Optional<User> user = userService.getUserById(id);
    if (!user.isPresent()) {
      ApiResponse rsp = ApiResponse.error("user not exist");
      rsp.setCode(10000);
      return rsp;
    }
    return userService.removeById(id) ? ApiResponse.ok() : ApiResponse.error("删除失败");
  }

  @GetMapping("/{id}")
  ApiResponse get(@PathVariable Integer id) {
    Optional<User> user = userService.getUserById(id);
    if (user.isPresent()) {
      return ApiResponse.ok(user);
    }

    ApiResponse rsp = ApiResponse.error("user not exist");
    rsp.setCode(10000);
    return rsp;
  }

  @PutMapping("/{id}")
  ApiResponse update(@PathVariable Integer id, @RequestBody UpdateUserRequest request) {
    Optional<User> queryUser = userService.getUserById(id);
    if (!queryUser.isPresent()) {
      ApiResponse rsp = ApiResponse.error("user not exist");
      rsp.setCode(10000);
      return rsp;
    }

    User user = new User();
    user.setId(id);
    user.setAge(request.getAge());
    user.setName(request.getName());
    user.setEmail(request.getEmail());
    user.setPhone(request.getPhone());
    user.setDescription(request.getDescription());
    user.setUpdateTime(LocalDateTime.now());

    return userService.updateById(user) ? ApiResponse.ok() : ApiResponse.error("user更新失败");
  }
}
