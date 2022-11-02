package com.tencent.wxcloudrun.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tangsh
 * @date 2022/10/30
 */

@Data
@Slf4j
public class WxUtils {

    public static JSONObject getData(String response) {
        JSONObject respJson = JSON.parseObject(response);
        if (respJson == null || respJson.getInteger("errcode") != 0) {
            log.warn("请求微信-接口异常");
            return null;
        }
        JSONObject wxResult = respJson.getJSONObject("respdata");
        if (wxResult == null) {
            log.warn("请求微信-接口异常");
            return null;
        }
        if (!"SUCCESS".equals(wxResult.getString("return_code"))) {
            log.warn("请求微信-接口异常 ,{}", wxResult.toJSONString());
            return null;
        }
        return wxResult;
    }

    public static JSONObject getPhoneInfo(String response) {
        JSONObject respJson = JSON.parseObject(response);
        if (respJson == null || respJson.getInteger("errcode") != 0) {
            log.warn("请求微信-接口异常");
            return null;
        }
        JSONObject wxResult = respJson.getJSONObject("phone_info");
        if (wxResult == null) {
            log.warn("请求微信-接口异常");
            return null;
        }
        return wxResult;
    }
}
