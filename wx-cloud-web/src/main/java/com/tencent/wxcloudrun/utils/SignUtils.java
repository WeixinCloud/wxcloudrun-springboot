package com.tencent.wxcloudrun.utils;

import com.github.wxpay.sdk.WXPayConstants;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static com.github.wxpay.sdk.WXPayUtil.HMACSHA256;
import static com.github.wxpay.sdk.WXPayUtil.MD5;

public class SignUtils {

    public static String getSign(final Map<String, String> data, String key) throws Exception {
        return generateSignature(data, key, WXPayConstants.SignType.MD5);  //MD5是常量 不想写常量可以直接写成"MD5"
    }

    public static String generateSignature(final Map<String, String> data, String key, WXPayConstants.SignType signType) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(WXPayConstants.FIELD_SIGN)) {   // FIELD_SIGN = sign
                continue;
            }
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
            {
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
            }
        }
        sb.append("key=").append(key);
        if (WXPayConstants.SignType.MD5.equals(signType)) {
            return MD5(sb.toString()).toUpperCase();
        } else if (WXPayConstants.SignType.HMACSHA256.equals(signType)) {  //HMACSHA256常量 可以直接写成 "HMACSHA256"
            return HMACSHA256(sb.toString(), key);
        } else {
            throw new Exception(String.format("Invalid sign_type: %s", signType));
        }
    }
}
