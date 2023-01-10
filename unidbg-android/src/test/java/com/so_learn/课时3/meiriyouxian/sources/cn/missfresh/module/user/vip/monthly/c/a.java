package cn.missfresh.module.user.vip.monthly.c;

import android.content.Context;
import cn.missfresh.module.base.bean.AutoPayInfo;
import cn.missfresh.module.user.vip.monthly.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ContinueMouthlyPresenterImp */
public class a implements a.b, a.c, a.e {
    private a.d a;
    private a.a b = new cn.missfresh.module.user.vip.monthly.b.a();
    private Context c;

    public a(Context context, a.d dVar) {
        AppMethodBeat.i(10071, false);
        this.a = dVar;
        this.c = context;
        AppMethodBeat.o(10071);
    }

    public void a() {
        AppMethodBeat.i(10073, false);
        this.b.a(this);
        AppMethodBeat.o(10073);
    }

    public void b() {
        AppMethodBeat.i(10076, false);
        this.b.a(this);
        AppMethodBeat.o(10076);
    }

    public void a(AutoPayInfo autoPayInfo) {
        AppMethodBeat.i(10080, false);
        if (!(this.a == null || autoPayInfo == null || autoPayInfo.getData() == null)) {
            this.a.a(autoPayInfo);
        }
        AppMethodBeat.o(10080);
    }

    public void a(String str) {
        AppMethodBeat.i(10081, false);
        cn.missfresh.ui.a.a.a(str);
        AppMethodBeat.o(10081);
    }

    public void c() {
        AppMethodBeat.i(10083, false);
        a.d dVar = this.a;
        if (dVar != null) {
            dVar.a();
        }
        AppMethodBeat.o(10083);
    }

    public void b(String str) {
        AppMethodBeat.i(10085, false);
        if (this.a != null) {
            cn.missfresh.ui.a.a.a(str);
            this.a.b();
        }
        AppMethodBeat.o(10085);
    }
}
