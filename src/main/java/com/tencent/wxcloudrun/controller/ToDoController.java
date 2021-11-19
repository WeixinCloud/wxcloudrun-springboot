package com.tencent.wxcloudrun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.CreateToDoRequest;
import com.tencent.wxcloudrun.dto.UpdateToDoRequest;
import com.tencent.wxcloudrun.model.ToDo;
import com.tencent.wxcloudrun.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;




import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

/**
 * todo控制器
 */
@RestController

public class ToDoController {

  final ToDoService todoService;
  final Logger logger;

  public ToDoController(@Autowired ToDoService todoService) {
    this.todoService = todoService;
    this.logger = LoggerFactory.getLogger(ToDoController.class);
  }

  /**
   * 获取所有todo
   * @return API response json
   */
  @GetMapping(value = "/api/todos")
  ApiResponse getAll() {
    logger.info("/api/todos get request");
    Optional<List<ToDo>> todoList = todoService.getAll();
    if (todoList.isPresent()) {
      return ApiResponse.ok(todoList);
    }

    ApiResponse rsp = ApiResponse.error("todo not exist");
    rsp.setCode(-1);
    return rsp;
  }

  /**
   * 创建todo
   * @param request {@link CreateToDoRequest}
   * @return API response json
   */
  @PostMapping(value = "/api/todos")
  ApiResponse create(@RequestBody CreateToDoRequest request) {
    logger.info("/api/todos post request");
    ToDo todo = new ToDo();
    todo.setTitle(request.getTitle());
    todo.setStatus(request.getStatus());
    todo.setCreateTime(LocalDateTime.now());
    todo.setUpdateTime(LocalDateTime.now());

    return todoService.create(todo) ? ApiResponse.ok(todo) : ApiResponse.error("创建失败");
  }

  /**
   * 根据ID删除todo
   * @param id todoID
   * @return API response json
   */
  @DeleteMapping(value = "/api/todos/{id}")
  ApiResponse delete(@PathVariable Integer id) {
    logger.info("/api/todos/{id} delete request");
    Optional<ToDo> todo = todoService.getById(id);
    if (!todo.isPresent()) {
      ApiResponse rsp = ApiResponse.error("todo not exist");
      rsp.setCode(-1);
      return rsp;
    }
    return todoService.removeById(id) ? ApiResponse.ok() : ApiResponse.error("删除失败");
  }

  /**
   * 根据todoID查询todo
   * @param id todoID
   * @return API response json
   */
  @GetMapping(value = "/api/todos/{id}")
  ApiResponse get(@PathVariable Integer id) {
    logger.info("/api/todos/{id} get request");
    Optional<ToDo> todo = todoService.getById(id);
    if (todo.isPresent()) {
      return ApiResponse.ok(todo);
    }

    ApiResponse rsp = ApiResponse.error("todo not exist");
    rsp.setCode(-1);
    return rsp;
  }

  /**
   * 根据todoID todo
   * @param request {@link UpdateToDoRequest}
   * @return API response json
   */
  @PutMapping(value = "/api/todos")
  ApiResponse update(@RequestBody UpdateToDoRequest request) {
    logger.info("/api/todos put request");
    Optional<ToDo> queryToDo = todoService.getById(request.getId());
    if (!queryToDo.isPresent()) {
      ApiResponse rsp = ApiResponse.error("todo not exist");
      rsp.setCode(-1);
      return rsp;
    }

    ToDo todo = new ToDo();
    todo.setId(request.getId());
    todo.setTitle(request.getTitle());
    todo.setStatus(request.getStatus());
    todo.setUpdateTime(LocalDateTime.now());

    return todoService.updateById(todo) ? ApiResponse.ok() : ApiResponse.error("更新失败");
  }
}
