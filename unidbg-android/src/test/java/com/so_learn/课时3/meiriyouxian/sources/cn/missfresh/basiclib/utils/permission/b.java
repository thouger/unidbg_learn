package cn.missfresh.basiclib.utils.permission;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: PermissionConfig */
public class b {
    private static b a;
    private Context b;

    private b(Context context) {
        AppMethodBeat.i(4852, false);
        if (context != null) {
            this.b = context.getApplicationContext();
            AppMethodBeat.o(4852);
            return;
        }
        NullPointerException nullPointerException = new NullPointerException("context is null");
        AppMethodBeat.o(4852);
        throw nullPointerException;
    }

    static void a(Context context) {
        int i = 4855;
        AppMethodBeat.i(4855, false);
        if (a == null) {
            synchronized (b.class) {
                try {
                    if (a == null) {
                        a = new b(context);
                    }
                } finally {
                    AppMethodBeat.o(i);
                }
            }
        }
    }

    static b a() {
        return a;
    }

    /* access modifiers changed from: package-private */
    public Context b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public String c() {
        AppMethodBeat.i(4860, false);
        String packageName = this.b.getPackageName();
        AppMethodBeat.o(4860);
        return packageName;
    }
}
