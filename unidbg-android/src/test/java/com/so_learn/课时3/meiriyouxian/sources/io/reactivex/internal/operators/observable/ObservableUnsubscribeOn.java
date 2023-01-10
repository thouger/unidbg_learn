package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.v;
import io.reactivex.w;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ObservableUnsubscribeOn<T> extends a<T, T> {
    final w b;

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        this.a.subscribe(new UnsubscribeObserver(vVar, this.b));
    }

    static final class UnsubscribeObserver<T> extends AtomicBoolean implements b, v<T> {
        private static final long serialVersionUID = 1015244841293359600L;
        final v<? super T> downstream;
        final w scheduler;
        b upstream;

        UnsubscribeObserver(v<? super T> vVar, w wVar) {
            this.downstream = vVar;
            this.scheduler = wVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            if (!get()) {
                this.downstream.onNext(t);
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (get()) {
                io.reactivex.e.a.a(th);
            } else {
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (!get()) {
                this.downstream.onComplete();
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (compareAndSet(false, true)) {
                this.scheduler.a(new a());
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get();
        }

        final class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                UnsubscribeObserver.this.upstream.dispose();
            }
        }
    }
}
