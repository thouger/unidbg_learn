package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.observers.e;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleResumeNext<T> extends x<T> {
    final ab<? extends T> a;
    final h<? super Throwable, ? extends ab<? extends T>> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.x
    public void b(z<? super T> zVar) {
        this.a.a(new ResumeMainSingleObserver(zVar, this.b));
    }

    static final class ResumeMainSingleObserver<T> extends AtomicReference<b> implements b, z<T> {
        private static final long serialVersionUID = -5314538511045349925L;
        final z<? super T> downstream;
        final h<? super Throwable, ? extends ab<? extends T>> nextFunction;

        ResumeMainSingleObserver(z<? super T> zVar, h<? super Throwable, ? extends ab<? extends T>> hVar) {
            this.downstream = zVar;
            this.nextFunction = hVar;
        }

        @Override // io.reactivex.z
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            try {
                ((ab) a.a(this.nextFunction.apply(th), "The nextFunction returned a null SingleSource.")).a(new e(this, this.downstream));
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                this.downstream.onError(new CompositeException(th, th2));
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }
}
