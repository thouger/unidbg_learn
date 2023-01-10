package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;
import com.taobao.agoo.TaobaoBaseIntentService;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.m;
import org.json.JSONObject;

public abstract class UmengBaseIntentService extends TaobaoBaseIntentService {
    private static final String a = UmengBaseIntentService.class.getName();

    /* access modifiers changed from: protected */
    @Override // com.taobao.agoo.TaobaoBaseIntentService
    public void onRegistered(Context context, String str) {
    }

    /* access modifiers changed from: protected */
    @Override // com.taobao.agoo.TaobaoBaseIntentService
    public void onUnregistered(Context context, String str) {
    }

    /* access modifiers changed from: protected */
    @Override // com.taobao.agoo.TaobaoBaseIntentService
    public void onMessage(Context context, Intent intent) {
        try {
            MLog.i(a, "--->>> UmengBaseIntentService onMessage");
            if (Process.getElapsedCpuTime() < 3000) {
                UMLog.mutlInfo(a, 2, "\u5e94\u7528\u7a0b\u5e8f\u901a\u8fc7\u63a8\u9001\u6d88\u606f\u542f\u52a8");
                PushAgent.setAppLaunchByMessage();
            }
            String stringExtra = intent.getStringExtra("body");
            String str = a;
            UMLog.mutlInfo(str, 2, "onMessage():[" + stringExtra + "]");
            try {
                UMessage uMessage = new UMessage(new JSONObject(stringExtra));
                uMessage.message_id = intent.getStringExtra("id");
                uMessage.task_id = intent.getStringExtra("task_id");
                UTrack.getInstance(getApplicationContext()).trackMsgArrival(uMessage);
                m.a(context).a(uMessage.message_id, uMessage.task_id, uMessage.display_type);
                if (TextUtils.equals(UMessage.DISPLAY_TYPE_AUTOUPDATE, uMessage.display_type)) {
                    String stringExtra2 = intent.getStringExtra("id");
                    String stringExtra3 = intent.getStringExtra("task_id");
                    Intent intent2 = new Intent();
                    intent2.setPackage(context.getPackageName());
                    intent2.setAction(MsgConstant.MESSAGE_AUTOUPDATE_HANDLER_ACTION);
                    intent2.putExtra("body", stringExtra);
                    intent2.putExtra("id", stringExtra2);
                    intent2.putExtra("task_id", stringExtra3);
                }
            } catch (Exception e) {
                e.printStackTrace();
                UMLog.mutlInfo(a, 0, e.toString());
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.taobao.agoo.TaobaoBaseIntentService
    public void onError(Context context, String str) {
        String str2 = a;
        UMLog.mutlInfo(str2, 0, "onError()[" + str + "]");
    }
}
