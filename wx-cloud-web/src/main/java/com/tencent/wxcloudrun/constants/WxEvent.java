package com.tencent.wxcloudrun.constants;

import lombok.Getter;

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
