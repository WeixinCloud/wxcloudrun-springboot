package com.tencent.cloudbaserun.controller;

import com.tencent.cloudbaserun.config.ApiResponse;
import com.tencent.cloudbaserun.dto.CreateUserRequest;
import com.tencent.cloudbaserun.dto.UpdateUserRequest;
import com.tencent.cloudbaserun.model.User;
import com.tencent.cloudbaserun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    return userService.removeById(id) ? ApiResponse.ok() : ApiResponse.error("删除失败");
  }

  @GetMapping("/{id}")
  ApiResponse get(@PathVariable Integer id) {
    User user = userService.getUserById(id).orElse(new User());
    return ApiResponse.ok(user);
  }

  @PutMapping
  ApiResponse update(@RequestBody UpdateUserRequest request) {

    User user = new User();
    user.setAge(request.getAge());
    user.setName(request.getName());
    user.setEmail(request.getEmail());
    user.setPhone(request.getPhone());
    user.setDescription(request.getDescription());
    user.setUpdateTime(LocalDateTime.now());

    return userService.updateById(user) ? ApiResponse.ok() : ApiResponse.error("user更新失败");
  }
}
