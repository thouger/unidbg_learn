package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.source.s;
import com.google.android.exoplayer2.trackselection.e;
import com.google.android.exoplayer2.x;
import java.io.IOException;

/* compiled from: MediaPeriod */
public interface l extends s {

    /* compiled from: MediaPeriod */
    public interface a extends s.a<l> {
        void a(l lVar);
    }

    long a(long j, x xVar);

    long a(e[] eVarArr, boolean[] zArr, r[] rVarArr, boolean[] zArr2, long j);

    @Override // com.google.android.exoplayer2.source.s
    void a(long j);

    void a(long j, boolean z);

    void a(a aVar, long j);

    void ae_() throws IOException;

    long b(long j);

    TrackGroupArray b();

    long c();

    @Override // com.google.android.exoplayer2.source.s
    boolean c(long j);

    @Override // com.google.android.exoplayer2.source.s
    long d();

    @Override // com.google.android.exoplayer2.source.s
    long e();
}
