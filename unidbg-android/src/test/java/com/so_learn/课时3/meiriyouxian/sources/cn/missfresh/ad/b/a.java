package cn.missfresh.ad.b;

import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: Logger */
public class a {
    private static boolean a;

    public static void a(cn.missfresh.ad.data.a aVar) {
        AppMethodBeat.i(6183, false);
        a = aVar.c();
        a("MFADSDK", "MFLinkInit Success: \n SDK_VERSION : Android+MFLink+0.0.3 Build_Num: 3 \n App ID is: " + aVar.a());
        AppMethodBeat.o(6183);
    }

    public static void a(String str, String str2) {
        AppMethodBeat.i(6186, false);
        if (a) {
            Log.d(str, str2);
        }
        AppMethodBeat.o(6186);
    }

    public static void b(String str, String str2) {
        AppMethodBeat.i(6188, false);
        if (a) {
            Log.d(str, str2);
        }
        AppMethodBeat.o(6188);
    }
}
