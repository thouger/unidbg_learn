package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.z;

/* compiled from: ForwardingTimeline */
public abstract class k extends z {
    protected final z b;

    public k(z zVar) {
        this.b = zVar;
    }

    @Override // com.google.android.exoplayer2.z
    public int b() {
        return this.b.b();
    }

    @Override // com.google.android.exoplayer2.z
    public int a(int i, int i2, boolean z) {
        return this.b.a(i, i2, z);
    }

    @Override // com.google.android.exoplayer2.z
    public int a(boolean z) {
        return this.b.a(z);
    }

    @Override // com.google.android.exoplayer2.z
    public int b(boolean z) {
        return this.b.b(z);
    }

    @Override // com.google.android.exoplayer2.z
    public z.b a(int i, z.b bVar, boolean z, long j) {
        return this.b.a(i, bVar, z, j);
    }

    @Override // com.google.android.exoplayer2.z
    public int c() {
        return this.b.c();
    }

    @Override // com.google.android.exoplayer2.z
    public z.a a(int i, z.a aVar, boolean z) {
        return this.b.a(i, aVar, z);
    }

    @Override // com.google.android.exoplayer2.z
    public int a(Object obj) {
        return this.b.a(obj);
    }

    @Override // com.google.android.exoplayer2.z
    public Object a(int i) {
        return this.b.a(i);
    }
}
