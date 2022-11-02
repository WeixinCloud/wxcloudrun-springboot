package com.tencent.wxcloudrun.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.dao.entity.AdsOrderEntity;
import com.tencent.wxcloudrun.common.expection.BizException;
import com.tencent.wxcloudrun.common.expection.ErrorCode;
import com.tencent.wxcloudrun.dao.repository.AdsOrderRepository;
import com.tencent.wxcloudrun.web.service.WebhookService;
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
    private AdsOrderRepository adsOrderRepository;

    @Override
    public JSONObject respWxPayHook(JSONObject req) {
        log.info("微信下单-回调成功:{}", req.toJSONString());
        String outTradeNo = req.getString("outTradeNo");
        String openid = req.getString("openid");
        Integer totalFee = req.getInteger("totalFee");
        AdsOrderEntity orderEntity = adsOrderRepository.getOneByOrderNo(outTradeNo);
        if (orderEntity != null) {
            return respSuccess();
        }
        buildPayOrder(req, openid, outTradeNo, totalFee);
        return respSuccess();
    }

    @Override
    public JSONObject respWxRefundHook(JSONObject req) {
        log.info("微信退款-回调成功:{}", req.toJSONString());
        String outTradeNo = req.getString("outTradeNo");
        AdsOrderEntity orderEntity = adsOrderRepository.getOneByOrderNo(outTradeNo);
        if (orderEntity == null) {
            log.error("支付订单不存在,{}", outTradeNo);
            throw new BizException(ErrorCode.BIZ_BREAK, "支付订单不存在!");
        }
        outTradeNo = req.getString("outRefundNo");
        Integer totalFee = req.getInteger("totalFee");
        //新增退款单表
        buildRefundOrder(req, orderEntity.getOpenid(), outTradeNo, totalFee);
        return respSuccess();
    }

    private void buildPayOrder(JSONObject req, String openid, String outTradeNo, Integer totalFee) {
        AdsOrderEntity order = new AdsOrderEntity();
        order.setOpenid(openid);
        order.setOutTradeNo(outTradeNo);
        order.setAmount(totalFee);
        order.setOrderType("PAY");
        order.setBusinessType("ADS");
        order.setResp(req.toJSONString());
        adsOrderRepository.save(order);
    }

    private void buildRefundOrder(JSONObject req, String openid, String outTradeNo, Integer totalFee) {
        AdsOrderEntity order = new AdsOrderEntity();
        order.setOpenid(openid);
        order.setOutTradeNo(outTradeNo);
        order.setAmount(totalFee);
        order.setOrderType("REFUND");
        order.setBusinessType("ADS");
        order.setResp(req.toJSONString());
        adsOrderRepository.save(order);
    }


    private JSONObject respSuccess() {
        JSONObject resp = new JSONObject();
        resp.put("errcode", 0);
        resp.put("errmsg", "OK");
        return resp;
    }
}
