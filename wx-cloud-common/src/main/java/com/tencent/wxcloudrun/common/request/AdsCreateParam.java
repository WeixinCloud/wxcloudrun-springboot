package com.tencent.wxcloudrun.common.request;

import com.tencent.wxcloudrun.common.expection.Assert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tangsh
 * @date 2022/10/27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdsCreateParam {

    private String title;
    private String dec;
    private String img;
    private Integer fee;
    private String content;
    private String category;

    public void checkParam() {
        Assert.warnNotEmpty(getTitle(), "广告标题不能为空");
        Assert.warnNotEmpty(getFee(), "广告金额不能为空");
    }
}
