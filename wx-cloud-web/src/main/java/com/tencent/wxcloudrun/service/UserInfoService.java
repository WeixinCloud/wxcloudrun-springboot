package com.tencent.wxcloudrun.service;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.entity.UserEntity;
import com.tencent.wxcloudrun.request.UserCodeParam;
import com.tencent.wxcloudrun.request.UserInfoParam;
import com.tencent.wxcloudrun.request.UserLoginParam;

/**
 * @author tangsh
 * @date 2022/10/27
 */

public interface UserInfoService {

    JSONObject login(UserLoginParam param);

    JSONObject getPhoneNum(UserCodeParam param);

    UserEntity getUserInfo(UserInfoParam param);
}
