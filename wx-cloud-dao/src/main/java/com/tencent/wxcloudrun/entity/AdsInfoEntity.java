package com.tencent.wxcloudrun.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author tangsh
 * @date 2022/10/30
 */
@Data
@TableName("ads_info")
public class AdsInfoEntity extends BaseDO {

    private String title;
    private String des;
    private String img;
    private Integer fee;
    private String content;
    private String status;
}
