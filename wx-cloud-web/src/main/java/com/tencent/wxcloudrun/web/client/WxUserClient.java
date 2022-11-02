package com.tencent.wxcloudrun.web.client;

import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.tencent.wxcloudrun.web.utils.WxUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author tangsh
 * @date 2022/10/30
 */
@Service
@Slf4j
public class WxUserClient {

    @Value("${wx.app.get.phone.num.url:https://api.weixin.qq.com/wxa/business/getuserphonenumber}")
    public String WX_APP_PHONE_NUM_URL;
    @Value("${wx.app.login.url:https://api.weixin.qq.com/sns/jscode2session}")
    public String WX_APP_LOGIN_URL;
    @Value("${wx.app.id.:wx180962a99caf9ff5}")
    public String WX_APP_ID;
    @Value("${wx.app.secret:44cf33babf1f631535c88a602be811a3}")
    public String WX_APP_SECRET;


    public JSONObject login(String code) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("appid", WX_APP_ID);
        reqJson.put("secret", WX_APP_SECRET);
        reqJson.put("js_code", code);
        reqJson.put("grant_type", "authorization_code");
        String response = HttpRequest.get(WX_APP_LOGIN_URL)
                .form(reqJson)
                .body();
        log.info("请求:{},响应:{}", reqJson.toJSONString(), response);
        return JSONObject.parseObject(response);
    }

    public JSONObject getPhoneNum(String code) {
        JSONObject reqJson = new JSONObject();
        reqJson.put("code", code);
        String response = HttpRequest.post(WX_APP_PHONE_NUM_URL)
                .header(HttpRequest.HEADER_CONTENT_TYPE, HttpRequest.CONTENT_TYPE_JSON)
                .send(reqJson.toJSONString())
                .body();
        log.info("请求:{},响应:{}", reqJson.toJSONString(), response);
        return WxUtils.getPhoneInfo(response);
    }

}
