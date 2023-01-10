package com.google.android.exoplayer2.text.a;

import com.google.android.exoplayer2.text.a;
import com.google.android.exoplayer2.text.d;
import java.util.Collections;
import java.util.List;

/* compiled from: CeaSubtitle */
final class f implements d {
    private final List<a> a;

    @Override // com.google.android.exoplayer2.text.d
    public int a(long j) {
        return j < 0 ? 0 : -1;
    }

    @Override // com.google.android.exoplayer2.text.d
    public int b() {
        return 1;
    }

    public f(List<a> list) {
        this.a = list;
    }

    @Override // com.google.android.exoplayer2.text.d
    public long a(int i) {
        com.google.android.exoplayer2.util.a.a(i == 0);
        return 0;
    }

    @Override // com.google.android.exoplayer2.text.d
    public List<a> b(long j) {
        return j >= 0 ? this.a : Collections.emptyList();
    }
}
