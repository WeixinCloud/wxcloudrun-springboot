package com.tencent.wxcloudrun.common.constants;

import com.tencent.wxcloudrun.common.expection.BizException;
import lombok.Getter;

/**
 * @author tangsh
 * @date 2022/10/31
 */
@Getter
public enum CategoryEnum {

    /**
     * 广告类目枚举
     */
    PARTY("PARTY", "日常聚会"),
    SOCIALLY("SOCIALLY", "公益活动"),
    GAMES("GAMES", "电竞比赛"),
    TRAVEL("TRAVEL", "组团旅游"),
    CHAT("CHAT", "交友"),
    VIP("VIP", "会员专享"),
    ;

    private String code;
    private String desc;

    CategoryEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CategoryEnum getByCode(String code) {
        for (CategoryEnum eventEnum : values()) {
            if (eventEnum.getCode().equals(code)) {
                return eventEnum;
            }
        }
        throw new BizException(500501, "广告类目枚举异常");
    }
}
