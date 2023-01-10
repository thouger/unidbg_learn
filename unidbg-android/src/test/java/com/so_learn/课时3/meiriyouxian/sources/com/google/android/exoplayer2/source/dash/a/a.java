package com.google.android.exoplayer2.source.dash.a;

import java.util.Collections;
import java.util.List;

/* compiled from: AdaptationSet */
public class a {
    public final int a;
    public final int b;
    public final List<i> c;
    public final List<d> d;
    public final List<d> e;

    public a(int i, int i2, List<i> list, List<d> list2, List<d> list3) {
        List<d> list4;
        List<d> list5;
        this.a = i;
        this.b = i2;
        this.c = Collections.unmodifiableList(list);
        if (list2 == null) {
            list4 = Collections.emptyList();
        } else {
            list4 = Collections.unmodifiableList(list2);
        }
        this.d = list4;
        if (list3 == null) {
            list5 = Collections.emptyList();
        } else {
            list5 = Collections.unmodifiableList(list3);
        }
        this.e = list5;
    }
}
