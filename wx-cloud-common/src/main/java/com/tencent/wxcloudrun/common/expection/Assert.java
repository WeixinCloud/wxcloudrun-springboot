package com.tencent.wxcloudrun.common.expection;

/**
 * @author tangsh
 * @date 2022/11/02
 */

import java.util.Collection;

public class Assert {

    public static void warnIsTrue(boolean match, String error){
        isTrue(match, ErrorCode.PARAM_ERROR,error);
    }

    public static void warnNotEmpty(Object obj,String error) {
        notEmpty(obj, ErrorCode.PARAM_ERROR,error);
    }

    public static void errorIsTrue(boolean match, String error){
        isTrue(match, ErrorCode.SHOULD_NEVER_HAPPEN,error);
    }

    public static void errorNotEmpty(Object obj,String error) {
        notEmpty(obj, ErrorCode.SHOULD_NEVER_HAPPEN,error);
    }

    private static void isTrue(boolean match,int code, String error){
        if(!match){
            throw new BizException(code,error);
        }
    }

    private static void notEmpty(Object obj,int code,String error) {
        if(obj==null){
            throw new BizException(code,error);
        }
        if(obj instanceof Collection){
            if(((Collection) obj).size()==0){
                throw new BizException(code,error);
            }
        }
        if(obj.equals("") || obj.toString().trim().equals("")) {
            throw new BizException(code,error);
        }
    }
}

