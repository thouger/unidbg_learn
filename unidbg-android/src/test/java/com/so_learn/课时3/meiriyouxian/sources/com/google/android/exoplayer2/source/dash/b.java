package com.google.android.exoplayer2.source.dash;

import android.util.Pair;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.a.g;
import com.google.android.exoplayer2.source.dash.a;
import com.google.android.exoplayer2.source.dash.a.d;
import com.google.android.exoplayer2.source.dash.a.f;
import com.google.android.exoplayer2.source.dash.a.i;
import com.google.android.exoplayer2.source.dash.h;
import com.google.android.exoplayer2.source.e;
import com.google.android.exoplayer2.source.l;
import com.google.android.exoplayer2.source.n;
import com.google.android.exoplayer2.source.r;
import com.google.android.exoplayer2.source.s;
import com.google.android.exoplayer2.upstream.o;
import com.google.android.exoplayer2.upstream.p;
import com.google.android.exoplayer2.util.z;
import com.google.android.exoplayer2.x;
import com.xiaomi.mipush.sdk.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: DashMediaPeriod */
/* access modifiers changed from: package-private */
public final class b implements g.b<a>, l, s.a<g<a>> {
    final int a;
    private final a.AbstractC0089a b;
    private final com.google.android.exoplayer2.upstream.s c;
    private final o d;
    private final long e;
    private final p f;
    private final com.google.android.exoplayer2.upstream.b g;
    private final TrackGroupArray h;
    private final a[] i;
    private final e j;
    private final h k;
    private final IdentityHashMap<g<a>, h.c> l = new IdentityHashMap<>();
    private final n.a m;
    private l.a n;
    private g<a>[] o = a(0);
    private g[] p = new g[0];
    private s q;
    private com.google.android.exoplayer2.source.dash.a.b r;
    private int s;
    private List<com.google.android.exoplayer2.source.dash.a.e> t;
    private boolean u;

    public b(int i, com.google.android.exoplayer2.source.dash.a.b bVar, int i2, a.AbstractC0089a aVar, com.google.android.exoplayer2.upstream.s sVar, o oVar, n.a aVar2, long j, p pVar, com.google.android.exoplayer2.upstream.b bVar2, e eVar, h.b bVar3) {
        this.a = i;
        this.r = bVar;
        this.s = i2;
        this.b = aVar;
        this.c = sVar;
        this.d = oVar;
        this.m = aVar2;
        this.e = j;
        this.f = pVar;
        this.g = bVar2;
        this.j = eVar;
        this.k = new h(bVar, bVar3, bVar2);
        this.q = eVar.a(this.o);
        f a2 = bVar.a(i2);
        this.t = a2.d;
        Pair<TrackGroupArray, a[]> a3 = a(a2.c, this.t);
        this.h = a3.first;
        this.i = (a[]) a3.second;
        aVar2.a();
    }

    public void a(com.google.android.exoplayer2.source.dash.a.b bVar, int i) {
        this.r = bVar;
        this.s = i;
        this.k.a(bVar);
        g<a>[] gVarArr = this.o;
        if (gVarArr != null) {
            for (g<a> gVar : gVarArr) {
                ((a) gVar.a()).a(bVar, i);
            }
            this.n.a((l.a) this);
        }
        this.t = bVar.a(i).d;
        g[] gVarArr2 = this.p;
        for (g gVar2 : gVarArr2) {
            Iterator<com.google.android.exoplayer2.source.dash.a.e> it2 = this.t.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                com.google.android.exoplayer2.source.dash.a.e next = it2.next();
                if (next.a().equals(gVar2.a())) {
                    boolean z = true;
                    int a2 = bVar.a() - 1;
                    if (!bVar.d || i != a2) {
                        z = false;
                    }
                    gVar2.a(next, z);
                }
            }
        }
    }

    public void f() {
        this.k.b();
        for (g<a> gVar : this.o) {
            gVar.a(this);
        }
        this.n = null;
        this.m.b();
    }

    @Override // com.google.android.exoplayer2.source.a.g.b
    public synchronized void a(g<a> gVar) {
        h.c remove = this.l.remove(gVar);
        if (remove != null) {
            remove.a();
        }
    }

    @Override // com.google.android.exoplayer2.source.l
    public void a(l.a aVar, long j) {
        this.n = aVar;
        aVar.a((l) this);
    }

    @Override // com.google.android.exoplayer2.source.l
    public void ae_() throws IOException {
        this.f.a();
    }

    @Override // com.google.android.exoplayer2.source.l
    public TrackGroupArray b() {
        return this.h;
    }

    @Override // com.google.android.exoplayer2.source.l
    public long a(com.google.android.exoplayer2.trackselection.e[] eVarArr, boolean[] zArr, r[] rVarArr, boolean[] zArr2, long j) {
        int[] a2 = a(eVarArr);
        a(eVarArr, zArr, rVarArr);
        a(eVarArr, rVarArr, a2);
        a(eVarArr, rVarArr, zArr2, j, a2);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (r rVar : rVarArr) {
            if (rVar instanceof g) {
                arrayList.add((g) rVar);
            } else if (rVar instanceof g) {
                arrayList2.add((g) rVar);
            }
        }
        this.o = a(arrayList.size());
        arrayList.toArray(this.o);
        this.p = new g[arrayList2.size()];
        arrayList2.toArray(this.p);
        this.q = this.j.a(this.o);
        return j;
    }

    @Override // com.google.android.exoplayer2.source.l
    public void a(long j, boolean z) {
        for (g<a> gVar : this.o) {
            gVar.a(j, z);
        }
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public void a(long j) {
        this.q.a(j);
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public boolean c(long j) {
        return this.q.c(j);
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public long e() {
        return this.q.e();
    }

    @Override // com.google.android.exoplayer2.source.l
    public long c() {
        if (this.u) {
            return -9223372036854775807L;
        }
        this.m.c();
        this.u = true;
        return -9223372036854775807L;
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public long d() {
        return this.q.d();
    }

    @Override // com.google.android.exoplayer2.source.l
    public long b(long j) {
        for (g<a> gVar : this.o) {
            gVar.b(j);
        }
        for (g gVar2 : this.p) {
            gVar2.b(j);
        }
        return j;
    }

    @Override // com.google.android.exoplayer2.source.l
    public long a(long j, x xVar) {
        g<a>[] gVarArr = this.o;
        for (g<a> gVar : gVarArr) {
            if (gVar.a == 2) {
                return gVar.a(j, xVar);
            }
        }
        return j;
    }

    /* renamed from: b */
    public void a(g<a> gVar) {
        this.n.a((l.a) this);
    }

    private int[] a(com.google.android.exoplayer2.trackselection.e[] eVarArr) {
        int[] iArr = new int[eVarArr.length];
        for (int i = 0; i < eVarArr.length; i++) {
            if (eVarArr[i] != null) {
                iArr[i] = this.h.a(eVarArr[i].f());
            } else {
                iArr[i] = -1;
            }
        }
        return iArr;
    }

    private void a(com.google.android.exoplayer2.trackselection.e[] eVarArr, boolean[] zArr, r[] rVarArr) {
        for (int i = 0; i < eVarArr.length; i++) {
            if (eVarArr[i] == null || !zArr[i]) {
                if (rVarArr[i] instanceof g) {
                    ((g) rVarArr[i]).a(this);
                } else if (rVarArr[i] instanceof g.a) {
                    ((g.a) rVarArr[i]).a();
                }
                rVarArr[i] = null;
            }
        }
    }

    private void a(com.google.android.exoplayer2.trackselection.e[] eVarArr, r[] rVarArr, int[] iArr) {
        boolean z;
        for (int i = 0; i < eVarArr.length; i++) {
            if ((rVarArr[i] instanceof com.google.android.exoplayer2.source.h) || (rVarArr[i] instanceof g.a)) {
                int a2 = a(i, iArr);
                if (a2 == -1) {
                    z = rVarArr[i] instanceof com.google.android.exoplayer2.source.h;
                } else {
                    z = (rVarArr[i] instanceof g.a) && ((g.a) rVarArr[i]).a == rVarArr[a2];
                }
                if (!z) {
                    if (rVarArr[i] instanceof g.a) {
                        ((g.a) rVarArr[i]).a();
                    }
                    rVarArr[i] = null;
                }
            }
        }
    }

    private void a(com.google.android.exoplayer2.trackselection.e[] eVarArr, r[] rVarArr, boolean[] zArr, long j, int[] iArr) {
        for (int i = 0; i < eVarArr.length; i++) {
            if (rVarArr[i] == null && eVarArr[i] != null) {
                zArr[i] = true;
                a aVar = this.i[iArr[i]];
                if (aVar.c == 0) {
                    rVarArr[i] = a(aVar, eVarArr[i], j);
                } else if (aVar.c == 2) {
                    rVarArr[i] = new g(this.t.get(aVar.d), eVarArr[i].f().a(0), this.r.d);
                }
            }
        }
        for (int i2 = 0; i2 < eVarArr.length; i2++) {
            if (rVarArr[i2] == null && eVarArr[i2] != null) {
                a aVar2 = this.i[iArr[i2]];
                if (aVar2.c == 1) {
                    int a2 = a(i2, iArr);
                    if (a2 == -1) {
                        rVarArr[i2] = new com.google.android.exoplayer2.source.h();
                    } else {
                        rVarArr[i2] = ((g) rVarArr[a2]).a(j, aVar2.b);
                    }
                }
            }
        }
    }

    private int a(int i, int[] iArr) {
        int i2 = iArr[i];
        if (i2 == -1) {
            return -1;
        }
        int i3 = this.i[i2].e;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            int i5 = iArr[i4];
            if (i5 == i3 && this.i[i5].c == 0) {
                return i4;
            }
        }
        return -1;
    }

    private static Pair<TrackGroupArray, a[]> a(List<com.google.android.exoplayer2.source.dash.a.a> list, List<com.google.android.exoplayer2.source.dash.a.e> list2) {
        int[][] a2 = a(list);
        int length = a2.length;
        boolean[] zArr = new boolean[length];
        boolean[] zArr2 = new boolean[length];
        int a3 = a(length, list, a2, zArr, zArr2) + length + list2.size();
        TrackGroup[] trackGroupArr = new TrackGroup[a3];
        a[] aVarArr = new a[a3];
        a(list2, trackGroupArr, aVarArr, a(list, a2, length, zArr, zArr2, trackGroupArr, aVarArr));
        return Pair.create(new TrackGroupArray(trackGroupArr), aVarArr);
    }

    private static int[][] a(List<com.google.android.exoplayer2.source.dash.a.a> list) {
        int size = list.size();
        SparseIntArray sparseIntArray = new SparseIntArray(size);
        for (int i = 0; i < size; i++) {
            sparseIntArray.put(list.get(i).a, i);
        }
        int[][] iArr = new int[size][];
        boolean[] zArr = new boolean[size];
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            if (!zArr[i3]) {
                zArr[i3] = true;
                d b = b(list.get(i3).e);
                if (b == null) {
                    iArr[i2] = new int[]{i3};
                    i2++;
                } else {
                    String[] a2 = z.a(b.b, Constants.ACCEPT_TIME_SEPARATOR_SP);
                    int[] iArr2 = new int[(a2.length + 1)];
                    iArr2[0] = i3;
                    int i4 = 1;
                    for (String str : a2) {
                        int i5 = sparseIntArray.get(Integer.parseInt(str), -1);
                        if (i5 != -1) {
                            zArr[i5] = true;
                            iArr2[i4] = i5;
                            i4++;
                        }
                    }
                    if (i4 < iArr2.length) {
                        iArr2 = Arrays.copyOf(iArr2, i4);
                    }
                    iArr[i2] = iArr2;
                    i2++;
                }
            }
        }
        return i2 < size ? (int[][]) Arrays.copyOf(iArr, i2) : iArr;
    }

    private static int a(int i, List<com.google.android.exoplayer2.source.dash.a.a> list, int[][] iArr, boolean[] zArr, boolean[] zArr2) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (a(list, iArr[i3])) {
                zArr[i3] = true;
                i2++;
            }
            if (b(list, iArr[i3])) {
                zArr2[i3] = true;
                i2++;
            }
        }
        return i2;
    }

    private static int a(List<com.google.android.exoplayer2.source.dash.a.a> list, int[][] iArr, int i, boolean[] zArr, boolean[] zArr2, TrackGroup[] trackGroupArr, a[] aVarArr) {
        int i2;
        int i3;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            int[] iArr2 = iArr[i4];
            ArrayList arrayList = new ArrayList();
            for (int i6 : iArr2) {
                arrayList.addAll(list.get(i6).c);
            }
            Format[] formatArr = new Format[arrayList.size()];
            for (int i7 = 0; i7 < formatArr.length; i7++) {
                formatArr[i7] = ((i) arrayList.get(i7)).c;
            }
            com.google.android.exoplayer2.source.dash.a.a aVar = list.get(iArr2[0]);
            int i8 = i5 + 1;
            if (zArr[i4]) {
                i2 = i8 + 1;
            } else {
                i2 = i8;
                i8 = -1;
            }
            if (zArr2[i4]) {
                i3 = i2 + 1;
            } else {
                i3 = i2;
                i2 = -1;
            }
            trackGroupArr[i5] = new TrackGroup(formatArr);
            aVarArr[i5] = a.a(aVar.b, iArr2, i5, i8, i2);
            if (i8 != -1) {
                trackGroupArr[i8] = new TrackGroup(Format.a(aVar.a + ":emsg", "application/x-emsg", (String) null, -1, (DrmInitData) null));
                aVarArr[i8] = a.a(iArr2, i5);
            }
            if (i2 != -1) {
                trackGroupArr[i2] = new TrackGroup(Format.a(aVar.a + ":cea608", "application/cea-608", 0, null));
                aVarArr[i2] = a.b(iArr2, i5);
            }
            i4++;
            i5 = i3;
        }
        return i5;
    }

    private static void a(List<com.google.android.exoplayer2.source.dash.a.e> list, TrackGroup[] trackGroupArr, a[] aVarArr, int i) {
        int i2 = i;
        int i3 = 0;
        while (i3 < list.size()) {
            trackGroupArr[i2] = new TrackGroup(Format.a(list.get(i3).a(), "application/x-emsg", (String) null, -1, (DrmInitData) null));
            aVarArr[i2] = a.a(i3);
            i3++;
            i2++;
        }
    }

    private g<a> a(a aVar, com.google.android.exoplayer2.trackselection.e eVar, long j) {
        int i;
        int[] iArr = new int[2];
        Format[] formatArr = new Format[2];
        boolean z = aVar.f != -1;
        if (z) {
            formatArr[0] = this.h.a(aVar.f).a(0);
            iArr[0] = 4;
            i = 1;
        } else {
            i = 0;
        }
        boolean z2 = aVar.g != -1;
        if (z2) {
            formatArr[i] = this.h.a(aVar.g).a(0);
            iArr[i] = 3;
            i++;
        }
        if (i < iArr.length) {
            formatArr = (Format[]) Arrays.copyOf(formatArr, i);
            iArr = Arrays.copyOf(iArr, i);
        }
        h.c a2 = (!this.r.d || !z) ? null : this.k.a();
        g<a> gVar = new g<>(aVar.b, iArr, formatArr, this.b.a(this.f, this.r, this.s, aVar.a, eVar, aVar.b, this.e, z, z2, a2, this.c), this, this.g, j, this.d, this.m);
        synchronized (this) {
            this.l.put(gVar, a2);
        }
        return gVar;
    }

    private static d b(List<d> list) {
        for (int i = 0; i < list.size(); i++) {
            d dVar = list.get(i);
            if ("urn:mpeg:dash:adaptation-set-switching:2016".equals(dVar.a)) {
                return dVar;
            }
        }
        return null;
    }

    private static boolean a(List<com.google.android.exoplayer2.source.dash.a.a> list, int[] iArr) {
        for (int i : iArr) {
            List<i> list2 = list.get(i).c;
            for (int i2 = 0; i2 < list2.size(); i2++) {
                if (!list2.get(i2).f.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean b(List<com.google.android.exoplayer2.source.dash.a.a> list, int[] iArr) {
        for (int i : iArr) {
            List<d> list2 = list.get(i).d;
            for (int i2 = 0; i2 < list2.size(); i2++) {
                if ("urn:scte:dash:cc:cea-608:2015".equals(list2.get(i2).a)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static g<a>[] a(int i) {
        return new g[i];
    }

    /* compiled from: DashMediaPeriod */
    /* access modifiers changed from: private */
    public static final class a {
        public final int[] a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final int g;

        public static a a(int i, int[] iArr, int i2, int i3, int i4) {
            return new a(i, 0, iArr, i2, i3, i4, -1);
        }

        public static a a(int[] iArr, int i) {
            return new a(4, 1, iArr, i, -1, -1, -1);
        }

        public static a b(int[] iArr, int i) {
            return new a(3, 1, iArr, i, -1, -1, -1);
        }

        public static a a(int i) {
            return new a(4, 2, null, -1, -1, -1, i);
        }

        private a(int i, int i2, int[] iArr, int i3, int i4, int i5, int i6) {
            this.b = i;
            this.a = iArr;
            this.c = i2;
            this.e = i3;
            this.f = i4;
            this.g = i5;
            this.d = i6;
        }
    }
}
