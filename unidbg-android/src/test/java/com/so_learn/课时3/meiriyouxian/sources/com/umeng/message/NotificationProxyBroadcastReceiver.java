package com.umeng.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.entity.UMessage;
import com.umeng.message.entity.UNotificationItem;
import org.json.JSONObject;

public class NotificationProxyBroadcastReceiver extends BroadcastReceiver {
    public static final int EXTRA_ACTION_CLICK = 10;
    public static final int EXTRA_ACTION_DISMISS = 11;
    public static final int EXTRA_ACTION_NOT_EXIST = -1;
    public static final String EXTRA_KEY_ACTION = "ACTION";
    public static final String EXTRA_KEY_MESSAGE_ID = "MESSAGE_ID";
    public static final String EXTRA_KEY_MSG = "MSG";
    public static final String EXTRA_KEY_NOTIFICATION_ID = "NOTIFICATION_ID";
    public static final String EXTRA_KEY_TASK_ID = "TASK_ID";
    public static final int LOCAL_ACTION_CLICK = 12;
    private static final String a = NotificationProxyBroadcastReceiver.class.getName();
    private UHandler b;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(EXTRA_KEY_MSG);
        try {
            int intExtra = intent.getIntExtra(EXTRA_KEY_ACTION, -1);
            UMLog.mutlInfo(a, 2, String.format("onReceive[msg=%s, action=%d]", stringExtra, Integer.valueOf(intExtra)));
            if (intExtra == 12) {
                a(context);
                return;
            }
            UMessage uMessage = new UMessage(new JSONObject(stringExtra));
            int intExtra2 = intent.getIntExtra(EXTRA_KEY_NOTIFICATION_ID, -1);
            uMessage.message_id = intent.getStringExtra(EXTRA_KEY_MESSAGE_ID);
            uMessage.task_id = intent.getStringExtra(EXTRA_KEY_TASK_ID);
            if (intExtra == 10) {
                UMLog.mutlInfo(a, 2, "\u70b9\u51fb\u901a\u77e5");
                UTrack.getInstance(context).setClearPrevMessage(true);
                UTrack.getInstance(context).trackMsgClick(uMessage);
                this.b = PushAgent.getInstance(context).getNotificationClickHandler();
                if (this.b != null) {
                    uMessage.clickOrDismiss = true;
                    this.b.handleMessage(context, uMessage);
                }
            } else if (intExtra == 11) {
                UMLog.mutlInfo(a, 2, "\u5ffd\u7565\u901a\u77e5");
                UTrack.getInstance(context).setClearPrevMessage(true);
                UTrack.getInstance(context).trackMsgDismissed(uMessage);
                this.b = PushAgent.getInstance(context).getNotificationClickHandler();
                if (this.b != null) {
                    uMessage.clickOrDismiss = false;
                    this.b.handleMessage(context, uMessage);
                }
            }
            MessageNotificationQueue.getInstance().remove(new UNotificationItem(intExtra2, uMessage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage == null) {
            String str = a;
            UMLog.mutlInfo(str, 0, "\u627e\u4e0d\u5230\u5e94\u7528: " + context.getPackageName());
            return;
        }
        launchIntentForPackage.setPackage(null);
        launchIntentForPackage.addFlags(268435456);
        try {
            context.startActivity(launchIntentForPackage);
            String str2 = a;
            UMLog.mutlInfo(str2, 2, "\u6253\u5f00\u5e94\u7528: " + context.getPackageName());
        } catch (Exception unused) {
            String str3 = a;
            UMLog.mutlInfo(str3, 2, "\u6253\u5f00\u5e94\u7528\u5931\u8d25: " + context.getPackageName());
        }
    }
}
