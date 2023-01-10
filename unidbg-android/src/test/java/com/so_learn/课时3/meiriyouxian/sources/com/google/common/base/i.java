package com.google.common.base;

import java.util.Arrays;

/* compiled from: MoreObjects */
public final class i {
    public static <T> T a(T t, T t2) {
        if (t != null) {
            return t;
        }
        if (t2 != null) {
            return t2;
        }
        throw new NullPointerException("Both parameters are null");
    }

    public static a a(Object obj) {
        return new a(obj.getClass().getSimpleName());
    }

    public static a a(Class<?> cls) {
        return new a(cls.getSimpleName());
    }

    /* compiled from: MoreObjects */
    public static final class a {
        private final String a;
        private final C0102a b;
        private C0102a c;
        private boolean d;

        private a(String str) {
            this.b = new C0102a();
            this.c = this.b;
            this.d = false;
            this.a = (String) m.a(str);
        }

        public a a(String str, Object obj) {
            return b(str, obj);
        }

        public a a(String str, double d) {
            return b(str, String.valueOf(d));
        }

        public a a(String str, int i) {
            return b(str, String.valueOf(i));
        }

        public a a(String str, long j) {
            return b(str, String.valueOf(j));
        }

        public a a(Object obj) {
            return b(obj);
        }

        public String toString() {
            boolean z = this.d;
            StringBuilder sb = new StringBuilder(32);
            sb.append(this.a);
            sb.append('{');
            String str = "";
            for (C0102a aVar = this.b.c; aVar != null; aVar = aVar.c) {
                Object obj = aVar.b;
                if (!z || obj != null) {
                    sb.append(str);
                    if (aVar.a != null) {
                        sb.append(aVar.a);
                        sb.append('=');
                    }
                    if (obj == null || !obj.getClass().isArray()) {
                        sb.append(obj);
                    } else {
                        String deepToString = Arrays.deepToString(new Object[]{obj});
                        sb.append((CharSequence) deepToString, 1, deepToString.length() - 1);
                    }
                    str = ", ";
                }
            }
            sb.append('}');
            return sb.toString();
        }

        private C0102a a() {
            C0102a aVar = new C0102a();
            this.c.c = aVar;
            this.c = aVar;
            return aVar;
        }

        private a b(Object obj) {
            a().b = obj;
            return this;
        }

        private a b(String str, Object obj) {
            C0102a a = a();
            a.b = obj;
            a.a = (String) m.a(str);
            return this;
        }

        /* compiled from: MoreObjects */
        /* access modifiers changed from: private */
        /* renamed from: com.google.common.base.i$a$a  reason: collision with other inner class name */
        public static final class C0102a {
            String a;
            Object b;
            C0102a c;

            private C0102a() {
            }
        }
    }
}
