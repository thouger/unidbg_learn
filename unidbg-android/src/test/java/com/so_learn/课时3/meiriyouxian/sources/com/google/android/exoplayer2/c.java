package com.google.android.exoplayer2;

import java.util.UUID;

/* compiled from: C */
public final class c {
    public static final UUID a = new UUID(0, 0);
    public static final UUID b = new UUID(1186680826959645954L, -5988876978535335093L);
    public static final UUID c = new UUID(-2129748144642739255L, 8654423357094679310L);
    public static final UUID d = new UUID(-1301668207276963122L, -6645017420763422227L);
    public static final UUID e = new UUID(-7348484286925749626L, -6083546864340672619L);

    public static long b(long j) {
        return (j == -9223372036854775807L || j == Long.MIN_VALUE) ? j : j * 1000;
    }

    public static long a(long j) {
        return (j == -9223372036854775807L || j == Long.MIN_VALUE) ? j : j / 1000;
    }
}
