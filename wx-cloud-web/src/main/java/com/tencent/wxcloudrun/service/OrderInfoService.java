package com.tencent.wxcloudrun.service;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.entity.AdsOrderEntity;
import com.tencent.wxcloudrun.model.dto.PageDTO;
import com.tencent.wxcloudrun.model.request.OrderDetailParam;
import com.tencent.wxcloudrun.model.request.OrderPageParam;

/**
 * @author tangsh
 * @date 2022/10/31
 */
public interface OrderInfoService {

    PageDTO<AdsOrderEntity> page(String openid, OrderPageParam param);

    JSONObject detail(OrderDetailParam param);
}
