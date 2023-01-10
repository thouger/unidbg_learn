package com.hjq.toast;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.WindowManager;

/* access modifiers changed from: package-private */
public final class WindowHelper implements Application.ActivityLifecycleCallbacks {
    private final ArrayMap<String, Activity> mActivitySet = new ArrayMap<>();
    private String mCurrentTag;
    private final ToastHelper mToastHelper;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    private WindowHelper(ToastHelper toastHelper) {
        this.mToastHelper = toastHelper;
    }

    static WindowHelper register(ToastHelper toastHelper, Application application) {
        WindowHelper windowHelper = new WindowHelper(toastHelper);
        application.registerActivityLifecycleCallbacks(windowHelper);
        return windowHelper;
    }

    /* access modifiers changed from: package-private */
    public WindowManager getWindowManager() throws NullPointerException {
        Activity activity;
        String str = this.mCurrentTag;
        if (str != null && (activity = (Activity) this.mActivitySet.get(str)) != null) {
            return getWindowManagerObject(activity);
        }
        throw new NullPointerException();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        this.mCurrentTag = getObjectTag(activity);
        this.mActivitySet.put(this.mCurrentTag, activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        this.mCurrentTag = getObjectTag(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this.mCurrentTag = getObjectTag(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        this.mToastHelper.cancel();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        this.mActivitySet.remove(getObjectTag(activity));
        if (getObjectTag(activity).equals(this.mCurrentTag)) {
            this.mCurrentTag = null;
        }
    }

    private static String getObjectTag(Object obj) {
        return obj.getClass().getName() + Integer.toHexString(obj.hashCode());
    }

    private static WindowManager getWindowManagerObject(Activity activity) {
        return (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
    }
}
