package com.tencent.wxcloudrun.web.utils;

import java.security.SecureRandom;

/**
 * @author tangsh
 * @date 2022/10/30
 */
public class NonceUtil {
    private static final char[] SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final SecureRandom random = new SecureRandom();

    private NonceUtil() {
    }

    public static String createNonce(int length) {
        char[] buf = new char[length];

        for(int i = 0; i < length; ++i) {
            buf[i] = SYMBOLS[random.nextInt(SYMBOLS.length)];
        }

        return new String(buf);
    }
}
