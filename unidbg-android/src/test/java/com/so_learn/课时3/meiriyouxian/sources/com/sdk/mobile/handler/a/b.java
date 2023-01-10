package com.sdk.mobile.handler.a;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.a.a.c;
import com.sdk.base.framework.b.a;
import com.sdk.base.framework.f.h.g;
import org.json.JSONObject;

final class b implements a<T> {
    private /* synthetic */ int a;
    private /* synthetic */ a b;

    b(a aVar, int i) {
        this.b = aVar;
        this.a = i;
    }

    public final void a(int i, int i2, String str) {
        AppMethodBeat.i(18371, false);
        a.a(this.b, i, i2, str);
        AppMethodBeat.o(18371);
    }

    public final void a(int i, String str, int i2, T t, String str2) {
        AppMethodBeat.i(18369, false);
        com.sdk.base.framework.f.j.b.b(a.a(this.b));
        String str3 = t;
        if (i == 0) {
            try {
                com.sdk.base.framework.a.a.a.a(a.a(this.b), this.a, com.sdk.base.framework.a.a.a.a(t, str2), g.b.a());
                String a = com.sdk.base.framework.f.l.a.a(a.a(this.b), String.valueOf(t));
                if (a == null) {
                    a.a(this.b, 1, "SDK\u89e3\u5bc6\u5f02\u5e38", 302001, a, str2);
                    AppMethodBeat.o(18369);
                    return;
                }
                JSONObject jSONObject = new JSONObject(String.valueOf(a));
                str3 = a;
                if (this.a == 1) {
                    jSONObject.remove("fakeMobile");
                    str3 = jSONObject.toString();
                }
            } catch (Exception e) {
                c.b(a.a(), e.toString(), a.b());
                str3 = t;
            }
        }
        a.a(this.b, i, str, i2, str3, str2);
        AppMethodBeat.o(18369);
    }
}
