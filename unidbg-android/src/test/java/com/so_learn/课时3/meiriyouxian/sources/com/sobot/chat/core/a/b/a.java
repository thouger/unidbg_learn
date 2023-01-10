package com.sobot.chat.core.a.b;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/* compiled from: CharsetUtil */
public class a {
    public static byte[] a(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes(str2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] b(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            byte[] a = a(str, str2);
            byte[] bArr = new byte[(a.length + 2)];
            bArr[0] = 1;
            bArr[1] = 1;
            System.arraycopy(a, 0, bArr, 2, a.length);
            return bArr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] c(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            byte[] a = a(str, str2);
            byte[] bArr = new byte[(a.length + 2)];
            bArr[0] = 1;
            bArr[1] = 0;
            System.arraycopy(a, 0, bArr, 2, a.length);
            return bArr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] d(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            byte[] a = a(str, str2);
            byte[] bArr = new byte[(a.length + 2)];
            bArr[0] = 1;
            bArr[1] = 2;
            System.arraycopy(a, 0, bArr, 2, a.length);
            return bArr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(byte[] bArr, String str) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(Arrays.copyOfRange(bArr, 2, bArr.length), str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
