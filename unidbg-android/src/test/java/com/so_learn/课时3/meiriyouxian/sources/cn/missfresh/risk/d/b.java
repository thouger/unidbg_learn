package cn.missfresh.risk.d;

import cn.missfresh.risk.f.a;
import cn.missfresh.risk.f.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.Random;

/* compiled from: RiskCryptoUtil */
public class b {
    public static String a(String str, String str2) throws Exception {
        AppMethodBeat.i(2472, false);
        String a = a.a(a.a(c.a(str).getBytes(), str2.getBytes()));
        AppMethodBeat.o(2472);
        return a;
    }

    public static String a(int i) {
        AppMethodBeat.i(2474, false);
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt(62)));
        }
        String stringBuffer2 = stringBuffer.toString();
        AppMethodBeat.o(2474);
        return stringBuffer2;
    }
}
