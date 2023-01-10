package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.e;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableTakeUntil<T, U> extends a<T, T> {
    final t<? extends U> b;

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        TakeUntilMainObserver takeUntilMainObserver = new TakeUntilMainObserver(vVar);
        vVar.onSubscribe(takeUntilMainObserver);
        this.b.subscribe(takeUntilMainObserver.otherObserver);
        this.a.subscribe(takeUntilMainObserver);
    }

    static final class TakeUntilMainObserver<T, U> extends AtomicInteger implements b, v<T> {
        private static final long serialVersionUID = 1418547743690811973L;
        final v<? super T> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final TakeUntilMainObserver<T, U>.OtherObserver otherObserver = new OtherObserver();
        final AtomicReference<b> upstream = new AtomicReference<>();

        TakeUntilMainObserver(v<? super T> vVar) {
            this.downstream = vVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this.otherObserver);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            e.a((v) this.downstream, (Object) t, (AtomicInteger) this, this.error);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.otherObserver);
            e.a((v<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            DisposableHelper.dispose(this.otherObserver);
            e.a(this.downstream, this, this.error);
        }

        /* access modifiers changed from: package-private */
        public void otherError(Throwable th) {
            DisposableHelper.dispose(this.upstream);
            e.a((v<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        /* access modifiers changed from: package-private */
        public void otherComplete() {
            DisposableHelper.dispose(this.upstream);
            e.a(this.downstream, this, this.error);
        }

        final class OtherObserver extends AtomicReference<b> implements v<U> {
            private static final long serialVersionUID = -8693423678067375039L;

            OtherObserver() {
            }

            @Override // io.reactivex.v
            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            @Override // io.reactivex.v
            public void onNext(U u) {
                DisposableHelper.dispose(this);
                TakeUntilMainObserver.this.otherComplete();
            }

            @Override // io.reactivex.v
            public void onError(Throwable th) {
                TakeUntilMainObserver.this.otherError(th);
            }

            @Override // io.reactivex.v
            public void onComplete() {
                TakeUntilMainObserver.this.otherComplete();
            }
        }
    }
}
