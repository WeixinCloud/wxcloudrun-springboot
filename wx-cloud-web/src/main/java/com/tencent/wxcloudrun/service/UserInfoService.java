package com.tencent.wxcloudrun.service;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.model.request.UseCodeParam;

/**
 * @author tangsh
 * @date 2022/10/27
 */

public interface UserInfoService {

   JSONObject getPhoneNum(UseCodeParam param);
}
