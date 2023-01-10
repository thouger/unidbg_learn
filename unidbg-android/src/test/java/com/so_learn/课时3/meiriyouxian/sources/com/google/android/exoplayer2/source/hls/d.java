package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import android.os.SystemClock;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.a.j;
import com.google.android.exoplayer2.source.a.l;
import com.google.android.exoplayer2.source.a.m;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.source.hls.playlist.c;
import com.google.android.exoplayer2.trackselection.e;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.h;
import com.google.android.exoplayer2.upstream.s;
import com.google.android.exoplayer2.util.z;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/* compiled from: HlsChunkSource */
/* access modifiers changed from: package-private */
public class d {
    private final f a;
    private final f b;
    private final f c;
    private final m d;
    private final c.a[] e;
    private final HlsPlaylistTracker f;
    private final TrackGroup g;
    private final List<Format> h;
    private boolean i;
    private byte[] j;
    private IOException k;
    private c.a l;
    private boolean m;
    private Uri n;
    private byte[] o;
    private String p;
    private byte[] q;
    private e r;
    private long s = -9223372036854775807L;
    private boolean t;

    /* compiled from: HlsChunkSource */
    public static final class b {
        public com.google.android.exoplayer2.source.a.d a;
        public boolean b;
        public c.a c;

        public b() {
            a();
        }

        public void a() {
            this.a = null;
            this.b = false;
            this.c = null;
        }
    }

    public d(f fVar, HlsPlaylistTracker hlsPlaylistTracker, c.a[] aVarArr, e eVar, s sVar, m mVar, List<Format> list) {
        this.a = fVar;
        this.f = hlsPlaylistTracker;
        this.e = aVarArr;
        this.d = mVar;
        this.h = list;
        Format[] formatArr = new Format[aVarArr.length];
        int[] iArr = new int[aVarArr.length];
        for (int i = 0; i < aVarArr.length; i++) {
            formatArr[i] = aVarArr[i].b;
            iArr[i] = i;
        }
        this.b = eVar.a(1);
        if (sVar != null) {
            this.b.a(sVar);
        }
        this.c = eVar.a(3);
        this.g = new TrackGroup(formatArr);
        this.r = new C0091d(this.g, iArr);
    }

    public void a() throws IOException {
        IOException iOException = this.k;
        if (iOException == null) {
            c.a aVar = this.l;
            if (aVar != null && this.t) {
                this.f.b(aVar);
                return;
            }
            return;
        }
        throw iOException;
    }

    public TrackGroup b() {
        return this.g;
    }

    public void a(e eVar) {
        this.r = eVar;
    }

    public e c() {
        return this.r;
    }

    public void d() {
        this.k = null;
    }

    public void a(boolean z) {
        this.i = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(long r40, long r42, java.util.List<com.google.android.exoplayer2.source.hls.h> r44, com.google.android.exoplayer2.source.hls.d.b r45) {
        /*
        // Method dump skipped, instructions count: 479
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.d.a(long, long, java.util.List, com.google.android.exoplayer2.source.hls.d$b):void");
    }

    public void a(com.google.android.exoplayer2.source.a.d dVar) {
        if (dVar instanceof a) {
            a aVar = (a) dVar;
            this.j = aVar.c();
            a(aVar.c.a, aVar.a, aVar.h());
        }
    }

    public boolean a(com.google.android.exoplayer2.source.a.d dVar, long j) {
        e eVar = this.r;
        return eVar.a(eVar.c(this.g.a(dVar.e)), j);
    }

    public boolean a(c.a aVar, long j) {
        int c2;
        int a2 = this.g.a(aVar.b);
        if (a2 == -1 || (c2 = this.r.c(a2)) == -1) {
            return true;
        }
        this.t = (this.l == aVar) | this.t;
        if (j == -9223372036854775807L || this.r.a(c2, j)) {
            return true;
        }
        return false;
    }

    public m[] a(h hVar, long j) {
        int i;
        if (hVar == null) {
            i = -1;
        } else {
            i = this.g.a(hVar.e);
        }
        m[] mVarArr = new m[this.r.g()];
        for (int i2 = 0; i2 < mVarArr.length; i2++) {
            int b2 = this.r.b(i2);
            c.a aVar = this.e[b2];
            if (!this.f.a(aVar)) {
                mVarArr[i2] = m.a;
            } else {
                com.google.android.exoplayer2.source.hls.playlist.d a2 = this.f.a(aVar, false);
                long c2 = a2.c - this.f.c();
                long a3 = a(hVar, b2 != i, a2, c2, j);
                if (a3 < a2.f) {
                    mVarArr[i2] = m.a;
                } else {
                    mVarArr[i2] = new c(a2, c2, (int) (a3 - a2.f));
                }
            }
        }
        return mVarArr;
    }

    private long a(h hVar, boolean z, com.google.android.exoplayer2.source.hls.playlist.d dVar, long j, long j2) {
        long a2;
        long j3;
        if (hVar != null && !z) {
            return hVar.h();
        }
        long j4 = dVar.m + j;
        if (hVar != null && !this.m) {
            j2 = hVar.h;
        }
        if (dVar.i || j2 < j4) {
            a2 = (long) z.a((List<? extends Comparable<? super Long>>) dVar.l, Long.valueOf(j2 - j), true, !this.f.e() || hVar == null);
            j3 = dVar.f;
        } else {
            a2 = dVar.f;
            j3 = (long) dVar.l.size();
        }
        return a2 + j3;
    }

    private long a(long j) {
        if (this.s != -9223372036854775807L) {
            return this.s - j;
        }
        return -9223372036854775807L;
    }

    private void a(com.google.android.exoplayer2.source.hls.playlist.d dVar) {
        long j;
        if (dVar.i) {
            j = -9223372036854775807L;
        } else {
            j = dVar.a() - this.f.c();
        }
        this.s = j;
    }

    private a a(Uri uri, String str, int i, int i2, Object obj) {
        return new a(this.c, new h(uri, 0, -1, null, 1), this.e[i].b, i2, obj, this.j, str);
    }

    private void a(Uri uri, String str, byte[] bArr) {
        byte[] byteArray = new BigInteger(z.d(str).startsWith("0x") ? str.substring(2) : str, 16).toByteArray();
        byte[] bArr2 = new byte[16];
        int length = byteArray.length > 16 ? byteArray.length - 16 : 0;
        System.arraycopy(byteArray, length, bArr2, (bArr2.length - byteArray.length) + length, byteArray.length - length);
        this.n = uri;
        this.o = bArr;
        this.p = str;
        this.q = bArr2;
    }

    private void e() {
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
    }

    /* compiled from: HlsChunkSource */
    /* renamed from: com.google.android.exoplayer2.source.hls.d$d  reason: collision with other inner class name */
    private static final class C0091d extends com.google.android.exoplayer2.trackselection.b {
        private int d;

        @Override // com.google.android.exoplayer2.trackselection.e
        public int b() {
            return 0;
        }

        @Override // com.google.android.exoplayer2.trackselection.e
        public Object c() {
            return null;
        }

        public C0091d(TrackGroup trackGroup, int[] iArr) {
            super(trackGroup, iArr);
            this.d = a(trackGroup.a(0));
        }

        @Override // com.google.android.exoplayer2.trackselection.b, com.google.android.exoplayer2.trackselection.e
        public void a(long j, long j2, long j3, List<? extends l> list, m[] mVarArr) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (b(this.d, elapsedRealtime)) {
                for (int i = this.b - 1; i >= 0; i--) {
                    if (!b(i, elapsedRealtime)) {
                        this.d = i;
                        return;
                    }
                }
                throw new IllegalStateException();
            }
        }

        @Override // com.google.android.exoplayer2.trackselection.e
        public int a() {
            return this.d;
        }
    }

    /* compiled from: HlsChunkSource */
    /* access modifiers changed from: private */
    public static final class a extends j {
        public final String a;
        private byte[] b;

        public a(f fVar, h hVar, Format format, int i, Object obj, byte[] bArr, String str) {
            super(fVar, hVar, 3, format, i, obj, bArr);
            this.a = str;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.android.exoplayer2.source.a.j
        public void a(byte[] bArr, int i) throws IOException {
            this.b = Arrays.copyOf(bArr, i);
        }

        public byte[] h() {
            return this.b;
        }
    }

    /* compiled from: HlsChunkSource */
    /* access modifiers changed from: private */
    public static final class c extends com.google.android.exoplayer2.source.a.b {
        private final com.google.android.exoplayer2.source.hls.playlist.d b;
        private final long c;

        public c(com.google.android.exoplayer2.source.hls.playlist.d dVar, long j, int i) {
            super((long) i, (long) (dVar.l.size() - 1));
            this.b = dVar;
            this.c = j;
        }
    }
}
