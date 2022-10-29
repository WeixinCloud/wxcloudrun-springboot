package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.dto.PageDTO;
import com.tencent.wxcloudrun.model.AdsInfoEntity;
import com.tencent.wxcloudrun.dto.AdsPageParam;

import java.util.List;

/**
 * @author tangsh
 * @date 2022/10/27
 */

public interface AdsInfoService {

    List<AdsInfoEntity> list();

    PageDTO<AdsInfoEntity> page(AdsPageParam param);
}
