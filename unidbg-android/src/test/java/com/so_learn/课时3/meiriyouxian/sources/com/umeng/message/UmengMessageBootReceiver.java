package com.umeng.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.common.e;
import com.umeng.message.proguard.m;
import java.util.Iterator;

public class UmengMessageBootReceiver extends BroadcastReceiver {
    private static final String b = UmengMessageBootReceiver.class.getName();
    private static final String c = "android.intent.action.BOOT_COMPLETED";
    Runnable a = new AnonymousClass1();
    private Context d;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            UMLog.mutlInfo(b, 2, "Boot this system , UmengMessageBootReceiver onReceive()");
            String action = intent.getAction();
            if (action == null) {
                return;
            }
            if (!action.equals("")) {
                String str = b;
                UMLog.mutlInfo(str, 2, "action=" + intent.getAction());
                if (TextUtils.equals(intent.getAction(), "android.intent.action.BOOT_COMPLETED")) {
                    this.d = context;
                    e.a(this.a);
                }
            }
        } catch (Exception e) {
            UMLog.mutlInfo(b, 0, e.toString());
        }
    }

    /* renamed from: com.umeng.message.UmengMessageBootReceiver$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Iterator<m.c> it2 = m.a(UmengMessageBootReceiver.this.d).b().iterator();
                while (it2.hasNext()) {
                    m.c next = it2.next();
                    if (m.a(UmengMessageBootReceiver.this.d).a(next.a) == null && next.b.equals("notification")) {
                        m.a(UmengMessageBootReceiver.this.d).a(next.a, 2, System.currentTimeMillis(), "");
                    }
                }
                Iterator<m.d> it3 = m.a(UmengMessageBootReceiver.this.d).d().iterator();
                while (it3.hasNext()) {
                    m.d next2 = it3.next();
                    if (m.a(UmengMessageBootReceiver.this.d).c(next2.a) == null && next2.c.equals("notification")) {
                        m.a(UmengMessageBootReceiver.this.d).a(next2.a, next2.b, "9", System.currentTimeMillis());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                UMLog.mutlInfo(UmengMessageBootReceiver.b, 2, e.toString());
            }
        }
    }
}
