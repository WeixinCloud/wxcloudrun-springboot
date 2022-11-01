package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.client.WxUserClient;
import com.tencent.wxcloudrun.entity.UserEntity;
import com.tencent.wxcloudrun.model.dto.RawDataDO;
import com.tencent.wxcloudrun.model.request.UserCodeParam;
import com.tencent.wxcloudrun.model.request.UserInfoParam;
import com.tencent.wxcloudrun.model.request.UserLoginParam;
import com.tencent.wxcloudrun.repository.UserRepository;
import com.tencent.wxcloudrun.service.UserInfoService;
import com.tencent.wxcloudrun.utils.EncryptUtils;
import com.tencent.wxcloudrun.utils.NonceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tangsh
 * @date 2022/10/31
 */

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private WxUserClient wxUserClient;
    @Autowired
    private UserRepository userRepository;

    @Override
    public JSONObject login(UserLoginParam param) {
        String code = param.getCode();
        JSONObject respJson = wxUserClient.login(code);
        String sessionKey = respJson.get("session_key").toString();
        String openid = respJson.get("openid").toString();
        UserEntity insertOrUpdateDO = buildUserEntity(param, sessionKey, openid);
        JSONObject userInfo = new JSONObject();
        UserEntity user = userRepository.getOneByOpenId(openid);
        if (user == null) {
            insertOrUpdateDO.setToken(getToken());
            userRepository.save(insertOrUpdateDO);
            userInfo.put("token", insertOrUpdateDO.getToken());
        } else {
            userInfo.put("token", user.getToken());
            // 已存在，做已存在的处理，如更新用户的头像，昵称等，根据openID更新，这里代码自己写
            userRepository.updateByOpenId(insertOrUpdateDO);
        }
        return userInfo;
    }


    @Override
    public UserEntity getUserInfo(UserInfoParam param) {
        return userRepository.getOneByToken(param.getToken());
    }


    @Override
    public JSONObject getPhoneNum(UserCodeParam param) {
        String code = param.getCode();
        JSONObject respJson = wxUserClient.getPhoneNum(code);

        //TODO 查询用户信息
        log.info("{}", respJson.toJSONString());
        return respJson;
    }

    private UserEntity buildUserEntity(UserLoginParam param, String sessionKey, String openid) {
        UserEntity userEntity = new UserEntity();
        userEntity.setOpenid(openid);
        if (param.getRawData() != null) {
            RawDataDO rawDataDO = JSON.parseObject(param.getRawData(), RawDataDO.class);
            userEntity.setNickname(rawDataDO.getNickName());
            userEntity.setAvatarUrl(rawDataDO.getAvatarUrl());
            userEntity.setGender(rawDataDO.getGender());
            userEntity.setCity(rawDataDO.getCity());
            userEntity.setCountry(rawDataDO.getCountry());
            userEntity.setProvince(rawDataDO.getProvince());
        }
        // 解密加密信息，获取unionID
        if (param.getEncryptedData() != null) {
            JSONObject encryptedData = EncryptUtils.getEncryptedData(param.getEncryptedData(), sessionKey, param.getIv());
            if (encryptedData != null) {
                String unionId = encryptedData.getString("unionId");
                userEntity.setUnionid(unionId);
            }
        }
        return userEntity;
    }

    private String getToken() {
        return NonceUtil.createNonce(32);
    }

}
