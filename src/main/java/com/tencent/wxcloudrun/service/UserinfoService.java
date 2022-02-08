package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.Userinfo;

public interface UserinfoService {

  void upsertUser(Userinfo userinfo);

  void upsertUserEmotion(Userinfo userinfo);

  void clearUser(String id);
}
