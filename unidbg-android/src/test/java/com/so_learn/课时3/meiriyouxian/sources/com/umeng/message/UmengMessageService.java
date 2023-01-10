package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.service.UMJobIntentService;

public abstract class UmengMessageService extends UMJobIntentService {
    private static final String a = UmengMessageService.class.getSimpleName();

    public abstract void onMessage(Context context, Intent intent);

    /* access modifiers changed from: protected */
    @Override // com.umeng.message.service.UMJobIntentService, com.umeng.message.service.JobIntentService
    public void a(Intent intent) {
        try {
            onMessage(this, intent);
            String stringExtra = intent.getStringExtra("body");
            String str = a;
            UMLog.mutlInfo(str, 2, "message:" + stringExtra);
        } catch (Throwable unused) {
        }
    }
}
