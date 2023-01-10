package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableResumeNext extends a {
    final e a;
    final h<? super Throwable, ? extends e> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.a
    public void b(c cVar) {
        ResumeNextObserver resumeNextObserver = new ResumeNextObserver(cVar, this.b);
        cVar.onSubscribe(resumeNextObserver);
        this.a.a(resumeNextObserver);
    }

    static final class ResumeNextObserver extends AtomicReference<b> implements c, b {
        private static final long serialVersionUID = 5018523762564524046L;
        final c downstream;
        final h<? super Throwable, ? extends e> errorMapper;
        boolean once;

        ResumeNextObserver(c cVar, h<? super Throwable, ? extends e> hVar) {
            this.downstream = cVar;
            this.errorMapper = hVar;
        }

        @Override // io.reactivex.c
        public void onSubscribe(b bVar) {
            DisposableHelper.replace(this, bVar);
        }

        @Override // io.reactivex.c
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // io.reactivex.c
        public void onError(Throwable th) {
            if (this.once) {
                this.downstream.onError(th);
                return;
            }
            this.once = true;
            try {
                ((e) io.reactivex.internal.functions.a.a(this.errorMapper.apply(th), "The errorMapper returned a null CompletableSource")).a(this);
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                this.downstream.onError(new CompositeException(th, th2));
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
