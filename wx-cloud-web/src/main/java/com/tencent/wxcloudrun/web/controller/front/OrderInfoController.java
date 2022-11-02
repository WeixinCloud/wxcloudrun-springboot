
package com.tencent.wxcloudrun.web.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.common.annotation.ApiRequest;
import com.tencent.wxcloudrun.common.dto.PageDTO;
import com.tencent.wxcloudrun.dao.entity.AdsOrderEntity;
import com.tencent.wxcloudrun.common.request.OrderDetailParam;
import com.tencent.wxcloudrun.common.request.OrderPageParam;
import com.tencent.wxcloudrun.common.response.Result;
import com.tencent.wxcloudrun.web.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author tangsh
 * @date 2022/10/27
 */
@Api(tags = "订单模块")
@RestController
@RequestMapping("/front")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @ApiOperation("订单查询-分页")
    @PostMapping("/v1/order/page")
    @ApiRequest
    public Result<PageDTO<AdsOrderEntity>> page(@RequestHeader("x-wx-openid") String openid, @RequestBody @Validated OrderPageParam param) {
        PageDTO<AdsOrderEntity> pageResult = orderInfoService.page(openid, param);
        return Result.Success(pageResult);
    }

    @ApiOperation("订单查询-详情")
    @PostMapping("/v1/order/detail")
    @ApiRequest
    public Result<JSONObject> detail(@RequestBody @Validated OrderDetailParam param) {
        return Result.Success(orderInfoService.detail(param));
    }
}
