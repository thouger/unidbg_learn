package cn.missfresh.risk.simcard;

import android.telephony.TelephonyManager;
import cn.missfresh.risk.exceptions.MobException;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.telephony.IccCardConstants;

/* compiled from: MobCardUtils */
public class b {
    private static final String a = b.class.getSimpleName();

    private static String a(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "unknow" : IccCardConstants.INTENT_VALUE_ICC_READY : "NETWORK_LOCKED" : "PUK_REQUIRED" : "PIN_REQUIRED" : IccCardConstants.INTENT_VALUE_ICC_ABSENT : "UNKNOWN";
    }

    static {
        AppMethodBeat.i(2557, false);
        AppMethodBeat.o(2557);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00af A[SYNTHETIC, Splitter:B:26:0x00af] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00eb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r10, cn.missfresh.risk.simcard.SimCardBean r11) {
        /*
        // Method dump skipped, instructions count: 254
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.risk.simcard.b.a(android.content.Context, cn.missfresh.risk.simcard.SimCardBean):void");
    }

    private static boolean a(TelephonyManager telephonyManager, String str, int i) throws MobException {
        boolean z = false;
        AppMethodBeat.i(2544, false);
        try {
            Object invoke = Class.forName(telephonyManager.getClass().getName()).getMethod(str, Integer.TYPE).invoke(telephonyManager, Integer.valueOf(i));
            if (invoke != null && Integer.parseInt(invoke.toString()) == 5) {
                z = true;
            }
            AppMethodBeat.o(2544);
            return z;
        } catch (Exception unused) {
            MobException mobException = new MobException(str);
            AppMethodBeat.o(2544);
            throw mobException;
        }
    }

    private static int b(TelephonyManager telephonyManager, String str, int i) throws MobException {
        int i2 = 0;
        AppMethodBeat.i(2547, false);
        try {
            Object invoke = Class.forName(telephonyManager.getClass().getName()).getMethod(str, Integer.TYPE).invoke(telephonyManager, Integer.valueOf(i));
            if (invoke != null) {
                i2 = Integer.parseInt(invoke.toString());
            }
            AppMethodBeat.o(2547);
            return i2;
        } catch (Exception unused) {
            MobException mobException = new MobException(str);
            AppMethodBeat.o(2547);
            throw mobException;
        }
    }

    private static String c(TelephonyManager telephonyManager, String str, int i) throws MobException {
        AppMethodBeat.i(2555, false);
        try {
            Object invoke = Class.forName(telephonyManager.getClass().getName()).getMethod(str, Integer.TYPE).invoke(telephonyManager, Integer.valueOf(i));
            String obj = invoke != null ? invoke.toString() : "$unknown";
            AppMethodBeat.o(2555);
            return obj;
        } catch (Exception unused) {
            MobException mobException = new MobException(str);
            AppMethodBeat.o(2555);
            throw mobException;
        }
    }
}
