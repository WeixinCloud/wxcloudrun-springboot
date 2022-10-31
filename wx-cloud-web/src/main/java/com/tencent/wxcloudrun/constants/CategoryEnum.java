package com.tencent.wxcloudrun.constants;

import com.tencent.wxcloudrun.expection.BizException;
import lombok.Getter;

/**
 * @author tangsh
 * @date 2022/10/31
 */
@Getter
public enum CategoryEnum {

    /**
     * 交易方状态枚举
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
        throw new BizException(500, "部门枚举异常");
    }
}
