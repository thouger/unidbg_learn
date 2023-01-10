package com.sdk.mobile.handler;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
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
        AppMethodBeat.i(18354, false);
        a.a(this.b, i, i2, str);
        AppMethodBeat.o(18354);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:13:? */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v2, types: [java.lang.Object, java.lang.String] */
    public final void a(int i, String str, int i2, T t, String str2) {
        AppMethodBeat.i(18353, false);
        com.sdk.base.framework.f.j.b.b(a.a(this.b));
        if (i == 0) {
            com.sdk.base.framework.a.a.a.a(a.a(this.b), this.a, com.sdk.base.framework.a.a.a.a((Object) t, str2), g.a.a());
            try {
                t = com.sdk.base.framework.f.l.a.a(a.a(this.b), String.valueOf((Object) t));
                if (t == 0) {
                    a.a(this.b, 1, "SDK\u89e3\u5bc6\u5f02\u5e38", 302001, (Object) t, str2);
                    AppMethodBeat.o(18353);
                    return;
                }
                JSONObject jSONObject = new JSONObject(String.valueOf((Object) t));
                if (this.a == 1) {
                    jSONObject.remove("fakeMobile");
                    t = jSONObject.toString();
                }
            } catch (Exception unused) {
            }
        }
        a.a(this.b, i, str, i2, (Object) t, str2);
        AppMethodBeat.o(18353);
    }
}
