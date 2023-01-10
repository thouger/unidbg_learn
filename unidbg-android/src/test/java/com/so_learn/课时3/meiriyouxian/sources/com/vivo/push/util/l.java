package com.vivo.push.util;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a.a;
import com.vivo.push.b.n;

/* compiled from: LogController */
public final class l implements m {
    private static final String a = (com.umeng.message.proguard.l.s + Process.myPid() + com.umeng.message.proguard.l.t);
    private static final String[] b = {"com.vivo.pushservice", "com.vivo.sdk.test", "com.vivo.pushdemo.test"};

    static {
        AppMethodBeat.i(1494, false);
        AppMethodBeat.o(1494);
    }

    @Override // com.vivo.push.util.m
    public final int a(String str, String str2) {
        AppMethodBeat.i(1460, false);
        int e = Log.e("VivoPush." + str, a + str2);
        AppMethodBeat.o(1460);
        return e;
    }

    @Override // com.vivo.push.util.m
    public final int a(String str, Throwable th) {
        AppMethodBeat.i(1463, false);
        int e = Log.e("VivoPush." + str, Log.getStackTraceString(th));
        AppMethodBeat.o(1463);
        return e;
    }

    @Override // com.vivo.push.util.m
    public final int a(String str, String str2, Throwable th) {
        AppMethodBeat.i(1465, false);
        int e = Log.e("VivoPush." + str, a + str2, th);
        AppMethodBeat.o(1465);
        return e;
    }

    @Override // com.vivo.push.util.m
    public final int b(String str, String str2) {
        AppMethodBeat.i(1468, false);
        int w = Log.w("VivoPush." + str, a + str2);
        AppMethodBeat.o(1468);
        return w;
    }

    @Override // com.vivo.push.util.m
    public final int c(String str, String str2) {
        AppMethodBeat.i(1470, false);
        int d = Log.d("VivoPush." + str, a + str2);
        AppMethodBeat.o(1470);
        return d;
    }

    @Override // com.vivo.push.util.m
    public final int d(String str, String str2) {
        AppMethodBeat.i(BluetoothClass.Device.PERIPHERAL_KEYBOARD_POINTING, false);
        if (n.a()) {
            int i = Log.i("VivoPush." + str, a + str2);
            AppMethodBeat.o(BluetoothClass.Device.PERIPHERAL_KEYBOARD_POINTING);
            return i;
        }
        AppMethodBeat.o(BluetoothClass.Device.PERIPHERAL_KEYBOARD_POINTING);
        return -1;
    }

    @Override // com.vivo.push.util.m
    public final int b(String str, String str2, Throwable th) {
        AppMethodBeat.i(1474, false);
        if (n.a()) {
            int i = Log.i("VivoPush." + str, a + str2, th);
            AppMethodBeat.o(1474);
            return i;
        }
        AppMethodBeat.o(1474);
        return -1;
    }

    @Override // com.vivo.push.util.m
    public final int e(String str, String str2) {
        AppMethodBeat.i(1476, false);
        if (n.a()) {
            int v = Log.v("VivoPush." + str, a + str2);
            AppMethodBeat.o(1476);
            return v;
        }
        AppMethodBeat.o(1476);
        return -1;
    }

    @Override // com.vivo.push.util.m
    public final String a(Throwable th) {
        AppMethodBeat.i(1478, false);
        String stackTraceString = Log.getStackTraceString(th);
        AppMethodBeat.o(1478);
        return stackTraceString;
    }

    @Override // com.vivo.push.util.m
    public final void a(Context context, String str) {
        AppMethodBeat.i(1481, false);
        if (!n.a()) {
            AppMethodBeat.o(1481);
            return;
        }
        a(context, str, 0);
        AppMethodBeat.o(1481);
    }

    @Override // com.vivo.push.util.m
    public final void b(Context context, String str) {
        AppMethodBeat.i(1482, false);
        if (!n.a()) {
            AppMethodBeat.o(1482);
            return;
        }
        a(context, str, 1);
        AppMethodBeat.o(1482);
    }

    @Override // com.vivo.push.util.m
    public final void c(Context context, String str) {
        AppMethodBeat.i(1484, false);
        if (!n.a()) {
            AppMethodBeat.o(1484);
            return;
        }
        a(context, str, 2);
        AppMethodBeat.o(1484);
    }

    private void a(Context context, String str, int i) {
        AppMethodBeat.i(1487, false);
        n nVar = new n();
        nVar.c = str;
        nVar.d = i;
        if (i > 0) {
            d("LogController", str);
        }
        if (y.a(context)) {
            nVar.e = true;
            for (String str2 : b) {
                a(context, nVar, str2);
            }
            AppMethodBeat.o(1487);
            return;
        }
        nVar.e = false;
        a(context, nVar, context.getPackageName());
        AppMethodBeat.o(1487);
    }

    private static void a(Context context, n nVar, String str) {
        String str2 = "com.vivo.pushclient.action.RECEIVE";
        AppMethodBeat.i(1491, false);
        if (str.contains("test")) {
            try {
                boolean a2 = r.a(context, str, str2);
                if (!a2) {
                    str2 = "com.vivo.pushservice.action.RECEIVE";
                }
                if (!TextUtils.isEmpty(str)) {
                    if (a.a(context, str2, str)) {
                        if (TextUtils.isEmpty(nVar.b)) {
                            nVar.b = context.getPackageName();
                        }
                        Intent intent = new Intent();
                        intent.setFlags(1048576);
                        if (!TextUtils.isEmpty(str2)) {
                            intent.setAction(str2);
                        }
                        intent.setPackage(str);
                        intent.setClassName(str, a2 ? "com.vivo.push.sdk.service.CommandClientService" : "com.vivo.push.sdk.service.CommandService");
                        nVar.a(intent);
                        intent.putExtra("command_type", "reflect_receiver");
                        a.a(context, intent);
                    }
                    AppMethodBeat.o(1491);
                    return;
                }
                n.c(context, "\u6d88\u606f\u63a5\u53d7\u8005\u5305\u540d\u4e3a\u7a7a\uff01");
                Exception exc = new Exception("\u6d88\u606f\u63a5\u53d7\u8005\u5305\u540d\u4e3a\u7a7a\uff01");
                AppMethodBeat.o(1491);
                throw exc;
            } catch (Exception e) {
                n.a("CommandBridge", "CommandBridge sendCommandToClient exception", e);
            }
        } else if (str.equals("com.vivo.pushservice")) {
            AppMethodBeat.o(1491);
            return;
        }
        AppMethodBeat.o(1491);
    }
}
