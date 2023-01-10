package com.cmic.sso.wy.utils;

import com.cmic.sso.wy.b.d;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: OverTimeUtils */
public class j {
    private static ConcurrentHashMap<String, d> a = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Boolean> b = new ConcurrentHashMap<>();

    public static boolean a(String str) {
        return !a.containsKey(str);
    }

    public static void a(String str, d dVar) {
        a.put(str, dVar);
    }

    public static void b(String str) {
        a.remove(str);
        if (b.containsKey(str)) {
            b.remove(str);
        }
    }

    public static d c(String str) {
        return a.get(str);
    }

    public static boolean a() {
        return a.isEmpty();
    }
}
