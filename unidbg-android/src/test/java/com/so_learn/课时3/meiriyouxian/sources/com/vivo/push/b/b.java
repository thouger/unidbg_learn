package com.vivo.push.b;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a;

/* compiled from: AppCommand */
public final class b extends c {
    public String c;
    public String d;
    private String j;
    private String k;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(boolean z, String str) {
        super(z ? 2006 : 2007, str);
        AppMethodBeat.i(2083, false);
        AppMethodBeat.o(2083);
    }

    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final void b(a aVar) {
        AppMethodBeat.i(2085, false);
        super.b(aVar);
        aVar.a("sdk_clients", this.j);
        aVar.a("sdk_version", 305L);
        aVar.a("BaseAppCommand.EXTRA_APPID", this.d);
        aVar.a("BaseAppCommand.EXTRA_APPKEY", this.c);
        aVar.a("PUSH_REGID", this.k);
        AppMethodBeat.o(2085);
    }

    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final void c(a aVar) {
        AppMethodBeat.i(2088, false);
        super.c(aVar);
        this.j = aVar.a("sdk_clients");
        this.d = aVar.a("BaseAppCommand.EXTRA_APPID");
        this.c = aVar.a("BaseAppCommand.EXTRA_APPKEY");
        this.k = aVar.a("PUSH_REGID");
        AppMethodBeat.o(2088);
    }

    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final String toString() {
        AppMethodBeat.i(2090, false);
        String str = "AppCommand:" + this.a;
        AppMethodBeat.o(2090);
        return str;
    }
}
