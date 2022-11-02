package com.tencent.wxcloudrun.web.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.common.annotation.ApiRequest;
import com.tencent.wxcloudrun.web.service.WebhookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "回调模块")
@RestController
@RequestMapping("/webhook")
@ApiIgnore
public class WebHookController {

    @Autowired
    private WebhookService webhookService;

    @ApiOperation("回调模块-下单回调")
    @PostMapping("/v1/pay")
    @ApiRequest
    public JSONObject payCallback(@RequestBody JSONObject req) {
        return webhookService.respWxPayHook(req);
    }

    @ApiOperation("回调模块-退款回调")
    @PostMapping("/v1/refund")
    @ApiRequest
    public JSONObject refundCallback(@RequestBody JSONObject req) {
        return webhookService.respWxPayHook(req);
    }
}
