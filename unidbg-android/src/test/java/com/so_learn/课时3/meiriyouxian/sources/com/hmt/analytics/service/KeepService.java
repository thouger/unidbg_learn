package com.hmt.analytics.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import com.hmt.analytics.android.a;
import com.hmt.analytics.task.WaTask;
import com.hmt.analytics.util.i;

public class KeepService extends Service {
    private static final String a = KeepService.class.getSimpleName();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        a.a(a, "KeepService is Created");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        com.hmt.analytics.task.a.a += "-9";
        a.a(a, "onStartCommand");
        if (Build.VERSION.SDK_INT < 18) {
            startForeground(1001, new Notification());
        } else if (Build.VERSION.SDK_INT > 18) {
            int i3 = Build.VERSION.SDK_INT;
        }
        if (intent != null) {
            String stringExtra = intent.getStringExtra("path");
            if (TextUtils.isEmpty(stringExtra)) {
                return 1;
            }
            i.a(getApplicationContext(), "sopath", "path", stringExtra);
            WaTask.a(getApplicationContext(), stringExtra);
            return 1;
        }
        String str = (String) i.b(getApplicationContext(), "sopath", "");
        if (TextUtils.isEmpty(str)) {
            return 1;
        }
        WaTask.a(getApplicationContext(), str);
        return 1;
    }
}
