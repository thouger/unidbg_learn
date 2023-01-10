package com.umeng.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.proguard.h;

public class NetworkReceiver extends BroadcastReceiver {
    private static final String a = "com.umeng.message.UmengLocationService";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        boolean isOnline = UmengMessageDeviceConfig.isOnline(context);
        boolean c = h.c(context, a);
        if (!isOnline && c) {
            Intent intent2 = new Intent();
            intent2.setAction(MsgConstant.MESSAGE_LBS_ACTION);
            intent2.setPackage(context.getPackageName());
            intent2.putExtra("stopTimer", true);
        }
        if (isOnline && c) {
            Intent intent3 = new Intent();
            intent3.setAction(MsgConstant.MESSAGE_LBS_ACTION);
            intent3.setPackage(context.getPackageName());
        }
    }
}
