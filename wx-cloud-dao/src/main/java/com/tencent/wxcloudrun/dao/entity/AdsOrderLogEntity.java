package com.tencent.wxcloudrun.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author tangsh
 * @date 2022/10/30
 */
@Data
@TableName("ads_order_log")
public class AdsOrderLogEntity extends BaseDO {

    private String openid;
    private String outTradeNo;
    private String event;
    private String req;
    private String resp;
}
