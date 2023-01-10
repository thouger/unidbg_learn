package com.vivo.push.b;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a;
import java.util.ArrayList;

/* compiled from: OnListTagReceiveCommand */
public final class m extends s {
    public ArrayList<String> c;

    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final String toString() {
        return "OnListTagCommand";
    }

    public m() {
        super(8);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void b(a aVar) {
        AppMethodBeat.i(2231, false);
        super.b(aVar);
        aVar.a("tags_list", this.c);
        AppMethodBeat.o(2231);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void c(a aVar) {
        AppMethodBeat.i(2234, false);
        super.c(aVar);
        this.c = aVar.b("tags_list");
        AppMethodBeat.o(2234);
    }
}
