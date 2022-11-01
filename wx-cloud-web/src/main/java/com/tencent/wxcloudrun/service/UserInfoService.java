package com.tencent.wxcloudrun.service;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.request.UserCodeParam;
import com.tencent.wxcloudrun.request.UserInfoParam;
import com.tencent.wxcloudrun.request.UserLoginParam;
import com.tencent.wxcloudrun.response.UserInfoResult;

/**
 * @author tangsh
 * @date 2022/10/27
 */

public interface UserInfoService {

    JSONObject login(UserLoginParam param);

    JSONObject getPhoneNum(String openid, UserCodeParam param);

    UserInfoResult getUserInfo(UserInfoParam param);

    String createInviteCode(String openid);
}
