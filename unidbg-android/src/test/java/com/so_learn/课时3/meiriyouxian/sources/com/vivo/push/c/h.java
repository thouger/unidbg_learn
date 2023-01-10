package com.vivo.push.c;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.b.l;
import com.vivo.push.g;
import com.vivo.push.util.v;

/* compiled from: OnDispatcherReceiveTask */
public final class h extends o {
    public h(g gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_FINGERPINT_DELETE_LAST, false);
        l lVar = (l) gVar;
        int i = lVar.c;
        int i2 = lVar.d;
        v.b().a("key_dispatch_environment", i);
        v.b().a("key_dispatch_area", i2);
        AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_FINGERPINT_DELETE_LAST);
    }
}
