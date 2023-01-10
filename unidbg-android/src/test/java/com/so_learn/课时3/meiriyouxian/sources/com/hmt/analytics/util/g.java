package com.hmt.analytics.util;

import android.security.keystore.KeyProperties;
import com.hmt.analytics.android.a;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: MD5Utility */
public class g {
    private static final String a = g.class.getSimpleName();

    public static String a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            a.a(a, "Collected:" + e.getMessage());
            return "";
        }
    }
}
