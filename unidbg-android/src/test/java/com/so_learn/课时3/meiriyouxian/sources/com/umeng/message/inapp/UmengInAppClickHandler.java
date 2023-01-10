package com.umeng.message.inapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.entity.UInAppMessage;

public class UmengInAppClickHandler implements UInAppHandler {
    private static final String a = UmengInAppClickHandler.class.getName();
    private String b = null;
    private String c = null;
    private String d = null;

    @Override // com.umeng.message.inapp.UInAppHandler
    public final void handleInAppMessage(Activity activity, UInAppMessage uInAppMessage, int i) {
        switch (i) {
            case 16:
                this.b = uInAppMessage.action_type;
                this.c = uInAppMessage.action_activity;
                this.d = uInAppMessage.action_url;
                break;
            case 17:
                this.b = uInAppMessage.bottom_action_type;
                this.c = uInAppMessage.bottom_action_activity;
                this.d = uInAppMessage.bottom_action_url;
                break;
            case 18:
                this.b = uInAppMessage.plainTextActionType;
                this.c = uInAppMessage.plainTextActivity;
                this.d = uInAppMessage.plainTextUrl;
                break;
            case 19:
                this.b = uInAppMessage.customButtonActionType;
                this.c = uInAppMessage.customButtonActivity;
                this.d = uInAppMessage.customButtonUrl;
                break;
        }
        if (TextUtils.isEmpty(this.b)) {
            return;
        }
        if (TextUtils.equals("go_activity", this.b)) {
            openActivity(activity, this.c);
        } else if (TextUtils.equals("go_url", this.b)) {
            openUrl(activity, this.d);
        } else {
            TextUtils.equals("go_app", this.b);
        }
    }

    public void openActivity(Activity activity, String str) {
        if (str != null) {
            try {
                if (!TextUtils.isEmpty(str.trim())) {
                    String str2 = a;
                    UMLog.mutlInfo(str2, 2, "\u6253\u5f00Activity: " + str);
                    Intent intent = new Intent();
                    intent.setClassName(activity, str);
                    intent.setFlags(536870912);
                    activity.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void openUrl(Activity activity, String str) {
        if (str != null) {
            try {
                if (!TextUtils.isEmpty(str.trim())) {
                    String str2 = a;
                    UMLog.mutlInfo(str2, 2, "\u6253\u5f00\u94fe\u63a5: " + str);
                    activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
