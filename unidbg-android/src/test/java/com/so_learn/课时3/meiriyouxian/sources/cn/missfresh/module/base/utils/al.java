package cn.missfresh.module.base.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import java.lang.reflect.Method;

/* compiled from: ProcessUtil */
public class al {
    public static String a(Context context) {
        AppMethodBeat.i(23395, false);
        if (!b.a((String) null)) {
            AppMethodBeat.o(23395);
            return null;
        }
        String a = a();
        if (!b.a(a)) {
            AppMethodBeat.o(23395);
            return a;
        }
        String b = b();
        if (!b.a(b)) {
            AppMethodBeat.o(23395);
            return b;
        }
        String b2 = b(context);
        AppMethodBeat.o(23395);
        return b2;
    }

    public static String a() {
        AppMethodBeat.i(23396, false);
        if (Build.VERSION.SDK_INT >= 28) {
            String processName = Application.getProcessName();
            AppMethodBeat.o(23396);
            return processName;
        }
        AppMethodBeat.o(23396);
        return null;
    }

    public static String b() {
        AppMethodBeat.i(23397, false);
        String str = null;
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader()).getDeclaredMethod("currentProcessName", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            if (invoke instanceof String) {
                str = (String) invoke;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AppMethodBeat.o(23397);
        return str;
    }

    public static String b(Context context) {
        AppMethodBeat.i(23398, false);
        if (context == null) {
            AppMethodBeat.o(23398);
            return null;
        }
        int myPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    String str = runningAppProcessInfo.processName;
                    AppMethodBeat.o(23398);
                    return str;
                }
            }
        }
        AppMethodBeat.o(23398);
        return null;
    }
}
