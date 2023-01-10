package com.sdk.mobile.handler;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.b.a;

final class c implements a<T> {
    private /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final void a(int i, int i2, String str) {
        AppMethodBeat.i(18444, false);
        a.a(this.a, i, i2, str);
        AppMethodBeat.o(18444);
    }

    public final void a(int i, String str, int i2, T t, String str2) {
        AppMethodBeat.i(18441, false);
        a.a(this.a, i, str, i2, t, str2);
        AppMethodBeat.o(18441);
    }
}
