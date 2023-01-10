package io.reactivex.internal.operators.observable;

import io.reactivex.c.d;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableRetryBiPredicate<T> extends a<T, T> {
    final d<? super Integer, ? super Throwable> b;

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        vVar.onSubscribe(sequentialDisposable);
        new RetryBiObserver(vVar, this.b, sequentialDisposable, this.a).subscribeNext();
    }

    static final class RetryBiObserver<T> extends AtomicInteger implements v<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final v<? super T> downstream;
        final d<? super Integer, ? super Throwable> predicate;
        int retries;
        final t<? extends T> source;
        final SequentialDisposable upstream;

        RetryBiObserver(v<? super T> vVar, d<? super Integer, ? super Throwable> dVar, SequentialDisposable sequentialDisposable, t<? extends T> tVar) {
            this.downstream = vVar;
            this.upstream = sequentialDisposable;
            this.source = tVar;
            this.predicate = dVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            this.upstream.replace(bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            try {
                d<? super Integer, ? super Throwable> dVar = this.predicate;
                int i = this.retries + 1;
                this.retries = i;
                if (!dVar.a(Integer.valueOf(i), th)) {
                    this.downstream.onError(th);
                } else {
                    subscribeNext();
                }
            } catch (Throwable th2) {
                a.b(th2);
                this.downstream.onError(new CompositeException(th, th2));
            }
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.downstream.onComplete();
        }

        /* access modifiers changed from: package-private */
        public void subscribeNext() {
            if (getAndIncrement() == 0) {
                int i = 1;
                while (!this.upstream.isDisposed()) {
                    this.source.subscribe(this);
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
    }
}
