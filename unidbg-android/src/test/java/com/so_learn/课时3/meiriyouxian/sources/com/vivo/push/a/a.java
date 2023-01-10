package com.vivo.push.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.b;
import com.vivo.push.g;
import com.vivo.push.util.n;
import com.vivo.push.util.r;
import com.vivo.push.util.y;
import java.util.List;

/* compiled from: CommandBridge */
public final class a {
    public static void a(Context context, String str, g gVar) {
        boolean z = false;
        AppMethodBeat.i(2928, false);
        boolean a = gVar.a();
        b a2 = b.a(context, a ? "com.vivo.vms.upstageservice" : "com.vivo.vms.aidlservice");
        a2.b = r.b(a2.c);
        if (TextUtils.isEmpty(a2.b)) {
            n.c(a2.c, "push pkgname is null");
        } else {
            if (y.a(a2.c, a2.b) >= 1260) {
                z = true;
            }
            a2.a = z;
            z = a2.a;
        }
        if (TextUtils.isEmpty(gVar.b)) {
            gVar.b = context.getPackageName();
        }
        if (z && !"com.vivo.pushservice".equals(context.getPackageName())) {
            com.vivo.push.a aVar = new com.vivo.push.a(gVar.b, str, new Bundle());
            gVar.a(aVar);
            if (a2.a(aVar.a)) {
                AppMethodBeat.o(2928);
                return;
            } else {
                n.b("CommandBridge", "send command error by aidl");
                n.c(context, "send command error by aidl");
            }
        }
        Intent intent = new Intent("com.vivo.pushservice.action.METHOD");
        intent.setPackage(str);
        intent.setClassName(str, a ? "com.vivo.push.sdk.service.UpstageService" : "com.vivo.push.sdk.service.PushService");
        com.vivo.push.a a3 = com.vivo.push.a.a(intent);
        if (a3 == null) {
            n.b("PushCommand", "bundleWapper is null");
        } else {
            gVar.a(a3);
            Bundle bundle = a3.a;
            if (bundle != null) {
                intent.putExtras(bundle);
            }
        }
        try {
            a(context, intent);
            AppMethodBeat.o(2928);
        } catch (Exception e) {
            n.a("CommandBridge", "CommandBridge startService exception: ", e);
            AppMethodBeat.o(2928);
        }
    }

    public static void a(Context context, Intent intent) throws Exception {
        AppMethodBeat.i(2931, false);
        if (context != null) {
            try {
                context.startService(intent);
                AppMethodBeat.o(2931);
            } catch (Exception e) {
                n.a("CommandBridge", "start service error", e);
                intent.setComponent(null);
                context.sendBroadcast(intent);
                AppMethodBeat.o(2931);
            }
        } else {
            n.d("CommandBridge", "enter startService context is null");
            Exception exc = new Exception("context is null");
            AppMethodBeat.o(2931);
            throw exc;
        }
    }

    public static boolean a(Context context, String str, String str2) {
        AppMethodBeat.i(2935, false);
        Intent intent = new Intent(str);
        intent.setPackage(str2);
        try {
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_CANCEL_CONNECT);
            if (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) {
                n.b("CommandBridge", "action check error\uff1aaction>>" + str + ";pkgname>>" + str2);
                AppMethodBeat.o(2935);
                return false;
            }
            AppMethodBeat.o(2935);
            return true;
        } catch (Exception unused) {
            n.b("CommandBridge", "queryBroadcastReceivers error");
            AppMethodBeat.o(2935);
            return false;
        }
    }
}
