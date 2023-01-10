package com.vivo.push.c;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.b.n;
import com.vivo.push.f;
import com.vivo.push.g;

/* compiled from: OnLogReceiveTask */
public final class j extends o {
    public j(g gVar) {
        super(gVar);
    }

    /* compiled from: OnLogReceiveTask */
    /* renamed from: com.vivo.push.c.j$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ n a;

        AnonymousClass1(n nVar) {
            this.a = nVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_USER_SETUP, false);
            j.this.c.onLog(j.this.a, this.a.c, this.a.d, this.a.e);
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_USER_SETUP);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_WIFI_PIN, false);
        f.b(new AnonymousClass1((n) gVar));
        AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_WIFI_PIN);
    }
}
