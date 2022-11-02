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
public class WxPrePayParam implements Serializable {

    private String body;
    @NotNull(message = "支付金额不能为空")
    @ApiModelProperty(value = "支付金额", required = true)
    private Integer total_fee;
}
