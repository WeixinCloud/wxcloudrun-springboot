package com.tencent.wxcloudrun;

import com.alibaba.fastjson.JSON;
import com.tencent.wxcloudrun.entity.AdsInfoEntity;
import com.tencent.wxcloudrun.model.dto.PageDTO;
import com.tencent.wxcloudrun.model.request.AdsPageParam;
import com.tencent.wxcloudrun.service.AdsInfoService;
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

    @Test
    public void test_query_ads_page() {
        int pageNo = 0;
        int pageSize = 2;
        AdsPageParam param = AdsPageParam.builder().pageNo(pageNo).pageSize(pageSize).build();
        PageDTO<AdsInfoEntity> pageDTO = adsInfoService.page(param);
        log.info("{}", JSON.toJSONString(pageDTO));
    }
}

