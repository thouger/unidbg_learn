package cn.missfresh.sherlock.tool;

import android.security.keystore.KeyProperties;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: AESUtils */
public final class a {
    public static String a(String str, String str2) {
        return b.a(a(str.getBytes("UTF-8"), str2.getBytes("UTF-8")));
    }

    public static String a(byte[] bArr, String str) {
        return b.a(a(bArr, str.getBytes("UTF-8")));
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
        instance.init(1, new SecretKeySpec(bArr2, KeyProperties.KEY_ALGORITHM_AES));
        return instance.doFinal(bArr);
    }
}
