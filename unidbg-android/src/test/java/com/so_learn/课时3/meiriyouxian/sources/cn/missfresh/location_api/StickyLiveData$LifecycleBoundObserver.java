package cn.missfresh.location_api;

import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class StickyLiveData$LifecycleBoundObserver extends StickyLiveData<T>.b implements GenericLifecycleObserver {
    final LifecycleOwner a;
    final /* synthetic */ StickyLiveData b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StickyLiveData$LifecycleBoundObserver(StickyLiveData stickyLiveData, LifecycleOwner lifecycleOwner, Observer<T> observer, boolean z) {
        super(stickyLiveData, observer, z);
        this.b = stickyLiveData;
        this.a = lifecycleOwner;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        AppMethodBeat.i(13071, false);
        boolean isAtLeast = this.a.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
        AppMethodBeat.o(13071);
        return isAtLeast;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        AppMethodBeat.i(13073, false);
        if (this.a.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            this.b.b(this.c);
            AppMethodBeat.o(13073);
            return;
        }
        a(a());
        AppMethodBeat.o(13073);
    }

    /* access modifiers changed from: package-private */
    public boolean a(LifecycleOwner lifecycleOwner) {
        return this.a == lifecycleOwner;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        AppMethodBeat.i(13075, false);
        this.a.getLifecycle().removeObserver(this);
        AppMethodBeat.o(13075);
    }
}
