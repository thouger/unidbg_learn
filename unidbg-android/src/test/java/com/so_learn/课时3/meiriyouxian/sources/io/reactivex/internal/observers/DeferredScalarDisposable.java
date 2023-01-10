package io.reactivex.internal.observers;

import io.reactivex.e.a;
import io.reactivex.v;

public class DeferredScalarDisposable<T> extends BasicIntQueueDisposable<T> {
    static final int DISPOSED = 4;
    static final int FUSED_CONSUMED = 32;
    static final int FUSED_EMPTY = 8;
    static final int FUSED_READY = 16;
    static final int TERMINATED = 2;
    private static final long serialVersionUID = -5502432239815349361L;
    protected final v<? super T> downstream;
    protected T value;

    public DeferredScalarDisposable(v<? super T> vVar) {
        this.downstream = vVar;
    }

    @Override // io.reactivex.internal.a.d
    public final int requestFusion(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        lazySet(8);
        return 2;
    }

    public final void complete(T t) {
        int i = get();
        if ((i & 54) == 0) {
            v<? super T> vVar = this.downstream;
            if (i == 8) {
                this.value = t;
                lazySet(16);
                vVar.onNext(null);
            } else {
                lazySet(2);
                vVar.onNext(t);
            }
            if (get() != 4) {
                vVar.onComplete();
            }
        }
    }

    public final void error(Throwable th) {
        if ((get() & 54) != 0) {
            a.a(th);
            return;
        }
        lazySet(2);
        this.downstream.onError(th);
    }

    public final void complete() {
        if ((get() & 54) == 0) {
            lazySet(2);
            this.downstream.onComplete();
        }
    }

    @Override // io.reactivex.internal.a.h
    public final T poll() throws Exception {
        if (get() != 16) {
            return null;
        }
        T t = this.value;
        this.value = null;
        lazySet(32);
        return t;
    }

    @Override // io.reactivex.internal.a.h
    public final boolean isEmpty() {
        return get() != 16;
    }

    @Override // io.reactivex.internal.a.h
    public final void clear() {
        lazySet(32);
        this.value = null;
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        set(4);
        this.value = null;
    }

    public final boolean tryDispose() {
        return getAndSet(4) != 4;
    }

    @Override // io.reactivex.disposables.b
    public final boolean isDisposed() {
        return get() == 4;
    }
}
