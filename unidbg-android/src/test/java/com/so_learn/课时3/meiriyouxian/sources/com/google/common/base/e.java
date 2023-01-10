package com.google.common.base;

/* compiled from: CommonPattern */
/* access modifiers changed from: package-private */
public abstract class e {
    public abstract int flags();

    public abstract d matcher(CharSequence charSequence);

    public abstract String pattern();

    @Override // java.lang.Object
    public abstract String toString();

    e() {
    }

    public static e compile(String str) {
        return l.b(str);
    }

    public static boolean isPcreLike() {
        return l.b();
    }
}
