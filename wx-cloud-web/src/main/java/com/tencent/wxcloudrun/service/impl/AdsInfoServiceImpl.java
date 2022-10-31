package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.client.WxPayClient;
import com.tencent.wxcloudrun.constants.WxEvent;
import com.tencent.wxcloudrun.entity.AdsInfoEntity;
import com.tencent.wxcloudrun.entity.AdsOrderLogEntity;
import com.tencent.wxcloudrun.model.dto.Container;
import com.tencent.wxcloudrun.model.dto.PageDTO;
import com.tencent.wxcloudrun.model.request.AdsPageParam;
import com.tencent.wxcloudrun.model.request.WxPayCloseParam;
import com.tencent.wxcloudrun.model.request.WxPayQueryParam;
import com.tencent.wxcloudrun.model.request.WxPrePayParam;
import com.tencent.wxcloudrun.repository.AdsInfoRepository;
import com.tencent.wxcloudrun.repository.AdsOrderLogRepository;
import com.tencent.wxcloudrun.service.AdsInfoService;
import com.tencent.wxcloudrun.utils.NonceUtil;
import com.tencent.wxcloudrun.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tangsh
 * @date 2022/10/27
 */

@Service
@Slf4j
public class AdsInfoServiceImpl implements AdsInfoService {

    @Value("${wx.env.id:prod-9ge6u8sn7684a421}")
    public String WX_ENV_ID;
    @Value("${wx.api.id:wx180962a99caf9ff5}")
    public String WX_APP_ID;
    @Value("${wx.mch.id:1633796573}")
    public String WX_MERCHANT_ID;

    @Autowired
    private WxPayClient wxClient;

    @Autowired
    private AdsInfoRepository adsInfoRepository;
    @Autowired
    private AdsOrderLogRepository adsOrderLogRepository;

    @Override
    public List<AdsInfoEntity> list() {
        return adsInfoRepository.list();
    }

    @Override
    public PageDTO<AdsInfoEntity> page(AdsPageParam param) {
        IPage<AdsInfoEntity> page = new Page<>(param.getPageNo(), param.getPageSize());
        LambdaQueryWrapper<AdsInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdsInfoEntity::getStatus, "ON");
        IPage<AdsInfoEntity> record = adsInfoRepository.page(page, queryWrapper);
        return PageUtils.copy(record);
    }

    @Override
    public JSONObject prePay(String openid, String ip, WxPrePayParam param) {
        JSONObject reqJson = (JSONObject) JSONObject.toJSON(param);
        reqJson.put("openid", openid);
        reqJson.put("sub_mch_id", WX_MERCHANT_ID);
        reqJson.put("env_id", WX_ENV_ID);
        String outTradeNo = NonceUtil.createNonce(32);
        reqJson.put("out_trade_no", outTradeNo);
        reqJson.put("spbill_create_ip", ip);
        reqJson.put("callback_type", 2);
        Container container = new Container();
        container.setPath("/webhook/v1/pay");
        container.setService("pre-pay");
        reqJson.put("container", container);
        WxEvent event = WxEvent.UNIFIED_ORDER;
        JSONObject respJson = wxClient.prePay(reqJson);
        saveOrderLog(reqJson, respJson, event);
        return respJson;
    }

    @Override
    public JSONObject payQuery(String openid, WxPayQueryParam param) {
        JSONObject reqJson = (JSONObject) JSONObject.toJSON(param);
        reqJson.put("sub_mch_id", WX_MERCHANT_ID);
        WxEvent event = WxEvent.QUERY_ORDER;
        JSONObject respJson = wxClient.payQuery(reqJson);
        saveOrderLog(reqJson, respJson, event);
        return respJson;
    }

    @Override
    public JSONObject payClose(String openid, WxPayCloseParam param) {
        JSONObject reqJson = (JSONObject) JSONObject.toJSON(param);
        reqJson.put("sub_mch_id", WX_MERCHANT_ID);
        WxEvent event = WxEvent.CLOSE_ORDER;
        JSONObject respJson = wxClient.payClose(reqJson);
        saveOrderLog(reqJson, respJson, event);
        return respJson;
    }


    private void saveOrderLog(JSONObject reqJson, JSONObject respJson, WxEvent event) {
        AdsOrderLogEntity entity = new AdsOrderLogEntity();
        String openId = reqJson.getString("openid");
        String outTradeNo = reqJson.getString("out_trade_no");
        entity.setOpenId(openId);
        entity.setEvent(event.name());
        entity.setReq(reqJson.toJSONString());
        entity.setResp(respJson.toJSONString());
        entity.setOutTradeNo(outTradeNo);
        adsOrderLogRepository.save(entity);
    }
}
