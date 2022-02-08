package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserinfoMapper {

  void upsertUser(Userinfo userinfo);

  void clearUser(@Param("id") String id);


  void upsertUserEmotion(Userinfo userinfo);
}
