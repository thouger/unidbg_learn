package com.sdk.base.framework.c;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.f.f.a;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
final class h extends g {
    h(String str, int i) {
        super(str, 0, (byte) 0);
    }

    public final String a() {
        AppMethodBeat.i(20733, false);
        String a = a.a("cucc/host_cucc.properties", "PRODUCE_STATISTICAL");
        AppMethodBeat.o(20733);
        return a;
    }
}
