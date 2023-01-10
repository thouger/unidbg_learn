package com.hmt.analytics.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.text.TextUtils;
import com.hmt.analytics.service.KeepService;
import com.hmt.analytics.task.WaTask;
import com.hmt.analytics.task.a;
import java.util.List;

/* compiled from: HMTUtils */
public class f {
    private static final String a = f.class.getSimpleName();

    public static void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent(context, KeepService.class);
            intent.putExtra("path", str);
            List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
            if (queryIntentServices == null || queryIntentServices.size() == 0) {
                a.a += "9";
                com.hmt.analytics.android.a.a(a, "There is no Service 2 handle the ws task!");
                WaTask.a(context, str);
            } else if (queryIntentServices.size() != 1) {
                com.hmt.analytics.android.a.a(a, "Find more than one KeepService");
            } else if (b(context, queryIntentServices.get(0).serviceInfo.name)) {
                com.hmt.analytics.android.a.a(a, "KeepService has already Started");
            } else if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }
        }
    }

    public static boolean b(Context context, String str) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(30);
        if (runningServices.size() <= 0) {
            return false;
        }
        for (int i = 0; i < runningServices.size(); i++) {
            if (runningServices.get(i).service.getClassName().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
