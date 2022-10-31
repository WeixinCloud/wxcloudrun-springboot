package com.tencent.wxcloudrun.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tangsh
 * @date 2022/10/30
 */
@Data
public class WxPrePayParam implements Serializable {

    private String body;
    private Integer total_fee;
}
