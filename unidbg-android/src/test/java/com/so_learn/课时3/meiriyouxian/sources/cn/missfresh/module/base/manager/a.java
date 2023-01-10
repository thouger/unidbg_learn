package cn.missfresh.module.base.manager;

import android.app.Activity;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.common.providers.ILoginService;
import cn.missfresh.module.mvp.mvp.impl.MVPActivity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.ref.WeakReference;
import java.util.Stack;

/* compiled from: ActivityManager */
public class a {
    private static a a;
    private Stack<WeakReference<Activity>> b;

    private a() {
    }

    public static a a() {
        AppMethodBeat.i(13858, false);
        if (a == null) {
            synchronized (a.class) {
                try {
                    a = new a();
                } catch (Throwable th) {
                    AppMethodBeat.o(13858);
                    throw th;
                }
            }
        }
        a aVar = a;
        AppMethodBeat.o(13858);
        return aVar;
    }

    public void a(Activity activity) {
        AppMethodBeat.i(13860, false);
        if (this.b == null) {
            this.b = new Stack<>();
        }
        this.b.add(new WeakReference<>(activity));
        AppMethodBeat.o(13860);
    }

    public Activity b() {
        AppMethodBeat.i(13863, false);
        Stack<WeakReference<Activity>> stack = this.b;
        if (stack == null || stack.size() <= 0) {
            AppMethodBeat.o(13863);
            return null;
        }
        for (int size = this.b.size() - 1; size >= 0; size--) {
            if (this.b.get(size) == null || this.b.get(size).get() == null) {
                this.b.remove(size);
            } else {
                Activity activity = this.b.get(size).get();
                AppMethodBeat.o(13863);
                return activity;
            }
        }
        AppMethodBeat.o(13863);
        return null;
    }

    public Activity c() {
        boolean z = false;
        AppMethodBeat.i(13867, false);
        Stack<WeakReference<Activity>> stack = this.b;
        if (stack == null || stack.size() <= 0) {
            AppMethodBeat.o(13867);
            return null;
        }
        ILoginService iLoginService = (ILoginService) com.alibaba.android.arouter.b.a.a().a("/user/login_service").navigation();
        for (int size = this.b.size() - 1; size >= 0; size--) {
            if (this.b.get(size) == null || this.b.get(size).get() == null) {
                this.b.remove(size);
            } else {
                if (iLoginService != null) {
                    z = iLoginService.a(this.b.get(size).get());
                }
                if (c(this.b.get(size).get()) && !z) {
                    Activity activity = this.b.get(size).get();
                    AppMethodBeat.o(13867);
                    return activity;
                }
            }
        }
        AppMethodBeat.o(13867);
        return null;
    }

    private boolean c(Activity activity) {
        if (activity == null) {
            return false;
        }
        return (activity instanceof BaseFragmentActivity) || (activity instanceof MVPActivity);
    }

    public void b(Activity activity) {
        AppMethodBeat.i(13872, false);
        if (activity == null) {
            AppMethodBeat.o(13872);
            return;
        }
        Stack<WeakReference<Activity>> stack = this.b;
        if (stack == null || stack.size() <= 0) {
            AppMethodBeat.o(13872);
            return;
        }
        int size = this.b.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            if (this.b.get(size) == null || this.b.get(size).get() == null) {
                this.b.remove(size);
            } else if (this.b.get(size).get().equals(activity)) {
                this.b.remove(size);
                break;
            }
            size--;
        }
        AppMethodBeat.o(13872);
    }

    public void d() {
        AppMethodBeat.i(13875, false);
        Stack<WeakReference<Activity>> stack = this.b;
        if (stack == null || stack.size() <= 0) {
            AppMethodBeat.o(13875);
            return;
        }
        for (int size = this.b.size() - 1; size >= 0; size--) {
            if (!(this.b.get(size) == null || this.b.get(size).get() == null)) {
                this.b.get(size).get().finish();
            }
            this.b.remove(size);
        }
        AppMethodBeat.o(13875);
    }

    public Stack<WeakReference<Activity>> e() {
        return this.b;
    }
}
