package com.vivo.push.c;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.b.r;
import com.vivo.push.f;
import com.vivo.push.g;

/* compiled from: OnPublishReceiveTask */
public final class n extends o {
    public n(g gVar) {
        super(gVar);
    }

    /* compiled from: OnPublishReceiveTask */
    /* renamed from: com.vivo.push.c.n$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ r a;

        AnonymousClass1(r rVar) {
            this.a = rVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(MetricsProto.MetricsEvent.APP_SPECIAL_PERMISSION_ADMIN_ALLOW, false);
            n.this.c.onPublish(n.this.a, this.a.g, this.a.f);
            AppMethodBeat.o(MetricsProto.MetricsEvent.APP_SPECIAL_PERMISSION_ADMIN_ALLOW);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        AppMethodBeat.i(769, false);
        f.b(new AnonymousClass1((r) gVar));
        AppMethodBeat.o(769);
    }
}
