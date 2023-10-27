package com.tencent.wxcloudrun.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tencent.wxcloudrun.dto.LoginRequest;
import com.tencent.wxcloudrun.dto.LoginResponse;
import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String authUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Value("${wechat.app-secret}")
    private String appSecret;

    @Value("${wechat.app-id}")
    private String appId;

    @PostMapping(value = "/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        // https://api.weixin.qq.com/sns/jscode2session?appid=<AppId>&secret=<AppSecret>&js_code=<code>&grant_type=authorization_code
        log.debug("WXLogin, appid={}, code={}", appId, request.getWxcode());

        ResponseEntity<WXLoginResponse> wxResponse = restTemplate.getForEntity(String.format(authUrl, appId, appSecret, request.getWxcode()), WXLoginResponse.class);
        log.debug("WxLogin response: {}", wxResponse);
        if(wxResponse.getStatusCode().is2xxSuccessful()) {
            String openid = wxResponse.getBody().getOpenid();
            LoginResponse res = new LoginResponse();
            res.setOpenid(openid);
            return res;
        } else {
            log.error("WxLogin rpc error: ", wxResponse.getStatusCode());
            LoginResponse res = new LoginResponse();
            res.setOpenid("");
            return res;
        }

    }

    @Data
    public static final class WXLoginResponse {
        @JsonProperty("openid")
        private String openid;
        @JsonProperty("session_key")
        private String sessionKey;
        private String unionid;
        @JsonProperty("errcode")
        private int errcode;
        @JsonProperty("errmsg")
        private String errmsg;
    }
}
