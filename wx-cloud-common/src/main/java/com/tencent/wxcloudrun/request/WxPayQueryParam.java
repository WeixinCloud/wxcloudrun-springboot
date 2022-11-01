package com.tencent.wxcloudrun.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tangsh
 * @date 2022/10/30
 */
@Data
public class WxPayQueryParam implements Serializable {
    private String out_trade_no;
}
