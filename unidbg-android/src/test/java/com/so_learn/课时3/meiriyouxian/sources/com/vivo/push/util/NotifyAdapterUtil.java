package com.vivo.push.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.widget.RemoteViews;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.b.p;
import com.vivo.push.c.l;
import com.vivo.push.d;
import com.vivo.push.model.InsideNotificationItem;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NotifyAdapterUtil {
    private static final int HIDE_TITLE = 1;
    public static final int NOTIFY_MULTITERM_STYLE = 1;
    public static final int NOTIFY_SINGLE_STYLE = 0;
    public static final String PRIMARY_CHANNEL = "vivo_push_channel";
    private static final String PUSH_EN = "PUSH";
    private static final String PUSH_ZH = "\u63a8\u9001\u901a\u77e5";
    private static final String TAG = "NotifyManager";
    private static NotificationManager sNotificationManager = null;
    private static int sNotifyId = 20000000;

    public static void pushNotification(Context context, List<Bitmap> list, InsideNotificationItem insideNotificationItem, long j, int i, l.a aVar) {
        AppMethodBeat.i(1104, false);
        n.d(TAG, "pushNotification");
        initAdapter(context);
        int notifyMode = NotifyUtil.getNotifyDataAdapter(context).getNotifyMode(insideNotificationItem);
        if (!TextUtils.isEmpty(insideNotificationItem.getPurePicUrl()) && list != null && list.size() > 1 && list.get(1) != null) {
            notifyMode = 1;
        }
        if (notifyMode == 2) {
            pushNotificationBySystem(context, list, insideNotificationItem, j, i, aVar);
            AppMethodBeat.o(1104);
            return;
        }
        if (notifyMode == 1) {
            pushNotificationByCustom(context, list, insideNotificationItem, j, aVar);
        }
        AppMethodBeat.o(1104);
    }

    private static synchronized void initAdapter(Context context) {
        synchronized (NotifyAdapterUtil.class) {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_TEXT_SELECTION_SMART_MULTI, false);
            if (sNotificationManager == null) {
                sNotificationManager = (NotificationManager) context.getSystemService("notification");
            }
            if (Build.VERSION.SDK_INT >= 26 && sNotificationManager != null) {
                NotificationChannel notificationChannel = sNotificationManager.getNotificationChannel("default");
                if (notificationChannel != null) {
                    CharSequence name = notificationChannel.getName();
                    if (PUSH_ZH.equals(name) || PUSH_EN.equals(name)) {
                        sNotificationManager.deleteNotificationChannel("default");
                    }
                }
                NotificationChannel notificationChannel2 = new NotificationChannel(PRIMARY_CHANNEL, isZh(context) ? PUSH_ZH : PUSH_EN, 4);
                notificationChannel2.setLightColor(Color.GREEN);
                notificationChannel2.enableVibration(true);
                notificationChannel2.setLockscreenVisibility(1);
                sNotificationManager.createNotificationChannel(notificationChannel2);
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_TEXT_SELECTION_SMART_MULTI);
        }
    }

    private static void pushNotificationByCustom(Context context, List<Bitmap> list, InsideNotificationItem insideNotificationItem, long j, l.a aVar) {
        Notification notification;
        Bitmap bitmap;
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_TEXT_SELECTION_DRAG, false);
        Resources resources = context.getResources();
        String packageName = context.getPackageName();
        String title = insideNotificationItem.getTitle();
        int defaultNotifyIcon = NotifyUtil.getNotifyDataAdapter(context).getDefaultNotifyIcon();
        int intValue = Integer.valueOf(context.getApplicationInfo().icon).intValue();
        if (Build.VERSION.SDK_INT >= 26) {
            Notification.Builder builder = new Notification.Builder(context, PRIMARY_CHANNEL);
            if (defaultNotifyIcon > 0) {
                Bundle bundle = new Bundle();
                bundle.putInt("vivo.summaryIconRes", defaultNotifyIcon);
                builder.setExtras(bundle);
            }
            notification = builder.build();
        } else {
            notification = new Notification();
        }
        notification.priority = 2;
        notification.flags = 16;
        notification.tickerText = title;
        int defaultSmallIconId = NotifyUtil.getNotifyDataAdapter(context).getDefaultSmallIconId();
        if (defaultSmallIconId <= 0) {
            defaultSmallIconId = intValue;
        }
        notification.icon = defaultSmallIconId;
        RemoteViews remoteViews = new RemoteViews(packageName, NotifyUtil.getNotifyLayoutAdapter(context).getNotificationLayout());
        remoteViews.setTextViewText(resources.getIdentifier("notify_title", "id", packageName), title);
        remoteViews.setTextColor(resources.getIdentifier("notify_title", "id", packageName), NotifyUtil.getNotifyLayoutAdapter(context).getTitleColor());
        remoteViews.setTextViewText(resources.getIdentifier("notify_msg", "id", packageName), insideNotificationItem.getContent());
        if (insideNotificationItem.isShowTime()) {
            remoteViews.setTextViewText(resources.getIdentifier("notify_when", "id", packageName), new SimpleDateFormat("HH:mm", Locale.CHINA).format(new Date()));
            remoteViews.setViewVisibility(resources.getIdentifier("notify_when", "id", packageName), 0);
        } else {
            remoteViews.setViewVisibility(resources.getIdentifier("notify_when", "id", packageName), 8);
        }
        int suitIconId = NotifyUtil.getNotifyLayoutAdapter(context).getSuitIconId();
        remoteViews.setViewVisibility(suitIconId, 0);
        if (list == null || list.isEmpty() || (bitmap = list.get(0)) == null) {
            if (defaultNotifyIcon > 0) {
                intValue = defaultNotifyIcon;
            }
            remoteViews.setImageViewResource(suitIconId, intValue);
        } else {
            remoteViews.setImageViewBitmap(suitIconId, bitmap);
        }
        Bitmap bitmap2 = null;
        if (list != null && list.size() > 1) {
            bitmap2 = list.get(1);
        }
        if (bitmap2 == null) {
            remoteViews.setViewVisibility(resources.getIdentifier("notify_cover", "id", packageName), 8);
        } else if (!TextUtils.isEmpty(insideNotificationItem.getPurePicUrl())) {
            remoteViews.setViewVisibility(resources.getIdentifier("notify_content", "id", packageName), 8);
            remoteViews.setViewVisibility(resources.getIdentifier("notify_cover", "id", packageName), 8);
            remoteViews.setViewVisibility(resources.getIdentifier("notify_pure_cover", "id", packageName), 0);
            remoteViews.setImageViewBitmap(resources.getIdentifier("notify_pure_cover", "id", packageName), bitmap2);
        } else {
            remoteViews.setViewVisibility(resources.getIdentifier("notify_cover", "id", packageName), 0);
            remoteViews.setImageViewBitmap(resources.getIdentifier("notify_cover", "id", packageName), bitmap2);
        }
        notification.contentView = remoteViews;
        if (Build.VERSION.SDK_INT >= 16 && TextUtils.isEmpty(insideNotificationItem.getPurePicUrl())) {
            notification.bigContentView = remoteViews;
        }
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        int ringerMode = audioManager.getRingerMode();
        int vibrateSetting = audioManager.getVibrateSetting(0);
        n.d(TAG, "ringMode=" + ringerMode + " callVibrateSetting=" + vibrateSetting);
        int notifyType = insideNotificationItem.getNotifyType();
        if (notifyType != 2) {
            if (notifyType != 3) {
                if (notifyType == 4) {
                    if (ringerMode == 2) {
                        notification.defaults = 1;
                    }
                    if (vibrateSetting == 1) {
                        notification.defaults |= 2;
                        notification.vibrate = new long[]{0, 100, 200, 300};
                    }
                }
            } else if (vibrateSetting == 1) {
                notification.defaults = 2;
                notification.vibrate = new long[]{0, 100, 200, 300};
            }
        } else if (ringerMode == 2) {
            notification.defaults = 1;
        }
        Intent intent = new Intent("com.vivo.pushservice.action.RECEIVE");
        intent.setPackage(context.getPackageName());
        intent.setClassName(context.getPackageName(), "com.vivo.push.sdk.service.CommandService");
        intent.putExtra("command_type", "reflect_receiver");
        new p(packageName, j, insideNotificationItem).a(intent);
        notification.contentIntent = PendingIntent.getService(context, (int) SystemClock.uptimeMillis(), intent, 268435456);
        if (sNotificationManager != null) {
            int i = d.a().l;
            if (i == 0) {
                try {
                    sNotificationManager.notify(sNotifyId, notification);
                    if (aVar != null) {
                        aVar.a();
                        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_TEXT_SELECTION_DRAG);
                        return;
                    }
                } catch (Exception e) {
                    n.a(TAG, e);
                    if (aVar != null) {
                        aVar.b();
                    }
                }
            } else if (i == 1) {
                sNotificationManager.notify((int) j, notification);
                if (aVar != null) {
                    aVar.a();
                    AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_TEXT_SELECTION_DRAG);
                    return;
                }
            } else {
                n.a(TAG, "unknow notify style " + i);
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_TEXT_SELECTION_DRAG);
            return;
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_TEXT_SELECTION_DRAG);
    }

    private static void pushNotificationBySystem(Context context, List<Bitmap> list, InsideNotificationItem insideNotificationItem, long j, int i, l.a aVar) {
        Bitmap bitmap;
        Notification.Builder builder;
        Bitmap bitmap2;
        int i2;
        Bitmap decodeResource;
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_SELECTION_SINCE_PREVIOUS, false);
        String packageName = context.getPackageName();
        String title = insideNotificationItem.getTitle();
        String content = insideNotificationItem.getContent();
        int intValue = Integer.valueOf(context.getApplicationInfo().icon).intValue();
        boolean isShowTime = insideNotificationItem.isShowTime();
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        int defaultNotifyIcon = NotifyUtil.getNotifyDataAdapter(context).getDefaultNotifyIcon();
        if (list == null || list.isEmpty()) {
            bitmap = null;
        } else {
            Bitmap bitmap3 = list.get(0);
            if (bitmap3 == null || defaultNotifyIcon <= 0 || (decodeResource = BitmapFactory.decodeResource(context.getResources(), defaultNotifyIcon)) == null) {
                bitmap = bitmap3;
            } else {
                int width = decodeResource.getWidth();
                int height = decodeResource.getHeight();
                decodeResource.recycle();
                bitmap = b.a(bitmap3, width, height);
            }
        }
        if (Build.VERSION.SDK_INT >= 26) {
            builder = new Notification.Builder(context, PRIMARY_CHANNEL);
            if (defaultNotifyIcon > 0) {
                Bundle bundle = new Bundle();
                bundle.putInt("vivo.summaryIconRes", defaultNotifyIcon);
                builder.setExtras(bundle);
            }
            if (bitmap != null) {
                builder.setLargeIcon(bitmap);
            }
        } else {
            builder = new Notification.Builder(context);
            if (bitmap != null) {
                builder.setLargeIcon(bitmap);
            } else if (Build.VERSION.SDK_INT <= 22) {
                builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), intValue));
            }
        }
        int defaultSmallIconId = NotifyUtil.getNotifyDataAdapter(context).getDefaultSmallIconId();
        if (defaultSmallIconId > 0) {
            intValue = defaultSmallIconId;
        }
        builder.setSmallIcon(intValue);
        if (insideNotificationItem.getCompatibleType() != 1) {
            builder.setContentTitle(title);
        }
        builder.setPriority(2);
        builder.setContentText(content);
        builder.setWhen(isShowTime ? System.currentTimeMillis() : 0);
        builder.setShowWhen(isShowTime);
        builder.setTicker(title);
        int ringerMode = audioManager.getRingerMode();
        int notifyType = insideNotificationItem.getNotifyType();
        if (notifyType != 2) {
            if (notifyType != 3) {
                if (notifyType == 4) {
                    if (ringerMode == 2) {
                        builder.setDefaults(3);
                        builder.setVibrate(new long[]{0, 100, 200, 300});
                    } else if (ringerMode == 1) {
                        builder.setDefaults(2);
                        builder.setVibrate(new long[]{0, 100, 200, 300});
                    }
                }
            } else if (ringerMode == 2) {
                builder.setDefaults(2);
                builder.setVibrate(new long[]{0, 100, 200, 300});
            }
        } else if (ringerMode == 2) {
            builder.setDefaults(1);
        }
        if (list == null || list.size() <= 1) {
            i2 = i;
            bitmap2 = null;
        } else {
            bitmap2 = list.get(1);
            i2 = i;
        }
        if (!(i2 == 1 || bitmap2 == null)) {
            Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle();
            bigPictureStyle.setBigContentTitle(title);
            bigPictureStyle.setSummaryText(content);
            bigPictureStyle.bigPicture(bitmap2);
            builder.setStyle(bigPictureStyle);
        }
        builder.setAutoCancel(true);
        Intent intent = new Intent("com.vivo.pushservice.action.RECEIVE");
        intent.setPackage(context.getPackageName());
        intent.setClassName(context.getPackageName(), "com.vivo.push.sdk.service.CommandService");
        intent.putExtra("command_type", "reflect_receiver");
        new p(packageName, j, insideNotificationItem).a(intent);
        builder.setContentIntent(PendingIntent.getService(context, (int) SystemClock.uptimeMillis(), intent, 268435456));
        Notification build = builder.build();
        int i3 = d.a().l;
        NotificationManager notificationManager = sNotificationManager;
        if (notificationManager != null) {
            if (i3 == 0) {
                try {
                    notificationManager.notify(sNotifyId, build);
                    if (aVar != null) {
                        aVar.a();
                        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_SELECTION_SINCE_PREVIOUS);
                        return;
                    }
                } catch (Exception e) {
                    n.a(TAG, e);
                    if (aVar != null) {
                        aVar.b();
                    }
                }
            } else if (i3 == 1) {
                notificationManager.notify((int) j, build);
                if (aVar != null) {
                    aVar.a();
                    AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_SELECTION_SINCE_PREVIOUS);
                    return;
                }
            } else {
                n.a(TAG, "unknow notify style " + i3);
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_SELECTION_SINCE_PREVIOUS);
            return;
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_SELECTION_SINCE_PREVIOUS);
    }

    public static boolean repealNotifyById(Context context, long j) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_SELECTION_RANGE, false);
        int i = d.a().l;
        if (i == 0) {
            long b = v.b().b("com.vivo.push.notify_key", -1);
            if (b == j) {
                n.d(TAG, "undo showed message " + j);
                n.a(context, "\u56de\u6536\u5df2\u5c55\u793a\u7684\u901a\u77e5\uff1a " + j);
                boolean cancelNotify = cancelNotify(context, sNotifyId);
                AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_SELECTION_RANGE);
                return cancelNotify;
            }
            n.d(TAG, "current showing message id " + b + " not match " + j);
            n.a(context, "\u4e0e\u5df2\u5c55\u793a\u7684\u901a\u77e5" + b + "\u4e0e\u5f85\u56de\u6536\u7684\u901a\u77e5" + j + "\u4e0d\u5339\u914d");
            AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_SELECTION_RANGE);
            return false;
        } else if (i == 1) {
            boolean cancelNotify2 = cancelNotify(context, (int) j);
            AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_SELECTION_RANGE);
            return cancelNotify2;
        } else {
            n.a(TAG, "unknow cancle notify style " + i);
            AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_SELECTION_RANGE);
            return false;
        }
    }

    private static boolean cancelNotify(Context context, int i) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.AUTOFILL_DATASET_AUTHENTICATED, false);
        initAdapter(context);
        NotificationManager notificationManager = sNotificationManager;
        if (notificationManager != null) {
            notificationManager.cancel(i);
            AppMethodBeat.o(MetricsProto.MetricsEvent.AUTOFILL_DATASET_AUTHENTICATED);
            return true;
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.AUTOFILL_DATASET_AUTHENTICATED);
        return false;
    }

    public static void cancelNotify(Context context) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_AUTOFILL_SAVE_TYPE, false);
        cancelNotify(context, sNotifyId);
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_AUTOFILL_SAVE_TYPE);
    }

    public static void setNotifyId(int i) {
        sNotifyId = i;
    }

    private static boolean isZh(Context context) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.AUTOFILL_SAVE_VALIDATION, false);
        boolean endsWith = context.getResources().getConfiguration().locale.getLanguage().endsWith("zh");
        AppMethodBeat.o(MetricsProto.MetricsEvent.AUTOFILL_SAVE_VALIDATION);
        return endsWith;
    }
}
