package com.sobot.chat.utils;

import android.security.keystore.KeyProperties;
import java.security.MessageDigest;

/* compiled from: MD5Util */
public class n {
    public static String a(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            for (byte b : MessageDigest.getInstance(KeyProperties.DIGEST_MD5).digest(str.getBytes())) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() < 2) {
                    sb.append("0");
                }
                sb.append(hexString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
