package com.sijla.lj;

import java.util.HashMap;
import java.util.Map;

public final class f {
    private static final Map<Long, L> a = new HashMap();

    public static synchronized L a() {
        L l;
        synchronized (f.class) {
            l = new L();
            a.put(Long.valueOf(l.a()), l);
        }
        return l;
    }

    public static synchronized L a(long j) {
        L l;
        synchronized (f.class) {
            l = a.get(Long.valueOf(j));
            if (l == null) {
                l = new L(j);
                a.put(Long.valueOf(j), l);
            }
        }
        return l;
    }

    public static synchronized long a(L l) {
        long a2;
        synchronized (f.class) {
            a.put(Long.valueOf(l.a()), l);
            a2 = l.a();
        }
        return a2;
    }
}
