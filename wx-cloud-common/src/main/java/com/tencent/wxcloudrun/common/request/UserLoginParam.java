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
public class UserLoginParam implements Serializable {

    @NotNull(message = "code不能为空")
    @ApiModelProperty(value = "微信code", required = true)
    private String code;
    @ApiModelProperty(value = "用户非敏感字段")
    private String rawData;
    @ApiModelProperty(value = "签名")
    private String signature;
    @ApiModelProperty(value = "用户敏感字段")
    private String encryptedData;
    @ApiModelProperty(value = "解密向量")
    private String iv;
    @ApiModelProperty(value = "邀请码")
    private String inviteCode;

}
