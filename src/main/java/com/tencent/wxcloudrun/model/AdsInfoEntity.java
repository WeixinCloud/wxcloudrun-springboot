package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ads_info")
public class AdsInfoEntity extends BaseDO {

    private String title;
    private String des;
    private String img;
    private String content;
    private String status;
}
