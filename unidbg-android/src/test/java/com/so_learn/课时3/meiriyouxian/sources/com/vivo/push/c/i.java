package com.vivo.push.c;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.b.m;
import com.vivo.push.f;
import com.vivo.push.g;

/* compiled from: OnListTagReceiveTask */
public final class i extends o {
    public i(g gVar) {
        super(gVar);
    }

    /* compiled from: OnListTagReceiveTask */
    /* renamed from: com.vivo.push.c.i$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ m a;

        AnonymousClass1(m mVar) {
            this.a = mVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_DELETE_GROUP, false);
            i.this.c.onListTags(i.this.a, this.a.g, this.a.c, this.a.f);
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_DELETE_GROUP);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_ACCOUNT_SYNC_CANNOT_ONETIME_SYNC, false);
        f.b(new AnonymousClass1((m) gVar));
        AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_ACCOUNT_SYNC_CANNOT_ONETIME_SYNC);
    }
}
