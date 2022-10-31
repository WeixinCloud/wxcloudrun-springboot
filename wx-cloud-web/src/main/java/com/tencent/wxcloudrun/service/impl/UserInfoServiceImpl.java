package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.client.WxUserClient;
import com.tencent.wxcloudrun.model.request.UseCodeParam;
import com.tencent.wxcloudrun.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tangsh
 * @date 2022/10/31
 */

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private WxUserClient wxUserClient;

    @Override
    public JSONObject getPhoneNum(UseCodeParam param) {
        String code = param.getCode();
        JSONObject respJson = wxUserClient.getPhoneNum(code);

        //TODO 查询用户信息
        log.info("{}", respJson.toJSONString());
        return respJson;
    }
}
