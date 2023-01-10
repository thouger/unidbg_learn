package cn.missfresh.pushlib;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: MFPushInformation */
public class c {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private boolean j;
    private boolean k;

    private c(a aVar) {
        AppMethodBeat.i(22120, false);
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        AppMethodBeat.o(22120);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.g;
    }

    public String h() {
        return this.h;
    }

    public String i() {
        return this.i;
    }

    public boolean j() {
        return this.j;
    }

    public boolean k() {
        return this.k;
    }

    /* compiled from: MFPushInformation */
    public static class a {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;
        private String i;
        private boolean j;
        private boolean k;
        private c l = new c(this);

        public a() {
            AppMethodBeat.i(22095, false);
            AppMethodBeat.o(22095);
        }

        public a a(String str) {
            AppMethodBeat.i(22097, false);
            this.l.a = str;
            AppMethodBeat.o(22097);
            return this;
        }

        public a b(String str) {
            AppMethodBeat.i(22098, false);
            this.l.b = str;
            AppMethodBeat.o(22098);
            return this;
        }

        public a c(String str) {
            AppMethodBeat.i(22101, false);
            this.l.d = str;
            AppMethodBeat.o(22101);
            return this;
        }

        public a d(String str) {
            AppMethodBeat.i(22103, false);
            this.l.e = str;
            AppMethodBeat.o(22103);
            return this;
        }

        public a e(String str) {
            AppMethodBeat.i(22104, false);
            this.l.f = str;
            AppMethodBeat.o(22104);
            return this;
        }

        public a f(String str) {
            AppMethodBeat.i(22105, false);
            this.l.g = str;
            AppMethodBeat.o(22105);
            return this;
        }

        public a g(String str) {
            AppMethodBeat.i(22107, false);
            this.l.h = str;
            AppMethodBeat.o(22107);
            return this;
        }

        public a h(String str) {
            AppMethodBeat.i(22109, false);
            this.l.i = str;
            AppMethodBeat.o(22109);
            return this;
        }

        public a a(boolean z) {
            AppMethodBeat.i(22111, false);
            this.l.j = z;
            AppMethodBeat.o(22111);
            return this;
        }

        public a b(boolean z) {
            AppMethodBeat.i(22112, false);
            this.l.k = z;
            AppMethodBeat.o(22112);
            return this;
        }

        public c a() {
            return this.l;
        }
    }
}
