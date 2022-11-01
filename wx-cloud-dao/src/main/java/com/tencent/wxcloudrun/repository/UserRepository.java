package com.tencent.wxcloudrun.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tencent.wxcloudrun.entity.AdsOrderEntity;
import com.tencent.wxcloudrun.entity.UserEntity;
import com.tencent.wxcloudrun.mapper.UserMapper;
import org.springframework.stereotype.Repository;

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
}
