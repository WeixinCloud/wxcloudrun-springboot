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

/**
 * 用户控制器
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

  final UserService userService;

  public UserController(@Autowired UserService userService) {
    this.userService = userService;
  }

  /**
   * 创建用户
   * @param request {@link CreateUserRequest}
   * @return API response json
   */
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

  /**
   * 根据ID删除用户
   * @param id 用户ID
   * @return API response json
   */
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

  /**
   * 根据用户ID查询用户
   * @param id 用户ID
   * @return API response json
   */
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

  /**
   * @param id 根据用户ID查询用户
   * @param request {@link UpdateUserRequest}
   * @return API response json
   */
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
