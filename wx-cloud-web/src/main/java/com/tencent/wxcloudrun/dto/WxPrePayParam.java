package com.tencent.wxcloudrun.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxPrePayParam implements Serializable {

    private String body;
    private String openid;
    private String out_trade_no;
    private String spbill_create_ip;
    private String env_id;
    private String sub_mch_id;
    private Integer total_fee;
    private Integer callback_type;
    private WxContainerDTO container;
    private String nonce_str;
}
