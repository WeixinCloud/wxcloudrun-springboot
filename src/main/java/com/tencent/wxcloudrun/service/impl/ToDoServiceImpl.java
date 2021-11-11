package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.ToDoMapper;
import com.tencent.wxcloudrun.model.ToDo;
import com.tencent.wxcloudrun.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {

  final ToDoMapper toDoMapper;

  public ToDoServiceImpl(@Autowired ToDoMapper toDoMapper) {
    this.toDoMapper = toDoMapper;
  }

  @Override
  public Optional<List<ToDo>> getAll() {
    return Optional.ofNullable(toDoMapper.queryAll());
  }

  @Override
  public Optional<ToDo> getById(Integer id) {
    return Optional.ofNullable(toDoMapper.queryById(id));
  }

  @Override
  public Boolean removeById(Integer id) {
    Integer effectedRows = toDoMapper.delete(id);
    return effectedRows == 1;
  }

  @Override
  public Boolean updateById(ToDo todo) {
    return toDoMapper.update(todo) == 1;
  }

  @Override
  public Boolean create(ToDo todo) {
    return toDoMapper.insert(todo) == 1;
  }
}
