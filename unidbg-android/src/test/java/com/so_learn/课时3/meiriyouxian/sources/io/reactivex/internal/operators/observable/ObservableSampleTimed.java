package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.c;
import io.reactivex.t;
import io.reactivex.v;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSampleTimed<T> extends a<T, T> {
    final long b;
    final TimeUnit c;
    final w d;
    final boolean e;

    public ObservableSampleTimed(t<T> tVar, long j, TimeUnit timeUnit, w wVar, boolean z) {
        super(tVar);
        this.b = j;
        this.c = timeUnit;
        this.d = wVar;
        this.e = z;
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        c cVar = new c(vVar);
        if (this.e) {
            this.a.subscribe(new SampleTimedEmitLast(cVar, this.b, this.c, this.d));
        } else {
            this.a.subscribe(new SampleTimedNoLast(cVar, this.b, this.c, this.d));
        }
    }

    static abstract class SampleTimedObserver<T> extends AtomicReference<T> implements b, v<T>, Runnable {
        private static final long serialVersionUID = -3517602651313910099L;
        final v<? super T> downstream;
        final long period;
        final w scheduler;
        final AtomicReference<b> timer = new AtomicReference<>();
        final TimeUnit unit;
        b upstream;

        /* access modifiers changed from: package-private */
        public abstract void complete();

        SampleTimedObserver(v<? super T> vVar, long j, TimeUnit timeUnit, w wVar) {
            this.downstream = vVar;
            this.period = j;
            this.unit = timeUnit;
            this.scheduler = wVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
                w wVar = this.scheduler;
                long j = this.period;
                DisposableHelper.replace(this.timer, wVar.a(this, j, j, this.unit));
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            lazySet(t);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            cancelTimer();
            this.downstream.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            cancelTimer();
            complete();
        }

        /* access modifiers changed from: package-private */
        public void cancelTimer() {
            DisposableHelper.dispose(this.timer);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            cancelTimer();
            this.upstream.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        /* access modifiers changed from: package-private */
        public void emit() {
            T andSet = getAndSet(null);
            if (andSet != null) {
                this.downstream.onNext(andSet);
            }
        }
    }

    static final class SampleTimedNoLast<T> extends SampleTimedObserver<T> {
        private static final long serialVersionUID = -7139995637533111443L;

        SampleTimedNoLast(v<? super T> vVar, long j, TimeUnit timeUnit, w wVar) {
            super(vVar, j, timeUnit, wVar);
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.observable.ObservableSampleTimed.SampleTimedObserver
        public void complete() {
            this.downstream.onComplete();
        }

        @Override // java.lang.Runnable
        public void run() {
            emit();
        }
    }

    static final class SampleTimedEmitLast<T> extends SampleTimedObserver<T> {
        private static final long serialVersionUID = -7139995637533111443L;
        final AtomicInteger wip = new AtomicInteger(1);

        SampleTimedEmitLast(v<? super T> vVar, long j, TimeUnit timeUnit, w wVar) {
            super(vVar, j, timeUnit, wVar);
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.observable.ObservableSampleTimed.SampleTimedObserver
        public void complete() {
            emit();
            if (this.wip.decrementAndGet() == 0) {
                this.downstream.onComplete();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.wip.incrementAndGet() == 2) {
                emit();
                if (this.wip.decrementAndGet() == 0) {
                    this.downstream.onComplete();
                }
            }
        }
    }
}
