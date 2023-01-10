package com.vivo.push.cache;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.util.n;
import com.vivo.push.util.y;
import java.lang.reflect.Method;

/* compiled from: ConfigManagerFactory */
public final class b {
    private static volatile b a;
    private d b;

    private b() {
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            AppMethodBeat.i(2913, false);
            if (a == null) {
                a = new b();
            }
            bVar = a;
            AppMethodBeat.o(2913);
        }
        return bVar;
    }

    public final d a(Context context) {
        AppMethodBeat.i(2919, false);
        d dVar = this.b;
        if (dVar != null) {
            AppMethodBeat.o(2919);
            return dVar;
        }
        try {
            String str = y.a(context) ? "com.vivo.push.cache.ServerConfigManagerImpl" : "com.vivo.push.cache.ClientConfigManagerImpl";
            Method method = Class.forName(str).getMethod("getInstance", Context.class);
            n.d("ConfigManagerFactory", "createConfig success is " + str);
            this.b = (d) method.invoke(null, context);
            d dVar2 = this.b;
            AppMethodBeat.o(2919);
            return dVar2;
        } catch (Exception e) {
            e.printStackTrace();
            n.b("ConfigManagerFactory", "createConfig error", e);
            AppMethodBeat.o(2919);
            return null;
        }
    }
}
