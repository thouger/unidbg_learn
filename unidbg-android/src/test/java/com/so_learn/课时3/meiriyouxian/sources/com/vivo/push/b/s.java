package com.vivo.push.b;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a;
import com.vivo.push.g;

/* compiled from: OnReceiveCommand */
public class s extends g {
    public String f = null;
    public int g = 0;

    @Override // com.vivo.push.g
    public String toString() {
        return "OnReceiveCommand";
    }

    public s(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public void b(a aVar) {
        AppMethodBeat.i(2188, false);
        aVar.a("req_id", this.f);
        aVar.a("status_msg_code", this.g);
        AppMethodBeat.o(2188);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public void c(a aVar) {
        AppMethodBeat.i(2193, false);
        this.f = aVar.a("req_id");
        this.g = aVar.b("status_msg_code", this.g);
        AppMethodBeat.o(2193);
    }
}
