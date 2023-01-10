package com.c.b;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/* compiled from: MemberUtils */
/* access modifiers changed from: package-private */
public class b {
    private static final Class<?>[] a = {Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};
    private static final Map<Class<?>, Class<?>> b = new HashMap();
    private static final Map<Class<?>, Class<?>> c = new HashMap();

    private static boolean a(int i) {
        return (i & 7) == 0;
    }

    static {
        b.put(Boolean.TYPE, Boolean.class);
        b.put(Byte.TYPE, Byte.class);
        b.put(Character.TYPE, Character.class);
        b.put(Short.TYPE, Short.class);
        b.put(Integer.TYPE, Integer.class);
        b.put(Long.TYPE, Long.class);
        b.put(Double.TYPE, Double.class);
        b.put(Float.TYPE, Float.class);
        b.put(Void.TYPE, Void.TYPE);
        for (Class<?> cls : b.keySet()) {
            Class<?> cls2 = b.get(cls);
            if (!cls.equals(cls2)) {
                c.put(cls2, cls);
            }
        }
    }

    static boolean a(Member member) {
        return member != null && Modifier.isPublic(member.getModifiers()) && !member.isSynthetic();
    }

    static boolean a(AccessibleObject accessibleObject) {
        if (accessibleObject != null && !accessibleObject.isAccessible()) {
            Member member = (Member) accessibleObject;
            if (!accessibleObject.isAccessible() && Modifier.isPublic(member.getModifiers()) && a(member.getDeclaringClass().getModifiers())) {
                try {
                    accessibleObject.setAccessible(true);
                    return true;
                } catch (SecurityException unused) {
                }
            }
        }
        return false;
    }
}
