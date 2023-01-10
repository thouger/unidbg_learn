package cn.missfresh.risk.simcard;

import android.telephony.PreciseDisconnectCause;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.regex.Pattern;

/* compiled from: MidInfo */
public class a {
    private static Pattern a = Pattern.compile("[0-9]*");

    static {
        AppMethodBeat.i(2532, false);
        AppMethodBeat.o(2532);
    }

    public static boolean a(String str) {
        AppMethodBeat.i(PreciseDisconnectCause.EPDG_TUNNEL_ESTABLISH_FAILURE, false);
        boolean matches = a.matcher(str).matches();
        AppMethodBeat.o(PreciseDisconnectCause.EPDG_TUNNEL_ESTABLISH_FAILURE);
        return matches;
    }
}
