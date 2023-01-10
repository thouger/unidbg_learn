package com.google.android.exoplayer2.source.hls;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.e;
import com.google.android.exoplayer2.source.hls.l;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.source.hls.playlist.c;
import com.google.android.exoplayer2.source.l;
import com.google.android.exoplayer2.source.n;
import com.google.android.exoplayer2.source.r;
import com.google.android.exoplayer2.upstream.b;
import com.google.android.exoplayer2.upstream.o;
import com.google.android.exoplayer2.upstream.s;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.z;
import com.google.android.exoplayer2.x;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;

/* compiled from: HlsMediaPeriod */
public final class i implements l.a, HlsPlaylistTracker.b, com.google.android.exoplayer2.source.l {
    private final f a;
    private final HlsPlaylistTracker b;
    private final e c;
    private final s d;
    private final o e;
    private final n.a f;
    private final b g;
    private final IdentityHashMap<r, Integer> h = new IdentityHashMap<>();
    private final m i = new m();
    private final e j;
    private final boolean k;
    private l.a l;
    private int m;
    private TrackGroupArray n;
    private l[] o = new l[0];
    private l[] p = new l[0];
    private com.google.android.exoplayer2.source.s q;
    private boolean r;

    @Override // com.google.android.exoplayer2.source.l
    public long a(long j, x xVar) {
        return j;
    }

    public i(f fVar, HlsPlaylistTracker hlsPlaylistTracker, e eVar, s sVar, o oVar, n.a aVar, b bVar, e eVar2, boolean z) {
        this.a = fVar;
        this.b = hlsPlaylistTracker;
        this.c = eVar;
        this.d = sVar;
        this.e = oVar;
        this.f = aVar;
        this.g = bVar;
        this.j = eVar2;
        this.k = z;
        this.q = eVar2.a(new com.google.android.exoplayer2.source.s[0]);
        aVar.a();
    }

    public void f() {
        this.b.b(this);
        for (l lVar : this.o) {
            lVar.h();
        }
        this.l = null;
        this.f.b();
    }

    @Override // com.google.android.exoplayer2.source.l
    public void a(l.a aVar, long j) {
        this.l = aVar;
        this.b.a(this);
        d(j);
    }

    @Override // com.google.android.exoplayer2.source.l
    public void ae_() throws IOException {
        for (l lVar : this.o) {
            lVar.c();
        }
    }

    @Override // com.google.android.exoplayer2.source.l
    public TrackGroupArray b() {
        return this.n;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ea, code lost:
        if (r11 != r8[0]) goto L_0x00ee;
     */
    @Override // com.google.android.exoplayer2.source.l
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long a(com.google.android.exoplayer2.trackselection.e[] r21, boolean[] r22, com.google.android.exoplayer2.source.r[] r23, boolean[] r24, long r25) {
        /*
        // Method dump skipped, instructions count: 297
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.i.a(com.google.android.exoplayer2.trackselection.e[], boolean[], com.google.android.exoplayer2.source.r[], boolean[], long):long");
    }

    @Override // com.google.android.exoplayer2.source.l
    public void a(long j, boolean z) {
        for (l lVar : this.p) {
            lVar.a(j, z);
        }
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public void a(long j) {
        this.q.a(j);
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public boolean c(long j) {
        if (this.n != null) {
            return this.q.c(j);
        }
        for (l lVar : this.o) {
            lVar.b();
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public long e() {
        return this.q.e();
    }

    @Override // com.google.android.exoplayer2.source.l
    public long c() {
        if (this.r) {
            return -9223372036854775807L;
        }
        this.f.c();
        this.r = true;
        return -9223372036854775807L;
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public long d() {
        return this.q.d();
    }

    @Override // com.google.android.exoplayer2.source.l
    public long b(long j) {
        l[] lVarArr = this.p;
        if (lVarArr.length > 0) {
            boolean b = lVarArr[0].b(j, false);
            int i = 1;
            while (true) {
                l[] lVarArr2 = this.p;
                if (i >= lVarArr2.length) {
                    break;
                }
                lVarArr2[i].b(j, b);
                i++;
            }
            if (b) {
                this.i.a();
            }
        }
        return j;
    }

    @Override // com.google.android.exoplayer2.source.hls.l.a
    public void g() {
        int i = this.m - 1;
        this.m = i;
        if (i <= 0) {
            int i2 = 0;
            for (l lVar : this.o) {
                i2 += lVar.f().b;
            }
            TrackGroup[] trackGroupArr = new TrackGroup[i2];
            l[] lVarArr = this.o;
            int length = lVarArr.length;
            int i3 = 0;
            int i4 = 0;
            while (i3 < length) {
                l lVar2 = lVarArr[i3];
                int i5 = lVar2.f().b;
                int i6 = i4;
                int i7 = 0;
                while (i7 < i5) {
                    trackGroupArr[i6] = lVar2.f().a(i7);
                    i7++;
                    i6++;
                }
                i3++;
                i4 = i6;
            }
            this.n = new TrackGroupArray(trackGroupArr);
            this.l.a((com.google.android.exoplayer2.source.l) this);
        }
    }

    @Override // com.google.android.exoplayer2.source.hls.l.a
    public void a(c.a aVar) {
        this.b.c(aVar);
    }

    public void a(l lVar) {
        this.l.a((l.a) this);
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.b
    public void h() {
        this.l.a((l.a) this);
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.b
    public boolean a(c.a aVar, long j) {
        boolean z = true;
        for (l lVar : this.o) {
            z &= lVar.a(aVar, j);
        }
        this.l.a((l.a) this);
        return z;
    }

    private void d(long j) {
        c b = this.b.b();
        List<c.a> list = b.c;
        List<c.a> list2 = b.d;
        int size = list.size() + 1 + list2.size();
        this.o = new l[size];
        this.m = size;
        a(b, j);
        char c = 0;
        int i = 1;
        int i2 = 0;
        while (i2 < list.size()) {
            c.a aVar = list.get(i2);
            c.a[] aVarArr = new c.a[1];
            aVarArr[c] = aVar;
            l a = a(1, aVarArr, (Format) null, Collections.emptyList(), j);
            int i3 = i + 1;
            this.o[i] = a;
            Format format = aVar.b;
            if (!this.k || format.d == null) {
                a.b();
            } else {
                a.a(new TrackGroupArray(new TrackGroup(aVar.b)), 0, TrackGroupArray.a);
            }
            i2++;
            i = i3;
            c = 0;
        }
        int i4 = 0;
        while (i4 < list2.size()) {
            c.a aVar2 = list2.get(i4);
            l a2 = a(3, new c.a[]{aVar2}, (Format) null, Collections.emptyList(), j);
            this.o[i] = a2;
            a2.a(new TrackGroupArray(new TrackGroup(aVar2.b)), 0, TrackGroupArray.a);
            i4++;
            i++;
        }
        this.p = this.o;
    }

    private void a(c cVar, long j) {
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList(cVar.b);
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (int i = 0; i < arrayList2.size(); i++) {
            c.a aVar = (c.a) arrayList2.get(i);
            Format format = aVar.b;
            if (format.m > 0 || z.a(format.d, 2) != null) {
                arrayList3.add(aVar);
            } else if (z.a(format.d, 1) != null) {
                arrayList4.add(aVar);
            }
        }
        if (!arrayList3.isEmpty()) {
            arrayList = arrayList3;
        } else {
            if (arrayList4.size() < arrayList2.size()) {
                arrayList2.removeAll(arrayList4);
            }
            arrayList = arrayList2;
        }
        a.a(!arrayList.isEmpty());
        c.a[] aVarArr = (c.a[]) arrayList.toArray(new c.a[0]);
        String str = aVarArr[0].b.d;
        l a = a(0, aVarArr, cVar.e, cVar.f, j);
        this.o[0] = a;
        if (!this.k || str == null) {
            a.a(true);
            a.b();
            return;
        }
        boolean z = z.a(str, 2) != null;
        boolean z2 = z.a(str, 1) != null;
        ArrayList arrayList5 = new ArrayList();
        if (z) {
            Format[] formatArr = new Format[arrayList.size()];
            for (int i2 = 0; i2 < formatArr.length; i2++) {
                formatArr[i2] = a(aVarArr[i2].b);
            }
            arrayList5.add(new TrackGroup(formatArr));
            if (z2 && (cVar.e != null || cVar.c.isEmpty())) {
                arrayList5.add(new TrackGroup(a(aVarArr[0].b, cVar.e, false)));
            }
            List<Format> list = cVar.f;
            if (list != null) {
                for (int i3 = 0; i3 < list.size(); i3++) {
                    arrayList5.add(new TrackGroup(list.get(i3)));
                }
            }
        } else if (z2) {
            Format[] formatArr2 = new Format[arrayList.size()];
            for (int i4 = 0; i4 < formatArr2.length; i4++) {
                formatArr2[i4] = a(aVarArr[i4].b, cVar.e, true);
            }
            arrayList5.add(new TrackGroup(formatArr2));
        } else {
            throw new IllegalArgumentException("Unexpected codecs attribute: " + str);
        }
        TrackGroup trackGroup = new TrackGroup(Format.a("ID3", "application/id3", (String) null, -1, (DrmInitData) null));
        arrayList5.add(trackGroup);
        a.a(new TrackGroupArray((TrackGroup[]) arrayList5.toArray(new TrackGroup[0])), 0, new TrackGroupArray(trackGroup));
    }

    private l a(int i, c.a[] aVarArr, Format format, List<Format> list, long j) {
        return new l(i, this, new d(this.a, this.b, aVarArr, this.c, this.d, this.i, list), this.g, j, format, this.e, this.f);
    }

    private static Format a(Format format) {
        String a = z.a(format.d, 2);
        return Format.a(format.a, format.b, format.f, com.google.android.exoplayer2.util.l.f(a), a, format.c, format.l, format.m, format.n, (List<byte[]>) null, format.y);
    }

    private static Format a(Format format, Format format2, boolean z) {
        String str;
        int i;
        int i2;
        String str2;
        String str3;
        int i3 = -1;
        if (format2 != null) {
            String str4 = format2.d;
            int i4 = format2.t;
            int i5 = format2.y;
            String str5 = format2.z;
            str3 = format2.b;
            str2 = str4;
            i2 = i4;
            i = i5;
            str = str5;
        } else {
            String a = z.a(format.d, 1);
            if (z) {
                int i6 = format.t;
                int i7 = format.y;
                str2 = a;
                i2 = i6;
                str = format.b;
                i = i7;
                str3 = format.b;
            } else {
                str2 = a;
                str3 = null;
                str = null;
                i2 = -1;
                i = 0;
            }
        }
        String f = com.google.android.exoplayer2.util.l.f(str2);
        if (z) {
            i3 = format.c;
        }
        return Format.a(format.a, str3, format.f, f, str2, i3, i2, -1, (List<byte[]>) null, i, str);
    }
}
