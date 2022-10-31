package com.tencent.wxcloudrun.controller;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.annotation.ApiRequest;
import com.tencent.wxcloudrun.model.response.Result;
import lombok.extern.slf4j.Slf4j;
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
@ApiIgnore
@Slf4j
public class WebHookController {

    @PostMapping("/v1/pay")
    @ApiRequest
    public Result<Void> payCallback(@RequestBody JSONObject resp) {
        log.info("回调成功..{}", resp.toJSONString());
        return Result.Success();
    }
}
