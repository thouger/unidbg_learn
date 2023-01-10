package com.vivo.push.b;

import android.view.WindowManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a;
import com.vivo.push.g;

/* compiled from: MsgArriveCommand */
public final class h extends g {
    private String c;

    public h() {
        super(WindowManager.LayoutParams.TYPE_WALLPAPER);
    }

    public h(String str) {
        this();
        this.c = str;
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public final void b(a aVar) {
        AppMethodBeat.i(2162, false);
        aVar.a("MsgArriveCommand.MSG_TAG", this.c);
        AppMethodBeat.o(2162);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public final void c(a aVar) {
        AppMethodBeat.i(2164, false);
        this.c = aVar.a("MsgArriveCommand.MSG_TAG");
        AppMethodBeat.o(2164);
    }
}
