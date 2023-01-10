package com.sina.weibo.sdk.a;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import androidx.core.app.NotificationCompat;

/* compiled from: SDKNotification */
public class h {
    private Context a;
    private Notification b;

    private h(Context context, Notification notification) {
        this.a = context.getApplicationContext();
        this.b = notification;
    }

    /* synthetic */ h(Context context, Notification notification, h hVar) {
        this(context, notification);
    }

    public void a(int i) {
        if (this.b != null) {
            ((NotificationManager) this.a.getSystemService("notification")).notify(i, this.b);
        }
    }

    /* compiled from: SDKNotification */
    public static class a {
        private String a;
        private String b;
        private String c;
        private PendingIntent d;
        private long[] e;
        private Uri f;

        public static a a() {
            return new a();
        }

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a b(String str) {
            this.b = str;
            return this;
        }

        public a c(String str) {
            this.c = str;
            return this;
        }

        public a a(PendingIntent pendingIntent) {
            this.d = pendingIntent;
            return this;
        }

        public h a(Context context) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setAutoCancel(true);
            builder.setContentIntent(this.d);
            builder.setTicker(this.a);
            builder.setSmallIcon(b(context));
            builder.setWhen(System.currentTimeMillis());
            Uri uri = this.f;
            if (uri != null) {
                builder.setSound(uri);
            }
            long[] jArr = this.e;
            if (jArr != null) {
                builder.setVibrate(jArr);
            }
            builder.setLargeIcon(((BitmapDrawable) g.a(context, "weibosdk_notification_icon.png")).getBitmap());
            builder.setContentTitle(this.b);
            builder.setContentText(this.c);
            return new h(context, builder.build(), null);
        }

        private static int b(Context context) {
            int a = a(context, "com_sina_weibo_sdk_weibo_logo", "drawable");
            if (a > 0) {
                return a;
            }
            return 17301659;
        }

        private static int a(Context context, String str, String str2) {
            String packageName = context.getApplicationContext().getPackageName();
            try {
                return context.getPackageManager().getResourcesForApplication(packageName).getIdentifier(str, str2, packageName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }
}
