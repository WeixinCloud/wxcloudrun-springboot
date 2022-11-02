package com.tencent.wxcloudrun.web.config;

import com.tencent.wxcloudrun.common.expection.BizException;
import com.tencent.wxcloudrun.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tangsh
 * @date 2022/11/01
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(BizException.class)
    public Result bizException(BizException e) {
        return Result.Error(e.getCode(), e.getMessage());
    }
}
