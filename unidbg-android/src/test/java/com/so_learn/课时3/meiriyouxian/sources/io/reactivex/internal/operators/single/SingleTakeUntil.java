package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.e.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.d;

public final class SingleTakeUntil<T, U> extends x<T> {
    final ab<T> a;
    final b<U> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        TakeUntilMainObserver takeUntilMainObserver = new TakeUntilMainObserver(zVar);
        zVar.onSubscribe(takeUntilMainObserver);
        this.b.subscribe(takeUntilMainObserver.other);
        this.a.a(takeUntilMainObserver);
    }

    static final class TakeUntilMainObserver<T> extends AtomicReference<io.reactivex.disposables.b> implements io.reactivex.disposables.b, z<T> {
        private static final long serialVersionUID = -622603812305745221L;
        final z<? super T> downstream;
        final TakeUntilOtherSubscriber other = new TakeUntilOtherSubscriber(this);

        TakeUntilMainObserver(z<? super T> zVar) {
            this.downstream = zVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
            this.other.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.z
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            this.other.dispose();
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onSuccess(t);
            }
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.other.dispose();
            if (get() == DisposableHelper.DISPOSED || getAndSet(DisposableHelper.DISPOSED) == DisposableHelper.DISPOSED) {
                a.a(th);
            } else {
                this.downstream.onError(th);
            }
        }

        /* access modifiers changed from: package-private */
        public void otherError(Throwable th) {
            io.reactivex.disposables.b andSet;
            if (get() == DisposableHelper.DISPOSED || (andSet = getAndSet(DisposableHelper.DISPOSED)) == DisposableHelper.DISPOSED) {
                a.a(th);
                return;
            }
            if (andSet != null) {
                andSet.dispose();
            }
            this.downstream.onError(th);
        }
    }

    static final class TakeUntilOtherSubscriber extends AtomicReference<d> implements j<Object> {
        private static final long serialVersionUID = 5170026210238877381L;
        final TakeUntilMainObserver<?> parent;

        TakeUntilOtherSubscriber(TakeUntilMainObserver<?> takeUntilMainObserver) {
            this.parent = takeUntilMainObserver;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.setOnce(this, dVar, Long.MAX_VALUE);
        }

        public void onNext(Object obj) {
            if (SubscriptionHelper.cancel(this)) {
                this.parent.otherError(new CancellationException());
            }
        }

        public void onError(Throwable th) {
            this.parent.otherError(th);
        }

        public void onComplete() {
            if (get() != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.otherError(new CancellationException());
            }
        }

        public void dispose() {
            SubscriptionHelper.cancel(this);
        }
    }
}
