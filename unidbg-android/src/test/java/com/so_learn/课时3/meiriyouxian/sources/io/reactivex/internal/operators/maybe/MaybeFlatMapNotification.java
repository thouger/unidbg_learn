package io.reactivex.internal.operators.maybe;

import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeFlatMapNotification<T, R> extends a<T, R> {
    final h<? super T, ? extends o<? extends R>> b;
    final h<? super Throwable, ? extends o<? extends R>> c;
    final Callable<? extends o<? extends R>> d;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super R> mVar) {
        this.a.a(new FlatMapMaybeObserver(mVar, this.b, this.c, this.d));
    }

    static final class FlatMapMaybeObserver<T, R> extends AtomicReference<b> implements b, m<T> {
        private static final long serialVersionUID = 4375739915521278546L;
        final m<? super R> downstream;
        final Callable<? extends o<? extends R>> onCompleteSupplier;
        final h<? super Throwable, ? extends o<? extends R>> onErrorMapper;
        final h<? super T, ? extends o<? extends R>> onSuccessMapper;
        b upstream;

        FlatMapMaybeObserver(m<? super R> mVar, h<? super T, ? extends o<? extends R>> hVar, h<? super Throwable, ? extends o<? extends R>> hVar2, Callable<? extends o<? extends R>> callable) {
            this.downstream = mVar;
            this.onSuccessMapper = hVar;
            this.onErrorMapper = hVar2;
            this.onCompleteSupplier = callable;
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
                ((o) io.reactivex.internal.functions.a.a(this.onSuccessMapper.apply(t), "The onSuccessMapper returned a null MaybeSource")).a(new a());
            } catch (Exception e) {
                io.reactivex.exceptions.a.b(e);
                this.downstream.onError(e);
            }
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            try {
                ((o) io.reactivex.internal.functions.a.a(this.onErrorMapper.apply(th), "The onErrorMapper returned a null MaybeSource")).a(new a());
            } catch (Exception e) {
                io.reactivex.exceptions.a.b(e);
                this.downstream.onError(new CompositeException(th, e));
            }
        }

        @Override // io.reactivex.m
        public void onComplete() {
            try {
                ((o) io.reactivex.internal.functions.a.a(this.onCompleteSupplier.call(), "The onCompleteSupplier returned a null MaybeSource")).a(new a());
            } catch (Exception e) {
                io.reactivex.exceptions.a.b(e);
                this.downstream.onError(e);
            }
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
