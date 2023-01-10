package com.vivo.push.b;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a;
import java.util.ArrayList;

/* compiled from: OnTagsReceiveCommand */
public final class t extends s {
    public ArrayList<String> c = null;
    public ArrayList<String> d = null;

    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final String toString() {
        return "OnSetTagsCommand";
    }

    public t(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void b(a aVar) {
        AppMethodBeat.i(2358, false);
        super.b(aVar);
        aVar.a("content", this.c);
        aVar.a("error_msg", this.d);
        AppMethodBeat.o(2358);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void c(a aVar) {
        AppMethodBeat.i(2362, false);
        super.c(aVar);
        this.c = aVar.b("content");
        this.d = aVar.b("error_msg");
        AppMethodBeat.o(2362);
    }
}
