package com.tencent.wxcloudrun.common.expection;

import lombok.Data;

@Data
public class BizException extends RuntimeException {

    private Integer code;

    private String message;

    private Throwable source;

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BizException(Integer code, String message, Throwable e) {
        super(message);
        this.code = code;
        this.message = message;
        this.source = e;
    }

    public BizException(String message, Throwable e) {
        super(message);
        this.code = 500;
        this.message = message;
        this.source = e;
    }
}