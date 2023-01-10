package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.source.l;
import com.google.android.exoplayer2.trackselection.e;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.x;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;

/* compiled from: MergingMediaPeriod */
final class o implements l, l.a {
    public final l[] a;
    private final IdentityHashMap<r, Integer> b;
    private final e c;
    private final ArrayList<l> d = new ArrayList<>();
    private l.a e;
    private TrackGroupArray f;
    private l[] g;
    private s h;

    public o(e eVar, l... lVarArr) {
        this.c = eVar;
        this.a = lVarArr;
        this.h = eVar.a(new s[0]);
        this.b = new IdentityHashMap<>();
    }

    @Override // com.google.android.exoplayer2.source.l
    public void a(l.a aVar, long j) {
        this.e = aVar;
        Collections.addAll(this.d, this.a);
        for (l lVar : this.a) {
            lVar.a(this, j);
        }
    }

    @Override // com.google.android.exoplayer2.source.l
    public void ae_() throws IOException {
        for (l lVar : this.a) {
            lVar.ae_();
        }
    }

    @Override // com.google.android.exoplayer2.source.l
    public TrackGroupArray b() {
        return this.f;
    }

    @Override // com.google.android.exoplayer2.source.l
    public long a(e[] eVarArr, boolean[] zArr, r[] rVarArr, boolean[] zArr2, long j) {
        int[] iArr = new int[eVarArr.length];
        int[] iArr2 = new int[eVarArr.length];
        for (int i = 0; i < eVarArr.length; i++) {
            iArr[i] = rVarArr[i] == null ? -1 : this.b.get(rVarArr[i]).intValue();
            iArr2[i] = -1;
            if (eVarArr[i] != null) {
                TrackGroup f = eVarArr[i].f();
                int i2 = 0;
                while (true) {
                    l[] lVarArr = this.a;
                    if (i2 >= lVarArr.length) {
                        break;
                    } else if (lVarArr[i2].b().a(f) != -1) {
                        iArr2[i] = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
            }
        }
        this.b.clear();
        r[] rVarArr2 = new r[eVarArr.length];
        r[] rVarArr3 = new r[eVarArr.length];
        e[] eVarArr2 = new e[eVarArr.length];
        ArrayList arrayList = new ArrayList(this.a.length);
        long j2 = j;
        int i3 = 0;
        while (i3 < this.a.length) {
            for (int i4 = 0; i4 < eVarArr.length; i4++) {
                e eVar = null;
                rVarArr3[i4] = iArr[i4] == i3 ? rVarArr[i4] : null;
                if (iArr2[i4] == i3) {
                    eVar = eVarArr[i4];
                }
                eVarArr2[i4] = eVar;
            }
            long a = this.a[i3].a(eVarArr2, zArr, rVarArr3, zArr2, j2);
            if (i3 == 0) {
                j2 = a;
            } else if (a != j2) {
                throw new IllegalStateException("Children enabled at different positions.");
            }
            boolean z = false;
            for (int i5 = 0; i5 < eVarArr.length; i5++) {
                boolean z2 = true;
                if (iArr2[i5] == i3) {
                    a.b(rVarArr3[i5] != null);
                    rVarArr2[i5] = rVarArr3[i5];
                    this.b.put(rVarArr3[i5], Integer.valueOf(i3));
                    z = true;
                } else if (iArr[i5] == i3) {
                    if (rVarArr3[i5] != null) {
                        z2 = false;
                    }
                    a.b(z2);
                }
            }
            if (z) {
                arrayList.add(this.a[i3]);
            }
            i3++;
            arrayList = arrayList;
            eVarArr2 = eVarArr2;
        }
        System.arraycopy(rVarArr2, 0, rVarArr, 0, rVarArr2.length);
        this.g = new l[arrayList.size()];
        arrayList.toArray(this.g);
        this.h = this.c.a(this.g);
        return j2;
    }

    @Override // com.google.android.exoplayer2.source.l
    public void a(long j, boolean z) {
        for (l lVar : this.g) {
            lVar.a(j, z);
        }
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public void a(long j) {
        this.h.a(j);
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public boolean c(long j) {
        if (this.d.isEmpty()) {
            return this.h.c(j);
        }
        int size = this.d.size();
        for (int i = 0; i < size; i++) {
            this.d.get(i).c(j);
        }
        return false;
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public long e() {
        return this.h.e();
    }

    @Override // com.google.android.exoplayer2.source.l
    public long c() {
        long c = this.a[0].c();
        int i = 1;
        while (true) {
            l[] lVarArr = this.a;
            if (i >= lVarArr.length) {
                if (c != -9223372036854775807L) {
                    l[] lVarArr2 = this.g;
                    for (l lVar : lVarArr2) {
                        if (lVar != this.a[0] && lVar.b(c) != c) {
                            throw new IllegalStateException("Unexpected child seekToUs result.");
                        }
                    }
                }
                return c;
            } else if (lVarArr[i].c() == -9223372036854775807L) {
                i++;
            } else {
                throw new IllegalStateException("Child reported discontinuity.");
            }
        }
    }

    @Override // com.google.android.exoplayer2.source.l, com.google.android.exoplayer2.source.s
    public long d() {
        return this.h.d();
    }

    @Override // com.google.android.exoplayer2.source.l
    public long b(long j) {
        long b = this.g[0].b(j);
        int i = 1;
        while (true) {
            l[] lVarArr = this.g;
            if (i >= lVarArr.length) {
                return b;
            }
            if (lVarArr[i].b(b) == b) {
                i++;
            } else {
                throw new IllegalStateException("Unexpected child seekToUs result.");
            }
        }
    }

    @Override // com.google.android.exoplayer2.source.l
    public long a(long j, x xVar) {
        return this.g[0].a(j, xVar);
    }

    @Override // com.google.android.exoplayer2.source.l.a
    public void a(l lVar) {
        this.d.remove(lVar);
        if (this.d.isEmpty()) {
            int i = 0;
            for (l lVar2 : this.a) {
                i += lVar2.b().b;
            }
            TrackGroup[] trackGroupArr = new TrackGroup[i];
            l[] lVarArr = this.a;
            int length = lVarArr.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                TrackGroupArray b = lVarArr[i2].b();
                int i4 = b.b;
                int i5 = i3;
                int i6 = 0;
                while (i6 < i4) {
                    trackGroupArr[i5] = b.a(i6);
                    i6++;
                    i5++;
                }
                i2++;
                i3 = i5;
            }
            this.f = new TrackGroupArray(trackGroupArr);
            this.e.a((l) this);
        }
    }

    /* renamed from: b */
    public void a(l lVar) {
        this.e.a((l.a) this);
    }
}
