package com.sina.weibo.sdk.a;

import android.security.keystore.KeyProperties;
import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: AesEncrypt */
public class a {
    public static String a(String str) {
        try {
            byte[] c = c(str);
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(2, b("Stark"));
            try {
                return new String(instance.doFinal(c), FileOpt.ENCODE_STR);
            } catch (Exception e) {
                d.c("Decrypt", e.getMessage());
                return null;
            }
        } catch (Exception e2) {
            d.c("Decrypt", e2.getMessage());
            return null;
        }
    }

    protected static Key b(String str) {
        try {
            String substring = e.a(str).substring(2, 18);
            if (substring == null) {
                d.e("Decrypt", "Key\u4e3a\u7a7anull");
                return null;
            } else if (substring.length() == 16) {
                return new SecretKeySpec(substring.getBytes(FileOpt.ENCODE_STR), KeyProperties.KEY_ALGORITHM_AES);
            } else {
                d.e("Decrypt", "Key\u957f\u5ea6\u4e0d\u662f16\u4f4d");
                return null;
            }
        } catch (Exception e) {
            d.c("generateKey", e.getMessage());
            return null;
        }
    }

    private static byte[] c(String str) {
        return c.a(str.getBytes());
    }
}
