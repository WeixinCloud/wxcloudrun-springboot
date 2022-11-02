

package com.tencent.wxcloudrun.web.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.common.annotation.ApiRequest;
import com.tencent.wxcloudrun.common.request.UserCodeParam;
import com.tencent.wxcloudrun.common.request.UserInfoParam;
import com.tencent.wxcloudrun.common.request.UserLoginParam;
import com.tencent.wxcloudrun.common.response.Result;
import com.tencent.wxcloudrun.common.response.UserInfoResult;
import com.tencent.wxcloudrun.web.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author tangsh
 * @date 2022/10/27
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/front")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation("用户模块-登录")
    @PostMapping("/v1/user/login")
    @ApiRequest
    public Result<JSONObject> login(@RequestBody @Validated UserLoginParam param) {
        return Result.Success(userInfoService.login(param));
    }

    @ApiOperation("用户模块-获取用户信息")
    @PostMapping("/v1/user/get-user-info")
    @ApiRequest
    public Result<UserInfoResult> getUserInfo(@RequestBody @Validated UserInfoParam param) {
        return Result.Success(userInfoService.getUserInfo(param));
    }

    @ApiOperation("用户模块-获取用户手机号")
    @PostMapping("/v1/user/get-phone-num")
    @ApiRequest
    public Result<JSONObject> getPhoneNum(@RequestHeader("x-wx-openid") String openid, @RequestBody @Validated UserCodeParam param) {
        return Result.Success(userInfoService.getPhoneNum(openid, param));
    }

    @ApiOperation("用户模块-生成邀请码")
    @PostMapping("/v1/user/create-invite-code")
    @ApiRequest
    public Result<String> createInviteCode(@RequestHeader("x-wx-openid") String openid) {
        return Result.Success(userInfoService.createInviteCode(openid));
    }
}
