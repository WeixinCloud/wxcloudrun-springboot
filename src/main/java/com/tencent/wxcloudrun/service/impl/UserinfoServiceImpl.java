package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.UserinfoMapper;
import com.tencent.wxcloudrun.model.Userinfo;
import com.tencent.wxcloudrun.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserinfoServiceImpl implements UserinfoService {

  final UserinfoMapper userinfoMapper;


  public UserinfoServiceImpl(@Autowired UserinfoMapper userinfoMapper) {
    this.userinfoMapper = userinfoMapper;
  }


  @Override
  public void upsertUser(Userinfo userinfo) {
    userinfoMapper.upsertUser(userinfo);
  }


  @Override
  public void clearUser(String id) {
    userinfoMapper.clearUser(id);
  }


  @Override
  public void upsertUserEmotion(Userinfo userinfo) {
    userinfoMapper.upsertUserEmotion(userinfo);
  }
}
