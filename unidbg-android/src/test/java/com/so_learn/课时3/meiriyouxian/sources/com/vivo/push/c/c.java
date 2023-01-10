package com.vivo.push.c;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.e;
import com.vivo.push.g;
import com.vivo.push.util.n;

/* compiled from: InitTask */
public final class c extends e {
    public c(g gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SUPPORT_VIEW_TRAVEL_ABROAD_DIALOG, false);
        n.a(ClientConfigManagerImpl.getInstance(this.a).isDebug());
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SUPPORT_VIEW_TRAVEL_ABROAD_DIALOG);
    }
}
