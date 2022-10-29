package com.tencent.wxcloudrun.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangsh
 * @date 2022/10/27
 */
@RestController
@RequestMapping("/webhook")
public class WebHookController {

    @PostMapping("/v1/pay")
    public void payCallback(){

    }
}
