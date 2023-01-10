package cn.missfresh.application.a;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.helper.h;
import cn.missfresh.module.base.helper.n;
import cn.missfresh.module.base.im.IMChatActivity;
import cn.missfresh.module.base.im.IMManager;
import cn.missfresh.module.base.manager.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMMessage;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IMEventListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: GlobalActivityLifecycleCallbacks */
public class b implements Application.ActivityLifecycleCallbacks {
    private int a = 0;
    private boolean b;
    private boolean c;
    private n d = new n();
    private Activity e;
    private IMEventListener f = new AnonymousClass1();

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public b() {
        AppMethodBeat.i(53, false);
        AppMethodBeat.o(53);
    }

    /* compiled from: GlobalActivityLifecycleCallbacks */
    /* renamed from: cn.missfresh.application.a.b$1  reason: invalid class name */
    class AnonymousClass1 extends IMEventListener {
        AnonymousClass1() {
        }

        @Override // com.tencent.qcloud.tim.uikit.base.IMEventListener
        public void onNewMessages(List<TIMMessage> list) {
            AppMethodBeat.i(56, false);
            HashSet hashSet = new HashSet();
            for (TIMMessage tIMMessage : list) {
                hashSet.add(tIMMessage.getConversation());
            }
            Iterator it2 = hashSet.iterator();
            while (it2.hasNext()) {
                TIMConversation tIMConversation = (TIMConversation) it2.next();
                b.this.a(tIMConversation.getPeer(), tIMConversation.getGroupName());
            }
            AppMethodBeat.o(56);
        }

        @Override // com.tencent.qcloud.tim.uikit.base.IMEventListener
        public void onForceOffline() {
            AppMethodBeat.i(61, false);
            b.this.b();
            AppMethodBeat.o(61);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        AppMethodBeat.i(60, false);
        d.d("GlobalActivityLifecycle", "activity=" + activity.getClass().getSimpleName());
        if (a(activity, bundle)) {
            AppMethodBeat.o(60);
            return;
        }
        a.a().a(activity);
        this.d.a(activity);
        AppMethodBeat.o(60);
    }

    private boolean a(Activity activity, Bundle bundle) {
        AppMethodBeat.i(64, false);
        if (bundle == null || activity == null || activity.getLocalClassName() == null) {
            AppMethodBeat.o(64);
            return false;
        } else if (activity.getLocalClassName().contains("com.sdk.mobile.manager") || activity.getLocalClassName().contains("com.cmic.sso") || activity.getLocalClassName().contains("com.netease.nis")) {
            activity.finish();
            AppMethodBeat.o(64);
            return true;
        } else {
            AppMethodBeat.o(64);
            return false;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        AppMethodBeat.i(67, false);
        a.a().b(activity);
        AppMethodBeat.o(67);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        AppMethodBeat.i(73, false);
        if (a(activity)) {
            h.a().b(activity);
        }
        AppMethodBeat.o(73);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        AppMethodBeat.i(78, false);
        this.e = activity;
        if (!this.c) {
            this.c = true;
        }
        if (a(activity)) {
            h.a().a(activity);
        }
        this.d.b(activity);
        d.c("GlobalActivityLifecycle", "onActivityResumed " + this.c);
        AppMethodBeat.o(78);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        AppMethodBeat.i(88, false);
        this.a++;
        if (this.a == 1 && !this.b) {
            c();
            IMManager.a().getAllNewMsgToShow();
            d.d("GlobalActivityLifecycle", "\u5e94\u7528\u5207\u6362\u5230\u524d\u53f0");
        }
        this.b = false;
        AppMethodBeat.o(88);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        n nVar;
        boolean z = false;
        AppMethodBeat.i(95, false);
        this.a--;
        if (this.a == 0) {
            d();
            d.d("GlobalActivityLifecycle", "\u5e94\u7528\u5207\u6362\u5230\u540e\u53f0");
        }
        this.b = this.e.isChangingConfigurations();
        if (this.a > 0) {
            z = true;
        }
        this.c = z;
        if (!this.c && (nVar = this.d) != null && nVar.a()) {
            StatisticsManager.d(activity.getApplication(), "end_app", null);
        }
        d.c("GlobalActivityLifecycle", "onActivityStopped " + this.c);
        if (!this.c) {
            h.a().b();
        }
        AppMethodBeat.o(95);
    }

    private boolean a(Activity activity) {
        boolean z = false;
        AppMethodBeat.i(100, false);
        if (!"SplashActivity".equals(activity.getClass().getSimpleName()) && !"NewUserActivity".equals(activity.getClass().getSimpleName())) {
            z = true;
        }
        AppMethodBeat.o(100);
        return z;
    }

    public boolean a() {
        return this.a > 0;
    }

    public void a(String str, String str2) {
        AppMethodBeat.i(108, false);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            AppMethodBeat.o(108);
            return;
        }
        Activity activity = this.e;
        if (activity == null || activity.isFinishing() || !(this.e instanceof IMChatActivity) || !str.equalsIgnoreCase(IMManager.a().d())) {
            try {
                new cn.missfresh.module.base.im.b(this.e, str, str2).a();
            } catch (Exception unused) {
                d.d("tencentIM", "IM \u6709\u65b0\u7684\u6d88\u606f\u5c55\u793a\u65f6\u51fa\u73b0\u5f02\u5e38");
            }
            AppMethodBeat.o(108);
            return;
        }
        AppMethodBeat.o(108);
    }

    public void b() {
        AppMethodBeat.i(112, false);
        Activity activity = this.e;
        if (activity != null && !activity.isFinishing()) {
            if (this.e instanceof IMChatActivity) {
                cn.missfresh.ui.a.a.a("\u60a8\u7684\u8d26\u53f7\u5df2\u5728\u5176\u4ed6\u8bbe\u5907\u767b\u5f55\uff0c\u6b64\u6b21\u5bf9\u8bdd\u88ab\u5f3a\u5236\u9000\u51fa");
                IMManager.a().setKickedOut(true);
                this.e.finish();
            } else {
                IMManager.a().setLoginSuccess(false);
            }
        }
        AppMethodBeat.o(112);
    }

    public void c() {
        AppMethodBeat.i(115, false);
        TUIKit.addIMEventListener(this.f);
        AppMethodBeat.o(115);
    }

    public void d() {
        AppMethodBeat.i(120, false);
        TUIKit.removeIMEventListener(this.f);
        AppMethodBeat.o(120);
    }
}
