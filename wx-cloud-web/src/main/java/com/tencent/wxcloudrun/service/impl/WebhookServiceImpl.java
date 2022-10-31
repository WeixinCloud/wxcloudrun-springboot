package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.entity.OrderEntity;
import com.tencent.wxcloudrun.repository.OrderRepository;
import com.tencent.wxcloudrun.service.WebhookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tangsh
 * @date 2022/10/31
 */
@Service
@Slf4j
public class WebhookServiceImpl implements WebhookService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public JSONObject respWxPayHook(JSONObject req) {
        log.info("微信下单-回调成功:{}", req.toJSONString());
        String outTradeNo = req.getString("outTradeNo");
        String openid = req.getString("openid");
        Integer totalFee = req.getInteger("totalFee");
        OrderEntity orderEntity = orderRepository.getOneByOrderNo(outTradeNo);
        if (orderEntity != null) {
            return respSuccess();
        }
        OrderEntity order = new OrderEntity();
        order.setOpenid(openid);
        order.setOutTradeNo(outTradeNo);
        order.setAmount(totalFee);
        order.setOrderType("PAY");
        order.setBusinessType("ADS");
        order.setResp(req.toJSONString());
        orderRepository.save(order);
        return respSuccess();
    }

    private JSONObject respSuccess() {
        JSONObject resp = new JSONObject();
        resp.put("errcode", 0);
        resp.put("errmsg", "OK");
        return resp;
    }
}
