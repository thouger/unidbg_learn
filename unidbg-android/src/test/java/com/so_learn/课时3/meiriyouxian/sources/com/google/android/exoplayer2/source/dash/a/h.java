package com.google.android.exoplayer2.source.dash.a;

import android.net.Uri;
import com.android.internal.logging.nano.MetricsProto;
import com.google.android.exoplayer2.util.y;
import com.umeng.message.proguard.l;

/* compiled from: RangedUri */
public final class h {
    public final long a;
    public final long b;
    private final String c;
    private int d;

    public h(String str, long j, long j2) {
        this.c = str == null ? "" : str;
        this.a = j;
        this.b = j2;
    }

    public Uri a(String str) {
        return y.a(str, this.c);
    }

    public String b(String str) {
        return y.b(str, this.c);
    }

    public h a(h hVar, String str) {
        String b = b(str);
        if (hVar != null && b.equals(hVar.b(str))) {
            long j = this.b;
            long j2 = -1;
            if (j != -1) {
                long j3 = this.a;
                if (j3 + j == hVar.a) {
                    long j4 = hVar.b;
                    if (j4 != -1) {
                        j2 = j + j4;
                    }
                    return new h(b, j3, j2);
                }
            }
            long j5 = hVar.b;
            if (j5 != -1) {
                long j6 = hVar.a;
                if (j6 + j5 == this.a) {
                    long j7 = this.b;
                    if (j7 != -1) {
                        j2 = j5 + j7;
                    }
                    return new h(b, j6, j2);
                }
            }
        }
        return null;
    }

    public int hashCode() {
        if (this.d == 0) {
            this.d = ((((MetricsProto.MetricsEvent.DIALOG_SUPPORT_PHONE + ((int) this.a)) * 31) + ((int) this.b)) * 31) + this.c.hashCode();
        }
        return this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        h hVar = (h) obj;
        return this.a == hVar.a && this.b == hVar.b && this.c.equals(hVar.c);
    }

    public String toString() {
        return "RangedUri(referenceUri=" + this.c + ", start=" + this.a + ", length=" + this.b + l.t;
    }
}
