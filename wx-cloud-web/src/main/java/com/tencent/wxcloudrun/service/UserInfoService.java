package com.tencent.wxcloudrun.service;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.model.request.UseCodeParam;
import com.tencent.wxcloudrun.model.request.UseLoginParam;

/**
 * @author tangsh
 * @date 2022/10/27
 */

public interface UserInfoService {

   JSONObject login(UseLoginParam param);

   JSONObject getPhoneNum(UseCodeParam param);

}
