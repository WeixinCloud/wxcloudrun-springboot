package com.tencent.wxcloudrun.utils;

import java.net.InetAddress;

/**
 * @author tangsh
 * @date 2022/10/30
 */
public class IPUtils {

    public static String getLocalIp() {
        InetAddress ia = null;
        String localip = null;
        try {
            ia = InetAddress.getLocalHost();
            localip = ia.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localip;
    }

}
