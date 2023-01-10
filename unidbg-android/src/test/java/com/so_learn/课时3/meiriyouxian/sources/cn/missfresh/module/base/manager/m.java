package cn.missfresh.module.base.manager;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: StatistiscMemoryCacheManager */
public class m {
    private static m a = new m();
    private String b;
    private String c;
    private String d;
    private String e;

    static {
        AppMethodBeat.i(14546, false);
        AppMethodBeat.o(14546);
    }

    public static m a() {
        return a;
    }

    private m() {
    }

    public void a(String str) {
        this.b = str;
    }

    public void b(String str) {
        this.c = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public void d(String str) {
        this.e = str;
    }

    public void b() {
        AppMethodBeat.i(14545, false);
        a("");
        b("");
        c("");
        d("");
        AppMethodBeat.o(14545);
    }
}
