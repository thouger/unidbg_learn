package com.google.android.exoplayer2.util;

import android.os.Trace;

/* compiled from: TraceUtil */
public final class x {
    public static void a(String str) {
        if (z.a >= 18) {
            b(str);
        }
    }

    public static void a() {
        if (z.a >= 18) {
            b();
        }
    }

    private static void b(String str) {
        Trace.beginSection(str);
    }

    private static void b() {
        Trace.endSection();
    }
}
