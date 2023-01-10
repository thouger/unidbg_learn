package com.vivo.push.b;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a;
import java.io.Serializable;
import java.util.ArrayList;

/* compiled from: TagCommand */
public final class z extends c {
    private ArrayList<String> c;

    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final String toString() {
        return "TagCommand";
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public z(boolean z, String str, ArrayList<String> arrayList) {
        super(z ? 2004 : 2005, str);
        AppMethodBeat.i(2435, false);
        this.c = arrayList;
        AppMethodBeat.o(2435);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final void b(a aVar) {
        AppMethodBeat.i(2438, false);
        super.b(aVar);
        aVar.a("tags", (Serializable) this.c);
        AppMethodBeat.o(2438);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.c, com.vivo.push.g
    public final void c(a aVar) {
        AppMethodBeat.i(2442, false);
        super.c(aVar);
        this.c = aVar.b("tags");
        AppMethodBeat.o(2442);
    }
}
