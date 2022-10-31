package com.tencent.wxcloudrun.client;

import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.tencent.wxcloudrun.utils.WxUtils;
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
