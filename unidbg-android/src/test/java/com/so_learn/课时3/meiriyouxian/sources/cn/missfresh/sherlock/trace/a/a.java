package cn.missfresh.sherlock.trace.a;

import cn.missfresh.sherlock.config.Config;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: TraceConfig */
public class a {
    public boolean a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public String g;
    public Set<String> h;

    /* compiled from: TraceConfig */
    public static class b {
        private a a = new a();

        public b a(boolean z) {
            this.a.a = z;
            return this;
        }

        public b b(boolean z) {
            this.a.b = z;
            return this;
        }

        public b c(boolean z) {
            this.a.d = z;
            return this;
        }

        public b d(boolean z) {
            this.a.c = z;
            return this;
        }

        public b e(boolean z) {
            this.a.e = z;
            return this;
        }

        public b f(boolean z) {
            this.a.f = z;
            return this;
        }

        public b a(String str) {
            this.a.g = str;
            return this;
        }

        public a a() {
            return this.a;
        }
    }

    private static Set<String> a(String str) {
        if (str != null) {
            return new HashSet(Arrays.asList(str.split(";")));
        }
        return new HashSet(Collections.emptyList());
    }

    public boolean a() {
        return this.a;
    }

    public boolean b() {
        return this.e;
    }

    public boolean c() {
        return this.b;
    }

    public boolean d() {
        return this.c;
    }

    public boolean e() {
        return this.d;
    }

    public Set<String> f() {
        if (this.h == null) {
            this.h = a(this.g);
        }
        return this.h;
    }

    public int g() {
        return Config.getInstance().slowFunctionTime;
    }

    public int h() {
        return 10000;
    }

    public int i() {
        return 4000;
    }

    public int j() {
        return 42;
    }

    public int k() {
        return 24;
    }

    public int l() {
        return 9;
    }

    public int m() {
        return 3;
    }

    public String toString() {
        return " \n# TraceConfig\n* isDebug:\t" + this.e + "\n* isDevEnv:\t" + this.f + "\n* defaultFpsEnable:\t" + this.a + "\n* defaultMethodTraceEnable:\t" + this.b + "\n* defaultStartupEnable:\t" + this.c + "\n* defaultAnrEnable:\t" + this.d + "\n* splashActivities:\t" + this.g + "\n";
    }

    private a() {
    }
}
