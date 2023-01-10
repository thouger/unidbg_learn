package com.google.android.exoplayer2.text.b;

import com.google.android.exoplayer2.text.a;
import com.google.android.exoplayer2.text.d;
import java.util.List;

/* compiled from: DvbSubtitle */
final class c implements d {
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

    public c(List<a> list) {
        this.a = list;
    }

    @Override // com.google.android.exoplayer2.text.d
    public List<a> b(long j) {
        return this.a;
    }
}
