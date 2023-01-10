package com.vivo.push.b;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;

/* compiled from: AliasCommand */
public final class a extends c {
    private ArrayList<String> c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public a(boolean z, String str, ArrayList<String> arrayList) {
        super(z ? 2002 : 2003, str);
        AppMethodBeat.i(2125, false);
        this.c = arrayList;
        AppMethodBeat.o(2125);
    }

    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        AppMethodBeat.i(2127, false);
        super.b(aVar);
        aVar.a("tags", this.c);
        AppMethodBeat.o(2127);
    }

    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        AppMethodBeat.i(2130, false);
        super.c(aVar);
        this.c = aVar.b("tags");
        AppMethodBeat.o(2130);
    }

    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final String toString() {
        AppMethodBeat.i(2133, false);
        String str = "AliasCommand:" + this.a;
        AppMethodBeat.o(2133);
        return str;
    }
}
