package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.w;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleUnsubscribeOn<T> extends x<T> {
    final ab<T> a;
    final w b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        this.a.a(new UnsubscribeOnSingleObserver(zVar, this.b));
    }

    static final class UnsubscribeOnSingleObserver<T> extends AtomicReference<b> implements b, z<T>, Runnable {
        private static final long serialVersionUID = 3256698449646456986L;
        final z<? super T> downstream;
        b ds;
        final w scheduler;

        UnsubscribeOnSingleObserver(z<? super T> zVar, w wVar) {
            this.downstream = zVar;
            this.scheduler = wVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            b andSet = getAndSet(DisposableHelper.DISPOSED);
            if (andSet != DisposableHelper.DISPOSED) {
                this.ds = andSet;
                this.scheduler.a(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.ds.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.z
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }
    }
}
