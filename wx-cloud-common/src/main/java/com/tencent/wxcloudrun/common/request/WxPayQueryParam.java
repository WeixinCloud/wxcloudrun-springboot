package com.tencent.wxcloudrun.common.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author tangsh
 * @date 2022/10/30
 */
@Data
public class WxPayQueryParam implements Serializable {

    @NotNull(message = "微信支付订单号不能为空")
    @ApiModelProperty(value = "微信支付订单号", required = true)
    private String outTradeNo;
}
