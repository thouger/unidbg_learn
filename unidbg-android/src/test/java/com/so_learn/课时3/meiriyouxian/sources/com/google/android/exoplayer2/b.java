package com.google.android.exoplayer2;

import com.google.android.exoplayer2.b.e;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.source.r;
import com.google.android.exoplayer2.t;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.k;
import java.io.IOException;

/* compiled from: BaseRenderer */
public abstract class b implements t, u {
    private final int a;
    private v b;
    private int c;
    private int d;
    private r e;
    private Format[] f;
    private long g;
    private boolean h = true;
    private boolean i;

    @Override // com.google.android.exoplayer2.t
    public /* synthetic */ void a(float f) throws ExoPlaybackException {
        t.CC.$default$a(this, f);
    }

    @Override // com.google.android.exoplayer2.s.b
    public void a(int i, Object obj) throws ExoPlaybackException {
    }

    /* access modifiers changed from: protected */
    public void a(long j, boolean z) throws ExoPlaybackException {
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) throws ExoPlaybackException {
    }

    /* access modifiers changed from: protected */
    public void a(Format[] formatArr, long j) throws ExoPlaybackException {
    }

    @Override // com.google.android.exoplayer2.t
    public final u b() {
        return this;
    }

    @Override // com.google.android.exoplayer2.t
    public k c() {
        return null;
    }

    @Override // com.google.android.exoplayer2.u
    public int m() throws ExoPlaybackException {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void n() throws ExoPlaybackException {
    }

    /* access modifiers changed from: protected */
    public void o() throws ExoPlaybackException {
    }

    /* access modifiers changed from: protected */
    public void p() {
    }

    public b(int i) {
        this.a = i;
    }

    @Override // com.google.android.exoplayer2.t, com.google.android.exoplayer2.u
    public final int a() {
        return this.a;
    }

    @Override // com.google.android.exoplayer2.t
    public final void a(int i) {
        this.c = i;
    }

    @Override // com.google.android.exoplayer2.t
    public final int ac_() {
        return this.d;
    }

    @Override // com.google.android.exoplayer2.t
    public final void a(v vVar, Format[] formatArr, r rVar, long j, boolean z, long j2) throws ExoPlaybackException {
        a.b(this.d == 0);
        this.b = vVar;
        this.d = 1;
        a(z);
        a(formatArr, rVar, j2);
        a(j, z);
    }

    @Override // com.google.android.exoplayer2.t
    public final void ad_() throws ExoPlaybackException {
        boolean z = true;
        if (this.d != 1) {
            z = false;
        }
        a.b(z);
        this.d = 2;
        n();
    }

    @Override // com.google.android.exoplayer2.t
    public final void a(Format[] formatArr, r rVar, long j) throws ExoPlaybackException {
        a.b(!this.i);
        this.e = rVar;
        this.h = false;
        this.f = formatArr;
        this.g = j;
        a(formatArr, j);
    }

    @Override // com.google.android.exoplayer2.t
    public final r f() {
        return this.e;
    }

    @Override // com.google.android.exoplayer2.t
    public final boolean g() {
        return this.h;
    }

    @Override // com.google.android.exoplayer2.t
    public final void h() {
        this.i = true;
    }

    @Override // com.google.android.exoplayer2.t
    public final boolean i() {
        return this.i;
    }

    @Override // com.google.android.exoplayer2.t
    public final void j() throws IOException {
        this.e.c();
    }

    @Override // com.google.android.exoplayer2.t
    public final void a(long j) throws ExoPlaybackException {
        this.i = false;
        this.h = false;
        a(j, false);
    }

    @Override // com.google.android.exoplayer2.t
    public final void k() throws ExoPlaybackException {
        a.b(this.d == 2);
        this.d = 1;
        o();
    }

    @Override // com.google.android.exoplayer2.t
    public final void l() {
        boolean z = true;
        if (this.d != 1) {
            z = false;
        }
        a.b(z);
        this.d = 0;
        this.e = null;
        this.f = null;
        this.i = false;
        p();
    }

    /* access modifiers changed from: protected */
    public final Format[] q() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public final v r() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public final int s() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public final int a(k kVar, e eVar, boolean z) {
        int a = this.e.a(kVar, eVar, z);
        if (a == -4) {
            if (eVar.c()) {
                this.h = true;
                if (this.i) {
                    return -4;
                }
                return -3;
            }
            eVar.c += this.g;
        } else if (a == -5) {
            Format format = kVar.a;
            if (format.k != Long.MAX_VALUE) {
                kVar.a = format.a(format.k + this.g);
            }
        }
        return a;
    }

    /* access modifiers changed from: protected */
    public int b(long j) {
        return this.e.b_(j - this.g);
    }

    /* access modifiers changed from: protected */
    public final boolean t() {
        return this.h ? this.i : this.e.b();
    }

    protected static boolean a(com.google.android.exoplayer2.drm.b<?> bVar, DrmInitData drmInitData) {
        if (drmInitData == null) {
            return true;
        }
        if (bVar == null) {
            return false;
        }
        return bVar.a(drmInitData);
    }
}
