package cn.missfresh.module.base.datastatistics.a;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.LinkedList;
import java.util.List;

/* compiled from: ScmConfig */
public class d {
    private boolean a;
    private String b;
    private a c;
    private cn.missfresh.module.base.datastatistics.a.b.a d;
    private List<cn.missfresh.module.base.datastatistics.a.b.a> e;

    private d() {
        this.a = false;
        this.b = "unknown";
        this.c = null;
        this.d = null;
        this.e = null;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public a b() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public String c() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public cn.missfresh.module.base.datastatistics.a.b.a d() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public List<cn.missfresh.module.base.datastatistics.a.b.a> e() {
        return this.e;
    }

    /* compiled from: ScmConfig */
    public static class a {
        d a = new d();

        public a() {
            AppMethodBeat.i(12767, false);
            AppMethodBeat.o(12767);
        }

        public a a(boolean z) {
            AppMethodBeat.i(12770, false);
            this.a.a = z;
            AppMethodBeat.o(12770);
            return this;
        }

        public a a(a aVar) {
            AppMethodBeat.i(12772, false);
            this.a.c = aVar;
            AppMethodBeat.o(12772);
            return this;
        }

        public a a(String str) {
            AppMethodBeat.i(12774, false);
            this.a.b = str;
            AppMethodBeat.o(12774);
            return this;
        }

        public a a(cn.missfresh.module.base.datastatistics.a.b.a aVar) {
            AppMethodBeat.i(12775, false);
            this.a.d = aVar;
            AppMethodBeat.o(12775);
            return this;
        }

        public a b(cn.missfresh.module.base.datastatistics.a.b.a aVar) {
            AppMethodBeat.i(12776, false);
            if (aVar != null) {
                if (this.a.e == null) {
                    this.a.e = new LinkedList();
                }
                this.a.e.add(aVar);
            }
            AppMethodBeat.o(12776);
            return this;
        }

        public d a() {
            return this.a;
        }
    }
}
