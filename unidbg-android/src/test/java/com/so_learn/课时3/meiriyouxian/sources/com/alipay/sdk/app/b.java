package com.alipay.sdk.app;

public class b {
    private static boolean a;
    private static String b;

    public static void a(String str) {
        b = str;
    }

    public static String a() {
        return b;
    }

    public static boolean b() {
        return a;
    }

    public static void a(boolean z) {
        a = z;
    }

    public static String c() {
        c b2 = c.b(c.CANCELED.a());
        return a(b2.a(), b2.b(), "");
    }

    public static String d() {
        c b2 = c.b(c.DOUBLE_REQUEST.a());
        return a(b2.a(), b2.b(), "");
    }

    public static String e() {
        c b2 = c.b(c.PARAMS_ERROR.a());
        return a(b2.a(), b2.b(), "");
    }

    public static String a(int i, String str, String str2) {
        return "resultStatus={" + i + "};memo={" + str + "};result={" + str2 + "}";
    }
}
