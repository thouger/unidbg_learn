package cn.missfresh.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import cn.missfresh.hotfix.HotFixApplicationLike;
import cn.missfresh.module.base.common.b.a;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.tinkerlib.c;
import cn.missfresh.tinkerlib.d;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

public class AppDelegate extends HotFixApplicationLike {
    public AppDelegate(Application application, int i, boolean z, long j, long j2, Intent intent) {
        super(application, i, z, j, j2, intent);
    }

    @Override // cn.missfresh.tinkerlib.AbstractTinkerBaseApplicationLike, com.tencent.tinker.entry.DefaultApplicationLike, com.tencent.tinker.entry.ApplicationLike, com.tencent.tinker.entry.ApplicationLifeCycle
    public void onBaseContextAttached(Context context) {
        AppMethodBeat.i(97, false);
        e.a(context);
        onContextAttached();
        d.a(this);
        d.b();
        d.a(true);
        TinkerInstaller.setLogIml(c.a());
        if (e.a) {
            d.a(this, getResultServiceClass());
        }
        a.a(getApplication(), new cn.missfresh.module.base.common.config.a.a());
        a.a().a(context.getApplicationContext());
        AppMethodBeat.o(97);
    }

    @Override // com.tencent.tinker.entry.DefaultApplicationLike, com.tencent.tinker.entry.ApplicationLike, com.tencent.tinker.entry.ApplicationLifeCycle
    public void onCreate() {
        AppMethodBeat.i(103, false);
        super.onCreate();
        a.a().b();
        AppMethodBeat.o(103);
    }

    @Override // com.tencent.tinker.entry.DefaultApplicationLike, com.tencent.tinker.entry.ApplicationLike, com.tencent.tinker.entry.ApplicationLifeCycle
    public void onTerminate() {
        AppMethodBeat.i(106, false);
        a.a().c();
        AppMethodBeat.o(106);
    }

    @Override // com.tencent.tinker.entry.DefaultApplicationLike, com.tencent.tinker.entry.ApplicationLike, com.tencent.tinker.entry.ApplicationLifeCycle
    public void onConfigurationChanged(Configuration configuration) {
        AppMethodBeat.i(109, false);
        a.a().a(configuration);
        AppMethodBeat.o(109);
    }

    @Override // com.tencent.tinker.entry.DefaultApplicationLike, com.tencent.tinker.entry.ApplicationLike, com.tencent.tinker.entry.ApplicationLifeCycle
    public void onLowMemory() {
        AppMethodBeat.i(114, false);
        a.a().d();
        AppMethodBeat.o(114);
    }

    @Override // com.tencent.tinker.entry.DefaultApplicationLike, com.tencent.tinker.entry.ApplicationLike, com.tencent.tinker.entry.ApplicationLifeCycle
    public void onTrimMemory(int i) {
        AppMethodBeat.i(118, false);
        a.a().a(i);
        AppMethodBeat.o(118);
    }
}
