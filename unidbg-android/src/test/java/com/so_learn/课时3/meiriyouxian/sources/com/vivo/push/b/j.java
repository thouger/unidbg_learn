package com.vivo.push.b;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a;

/* compiled from: OnChangePushStatusReceiveCommand */
public final class j extends s {
    public int c = -1;
    public int d = -1;

    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final String toString() {
        return "OnChangePushStatusCommand";
    }

    public j() {
        super(12);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void b(a aVar) {
        AppMethodBeat.i(2201, false);
        super.b(aVar);
        aVar.a("OnChangePushStatus.EXTRA_REQ_SERVICE_STATUS", this.c);
        aVar.a("OnChangePushStatus.EXTRA_REQ_RECEIVER_STATUS", this.d);
        AppMethodBeat.o(2201);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void c(a aVar) {
        AppMethodBeat.i(2204, false);
        super.c(aVar);
        this.c = aVar.b("OnChangePushStatus.EXTRA_REQ_SERVICE_STATUS", this.c);
        this.d = aVar.b("OnChangePushStatus.EXTRA_REQ_RECEIVER_STATUS", this.d);
        AppMethodBeat.o(2204);
    }
}
