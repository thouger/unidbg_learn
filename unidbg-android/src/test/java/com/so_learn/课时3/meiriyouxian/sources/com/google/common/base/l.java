package com.google.common.base;

import java.util.Locale;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/* compiled from: Platform */
/* access modifiers changed from: package-private */
public final class l {
    private static final Logger a = Logger.getLogger(l.class.getName());
    private static final k b = c();

    private l() {
    }

    static long a() {
        return System.nanoTime();
    }

    static String a(double d) {
        return String.format(Locale.ROOT, "%.4g", Double.valueOf(d));
    }

    static boolean a(String str) {
        return str == null || str.isEmpty();
    }

    static e b(String str) {
        m.a(str);
        return b.a(str);
    }

    static boolean b() {
        return b.a();
    }

    private static k c() {
        return new a();
    }

    /* compiled from: Platform */
    /* access modifiers changed from: private */
    public static final class a implements k {
        @Override // com.google.common.base.k
        public boolean a() {
            return true;
        }

        private a() {
        }

        @Override // com.google.common.base.k
        public e a(String str) {
            return new JdkPattern(Pattern.compile(str));
        }
    }
}
