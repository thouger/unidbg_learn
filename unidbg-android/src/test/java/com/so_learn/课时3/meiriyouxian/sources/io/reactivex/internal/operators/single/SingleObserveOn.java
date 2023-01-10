package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.w;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleObserveOn<T> extends x<T> {
    final ab<T> a;
    final w b;

    public SingleObserveOn(ab<T> abVar, w wVar) {
        this.a = abVar;
        this.b = wVar;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        this.a.a(new ObserveOnSingleObserver(zVar, this.b));
    }

    static final class ObserveOnSingleObserver<T> extends AtomicReference<b> implements b, z<T>, Runnable {
        private static final long serialVersionUID = 3528003840217436037L;
        final z<? super T> downstream;
        Throwable error;
        final w scheduler;
        T value;

        ObserveOnSingleObserver(z<? super T> zVar, w wVar) {
            this.downstream = zVar;
            this.scheduler = wVar;
        }

        @Override // io.reactivex.z
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            this.value = t;
            DisposableHelper.replace(this, this.scheduler.a(this));
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.error = th;
            DisposableHelper.replace(this, this.scheduler.a(this));
        }

        @Override // java.lang.Runnable
        public void run() {
            Throwable th = this.error;
            if (th != null) {
                this.downstream.onError(th);
            } else {
                this.downstream.onSuccess(this.value);
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }
}
