package io.reactivex.internal.functions;

import io.reactivex.c.d;

/* compiled from: ObjectHelper */
public final class a {
    static final d<Object, Object> a = new C0206a();

    public static int a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i > i2 ? 1 : 0;
    }

    public static int a(long j, long j2) {
        int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    public static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int a(int i, String str) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " > 0 required but it was " + i);
    }

    public static long a(long j, String str) {
        if (j > 0) {
            return j;
        }
        throw new IllegalArgumentException(str + " > 0 required but it was " + j);
    }

    /* compiled from: ObjectHelper */
    /* renamed from: io.reactivex.internal.functions.a$a  reason: collision with other inner class name */
    static final class C0206a implements d<Object, Object> {
        C0206a() {
        }

        @Override // io.reactivex.c.d
        public boolean a(Object obj, Object obj2) {
            return a.a(obj, obj2);
        }
    }
}
