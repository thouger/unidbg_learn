package io.reactivex.internal.observers;

import io.reactivex.disposables.b;
import io.reactivex.internal.a.c;
import io.reactivex.internal.a.h;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicReference;

public final class InnerQueuedObserver<T> extends AtomicReference<b> implements b, v<T> {
    private static final long serialVersionUID = -5417183359794346637L;
    volatile boolean done;
    int fusionMode;
    final d<T> parent;
    final int prefetch;
    h<T> queue;

    public InnerQueuedObserver(d<T> dVar, int i) {
        this.parent = dVar;
        this.prefetch = i;
    }

    @Override // io.reactivex.v
    public void onSubscribe(b bVar) {
        if (DisposableHelper.setOnce(this, bVar)) {
            if (bVar instanceof c) {
                c cVar = (c) bVar;
                int requestFusion = cVar.requestFusion(3);
                if (requestFusion == 1) {
                    this.fusionMode = requestFusion;
                    this.queue = cVar;
                    this.done = true;
                    this.parent.innerComplete(this);
                    return;
                } else if (requestFusion == 2) {
                    this.fusionMode = requestFusion;
                    this.queue = cVar;
                    return;
                }
            }
            this.queue = io.reactivex.internal.util.h.a(-this.prefetch);
        }
    }

    @Override // io.reactivex.v
    public void onNext(T t) {
        if (this.fusionMode == 0) {
            this.parent.innerNext(this, t);
        } else {
            this.parent.drain();
        }
    }

    @Override // io.reactivex.v
    public void onError(Throwable th) {
        this.parent.innerError(this, th);
    }

    @Override // io.reactivex.v
    public void onComplete() {
        this.parent.innerComplete(this);
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        DisposableHelper.dispose(this);
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone() {
        this.done = true;
    }

    public h<T> queue() {
        return this.queue;
    }

    public int fusionMode() {
        return this.fusionMode;
    }
}
