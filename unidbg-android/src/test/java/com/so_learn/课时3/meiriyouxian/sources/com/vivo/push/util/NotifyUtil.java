package com.vivo.push.util;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class NotifyUtil {
    private static BaseNotifyDataAdapter sNotifyData = null;
    private static String sNotifyDataAdapter = "com.vivo.push.util.NotifyDataAdapter";
    private static BaseNotifyLayoutAdapter sNotifyLayout = null;
    private static String sNotifyLayoutAdapter = "com.vivo.push.util.NotifyLayoutAdapter";

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object getObjectByReflect(java.lang.String r2, java.lang.Object r3) {
        /*
            r0 = 1564(0x61c, float:2.192E-42)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            r1 = 0
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ Exception -> 0x000c }
            goto L_0x000d
        L_0x000c:
            r2 = r1
        L_0x000d:
            if (r2 == 0) goto L_0x0014
            java.lang.Object r2 = r2.newInstance()     // Catch:{ Exception -> 0x0014 }
            goto L_0x0015
        L_0x0014:
            r2 = r1
        L_0x0015:
            if (r2 != 0) goto L_0x0018
            r2 = r3
        L_0x0018:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.NotifyUtil.getObjectByReflect(java.lang.String, java.lang.Object):java.lang.Object");
    }

    private static synchronized void initAdapter(Context context) {
        synchronized (NotifyUtil.class) {
            AppMethodBeat.i(1567, false);
            if (sNotifyData == null) {
                BaseNotifyDataAdapter baseNotifyDataAdapter = (BaseNotifyDataAdapter) getObjectByReflect(sNotifyDataAdapter, new g());
                sNotifyData = baseNotifyDataAdapter;
                baseNotifyDataAdapter.init(context);
            }
            if (sNotifyLayout == null) {
                BaseNotifyLayoutAdapter baseNotifyLayoutAdapter = (BaseNotifyLayoutAdapter) getObjectByReflect(sNotifyLayoutAdapter, new h());
                sNotifyLayout = baseNotifyLayoutAdapter;
                baseNotifyLayoutAdapter.init(context);
            }
            AppMethodBeat.o(1567);
        }
    }

    public static BaseNotifyDataAdapter getNotifyDataAdapter(Context context) {
        AppMethodBeat.i(1569, false);
        initAdapter(context);
        BaseNotifyDataAdapter baseNotifyDataAdapter = sNotifyData;
        AppMethodBeat.o(1569);
        return baseNotifyDataAdapter;
    }

    public static BaseNotifyLayoutAdapter getNotifyLayoutAdapter(Context context) {
        AppMethodBeat.i(1572, false);
        initAdapter(context);
        BaseNotifyLayoutAdapter baseNotifyLayoutAdapter = sNotifyLayout;
        AppMethodBeat.o(1572);
        return baseNotifyLayoutAdapter;
    }
}
