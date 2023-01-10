package com.umeng.message.proguard;

import java.io.UnsupportedEncodingException;

/* compiled from: AESStringUtils */
public class b {
    public static byte[] a(String str) {
        return a(str, "ISO-8859-1");
    }

    public static byte[] b(String str) {
        return a(str, f.b);
    }

    public static byte[] c(String str) {
        return a(str, f.c);
    }

    public static byte[] d(String str) {
        return a(str, f.d);
    }

    public static byte[] e(String str) {
        return a(str, f.e);
    }

    public static byte[] f(String str) {
        return a(str, "UTF-8");
    }

    public static byte[] a(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes(str2);
        } catch (UnsupportedEncodingException e) {
            throw a(str2, e);
        }
    }

    private static IllegalStateException a(String str, UnsupportedEncodingException unsupportedEncodingException) {
        return new IllegalStateException(str + ": " + unsupportedEncodingException);
    }

    public static String a(byte[] bArr, String str) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, str);
        } catch (UnsupportedEncodingException e) {
            throw a(str, e);
        }
    }

    public static String a(byte[] bArr) {
        return a(bArr, "ISO-8859-1");
    }

    public static String b(byte[] bArr) {
        return a(bArr, f.b);
    }

    public static String c(byte[] bArr) {
        return a(bArr, f.c);
    }

    public static String d(byte[] bArr) {
        return a(bArr, f.d);
    }

    public static String e(byte[] bArr) {
        return a(bArr, f.e);
    }

    public static String f(byte[] bArr) {
        return a(bArr, "UTF-8");
    }
}
