package cn.missfresh.module.user.login.livedata;

import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class MFStickyLiveData2$LifecycleBoundObserver extends MFStickyLiveData2<T>.a implements GenericLifecycleObserver {
    final LifecycleOwner a;
    final /* synthetic */ MFStickyLiveData2 b;

    /* access modifiers changed from: package-private */
    public boolean a() {
        AppMethodBeat.i(7843, false);
        boolean isAtLeast = this.a.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
        AppMethodBeat.o(7843);
        return isAtLeast;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        AppMethodBeat.i(7847, false);
        if (this.a.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            this.b.a(this.c);
            AppMethodBeat.o(7847);
            return;
        }
        a(a());
        AppMethodBeat.o(7847);
    }

    /* access modifiers changed from: package-private */
    public void b() {
        AppMethodBeat.i(7854, false);
        this.a.getLifecycle().removeObserver(this);
        AppMethodBeat.o(7854);
    }
}
