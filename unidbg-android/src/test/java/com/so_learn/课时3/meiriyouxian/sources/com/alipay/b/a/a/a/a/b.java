package com.alipay.b.a.a.a.a;

import android.security.keystore.KeyProperties;
import com.alipay.b.a.a.a.a;
import java.security.MessageDigest;

public final class b {
    public static String a(String str) {
        try {
            if (a.a(str)) {
                return null;
            }
            MessageDigest instance = MessageDigest.getInstance(KeyProperties.DIGEST_SHA1);
            instance.update(str.getBytes("UTF-8"));
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(String.format("%02x", Byte.valueOf(digest[i])));
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }
}
