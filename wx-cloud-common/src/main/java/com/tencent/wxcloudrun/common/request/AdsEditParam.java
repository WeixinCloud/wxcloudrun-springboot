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
public class AdsEditParam {

    private Integer id;
    private String title;
    private String dec;
    private String img;
    private Integer fee;
    private String content;
    private String category;

    public void checkParam() {
        Assert.warnNotEmpty(getId(), "广告id不能为空");
    }
}
