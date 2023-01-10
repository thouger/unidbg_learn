package cn.missfresh.buttomline.logtrace;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.HashMap;

/* compiled from: CensusConfig */
public class b {
    public static HashMap<String, String> a = new HashMap<>();
    private static String b = "0";
    private static String c = "gf";
    private static String d = "unkown";
    private static String e = "0";
    private static String f = "0";
    private static String g = "0";

    static {
        AppMethodBeat.i(16650, false);
        AppMethodBeat.o(16650);
    }

    public static String a() {
        AppMethodBeat.i(16624, false);
        String str = a.c() ? "http://10.2.39.11:9997/" : "https://logtrace.missfresh.net/";
        AppMethodBeat.o(16624);
        return str;
    }

    public static String b() {
        return b;
    }

    public static String c() {
        return g;
    }
}
