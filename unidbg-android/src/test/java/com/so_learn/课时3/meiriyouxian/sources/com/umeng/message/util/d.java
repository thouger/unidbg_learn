package com.umeng.message.util;

import android.os.Build;
import java.io.IOException;

/* compiled from: OSUtils */
public class d {
    private static final String a = "ro.build.version.emui";
    private static final String b = "ro.miui.ui.version.code";
    private static final String c = "ro.miui.ui.version.name";
    private static final String d = "ro.miui.internal.storage";

    private static boolean a(String... strArr) {
        try {
            a g = a.g();
            for (String str : strArr) {
                if (g.a(str) == null) {
                    return false;
                }
            }
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean a() {
        return a(a);
    }

    public static boolean b() {
        return a(b, c, d);
    }

    public static boolean c() {
        try {
            return Build.class.getMethod("hasSmartBar", new Class[0]) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public static Class<?> a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
