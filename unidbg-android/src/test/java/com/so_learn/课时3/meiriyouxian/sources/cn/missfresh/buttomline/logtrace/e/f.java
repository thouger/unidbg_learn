package cn.missfresh.buttomline.logtrace.e;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: NetWorkHelper */
public class f {
    private static String a(int i) {
        switch (i) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return "2";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return "3";
            case 13:
            case 18:
                return "4";
            default:
                return "1";
        }
    }

    public static NetworkInfo a(Context context) {
        AppMethodBeat.i(17138, false);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            AppMethodBeat.o(17138);
            return null;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        AppMethodBeat.o(17138);
        return activeNetworkInfo;
    }

    public static String b(Context context) {
        AppMethodBeat.i(17141, false);
        NetworkInfo a = a(context);
        if (a == null || !a.isConnected()) {
            AppMethodBeat.o(17141);
            return "0";
        } else if (a.getType() == 1) {
            AppMethodBeat.o(17141);
            return "5";
        } else if (a.getType() == 0) {
            String a2 = a(a.getSubtype());
            AppMethodBeat.o(17141);
            return a2;
        } else {
            AppMethodBeat.o(17141);
            return "1";
        }
    }
}
