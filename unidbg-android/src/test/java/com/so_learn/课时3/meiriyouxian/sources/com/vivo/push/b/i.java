package com.vivo.push.b;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.connect.common.Constants;
import com.vivo.push.a;

/* compiled from: OnAppReceiveCommand */
public final class i extends s {
    public String c;
    public String d;
    private String e;

    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final String toString() {
        return "OnBindCommand";
    }

    public i(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void b(a aVar) {
        AppMethodBeat.i(2172, false);
        super.b(aVar);
        aVar.a("app_id", this.c);
        aVar.a(Constants.PARAM_CLIENT_ID, this.e);
        aVar.a("client_token", this.d);
        AppMethodBeat.o(2172);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void c(a aVar) {
        AppMethodBeat.i(2175, false);
        super.c(aVar);
        this.c = aVar.a("app_id");
        this.e = aVar.a(Constants.PARAM_CLIENT_ID);
        this.d = aVar.a("client_token");
        AppMethodBeat.o(2175);
    }
}
