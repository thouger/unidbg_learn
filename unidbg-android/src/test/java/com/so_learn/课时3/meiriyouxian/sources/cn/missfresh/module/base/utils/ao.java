package cn.missfresh.module.base.utils;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.Random;

/* compiled from: RandomUtil */
public class ao {
    private static Random a = new Random();

    static {
        AppMethodBeat.i(23408, false);
        AppMethodBeat.o(23408);
    }

    public static String a(int i) {
        AppMethodBeat.i(23405, false);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(a.nextInt(53)));
        }
        String stringBuffer2 = stringBuffer.toString();
        AppMethodBeat.o(23405);
        return stringBuffer2;
    }
}
