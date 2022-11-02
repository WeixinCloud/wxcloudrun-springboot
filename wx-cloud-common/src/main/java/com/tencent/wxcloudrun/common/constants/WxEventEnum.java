package com.tencent.wxcloudrun.common.constants;

import lombok.Getter;

/**
 * @author tangsh
 * @date 2022/10/30
 */
@Getter
public enum WxEventEnum {

    /**
     * 微信请求事件
     */
    UNIFIED_ORDER,

    QUERY_ORDER,

    CLOSE_ORDER,

    REFUND_ORDER
}
