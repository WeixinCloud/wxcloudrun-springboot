package com.tencent.wxcloudrun.web.service;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.common.request.UserCodeParam;
import com.tencent.wxcloudrun.common.request.UserInfoParam;
import com.tencent.wxcloudrun.common.request.UserLoginParam;
import com.tencent.wxcloudrun.common.response.UserInfoResult;

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
