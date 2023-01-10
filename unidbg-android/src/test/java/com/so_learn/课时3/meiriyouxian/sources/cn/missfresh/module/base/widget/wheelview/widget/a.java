package cn.missfresh.module.base.widget.wheelview.widget;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ItemsRange */
public class a {
    private int a;
    private int b;

    public a() {
        this(0, 0);
    }

    public a(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        AppMethodBeat.i(24388, false);
        int a = (a() + c()) - 1;
        AppMethodBeat.o(24388);
        return a;
    }

    public int c() {
        return this.b;
    }

    public boolean a(int i) {
        boolean z = false;
        AppMethodBeat.i(24389, false);
        if (i >= a() && i <= b()) {
            z = true;
        }
        AppMethodBeat.o(24389);
        return z;
    }
}
