package com.tencent.wxcloudrun.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ChatController {

    @PostMapping(value = "/chat")
    public String chat(@RequestParam(required = false) String user) {
        return "Server: "+ UUID.randomUUID().toString();
    }
}
