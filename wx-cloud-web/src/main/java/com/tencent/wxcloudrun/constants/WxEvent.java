package com.tencent.wxcloudrun.constants;

import lombok.Getter;

/**
 * @author tangsh
 * @date 2022/10/30
 */
@Getter
public enum WxEvent {

    /**
     * 微信请求事件
     */
    UNIFIED_ORDER,

    QUERY_ORDER,

    CLOSE_ORDER,

    REFUND_ORDER
}
