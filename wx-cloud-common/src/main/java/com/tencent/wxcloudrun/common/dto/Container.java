package com.tencent.wxcloudrun.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tangsh
 * @date 2022/10/27
 */
@Data
public class Container implements Serializable {
    private String service;
    private String path;
}
