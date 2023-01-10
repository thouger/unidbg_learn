package com.google.android.exoplayer2.source.dash.a;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.dash.a.j;
import com.google.android.exoplayer2.source.dash.d;
import java.util.Collections;
import java.util.List;

/* compiled from: Representation */
public abstract class i {
    public final String a;
    public final long b;
    public final Format c;
    public final String d;
    public final long e;
    public final List<d> f;
    private final h g;

    public abstract h d();

    public abstract d e();

    public abstract String f();

    public static i a(String str, long j, Format format, String str2, j jVar, List<d> list) {
        return a(str, j, format, str2, jVar, list, null);
    }

    public static i a(String str, long j, Format format, String str2, j jVar, List<d> list, String str3) {
        if (jVar instanceof j.e) {
            return new b(str, j, format, str2, (j.e) jVar, list, str3, -1);
        }
        if (jVar instanceof j.a) {
            return new a(str, j, format, str2, (j.a) jVar, list);
        }
        throw new IllegalArgumentException("segmentBase must be of type SingleSegmentBase or MultiSegmentBase");
    }

    private i(String str, long j, Format format, String str2, j jVar, List<d> list) {
        List<d> list2;
        this.a = str;
        this.b = j;
        this.c = format;
        this.d = str2;
        if (list == null) {
            list2 = Collections.emptyList();
        } else {
            list2 = Collections.unmodifiableList(list);
        }
        this.f = list2;
        this.g = jVar.a(this);
        this.e = jVar.a();
    }

    public h c() {
        return this.g;
    }

    /* compiled from: Representation */
    public static class b extends i {
        public final Uri g;
        public final long h;
        private final String i;
        private final h j;
        private final k k;

        public b(String str, long j, Format format, String str2, j.e eVar, List<d> list, String str3, long j2) {
            super(str, j, format, str2, eVar, list);
            String str4;
            this.g = Uri.parse(str2);
            this.j = eVar.b();
            k kVar = null;
            if (str3 != null) {
                str4 = str3;
            } else if (str != null) {
                str4 = str + "." + format.a + "." + j;
            } else {
                str4 = null;
            }
            this.i = str4;
            this.h = j2;
            this.k = this.j == null ? new k(new h(null, 0, j2)) : kVar;
        }

        @Override // com.google.android.exoplayer2.source.dash.a.i
        public h d() {
            return this.j;
        }

        @Override // com.google.android.exoplayer2.source.dash.a.i
        public d e() {
            return this.k;
        }

        @Override // com.google.android.exoplayer2.source.dash.a.i
        public String f() {
            return this.i;
        }
    }

    /* compiled from: Representation */
    public static class a extends i implements d {
        private final j.a g;

        @Override // com.google.android.exoplayer2.source.dash.a.i
        public h d() {
            return null;
        }

        @Override // com.google.android.exoplayer2.source.dash.a.i
        public d e() {
            return this;
        }

        @Override // com.google.android.exoplayer2.source.dash.a.i
        public String f() {
            return null;
        }

        public a(String str, long j, Format format, String str2, j.a aVar, List<d> list) {
            super(str, j, format, str2, aVar, list);
            this.g = aVar;
        }

        @Override // com.google.android.exoplayer2.source.dash.d
        public h b(long j) {
            return this.g.a(this, j);
        }

        @Override // com.google.android.exoplayer2.source.dash.d
        public long a(long j, long j2) {
            return this.g.a(j, j2);
        }

        @Override // com.google.android.exoplayer2.source.dash.d
        public long a(long j) {
            return this.g.a(j);
        }

        @Override // com.google.android.exoplayer2.source.dash.d
        public long b(long j, long j2) {
            return this.g.b(j, j2);
        }

        @Override // com.google.android.exoplayer2.source.dash.d
        public long a() {
            return this.g.b();
        }

        @Override // com.google.android.exoplayer2.source.dash.d
        public int c(long j) {
            return this.g.b(j);
        }

        @Override // com.google.android.exoplayer2.source.dash.d
        public boolean b() {
            return this.g.c();
        }
    }
}
