package com.google.android.exoplayer2.text.a;

import android.text.Layout;
import com.google.android.exoplayer2.text.a;

/* compiled from: Cea708Cue */
final class b extends a implements Comparable<b> {
    public final int o;

    public b(CharSequence charSequence, Layout.Alignment alignment, float f, int i, int i2, float f2, int i3, float f3, boolean z, int i4, int i5) {
        super(charSequence, alignment, f, i, i2, f2, i3, f3, z, i4);
        this.o = i5;
    }

    /* renamed from: a */
    public int compareTo(b bVar) {
        int i = bVar.o;
        int i2 = this.o;
        if (i < i2) {
            return -1;
        }
        return i > i2 ? 1 : 0;
    }
}
