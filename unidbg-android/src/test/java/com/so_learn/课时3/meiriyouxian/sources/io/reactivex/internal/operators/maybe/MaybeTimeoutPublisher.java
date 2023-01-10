package io.reactivex.internal.operators.maybe;

import io.reactivex.e.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.d;

public final class MaybeTimeoutPublisher<T, U> extends a<T, T> {
    final b<U> b;
    final o<? extends T> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super T> mVar) {
        TimeoutMainMaybeObserver timeoutMainMaybeObserver = new TimeoutMainMaybeObserver(mVar, this.c);
        mVar.onSubscribe(timeoutMainMaybeObserver);
        this.b.subscribe(timeoutMainMaybeObserver.other);
        this.a.a(timeoutMainMaybeObserver);
    }

    static final class TimeoutMainMaybeObserver<T, U> extends AtomicReference<io.reactivex.disposables.b> implements io.reactivex.disposables.b, m<T> {
        private static final long serialVersionUID = -5955289211445418871L;
        final m<? super T> downstream;
        final o<? extends T> fallback;
        final TimeoutOtherMaybeObserver<T, U> other = new TimeoutOtherMaybeObserver<>(this);
        final TimeoutFallbackMaybeObserver<T> otherObserver;

        TimeoutMainMaybeObserver(m<? super T> mVar, o<? extends T> oVar) {
            this.downstream = mVar;
            this.fallback = oVar;
            this.otherObserver = oVar != null ? new TimeoutFallbackMaybeObserver<>(mVar) : null;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
            SubscriptionHelper.cancel(this.other);
            TimeoutFallbackMaybeObserver<T> timeoutFallbackMaybeObserver = this.otherObserver;
            if (timeoutFallbackMaybeObserver != null) {
                DisposableHelper.dispose(timeoutFallbackMaybeObserver);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.m
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            SubscriptionHelper.cancel(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onSuccess(t);
            }
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onError(th);
            } else {
                a.a(th);
            }
        }

        @Override // io.reactivex.m
        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onComplete();
            }
        }

        public void otherError(Throwable th) {
            if (DisposableHelper.dispose(this)) {
                this.downstream.onError(th);
            } else {
                a.a(th);
            }
        }

        public void otherComplete() {
            if (DisposableHelper.dispose(this)) {
                o<? extends T> oVar = this.fallback;
                if (oVar == null) {
                    this.downstream.onError(new TimeoutException());
                } else {
                    oVar.a(this.otherObserver);
                }
            }
        }
    }

    static final class TimeoutOtherMaybeObserver<T, U> extends AtomicReference<d> implements j<Object> {
        private static final long serialVersionUID = 8663801314800248617L;
        final TimeoutMainMaybeObserver<T, U> parent;

        TimeoutOtherMaybeObserver(TimeoutMainMaybeObserver<T, U> timeoutMainMaybeObserver) {
            this.parent = timeoutMainMaybeObserver;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.setOnce(this, dVar, Long.MAX_VALUE);
        }

        public void onNext(Object obj) {
            get().cancel();
            this.parent.otherComplete();
        }

        public void onError(Throwable th) {
            this.parent.otherError(th);
        }

        public void onComplete() {
            this.parent.otherComplete();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class TimeoutFallbackMaybeObserver<T> extends AtomicReference<io.reactivex.disposables.b> implements m<T> {
        private static final long serialVersionUID = 8663801314800248617L;
        final m<? super T> downstream;

        TimeoutFallbackMaybeObserver(m<? super T> mVar) {
            this.downstream = mVar;
        }

        @Override // io.reactivex.m
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.m
        public void onComplete() {
            this.downstream.onComplete();
        }
    }
}
