package com.tencent.wxcloudrun.common.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author tangsh
 * @date 2022/10/31
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailParam implements Serializable {

    @NotNull(message = "订单号不能为空")
    private String outTradeNo;

}
