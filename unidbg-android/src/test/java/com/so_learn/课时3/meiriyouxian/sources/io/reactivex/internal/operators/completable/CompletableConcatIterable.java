package io.reactivex.internal.operators.completable;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class CompletableConcatIterable extends a {
    final Iterable<? extends e> a;

    @Override // io.reactivex.a
    public void b(c cVar) {
        try {
            ConcatInnerObserver concatInnerObserver = new ConcatInnerObserver(cVar, (Iterator) io.reactivex.internal.functions.a.a(this.a.iterator(), "The iterator returned is null"));
            cVar.onSubscribe(concatInnerObserver.sd);
            concatInnerObserver.next();
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, cVar);
        }
    }

    static final class ConcatInnerObserver extends AtomicInteger implements c {
        private static final long serialVersionUID = -7965400327305809232L;
        final c downstream;
        final SequentialDisposable sd = new SequentialDisposable();
        final Iterator<? extends e> sources;

        ConcatInnerObserver(c cVar, Iterator<? extends e> it2) {
            this.downstream = cVar;
            this.sources = it2;
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
                Iterator<? extends e> it2 = this.sources;
                while (!this.sd.isDisposed()) {
                    try {
                        if (!it2.hasNext()) {
                            this.downstream.onComplete();
                            return;
                        }
                        try {
                            ((e) io.reactivex.internal.functions.a.a(it2.next(), "The CompletableSource returned is null")).a(this);
                            if (decrementAndGet() == 0) {
                                return;
                            }
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            this.downstream.onError(th);
                            return;
                        }
                    } catch (Throwable th2) {
                        io.reactivex.exceptions.a.b(th2);
                        this.downstream.onError(th2);
                        return;
                    }
                }
            }
        }
    }
}
