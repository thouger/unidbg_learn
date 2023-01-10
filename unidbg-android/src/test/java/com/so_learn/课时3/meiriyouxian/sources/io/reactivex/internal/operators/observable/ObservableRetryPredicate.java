package io.reactivex.internal.operators.observable;

import io.reactivex.c.k;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableRetryPredicate<T> extends a<T, T> {
    final k<? super Throwable> b;
    final long c;

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        vVar.onSubscribe(sequentialDisposable);
        new RepeatObserver(vVar, this.c, this.b, sequentialDisposable, this.a).subscribeNext();
    }

    static final class RepeatObserver<T> extends AtomicInteger implements v<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final v<? super T> downstream;
        final k<? super Throwable> predicate;
        long remaining;
        final t<? extends T> source;
        final SequentialDisposable upstream;

        RepeatObserver(v<? super T> vVar, long j, k<? super Throwable> kVar, SequentialDisposable sequentialDisposable, t<? extends T> tVar) {
            this.downstream = vVar;
            this.upstream = sequentialDisposable;
            this.source = tVar;
            this.predicate = kVar;
            this.remaining = j;
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
            long j = this.remaining;
            if (j != Long.MAX_VALUE) {
                this.remaining = j - 1;
            }
            if (j == 0) {
                this.downstream.onError(th);
                return;
            }
            try {
                if (!this.predicate.test(th)) {
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
