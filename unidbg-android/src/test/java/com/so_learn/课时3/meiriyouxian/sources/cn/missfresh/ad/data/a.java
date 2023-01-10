package cn.missfresh.ad.data;

import android.content.Context;
import cn.missfresh.ad.b.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.HashSet;
import java.util.Set;

/* compiled from: MFADConfig */
public class a {
    private boolean a;
    private HashSet<Integer> b;
    private int c;
    private Context d;
    private String e;
    private String f;

    private a() {
        AppMethodBeat.i(5923, false);
        this.b = new HashSet<>();
        AppMethodBeat.o(5923);
    }

    public String a() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        this.e = str;
    }

    public String b() {
        return this.f;
    }

    public boolean c() {
        return this.a;
    }

    public Set<Integer> d() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public void a(Context context) {
        AppMethodBeat.i(5937, false);
        c.a(context, "Context should not be null!");
        this.d = context.getApplicationContext();
        AppMethodBeat.o(5937);
    }

    public Context e() {
        return this.d;
    }

    public int f() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    /* compiled from: MFADConfig */
    /* renamed from: cn.missfresh.ad.data.a$a  reason: collision with other inner class name */
    public static class C0014a {
        a a = new a();

        public C0014a() {
            AppMethodBeat.i(5905, false);
            AppMethodBeat.o(5905);
        }

        public C0014a a(String str) {
            AppMethodBeat.i(5906, false);
            this.a.a(str);
            AppMethodBeat.o(5906);
            return this;
        }

        public C0014a a(Context context) {
            AppMethodBeat.i(5913, false);
            this.a.a(context);
            AppMethodBeat.o(5913);
            return this;
        }

        public C0014a a(int i) {
            AppMethodBeat.i(5916, false);
            if (i <= 0) {
                cn.missfresh.ad.b.a.b("MFADConfig", "Expiration Time must be greater than zero\uff01");
                AppMethodBeat.o(5916);
                return this;
            }
            this.a.a(i);
            AppMethodBeat.o(5916);
            return this;
        }

        public a a() {
            AppMethodBeat.i(5919, false);
            c.a(this.a.a(), "AppID\u4e0d\u80fd\u4e3a\u7a7a");
            c.a(this.a.e(), "Context\u4e0d\u80fd\u4e3a\u7a7a");
            a aVar = this.a;
            AppMethodBeat.o(5919);
            return aVar;
        }
    }
}
