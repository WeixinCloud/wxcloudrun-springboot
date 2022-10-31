package com.tencent.wxcloudrun.service;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.entity.AdsInfoEntity;
import com.tencent.wxcloudrun.model.dto.PageDTO;
import com.tencent.wxcloudrun.model.request.*;

import java.util.List;

/**
 * @author tangsh
 * @date 2022/10/27
 */

public interface AdsInfoService {

    List<AdsInfoEntity> list();

    PageDTO<AdsInfoEntity> page(AdsPageParam param);

    AdsInfoEntity detail(AdsDetailParam param);

    JSONObject prePay(String openid, String ip, WxPrePayParam param);

    JSONObject payQuery(String openid, WxPayQueryParam param);

    JSONObject payClose(String openid, WxPayCloseParam param);
}
