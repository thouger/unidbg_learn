package com.google.android.exoplayer2;

import java.util.HashSet;

/* compiled from: ExoPlayerLibraryInfo */
public final class j {
    private static final HashSet<String> a = new HashSet<>();
    private static String b = "goog.exo.core";

    public static synchronized String a() {
        String str;
        synchronized (j.class) {
            str = b;
        }
        return str;
    }

    public static synchronized void a(String str) {
        synchronized (j.class) {
            if (a.add(str)) {
                b += ", " + str;
            }
        }
    }
}
