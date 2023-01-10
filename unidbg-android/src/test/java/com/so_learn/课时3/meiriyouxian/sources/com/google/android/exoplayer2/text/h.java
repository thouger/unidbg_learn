package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.b.f;
import java.util.List;

/* compiled from: SubtitleOutputBuffer */
public abstract class h extends f implements d {
    private d c;
    private long d;

    @Override // com.google.android.exoplayer2.b.f
    public abstract void e();

    public void a(long j, d dVar, long j2) {
        this.a = j;
        this.c = dVar;
        if (j2 == Long.MAX_VALUE) {
            j2 = this.a;
        }
        this.d = j2;
    }

    @Override // com.google.android.exoplayer2.text.d
    public int b() {
        return this.c.b();
    }

    @Override // com.google.android.exoplayer2.text.d
    public long a(int i) {
        return this.c.a(i) + this.d;
    }

    @Override // com.google.android.exoplayer2.text.d
    public int a(long j) {
        return this.c.a(j - this.d);
    }

    @Override // com.google.android.exoplayer2.text.d
    public List<a> b(long j) {
        return this.c.b(j - this.d);
    }

    @Override // com.google.android.exoplayer2.b.a
    public void a() {
        super.a();
        this.c = null;
    }
}
