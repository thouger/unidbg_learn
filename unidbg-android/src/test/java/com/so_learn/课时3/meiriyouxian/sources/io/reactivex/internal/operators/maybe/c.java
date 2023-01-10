package io.reactivex.internal.operators.maybe;

import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.m;

/* compiled from: MaybeMap */
public final class c<T, R> extends a<T, R> {
    final h<? super T, ? extends R> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.k
    public void b(m<? super R> mVar) {
        this.a.a(new a(mVar, this.b));
    }

    /* compiled from: MaybeMap */
    static final class a<T, R> implements b, m<T> {
        final m<? super R> a;
        final h<? super T, ? extends R> b;
        b c;

        a(m<? super R> mVar, h<? super T, ? extends R> hVar) {
            this.a = mVar;
            this.b = hVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            b bVar = this.c;
            this.c = DisposableHelper.DISPOSED;
            bVar.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.c.isDisposed();
        }

        @Override // io.reactivex.m
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.c, bVar)) {
                this.c = bVar;
                this.a.onSubscribe(this);
            }
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            try {
                this.a.onSuccess(io.reactivex.internal.functions.a.a(this.b.apply(t), "The mapper returned a null item"));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.a.onError(th);
            }
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.a.onError(th);
        }

        @Override // io.reactivex.m
        public void onComplete() {
            this.a.onComplete();
        }
    }
}
