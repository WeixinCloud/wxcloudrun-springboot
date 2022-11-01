package com.tencent.wxcloudrun.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tangsh
 * @date 2022/10/30
 */
@Data
public class UseLoginParam implements Serializable {

    private String code;

}
