package com.c.b;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/* compiled from: FieldUtils */
public class a {
    private static Map<String, Field> a = new HashMap();

    private static String a(Class<?> cls, String str) {
        return cls.toString() + "#" + str;
    }

    private static Field b(Class<?> cls, String str, boolean z) {
        Field field;
        d.a(cls != null, "The class must not be null", new Object[0]);
        d.a(!c.a(str), "The field name must not be blank/empty", new Object[0]);
        String a2 = a(cls, str);
        synchronized (a) {
            field = a.get(a2);
        }
        if (field != null) {
            if (z && !field.isAccessible()) {
                field.setAccessible(true);
            }
            return field;
        }
        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            try {
                Field declaredField = cls2.getDeclaredField(str);
                if (!Modifier.isPublic(declaredField.getModifiers())) {
                    if (z) {
                        declaredField.setAccessible(true);
                    }
                }
                synchronized (a) {
                    a.put(a2, declaredField);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
            }
        }
        Field field2 = null;
        for (Class<?> cls3 : c.a(cls)) {
            try {
                Field field3 = cls3.getField(str);
                d.a(field2 == null, "Reference to field %s is ambiguous relative to %s; a matching field exists on two or more implemented interfaces.", str, cls);
                field2 = field3;
            } catch (NoSuchFieldException unused2) {
            }
        }
        synchronized (a) {
            a.put(a2, field2);
        }
        return field2;
    }

    public static Object a(Field field, Object obj, boolean z) throws IllegalAccessException {
        d.a(field != null, "The field must not be null", new Object[0]);
        if (!z || field.isAccessible()) {
            b.a((AccessibleObject) field);
        } else {
            field.setAccessible(true);
        }
        return field.get(obj);
    }

    public static void a(Field field, Object obj, Object obj2, boolean z) throws IllegalAccessException {
        d.a(field != null, "The field must not be null", new Object[0]);
        if (!z || field.isAccessible()) {
            b.a((AccessibleObject) field);
        } else {
            field.setAccessible(true);
        }
        field.set(obj, obj2);
    }

    public static Object a(Field field, Object obj) throws IllegalAccessException {
        return a(field, obj, true);
    }

    public static Object a(Object obj, String str) throws IllegalAccessException {
        d.a(obj != null, "target object must not be null", new Object[0]);
        Class<?> cls = obj.getClass();
        Field b = b(cls, str, true);
        d.a(b != null, "Cannot locate field %s on %s", str, cls);
        return a(b, obj, false);
    }

    public static void a(Object obj, String str, Object obj2) throws IllegalAccessException {
        a(obj, str, obj2, true);
    }

    public static void a(Object obj, String str, Object obj2, boolean z) throws IllegalAccessException {
        d.a(obj != null, "target object must not be null", new Object[0]);
        Class<?> cls = obj.getClass();
        Field b = b(cls, str, true);
        d.a(b != null, "Cannot locate declared field %s.%s", cls.getName(), str);
        a(b, obj, obj2, z);
    }

    public static Field a(String str, String str2, boolean z) {
        try {
            return a(Class.forName(str), str2, z);
        } catch (Throwable th) {
            c.a(th);
            return null;
        }
    }

    public static Field a(Class<?> cls, String str, boolean z) {
        d.a(cls != null, "The class must not be null", new Object[0]);
        d.a(!c.a(str), "The field name must not be blank/empty", new Object[0]);
        try {
            Field declaredField = cls.getDeclaredField(str);
            if (!b.a((Member) declaredField)) {
                if (!z) {
                    return null;
                }
                declaredField.setAccessible(true);
            }
            return declaredField;
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }
}
