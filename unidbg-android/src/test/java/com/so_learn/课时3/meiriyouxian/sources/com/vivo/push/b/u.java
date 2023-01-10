package com.vivo.push.b;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a;

/* compiled from: OnUndoMsgReceiveCommand */
public final class u extends v {
    public long c = -1;
    private int h;

    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final String toString() {
        return "OnUndoMsgCommand";
    }

    public u() {
        super(20);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.v, com.vivo.push.b.s, com.vivo.push.g
    public final void b(a aVar) {
        AppMethodBeat.i(2377, false);
        super.b(aVar);
        aVar.a("undo_msg_v1", this.c);
        aVar.a("undo_msg_type_v1", this.h);
        AppMethodBeat.o(2377);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.v, com.vivo.push.b.s, com.vivo.push.g
    public final void c(a aVar) {
        AppMethodBeat.i(2382, false);
        super.c(aVar);
        this.c = aVar.b("undo_msg_v1", this.c);
        this.h = aVar.b("undo_msg_type_v1", 0);
        AppMethodBeat.o(2382);
    }
}
