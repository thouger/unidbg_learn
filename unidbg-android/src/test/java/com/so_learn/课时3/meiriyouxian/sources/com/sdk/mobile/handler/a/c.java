package com.sdk.mobile.handler.a;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.b.a;

final class c implements a<T> {
    private /* synthetic */ String a;
    private /* synthetic */ a b;

    c(a aVar, String str) {
        this.b = aVar;
        this.a = str;
    }

    public final void a(int i, int i2, String str) {
        AppMethodBeat.i(18362, false);
        a.a(this.b, i, i2, str);
        AppMethodBeat.o(18362);
    }

    public final void a(int i, String str, int i2, T t, String str2) {
        String str3;
        AppMethodBeat.i(18360, false);
        if (i != 0 || !com.sdk.base.framework.a.a.c.a(this.a).booleanValue()) {
            str3 = t;
            a.a(this.b, i, str, i2, str3, str2);
        } else {
            String a = com.sdk.base.framework.f.l.a.a(a.a(this.b), String.valueOf(t));
            if (a == null) {
                a.a(this.b, 1, "SDK\u89e3\u5bc6\u5f02\u5e38", 302001, a, str2);
            } else {
                str3 = a;
                a.a(this.b, i, str, i2, str3, str2);
            }
        }
        AppMethodBeat.o(18360);
    }
}
