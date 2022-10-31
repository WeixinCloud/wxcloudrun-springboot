

package com.tencent.wxcloudrun.controller;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.annotation.ApiRequest;
import com.tencent.wxcloudrun.model.request.UseCodeParam;
import com.tencent.wxcloudrun.model.response.Result;
import com.tencent.wxcloudrun.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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

    @ApiOperation("用户模块-获取用户手机号")
    @PostMapping("/v1/user/get-phone-num")
    @ApiRequest
    public Result<JSONObject> getPhoneNum(@RequestBody @Validated UseCodeParam param) {
        return Result.Success(userInfoService.getPhoneNum(param));
    }
}
