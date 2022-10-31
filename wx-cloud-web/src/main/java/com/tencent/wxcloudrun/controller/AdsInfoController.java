
package com.tencent.wxcloudrun.controller;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.annotation.ApiRequest;
import com.tencent.wxcloudrun.entity.AdsInfoEntity;
import com.tencent.wxcloudrun.model.dto.PageDTO;
import com.tencent.wxcloudrun.model.request.AdsPageParam;
import com.tencent.wxcloudrun.model.request.WxPayCloseParam;
import com.tencent.wxcloudrun.model.request.WxPayQueryParam;
import com.tencent.wxcloudrun.model.request.WxPrePayParam;
import com.tencent.wxcloudrun.model.response.Result;
import com.tencent.wxcloudrun.service.AdsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author tangsh
 * @date 2022/10/27
 */
@Api(tags = "广告模块")
@RestController
@RequestMapping("/front")
@Slf4j
public class AdsInfoController {

    @Autowired
    private AdsInfoService adsInfoService;

    @ApiOperation("广告查询-分页")
    @PostMapping("/v1/ads/page")
    @ApiRequest
    public Result<PageDTO<AdsInfoEntity>> page(@RequestBody @Validated AdsPageParam param) {
        PageDTO<AdsInfoEntity> pageResult = adsInfoService.page(param);
        return Result.Success(pageResult);
    }

    @ApiOperation("广告订单-预支付")
    @PostMapping("/v1/ads/pay")
    @ApiRequest
    public Result<JSONObject> prePay(@RequestHeader("x-wx-openid") String openid,
                                     @RequestHeader("x-real-ip") String ip,
                                     @RequestBody @Validated WxPrePayParam param) {
        JSONObject result = adsInfoService.prePay(openid, ip, param);
        return Result.Success(result);
    }

    @ApiOperation("广告订单-查询")
    @PostMapping("/v1/ads/pay-query")
    @ApiRequest
    public Result<JSONObject> payQuery(@RequestHeader("x-wx-openid") String openid, @RequestBody WxPayQueryParam param) {
        JSONObject result = adsInfoService.payQuery(openid, param);
        return Result.Success(result);
    }

    @ApiOperation("广告订单-关闭")
    @PostMapping("/v1/ads/pay-close")
    @ApiRequest
    public Result<JSONObject> payClose(@RequestHeader("x-wx-openid") String openid, @RequestBody WxPayCloseParam param) {
        JSONObject result = adsInfoService.payClose(openid, param);
        return Result.Success(result);
    }
}
