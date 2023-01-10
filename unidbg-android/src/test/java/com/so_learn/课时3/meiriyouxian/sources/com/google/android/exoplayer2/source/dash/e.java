package com.google.android.exoplayer2.source.dash;

import com.google.android.exoplayer2.extractor.b;
import com.google.android.exoplayer2.source.dash.a.h;

/* compiled from: DashWrappingSegmentIndex */
public final class e implements d {
    private final b a;
    private final long b;

    @Override // com.google.android.exoplayer2.source.dash.d
    public long a() {
        return 0;
    }

    @Override // com.google.android.exoplayer2.source.dash.d
    public boolean b() {
        return true;
    }

    public e(b bVar, long j) {
        this.a = bVar;
        this.b = j;
    }

    @Override // com.google.android.exoplayer2.source.dash.d
    public int c(long j) {
        return this.a.a;
    }

    @Override // com.google.android.exoplayer2.source.dash.d
    public long a(long j) {
        return this.a.e[(int) j] - this.b;
    }

    @Override // com.google.android.exoplayer2.source.dash.d
    public long b(long j, long j2) {
        return this.a.d[(int) j];
    }

    @Override // com.google.android.exoplayer2.source.dash.d
    public h b(long j) {
        int i = (int) j;
        return new h(null, this.a.c[i], (long) this.a.b[i]);
    }

    @Override // com.google.android.exoplayer2.source.dash.d
    public long a(long j, long j2) {
        return (long) this.a.b(j + this.b);
    }
}
