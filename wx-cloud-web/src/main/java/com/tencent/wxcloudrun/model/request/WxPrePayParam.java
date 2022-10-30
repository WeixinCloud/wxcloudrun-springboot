package com.tencent.wxcloudrun.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxPrePayParam implements Serializable {

    private String openid;
    private String body;
    private String out_trade_no;
    private String spbill_create_ip;
    private String env_id;
    private String sub_mch_id;
    private Integer total_fee;
    private Integer callback_type;
    private Container container;
    private String trade_type;

    @Data
    public class Container {
        private String service;
        private String path;
    }
}
