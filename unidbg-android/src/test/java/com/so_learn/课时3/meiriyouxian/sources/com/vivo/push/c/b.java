package com.vivo.push.c;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.a.a;
import com.vivo.push.b.d;
import com.vivo.push.b.f;
import com.vivo.push.b.y;
import com.vivo.push.e;
import com.vivo.push.g;
import com.vivo.push.util.r;

/* compiled from: ChangeNetPermissionTask */
public final class b extends e {
    public b(g gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        boolean z;
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SUPPORT_HELP_AND_FEEDBACK, false);
        com.vivo.push.model.b a = r.a(this.a);
        if (((d) gVar).c) {
            try {
                z = e.a(this.a);
            } catch (Exception e) {
                e.printStackTrace();
                AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SUPPORT_HELP_AND_FEEDBACK);
                return;
            }
        } else {
            z = e.b(this.a);
        }
        if (z) {
            com.vivo.push.model.b a2 = r.a(this.a);
            if (a == null || a2 == null || a2.a == null || !a2.a.equals(a.a)) {
                if (!(a == null || a.a == null)) {
                    a.a(this.a, a.a, new y(a.a));
                }
                if (!(a2 == null || a2.a == null)) {
                    a.a(this.a, a2.a, new f());
                }
            } else {
                AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SUPPORT_HELP_AND_FEEDBACK);
                return;
            }
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SUPPORT_HELP_AND_FEEDBACK);
    }
}
