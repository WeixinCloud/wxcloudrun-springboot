package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.request.AdsPageParam;
import com.tencent.wxcloudrun.model.dto.PageDTO;
import com.tencent.wxcloudrun.model.request.WxPrePayParam;
import com.tencent.wxcloudrun.entity.AdsInfoEntity;

import java.util.List;

/**
 * @author tangsh
 * @date 2022/10/27
 */

public interface AdsInfoService {

    List<AdsInfoEntity> list();

    PageDTO<AdsInfoEntity> page(AdsPageParam param);

    void prePay(WxPrePayParam request);
}
