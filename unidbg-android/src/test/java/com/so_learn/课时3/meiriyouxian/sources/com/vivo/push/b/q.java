package com.vivo.push.b;

import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.util.o;

/* compiled from: OnNotifyArrivedReceiveCommand */
public final class q extends v {
    protected InsideNotificationItem c;
    private String h;

    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final String toString() {
        return "OnNotifyArrivedCommand";
    }

    public q() {
        super(4);
    }

    public final InsideNotificationItem b() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.v, com.vivo.push.b.s, com.vivo.push.g
    public final void b(a aVar) {
        AppMethodBeat.i(2321, false);
        super.b(aVar);
        this.h = o.b(this.c);
        aVar.a("notification_v1", this.h);
        AppMethodBeat.o(2321);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.v, com.vivo.push.b.s, com.vivo.push.g
    public final void c(a aVar) {
        AppMethodBeat.i(2326, false);
        super.c(aVar);
        this.h = aVar.a("notification_v1");
        if (!TextUtils.isEmpty(this.h)) {
            this.c = o.a(this.h);
            InsideNotificationItem insideNotificationItem = this.c;
            if (insideNotificationItem != null) {
                insideNotificationItem.setMsgId(this.e);
            }
        }
        AppMethodBeat.o(2326);
    }

    public final String c() {
        String str;
        AppMethodBeat.i(2331, false);
        if (TextUtils.isEmpty(this.h)) {
            InsideNotificationItem insideNotificationItem = this.c;
            str = insideNotificationItem == null ? null : o.b(insideNotificationItem);
        } else {
            str = this.h;
        }
        AppMethodBeat.o(2331);
        return str;
    }
}
