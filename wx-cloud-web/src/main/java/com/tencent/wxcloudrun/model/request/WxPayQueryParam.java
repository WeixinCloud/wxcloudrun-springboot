package com.tencent.wxcloudrun.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tangsh
 * @date 2022/10/30
 */
@Data
public class WxPayQueryParam implements Serializable {
    private String openid;
    private String out_trade_no;
}
