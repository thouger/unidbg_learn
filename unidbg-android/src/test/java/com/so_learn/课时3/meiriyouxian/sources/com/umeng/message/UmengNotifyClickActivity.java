package com.umeng.message;

import android.content.Intent;
import android.os.Bundle;
import com.taobao.agoo.BaseNotifyClickActivity;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.entity.UMessage;
import org.json.JSONException;
import org.json.JSONObject;

public class UmengNotifyClickActivity extends BaseNotifyClickActivity {
    private static final String a = UmengNotifyClickActivity.class.getName();

    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.onStart();
    }

    @Override // com.taobao.agoo.BaseNotifyClickActivity
    public void onMessage(Intent intent) {
        try {
            String stringExtra = intent.getStringExtra("body");
            String str = a;
            UMLog.mutlInfo(str, 2, "onMessage():[" + stringExtra + "]");
            try {
                UMessage uMessage = new UMessage(new JSONObject(stringExtra));
                uMessage.message_id = intent.getStringExtra("id");
                uMessage.task_id = intent.getStringExtra("task_id");
                UTrack.getInstance(this).trackMiPushMsgClick(uMessage);
            } catch (JSONException e) {
                e.printStackTrace();
                UMLog.mutlInfo(a, 2, e.toString());
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void a(Intent intent) {
        super.onNewIntent(intent);
    }
}
