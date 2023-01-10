package io.reactivex.internal.operators.observable;

import io.reactivex.c.c;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableWithLatestFrom<T, U, R> extends a<T, R> {
    final c<? super T, ? super U, ? extends R> b;
    final t<? extends U> c;

    @Override // io.reactivex.q
    public void a(v<? super R> vVar) {
        io.reactivex.observers.c cVar = new io.reactivex.observers.c(vVar);
        WithLatestFromObserver withLatestFromObserver = new WithLatestFromObserver(cVar, this.b);
        cVar.onSubscribe(withLatestFromObserver);
        this.c.subscribe(new a(withLatestFromObserver));
        this.a.subscribe(withLatestFromObserver);
    }

    static final class WithLatestFromObserver<T, U, R> extends AtomicReference<U> implements b, v<T> {
        private static final long serialVersionUID = -312246233408980075L;
        final c<? super T, ? super U, ? extends R> combiner;
        final v<? super R> downstream;
        final AtomicReference<b> other = new AtomicReference<>();
        final AtomicReference<b> upstream = new AtomicReference<>();

        WithLatestFromObserver(v<? super R> vVar, c<? super T, ? super U, ? extends R> cVar) {
            this.downstream = vVar;
            this.combiner = cVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            U u = get();
            if (u != null) {
                try {
                    this.downstream.onNext(io.reactivex.internal.functions.a.a(this.combiner.apply(t, u), "The combiner returned a null value"));
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    dispose();
                    this.downstream.onError(th);
                }
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.other);
            this.downstream.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            DisposableHelper.dispose(this.other);
            this.downstream.onComplete();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this.other);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
        }

        public boolean setOther(b bVar) {
            return DisposableHelper.setOnce(this.other, bVar);
        }

        public void otherError(Throwable th) {
            DisposableHelper.dispose(this.upstream);
            this.downstream.onError(th);
        }
    }

    final class a implements v<U> {
        private final WithLatestFromObserver<T, U, R> b;

        @Override // io.reactivex.v
        public void onComplete() {
        }

        a(WithLatestFromObserver<T, U, R> withLatestFromObserver) {
            this.b = withLatestFromObserver;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            this.b.setOther(bVar);
        }

        @Override // io.reactivex.v
        public void onNext(U u) {
            this.b.lazySet(u);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.b.otherError(th);
        }
    }
}
