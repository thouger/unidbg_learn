package com.sdk.mobile.handler;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.b.a;

final class d implements a<T> {
    private /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    public final void a(int i, int i2, String str) {
        AppMethodBeat.i(18346, false);
        a.a(this.a, i, i2, str);
        AppMethodBeat.o(18346);
    }

    public final void a(int i, String str, int i2, T t, String str2) {
        String str3;
        AppMethodBeat.i(18345, false);
        if (i == 0) {
            String a = com.sdk.base.framework.f.l.a.a(a.a(this.a), String.valueOf(t));
            if (a == null) {
                a.a(this.a, 1, "SDK\u89e3\u5bc6\u5f02\u5e38", 302001, a, str2);
            } else {
                str3 = a;
                a.a(this.a, i, str, i2, str3, str2);
            }
        } else {
            str3 = t;
            a.a(this.a, i, str, i2, str3, str2);
        }
        AppMethodBeat.o(18345);
    }
}
