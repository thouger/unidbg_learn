package com.google.android.exoplayer2.util;

import android.util.TimeUtils;
import android.util.proto.ProtoOutputStream;

/* compiled from: TimestampAdjuster */
public final class w {
    private long a;
    private long b;
    private volatile long c = -9223372036854775807L;

    public w(long j) {
        a(j);
    }

    public synchronized void a(long j) {
        a.b(this.c == -9223372036854775807L);
        this.a = j;
    }

    public long a() {
        return this.a;
    }

    public long b() {
        if (this.c != -9223372036854775807L) {
            return this.b + this.c;
        }
        long j = this.a;
        if (j != Long.MAX_VALUE) {
            return j;
        }
        return -9223372036854775807L;
    }

    public long c() {
        if (this.a == Long.MAX_VALUE) {
            return 0;
        }
        if (this.c == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        return this.b;
    }

    public void d() {
        this.c = -9223372036854775807L;
    }

    public long b(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        if (this.c != -9223372036854775807L) {
            long e = e(this.c);
            long j2 = (ProtoOutputStream.FIELD_TYPE_DOUBLE + e) / ProtoOutputStream.FIELD_TYPE_FLOAT;
            long j3 = ((j2 - 1) * ProtoOutputStream.FIELD_TYPE_FLOAT) + j;
            j += j2 * ProtoOutputStream.FIELD_TYPE_FLOAT;
            if (Math.abs(j3 - e) < Math.abs(j - e)) {
                j = j3;
            }
        }
        return c(d(j));
    }

    public long c(long j) {
        if (j == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        if (this.c != -9223372036854775807L) {
            this.c = j;
        } else {
            long j2 = this.a;
            if (j2 != Long.MAX_VALUE) {
                this.b = j2 - j;
            }
            synchronized (this) {
                this.c = j;
                notifyAll();
            }
        }
        return j + this.b;
    }

    public synchronized void e() throws InterruptedException {
        while (this.c == -9223372036854775807L) {
            wait();
        }
    }

    public static long d(long j) {
        return (j * TimeUtils.NANOS_PER_MS) / 90000;
    }

    public static long e(long j) {
        return (j * 90000) / TimeUtils.NANOS_PER_MS;
    }
}
