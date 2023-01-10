package com.vivo.push.b;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a;
import com.vivo.push.g;

/* compiled from: StopServiceCommand */
public final class y extends g {
    private String c;

    @Override // com.vivo.push.g
    public final String toString() {
        return "StopServiceCommand";
    }

    public y(String str) {
        super(2008);
        this.c = str;
    }

    public y() {
        super(2008);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public final void b(a aVar) {
        AppMethodBeat.i(2420, false);
        aVar.a("package_name", this.c);
        AppMethodBeat.o(2420);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public final void c(a aVar) {
        AppMethodBeat.i(2425, false);
        this.c = aVar.a("package_name");
        AppMethodBeat.o(2425);
    }
}
