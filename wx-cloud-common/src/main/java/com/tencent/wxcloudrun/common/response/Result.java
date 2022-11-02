package com.tencent.wxcloudrun.common.response;

import java.io.Serializable;

/**
 * @author tangsh
 * @date 2022/10/30
 */
public class Result<T> implements Serializable {

    private T data;

    private boolean success;

    private Integer code;

    private String message;

    public Result() {
    }

    public static <T> Result<T> Success() {
        Result<T> result = new Result<T>();
        result.data = null;
        result.success = true;
        result.code = 0;
        return result;
    }

    public static <T> Result<T> Success(T data) {
        Result<T> result = new Result<T>();
        result.data = data;
        result.success = true;
        result.code = 0;
        return result;
    }

    public static <T> Result<T> Success(String message, T data) {
        Result<T> result = new Result<T>();
        result.data = data;
        result.success = true;
        result.code = 0;
        result.message = message;
        return result;
    }

    public static <T> Result<T> Error(Integer code, String message) {
        Result<T> result = new Result<T>();
        result.success = false;
        result.code = code;
        result.message = message;
        return result;
    }

    public T getData() {
        return this.data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Result<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public Integer getCode() {
        return this.code;
    }

    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("success=");
        sb.append(this.success);
        sb.append(",");
        sb.append("code=");
        sb.append(this.code);
        sb.append(",");
        sb.append("message=");
        sb.append(this.message);
        sb.append(",");
        sb.append("data=");
        sb.append(this.data);
        sb.append("}");
        return sb.toString();
    }
}
