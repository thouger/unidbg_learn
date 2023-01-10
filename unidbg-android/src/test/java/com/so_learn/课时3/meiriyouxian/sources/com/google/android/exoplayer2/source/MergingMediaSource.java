package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.upstream.b;
import com.google.android.exoplayer2.upstream.s;
import com.google.android.exoplayer2.z;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public final class MergingMediaSource extends c<Integer> {
    private final m[] a;
    private final z[] b;
    private final ArrayList<m> c;
    private final e d;
    private Object e;
    private int f;
    private IllegalMergeException g;

    public static final class IllegalMergeException extends IOException {
        public static final int REASON_PERIOD_COUNT_MISMATCH = 0;
        public final int reason;

        public IllegalMergeException(int i) {
            this.reason = i;
        }
    }

    @Override // com.google.android.exoplayer2.source.c, com.google.android.exoplayer2.source.a
    public void a(s sVar) {
        super.a(sVar);
        for (int i = 0; i < this.a.length; i++) {
            a((MergingMediaSource) Integer.valueOf(i), this.a[i]);
        }
    }

    @Override // com.google.android.exoplayer2.source.c, com.google.android.exoplayer2.source.m
    public void b() throws IOException {
        IllegalMergeException illegalMergeException = this.g;
        if (illegalMergeException == null) {
            super.b();
            return;
        }
        throw illegalMergeException;
    }

    @Override // com.google.android.exoplayer2.source.m
    public l a(m.a aVar, b bVar, long j) {
        l[] lVarArr = new l[this.a.length];
        int a = this.b[0].a(aVar.a);
        for (int i = 0; i < lVarArr.length; i++) {
            lVarArr[i] = this.a[i].a(aVar.a(this.b[i].a(a)), bVar, j);
        }
        return new o(this.d, lVarArr);
    }

    @Override // com.google.android.exoplayer2.source.m
    public void a(l lVar) {
        o oVar = (o) lVar;
        int i = 0;
        while (true) {
            m[] mVarArr = this.a;
            if (i < mVarArr.length) {
                mVarArr[i].a(oVar.a[i]);
                i++;
            } else {
                return;
            }
        }
    }

    @Override // com.google.android.exoplayer2.source.c, com.google.android.exoplayer2.source.a
    public void a() {
        super.a();
        Arrays.fill(this.b, (Object) null);
        this.e = null;
        this.f = -1;
        this.g = null;
        this.c.clear();
        Collections.addAll(this.c, this.a);
    }

    /* access modifiers changed from: protected */
    public void a(Integer num, m mVar, z zVar, Object obj) {
        if (this.g == null) {
            this.g = a(zVar);
        }
        if (this.g == null) {
            this.c.remove(mVar);
            this.b[num.intValue()] = zVar;
            if (mVar == this.a[0]) {
                this.e = obj;
            }
            if (this.c.isEmpty()) {
                a(this.b[0], this.e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public m.a a(Integer num, m.a aVar) {
        if (num.intValue() == 0) {
            return aVar;
        }
        return null;
    }

    private IllegalMergeException a(z zVar) {
        if (this.f == -1) {
            this.f = zVar.c();
            return null;
        } else if (zVar.c() != this.f) {
            return new IllegalMergeException(0);
        } else {
            return null;
        }
    }
}
