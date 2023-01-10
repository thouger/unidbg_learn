package cn.missfresh.module.base.datastatistics.a;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;
import java.util.Map;

/* compiled from: ScmEventHandler */
public class f {
    private c a;
    private List<cn.missfresh.module.base.datastatistics.a.b.a> b;

    /* compiled from: ScmEventHandler */
    public interface a {
        void a(Map<String, String> map);
    }

    private boolean c() {
        return false;
    }

    private f() {
        AppMethodBeat.i(12797, false);
        this.a = new c();
        if (e.a() != null) {
            this.b = e.a().e();
        }
        AppMethodBeat.o(12797);
    }

    public static f a() {
        AppMethodBeat.i(12799, false);
        f fVar = b.a;
        AppMethodBeat.o(12799);
        return fVar;
    }

    /* compiled from: ScmEventHandler */
    /* access modifiers changed from: private */
    public static class b {
        private static final f a = new f();

        static {
            AppMethodBeat.i(12794, false);
            AppMethodBeat.o(12794);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(cn.missfresh.module.base.datastatistics.a.a.a aVar, a aVar2) {
        AppMethodBeat.i(12802, false);
        if (aVar == null || e.a() == null) {
            AppMethodBeat.o(12802);
            return;
        }
        cn.missfresh.module.base.datastatistics.a.b.a a2 = a(aVar);
        if (a2 == null) {
            a2 = e.a().d();
        }
        if (a2 != null) {
            a("event handled", aVar);
            a2.b(aVar);
            a(aVar, a2, aVar2);
            a("scm cache", this.a);
        }
        b(b());
        a(b(aVar));
        AppMethodBeat.o(12802);
    }

    private void a(cn.missfresh.module.base.datastatistics.a.a.a aVar, cn.missfresh.module.base.datastatistics.a.b.a aVar2, a aVar3) {
        int i = 12805;
        AppMethodBeat.i(12805, false);
        c cVar = this.a;
        if (cVar == null) {
            AppMethodBeat.o(12805);
            return;
        }
        Map<String, String> map = null;
        synchronized (cVar) {
            try {
                if (aVar2.a() && a(c(aVar), c())) {
                    a(c(aVar));
                }
                if (!(aVar3 == null || this.a == null)) {
                    map = this.a.c();
                }
                if (aVar2.b() && this.a != null) {
                    this.a.d();
                }
            } finally {
                AppMethodBeat.o(i);
            }
        }
        if (aVar3 != null) {
            aVar3.a(map);
        }
    }

    private cn.missfresh.module.base.datastatistics.a.b.a a(cn.missfresh.module.base.datastatistics.a.a.a aVar) {
        AppMethodBeat.i(12808, false);
        List<cn.missfresh.module.base.datastatistics.a.b.a> list = this.b;
        if (list != null) {
            for (cn.missfresh.module.base.datastatistics.a.b.a aVar2 : list) {
                if (aVar2 != null && aVar2.a(aVar)) {
                    AppMethodBeat.o(12808);
                    return aVar2;
                }
            }
        }
        AppMethodBeat.o(12808);
        return null;
    }

    private h b(cn.missfresh.module.base.datastatistics.a.a.a aVar) {
        AppMethodBeat.i(12810, false);
        h hVar = new h();
        hVar.c(aVar.b);
        hVar.d(aVar.c);
        hVar.a(aVar.a);
        hVar.b(aVar.d);
        AppMethodBeat.o(12810);
        return hVar;
    }

    private b c(cn.missfresh.module.base.datastatistics.a.a.a aVar) {
        AppMethodBeat.i(12813, false);
        h b2 = b(aVar);
        b bVar = new b();
        bVar.a(b2);
        bVar.a(aVar.e);
        AppMethodBeat.o(12813);
        return bVar;
    }

    private void b(h hVar) {
        AppMethodBeat.i(12815, false);
        c cVar = this.a;
        if (cVar != null) {
            cVar.b(hVar);
        }
        AppMethodBeat.o(12815);
    }

    public void a(h hVar) {
        AppMethodBeat.i(12818, false);
        c cVar = this.a;
        if (cVar != null) {
            cVar.a(hVar);
        }
        AppMethodBeat.o(12818);
    }

    private void a(b bVar) {
        AppMethodBeat.i(12821, false);
        c cVar = this.a;
        if (cVar != null) {
            cVar.a(bVar);
        }
        AppMethodBeat.o(12821);
    }

    private boolean a(b bVar, boolean z) {
        AppMethodBeat.i(12824, false);
        if (bVar == null) {
            AppMethodBeat.o(12824);
            return false;
        } else if (z) {
            AppMethodBeat.o(12824);
            return true;
        } else if (!bVar.equals(d())) {
            AppMethodBeat.o(12824);
            return true;
        } else {
            AppMethodBeat.o(12824);
            return false;
        }
    }

    public h b() {
        AppMethodBeat.i(12826, false);
        c cVar = this.a;
        h a2 = cVar != null ? cVar.a() : null;
        AppMethodBeat.o(12826);
        return a2;
    }

    private b d() {
        AppMethodBeat.i(12828, false);
        c cVar = this.a;
        b bVar = (cVar == null || cVar.b() == null || this.a.b().isEmpty()) ? null : this.a.b().get(0);
        AppMethodBeat.o(12828);
        return bVar;
    }

    private void a(String str, Object obj) {
        AppMethodBeat.i(12830, false);
        a b2 = (e.a() == null || !e.a().a()) ? null : e.a().b();
        if (b2 != null) {
            b2.a("SCM", str + ", " + obj);
        }
        AppMethodBeat.o(12830);
    }
}
