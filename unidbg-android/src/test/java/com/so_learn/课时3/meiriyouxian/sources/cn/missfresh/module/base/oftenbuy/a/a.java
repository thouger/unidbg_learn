package cn.missfresh.module.base.oftenbuy.a;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;

/* compiled from: OftenBuyHelper */
public class a {
    public static String a(String str, int i) {
        AppMethodBeat.i(16250, false);
        if (b.a(str)) {
            AppMethodBeat.o(16250);
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.endsWith(".html") ? "?" : "&");
        sb.append(String.format("favouriteFrom=%d", Integer.valueOf(i)));
        String sb2 = sb.toString();
        AppMethodBeat.o(16250);
        return sb2;
    }
}
