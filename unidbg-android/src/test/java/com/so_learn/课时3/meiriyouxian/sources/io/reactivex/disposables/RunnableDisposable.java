package io.reactivex.disposables;

import com.umeng.message.proguard.l;

/* access modifiers changed from: package-private */
public final class RunnableDisposable extends ReferenceDisposable<Runnable> {
    private static final long serialVersionUID = -8219729196779211169L;

    RunnableDisposable(Runnable runnable) {
        super(runnable);
    }

    /* access modifiers changed from: protected */
    public void onDisposed(Runnable runnable) {
        runnable.run();
    }

    @Override // java.util.concurrent.atomic.AtomicReference, java.lang.Object
    public String toString() {
        return "RunnableDisposable(disposed=" + isDisposed() + ", " + get() + l.t;
    }
}
