package cn.missfresh.ad.data;

import android.content.Context;
import android.text.TextUtils;
import cn.missfresh.ad.b.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.File;

public class MFADPathHelper {
    private static String c;
    private final String a = "mf_AdCache_";
    private final String b = "mf_pre_AdCache_";

    public MFADPathHelper(Context context) {
        AppMethodBeat.i(5948, false);
        c = b.a(context);
        AppMethodBeat.o(5948);
    }

    public String a(String str) {
        AppMethodBeat.i(5959, false);
        String str2 = c + File.separator + "mf_pre_AdCache_" + str;
        AppMethodBeat.o(5959);
        return str2;
    }

    public String b(String str) {
        AppMethodBeat.i(5961, false);
        String str2 = c + File.separator + str;
        AppMethodBeat.o(5961);
        return str2;
    }

    public String c(String str) {
        AppMethodBeat.i(5965, false);
        if (TextUtils.isEmpty(str) || str.indexOf(".", str.length() - 6) == -1) {
            AppMethodBeat.o(5965);
            return null;
        }
        String substring = str.substring(str.indexOf(".", str.length() - 6) + 1);
        String str2 = b.a(str) + "." + substring.toLowerCase();
        AppMethodBeat.o(5965);
        return str2;
    }

    public static boolean d(String str) {
        AppMethodBeat.i(5969, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(5969);
            return false;
        }
        int indexOf = str.indexOf(".", str.length() - 6);
        if (indexOf == -1) {
            AppMethodBeat.o(5969);
            return false;
        }
        try {
            boolean z = !TextUtils.isEmpty(str.substring(indexOf + 1));
            AppMethodBeat.o(5969);
            return z;
        } catch (Exception unused) {
            AppMethodBeat.o(5969);
            return false;
        }
    }
}
