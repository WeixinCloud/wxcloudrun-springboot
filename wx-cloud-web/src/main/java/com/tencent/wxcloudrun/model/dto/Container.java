package com.tencent.wxcloudrun.model.dto;

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
