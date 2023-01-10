package cn.missfresh.module.base.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.core.app.NotificationManagerCompat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: NotificationsUtils */
public class ag {
    public static int a(Context context) {
        AppMethodBeat.i(23373, false);
        if (Build.VERSION.SDK_INT >= 19) {
            boolean areNotificationsEnabled = NotificationManagerCompat.from(context).areNotificationsEnabled();
            AppMethodBeat.o(23373);
            return areNotificationsEnabled ? 1 : 0;
        }
        AppMethodBeat.o(23373);
        return 2;
    }

    public static void b(Context context) {
        AppMethodBeat.i(23374, false);
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 26) {
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
        } else if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 26) {
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra("app_package", context.getPackageName());
            intent.putExtra(Settings.EXTRA_APP_UID, context.getApplicationInfo().uid);
        } else if (Build.VERSION.SDK_INT == 19) {
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
        } else {
            intent.addFlags(268435456);
            if (Build.VERSION.SDK_INT >= 9) {
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.fromParts("package", context.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                intent.setAction("android.intent.action.VIEW");
                intent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
                intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
            }
        }
        context.startActivity(intent);
        AppMethodBeat.o(23374);
    }
}
