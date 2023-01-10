package com.google.android.exoplayer2.source.dash;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.k;
import com.google.android.exoplayer2.metadata.emsg.b;
import com.google.android.exoplayer2.source.dash.a.e;
import com.google.android.exoplayer2.source.r;
import com.google.android.exoplayer2.util.z;
import java.io.IOException;

/* compiled from: EventSampleStream */
/* access modifiers changed from: package-private */
public final class g implements r {
    private final Format a;
    private final b b = new b();
    private long[] c;
    private boolean d;
    private e e;
    private boolean f;
    private int g;
    private long h = -9223372036854775807L;

    @Override // com.google.android.exoplayer2.source.r
    public boolean b() {
        return true;
    }

    @Override // com.google.android.exoplayer2.source.r
    public void c() throws IOException {
    }

    public g(e eVar, Format format, boolean z) {
        this.a = format;
        this.e = eVar;
        this.c = eVar.b;
        a(eVar, z);
    }

    public String a() {
        return this.e.a();
    }

    public void a(e eVar, boolean z) {
        int i = this.g;
        long j = i == 0 ? -9223372036854775807L : this.c[i - 1];
        this.d = z;
        this.e = eVar;
        this.c = eVar.b;
        long j2 = this.h;
        if (j2 != -9223372036854775807L) {
            b(j2);
        } else if (j != -9223372036854775807L) {
            this.g = z.b(this.c, j, false, false);
        }
    }

    public void b(long j) {
        boolean z = false;
        this.g = z.b(this.c, j, true, false);
        if (this.d && this.g == this.c.length) {
            z = true;
        }
        if (!z) {
            j = -9223372036854775807L;
        }
        this.h = j;
    }

    @Override // com.google.android.exoplayer2.source.r
    public int a(k kVar, com.google.android.exoplayer2.b.e eVar, boolean z) {
        if (z || !this.f) {
            kVar.a = this.a;
            this.f = true;
            return -5;
        }
        int i = this.g;
        if (i != this.c.length) {
            this.g = i + 1;
            byte[] a = this.b.a(this.e.a[i]);
            if (a == null) {
                return -3;
            }
            eVar.e(a.length);
            eVar.b_(1);
            eVar.b.put(a);
            eVar.c = this.c[i];
            return -4;
        } else if (this.d) {
            return -3;
        } else {
            eVar.b_(4);
            return -4;
        }
    }

    @Override // com.google.android.exoplayer2.source.r
    public int b_(long j) {
        int max = Math.max(this.g, z.b(this.c, j, true, false));
        int i = max - this.g;
        this.g = max;
        return i;
    }
}
