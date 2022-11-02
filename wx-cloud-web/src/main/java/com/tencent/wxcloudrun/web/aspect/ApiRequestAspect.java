package com.tencent.wxcloudrun.web.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author tangsh
 * @date 2022/10/27
 */
@Slf4j
@Aspect
@Component
public class ApiRequestAspect {

    @Pointcut("@annotation(com.tencent.wxcloudrun.common.annotation.ApiRequest)")
    public void point() {
    }

    @Around("point()")
    public Object process(ProceedingJoinPoint pp) throws Throwable {
        long beginTime = System.currentTimeMillis();
        MDC.put("request", UUID.randomUUID().toString());
        Object result;
        log.info("API服务申请 : {}.{}", pp.getSignature().getDeclaringTypeName(), pp.getSignature().getName());
        for (Object request : pp.getArgs()) {
            log.info("请求参数 : {}", JSON.toJSONString(request));
        }
        result = pp.proceed();

        long endTime = System.currentTimeMillis();
        log.info("处理时间 : {}ms", endTime - beginTime);
        log.info("响应结果 : {}", JSON.toJSONString(result));
        return result;
    }

}
