package com.google.android.exoplayer2.text.f;

import com.google.android.exoplayer2.text.a;
import com.google.android.exoplayer2.text.d;
import com.google.android.exoplayer2.util.z;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: TtmlSubtitle */
/* access modifiers changed from: package-private */
public final class f implements d {
    private final b a;
    private final long[] b;
    private final Map<String, e> c;
    private final Map<String, c> d;
    private final Map<String, String> e;

    public f(b bVar, Map<String, e> map, Map<String, c> map2, Map<String, String> map3) {
        this.a = bVar;
        this.d = map2;
        this.e = map3;
        this.c = map != null ? Collections.unmodifiableMap(map) : Collections.emptyMap();
        this.b = bVar.b();
    }

    @Override // com.google.android.exoplayer2.text.d
    public int a(long j) {
        int b = z.b(this.b, j, false, false);
        if (b < this.b.length) {
            return b;
        }
        return -1;
    }

    @Override // com.google.android.exoplayer2.text.d
    public int b() {
        return this.b.length;
    }

    @Override // com.google.android.exoplayer2.text.d
    public long a(int i) {
        return this.b[i];
    }

    @Override // com.google.android.exoplayer2.text.d
    public List<a> b(long j) {
        return this.a.a(j, this.c, this.d, this.e);
    }
}
