package com.vivo.push.c;

import android.content.SharedPreferences;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.g;
import com.vivo.push.util.a;
import com.vivo.push.util.n;
import com.vivo.push.util.w;
import com.vivo.push.util.x;

/* compiled from: OnClearCacheReceiveTask */
public final class f extends o {
    public f(g gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_CONFIRM_AUTO_SYNC_CHANGE, false);
        n.d("OnClearCacheTask", "delete push info " + this.a.getPackageName());
        x b = x.b(this.a);
        w wVar = new w();
        if (wVar.a(b.a)) {
            SharedPreferences.Editor edit = wVar.b.edit();
            if (edit != null) {
                edit.clear();
                a.a(edit);
            }
            n.d(w.a, "system cache is cleared");
            n.d("SystemCache", "sp cache is cleared");
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_CONFIRM_AUTO_SYNC_CHANGE);
    }
}
