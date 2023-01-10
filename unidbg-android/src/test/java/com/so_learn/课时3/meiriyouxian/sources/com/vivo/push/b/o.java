package com.vivo.push.b;

import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a;
import com.vivo.push.model.UnvarnishedMessage;

/* compiled from: OnMessageReceiveCommand */
public final class o extends v {
    protected UnvarnishedMessage c;

    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final String toString() {
        return "OnMessageCommand";
    }

    public o() {
        super(3);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.v, com.vivo.push.b.s, com.vivo.push.g
    public final void b(a aVar) {
        AppMethodBeat.i(2261, false);
        super.b(aVar);
        aVar.a("msg_v1", this.c.unpackToJson());
        AppMethodBeat.o(2261);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.v, com.vivo.push.b.s, com.vivo.push.g
    public final void c(a aVar) {
        AppMethodBeat.i(2266, false);
        super.c(aVar);
        String a = aVar.a("msg_v1");
        if (!TextUtils.isEmpty(a)) {
            this.c = new UnvarnishedMessage(a);
            this.c.setMsgId(this.e);
        }
        AppMethodBeat.o(2266);
    }

    public final String b() {
        AppMethodBeat.i(2271, false);
        UnvarnishedMessage unvarnishedMessage = this.c;
        String unpackToJson = unvarnishedMessage == null ? null : unvarnishedMessage.unpackToJson();
        AppMethodBeat.o(2271);
        return unpackToJson;
    }

    public final UnvarnishedMessage c() {
        return this.c;
    }
}
