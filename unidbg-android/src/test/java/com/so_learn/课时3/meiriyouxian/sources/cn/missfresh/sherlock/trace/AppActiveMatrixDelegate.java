package cn.missfresh.sherlock.trace;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import cn.missfresh.sherlock.tool.j;
import cn.missfresh.sherlock.trace.tracer.d;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum AppActiveMatrixDelegate {
    INSTANCE;
    
    private b controller = new b();
    private String currentFragmentName;
    private boolean isAppForeground = false;
    private boolean isInited = false;
    private Set<d> listeners = Collections.synchronizedSet(new HashSet());
    private String visibleScene = "default";

    private final class b implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
        private b() {
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
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            AppActiveMatrixDelegate.this.a(activity);
            if (!AppActiveMatrixDelegate.this.isAppForeground) {
                AppActiveMatrixDelegate appActiveMatrixDelegate = AppActiveMatrixDelegate.this;
                appActiveMatrixDelegate.b(appActiveMatrixDelegate.getVisibleScene());
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (AppActiveMatrixDelegate.getTopActivityName() == null) {
                AppActiveMatrixDelegate appActiveMatrixDelegate = AppActiveMatrixDelegate.this;
                appActiveMatrixDelegate.a(appActiveMatrixDelegate.getVisibleScene());
            }
        }

        @Override // android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration configuration) {
        }

        @Override // android.content.ComponentCallbacks
        public void onLowMemory() {
        }

        @Override // android.content.ComponentCallbacks2
        public void onTrimMemory(int i) {
            j.a("AppActiveMatrixDelegate", "[onTrimMemory] level:%s", Integer.valueOf(i));
            if (i == 20 && AppActiveMatrixDelegate.this.isAppForeground) {
                AppActiveMatrixDelegate appActiveMatrixDelegate = AppActiveMatrixDelegate.this;
                appActiveMatrixDelegate.a(appActiveMatrixDelegate.visibleScene);
            }
        }
    }

    private AppActiveMatrixDelegate() {
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str) {
        if (this.isAppForeground && this.isInited) {
            boolean z = false;
            j.a("AppActiveMatrixDelegate", "onBackground... visibleScene[%s]", str);
            try {
                for (d dVar : this.listeners) {
                    dVar.a(false);
                }
            } finally {
                this.isAppForeground = z;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(String str) {
        if (!this.isAppForeground && this.isInited) {
            boolean z = true;
            j.a("AppActiveMatrixDelegate", "onForeground... visibleScene[%s]", str);
            try {
                for (d dVar : this.listeners) {
                    dVar.a(true);
                }
            } finally {
                this.isAppForeground = z;
            }
        }
    }

    private void c(String str) {
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(str)) {
            str = "?";
        }
        sb.append(str);
        this.visibleScene = sb.toString();
    }

    public static String getTopActivityName() {
        Map map;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            if (Build.VERSION.SDK_INT < 19) {
                map = (HashMap) declaredField.get(invoke);
            } else {
                map = (ArrayMap) declaredField.get(invoke);
            }
            if (map.size() < 1) {
                j.b("AppActiveMatrixDelegate", "[getTopActivityName] Cost:%s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                return null;
            }
            for (Object obj : map.values()) {
                Class<?> cls2 = obj.getClass();
                Field declaredField2 = cls2.getDeclaredField("paused");
                declaredField2.setAccessible(true);
                if (!declaredField2.getBoolean(obj)) {
                    Field declaredField3 = cls2.getDeclaredField("activity");
                    declaredField3.setAccessible(true);
                    String name = ((Activity) declaredField3.get(obj)).getClass().getName();
                    j.b("AppActiveMatrixDelegate", "[getTopActivityName] Cost:%s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    return name;
                }
            }
            j.b("AppActiveMatrixDelegate", "[getTopActivityName] Cost:%s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            j.b("AppActiveMatrixDelegate", "[getTopActivityName] Cost:%s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        } catch (Throwable th) {
            j.b("AppActiveMatrixDelegate", "[getTopActivityName] Cost:%s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            throw th;
        }
    }

    public void addListener(d dVar) {
        this.listeners.add(dVar);
    }

    public String getCurrentFragmentName() {
        return this.currentFragmentName;
    }

    public String getVisibleScene() {
        return this.visibleScene;
    }

    public void init(Application application) {
        if (this.isInited) {
            j.e("AppActiveMatrixDelegate", "has inited!");
            return;
        }
        this.isInited = true;
        application.registerComponentCallbacks(this.controller);
        application.registerActivityLifecycleCallbacks(this.controller);
    }

    public boolean isAppForeground() {
        return this.isAppForeground;
    }

    public void removeListener(d dVar) {
        this.listeners.remove(dVar);
    }

    public void setCurrentFragmentName(String str) {
        j.a("AppActiveMatrixDelegate", "[setCurrentFragmentName] fragmentName:%s", str);
        this.currentFragmentName = str;
        c(str);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(Activity activity) {
        this.visibleScene = activity.getClass().getName();
    }
}
