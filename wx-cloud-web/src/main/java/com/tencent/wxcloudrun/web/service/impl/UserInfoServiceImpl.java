package com.tencent.wxcloudrun.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.web.client.WxUserClient;
import com.tencent.wxcloudrun.common.dto.RawDataDO;
import com.tencent.wxcloudrun.dao.entity.AdsOrderEntity;
import com.tencent.wxcloudrun.dao.entity.UserEntity;
import com.tencent.wxcloudrun.dao.entity.UserInviteCodeEntity;
import com.tencent.wxcloudrun.common.expection.BizException;
import com.tencent.wxcloudrun.common.expection.ErrorCode;
import com.tencent.wxcloudrun.dao.repository.AdsOrderRepository;
import com.tencent.wxcloudrun.dao.repository.UserInviteCodeRepository;
import com.tencent.wxcloudrun.dao.repository.UserRepository;
import com.tencent.wxcloudrun.common.request.UserCodeParam;
import com.tencent.wxcloudrun.common.request.UserInfoParam;
import com.tencent.wxcloudrun.common.request.UserLoginParam;
import com.tencent.wxcloudrun.common.response.UserInfoResult;
import com.tencent.wxcloudrun.web.service.UserInfoService;
import com.tencent.wxcloudrun.web.utils.EncryptUtils;
import com.tencent.wxcloudrun.web.utils.NonceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private UserInviteCodeRepository inviteCodeRepository;
    @Autowired
    private AdsOrderRepository adsOrderRepository;


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
            insertOrUpdateDO.setToken(NonceUtil.createNonce(32));
            insertOrUpdateDO.setInviteCode(param.getInviteCode());
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
    public UserInfoResult getUserInfo(UserInfoParam param) {
        UserEntity userEntity = userRepository.getOneByToken(param.getToken());
        if (userEntity == null) {
            throw new BizException(ErrorCode.BIZ_BREAK, "用户信息不存在!");
        }
        return buildUserInfoResult(userEntity);
    }

    @Override
    public String createInviteCode(String openid) {
        UserInviteCodeEntity inviteCodeEntity = inviteCodeRepository.getOneByOpenId(openid);
        String inviteCode;
        if (inviteCodeEntity == null) {
            inviteCode = NonceUtil.createNonce(6);
            UserInviteCodeEntity entity = new UserInviteCodeEntity();
            entity.setInviteCode(inviteCode);
            entity.setOpenid(openid);
            inviteCodeRepository.save(entity);
        } else {
            inviteCode = inviteCodeEntity.getInviteCode();
        }
        return inviteCode;
    }


    @Override
    public JSONObject getPhoneNum(String openid, UserCodeParam param) {
        UserEntity userEntity = userRepository.getOneByOpenId(openid);
        if (userEntity == null) {
            throw new BizException(ErrorCode.BIZ_BREAK, "用户信息不存在!");
        }
        String code = param.getCode();
        JSONObject respJson = wxUserClient.getPhoneNum(code);
        String phoneNum = respJson.getString("phoneNumber");
        //获取手机号，更新用户信息.
        userEntity.setMobile(phoneNum);
        userRepository.updateByOpenId(userEntity);
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

    private UserInfoResult buildUserInfoResult(UserEntity userEntity) {
        String openid = userEntity.getOpenid();
        UserInfoResult result = new UserInfoResult();
        BeanUtils.copyProperties(userEntity, result);
        UserInviteCodeEntity inviteCodeEntity = inviteCodeRepository.getOneByOpenId(openid);
        //若用户存在邀请码
        if (inviteCodeEntity != null) {
            List<UserEntity> inviteUserList = userRepository.queryByInviteCode(inviteCodeEntity.getInviteCode());
            if (!CollectionUtils.isEmpty(inviteUserList)) {
                List<String> inviteUserOpenIdList = inviteUserList.stream().map(UserEntity::getOpenid).collect(Collectors.toList());
                List<AdsOrderEntity> payOrderList = adsOrderRepository.queryByOpenIdList(inviteUserOpenIdList);
                result.setInviteNum(inviteUserList.size());
                result.setOrderPayNum(payOrderList.size());
            }
        }
        return result;
    }


}
