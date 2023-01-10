package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.c;
import com.umeng.message.proguard.m;
import java.lang.reflect.Method;
import java.util.Map;
import org.json.JSONObject;

public class UmengNotificationClickHandler implements UHandler {
    private static final String a = UmengNotificationClickHandler.class.getName();

    public void dealWithCustomAction(Context context, UMessage uMessage) {
    }

    public void dismissNotification(Context context, UMessage uMessage) {
    }

    @Override // com.umeng.message.UHandler
    public void handleMessage(Context context, UMessage uMessage) {
        try {
            if (!uMessage.clickOrDismiss) {
                dismissNotification(context, uMessage);
            } else if (!TextUtils.equals(UMessage.DISPLAY_TYPE_AUTOUPDATE, uMessage.display_type) || !PushAgent.getInstance(context).isIncludesUmengUpdateSDK()) {
                if (!TextUtils.isEmpty(uMessage.after_open)) {
                    if (UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
                        if (TextUtils.equals(UMessage.NOTIFICATION_GO_APPURL, uMessage.after_open)) {
                            a(context, uMessage);
                            return;
                        }
                    } else if (TextUtils.equals("go_url", uMessage.after_open)) {
                        openUrl(context, uMessage);
                        return;
                    } else if (TextUtils.equals("go_activity", uMessage.after_open)) {
                        openActivity(context, uMessage);
                        return;
                    } else if (TextUtils.equals(UMessage.NOTIFICATION_GO_CUSTOM, uMessage.after_open)) {
                        dealWithCustomAction(context, uMessage);
                        return;
                    } else if (TextUtils.equals("go_app", uMessage.after_open)) {
                        launchApp(context, uMessage);
                        return;
                    }
                }
                if (!UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
                    if (uMessage.url != null && !TextUtils.isEmpty(uMessage.url.trim())) {
                        openUrl(context, uMessage);
                    } else if (uMessage.activity != null && !TextUtils.isEmpty(uMessage.activity.trim())) {
                        openActivity(context, uMessage);
                    } else if (uMessage.custom == null || TextUtils.isEmpty(uMessage.custom.trim())) {
                        launchApp(context, uMessage);
                    } else {
                        dealWithCustomAction(context, uMessage);
                    }
                }
            } else {
                autoUpdate(context, uMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void autoUpdate(Context context, UMessage uMessage) {
        try {
            Object g = m.a(context).g();
            Class<?> cls = Class.forName("com.umeng.update.UmengUpdateAgent");
            Class<?> cls2 = Class.forName("com.umeng.update.UpdateResponse");
            Method method = cls.getMethod("showUpdateDialog", Context.class, cls2);
            if (g != null) {
                method.invoke(cls, context, cls2.cast(g));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(Context context, UMessage uMessage) {
        String str = "";
        try {
            String str2 = uMessage.custom;
            if (str2 == null) {
                return;
            }
            if (!str2.equals(str)) {
                JSONObject jSONObject = new JSONObject(str2);
                String optString = jSONObject.optString("p");
                String optString2 = jSONObject.optString("pu");
                String optString3 = jSONObject.optString("ju");
                int optInt = jSONObject.optInt("en");
                Intent intent = new Intent();
                String[] split = optString2.split(HttpConstant.SCHEME_SPLIT);
                if (split == null || split.length >= 2) {
                    String[] split2 = split[1].split(NotificationIconUtil.SPLIT_CHAR);
                    if (split2 == null || split2.length >= 1) {
                        String str3 = split2[0];
                        if (optInt == 1) {
                            String[] split3 = optString2.split(str3 + NotificationIconUtil.SPLIT_CHAR);
                            try {
                                StringBuilder sb = new StringBuilder();
                                if (split3 != null && split3.length >= 2) {
                                    sb.append(split3[1]);
                                }
                                sb.append("&umessage=");
                                sb.append(uMessage.getRaw().toString());
                                sb.append("&thirdkey=");
                                sb.append(PushAgent.getInstance(context).getMessageAppkey());
                                UMLog.mutlInfo(a, 2, "url:" + sb.toString());
                                str = c.a(sb.toString());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            optString2 = split3[0] + str3 + NotificationIconUtil.SPLIT_CHAR + str;
                        }
                        intent.setData(Uri.parse(optString2));
                        intent.setPackage(optString);
                        intent.addFlags(268435456);
                        if (UmengMessageDeviceConfig.getDataData(optString)) {
                            try {
                                if (UmengMessageDeviceConfig.isIntentExist(context, optString2, optString)) {
                                    UTrack.getInstance(context).trackMsgPulled(uMessage, 62);
                                    context.startActivity(intent);
                                    return;
                                }
                                UTrack.getInstance(context).trackMsgPulled(uMessage, 61);
                                if (optString3 == null) {
                                    return;
                                }
                                if (!TextUtils.isEmpty(optString3.trim())) {
                                    Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(optString3));
                                    intent2.addFlags(268435456);
                                    context.startActivity(intent2);
                                }
                            } catch (Exception unused) {
                            }
                        } else {
                            UTrack.getInstance(context).trackMsgPulled(uMessage, 60);
                            if (optString3 != null && !TextUtils.isEmpty(optString3.trim())) {
                                Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse(optString3));
                                intent3.addFlags(268435456);
                                context.startActivity(intent3);
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void openUrl(Context context, UMessage uMessage) {
        if (uMessage.url != null && !TextUtils.isEmpty(uMessage.url.trim())) {
            String str = a;
            UMLog.mutlInfo(str, 2, "\u6253\u5f00\u94fe\u63a5: " + uMessage.url);
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(uMessage.url));
            a(intent, uMessage);
            intent.addFlags(268435456);
            try {
                context.startActivity(intent);
            } catch (Exception unused) {
            }
        }
    }

    public void openActivity(Context context, UMessage uMessage) {
        if (uMessage.activity != null && !TextUtils.isEmpty(uMessage.activity.trim())) {
            Intent intent = new Intent();
            a(intent, uMessage);
            intent.setClassName(context, uMessage.activity);
            intent.addFlags(268435456);
            try {
                context.startActivity(intent);
            } catch (Exception unused) {
            }
        }
    }

    public void launchApp(Context context, UMessage uMessage) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage == null) {
            String str = a;
            UMLog.mutlInfo(str, 0, "\u627e\u4e0d\u5230\u5e94\u7528: " + context.getPackageName());
            return;
        }
        launchIntentForPackage.setPackage(null);
        launchIntentForPackage.addFlags(268435456);
        a(launchIntentForPackage, uMessage);
        try {
            context.startActivity(launchIntentForPackage);
            String str2 = a;
            UMLog.mutlInfo(str2, 0, "\u542f\u52a8\u5e94\u7528: " + context.getPackageName());
        } catch (Exception unused) {
        }
    }

    private Intent a(Intent intent, UMessage uMessage) {
        if (!(intent == null || uMessage == null || uMessage.extra == null)) {
            for (Map.Entry<String, String> entry : uMessage.extra.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key != null) {
                    intent.putExtra(key, value);
                }
            }
        }
        return intent;
    }
}
