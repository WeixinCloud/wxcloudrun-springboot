package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.annotation.ApiRequest;
import com.tencent.wxcloudrun.model.response.Result;
import org.springframework.web.bind.annotation.PostMapping;
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
public class WebHookController {

    @PostMapping("/v1/pay")
    @ApiRequest
    public Result<Void> payCallback(){
        return Result.Success();
    }
}
