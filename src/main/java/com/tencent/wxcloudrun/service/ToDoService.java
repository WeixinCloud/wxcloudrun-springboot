package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.ToDo;

import java.util.Optional;
import java.util.List;

public interface ToDoService {

  Optional<List<ToDo>> getAll();

  Optional<ToDo> getById(Integer id);

  Boolean removeById(Integer id);

  Boolean updateById(ToDo todo);

  Boolean create(ToDo todo);
}
