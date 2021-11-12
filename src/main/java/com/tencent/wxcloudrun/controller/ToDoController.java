package com.tencent.wxcloudrun.controller;

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

  public ToDoController(@Autowired ToDoService todoService) {
    this.todoService = todoService;
  }

  /**
   * 主页页面
   * @return API response html
   */
  @GetMapping
  public String index() {
    return "<!doctype html><html lang=\"en\"><head><meta charset=\"utf-8\"/><link rel=\"icon\" href=\"https://cloudbase-run-todolist-92bb28a0d-1258016615.tcloudbaseapp.com/favicon.ico\"/><meta name=\"viewport\" content=\"width=device-width,initial-scale=1\"/><meta name=\"theme-color\" content=\"#000000\"/><meta name=\"description\" content=\"Web site created using create-react-app\"/><link rel=\"apple-touch-icon\" href=\"https://cloudbase-run-todolist-92bb28a0d-1258016615.tcloudbaseapp.com/logo192.png\"/><link rel=\"manifest\" href=\"https://cloudbase-run-todolist-92bb28a0d-1258016615.tcloudbaseapp.com/manifest.json\"/><title>Todo List</title><link href=\"https://cloudbase-run-todolist-92bb28a0d-1258016615.tcloudbaseapp.com/static/css/2.20aa2d7b.chunk.css\" rel=\"stylesheet\"><link href=\"https://cloudbase-run-todolist-92bb28a0d-1258016615.tcloudbaseapp.com/static/css/main.d8680f04.chunk.css\" rel=\"stylesheet\"></head><body><noscript>You need to enable JavaScript to run this app.</noscript><div id=\"root\"></div><script>!function(e){function t(t){for(var n,l,a=t[0],p=t[1],i=t[2],f=0,s=[];f<a.length;f++)l=a[f],Object.prototype.hasOwnProperty.call(o,l)&&o[l]&&s.push(o[l][0]),o[l]=0;for(n in p)Object.prototype.hasOwnProperty.call(p,n)&&(e[n]=p[n]);for(c&&c(t);s.length;)s.shift()();return u.push.apply(u,i||[]),r()}function r(){for(var e,t=0;t<u.length;t++){for(var r=u[t],n=!0,a=1;a<r.length;a++){var p=r[a];0!==o[p]&&(n=!1)}n&&(u.splice(t--,1),e=l(l.s=r[0]))}return e}var n={},o={1:0},u=[];function l(t){if(n[t])return n[t].exports;var r=n[t]={i:t,l:!1,exports:{}};return e[t].call(r.exports,r,r.exports,l),r.l=!0,r.exports}l.m=e,l.c=n,l.d=function(e,t,r){l.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:r})},l.r=function(e){\"undefined\"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:\"Module\"}),Object.defineProperty(e,\"__esModule\",{value:!0})},l.t=function(e,t){if(1&t&&(e=l(e)),8&t)return e;if(4&t&&\"object\"==typeof e&&e&&e.__esModule)return e;var r=Object.create(null);if(l.r(r),Object.defineProperty(r,\"default\",{enumerable:!0,value:e}),2&t&&\"string\"!=typeof e)for(var n in e)l.d(r,n,function(t){return e[t]}.bind(null,n));return r},l.n=function(e){var t=e&&e.__esModule?function(){return e.default}:function(){return e};return l.d(t,\"a\",t),t},l.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},l.p=\"https://cloudbase-run-todolist-92bb28a0d-1258016615.tcloudbaseapp.com/\";var a=this.webpackJsonptodo=this.webpackJsonptodo||[],p=a.push.bind(a);a.push=t,a=a.slice();for(var i=0;i<a.length;i++)t(a[i]);var c=p;r()}([])</script><script src=\"https://cloudbase-run-todolist-92bb28a0d-1258016615.tcloudbaseapp.com/static/js/2.18b41bed.chunk.js\"></script><script src=\"https://cloudbase-run-todolist-92bb28a0d-1258016615.tcloudbaseapp.com/static/js/main.bde3e603.chunk.js\"></script></body></html>";
  }

  /**
   * 获取所有todo
   * @return API response json
   */
  @GetMapping(value = "/api/todos")
  ApiResponse getAll() {
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
