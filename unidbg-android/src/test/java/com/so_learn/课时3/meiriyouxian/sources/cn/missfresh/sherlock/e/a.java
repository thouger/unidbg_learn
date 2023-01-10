package cn.missfresh.sherlock.e;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import cn.missfresh.sherlock.config.Config;
import cn.missfresh.sherlock.d.e;
import cn.missfresh.sherlock.group.SherlockLoggerHandler;
import cn.missfresh.sherlock.to.MethodTO;
import cn.missfresh.sherlock.tool.j;

/* compiled from: LifecycleManager */
public class a {
    private String a;
    private String b;
    private int c;

    /* compiled from: LifecycleManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.sherlock.e.a$a  reason: collision with other inner class name */
    public class C0041a implements Application.ActivityLifecycleCallbacks {
        C0041a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            if (Config.getInstance().enableSherlock()) {
                e.a().a(false);
            }
            if (Config.getInstance().enableScreenSwitch()) {
                c.a().c();
            }
            String simpleName = activity.getClass().getSimpleName();
            if (!simpleName.equals(a.this.b)) {
                a.this.b = simpleName;
                a.this.a = null;
                j.a("LifecycleManager", "\u5f53\u524dActivity: " + a.this.b);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            a.a(a.this);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            a.c(a.this);
            if (a.this.c == 0) {
                cn.missfresh.sherlock.e.b();
            }
        }
    }

    /* compiled from: LifecycleManager */
    /* access modifiers changed from: package-private */
    public class b extends SherlockLoggerHandler {
        b(a aVar) {
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.sherlock.group.SherlockLoggerHandler
        public void log(String str, String str2, long j, String str3) {
            if (cn.missfresh.sherlock.e.d() && cn.missfresh.sherlock.e.m() != null && !TextUtils.isEmpty(str2) && str3.contains("\u21e0")) {
                MethodTO methodTO = new MethodTO();
                methodTO.setClassName(str);
                methodTO.setMethodName(str2);
                methodTO.setCostedMilles(j);
                cn.missfresh.sherlock.e.m().a(methodTO);
            }
        }
    }

    /* compiled from: LifecycleManager */
    public static class c {
        private static final a a = new a(null);
    }

    /* synthetic */ a(C0041a aVar) {
        this();
    }

    static /* synthetic */ int a(a aVar) {
        int i = aVar.c;
        aVar.c = i + 1;
        return i;
    }

    static /* synthetic */ int c(a aVar) {
        int i = aVar.c;
        aVar.c = i - 1;
        return i;
    }

    private void e() {
        SherlockLoggerHandler.installLogImpl(new b(this));
    }

    private a() {
        this.c = 0;
    }

    public static a a() {
        return c.a;
    }

    public void b() {
        j.b("LifecycleManager", "initLifecycleCallback");
        cn.missfresh.sherlock.e.e().registerActivityLifecycleCallbacks(new C0041a());
        e();
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(this.a)) {
            this.a = str;
            j.a("LifecycleManager", "\u5f53\u524dFragment: " + this.a);
        }
    }

    public String c() {
        return this.a;
    }

    public String d() {
        return this.b;
    }
}
