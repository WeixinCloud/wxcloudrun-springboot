package com.tencent.wxcloudrun.utils.baidu;

import lombok.val;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 人脸检测与属性分析
 */
@Component
public class FaceDetect {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String faceDetect(String src) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", src);
            map.put("face_field", "emotion");
            map.put("image_type", "URL");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            AuthService authService = new AuthService();
            String accessToken = authService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            Map<String,Object> resultMap = new HashMap();
            resultMap = GsonUtils.fromJson(result,resultMap.getClass());

            Map<String,Object> resultMap2 = (Map<String,Object>) resultMap.get("result");

            ArrayList<Map<String,Object>> face_list = (ArrayList<Map<String,Object>>) resultMap2.get("face_list");

            Map<String,Object> emotion_map = (Map<String,Object>) face_list.get(0).get("emotion");

            String emotion = (String) emotion_map.get("type");


            return emotion;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
