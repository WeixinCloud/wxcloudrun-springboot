package com.tencent.wxcloudrun.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxPrePayDTO implements Serializable {
    private String appId;
    private String openid;
    private String mch_id;
    private String nonce_str;
    private String sign;
    private String body;
    private String out_trade_no;
    private Integer total_fee;
    private String spbill_create_ip;
    private String notify_url;
    private String trade_type;
}
