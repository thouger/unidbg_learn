package io.reactivex.internal.operators.maybe;

import io.reactivex.c.c;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.a;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeFlatMapBiSelector<T, U, R> extends a<T, R> {
    final h<? super T, ? extends o<? extends U>> b;
    final c<? super T, ? super U, ? extends R> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super R> mVar) {
        this.a.a(new FlatMapBiMainObserver(mVar, this.b, this.c));
    }

    static final class FlatMapBiMainObserver<T, U, R> implements b, m<T> {
        final h<? super T, ? extends o<? extends U>> a;
        final InnerObserver<T, U, R> b;

        FlatMapBiMainObserver(m<? super R> mVar, h<? super T, ? extends o<? extends U>> hVar, c<? super T, ? super U, ? extends R> cVar) {
            this.b = new InnerObserver<>(mVar, cVar);
            this.a = hVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this.b);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.b.get());
        }

        @Override // io.reactivex.m
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this.b, bVar)) {
                this.b.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            try {
                o oVar = (o) a.a(this.a.apply(t), "The mapper returned a null MaybeSource");
                if (DisposableHelper.replace(this.b, null)) {
                    InnerObserver<T, U, R> innerObserver = this.b;
                    innerObserver.value = t;
                    oVar.a(innerObserver);
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.b.downstream.onError(th);
            }
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.b.downstream.onError(th);
        }

        @Override // io.reactivex.m
        public void onComplete() {
            this.b.downstream.onComplete();
        }

        static final class InnerObserver<T, U, R> extends AtomicReference<b> implements m<U> {
            private static final long serialVersionUID = -2897979525538174559L;
            final m<? super R> downstream;
            final c<? super T, ? super U, ? extends R> resultSelector;
            T value;

            InnerObserver(m<? super R> mVar, c<? super T, ? super U, ? extends R> cVar) {
                this.downstream = mVar;
                this.resultSelector = cVar;
            }

            @Override // io.reactivex.m
            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            @Override // io.reactivex.m
            public void onSuccess(U u) {
                T t = this.value;
                this.value = null;
                try {
                    this.downstream.onSuccess(a.a(this.resultSelector.apply(t, u), "The resultSelector returned a null value"));
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.downstream.onError(th);
                }
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
}
