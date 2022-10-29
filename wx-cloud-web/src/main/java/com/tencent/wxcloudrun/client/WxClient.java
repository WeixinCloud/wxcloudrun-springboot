package com.tencent.wxcloudrun.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.tencent.wxcloudrun.dto.WxPrePayParam;
import com.tencent.wxcloudrun.utils.PayUtils;
import com.wechat.pay.java.core.util.NonceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.SortedMap;

@Service
@Slf4j
public class WxClient {

    @Value("${wx.created.order.url:http://api.weixin.qq.com/_/pay/unifiedorder}")
    public String WX_CREATE_ORDER_URL;
    @Value("${wx.v3.api.secret:xQauK8PrfXR0l9m4lV1J2vbXFj6ZOGfh}")
    public String WX_V3_API_SECRET;


    public void prePay(WxPrePayParam param) {
        JSONObject reqJson = (JSONObject) JSONObject.toJSON(param);
        String nonce_str = NonceUtil.createNonce(16);
        reqJson.put("nonce_str", nonce_str);
        SortedMap<String, Object> maps = JSONObject.parseObject(reqJson.toJSONString(), SortedMap.class);
        String sign = PayUtils.createSign(WX_V3_API_SECRET, maps);
        reqJson.put("sign", sign);
        log.info("sign:{},map:{}", sign, JSON.toJSONString(maps));
        String response = HttpRequest.post(WX_CREATE_ORDER_URL)
                .header(HttpRequest.HEADER_CONTENT_TYPE, HttpRequest.CONTENT_TYPE_JSON)
                .send(reqJson.toJSONString())
                .body();
        log.info("{},{}", reqJson.toJSONString(), response);
    }

}
