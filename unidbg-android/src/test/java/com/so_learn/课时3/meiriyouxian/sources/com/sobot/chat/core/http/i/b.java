package com.sobot.chat.core.http.i;

import com.sobot.chat.core.http.d.e;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: SobotUpload */
public class b {
    private static b a;
    private Map<String, d<?>> b = new LinkedHashMap();
    private e c = new e();

    public static b a() {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b();
                }
            }
        }
        return a;
    }

    private b() {
    }

    /* JADX DEBUG: Type inference failed for r1v2. Raw type applied. Possible types: com.sobot.chat.core.http.i.d<T>, com.sobot.chat.core.http.i.d<?> */
    public static <T> d<T> a(String str, e eVar) {
        Map<String, d<?>> c = a().c();
        d<T> dVar = (d<T>) c.get(str);
        if (dVar != null) {
            return dVar;
        }
        d dVar2 = (d<T>) new d(str, eVar);
        c.put(str, dVar2);
        return dVar2;
    }

    public e b() {
        return this.c;
    }

    public Map<String, d<?>> c() {
        return this.b;
    }

    public d<?> a(String str) {
        return this.b.get(str);
    }

    public boolean b(String str) {
        return this.b.containsKey(str);
    }

    public d<?> c(String str) {
        return this.b.remove(str);
    }

    public void d() {
        for (d<?> dVar : this.b.values()) {
            dVar.b.clear();
        }
    }
}
