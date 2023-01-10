package cn.missfresh.module.user.login.livedata;

import androidx.lifecycle.Observer;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class EventObserver<T> implements Observer<b<T>> {
    private final OnEventUnhandledContent a;

    @Override // androidx.lifecycle.Observer
    public /* synthetic */ void onChanged(Object obj) {
        AppMethodBeat.i(7821, false);
        a((b) obj);
        AppMethodBeat.o(7821);
    }

    public void a(b<T> bVar) {
        AppMethodBeat.i(7819, false);
        if (bVar == null) {
            AppMethodBeat.o(7819);
            return;
        }
        Object a = bVar.a();
        if (a != null) {
            this.a.onEventUnhandledContent(a);
        }
        AppMethodBeat.o(7819);
    }
}
