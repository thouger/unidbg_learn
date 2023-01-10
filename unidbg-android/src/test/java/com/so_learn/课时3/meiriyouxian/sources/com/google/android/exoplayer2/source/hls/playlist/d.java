package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.offline.c;
import java.util.Collections;
import java.util.List;

/* compiled from: HlsMediaPlaylist */
public final class d extends e {
    public final int a;
    public final long b;
    public final long c;
    public final boolean d;
    public final int e;
    public final long f;
    public final int g;
    public final long h;
    public final boolean i;
    public final boolean j;
    public final DrmInitData k;
    public final List<a> l;
    public final long m;

    /* renamed from: b */
    public d a(List<c> list) {
        return this;
    }

    /* compiled from: HlsMediaPlaylist */
    public static final class a implements Comparable<Long> {
        public final String a;
        public final a b;
        public final long c;
        public final String d;
        public final int e;
        public final long f;
        public final DrmInitData g;
        public final String h;
        public final String i;
        public final long j;
        public final long k;
        public final boolean l;

        public a(String str, long j, long j2) {
            this(str, null, "", 0, -1, -9223372036854775807L, null, null, null, j, j2, false);
        }

        public a(String str, a aVar, String str2, long j, int i, long j2, DrmInitData drmInitData, String str3, String str4, long j3, long j4, boolean z) {
            this.a = str;
            this.b = aVar;
            this.d = str2;
            this.c = j;
            this.e = i;
            this.f = j2;
            this.g = drmInitData;
            this.h = str3;
            this.i = str4;
            this.j = j3;
            this.k = j4;
            this.l = z;
        }

        /* renamed from: a */
        public int compareTo(Long l) {
            if (this.f > l.longValue()) {
                return 1;
            }
            return this.f < l.longValue() ? -1 : 0;
        }
    }

    public d(int i, String str, List<String> list, long j, long j2, boolean z, int i2, long j3, int i3, long j4, boolean z2, boolean z3, boolean z4, DrmInitData drmInitData, List<a> list2) {
        super(str, list, z2);
        this.a = i;
        this.c = j2;
        this.d = z;
        this.e = i2;
        this.f = j3;
        this.g = i3;
        this.h = j4;
        this.i = z3;
        this.j = z4;
        this.k = drmInitData;
        this.l = Collections.unmodifiableList(list2);
        if (!list2.isEmpty()) {
            a aVar = list2.get(list2.size() - 1);
            this.m = aVar.f + aVar.c;
        } else {
            this.m = 0;
        }
        this.b = j == -9223372036854775807L ? -9223372036854775807L : j >= 0 ? j : this.m + j;
    }

    public boolean a(d dVar) {
        if (dVar == null) {
            return true;
        }
        long j = this.f;
        long j2 = dVar.f;
        if (j > j2) {
            return true;
        }
        if (j < j2) {
            return false;
        }
        int size = this.l.size();
        int size2 = dVar.l.size();
        if (size <= size2) {
            return size == size2 && this.i && !dVar.i;
        }
        return true;
    }

    public long a() {
        return this.c + this.m;
    }

    public d a(long j, int i) {
        return new d(this.a, this.n, this.o, this.b, j, true, i, this.f, this.g, this.h, this.p, this.i, this.j, this.k, this.l);
    }

    public d b() {
        if (this.i) {
            return this;
        }
        return new d(this.a, this.n, this.o, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.p, true, this.j, this.k, this.l);
    }
}
