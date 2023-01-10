package com.google.android.exoplayer2.text.g;

import com.google.android.exoplayer2.text.a;
import com.google.android.exoplayer2.text.d;
import java.util.Collections;
import java.util.List;

/* compiled from: Tx3gSubtitle */
final class b implements d {
    public static final b a = new b();
    private final List<a> b;

    @Override // com.google.android.exoplayer2.text.d
    public int a(long j) {
        return j < 0 ? 0 : -1;
    }

    @Override // com.google.android.exoplayer2.text.d
    public int b() {
        return 1;
    }

    public b(a aVar) {
        this.b = Collections.singletonList(aVar);
    }

    private b() {
        this.b = Collections.emptyList();
    }

    @Override // com.google.android.exoplayer2.text.d
    public long a(int i) {
        com.google.android.exoplayer2.util.a.a(i == 0);
        return 0;
    }

    @Override // com.google.android.exoplayer2.text.d
    public List<a> b(long j) {
        return j >= 0 ? this.b : Collections.emptyList();
    }
}
