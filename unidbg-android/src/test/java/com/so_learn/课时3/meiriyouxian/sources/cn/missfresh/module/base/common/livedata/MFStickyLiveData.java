package cn.missfresh.module.base.common.livedata;

import android.os.Looper;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MFStickyLiveData<T> {
    private static final String a = MFStickyLiveData.class.getSimpleName();
    private static final Object b = new Object();
    private Map<Observer<T>, MFStickyLiveData<T>.b> c = new ConcurrentHashMap();
    private volatile Object d = b;
    private int e = -1;
    private boolean f;
    private boolean g;

    public MFStickyLiveData() {
        AppMethodBeat.i(12100, false);
        AppMethodBeat.o(12100);
    }

    static /* synthetic */ void a(MFStickyLiveData mFStickyLiveData, b bVar) {
        AppMethodBeat.i(12134, false);
        mFStickyLiveData.a((MFStickyLiveData<T>.b) bVar);
        AppMethodBeat.o(12134);
    }

    static {
        AppMethodBeat.i(12135, false);
        AppMethodBeat.o(12135);
    }

    public void a(LifecycleOwner lifecycleOwner, Observer<T> observer) {
        AppMethodBeat.i(12101, false);
        a(lifecycleOwner, observer, false);
        AppMethodBeat.o(12101);
    }

    public void a(Observer<T> observer) {
        AppMethodBeat.i(12103, false);
        a((Observer) observer, false);
        AppMethodBeat.o(12103);
    }

    public void a(LifecycleOwner lifecycleOwner, Observer<T> observer, boolean z) {
        AppMethodBeat.i(12105, false);
        if (lifecycleOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            AppMethodBeat.o(12105);
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(lifecycleOwner, observer, z);
        MFStickyLiveData<T>.b bVar = this.c.get(observer);
        if (bVar == null) {
            bVar = this.c.put(observer, lifecycleBoundObserver);
        }
        if (bVar != null && !bVar.a(lifecycleOwner)) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            AppMethodBeat.o(12105);
            throw illegalArgumentException;
        } else if (bVar != null) {
            AppMethodBeat.o(12105);
        } else {
            lifecycleOwner.getLifecycle().addObserver(lifecycleBoundObserver);
            AppMethodBeat.o(12105);
        }
    }

    public void a(Observer<T> observer, boolean z) {
        AppMethodBeat.i(12106, false);
        a aVar = new a(observer, z);
        MFStickyLiveData<T>.b bVar = this.c.get(observer);
        if (bVar == null) {
            bVar = this.c.put(observer, aVar);
        }
        if (bVar != null && (bVar instanceof LifecycleBoundObserver)) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            AppMethodBeat.o(12106);
            throw illegalArgumentException;
        } else if (bVar != null) {
            AppMethodBeat.o(12106);
        } else {
            aVar.a(true);
            AppMethodBeat.o(12106);
        }
    }

    public void a(T t) {
        AppMethodBeat.i(12107, false);
        a("setValue");
        this.e++;
        this.d = t;
        a((MFStickyLiveData<T>.b) null);
        AppMethodBeat.o(12107);
    }

    /* access modifiers changed from: package-private */
    public class LifecycleBoundObserver extends MFStickyLiveData<T>.b implements GenericLifecycleObserver {
        final LifecycleOwner a;

        LifecycleBoundObserver(LifecycleOwner lifecycleOwner, Observer<T> observer, boolean z) {
            super(observer, z);
            this.a = lifecycleOwner;
        }

        /* access modifiers changed from: package-private */
        @Override // cn.missfresh.module.base.common.livedata.MFStickyLiveData.b
        public boolean a() {
            AppMethodBeat.i(12092, false);
            boolean isAtLeast = this.a.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
            AppMethodBeat.o(12092);
            return isAtLeast;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            AppMethodBeat.i(12094, false);
            if (this.a.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
                MFStickyLiveData.this.b(this.c);
                AppMethodBeat.o(12094);
                return;
            }
            a(a());
            AppMethodBeat.o(12094);
        }

        /* access modifiers changed from: package-private */
        @Override // cn.missfresh.module.base.common.livedata.MFStickyLiveData.b
        public boolean a(LifecycleOwner lifecycleOwner) {
            return this.a == lifecycleOwner;
        }

        /* access modifiers changed from: package-private */
        @Override // cn.missfresh.module.base.common.livedata.MFStickyLiveData.b
        public void b() {
            AppMethodBeat.i(12096, false);
            this.a.getLifecycle().removeObserver(this);
            AppMethodBeat.o(12096);
        }
    }

    /* access modifiers changed from: private */
    public class a extends MFStickyLiveData<T>.b {
        /* access modifiers changed from: package-private */
        @Override // cn.missfresh.module.base.common.livedata.MFStickyLiveData.b
        public boolean a() {
            return true;
        }

        a(Observer<T> observer, boolean z) {
            super(observer, z);
        }
    }

    /* access modifiers changed from: private */
    public abstract class b {
        final Observer<T> c;
        boolean d;
        int e;
        boolean f;

        /* access modifiers changed from: package-private */
        public abstract boolean a();

        /* access modifiers changed from: package-private */
        public boolean a(LifecycleOwner lifecycleOwner) {
            return false;
        }

        /* access modifiers changed from: package-private */
        public void b() {
        }

        b(Observer<T> observer, boolean z) {
            int i;
            this.c = observer;
            this.f = z;
            if (z) {
                i = -1;
            } else {
                i = MFStickyLiveData.this.e;
            }
            this.e = i;
        }

        /* access modifiers changed from: package-private */
        public void a(boolean z) {
            if (z != this.d) {
                this.d = z;
                if (this.d) {
                    MFStickyLiveData.a(MFStickyLiveData.this, this);
                }
            }
        }
    }

    private void a(MFStickyLiveData<T>.b bVar) {
        AppMethodBeat.i(12129, false);
        if (this.f) {
            this.g = true;
            AppMethodBeat.o(12129);
            return;
        }
        this.f = true;
        do {
            this.g = false;
            if (bVar == null) {
                for (Map.Entry<Observer<T>, MFStickyLiveData<T>.b> entry : this.c.entrySet()) {
                    b(entry.getValue());
                    if (this.g) {
                        break;
                    }
                }
            } else {
                b(bVar);
                bVar = null;
            }
        } while (this.g);
        this.f = false;
        AppMethodBeat.o(12129);
    }

    private void b(MFStickyLiveData<T>.b bVar) {
        AppMethodBeat.i(12130, false);
        if (!bVar.d) {
            AppMethodBeat.o(12130);
        } else if (!bVar.a()) {
            bVar.a(false);
            AppMethodBeat.o(12130);
        } else {
            int i = bVar.e;
            int i2 = this.e;
            if (i >= i2) {
                AppMethodBeat.o(12130);
                return;
            }
            bVar.e = i2;
            bVar.c.onChanged(this.d);
            AppMethodBeat.o(12130);
        }
    }

    public void b(Observer<T> observer) {
        AppMethodBeat.i(12131, false);
        a("removeObserver");
        MFStickyLiveData<T>.b remove = this.c.remove(observer);
        if (remove == null) {
            AppMethodBeat.o(12131);
            return;
        }
        remove.b();
        remove.a(false);
        AppMethodBeat.o(12131);
    }

    private static void a(String str) {
        AppMethodBeat.i(12133, false);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            AppMethodBeat.o(12133);
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException("Cannot invoke " + str + " on a background thread");
        AppMethodBeat.o(12133);
        throw illegalStateException;
    }
}
