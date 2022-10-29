package com.tencent.wxcloudrun.client;

import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.tencent.wxcloudrun.dto.WxPrePayParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WxClient {

    @Value("${wx.created.order.url:http://api.weixin.qq.com/_/pay/unifiedorder}")
    public String WX_CREATE_ORDER_URL;

    public void prePay(WxPrePayParam param) {
        JSONObject reqJson = (JSONObject) JSONObject.toJSON(param);
        String response = HttpRequest.post(WX_CREATE_ORDER_URL)
                .header(HttpRequest.HEADER_CONTENT_TYPE, HttpRequest.CONTENT_TYPE_JSON)
                .send(reqJson.toJSONString())
                .body();
        log.info("{},{}", reqJson.toJSONString(), response);
    }
}
