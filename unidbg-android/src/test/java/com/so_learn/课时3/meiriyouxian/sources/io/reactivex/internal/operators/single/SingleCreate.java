package io.reactivex.internal.operators.single;

import io.reactivex.aa;
import io.reactivex.c.f;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.CancellableDisposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.x;
import io.reactivex.y;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleCreate<T> extends x<T> {
    final aa<T> a;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        Emitter emitter = new Emitter(zVar);
        zVar.onSubscribe(emitter);
        try {
            this.a.a(emitter);
        } catch (Throwable th) {
            a.b(th);
            emitter.onError(th);
        }
    }

    static final class Emitter<T> extends AtomicReference<b> implements b, y<T> {
        private static final long serialVersionUID = -2467358622224974244L;
        final z<? super T> downstream;

        Emitter(z<? super T> zVar) {
            this.downstream = zVar;
        }

        public void onSuccess(T t) {
            b andSet;
            if (get() != DisposableHelper.DISPOSED && (andSet = getAndSet(DisposableHelper.DISPOSED)) != DisposableHelper.DISPOSED) {
                if (t == null) {
                    try {
                        this.downstream.onError(new NullPointerException("onSuccess called with null. Null values are generally not allowed in 2.x operators and sources."));
                    } catch (Throwable th) {
                        if (andSet != null) {
                            andSet.dispose();
                        }
                        throw th;
                    }
                } else {
                    this.downstream.onSuccess(t);
                }
                if (andSet != null) {
                    andSet.dispose();
                }
            }
        }

        public void onError(Throwable th) {
            if (!tryOnError(th)) {
                io.reactivex.e.a.a(th);
            }
        }

        public boolean tryOnError(Throwable th) {
            b andSet;
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (get() == DisposableHelper.DISPOSED || (andSet = getAndSet(DisposableHelper.DISPOSED)) == DisposableHelper.DISPOSED) {
                return false;
            }
            try {
                this.downstream.onError(th);
            } finally {
                if (andSet != null) {
                    andSet.dispose();
                }
            }
        }

        public void setDisposable(b bVar) {
            DisposableHelper.set(this, bVar);
        }

        public void setCancellable(f fVar) {
            setDisposable(new CancellableDisposable(fVar));
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // java.util.concurrent.atomic.AtomicReference, java.lang.Object
        public String toString() {
            return String.format("%s{%s}", getClass().getSimpleName(), super.toString());
        }
    }
}
