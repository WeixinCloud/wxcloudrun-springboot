package com.tencent.wxcloudrun.entity;

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
    private Integer fee;
    private String callType;
    private String resp;

}
