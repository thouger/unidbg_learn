package io.reactivex.internal.operators.maybe;

import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeFlatten<T, R> extends a<T, R> {
    final h<? super T, ? extends o<? extends R>> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super R> mVar) {
        this.a.a(new FlatMapMaybeObserver(mVar, this.b));
    }

    static final class FlatMapMaybeObserver<T, R> extends AtomicReference<b> implements b, m<T> {
        private static final long serialVersionUID = 4375739915521278546L;
        final m<? super R> downstream;
        final h<? super T, ? extends o<? extends R>> mapper;
        b upstream;

        FlatMapMaybeObserver(m<? super R> mVar, h<? super T, ? extends o<? extends R>> hVar) {
            this.downstream = mVar;
            this.mapper = hVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
            this.upstream.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.m
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            try {
                o oVar = (o) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The mapper returned a null MaybeSource");
                if (!isDisposed()) {
                    oVar.a(new a());
                }
            } catch (Exception e) {
                io.reactivex.exceptions.a.b(e);
                this.downstream.onError(e);
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

        final class a implements m<R> {
            a() {
            }

            @Override // io.reactivex.m
            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(FlatMapMaybeObserver.this, bVar);
            }

            @Override // io.reactivex.m
            public void onSuccess(R r) {
                FlatMapMaybeObserver.this.downstream.onSuccess(r);
            }

            @Override // io.reactivex.m
            public void onError(Throwable th) {
                FlatMapMaybeObserver.this.downstream.onError(th);
            }

            @Override // io.reactivex.m
            public void onComplete() {
                FlatMapMaybeObserver.this.downstream.onComplete();
            }
        }
    }
}
