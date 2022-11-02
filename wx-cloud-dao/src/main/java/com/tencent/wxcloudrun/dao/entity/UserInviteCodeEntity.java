package com.tencent.wxcloudrun.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author tangsh
 * @date 2022/11/01
 */
@Data
@TableName("user_invite_code")
public class UserInviteCodeEntity extends BaseDO {

    private String openid;
    private String inviteCode;

}
