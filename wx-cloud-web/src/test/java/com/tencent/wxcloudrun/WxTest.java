package com.tencent.wxcloudrun;

import com.alibaba.fastjson.JSON;
import com.tencent.wxcloudrun.client.WxUserClient;
import com.tencent.wxcloudrun.entity.AdsInfoEntity;
import com.tencent.wxcloudrun.entity.AdsOrderEntity;
import com.tencent.wxcloudrun.model.dto.PageDTO;
import com.tencent.wxcloudrun.model.request.AdsPageParam;
import com.tencent.wxcloudrun.model.request.OrderPageParam;
import com.tencent.wxcloudrun.service.AdsInfoService;
import com.tencent.wxcloudrun.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private WxUserClient wxUserClient;


    @Test
    public void test_query_ads_page() {
        int pageNo = 0;
        int pageSize = 2;
        AdsPageParam param = AdsPageParam.builder().pageNo(pageNo).pageSize(pageSize).build();
        PageDTO<AdsInfoEntity> pageDTO = adsInfoService.page(param);
        log.info("{}", JSON.toJSONString(pageDTO));
    }

    @Test
    public void test_query_order_page() {
        int pageNo = 0;
        int pageSize = 2;
        String openid = "oPoo441ctvw8R0EwYeM8oT0bVNbo";
        OrderPageParam param = OrderPageParam.builder().pageNo(pageNo).pageSize(pageSize).build();
        PageDTO<AdsOrderEntity> pageDTO = orderInfoService.page(openid, param);
        log.info("{}", JSON.toJSONString(pageDTO));
    }

    @Test
    public void get_user_info() {
        String code = "021F8j000lDgPO1u1D100IpFvr3F8j0I";
        wxUserClient.login(code);
    }
}

