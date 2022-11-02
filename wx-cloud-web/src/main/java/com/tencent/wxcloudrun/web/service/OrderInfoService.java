package com.tencent.wxcloudrun.web.service;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.common.dto.PageDTO;
import com.tencent.wxcloudrun.dao.entity.AdsOrderEntity;
import com.tencent.wxcloudrun.common.request.OrderDetailParam;
import com.tencent.wxcloudrun.common.request.OrderPageParam;

/**
 * @author tangsh
 * @date 2022/10/31
 */
public interface OrderInfoService {

    PageDTO<AdsOrderEntity> page(String openid, OrderPageParam param);

    JSONObject detail(OrderDetailParam param);
}
