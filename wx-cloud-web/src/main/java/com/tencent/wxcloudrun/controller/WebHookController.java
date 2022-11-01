package com.tencent.wxcloudrun.controller;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.annotation.ApiRequest;
import com.tencent.wxcloudrun.service.WebhookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author tangsh
 * @date 2022/10/27
 */
@RestController
@RequestMapping("/webhook")
@Slf4j
@ApiIgnore
public class WebHookController {

    @Autowired
    private WebhookService webhookService;

    @PostMapping("/v1/pay")
    @ApiRequest
    public JSONObject payCallback(@RequestBody JSONObject req) {
        return webhookService.respWxPayHook(req);
    }
}
