package com.sijla.g;

import android.text.TextUtils;

public class h {
    public static String a = "QLOG";
    public static final String b = System.getProperty("line.separator");

    public static void a(String str, Object obj, String str2) {
    }

    public static void a(String str, String str2) {
    }

    public static void b(String str, String str2) {
    }

    public static void c(String str, String str2) {
    }

    private static String a(StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        String format = String.format("%s.%s(Line:%d)", className.substring(className.lastIndexOf(".") + 1), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber()));
        if (TextUtils.isEmpty(a)) {
            return format;
        }
        return a + ":" + format;
    }

    public static void a(String str) {
        a(a(a()), str);
    }

    public static void b(String str) {
        c(a(a()), str);
    }

    private static StackTraceElement a() {
        return Thread.currentThread().getStackTrace()[4];
    }
}
