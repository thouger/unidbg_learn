package com.sdk.base.framework.a.c;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public final class n extends k<Runnable> implements Runnable {
    public n(int i, Runnable runnable) {
        super(i, runnable);
    }

    @Override // java.lang.Runnable
    public final void run() {
        AppMethodBeat.i(20585, false);
        ((Runnable) this.b).run();
        AppMethodBeat.o(20585);
    }
}
