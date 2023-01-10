package cn.missfresh.risk.f;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.Collection;

/* compiled from: CommonTools */
public class b {
    public static boolean a(Collection collection) {
        boolean z = false;
        AppMethodBeat.i(2795, false);
        if (collection == null || collection.isEmpty()) {
            z = true;
        }
        AppMethodBeat.o(2795);
        return z;
    }

    public static boolean a(Context context) {
        boolean z = false;
        AppMethodBeat.i(2799, false);
        if (context == null) {
            AppMethodBeat.o(2799);
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            AppMethodBeat.o(2799);
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
            z = true;
        }
        AppMethodBeat.o(2799);
        return z;
    }
}
