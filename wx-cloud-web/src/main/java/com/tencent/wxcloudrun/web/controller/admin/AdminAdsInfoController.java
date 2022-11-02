
package com.tencent.wxcloudrun.web.controller.admin;

import com.tencent.wxcloudrun.common.annotation.ApiRequest;
import com.tencent.wxcloudrun.common.dto.PageDTO;
import com.tencent.wxcloudrun.common.request.AdsBaseParam;
import com.tencent.wxcloudrun.common.request.AdsCreateParam;
import com.tencent.wxcloudrun.common.request.AdsEditParam;
import com.tencent.wxcloudrun.common.request.AdsPageParam;
import com.tencent.wxcloudrun.common.response.Result;
import com.tencent.wxcloudrun.dao.entity.AdsInfoEntity;
import com.tencent.wxcloudrun.web.service.AdminAdsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "广告后台模块")
@RestController
@RequestMapping("/admin")
public class AdminAdsInfoController {

    @Autowired
    private AdminAdsInfoService adsInfoService;

    @ApiOperation("广告后台-分页")
    @PostMapping("/v1/ads/page")
    @ApiRequest
    public Result<PageDTO<AdsInfoEntity>> page(@RequestBody @Validated AdsPageParam param) {
        PageDTO<AdsInfoEntity> pageResult = adsInfoService.page(param);
        return Result.Success(pageResult);
    }

    @ApiOperation("广告后台-详情")
    @PostMapping("/v1/ads/detail")
    @ApiRequest
    public Result<AdsInfoEntity> detail(@RequestBody @Validated AdsBaseParam param) {
        return Result.Success(adsInfoService.detail(param));
    }

    @ApiOperation("广告后台-上架")
    @PostMapping("/v1/ads/status-on")
    @ApiRequest
    public Result<Void> statusOn(@RequestBody @Validated AdsBaseParam param) {
        adsInfoService.on(param);
        return Result.Success();
    }

    @ApiOperation("广告后台-下架")
    @PostMapping("/v1/ads/status-off")
    @ApiRequest
    public Result<Void> statusOff(@RequestBody @Validated AdsBaseParam param) {
        adsInfoService.off(param);
        return Result.Success();
    }

    @ApiOperation("广告后台-创建")
    @PostMapping("/v1/ads/create")
    @ApiRequest
    public Result<Void> edit(@RequestBody @Validated AdsCreateParam param) {
        adsInfoService.create(param);
        return Result.Success();
    }

    @ApiOperation("广告后台-编辑")
    @PostMapping("/v1/ads/edit")
    @ApiRequest
    public Result<Void> edit(@RequestBody @Validated AdsEditParam param) {
        adsInfoService.edit(param);
        return Result.Success();
    }

}
