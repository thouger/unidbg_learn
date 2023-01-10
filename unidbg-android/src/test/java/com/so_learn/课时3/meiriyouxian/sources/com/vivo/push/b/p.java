package com.vivo.push.b;

import android.telephony.PreciseDisconnectCause;
import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a;
import com.vivo.push.g;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.util.o;

/* compiled from: OnNotificationClickReceiveCommand */
public final class p extends g {
    public String c;
    public String d;
    public long e;
    public InsideNotificationItem f;

    @Override // com.vivo.push.g
    public final String toString() {
        return "OnNotificationClickCommand";
    }

    public p(String str, long j, InsideNotificationItem insideNotificationItem) {
        super(5);
        this.c = str;
        this.e = j;
        this.f = insideNotificationItem;
        this.d = null;
    }

    public p() {
        super(5);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public final void b(a aVar) {
        AppMethodBeat.i(2297, false);
        aVar.a("package_name", this.c);
        aVar.a("notify_id", this.e);
        aVar.a("notification_v1", o.b(this.f));
        aVar.a("open_pkg_name", this.d);
        AppMethodBeat.o(2297);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public final void c(a aVar) {
        AppMethodBeat.i(PreciseDisconnectCause.SUPP_SVC_REINVITE_COLLISION, false);
        this.c = aVar.a("package_name");
        this.e = aVar.b("notify_id", -1L);
        this.d = aVar.a("open_pkg_name");
        String a = aVar.a("notification_v1");
        if (!TextUtils.isEmpty(a)) {
            this.f = o.a(a);
        }
        InsideNotificationItem insideNotificationItem = this.f;
        if (insideNotificationItem != null) {
            insideNotificationItem.setMsgId(this.e);
        }
        AppMethodBeat.o(PreciseDisconnectCause.SUPP_SVC_REINVITE_COLLISION);
    }
}
