package com.tencent.wxcloudrun.utils;

import java.net.InetAddress;

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
