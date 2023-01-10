package com.hmt.analytics.util;

import android.text.TextUtils;
import com.hmt.analytics.android.a;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* compiled from: DESUtil */
public class b {
    private static final String a = b.class.getSimpleName();

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(1, generateSecret, new IvParameterSpec(a()));
            return a(instance.doFinal(str2.getBytes()));
        } catch (Exception e) {
            String str3 = a;
            a.a(str3, "Collected:" + e.getMessage());
            return str2;
        } catch (UnsatisfiedLinkError e2) {
            String str4 = a;
            a.a(str4, "Collected:" + e2.getMessage());
            return str2;
        }
    }

    public static String b(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(str.getBytes()));
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(2, generateSecret, new IvParameterSpec(a()));
            return new String(instance.doFinal(b(str2.getBytes())));
        } catch (Exception e) {
            String str3 = a;
            a.a(str3, "Collected:" + e.getMessage());
            return str2;
        } catch (UnsatisfiedLinkError e2) {
            String str4 = a;
            a.a(str4, "Collected:" + e2.getMessage());
            return str2;
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (bArr != null && i < bArr.length) {
            String hexString = Integer.toHexString(bArr[i] & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
            i++;
        }
        return sb.toString().toUpperCase(Locale.CHINA);
    }

    private static byte[] b(byte[] bArr) {
        if (bArr.length % 2 == 0) {
            byte[] bArr2 = new byte[(bArr.length / 2)];
            for (int i = 0; i < bArr.length; i += 2) {
                bArr2[i / 2] = (byte) Integer.parseInt(new String(bArr, i, 2), 16);
            }
            return bArr2;
        }
        throw new IllegalArgumentException();
    }

    private static byte[] a() {
        return ("12345678").getBytes();
    }
}
