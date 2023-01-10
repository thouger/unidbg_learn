package com.vivo.push.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: NetUtils */
public final class p {
    public static NetworkInfo a(Context context) {
        AppMethodBeat.i(1559, false);
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            AppMethodBeat.o(1559);
            return activeNetworkInfo;
        } catch (Exception e) {
            n.a("NetUtils", e);
            AppMethodBeat.o(1559);
            return null;
        }
    }
}
