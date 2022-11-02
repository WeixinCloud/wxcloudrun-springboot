package com.tencent.wxcloudrun.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tangsh
 * @date 2022/11/01
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResult implements Serializable {

    private String openid;
    private String nickname;
    private String avatarUrl;
    private Integer gender;
    private String mobile;
    //邀请人数
    private Integer inviteNum;
    private Integer orderPayNum;

}
