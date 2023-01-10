package com.google.android.exoplayer2.source.a;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.h;
import com.google.android.exoplayer2.upstream.r;
import com.google.android.exoplayer2.util.a;
import java.util.List;
import java.util.Map;

/* compiled from: Chunk */
public abstract class d implements Loader.d {
    public final h c;
    public final int d;
    public final Format e;
    public final int f;
    public final Object g;
    public final long h;
    public final long i;
    protected final r j;

    public d(f fVar, h hVar, int i, Format format, int i2, Object obj, long j, long j2) {
        this.j = new r(fVar);
        this.c = (h) a.a(hVar);
        this.d = i;
        this.e = format;
        this.f = i2;
        this.g = obj;
        this.h = j;
        this.i = j2;
    }

    public final long d() {
        return this.i - this.h;
    }

    public final long e() {
        return this.j.e();
    }

    public final Uri f() {
        return this.j.f();
    }

    public final Map<String, List<String>> g() {
        return this.j.g();
    }
}
