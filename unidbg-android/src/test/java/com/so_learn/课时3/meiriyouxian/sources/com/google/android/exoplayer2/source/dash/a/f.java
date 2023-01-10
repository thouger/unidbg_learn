package com.google.android.exoplayer2.source.dash.a;

import java.util.Collections;
import java.util.List;

/* compiled from: Period */
public class f {
    public final String a;
    public final long b;
    public final List<a> c;
    public final List<e> d;

    public f(String str, long j, List<a> list, List<e> list2) {
        this.a = str;
        this.b = j;
        this.c = Collections.unmodifiableList(list);
        this.d = Collections.unmodifiableList(list2);
    }

    public int a(int i) {
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.c.get(i2).b == i) {
                return i2;
            }
        }
        return -1;
    }
}
