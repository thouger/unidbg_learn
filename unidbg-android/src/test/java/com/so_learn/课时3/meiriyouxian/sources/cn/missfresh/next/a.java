package cn.missfresh.next;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/* compiled from: Project */
public class a extends e {
    private static ExecutorService h = b.a();
    private Runnable a;
    private boolean d;
    private boolean e;
    private int f;
    private List<e> g;

    static {
        AppMethodBeat.i(1594, false);
        AppMethodBeat.o(1594);
    }

    public a() {
        this("SyncProject", true);
    }

    public a(String str, boolean z) {
        super(str);
        AppMethodBeat.i(1565, false);
        this.e = true;
        this.g = new ArrayList();
        this.d = z;
        AppMethodBeat.o(1565);
    }

    public synchronized void b() {
        AppMethodBeat.i(1568, false);
        if (this.g.isEmpty()) {
            AppMethodBeat.o(1568);
            return;
        }
        if (this.a == null) {
            this.a = new 1(this);
        }
        if (this.d) {
            this.a.run();
        } else {
            h.execute(this.a);
        }
        AppMethodBeat.o(1568);
    }

    public void a() {
        AppMethodBeat.i(1571, false);
        c.a("Project", "Project [" + this.b + "] Start");
        long currentTimeMillis = System.currentTimeMillis();
        for (e eVar : this.g) {
            eVar.a(this.c);
            eVar.b();
        }
        a(System.currentTimeMillis() - currentTimeMillis);
        if (this.d) {
            d.a().c();
        } else if (this.e) {
            d.a().d();
        }
        AppMethodBeat.o(1571);
    }

    public void a(e eVar) {
        AppMethodBeat.i(1575, false);
        if (eVar == null) {
            AppMethodBeat.o(1575);
            return;
        }
        this.g.add(eVar);
        AppMethodBeat.o(1575);
    }

    public boolean c() {
        return this.d;
    }

    public int d() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    private void a(long j) {
        AppMethodBeat.i(1591, false);
        if (this.c != null) {
            this.c.b(this.b, j);
        }
        AppMethodBeat.o(1591);
    }
}
