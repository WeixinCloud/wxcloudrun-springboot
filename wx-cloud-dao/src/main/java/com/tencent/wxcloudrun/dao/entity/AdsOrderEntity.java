package com.tencent.wxcloudrun.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author tangsh
 * @date 2022/10/31
 */
@Data
@TableName("ads_order")
public class AdsOrderEntity extends BaseDO {

    private String openid;
    private String outTradeNo;
    private Integer amount;
    private String currency;
    private String businessType;
    private String orderType;
    private String resp;

}
