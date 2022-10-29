package com.tencent.wxcloudrun.client;

import com.github.wxpay.sdk.WXPayUtil;
import com.tencent.wxcloudrun.model.request.WxPrePayParam;
import com.tencent.wxcloudrun.utils.HttpUtils;
import com.tencent.wxcloudrun.utils.IPUtils;
import com.tencent.wxcloudrun.utils.SignUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class WxClient {
    @Value("${wx.api.id:wx180962a99caf9ff5}")
    public String WX_APP_ID;
    @Value("${wx.mch.id:1633796573}")
    public String WX_MERCHANT_ID;
    @Value("${wx.v3.api.secret:xQauK8PrfXR0l9m4lV1J2vbXFj6ZOGfh}")
    public String WX_V3_API_SECRET;

    @Value("${wx.mch.pay.web.hook:https://springboot-mvyj-15312-5-1314693576.sh.run.tcloudbase.com/webhook/v1/pay}")
    public String WX_V3_PAY_WEB_HOOK_URL;

    @Value("${wx.mch.order.url:https://api.mch.weixin.qq.com/pay/unifiedorder}")
    public String WX_MCH_PRE_ORDER_URL;

    @Value("${wx.created.order.url:http://api.weixin.qq.com/_/pay/unifiedorder}")
    public String WX_CLOUD_PRE_ORDER_URL;


    public void prePay(WxPrePayParam param) {
        Map<String, String> map = new HashMap<>();
        map.put("appid", WX_APP_ID);
        //微信支付分配的商户号
        map.put("mch_id", WX_MERCHANT_ID);
        //随机字符串，长度要求在32位以内
        map.put("nonce_str", WXPayUtil.generateNonceStr());
        //商品简单描述
        map.put("body", param.getBody());
        //商户系统内部订单号
        map.put("out_trade_no", param.getOut_trade_no());
        //订单总金额，单位为分
        map.put("total_fee", param.getTotal_fee());
        //支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
        map.put("spbill_create_ip", IPUtils.getLocalIp());
        //交易类型  小程序用JSAPI
        map.put("trade_type", "JSAPI");
        //回调地址  异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
        map.put("notify_url", WX_V3_PAY_WEB_HOOK_URL);
        //trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识
        map.put("openid", param.getOpenid());
        String unifiedorderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder"; // 微信统一下单URL
        try {
            String sign = SignUtils.getSign(map, WX_V3_API_SECRET);// 生成签名 PAY_API_SECRET=微信支付相关API调用时使用的秘钥
            map.put("sign", sign);  // 参数配置 我
            String xml = WXPayUtil.mapToXml(map);
            //请求微信统一下单接口
            String xmlStr = HttpUtils.httpRequest(unifiedorderUrl, "POST", xml);
        } catch (Exception e) {
            log.error("请求微信下单接口异常", e);
            throw new RuntimeException("请求微信下单接口异常", e);
        }


//
//        log.info("sign:{},map:{}", sign, JSON.toJSONString(maps));
//        String response = HttpRequest.post(WX_CREATE_ORDER_URL)
//                .header(HttpRequest.HEADER_CONTENT_TYPE, HttpRequest.CONTENT_TYPE_JSON)
//                .send(reqJson.toJSONString())
//                .body();
//        log.info("{},{}", reqJson.toJSONString(), response);
    }

}
