package cn.missfresh.unitepoplib;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.unitepoplib.bean.DialogBean;
import cn.missfresh.unitepoplib.bean.UnitePopMsgBean;
import cn.missfresh.unitepoplib.listener.UniteDialogEventListener;
import cn.missfresh.unitepoplib.listener.b;
import java.util.List;

public class UnitePopManager {
    private static boolean a;
    private static Application b;

    static /* synthetic */ cn.missfresh.unitepoplib.a.a c() {
        AppMethodBeat.i(15267, false);
        cn.missfresh.unitepoplib.a.a e = e();
        AppMethodBeat.o(15267);
        return e;
    }

    public static void init(Application application) {
        AppMethodBeat.i(15241, false);
        if (a) {
            cn.missfresh.unitepoplib.b.a.a("MFUnitePop", "allready init");
            AppMethodBeat.o(15241);
            return;
        }
        a = true;
        b = application;
        d();
        cn.missfresh.unitepoplib.b.a.a();
        e();
        AppMethodBeat.o(15241);
    }

    public static void insertDialog(String str, DialogBean dialogBean) {
        AppMethodBeat.i(15243, false);
        e().a(str, dialogBean);
        AppMethodBeat.o(15243);
    }

    public void insertDialog(String str, List<DialogBean> list) {
        AppMethodBeat.i(15244, false);
        e().a(str, list);
        AppMethodBeat.o(15244);
    }

    public static void updateDialogReady(String str) {
        AppMethodBeat.i(15245, false);
        e().a(str, 1);
        AppMethodBeat.o(15245);
    }

    public static void removeDialog(String str) {
        AppMethodBeat.i(15247, false);
        e().a(str, 3);
        AppMethodBeat.o(15247);
    }

    public static void updateQueue(String str, DialogBean dialogBean) {
        AppMethodBeat.i(15249, false);
        e().b(str, dialogBean);
        AppMethodBeat.o(15249);
    }

    public static void showNextDialog(String str) {
        AppMethodBeat.i(15251, false);
        e().b(str);
        AppMethodBeat.o(15251);
    }

    public static DialogBean getShowingDialog(String str) {
        AppMethodBeat.i(15252, false);
        DialogBean c = e().c(str);
        AppMethodBeat.o(15252);
        return c;
    }

    public static boolean hasDialogQueue(String str) {
        AppMethodBeat.i(15254, false);
        boolean d = e().d(str);
        AppMethodBeat.o(15254);
        return d;
    }

    public static void setFragmentTag(String str) {
        AppMethodBeat.i(15257, false);
        e().a(str);
        AppMethodBeat.o(15257);
    }

    public static void registerDialogListener(String str, UniteDialogEventListener<UnitePopMsgBean> uniteDialogEventListener) {
        AppMethodBeat.i(15259, false);
        b.a().a(str, uniteDialogEventListener);
        AppMethodBeat.o(15259);
    }

    public static void removeDialoglistener(UniteDialogEventListener<UnitePopMsgBean> uniteDialogEventListener) {
        AppMethodBeat.i(15261, false);
        b.a().a(uniteDialogEventListener);
        AppMethodBeat.o(15261);
    }

    public static String a() {
        AppMethodBeat.i(15262, false);
        String b2 = e().b();
        AppMethodBeat.o(15262);
        return b2;
    }

    public static Activity b() {
        AppMethodBeat.i(15264, false);
        Activity d = e().d();
        AppMethodBeat.o(15264);
        return d;
    }

    /* access modifiers changed from: package-private */
    public static class a implements Application.ActivityLifecycleCallbacks {
        a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            AppMethodBeat.i(15228, false);
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "onActivityCreated ===> " + activity);
            AppMethodBeat.o(15228);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            AppMethodBeat.i(15229, false);
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "onActivityStarted ===> " + activity);
            AppMethodBeat.o(15229);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            AppMethodBeat.i(15230, false);
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "onActivityResumed ===> " + activity);
            UnitePopManager.c().b(activity);
            AppMethodBeat.o(15230);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            AppMethodBeat.i(15232, false);
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "onActivityPaused ===> " + activity);
            AppMethodBeat.o(15232);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            AppMethodBeat.i(15234, false);
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "onActivityStopped ===> " + activity);
            AppMethodBeat.o(15234);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            AppMethodBeat.i(15236, false);
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "onActivitySaveInstanceState ===> " + activity);
            AppMethodBeat.o(15236);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            AppMethodBeat.i(15238, false);
            cn.missfresh.unitepoplib.b.a.a("dialogSDk", "onActivityDestroyed ===> " + activity);
            UnitePopManager.c().a(activity);
            AppMethodBeat.o(15238);
        }
    }

    private static void d() {
        AppMethodBeat.i(15265, false);
        b.registerActivityLifecycleCallbacks(new a());
        AppMethodBeat.o(15265);
    }

    private static cn.missfresh.unitepoplib.a.a e() {
        AppMethodBeat.i(15266, false);
        cn.missfresh.unitepoplib.a.a a2 = cn.missfresh.unitepoplib.a.a.a();
        AppMethodBeat.o(15266);
        return a2;
    }
}
