package cn.com.chinatelecom.account.api.c;

import android.content.Context;
import android.text.TextUtils;
import cn.com.chinatelecom.account.a.c;
import cn.com.chinatelecom.account.api.CtAuth;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;

public class f {
    private static final String a = f.class.getSimpleName();
    private static int b = 0;
    private static Map<String, e> c = new HashMap();
    private static List<e> d = new ArrayList();
    private static e e = null;

    /* access modifiers changed from: package-private */
    /* renamed from: cn.com.chinatelecom.account.api.c.f$1  reason: invalid class name */
    public static class AnonymousClass1 extends TimerTask {
        AnonymousClass1() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            AppMethodBeat.i(8269, false);
            f.a(CtAuth.mContext);
            AppMethodBeat.o(8269);
        }
    }

    static {
        AppMethodBeat.i(8286, false);
        AppMethodBeat.o(8286);
    }

    public static synchronized e a(String str) {
        e eVar;
        synchronized (f.class) {
            AppMethodBeat.i(8273, false);
            eVar = null;
            try {
                if (c.containsKey(str)) {
                    eVar = c.get(str);
                }
                if (eVar == null) {
                    eVar = new e(str);
                    c.put(str, eVar);
                }
                AppMethodBeat.o(8273);
            } catch (Throwable th) {
                th.printStackTrace();
                e eVar2 = new e(str);
                AppMethodBeat.o(8273);
                return eVar2;
            }
        }
        return eVar;
    }

    static /* synthetic */ void a(Context context) {
        AppMethodBeat.i(8285, false);
        b(context);
        AppMethodBeat.o(8285);
    }

    public static void a(Context context, String str) {
        AppMethodBeat.i(8284, false);
        c.a(context, str);
        AppMethodBeat.o(8284);
    }

    public static synchronized void a(String str, String str2, String str3) {
        synchronized (f.class) {
            AppMethodBeat.i(8277, false);
            try {
                if (c.containsKey(str)) {
                    c.get(str).g(str3);
                    AppMethodBeat.o(8277);
                    return;
                }
                if (d.size() > 0) {
                    for (e eVar : d) {
                        if (eVar.a() != null && eVar.a().equals(str)) {
                            JSONObject jSONObject = new JSONObject(str2);
                            jSONObject.remove("data");
                            eVar.g(jSONObject.toString());
                            eVar.g(str3);
                        }
                    }
                }
                AppMethodBeat.o(8277);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static void b(Context context) {
        int i = 8280;
        AppMethodBeat.i(8280, false);
        if (context == null) {
            AppMethodBeat.o(8280);
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            synchronized (f.class) {
                try {
                    if (e != null) {
                        arrayList.add(e.toString());
                        e = null;
                    }
                    for (e eVar : d) {
                        arrayList.add(eVar.toString());
                    }
                    b = 0;
                    d.clear();
                } finally {
                    AppMethodBeat.o(i);
                }
            }
            if (arrayList.isEmpty()) {
                AppMethodBeat.o(8280);
            } else {
                c.a(context, arrayList);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static synchronized void b(String str) {
        synchronized (f.class) {
            AppMethodBeat.i(8275, false);
            try {
                if (c.containsKey(str)) {
                    e = c.get(str);
                    e.b();
                    c.remove(str);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            AppMethodBeat.o(8275);
        }
    }

    public static void b(String str, String str2, String str3) {
        AppMethodBeat.i(8282, false);
        String str4 = "";
        int i = -1;
        try {
            if (!TextUtils.isEmpty(str2)) {
                JSONObject jSONObject = new JSONObject(str2);
                i = jSONObject.getInt("result");
                str4 = jSONObject.optString("msg");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        e a2 = a(str).a(i);
        if (i == 0) {
            a2.e(str4);
        } else {
            a2.e(str4).d(str3);
        }
        AppMethodBeat.o(8282);
    }

    public static void c(String str) {
        int i = 8279;
        AppMethodBeat.i(8279, false);
        try {
            synchronized (f.class) {
                try {
                    if (c.containsKey(str)) {
                        e eVar = c.get(str);
                        eVar.b();
                        d.add(eVar);
                        c.remove(str);
                    }
                    if (b != 1) {
                        if (!d.isEmpty()) {
                            b = 1;
                        }
                    }
                    return;
                } finally {
                    AppMethodBeat.o(i);
                }
            }
            new Timer().schedule(new AnonymousClass1(), 8000);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AppMethodBeat.o(8279);
    }
}
