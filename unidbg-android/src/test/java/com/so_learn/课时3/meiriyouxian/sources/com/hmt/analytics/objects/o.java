package com.hmt.analytics.objects;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hmt.analytics.android.a;
import com.hmt.analytics.task.b;
import com.hmt.analytics.util.j;

/* compiled from: WakeMonitorReceiver */
public class o extends BroadcastReceiver {
    private static final String a = o.class.getSimpleName();
    private b b;

    /* compiled from: WakeMonitorReceiver */
    /* renamed from: com.hmt.analytics.objects.o$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ Intent a;
        final /* synthetic */ Context b;

        AnonymousClass1(Intent intent, Context context) {
            this.a = intent;
            this.b = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (o.this.b == null) {
                a.a(o.a, "Collected:wake monitor task is null!!!!");
                return;
            }
            String stringExtra = this.a.getStringExtra("HMT_CODE");
            String stringExtra2 = this.a.getStringExtra("HMT_CHANNEL");
            String stringExtra3 = this.a.getStringExtra("HMT_MESSAGE");
            String stringExtra4 = this.a.getStringExtra("HMT_SOURCE");
            String stringExtra5 = this.a.getStringExtra("HMT_WAKEUP_WAY");
            com.hmt.analytics.util.a aVar = new com.hmt.analytics.util.a();
            aVar.a("hmt_code", stringExtra);
            aVar.a("hmt_channel", stringExtra2);
            aVar.a("hmt_message", stringExtra3);
            aVar.a("hmt_source", stringExtra4);
            aVar.a("hmt_wakeup_way", stringExtra5);
            aVar.a("wake_id", o.this.b.a());
            com.hmt.analytics.a.a(this.b, "wake_idmapping", aVar);
            o.this.b.a(stringExtra2);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        a.a(a, "WakeMonitorReceiver");
        j.b().execute(new AnonymousClass1(intent, context));
    }
}
