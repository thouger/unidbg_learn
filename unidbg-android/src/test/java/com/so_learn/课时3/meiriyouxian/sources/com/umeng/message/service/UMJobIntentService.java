package com.umeng.message.service;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import java.util.HashMap;

public class UMJobIntentService extends JobIntentService {
    private static int a = 21000;
    private static HashMap<Class<? extends UMJobIntentService>, Integer> b = new HashMap<>();
    private static Object c = new Object();

    public static void enqueueWork(Context context, Class<? extends UMJobIntentService> cls, Intent intent) {
        int i;
        synchronized (c) {
            MLog.i("wuchi", "--->>> UMJobIntentService enqueueWork, cla is " + cls.getName());
            if (!(context == null || cls == null)) {
                if (intent != null) {
                    if (!b.containsKey(cls)) {
                        i = a + b.size();
                        b.put(cls, Integer.valueOf(i));
                    } else {
                        i = b.get(cls).intValue();
                    }
                    MLog.i("wuchi", "UMJobIntentService cla jobId is " + i);
                    enqueueWork(context, cls, i, intent);
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v4, resolved type: java.util.HashMap<java.lang.Class<? extends com.umeng.message.service.UMJobIntentService>, java.lang.Integer> */
    /* JADX WARN: Multi-variable type inference failed */
    public static void enqueueWork(Context context, String str, Intent intent) {
        int i;
        synchronized (c) {
            MLog.i("wuchi", "--->>> UMJobIntentService enqueueWork, className is " + str);
            if (context != null && !TextUtils.isEmpty(str) && intent != null) {
                Class<?> cls = null;
                try {
                    cls = Class.forName(str);
                } catch (ClassNotFoundException unused) {
                }
                if (cls == null) {
                    MLog.i("wuchi", "UMJobIntentService cla == null");
                    return;
                }
                if (!b.containsKey(cls)) {
                    i = a + b.size();
                    b.put(cls, Integer.valueOf(i));
                } else {
                    i = b.get(cls).intValue();
                }
                MLog.i("wuchi", "UMJobIntentService className jobId is " + i);
                enqueueWork(context, cls, i, intent);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.umeng.message.service.JobIntentService
    public void a(Intent intent) {
        MLog.i("wuchi", "--->>> UMJobIntentService onHandleWork");
    }
}
