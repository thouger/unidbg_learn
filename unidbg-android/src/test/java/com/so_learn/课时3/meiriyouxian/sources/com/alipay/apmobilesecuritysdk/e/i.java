package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import com.alipay.b.a.a.a.a;
import java.util.HashMap;
import java.util.Map;

public final class i {
    private static String a = "";
    private static String b = "";
    private static String c = "";
    private static String d = "";
    private static String e = "";
    private static Map<String, String> f = new HashMap();

    public static synchronized String a(String str) {
        synchronized (i.class) {
            String str2 = "apdidTokenCache" + str;
            if (f.containsKey(str2)) {
                String str3 = f.get(str2);
                if (a.b(str3)) {
                    return str3;
                }
            }
            return "";
        }
    }

    public static synchronized void a() {
        synchronized (i.class) {
        }
    }

    public static synchronized void a(b bVar) {
        synchronized (i.class) {
            if (bVar != null) {
                a = bVar.a;
                b = bVar.b;
                c = bVar.c;
            }
        }
    }

    public static synchronized void a(c cVar) {
        synchronized (i.class) {
            if (cVar != null) {
                a = cVar.a;
                b = cVar.b;
                d = cVar.d;
                e = cVar.e;
                c = cVar.c;
            }
        }
    }

    public static synchronized void a(String str, String str2) {
        synchronized (i.class) {
            String str3 = "apdidTokenCache" + str;
            if (f.containsKey(str3)) {
                f.remove(str3);
            }
            f.put(str3, str2);
        }
    }

    public static synchronized boolean a(Context context, String str) {
        boolean z;
        synchronized (i.class) {
            long j = 86400000;
            try {
                long a2 = h.a(context);
                if (a2 >= 0) {
                    j = a2;
                }
            } catch (Throwable unused) {
            }
            try {
                if (Math.abs(System.currentTimeMillis() - h.h(context, str)) < j) {
                    z = true;
                }
            } catch (Throwable th) {
                com.alipay.apmobilesecuritysdk.c.a.a(th);
            }
            z = false;
        }
        return z;
    }

    public static synchronized String b() {
        String str;
        synchronized (i.class) {
            str = a;
        }
        return str;
    }

    public static void b(String str) {
        a = str;
    }

    public static synchronized String c() {
        String str;
        synchronized (i.class) {
            str = b;
        }
        return str;
    }

    public static void c(String str) {
        b = str;
    }

    public static synchronized String d() {
        String str;
        synchronized (i.class) {
            str = d;
        }
        return str;
    }

    public static void d(String str) {
        c = str;
    }

    public static synchronized String e() {
        String str;
        synchronized (i.class) {
            str = e;
        }
        return str;
    }

    public static void e(String str) {
        d = str;
    }

    public static synchronized String f() {
        String str;
        synchronized (i.class) {
            str = c;
        }
        return str;
    }

    public static void f(String str) {
        e = str;
    }

    public static synchronized c g() {
        c cVar;
        synchronized (i.class) {
            cVar = new c(a, b, c, d, e);
        }
        return cVar;
    }

    public static void h() {
        f.clear();
        a = "";
        b = "";
        d = "";
        e = "";
        c = "";
    }
}
