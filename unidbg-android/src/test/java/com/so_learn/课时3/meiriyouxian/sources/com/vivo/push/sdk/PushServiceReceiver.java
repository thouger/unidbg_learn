package com.vivo.push.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.umeng.message.proguard.l;
import com.vivo.push.PushClient;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.d;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.n;
import com.vivo.push.util.p;
import com.xiaomi.mipush.sdk.Constants;

public class PushServiceReceiver extends BroadcastReceiver {
    private static HandlerThread a = null;
    private static Handler b = null;
    private static a c = new a();

    static {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ENTERPRISE_PRIVACY_PERMISSIONS, false);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ENTERPRISE_PRIVACY_PERMISSIONS);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.APPLICATIONS_STORAGE_MOVIES, false);
        Context context2 = ContextDelegate.getContext(context);
        String action = intent.getAction();
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action) || Intent.ACTION_POWER_CONNECTED.equals(action) || Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
            if (a == null) {
                HandlerThread handlerThread = new HandlerThread("PushServiceReceiver");
                a = handlerThread;
                handlerThread.start();
                b = new Handler(a.getLooper());
            }
            n.d("PushServiceReceiver", context2.getPackageName() + ": start PushSerevice for by " + action + "  ; handler : " + b);
            a.a(c, context2, action);
            b.removeCallbacks(c);
            b.postDelayed(c, 2000);
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.APPLICATIONS_STORAGE_MOVIES);
    }

    static class a implements Runnable {
        private Context a;
        private String b;

        a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            boolean z = false;
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACCESSIBILITY_SCREEN_MAGNIFICATION_SETTINGS, false);
            NetworkInfo a = p.a(this.a);
            if (a != null) {
                z = a.isConnectedOrConnecting();
            }
            if (!z) {
                n.d("PushServiceReceiver", this.a.getPackageName() + ": \u65e0\u7f51\u7edc  by " + this.b);
                Context context = this.a;
                n.a(context, "\u89e6\u53d1\u9759\u6001\u5e7f\u64ad:\u65e0\u7f51\u7edc(" + this.b + Constants.ACCEPT_TIME_SEPARATOR_SP + this.a.getPackageName() + l.t);
                AppMethodBeat.o(MetricsProto.MetricsEvent.ACCESSIBILITY_SCREEN_MAGNIFICATION_SETTINGS);
                return;
            }
            n.d("PushServiceReceiver", this.a.getPackageName() + ": \u6267\u884c\u5f00\u59cb\u51fa\u53d1\u52a8\u4f5c: " + this.b);
            Context context2 = this.a;
            n.a(context2, "\u89e6\u53d1\u9759\u6001\u5e7f\u64ad(" + this.b + Constants.ACCEPT_TIME_SEPARATOR_SP + this.a.getPackageName() + l.t);
            d.a().a(this.a);
            if (!ClientConfigManagerImpl.getInstance(this.a).isCancleBroadcastReceiver()) {
                PushClient.getInstance(this.a).initialize();
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACCESSIBILITY_SCREEN_MAGNIFICATION_SETTINGS);
        }

        static /* synthetic */ void a(a aVar, Context context, String str) {
            AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_QS_SECONDARY_CLICK, false);
            aVar.a = ContextDelegate.getContext(context).getApplicationContext();
            aVar.b = str;
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_QS_SECONDARY_CLICK);
        }
    }
}
