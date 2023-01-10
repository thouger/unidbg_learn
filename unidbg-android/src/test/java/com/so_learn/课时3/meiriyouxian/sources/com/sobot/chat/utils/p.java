package com.sobot.chat.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import com.sobot.chat.api.model.ZhiChiPushMessage;

/* compiled from: NotificationUtils */
public class p {
    public static int a = 1000;

    public static void a(Context context, String str, String str2, String str3, int i, ZhiChiPushMessage zhiChiPushMessage) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager != null) {
            Intent intent = new Intent("sobot_notification_click");
            if (zhiChiPushMessage != null) {
                intent.putExtra("sobot_appId", zhiChiPushMessage.getAppId());
            }
            intent.setPackage(context.getPackageName());
            boolean z = false;
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 134217728);
            int b = s.b(context, "sobot_notification_small_icon", q.a(context, "drawable", "sobot_logo_small_icon"));
            ((BitmapDrawable) context.getResources().getDrawable(s.b(context, "sobot_notification_large_icon", q.a(context, "drawable", "sobot_logo_icon")))).getBitmap();
            Notification.Builder contentIntent = new Notification.Builder(context).setSmallIcon(b).setTicker(str3).setContentTitle(str).setContentText(str2).setContentIntent(broadcast);
            if (d.l(context) >= 26) {
                z = true;
            }
            if (Build.VERSION.SDK_INT >= 26 && z) {
                notificationManager.createNotificationChannel(new NotificationChannel("sobot_channel_id", q.f(context, "sobot_notification_name"), 3));
                contentIntent.setChannelId("sobot_channel_id");
            }
            Notification notification = contentIntent.getNotification();
            notification.flags |= 16;
            notification.defaults = 3;
            notificationManager.notify(i, notification);
        }
    }

    public static void a(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (notificationManager != null) {
            try {
                notificationManager.cancelAll();
            } catch (Exception unused) {
            }
        }
    }
}
