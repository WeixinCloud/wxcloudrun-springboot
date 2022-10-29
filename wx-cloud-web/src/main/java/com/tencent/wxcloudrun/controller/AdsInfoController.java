
package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.annotation.ApiRequest;
import com.tencent.wxcloudrun.model.request.AdsPageParam;
import com.tencent.wxcloudrun.model.dto.PageDTO;
import com.tencent.wxcloudrun.model.request.WxPrePayParam;
import com.tencent.wxcloudrun.entity.AdsInfoEntity;
import com.tencent.wxcloudrun.model.response.Result;
import com.tencent.wxcloudrun.service.AdsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangsh
 * @date 2022/10/27
 */
@RestController
@RequestMapping("/front")
public class AdsInfoController {

    @Autowired
    private AdsInfoService adsInfoService;

    @PostMapping("/v1/ads/page")
    @ApiRequest
    public Result<PageDTO<AdsInfoEntity>> page(@RequestBody @Validated AdsPageParam param) {
        PageDTO<AdsInfoEntity> pageResult = adsInfoService.page(param);
        return Result.Success(pageResult);
    }

    @PostMapping("/v1/ads/pay")
    @ApiRequest
    public Result<Void> pay(@RequestBody @Validated WxPrePayParam param) {
        adsInfoService.prePay(param);
        return Result.Success();
    }
}
