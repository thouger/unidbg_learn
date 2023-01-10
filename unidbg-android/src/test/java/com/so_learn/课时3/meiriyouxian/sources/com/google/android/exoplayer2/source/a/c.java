package com.google.android.exoplayer2.source.a;

import com.google.android.exoplayer2.extractor.f;
import com.google.android.exoplayer2.source.a.e;
import com.google.android.exoplayer2.source.q;
import com.google.android.exoplayer2.util.i;

/* compiled from: BaseMediaChunkOutput */
public final class c implements e.b {
    private final int[] a;
    private final q[] b;

    public c(int[] iArr, q[] qVarArr) {
        this.a = iArr;
        this.b = qVarArr;
    }

    @Override // com.google.android.exoplayer2.source.a.e.b
    public com.google.android.exoplayer2.extractor.q a(int i, int i2) {
        int i3 = 0;
        while (true) {
            int[] iArr = this.a;
            if (i3 >= iArr.length) {
                i.d("BaseMediaChunkOutput", "Unmatched track of type: " + i2);
                return new f();
            } else if (i2 == iArr[i3]) {
                return this.b[i3];
            } else {
                i3++;
            }
        }
    }

    public int[] a() {
        int[] iArr = new int[this.b.length];
        int i = 0;
        while (true) {
            q[] qVarArr = this.b;
            if (i >= qVarArr.length) {
                return iArr;
            }
            if (qVarArr[i] != null) {
                iArr[i] = qVarArr[i].c();
            }
            i++;
        }
    }

    public void a(long j) {
        q[] qVarArr = this.b;
        for (q qVar : qVarArr) {
            if (qVar != null) {
                qVar.a(j);
            }
        }
    }
}
