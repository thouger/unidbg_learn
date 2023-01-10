package com.vivo.push.b;

import android.view.WindowManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a;

/* compiled from: OnDispatcherReceiveCommand */
public final class l extends s {
    public int c = -1;
    public int d = -1;

    public l() {
        super(WindowManager.LayoutParams.TYPE_DRAG);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void b(a aVar) {
        AppMethodBeat.i(2219, false);
        super.b(aVar);
        aVar.a("key_dispatch_environment", this.c);
        aVar.a("key_dispatch_area", this.d);
        AppMethodBeat.o(2219);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void c(a aVar) {
        AppMethodBeat.i(2223, false);
        super.c(aVar);
        this.c = aVar.b("key_dispatch_environment", 1);
        this.d = aVar.b("key_dispatch_area", 1);
        AppMethodBeat.o(2223);
    }
}
