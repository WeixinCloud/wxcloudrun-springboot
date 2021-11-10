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

  final ToDoMapper ToDoMapper;

  public ToDoServiceImpl(@Autowired ToDoMapper ToDoMapper) {
    this.ToDoMapper = ToDoMapper;
  }

  @Override
  public Optional<List<ToDo>> getAll() {
    return Optional.ofNullable(ToDoMapper.queryAll());
  }

  @Override
  public Optional<ToDo> getById(Integer id) {
    return Optional.ofNullable(ToDoMapper.queryById(id));
  }

  @Override
  public Boolean removeById(Integer id) {
    Integer effectedRows = ToDoMapper.delete(id);
    return effectedRows == 1;
  }

  @Override
  public Boolean updateById(ToDo todo) {
    return ToDoMapper.update(todo) == 1;
  }

  @Override
  public Boolean create(ToDo todo) {
    return ToDoMapper.insert(todo) == 1;
  }
}
