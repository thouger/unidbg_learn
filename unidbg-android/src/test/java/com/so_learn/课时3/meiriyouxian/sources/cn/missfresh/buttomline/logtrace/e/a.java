package cn.missfresh.buttomline.logtrace.e;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.ref.WeakReference;
import java.util.Stack;

/* compiled from: AppLifecycle */
public class a implements Application.ActivityLifecycleCallbacks {
    public static boolean a = false;
    private static a b = new a();
    private Stack<WeakReference<Activity>> c = new Stack<>();

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    public a() {
        AppMethodBeat.i(16963, false);
        AppMethodBeat.o(16963);
    }

    static {
        AppMethodBeat.i(16984, false);
        AppMethodBeat.o(16984);
    }

    public static a a() {
        return b;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        AppMethodBeat.i(16967, false);
        try {
            this.c.push(new WeakReference<>(activity));
        } catch (Exception unused) {
        }
        AppMethodBeat.o(16967);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        a = true;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        a = false;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        AppMethodBeat.i(16975, false);
        try {
            this.c.pop();
        } catch (Exception unused) {
        }
        AppMethodBeat.o(16975);
    }
}
