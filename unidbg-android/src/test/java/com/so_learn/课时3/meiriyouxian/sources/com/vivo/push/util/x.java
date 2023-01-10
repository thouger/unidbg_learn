package com.vivo.push.util;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.telephony.PreciseDisconnectCause;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.HashMap;

/* compiled from: SystemCache */
public final class x implements c {
    private static final HashMap<String, Integer> b = new HashMap<>();
    private static final HashMap<String, Long> c = new HashMap<>();
    private static final HashMap<String, String> d = new HashMap<>();
    private static x e;
    public Context a;
    private c f;
    private boolean g = false;

    static {
        AppMethodBeat.i(1809, false);
        AppMethodBeat.o(1809);
    }

    private x(Context context) {
        AppMethodBeat.i(1795, false);
        this.a = context;
        this.g = a(context);
        n.d("SystemCache", "init status is " + this.g + ";  curCache is " + this.f);
        AppMethodBeat.o(1795);
    }

    public static synchronized x b(Context context) {
        x xVar;
        synchronized (x.class) {
            AppMethodBeat.i(1798, false);
            if (e == null) {
                e = new x(context.getApplicationContext());
            }
            xVar = e;
            AppMethodBeat.o(1798);
        }
        return xVar;
    }

    @Override // com.vivo.push.util.c
    public final boolean a(Context context) {
        AppMethodBeat.i(PreciseDisconnectCause.UT_SERVICE_UNAVAILABLE, false);
        this.f = new u();
        boolean a = this.f.a(context);
        if (!a) {
            this.f = new t();
            a = this.f.a(context);
        }
        if (!a) {
            this.f = new w();
            a = this.f.a(context);
        }
        if (!a) {
            this.f = null;
        }
        AppMethodBeat.o(PreciseDisconnectCause.UT_SERVICE_UNAVAILABLE);
        return a;
    }

    @Override // com.vivo.push.util.c
    public final String a(String str, String str2) {
        c cVar;
        AppMethodBeat.i(1805, false);
        String str3 = d.get(str);
        if (str3 != null || (cVar = this.f) == null) {
            AppMethodBeat.o(1805);
            return str3;
        }
        String a = cVar.a(str, str2);
        AppMethodBeat.o(1805);
        return a;
    }

    @Override // com.vivo.push.util.c
    public final void b(String str, String str2) {
        c cVar;
        AppMethodBeat.i(BluetoothClass.Device.WEARABLE_HELMET, false);
        d.put(str, str2);
        if (!this.g || (cVar = this.f) == null) {
            AppMethodBeat.o(BluetoothClass.Device.WEARABLE_HELMET);
            return;
        }
        cVar.b(str, str2);
        AppMethodBeat.o(BluetoothClass.Device.WEARABLE_HELMET);
    }
}
