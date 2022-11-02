package com.tencent.wxcloudrun.dao.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tencent.wxcloudrun.dao.entity.UserEntity;
import com.tencent.wxcloudrun.dao.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tangsh
 * @date 2022/11/01
 */
@Repository
public class UserRepository extends BaseRepository<UserMapper, UserEntity> {

    public UserEntity getOneByOpenId(String openid) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getOpenid, openid);
        return this.getOne(wrapper);
    }

    public UserEntity getOneByToken(String token) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getToken, token);
        return this.getOne(wrapper);
    }

    public void updateByOpenId(UserEntity update) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getOpenid, update);
        this.update(update, wrapper);
    }

    public List<UserEntity> queryByInviteCode(String inviteCode) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getInviteCode, inviteCode);
        return this.list(wrapper);
    }
}
