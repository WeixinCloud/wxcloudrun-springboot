package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.CounterRequest;
import com.tencent.wxcloudrun.dto.UserRequest;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.model.Userinfo;
import com.tencent.wxcloudrun.service.CounterService;
import com.tencent.wxcloudrun.service.UserinfoService;
import com.tencent.wxcloudrun.utils.baidu.FaceDetect;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * counter控制器
 */
@RestController

public class UserController {

    final UserinfoService userinfoService;
    final Logger logger;


    public UserController(@Autowired UserinfoService userinfoService) {
        this.userinfoService = userinfoService;
        this.logger = LoggerFactory.getLogger(UserController.class);
    }


    /**
     * 更新、删除用户记录
     *
     * @param request {@link UserRequest}
     * @return API response json
     */
    @PostMapping(value = "/api/user")
    ApiResponse create(@RequestBody UserRequest request) {
        logger.info("/api/user post request, action: {}", request.getAction());

        if (request.getAction().equals("inc")) {

            Userinfo userinfo = new Userinfo();
            userinfo.setId(request.getId());
            userinfo.setSex(request.getSex());
            userinfo.setLocation(request.getLocation());
            userinfoService.upsertUser(userinfo);
            return ApiResponse.ok(userinfo);
        } else if (request.getAction().equals("clear")) {
            userinfoService.clearUser(request.getId());
            return ApiResponse.ok(0);
        } else {
            return ApiResponse.error("参数action错误");
        }
    }


    @PostMapping(value = "api/emotion")
    ApiResponse emotion(@RequestParam String id, @RequestParam String src) {

        String emotion = FaceDetect.faceDetect(src);
        if (null != emotion) {
            Userinfo userinfo = new Userinfo();
            userinfo.setId(id);
            userinfo.setEmotion(emotion);

            userinfoService.upsertUserEmotion(userinfo);
            return ApiResponse.ok(emotion);
        } else {
            return ApiResponse.error("您的头像可能没有表情");
        }
    }


}