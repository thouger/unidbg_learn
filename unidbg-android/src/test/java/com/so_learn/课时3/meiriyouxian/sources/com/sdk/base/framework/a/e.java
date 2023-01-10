package com.sdk.base.framework.a;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.a.a.c;

final class e extends c<String, String> {
    e(d dVar, int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ int a(Object obj, Object obj2) {
        AppMethodBeat.i(20437, false);
        String str = (String) obj2;
        if (str == null) {
            AppMethodBeat.o(20437);
            return 0;
        }
        int length = str.length();
        AppMethodBeat.o(20437);
        return length;
    }
}
