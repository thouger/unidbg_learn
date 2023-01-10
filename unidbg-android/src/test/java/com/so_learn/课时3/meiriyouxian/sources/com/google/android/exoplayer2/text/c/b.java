package com.google.android.exoplayer2.text.c;

import com.google.android.exoplayer2.text.a;
import com.google.android.exoplayer2.text.d;
import java.util.List;

/* compiled from: PgsSubtitle */
final class b implements d {
    private final List<a> a;

    @Override // com.google.android.exoplayer2.text.d
    public int a(long j) {
        return -1;
    }

    @Override // com.google.android.exoplayer2.text.d
    public long a(int i) {
        return 0;
    }

    @Override // com.google.android.exoplayer2.text.d
    public int b() {
        return 1;
    }

    public b(List<a> list) {
        this.a = list;
    }

    @Override // com.google.android.exoplayer2.text.d
    public List<a> b(long j) {
        return this.a;
    }
}
