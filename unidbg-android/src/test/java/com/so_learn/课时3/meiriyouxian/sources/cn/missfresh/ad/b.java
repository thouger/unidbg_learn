package cn.missfresh.ad;

import android.content.Context;
import cn.missfresh.ad.b.c;
import cn.missfresh.ad.data.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.HashSet;
import java.util.Set;

/* compiled from: MFADConfigure */
public class b {
    private Context a;
    private boolean b;
    private Set<Integer> c = new HashSet();
    private int d;
    private final String e = "Android";
    private String f;
    private String g;
    private String h;
    private String i;
    private int j;
    private int k;

    public b() {
        AppMethodBeat.i(5799, false);
        AppMethodBeat.o(5799);
    }

    public void a(a aVar) {
        AppMethodBeat.i(5802, false);
        c.a(aVar, "MFADConfig should not be null\uff01");
        this.f = aVar.a();
        this.i = aVar.b();
        this.a = aVar.e();
        this.d = aVar.f();
        this.c = aVar.d();
        this.b = aVar.c();
        a(aVar.e());
        AppMethodBeat.o(5802);
    }

    /* access modifiers changed from: package-private */
    public void a(Context context) {
        AppMethodBeat.i(5806, false);
        this.g = "0.0.2";
        this.h = cn.missfresh.ad.b.b.b(context);
        this.k = cn.missfresh.ad.b.b.d(context);
        this.j = cn.missfresh.ad.b.b.c(context);
        AppMethodBeat.o(5806);
    }

    public Context a() {
        return this.a;
    }
}
