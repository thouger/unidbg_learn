package com.hmt.analytics.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import com.hmt.analytics.util.f;
import com.hmt.analytics.util.i;

public class KeepReceiver extends BroadcastReceiver {
    private static final String a = KeepReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null && context != null) {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action)) {
                char c = '\uffff';
                switch (action.hashCode()) {
                    case -1886648615:
                        if (action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
                            c = 4;
                            break;
                        }
                        break;
                    case -1514214344:
                        if (action.equals(Intent.ACTION_MEDIA_MOUNTED)) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1172645946:
                        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                            c = 5;
                            break;
                        }
                        break;
                    case 798292259:
                        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
                            c = 0;
                            break;
                        }
                        break;
                    case 823795052:
                        if (action.equals(Intent.ACTION_USER_PRESENT)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1019184907:
                        if (action.equals(Intent.ACTION_POWER_CONNECTED)) {
                            c = 3;
                            break;
                        }
                        break;
                }
                if (c == 0 || c == 1 || c == 2 || c == 3 || c == 4 || c == 5) {
                    String str = (String) i.b(context.getApplicationContext(), "sopath", "path", "");
                    if (!TextUtils.isEmpty(str)) {
                        f.a(context.getApplicationContext(), str);
                    }
                }
            }
        }
    }
}
