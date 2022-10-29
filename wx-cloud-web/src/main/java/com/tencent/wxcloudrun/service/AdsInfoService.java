package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dto.AdsPageParam;
import com.tencent.wxcloudrun.dto.PageDTO;
import com.tencent.wxcloudrun.dto.WxPrePayParam;
import com.tencent.wxcloudrun.entity.AdsInfoEntity;

import javax.servlet.http.HttpServletRequest;
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
