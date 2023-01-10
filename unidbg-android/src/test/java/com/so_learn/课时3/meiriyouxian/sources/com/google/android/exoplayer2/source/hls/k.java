package com.google.android.exoplayer2.source.hls;

import com.google.android.exoplayer2.b.e;
import com.google.android.exoplayer2.source.r;
import com.google.android.exoplayer2.util.a;
import java.io.IOException;

/* compiled from: HlsSampleStream */
/* access modifiers changed from: package-private */
public final class k implements r {
    private final int a;
    private final l b;
    private int c = -1;

    public k(l lVar, int i) {
        this.b = lVar;
        this.a = i;
    }

    public void a() {
        a.a(this.c == -1);
        this.c = this.b.a(this.a);
    }

    public void d() {
        if (this.c != -1) {
            this.b.b(this.a);
            this.c = -1;
        }
    }

    @Override // com.google.android.exoplayer2.source.r
    public boolean b() {
        return this.c == -3 || (e() && this.b.c(this.c));
    }

    @Override // com.google.android.exoplayer2.source.r
    public void c() throws IOException {
        if (this.c != -2) {
            this.b.i();
            return;
        }
        throw new SampleQueueMappingException(this.b.f().a(this.a).a(0).g);
    }

    @Override // com.google.android.exoplayer2.source.r
    public int a(com.google.android.exoplayer2.k kVar, e eVar, boolean z) {
        if (this.c == -3) {
            eVar.b(4);
            return -4;
        } else if (e()) {
            return this.b.a(this.c, kVar, eVar, z);
        } else {
            return -3;
        }
    }

    @Override // com.google.android.exoplayer2.source.r
    public int b_(long j) {
        if (e()) {
            return this.b.a(this.c, j);
        }
        return 0;
    }

    private boolean e() {
        int i = this.c;
        return (i == -1 || i == -3 || i == -2) ? false : true;
    }
}
