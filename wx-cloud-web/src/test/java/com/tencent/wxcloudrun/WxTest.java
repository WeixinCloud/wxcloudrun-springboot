package com.tencent.wxcloudrun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.tencent.wxcloudrun.dto.AdsPageParam;
import com.tencent.wxcloudrun.dto.PageDTO;
import com.tencent.wxcloudrun.entity.AdsInfoEntity;
import com.tencent.wxcloudrun.service.AdsInfoService;
import com.wechat.pay.java.core.http.*;
import com.wechat.pay.java.service.payments.app.model.PrepayResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {WxCloudRunApplication.class})
public class WxTest {

    @Value("${wx.created.order.url:http://api.weixin.qq.com/_/pay/unifiedorder}")
    public String WX_CREATE_ORDER_URL;

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
        String msg = "{\"body\":\"测试微信支付\",\"openid\":\"oZp5njTTsWaCeEoXi14oeOVCKlik\",\"out_trade_no\":\"2021WERUN1647840687637\",\"spbill_create_ip\":\"59.37.125.120\",\"env_id\":\"prod-66733tes755tabc\",\"sub_mch_id\":\"1712734762\",\"total_fee\":1,\"callback_type\":2,\"container\":{\"service\":\"pay\",\"path\":\"/\"}}";
        HttpHeaders headers = new HttpHeaders();
        headers.addHeader("Accept", MediaType.APPLICATION_JSON.getValue());
        headers.addHeader("Content-Type", MediaType.APPLICATION_JSON.getValue());
        //HttpRequest httpRequest = (new com.wechat.pay.java.core.http.HttpRequest.Builder()).httpMethod(HttpMethod.POST).url(requestPath).headers(headers).body(this.createRequestBody(request)).build();
        //HttpResponse<PrepayResponse> httpResponse = this.httpClient.execute(httpRequest, PrepayResponse.class);
        //PrepayResponse serviceResponse = httpResponse.getServiceResponse();
        //log.info("{}",JSON.toJSONString(serviceResponse));
    }

}

