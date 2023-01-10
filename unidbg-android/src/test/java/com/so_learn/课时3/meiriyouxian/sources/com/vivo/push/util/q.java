package com.vivo.push.util;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.b.c;

/* compiled from: OperateUtil */
public final class q {
    public static int a(c cVar) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.NOTIFICATION_SELECT_SNOOZE, false);
        v b = v.b();
        int i = cVar.a;
        long currentTimeMillis = System.currentTimeMillis();
        int b2 = b.b("com.vivo.push_preferences.operate." + i + "OPERATE_COUNT");
        long b3 = currentTimeMillis - b.b("com.vivo.push_preferences.operate." + i + "START_TIME", 0);
        if (b3 > 86400000 || b3 < 0) {
            b.a("com.vivo.push_preferences.operate." + i + "START_TIME", System.currentTimeMillis());
            b.a("com.vivo.push_preferences.operate." + i + "OPERATE_COUNT", 1);
        } else if (b2 >= cVar.h) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.NOTIFICATION_SELECT_SNOOZE);
            return 1001;
        } else {
            b.a("com.vivo.push_preferences.operate." + i + "OPERATE_COUNT", b2 + 1);
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.NOTIFICATION_SELECT_SNOOZE);
        return 0;
    }
}
