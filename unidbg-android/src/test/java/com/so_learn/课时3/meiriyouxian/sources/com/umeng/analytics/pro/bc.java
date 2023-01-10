package com.umeng.analytics.pro;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* compiled from: FieldMetaData */
public class bc implements Serializable {
    private static Map<Class<? extends aq>, Map<? extends ax, bc>> d = new HashMap();
    public final String a;
    public final byte b;
    public final bd c;

    public bc(String str, byte b, bd bdVar) {
        this.a = str;
        this.b = b;
        this.c = bdVar;
    }

    public static void a(Class<? extends aq> cls, Map<? extends ax, bc> map) {
        d.put(cls, map);
    }

    public static Map<? extends ax, bc> a(Class<? extends aq> cls) {
        if (!d.containsKey(cls)) {
            try {
                cls.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("InstantiationException for TBase class: " + cls.getName() + ", message: " + e.getMessage());
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + cls.getName() + ", message: " + e2.getMessage());
            }
        }
        return d.get(cls);
    }
}
