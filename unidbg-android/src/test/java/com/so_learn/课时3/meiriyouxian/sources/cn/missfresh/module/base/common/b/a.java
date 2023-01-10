package cn.missfresh.module.base.common.b;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.homepage.b.f;
import cn.missfresh.module.search.b.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AppDelegateManager */
public class a {
    private List<d> a;
    private Application b;
    private cn.missfresh.module.base.common.config.a.a c;
    private String d;

    /* compiled from: AppDelegateManager */
    /* access modifiers changed from: private */
    /* renamed from: cn.missfresh.module.base.common.b.a$a  reason: collision with other inner class name */
    public static class C0020a {
        private static a a = new a();

        static {
            AppMethodBeat.i(10235, false);
            AppMethodBeat.o(10235);
        }
    }

    private a() {
        AppMethodBeat.i(10243, false);
        this.a = new ArrayList();
        AppMethodBeat.o(10243);
    }

    public static void a(Application application, cn.missfresh.module.base.common.config.a.a aVar) {
        AppMethodBeat.i(10247, false);
        C0020a.a.b(application, aVar);
        e();
        for (d dVar : C0020a.a.a) {
            Log.i("IApplicationDelegate:", dVar.getClass().getSimpleName());
        }
        AppMethodBeat.o(10247);
    }

    public static a a() {
        AppMethodBeat.i(10250, false);
        a aVar = C0020a.a;
        AppMethodBeat.o(10250);
        return aVar;
    }

    private void b(Application application, cn.missfresh.module.base.common.config.a.a aVar) {
        AppMethodBeat.i(10254, false);
        this.d = j.a(application);
        this.b = application;
        this.c = aVar;
        AppMethodBeat.o(10254);
    }

    private static void e() {
        AppMethodBeat.i(10257, false);
        Log.i("AppDelegate", "autoInstall is called");
        AppMethodBeat.o(10257);
        a((d) new cn.missfresh.module.classifynew.a.a());
        a(new c());
        a((d) new f());
        a(new c());
    }

    public static void a(d dVar) {
        AppMethodBeat.i(10260, false);
        Log.i("AppDelegate", "install is called " + dVar.getClass().getName());
        a().b(dVar);
        AppMethodBeat.o(10260);
    }

    private void b(d dVar) {
        AppMethodBeat.i(10262, false);
        if (!this.a.contains(dVar)) {
            this.a.add(dVar);
            Log.i("AppDelegate", "installDelegate@" + this.d + "--->" + dVar);
            dVar.a(this.b, this.c);
        }
        AppMethodBeat.o(10262);
    }

    public void a(Context context) {
        AppMethodBeat.i(10264, false);
        String str = this.d;
        for (d dVar : this.a) {
            if (dVar.a(str)) {
                dVar.a(context);
                Log.i("AppDelegate", "dispatchOnBaseContextAttached@" + str + "-->" + dVar);
            }
        }
        AppMethodBeat.o(10264);
    }

    public void b() {
        AppMethodBeat.i(10267, false);
        String str = this.d;
        for (d dVar : this.a) {
            if (dVar.a(str)) {
                dVar.a();
                Log.i("AppDelegate", "dispatchOnCreate@" + str + "-->" + dVar);
            }
        }
        AppMethodBeat.o(10267);
    }

    public void c() {
        AppMethodBeat.i(10269, false);
        String str = this.d;
        for (d dVar : this.a) {
            if (dVar.a(str)) {
                dVar.b();
                Log.i("AppDelegate", "dispatchOnTerminate@" + str + "-->" + dVar);
            }
        }
        AppMethodBeat.o(10269);
    }

    public void a(Configuration configuration) {
        AppMethodBeat.i(10271, false);
        String str = this.d;
        for (d dVar : this.a) {
            if (dVar.a(str)) {
                dVar.a(configuration);
                Log.i("AppDelegate", "dispatchOnConfigurationChanged@" + str + "-->" + dVar);
            }
        }
        AppMethodBeat.o(10271);
    }

    public void d() {
        AppMethodBeat.i(10273, false);
        String str = this.d;
        for (d dVar : this.a) {
            if (dVar.a(str)) {
                dVar.c();
                Log.i("AppDelegate", "dispatchOnLowMemory@" + str + "-->" + dVar);
            }
        }
        AppMethodBeat.o(10273);
    }

    public void a(int i) {
        AppMethodBeat.i(10276, false);
        String str = this.d;
        for (d dVar : this.a) {
            if (dVar.a(str)) {
                dVar.a(i);
                Log.i("AppDelegate", "dispatchOnTrimMemory@" + str + "-->" + dVar);
            }
        }
        AppMethodBeat.o(10276);
    }
}
