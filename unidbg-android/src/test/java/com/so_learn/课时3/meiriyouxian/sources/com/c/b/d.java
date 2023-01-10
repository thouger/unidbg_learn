package com.c.b;

/* compiled from: Validate */
/* access modifiers changed from: package-private */
public class d {
    static void a(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }
}
