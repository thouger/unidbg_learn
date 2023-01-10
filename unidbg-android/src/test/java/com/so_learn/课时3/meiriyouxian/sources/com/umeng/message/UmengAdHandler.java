package com.umeng.message;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.tencent.open.SocialConstants;
import com.umeng.analytics.pro.ai;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.common.d;
import com.umeng.message.entity.UMessage;
import com.umeng.message.entity.UNotificationItem;
import com.umeng.message.proguard.h;
import com.umeng.message.service.UMJobIntentService;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import org.json.JSONObject;

public class UmengAdHandler implements UHandler {
    private static int a = 64;
    private static final String b = UmengAdHandler.class.getName();
    private static Date c = null;
    private static String d = null;
    private static final String g = "umeng_push_notification_default_large_icon";
    private static final String h = "umeng_push_notification_default_small_icon";
    private static final String i = "umeng_push_notification_default_sound";
    private UMessage e = null;
    private int f;

    public void dealWithCustomMessage(Context context, UMessage uMessage) {
    }

    static {
        d = "9999999999999";
    }

    public void setPrevMessage(UMessage uMessage) {
        this.e = uMessage;
    }

    @Override // com.umeng.message.UHandler
    public void handleMessage(Context context, UMessage uMessage) {
        UMLog.mutlInfo(b, 2, "handleMessage");
        dealWithNotificationMessage(context, uMessage);
    }

    public void dealWithNotificationMessage(Context context, UMessage uMessage) {
        String str;
        String str2;
        boolean z = true;
        UMLog.mutlInfo(b, 2, "notify: " + uMessage.getRaw().toString());
        if (MessageSharedPrefs.getInstance(context).hasMessageResourceDownloaded(uMessage.msg_id) || !startDownloadResourceService(context, uMessage)) {
            String lastMessageMsgID = MessageSharedPrefs.getInstance(context).getLastMessageMsgID();
            if ("".equals(lastMessageMsgID)) {
                str = "";
            } else {
                str = lastMessageMsgID.substring(7, 20);
            }
            if (uMessage.msg_id == null || 22 != uMessage.msg_id.length() || !uMessage.msg_id.startsWith(ai.aE)) {
                str2 = d;
            } else {
                MessageSharedPrefs.getInstance(context).setLastMessageMsgID(uMessage.msg_id);
                str2 = uMessage.msg_id.substring(7, 20);
            }
            if (!"".equals(str) && str2.compareToIgnoreCase(str) < 0) {
                z = false;
            }
            MessageSharedPrefs.getInstance(context).removeMessageResouceRecord(uMessage.msg_id);
            Notification notification = getNotification(context, uMessage);
            if (notification != null) {
                int i2 = notification.icon;
            }
            if (notification != null) {
                this.f = new Random(System.nanoTime()).nextInt();
                PendingIntent clickPendingIntent = getClickPendingIntent(context, uMessage);
                notification.deleteIntent = getDismissPendingIntent(context, uMessage);
                notification.contentIntent = clickPendingIntent;
                int notificationDefaults = getNotificationDefaults(context, uMessage);
                if ((notificationDefaults & 1) != 0) {
                    Uri sound = getSound(context, uMessage);
                    if (sound != null) {
                        notification.sound = getSound(context, uMessage);
                    }
                    if (sound != null) {
                        notificationDefaults ^= 1;
                    }
                }
                notification.defaults = notificationDefaults;
                a(context, notification, z, uMessage);
            }
        }
    }

    private void a(Context context, Notification notification, boolean z, UMessage uMessage) {
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            int i2 = this.f;
            if (!h.c(context) || !h.b(context) || PushAgent.getInstance(context).getNotificationOnForeground()) {
                if (MessageSharedPrefs.getInstance(context).getDisplayNotificationNumber() == 1) {
                    if (z) {
                    }
                }
                if (MessageSharedPrefs.getInstance(context).getDisplayNotificationNumber() > 0) {
                    while (MessageNotificationQueue.getInstance().size() >= MessageSharedPrefs.getInstance(context).getDisplayNotificationNumber()) {
                        UNotificationItem pollFirst = MessageNotificationQueue.getInstance().pollFirst();
                        notificationManager.cancel(pollFirst.id);
                        UTrack.getInstance(context).setClearPrevMessage(false);
                        UTrack.getInstance(context).trackMsgDismissed(pollFirst.message);
                    }
                    MessageNotificationQueue.getInstance().addLast(new UNotificationItem(i2, uMessage));
                }
                notificationManager.notify(i2, notification);
                return;
            }
            UTrack.getInstance(context).setClearPrevMessage(false);
            UTrack.getInstance(context).trackMsgDismissed(uMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(Context context) {
        boolean z;
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            if (Build.VERSION.SDK_INT >= 7) {
                z = new AnonymousClass1(powerManager).a();
            } else {
                UMLog.mutlInfo(b, 0, "android os version < 7, skip checking screen on status");
                z = false;
            }
            UMLog.mutlInfo(b, 2, "\u5c4f\u5e55\u662f\u5426\u5904\u4e8e\u5f00\u542f\u72b6\u6001:" + z);
            if (!z) {
                powerManager.newWakeLock(805306374, "MyLock").acquire(JobInfo.MIN_BACKOFF_MILLIS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.UmengAdHandler$1  reason: invalid class name */
    public class AnonymousClass1 {
        final /* synthetic */ PowerManager a;

        AnonymousClass1(PowerManager powerManager) {
            this.a = powerManager;
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return this.a.isScreenOn();
        }
    }

    public PendingIntent getClickPendingIntent(Context context, UMessage uMessage) {
        Intent intent = new Intent();
        intent.setClass(context, NotificationProxyBroadcastReceiver.class);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_MSG, uMessage.getRaw().toString());
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_ACTION, 10);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_MESSAGE_ID, uMessage.message_id);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_NOTIFICATION_ID, this.f);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_TASK_ID, uMessage.task_id);
        return PendingIntent.getBroadcast(context, (int) System.currentTimeMillis(), intent, 268435456);
    }

    public PendingIntent getDismissPendingIntent(Context context, UMessage uMessage) {
        Intent intent = new Intent();
        intent.setClass(context, NotificationProxyBroadcastReceiver.class);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_MSG, uMessage.getRaw().toString());
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_ACTION, 11);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_MESSAGE_ID, uMessage.message_id);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_NOTIFICATION_ID, this.f);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_TASK_ID, uMessage.task_id);
        return PendingIntent.getBroadcast(context, (int) (System.currentTimeMillis() + 1), intent, 268435456);
    }

    public boolean isInNoDisturbTime(Context context) {
        int i2 = (Calendar.getInstance().get(11) * 60) + Calendar.getInstance().get(12);
        boolean z = i2 >= (PushAgent.getInstance(context).getNoDisturbStartHour() * 60) + PushAgent.getInstance(context).getNoDisturbStartMinute();
        boolean z2 = i2 <= (PushAgent.getInstance(context).getNoDisturbEndHour() * 60) + PushAgent.getInstance(context).getNoDisturbEndMinute();
        if ((PushAgent.getInstance(context).getNoDisturbEndHour() * 60) + PushAgent.getInstance(context).getNoDisturbEndMinute() >= (PushAgent.getInstance(context).getNoDisturbStartHour() * 60) + PushAgent.getInstance(context).getNoDisturbStartMinute()) {
            if (z && z2) {
                return true;
            }
        } else if (z || z2) {
            return true;
        }
        return false;
    }

    public int getNotificationDefaults(Context context, UMessage uMessage) {
        int i2;
        boolean isInNoDisturbTime = isInNoDisturbTime(context);
        long muteDuration = ((long) MessageSharedPrefs.getInstance(context).getMuteDuration()) * 1 * 1000;
        int i3 = 0;
        if (!isInNoDisturbTime && (c == null || Calendar.getInstance().getTimeInMillis() - c.getTime() >= muteDuration)) {
            int notificationPlayVibrate = MessageSharedPrefs.getInstance(context).getNotificationPlayVibrate();
            UMLog.mutlInfo(b, 2, "playVibrate:" + notificationPlayVibrate);
            if (notificationPlayVibrate != 1 && (notificationPlayVibrate == 2 || !uMessage.play_vibrate)) {
                i2 = 0;
            } else {
                i2 = 2;
            }
            int notificationPlayLights = MessageSharedPrefs.getInstance(context).getNotificationPlayLights();
            UMLog.mutlInfo(b, 2, "playLights:" + notificationPlayLights);
            if (notificationPlayLights == 1 || (notificationPlayLights != 2 && uMessage.play_lights)) {
                i2 |= 4;
            }
            int notificationPlaySound = MessageSharedPrefs.getInstance(context).getNotificationPlaySound();
            UMLog.mutlInfo(b, 2, "playSound:" + notificationPlaySound);
            if (notificationPlaySound == 1 || (notificationPlaySound != 2 && uMessage.play_sound)) {
                i2 |= 1;
            }
            i3 = i2;
            c = Calendar.getInstance().getTime();
            if (uMessage.screen_on) {
                a(context);
            }
        }
        return i3;
    }

    public boolean startDownloadResourceService(Context context, UMessage uMessage) {
        try {
            Intent intent = new Intent(context, UmengDownloadResourceService.class);
            intent.putExtra("body", uMessage.getRaw().toString());
            intent.putExtra("id", uMessage.message_id);
            intent.putExtra("task_id", uMessage.task_id);
            UMJobIntentService.enqueueWork(context, UmengDownloadResourceService.class, intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean a(Context context, Notification.Builder builder, UMessage uMessage) {
        int smallIconId = getSmallIconId(context, uMessage);
        Bitmap largeIcon = getLargeIcon(context, uMessage);
        if (smallIconId < 0) {
            return false;
        }
        builder.setSmallIcon(smallIconId);
        builder.setLargeIcon(largeIcon);
        return true;
    }

    public int getSmallIconId(Context context, UMessage uMessage) {
        int i2 = -1;
        try {
            if (!TextUtils.isEmpty(uMessage.icon)) {
                i2 = d.a(context).d(uMessage.icon);
            }
            if (i2 < 0) {
                i2 = d.a(context).d(h);
            }
            if (i2 < 0) {
                UMLog.mutlInfo(b, 2, "\u6ca1\u6709\u81ea\u5b9a\u4e49\u56fe\u6807\uff0c\u6539\u7528\u5e94\u7528\u56fe\u6807");
                i2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.icon;
            }
            if (i2 < 0) {
                UMLog.mutlInfo(b, 0, "\u627e\u4e0d\u5230\u9002\u5f53\u7684\u901a\u77e5\u56fe\u6807\uff0c\u8bf7\u786e\u4fdd\u60a8\u4e3a\u6b64\u901a\u77e5\u6307\u5b9a\u4e86\u4e00\u4e2a\u56fe\u6807\uff0c\u6216\u8005\u5e94\u7528\u5df2\u7ecf\u5b9a\u4e49\u4e86\u4e00\u4e2a\u56fe\u6807");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i2;
    }

    public Bitmap getLargeIcon(Context context, UMessage uMessage) {
        Bitmap bitmap;
        try {
            if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
                try {
                    String optString = new JSONObject(uMessage.custom).optString(SocialConstants.PARAM_IMG_URL);
                    bitmap = BitmapFactory.decodeFile(UmengDownloadResourceService.getMessageResourceFolder(context, uMessage) + optString.hashCode());
                } catch (Exception unused) {
                }
            } else {
                if (uMessage.isLargeIconFromInternet()) {
                    bitmap = BitmapFactory.decodeFile(UmengDownloadResourceService.getMessageResourceFolder(context, uMessage) + uMessage.img.hashCode());
                }
                bitmap = null;
            }
            if (bitmap != null) {
                return bitmap;
            }
            UMLog.mutlInfo(b, 2, "bmp is null");
            int i2 = -1;
            if (!TextUtils.isEmpty(uMessage.largeIcon)) {
                i2 = d.a(context).d(uMessage.largeIcon);
            }
            if (i2 < 0) {
                i2 = d.a(context).d(g);
            }
            return i2 > 0 ? BitmapFactory.decodeResource(context.getResources(), i2) : bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0029, code lost:
        if (new java.io.File(r1).exists() == false) goto L_0x002b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.net.Uri getSound(android.content.Context r5, com.umeng.message.entity.UMessage r6) {
        /*
            r4 = this;
            r0 = 0
            boolean r1 = r6.isSoundFromInternet()     // Catch:{ all -> 0x0076 }
            if (r1 == 0) goto L_0x002b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0076 }
            r1.<init>()     // Catch:{ all -> 0x0076 }
            java.lang.String r2 = com.umeng.message.UmengDownloadResourceService.getMessageResourceFolder(r5, r6)     // Catch:{ all -> 0x0076 }
            r1.append(r2)     // Catch:{ all -> 0x0076 }
            java.lang.String r2 = r6.sound     // Catch:{ all -> 0x0076 }
            int r2 = r2.hashCode()     // Catch:{ all -> 0x0076 }
            r1.append(r2)     // Catch:{ all -> 0x0076 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0076 }
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x0076 }
            r2.<init>(r1)     // Catch:{ all -> 0x0076 }
            boolean r2 = r2.exists()     // Catch:{ all -> 0x0076 }
            if (r2 != 0) goto L_0x002c
        L_0x002b:
            r1 = r0
        L_0x002c:
            if (r1 != 0) goto L_0x006f
            r2 = -1
            java.lang.String r3 = r6.sound     // Catch:{ all -> 0x0076 }
            boolean r3 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0076 }
            if (r3 != 0) goto L_0x0041
            com.umeng.message.common.d r2 = com.umeng.message.common.d.a(r5)     // Catch:{ all -> 0x0076 }
            java.lang.String r6 = r6.sound     // Catch:{ all -> 0x0076 }
            int r2 = r2.j(r6)     // Catch:{ all -> 0x0076 }
        L_0x0041:
            if (r2 >= 0) goto L_0x004e
            com.umeng.message.common.d r6 = com.umeng.message.common.d.a(r5)     // Catch:{ all -> 0x0076 }
            java.lang.String r2 = "umeng_push_notification_default_sound"
            int r2 = r6.j(r2)     // Catch:{ all -> 0x0076 }
        L_0x004e:
            if (r2 <= 0) goto L_0x006f
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0076 }
            r6.<init>()     // Catch:{ all -> 0x0076 }
            java.lang.String r1 = "android.resource://"
            r6.append(r1)     // Catch:{ all -> 0x0076 }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ all -> 0x0076 }
            r6.append(r5)     // Catch:{ all -> 0x0076 }
            java.lang.String r5 = "/"
            r6.append(r5)     // Catch:{ all -> 0x0076 }
            r6.append(r2)     // Catch:{ all -> 0x0076 }
            java.lang.String r1 = r6.toString()     // Catch:{ all -> 0x0076 }
        L_0x006f:
            if (r1 == 0) goto L_0x0076
            android.net.Uri r5 = android.net.Uri.parse(r1)     // Catch:{ all -> 0x0076 }
            return r5
        L_0x0076:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.UmengAdHandler.getSound(android.content.Context, com.umeng.message.entity.UMessage):android.net.Uri");
    }

    public Notification getNotification(Context context, UMessage uMessage) {
        String str = uMessage.custom;
        if (str == null || str.equals("")) {
            return null;
        }
        try {
            int optInt = new JSONObject(str).optInt("t");
            Notification.Builder builder = new Notification.Builder(context);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), d.a(context).f("upush_notification"));
            if (optInt == 0) {
                remoteViews.setImageViewBitmap(d.a(context).c("notification_large_icon1"), getLargeIcon(context, uMessage));
                remoteViews.setViewVisibility(d.a(context).c("upush_notification1"), 0);
                remoteViews.setViewVisibility(d.a(context).c("upush_notification2"), 8);
                remoteViews.setTextViewText(d.a(context).c("notification_title"), uMessage.title);
                remoteViews.setTextViewText(d.a(context).c("notification_text"), uMessage.text);
                builder.setContent(remoteViews).setTicker(uMessage.ticker).setSmallIcon(getSmallIconId(context, uMessage)).setAutoCancel(true);
                return builder.getNotification();
            } else if (optInt != 1) {
                return null;
            } else {
                remoteViews.setImageViewBitmap(d.a(context).c("notification_large_icon2"), getLargeIcon(context, uMessage));
                remoteViews.setViewVisibility(d.a(context).c("upush_notification1"), 8);
                remoteViews.setViewVisibility(d.a(context).c("upush_notification2"), 0);
                builder.setContent(remoteViews).setTicker(uMessage.ticker).setSmallIcon(getSmallIconId(context, uMessage)).setAutoCancel(true);
                return builder.getNotification();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
