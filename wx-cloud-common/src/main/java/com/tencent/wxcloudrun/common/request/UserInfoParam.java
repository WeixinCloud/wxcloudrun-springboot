package com.tencent.wxcloudrun.common.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author tangsh
 * @date 2022/10/30
 */
@Data
public class UserInfoParam implements Serializable {

    @NotNull(message = "token")
    @ApiModelProperty(value = "用户唯一凭证", required = true)
    private String token;
}
