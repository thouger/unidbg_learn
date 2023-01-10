package com.google.android.exoplayer2.source.smoothstreaming.manifest;

import android.net.Uri;
import android.util.TimeUtils;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.mp4.k;
import com.google.android.exoplayer2.offline.c;
import com.google.android.exoplayer2.util.y;
import com.google.android.exoplayer2.util.z;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/* compiled from: SsManifest */
public class a implements com.google.android.exoplayer2.offline.a<a> {
    public final int a;
    public final int b;
    public final int c;
    public final boolean d;
    public final C0094a e;
    public final b[] f;
    public final long g;
    public final long h;

    /* compiled from: SsManifest */
    /* renamed from: com.google.android.exoplayer2.source.smoothstreaming.manifest.a$a  reason: collision with other inner class name */
    public static class C0094a {
        public final UUID a;
        public final byte[] b;
        public final k[] c;

        public C0094a(UUID uuid, byte[] bArr, k[] kVarArr) {
            this.a = uuid;
            this.b = bArr;
            this.c = kVarArr;
        }
    }

    /* compiled from: SsManifest */
    public static class b {
        public final int a;
        public final String b;
        public final long c;
        public final String d;
        public final int e;
        public final int f;
        public final int g;
        public final int h;
        public final String i;
        public final Format[] j;
        public final int k;
        private final String l;
        private final String m;
        private final List<Long> n;
        private final long[] o;
        private final long p;

        public b(String str, String str2, int i, String str3, long j, String str4, int i2, int i3, int i4, int i5, String str5, Format[] formatArr, List<Long> list, long j2) {
            this(str, str2, i, str3, j, str4, i2, i3, i4, i5, str5, formatArr, list, z.a(list, (long) TimeUtils.NANOS_PER_MS, j), z.d(j2, TimeUtils.NANOS_PER_MS, j));
        }

        private b(String str, String str2, int i, String str3, long j, String str4, int i2, int i3, int i4, int i5, String str5, Format[] formatArr, List<Long> list, long[] jArr, long j2) {
            this.l = str;
            this.m = str2;
            this.a = i;
            this.b = str3;
            this.c = j;
            this.d = str4;
            this.e = i2;
            this.f = i3;
            this.g = i4;
            this.h = i5;
            this.i = str5;
            this.j = formatArr;
            this.n = list;
            this.o = jArr;
            this.p = j2;
            this.k = list.size();
        }

        public b a(Format[] formatArr) {
            return new b(this.l, this.m, this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, formatArr, this.n, this.o, this.p);
        }

        public int a(long j) {
            return z.a(this.o, j, true, true);
        }

        public long a(int i) {
            return this.o[i];
        }

        public long b(int i) {
            if (i == this.k - 1) {
                return this.p;
            }
            long[] jArr = this.o;
            return jArr[i + 1] - jArr[i];
        }

        public Uri a(int i, int i2) {
            boolean z = true;
            com.google.android.exoplayer2.util.a.b(this.j != null);
            com.google.android.exoplayer2.util.a.b(this.n != null);
            if (i2 >= this.n.size()) {
                z = false;
            }
            com.google.android.exoplayer2.util.a.b(z);
            String num = Integer.toString(this.j[i].c);
            String l = this.n.get(i2).toString();
            return y.a(this.l, this.m.replace("{bitrate}", num).replace("{Bitrate}", num).replace("{start time}", l).replace("{start_time}", l));
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public a(int i, int i2, long j, long j2, long j3, int i3, boolean z, C0094a aVar, b[] bVarArr) {
        this(i, i2, j2 == 0 ? -9223372036854775807L : z.d(j2, TimeUtils.NANOS_PER_MS, j), j3 != 0 ? z.d(j3, TimeUtils.NANOS_PER_MS, j) : -9223372036854775807L, i3, z, aVar, bVarArr);
    }

    private a(int i, int i2, long j, long j2, int i3, boolean z, C0094a aVar, b[] bVarArr) {
        this.a = i;
        this.b = i2;
        this.g = j;
        this.h = j2;
        this.c = i3;
        this.d = z;
        this.e = aVar;
        this.f = bVarArr;
    }

    /* renamed from: b */
    public final a a(List<c> list) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        b bVar = null;
        int i = 0;
        while (i < arrayList.size()) {
            c cVar = (c) arrayList.get(i);
            b bVar2 = this.f[cVar.b];
            if (!(bVar2 == bVar || bVar == null)) {
                arrayList2.add(bVar.a((Format[]) arrayList3.toArray(new Format[0])));
                arrayList3.clear();
            }
            arrayList3.add(bVar2.j[cVar.c]);
            i++;
            bVar = bVar2;
        }
        if (bVar != null) {
            arrayList2.add(bVar.a((Format[]) arrayList3.toArray(new Format[0])));
        }
        return new a(this.a, this.b, this.g, this.h, this.c, this.d, this.e, (b[]) arrayList2.toArray(new b[0]));
    }
}
