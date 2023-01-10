package cn.missfresh.module.base.helper;

import android.app.Activity;
import android.os.Build;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import cn.missfresh.module.base.bean.LoginEvent;
import cn.missfresh.module.base.common.livedata.MFStickyLiveData;
import cn.missfresh.module.base.common.providers.ILoginService;
import cn.missfresh.module.base.manager.a;
import cn.missfresh.module.base.utils.f;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import java.lang.ref.WeakReference;
import java.util.Stack;

/* compiled from: UserRouterHelper */
public class o {
    public static void a(int i) {
        boolean z = false;
        AppMethodBeat.i(13235, false);
        if (Build.VERSION.SDK_INT < 17 || !f.n()) {
            b(i);
            AppMethodBeat.o(13235);
            return;
        }
        Stack<WeakReference<Activity>> e = a.a().e();
        if (e != null && e.size() > 0) {
            int size = e.size() - 1;
            while (true) {
                if (size >= 0) {
                    if (e.get(size) != null && e.get(size).get() != null && e.get(size).get().getLocalClassName().contains("QuickLoginActivity")) {
                        d.d("UserRouterHelper", "have already to login");
                        z = true;
                        break;
                    }
                    size--;
                } else {
                    break;
                }
            }
        }
        if (z) {
            AppMethodBeat.o(13235);
            return;
        }
        com.alibaba.android.arouter.b.a.a().a("/user/onpass_login").withInt("login_type", i).withFlags(335544320).navigation();
        AppMethodBeat.o(13235);
    }

    public static void b(int i) {
        AppMethodBeat.i(13236, false);
        com.alibaba.android.arouter.b.a.a().a("/user/login").withInt("login_type", i).withFlags(335544320).navigation();
        AppMethodBeat.o(13236);
    }

    public static void a(int i, boolean z) {
        AppMethodBeat.i(13238, false);
        com.alibaba.android.arouter.b.a.a().a("/user/login").withInt("login_type", i).withBoolean("login_permission_risk", true).withFlags(335544320).navigation();
        AppMethodBeat.o(13238);
    }

    public static void a(boolean z, String str) {
        AppMethodBeat.i(13240, false);
        com.alibaba.android.arouter.b.a.a().a("/user/bind_phone_num").withBoolean("is_bind_event", z).withString("from_page", str).withFlags(268435456).navigation();
        AppMethodBeat.o(13240);
    }

    public static void a(Activity activity, boolean z, String str, String str2) {
        AppMethodBeat.i(13242, false);
        if (activity == null) {
            cn.missfresh.ui.a.a.a("\u6570\u636e\u6709\u8bef!");
            AppMethodBeat.o(13242);
            return;
        }
        com.alibaba.android.arouter.b.a.a().a("/user/bind_wx_num").withBoolean("is_bind_event", z).withString("from_page", str).withString("from_source", str2).withFlags(268435456).navigation(activity, 100);
        AppMethodBeat.o(13242);
    }

    public static ILoginService a() {
        AppMethodBeat.i(13244, false);
        ILoginService iLoginService = (ILoginService) com.alibaba.android.arouter.b.a.a().a("/user/login_service").navigation();
        AppMethodBeat.o(13244);
        return iLoginService;
    }

    public static void c(int i) {
        AppMethodBeat.i(13246, false);
        ILoginService a = a();
        if (!(a == null || a.a() == null)) {
            a.a().a((MFStickyLiveData<LoginEvent>) new LoginEvent(i));
        }
        AppMethodBeat.o(13246);
    }

    public static MFStickyLiveData<LoginEvent> b() {
        AppMethodBeat.i(13247, false);
        ILoginService a = a();
        if (a != null) {
            MFStickyLiveData<LoginEvent> a2 = a.a();
            AppMethodBeat.o(13247);
            return a2;
        }
        AppMethodBeat.o(13247);
        return null;
    }

    public static boolean a(LifecycleOwner lifecycleOwner, Observer<LoginEvent> observer) {
        AppMethodBeat.i(13248, false);
        MFStickyLiveData<LoginEvent> b = b();
        if (b == null) {
            AppMethodBeat.o(13248);
            return false;
        }
        b.a(lifecycleOwner, observer, false);
        AppMethodBeat.o(13248);
        return true;
    }

    public static boolean a(Observer<LoginEvent> observer) {
        AppMethodBeat.i(13249, false);
        MFStickyLiveData<LoginEvent> b = b();
        if (b == null) {
            AppMethodBeat.o(13249);
            return false;
        }
        b.a(observer, false);
        AppMethodBeat.o(13249);
        return true;
    }
}
