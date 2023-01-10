package cn.missfresh.risk.f;

import android.content.Context;
import cn.missfresh.risk.b.a;
import cn.missfresh.risk.k;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.e;

/* compiled from: ConfigManager */
public class d {
    private static d a;
    private static f b;
    private static Context c;

    private d(Context context) {
        AppMethodBeat.i(2829, false);
        if (context != null) {
            c = context.getApplicationContext();
        }
        b = new f(context, "mryx_user");
        AppMethodBeat.o(2829);
    }

    public static void a(Context context) {
        int i = 2831;
        AppMethodBeat.i(2831, false);
        if (a == null) {
            synchronized (d.class) {
                try {
                    if (a == null) {
                        a = new d(context);
                    }
                } finally {
                    AppMethodBeat.o(i);
                }
            }
        }
    }

    public static void a(String str) {
        AppMethodBeat.i(2836, false);
        try {
            a.a = str;
            b.b("risk_token_new", str);
            k.a(c, str);
            cn.missfresh.risk.exceptions.a.a(str, "ConfigManager:42 server");
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(2836);
    }

    public static String a() {
        AppMethodBeat.i(2840, false);
        if (!e.a(a.a)) {
            String str = a.a;
            AppMethodBeat.o(2840);
            return str;
        }
        f fVar = b;
        String str2 = "";
        if (fVar == null) {
            AppMethodBeat.o(2840);
            return str2;
        }
        try {
            str2 = fVar.a("risk_token_new", str2);
            if (e.a(str2)) {
                str2 = k.b(c);
            }
            a.a = str2;
        } catch (Exception e) {
            e.printStackTrace();
            cn.missfresh.risk.exceptions.a.a("ConfigManager:69 " + e.getMessage());
        }
        AppMethodBeat.o(2840);
        return str2;
    }
}
