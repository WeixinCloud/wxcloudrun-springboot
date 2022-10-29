package com.tencent.wxcloudrun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.tencent.wxcloudrun.dto.AdsPageParam;
import com.tencent.wxcloudrun.dto.PageDTO;
import com.tencent.wxcloudrun.entity.AdsInfoEntity;
import com.tencent.wxcloudrun.service.AdsInfoService;
import com.tencent.wxcloudrun.utils.PayUtils;
import com.wechat.pay.java.core.http.*;
import com.wechat.pay.java.core.util.NonceUtil;
import com.wechat.pay.java.service.payments.app.model.PrepayResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {WxCloudRunApplication.class})
public class WxTest {

    @Value("${wx.created.order.url:http://api.weixin.qq.com/_/pay/unifiedorder}")
    public String WX_CREATE_ORDER_URL;
    @Value("${wx.v3.api.secret:xQauK8PrfXR0l9m4lV1J2vbXFj6ZOGfh}")
    public String WX_V3_API_SECRET;

    @Autowired
    private AdsInfoService adsInfoService;

    @Test
    public void test_query_ads_page() {
        int pageNo = 0;
        int pageSize = 10;
        AdsPageParam param = AdsPageParam.builder().pageNo(pageNo).pageSize(pageSize).build();
        PageDTO<AdsInfoEntity> pageDTO = adsInfoService.page(param);
        log.info("{}", JSON.toJSONString(pageDTO));
    }

    @Test
    public void test_wx_pay() {
//        String msg = "{\"nonce_str\":\"FH036NbHNY5tGvue\",\"out_trade_no\":\"2021WERUN1647840687637\",\"env_id\":\"prod-66733tes755tabc\"}";
//        JSONObject reqJson = JSON.parseObject(msg);
//        Map maps = (Map) JSON.parse(reqJson.toJSONString());
//        String sign = PayUtils.createSign(WX_V3_API_SECRET, maps);
//        reqJson.put("sign", sign);
//        System.out.println(reqJson.toJSONString());
//        System.out.println(sign);

    }

}

