package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.wxpay.sdk.WXPayUtil;
import com.tencent.wxcloudrun.client.WxClient;
import com.tencent.wxcloudrun.model.dto.WxPrePayDTO;
import com.tencent.wxcloudrun.model.request.AdsPageParam;
import com.tencent.wxcloudrun.model.dto.PageDTO;
import com.tencent.wxcloudrun.model.request.WxPrePayParam;
import com.tencent.wxcloudrun.entity.AdsInfoEntity;
import com.tencent.wxcloudrun.repository.AdsInfoRepository;
import com.tencent.wxcloudrun.service.AdsInfoService;
import com.tencent.wxcloudrun.utils.PageUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author tangsh
 * @date 2022/10/27
 */

@Service
@Slf4j
public class AdsInfoServiceImpl implements AdsInfoService {

    @Autowired
    private WxClient wxClient;

    @Autowired
    private AdsInfoRepository adsInfoRepository;

    @Override
    public List<AdsInfoEntity> list() {
        return adsInfoRepository.list();
    }

    @Override
    public PageDTO<AdsInfoEntity> page(AdsPageParam param) {
        int pageNo = param.getPageNo();
        int pageSize = param.getPageSize();
        IPage<AdsInfoEntity> page = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<AdsInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdsInfoEntity::getStatus, "ON");
        IPage<AdsInfoEntity> record = adsInfoRepository.page(page, queryWrapper);
        return PageUtils.copy(record);
    }

    @Override
    public void prePay(WxPrePayParam param) {
        wxClient.prePay(param);
    }

    @SneakyThrows
    public static void main(String[] args) {
        String msg ="{\"nonce_str\":\"89f1e03f32174781b43b9472a34819ec\",\"out_trade_no\":\"2021WERUN16478406876373\",\"openid\":\"oXPeb4gGUWuARbGkIcvrb3PXTb30\",\"appid\":\"wx180962a99caf9ff5\",\"total_fee\":\"1\",\"sign\":\"F1416A07F0BE84ED3E22F3DA5BF00D5A\",\"trade_type\":\"JSAPI\",\"mch_id\":\"1633796573\",\"body\":\"测试wechat-pay\",\"notify_url\":\"https://springboot-mvyj-15312-5-1314693576.sh.run.tcloudbase.com/webhook/v1/pay\",\"spbill_create_ip\":\"10.28.16.126\"}";
//        WxPrePayDTO dto = JSON.parseObject(msg, WxPrePayDTO.class);
        Map map = JSONObject.parseObject(msg);
        String xml = WXPayUtil.mapToXml(map);
        System.out.println(xml);
    }
}
