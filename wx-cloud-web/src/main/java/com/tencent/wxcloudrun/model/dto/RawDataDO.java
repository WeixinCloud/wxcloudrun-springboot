package com.tencent.wxcloudrun.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tangsh
 * @date 2022/11/01
 */
@Data
public class RawDataDO implements Serializable {

    private String nickName;
    private String avatarUrl;
    private Integer gender;
    private String city;
    private String country;
    private String province;
}
