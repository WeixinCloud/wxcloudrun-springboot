package com.tencent.wxcloudrun.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxPrePayParam implements Serializable {

    private String body;
    private Integer total_fee;
}
