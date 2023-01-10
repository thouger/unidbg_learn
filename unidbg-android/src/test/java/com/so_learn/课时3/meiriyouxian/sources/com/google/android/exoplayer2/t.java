package com.google.android.exoplayer2;

import com.google.android.exoplayer2.s;
import com.google.android.exoplayer2.source.r;
import com.google.android.exoplayer2.util.k;
import java.io.IOException;

/* compiled from: Renderer */
public interface t extends s.b {

    /* compiled from: Renderer */
    /* renamed from: com.google.android.exoplayer2.t$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$a(t tVar, float f) throws ExoPlaybackException {
        }
    }

    @Override // com.google.android.exoplayer2.u
    int a();

    void a(float f) throws ExoPlaybackException;

    void a(int i);

    void a(long j) throws ExoPlaybackException;

    void a(long j, long j2) throws ExoPlaybackException;

    void a(v vVar, Format[] formatArr, r rVar, long j, boolean z, long j2) throws ExoPlaybackException;

    void a(Format[] formatArr, r rVar, long j) throws ExoPlaybackException;

    int ac_();

    void ad_() throws ExoPlaybackException;

    u b();

    k c();

    r f();

    boolean g();

    void h();

    boolean i();

    void j() throws IOException;

    void k() throws ExoPlaybackException;

    void l();

    boolean u();

    boolean v();
}
