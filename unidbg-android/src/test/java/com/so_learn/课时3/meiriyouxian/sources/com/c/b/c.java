package com.c.b;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/* compiled from: ReflectUtils */
/* access modifiers changed from: package-private */
public class c {
    static final Object[] a = new Object[0];
    static final Class<?>[] b = new Class[0];

    public static List<Class<?>> a(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        a(cls, linkedHashSet);
        return new ArrayList(linkedHashSet);
    }

    private static void a(Class<?> cls, HashSet<Class<?>> hashSet) {
        while (cls != null) {
            Class<?>[] interfaces = cls.getInterfaces();
            for (Class<?> cls2 : interfaces) {
                if (hashSet.add(cls2)) {
                    a(cls2, hashSet);
                }
            }
            cls = cls.getSuperclass();
        }
    }

    public static boolean a(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static void a(Throwable th) {
        if (th != null) {
            th.printStackTrace();
        }
    }
}
