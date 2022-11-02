package com.tencent.wxcloudrun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.common.dto.PageDTO;
import com.tencent.wxcloudrun.dao.entity.AdsInfoEntity;
import com.tencent.wxcloudrun.dao.entity.AdsOrderEntity;
import com.tencent.wxcloudrun.common.request.AdsPageParam;
import com.tencent.wxcloudrun.common.request.OrderPageParam;
import com.tencent.wxcloudrun.common.request.UserLoginParam;
import com.tencent.wxcloudrun.web.WxCloudRunApplication;
import com.tencent.wxcloudrun.web.service.AdsInfoService;
import com.tencent.wxcloudrun.web.service.OrderInfoService;
import com.tencent.wxcloudrun.web.service.UserInfoService;
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
    private UserInfoService userInfoService;


    @Test
    public void test_query_ads_page() {
        int pageNo = 0;
        int pageSize = 20;
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
        String msg = "{\"code\":\"041tVGFa1LRKaE0JjJIa1n5QyX1tVGFi\",\"iv\":\"uAxz60obe+xAHakCJkOvAw==\",\"encryptedData\":\"BKyCdVKPBQxYqiii1SCWR3NcFyAfkMYh6lyJ7RGPTk0l0rbWiLwsevBAHfqH7ey0WeEzB2+wb20ta1dlIlUv87Qz5RZmuxlc5mSGl9kKd42zI/PNt1gaArjIkpwQND6f1mLGS24fHszAn3B92tOZYk/OVN/3gjNUIv9Yl077UFpx0Z8TdAU/mNfoKMHBwxkSn4Fpf8GJHOQQ0X79Ej0bp77uUsYBtZtPj4wVZ5cPbAAzMfzDWXV8hzmhe7bFUicrRuaASmiDpokWF+/EEpYii2PsX5itUyKtJUtQ41gymjBbLwqCeXTAvvDI3DqH+h4rg9ZtpY6ss0WG5OVnLrOUh+QIhXSrPVahCQddel/2OUorc9OpX+GSb+JmfhVtFiKwGaJboeF/kyRT81w12iJSAg==\",\"signature\":\"a3946f98bff3138e25934440b0d6ee6cf75ec606\",\"rawData\":\"{\\\"nickName\\\":\\\"铭\\\",\\\"gender\\\":0,\\\"language\\\":\\\"zh_CN\\\",\\\"city\\\":\\\"\\\",\\\"province\\\":\\\"\\\",\\\"country\\\":\\\"\\\",\\\"avatarUrl\\\":\\\"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKeQj8NaUBPGkrS0ricGiccRC2JtARwU6vbbeOtQWI1naicNqxL7icHBD4x2iaEV9wicRcWwRBpdo7pxKFA/132\\\"}\"}";
        UserLoginParam param = JSONObject.parseObject(msg, UserLoginParam.class);
        userInfoService.login(param);
    }


}

