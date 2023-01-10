package com.vivo.push.b;

import android.os.Bundle;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a;

/* compiled from: OnLogReceiveCommand */
public final class n extends s {
    public String c;
    public int d = 0;
    public boolean e = false;

    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final String toString() {
        return "OnLogCommand";
    }

    public n() {
        super(7);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void b(a aVar) {
        AppMethodBeat.i(2245, false);
        super.b(aVar);
        aVar.a("content", this.c);
        aVar.a("log_level", this.d);
        boolean z = this.e;
        if (aVar.a == null) {
            aVar.a = new Bundle();
        }
        aVar.a.putBoolean("is_server_log", z);
        AppMethodBeat.o(2245);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void c(a aVar) {
        boolean z = false;
        AppMethodBeat.i(2250, false);
        super.c(aVar);
        this.c = aVar.a("content");
        this.d = aVar.b("log_level", 0);
        if (aVar.a != null) {
            z = aVar.a.getBoolean("is_server_log", false);
        }
        this.e = z;
        AppMethodBeat.o(2250);
    }
}
