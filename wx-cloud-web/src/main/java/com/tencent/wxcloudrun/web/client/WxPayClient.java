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
public class WxPayClient {


    @Value("${wx.cloud.created.order.url:http://api.weixin.qq.com/_/pay/unifiedorder}")
    public String WX_CLOUD_PRE_ORDER_URL;
    @Value("${wx.cloud.created.order.url:http://api.weixin.qq.com/_/pay/refund}")
    public String WX_CLOUD_REFUND_ORDER_URL;
    @Value("${wx.cloud.query.order.url:http://api.weixin.qq.com/_/pay/queryorder}")
    public String WX_CLOUD_QUERY_ORDER_URL;
    @Value("${wx.cloud.close.order.url:http://api.weixin.qq.com/_/pay/closeorder}")
    public String WX_CLOUD_CLOSE_ORDER_URL;


    public JSONObject prePay(JSONObject reqJson) {
        String response = HttpRequest.post(WX_CLOUD_PRE_ORDER_URL)
                .header(HttpRequest.HEADER_CONTENT_TYPE, HttpRequest.CONTENT_TYPE_JSON)
                .send(reqJson.toJSONString())
                .body();
        log.info("请求:{},响应:{}", reqJson.toJSONString(), response);
        return WxUtils.getData(response);
    }

    public JSONObject refund(JSONObject reqJson) {
        String response = HttpRequest.post(WX_CLOUD_REFUND_ORDER_URL)
                .header(HttpRequest.HEADER_CONTENT_TYPE, HttpRequest.CONTENT_TYPE_JSON)
                .send(reqJson.toJSONString())
                .body();
        log.info("请求:{},响应:{}", reqJson.toJSONString(), response);
        return WxUtils.getData(response);
    }

    public JSONObject payQuery(JSONObject reqJson) {
        String response = HttpRequest.post(WX_CLOUD_QUERY_ORDER_URL)
                .header(HttpRequest.HEADER_CONTENT_TYPE, HttpRequest.CONTENT_TYPE_JSON)
                .send(reqJson.toJSONString())
                .body();
        log.info("请求:{},响应:{}", reqJson.toJSONString(), response);
        return WxUtils.getData(response);
    }

    public JSONObject payClose(JSONObject reqJson) {
        String response = HttpRequest.post(WX_CLOUD_CLOSE_ORDER_URL)
                .header(HttpRequest.HEADER_CONTENT_TYPE, HttpRequest.CONTENT_TYPE_JSON)
                .send(reqJson.toJSONString())
                .body();
        log.info("请求:{},响应:{}", reqJson.toJSONString(), response);
        return WxUtils.getData(response);
    }
}
