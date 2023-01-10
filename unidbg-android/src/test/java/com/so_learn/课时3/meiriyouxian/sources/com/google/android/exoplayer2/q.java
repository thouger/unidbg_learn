package com.google.android.exoplayer2;

import com.android.internal.logging.nano.MetricsProto;
import com.google.android.exoplayer2.util.a;

/* compiled from: PlaybackParameters */
public final class q {
    public static final q a = new q(1.0f);
    public final float b;
    public final float c;
    public final boolean d;
    private final int e;

    public q(float f) {
        this(f, 1.0f, false);
    }

    public q(float f, float f2, boolean z) {
        boolean z2 = true;
        a.a(f > 0.0f);
        a.a(f2 <= 0.0f ? false : z2);
        this.b = f;
        this.c = f2;
        this.d = z;
        this.e = Math.round(f * 1000.0f);
    }

    public long a(long j) {
        return j * ((long) this.e);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        q qVar = (q) obj;
        return this.b == qVar.b && this.c == qVar.c && this.d == qVar.d;
    }

    public int hashCode() {
        return ((((MetricsProto.MetricsEvent.DIALOG_SUPPORT_PHONE + Float.floatToRawIntBits(this.b)) * 31) + Float.floatToRawIntBits(this.c)) * 31) + (this.d ? 1 : 0);
    }
}
