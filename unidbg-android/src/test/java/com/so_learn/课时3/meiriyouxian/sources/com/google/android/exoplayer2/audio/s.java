package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.util.z;

/* compiled from: WavUtil */
public final class s {
    public static final int a = z.h("RIFF");
    public static final int b = z.h("WAVE");
    public static final int c = z.h("fmt ");
    public static final int d = z.h("data");

    public static int a(int i, int i2) {
        if (i != 1) {
            if (i == 3) {
                return i2 == 32 ? 4 : 0;
            }
            if (i != 65534) {
                if (i != 6) {
                    return i != 7 ? 0 : 268435456;
                }
                return 536870912;
            }
        }
        return z.b(i2);
    }
}
