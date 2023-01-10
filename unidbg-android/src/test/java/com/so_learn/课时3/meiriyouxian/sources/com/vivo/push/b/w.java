package com.vivo.push.b;

import android.view.WindowManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a;
import com.vivo.push.g;

/* compiled from: PushModeCommand */
public final class w extends g {
    public int c = 0;

    @Override // com.vivo.push.g
    public final boolean a() {
        return true;
    }

    @Override // com.vivo.push.g
    public final String toString() {
        return "PushModeCommand";
    }

    public w() {
        super(WindowManager.LayoutParams.TYPE_INPUT_METHOD);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public final void b(a aVar) {
        AppMethodBeat.i(2072, false);
        aVar.a("com.bbk.push.ikey.MODE_TYPE", this.c);
        AppMethodBeat.o(2072);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public final void c(a aVar) {
        AppMethodBeat.i(2075, false);
        this.c = aVar.b("com.bbk.push.ikey.MODE_TYPE", 0);
        AppMethodBeat.o(2075);
    }
}
