package com.google.android.exoplayer2.text.h;

import android.text.SpannableStringBuilder;
import com.google.android.exoplayer2.text.d;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.z;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: WebvttSubtitle */
/* access modifiers changed from: package-private */
public final class i implements d {
    private final List<e> a;
    private final int b;
    private final long[] c = new long[(this.b * 2)];
    private final long[] d;

    public i(List<e> list) {
        this.a = list;
        this.b = list.size();
        for (int i = 0; i < this.b; i++) {
            e eVar = list.get(i);
            int i2 = i * 2;
            this.c[i2] = eVar.o;
            this.c[i2 + 1] = eVar.p;
        }
        long[] jArr = this.c;
        this.d = Arrays.copyOf(jArr, jArr.length);
        Arrays.sort(this.d);
    }

    @Override // com.google.android.exoplayer2.text.d
    public int a(long j) {
        int b = z.b(this.d, j, false, false);
        if (b < this.d.length) {
            return b;
        }
        return -1;
    }

    @Override // com.google.android.exoplayer2.text.d
    public int b() {
        return this.d.length;
    }

    @Override // com.google.android.exoplayer2.text.d
    public long a(int i) {
        boolean z = true;
        a.a(i >= 0);
        if (i >= this.d.length) {
            z = false;
        }
        a.a(z);
        return this.d[i];
    }

    @Override // com.google.android.exoplayer2.text.d
    public List<com.google.android.exoplayer2.text.a> b(long j) {
        SpannableStringBuilder spannableStringBuilder = null;
        e eVar = null;
        ArrayList arrayList = null;
        for (int i = 0; i < this.b; i++) {
            long[] jArr = this.c;
            int i2 = i * 2;
            if (jArr[i2] <= j && j < jArr[i2 + 1]) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                e eVar2 = this.a.get(i);
                if (!eVar2.a()) {
                    arrayList.add(eVar2);
                } else if (eVar == null) {
                    eVar = eVar2;
                } else if (spannableStringBuilder == null) {
                    spannableStringBuilder = new SpannableStringBuilder();
                    spannableStringBuilder.append(eVar.a).append((CharSequence) "\n").append(eVar2.a);
                } else {
                    spannableStringBuilder.append((CharSequence) "\n").append(eVar2.a);
                }
            }
        }
        if (spannableStringBuilder != null) {
            arrayList.add(new e(spannableStringBuilder));
        } else if (eVar != null) {
            arrayList.add(eVar);
        }
        if (arrayList != null) {
            return arrayList;
        }
        return Collections.emptyList();
    }
}
