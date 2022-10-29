package com.tencent.wxcloudrun.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxContainerDTO implements Serializable {

    private String service;
    private String path;
}
