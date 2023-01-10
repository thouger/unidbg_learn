package cn.missfresh.module.base.common.ministart.a;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import cn.missfresh.module.base.common.ministart.bean.RefreshTokenBean;
import cn.missfresh.module.base.common.ministart.model.UpdateTokenModel;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.utils.f;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;

/* compiled from: AppFrontBackHelper */
public class a {
    private UpdateTokenModel a;
    private Application.ActivityLifecycleCallbacks b;

    /* compiled from: AppFrontBackHelper */
    public interface b {
        void a(int i, String str);

        void a(RefreshTokenBean refreshTokenBean);
    }

    /* synthetic */ a(AnonymousClass1 r1) {
        this();
    }

    private a() {
        AppMethodBeat.i(12193, false);
        this.b = new AnonymousClass1();
        this.a = new UpdateTokenModel();
        AppMethodBeat.o(12193);
    }

    /* compiled from: AppFrontBackHelper */
    /* renamed from: cn.missfresh.module.base.common.ministart.a.a$a  reason: collision with other inner class name */
    private static class C0021a {
        private static final a a = new a(null);

        static {
            AppMethodBeat.i(12190, false);
            AppMethodBeat.o(12190);
        }
    }

    public static a a() {
        AppMethodBeat.i(12194, false);
        a aVar = C0021a.a;
        AppMethodBeat.o(12194);
        return aVar;
    }

    public void a(Application application) {
        AppMethodBeat.i(12195, false);
        application.registerActivityLifecycleCallbacks(this.b);
        AppMethodBeat.o(12195);
    }

    /* compiled from: AppFrontBackHelper */
    /* renamed from: cn.missfresh.module.base.common.ministart.a.a$1  reason: invalid class name */
    class AnonymousClass1 implements Application.ActivityLifecycleCallbacks {
        private int b = 0;

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
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        AnonymousClass1() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            AppMethodBeat.i(12181, false);
            this.b++;
            if (this.b == 1 && e.o()) {
                a.this.a.a(new AnonymousClass1());
            }
            AppMethodBeat.o(12181);
        }

        /* compiled from: AppFrontBackHelper */
        /* renamed from: cn.missfresh.module.base.common.ministart.a.a$1$1  reason: invalid class name */
        class AnonymousClass1 implements b {
            @Override // cn.missfresh.module.base.common.ministart.a.a.b
            public void a(int i, String str) {
            }

            AnonymousClass1() {
            }

            @Override // cn.missfresh.module.base.common.ministart.a.a.b
            public void a(RefreshTokenBean refreshTokenBean) {
                AppMethodBeat.i(12178, false);
                if (refreshTokenBean != null) {
                    if (cn.missfresh.utils.b.a(refreshTokenBean.getAccess_token())) {
                        e.r();
                    } else {
                        e.g(refreshTokenBean.getAccess_token());
                    }
                }
                AppMethodBeat.o(12178);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            AppMethodBeat.i(12184, false);
            this.b--;
            if (this.b == 0) {
                cn.missfresh.buttomline.logtrace.a.a(f.d());
                d.c("MissFreshApplicationLike", "\u5e94\u7528\u5207\u5230\u540e\u53f0\u5904\u7406");
            }
            AppMethodBeat.o(12184);
        }
    }
}
