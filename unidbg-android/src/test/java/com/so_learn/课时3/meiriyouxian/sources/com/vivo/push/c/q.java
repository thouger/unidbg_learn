package com.vivo.push.c;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.b.i;
import com.vivo.push.d;
import com.vivo.push.f;
import com.vivo.push.g;

/* compiled from: OnUnBindAppReceiveTask */
public final class q extends o {
    public q(g gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_APP_FORCE_STOP, false);
        i iVar = (i) gVar;
        d.a().a(iVar.f, iVar.g, new Object[0]);
        f.b(new AnonymousClass1(iVar));
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_APP_FORCE_STOP);
    }

    /* compiled from: OnUnBindAppReceiveTask */
    /* renamed from: com.vivo.push.c.q$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ i a;

        AnonymousClass1(i iVar) {
            this.a = iVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(MetricsProto.MetricsEvent.RESERVED_FOR_LOGBUILDER_NAME, false);
            q.this.c.onUnBind(q.this.a, this.a.g, this.a.c);
            AppMethodBeat.o(MetricsProto.MetricsEvent.RESERVED_FOR_LOGBUILDER_NAME);
        }
    }
}
