package io.reactivex.internal.operators.mixed;

import io.reactivex.ab;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.a;
import io.reactivex.q;
import io.reactivex.t;
import io.reactivex.v;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleFlatMapObservable<T, R> extends q<R> {
    final ab<T> a;
    final h<? super T, ? extends t<? extends R>> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super R> vVar) {
        FlatMapObserver flatMapObserver = new FlatMapObserver(vVar, this.b);
        vVar.onSubscribe(flatMapObserver);
        this.a.a(flatMapObserver);
    }

    static final class FlatMapObserver<T, R> extends AtomicReference<b> implements b, v<R>, z<T> {
        private static final long serialVersionUID = -8948264376121066672L;
        final v<? super R> downstream;
        final h<? super T, ? extends t<? extends R>> mapper;

        FlatMapObserver(v<? super R> vVar, h<? super T, ? extends t<? extends R>> hVar) {
            this.downstream = vVar;
            this.mapper = hVar;
        }

        @Override // io.reactivex.v
        public void onNext(R r) {
            this.downstream.onNext(r);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.replace(this, bVar);
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            try {
                ((t) a.a(this.mapper.apply(t), "The mapper returned a null Publisher")).subscribe(this);
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.downstream.onError(th);
            }
        }
    }
}
