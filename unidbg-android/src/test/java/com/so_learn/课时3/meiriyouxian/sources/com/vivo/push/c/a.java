package com.vivo.push.c;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.b.c;
import com.vivo.push.d;
import com.vivo.push.e;
import com.vivo.push.g;
import com.vivo.push.model.b;
import com.vivo.push.util.q;
import com.vivo.push.util.r;

/* compiled from: BindAppSendCommandTask */
public final class a extends e {
    public a(g gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_DELETION_HELPER_DOWNLOADS_DELETION_FAIL, false);
        c cVar = (c) gVar;
        b a = r.a(this.a);
        if (a == null) {
            d.a().a(cVar.e, 1005, new Object[0]);
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_DELETION_HELPER_DOWNLOADS_DELETION_FAIL);
            return;
        }
        String str = a.a;
        if (a.e) {
            d.a().a(cVar.e, 1004, new Object[0]);
            gVar = new com.vivo.push.b.e();
        } else {
            int a2 = q.a(cVar);
            if (a2 != 0) {
                d.a().a(cVar.e, a2, new Object[0]);
                AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_DELETION_HELPER_DOWNLOADS_DELETION_FAIL);
                return;
            }
        }
        com.vivo.push.a.a.a(this.a, str, gVar);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_DELETION_HELPER_DOWNLOADS_DELETION_FAIL);
    }
}
