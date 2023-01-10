package cn.missfresh.buttomline.logtrace.e;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.google.gson.Gson;
import java.lang.reflect.Type;

/* compiled from: JsonUtil */
public class d {
    private static Gson a = new Gson();

    static {
        AppMethodBeat.i(17113, false);
        AppMethodBeat.o(17113);
    }

    public static String a(Object obj) {
        AppMethodBeat.i(17108, false);
        String json = a.toJson(obj);
        AppMethodBeat.o(17108);
        return json;
    }

    public static <T> T a(String str, Type type) {
        T t;
        AppMethodBeat.i(17110, false);
        try {
            t = (T) a.fromJson(str, type);
        } catch (Exception unused) {
            t = null;
        }
        AppMethodBeat.o(17110);
        return t;
    }
}
