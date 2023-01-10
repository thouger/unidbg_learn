package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.internal.disposables.SequentialDisposable;
import java.util.concurrent.atomic.AtomicInteger;

public final class CompletableConcatArray extends a {
    final e[] a;

    @Override // io.reactivex.a
    public void b(c cVar) {
        ConcatInnerObserver concatInnerObserver = new ConcatInnerObserver(cVar, this.a);
        cVar.onSubscribe(concatInnerObserver.sd);
        concatInnerObserver.next();
    }

    static final class ConcatInnerObserver extends AtomicInteger implements c {
        private static final long serialVersionUID = -7965400327305809232L;
        final c downstream;
        int index;
        final SequentialDisposable sd = new SequentialDisposable();
        final e[] sources;

        ConcatInnerObserver(c cVar, e[] eVarArr) {
            this.downstream = cVar;
            this.sources = eVarArr;
        }

        @Override // io.reactivex.c
        public void onSubscribe(b bVar) {
            this.sd.replace(bVar);
        }

        @Override // io.reactivex.c
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.c
        public void onComplete() {
            next();
        }

        /* access modifiers changed from: package-private */
        public void next() {
            if (!this.sd.isDisposed() && getAndIncrement() == 0) {
                e[] eVarArr = this.sources;
                while (!this.sd.isDisposed()) {
                    int i = this.index;
                    this.index = i + 1;
                    if (i == eVarArr.length) {
                        this.downstream.onComplete();
                        return;
                    }
                    eVarArr[i].a(this);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }
}
