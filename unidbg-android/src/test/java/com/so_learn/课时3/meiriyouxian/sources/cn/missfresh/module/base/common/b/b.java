package cn.missfresh.module.base.common.b;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import cn.missfresh.module.base.common.config.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ApplicationDelegateSimple */
public class b implements d {
    private Application a;
    private a b;

    @Override // cn.missfresh.module.base.common.b.d
    public void a() {
    }

    @Override // cn.missfresh.module.base.common.b.d
    public void a(int i) {
    }

    @Override // cn.missfresh.module.base.common.b.d
    public void a(Context context) {
    }

    @Override // cn.missfresh.module.base.common.b.d
    public void a(Configuration configuration) {
    }

    @Override // cn.missfresh.module.base.common.b.d
    public void b() {
    }

    @Override // cn.missfresh.module.base.common.b.d
    public void c() {
    }

    @Override // cn.missfresh.module.base.common.b.d
    public void a(Application application, a aVar) {
        this.a = application;
        this.b = aVar;
    }

    @Override // cn.missfresh.module.base.common.b.d
    public boolean a(String str) {
        AppMethodBeat.i(10282, false);
        boolean equals = this.a.getPackageName().equals(str);
        AppMethodBeat.o(10282);
        return equals;
    }
}
