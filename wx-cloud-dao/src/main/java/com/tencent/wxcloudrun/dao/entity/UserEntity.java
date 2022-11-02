package com.tencent.wxcloudrun.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author tangsh
 * @date 2022/11/01
 */
@Data
@TableName("user")
public class UserEntity extends BaseDO {

    private String openid;
    private String unionid;
    private String token;
    private String nickname;
    private String avatarUrl;
    private Integer gender;
    private String country;
    private String province;
    private String city;
    private String mobile;
    private String inviteCode;

}
