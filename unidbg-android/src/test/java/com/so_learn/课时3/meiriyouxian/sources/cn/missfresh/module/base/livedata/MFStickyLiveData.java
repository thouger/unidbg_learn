package cn.missfresh.module.base.livedata;

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
    private Map<Observer<T>, MFStickyLiveData<T>.a> c = new ConcurrentHashMap();
    private volatile Object d = b;
    private int e = -1;
    private boolean f;
    private boolean g;

    public MFStickyLiveData() {
        AppMethodBeat.i(13815, false);
        AppMethodBeat.o(13815);
    }

    static /* synthetic */ void a(MFStickyLiveData mFStickyLiveData, a aVar) {
        AppMethodBeat.i(13851, false);
        mFStickyLiveData.a((MFStickyLiveData<T>.a) aVar);
        AppMethodBeat.o(13851);
    }

    static {
        AppMethodBeat.i(13855, false);
        AppMethodBeat.o(13855);
    }

    public void a(LifecycleOwner lifecycleOwner, Observer<T> observer) {
        AppMethodBeat.i(13817, false);
        a(lifecycleOwner, observer, false);
        AppMethodBeat.o(13817);
    }

    public void a(LifecycleOwner lifecycleOwner, Observer<T> observer, boolean z) {
        AppMethodBeat.i(13823, false);
        if (lifecycleOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            AppMethodBeat.o(13823);
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(lifecycleOwner, observer, z);
        MFStickyLiveData<T>.a aVar = this.c.get(observer);
        if (aVar == null) {
            aVar = this.c.put(observer, lifecycleBoundObserver);
        }
        if (aVar != null && !aVar.a(lifecycleOwner)) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            AppMethodBeat.o(13823);
            throw illegalArgumentException;
        } else if (aVar != null) {
            AppMethodBeat.o(13823);
        } else {
            lifecycleOwner.getLifecycle().addObserver(lifecycleBoundObserver);
            AppMethodBeat.o(13823);
        }
    }

    public void a(T t) {
        AppMethodBeat.i(13828, false);
        a("setValue");
        this.e++;
        this.d = t;
        a((MFStickyLiveData<T>.a) null);
        AppMethodBeat.o(13828);
    }

    /* access modifiers changed from: package-private */
    public class LifecycleBoundObserver extends MFStickyLiveData<T>.a implements GenericLifecycleObserver {
        final LifecycleOwner a;

        LifecycleBoundObserver(LifecycleOwner lifecycleOwner, Observer<T> observer, boolean z) {
            super(observer, z);
            this.a = lifecycleOwner;
        }

        /* access modifiers changed from: package-private */
        @Override // cn.missfresh.module.base.livedata.MFStickyLiveData.a
        public boolean a() {
            AppMethodBeat.i(13799, false);
            boolean isAtLeast = this.a.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
            AppMethodBeat.o(13799);
            return isAtLeast;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            AppMethodBeat.i(13802, false);
            if (this.a.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
                MFStickyLiveData.this.a((Observer) this.c);
                AppMethodBeat.o(13802);
                return;
            }
            a(a());
            AppMethodBeat.o(13802);
        }

        /* access modifiers changed from: package-private */
        @Override // cn.missfresh.module.base.livedata.MFStickyLiveData.a
        public boolean a(LifecycleOwner lifecycleOwner) {
            return this.a == lifecycleOwner;
        }

        /* access modifiers changed from: package-private */
        @Override // cn.missfresh.module.base.livedata.MFStickyLiveData.a
        public void b() {
            AppMethodBeat.i(13806, false);
            this.a.getLifecycle().removeObserver(this);
            AppMethodBeat.o(13806);
        }
    }

    /* access modifiers changed from: private */
    public abstract class a {
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

        a(Observer<T> observer, boolean z) {
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

    private void a(MFStickyLiveData<T>.a aVar) {
        AppMethodBeat.i(13833, false);
        if (this.f) {
            this.g = true;
            AppMethodBeat.o(13833);
            return;
        }
        this.f = true;
        do {
            this.g = false;
            if (aVar == null) {
                for (Map.Entry<Observer<T>, MFStickyLiveData<T>.a> entry : this.c.entrySet()) {
                    b(entry.getValue());
                    if (this.g) {
                        break;
                    }
                }
            } else {
                b(aVar);
                aVar = null;
            }
        } while (this.g);
        this.f = false;
        AppMethodBeat.o(13833);
    }

    private void b(MFStickyLiveData<T>.a aVar) {
        AppMethodBeat.i(13836, false);
        if (!aVar.d) {
            AppMethodBeat.o(13836);
        } else if (!aVar.a()) {
            aVar.a(false);
            AppMethodBeat.o(13836);
        } else {
            int i = aVar.e;
            int i2 = this.e;
            if (i >= i2) {
                AppMethodBeat.o(13836);
                return;
            }
            aVar.e = i2;
            aVar.c.onChanged(this.d);
            AppMethodBeat.o(13836);
        }
    }

    public void a(Observer<T> observer) {
        AppMethodBeat.i(13839, false);
        a("removeObserver");
        MFStickyLiveData<T>.a remove = this.c.remove(observer);
        if (remove == null) {
            AppMethodBeat.o(13839);
            return;
        }
        remove.b();
        remove.a(false);
        AppMethodBeat.o(13839);
    }

    public void a(LifecycleOwner lifecycleOwner) {
        AppMethodBeat.i(13841, false);
        a("removeObservers");
        for (Map.Entry<Observer<T>, MFStickyLiveData<T>.a> entry : this.c.entrySet()) {
            if (entry.getValue().a(lifecycleOwner)) {
                a((Observer) entry.getKey());
            }
        }
        AppMethodBeat.o(13841);
    }

    private static void a(String str) {
        AppMethodBeat.i(13843, false);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            AppMethodBeat.o(13843);
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException("Cannot invoke " + str + " on a background thread");
        AppMethodBeat.o(13843);
        throw illegalStateException;
    }
}
