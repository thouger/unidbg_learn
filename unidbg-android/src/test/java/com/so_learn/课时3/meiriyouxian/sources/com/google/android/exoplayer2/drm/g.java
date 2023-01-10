package com.google.android.exoplayer2.drm;

import android.util.Pair;
import java.util.Map;

/* compiled from: WidevineUtil */
public final class g {
    public static Pair<Long, Long> a(DrmSession<?> drmSession) {
        Map<String, String> h = drmSession.h();
        if (h == null) {
            return null;
        }
        return new Pair<>(Long.valueOf(a(h, "LicenseDurationRemaining")), Long.valueOf(a(h, "PlaybackDurationRemaining")));
    }

    private static long a(Map<String, String> map, String str) {
        if (map == null) {
            return -9223372036854775807L;
        }
        try {
            String str2 = map.get(str);
            if (str2 != null) {
                return Long.parseLong(str2);
            }
            return -9223372036854775807L;
        } catch (NumberFormatException unused) {
            return -9223372036854775807L;
        }
    }
}
