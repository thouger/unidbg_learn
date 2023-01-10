package com.google.android.exoplayer2.text.e;

import com.google.android.exoplayer2.text.a;
import com.google.android.exoplayer2.text.d;
import com.google.android.exoplayer2.util.z;
import java.util.Collections;
import java.util.List;

/* compiled from: SubripSubtitle */
final class b implements d {
    private final a[] a;
    private final long[] b;

    public b(a[] aVarArr, long[] jArr) {
        this.a = aVarArr;
        this.b = jArr;
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
        boolean z = true;
        com.google.android.exoplayer2.util.a.a(i >= 0);
        if (i >= this.b.length) {
            z = false;
        }
        com.google.android.exoplayer2.util.a.a(z);
        return this.b[i];
    }

    @Override // com.google.android.exoplayer2.text.d
    public List<a> b(long j) {
        int a = z.a(this.b, j, true, false);
        if (a != -1) {
            a[] aVarArr = this.a;
            if (aVarArr[a] != null) {
                return Collections.singletonList(aVarArr[a]);
            }
        }
        return Collections.emptyList();
    }
}
