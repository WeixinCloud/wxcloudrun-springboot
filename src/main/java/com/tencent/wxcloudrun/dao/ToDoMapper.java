package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.ToDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ToDoMapper {

  List<ToDo> queryAll();

  ToDo queryById(@Param("id") Integer id);

  Integer insert(ToDo todo);

  Integer delete(@Param("id") Integer id);

  Integer update(ToDo todo);
}
