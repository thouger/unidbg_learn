package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.c;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSampleWithObservable<T> extends a<T, T> {
    final t<?> b;
    final boolean c;

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        c cVar = new c(vVar);
        if (this.c) {
            this.a.subscribe(new SampleMainEmitLast(cVar, this.b));
        } else {
            this.a.subscribe(new SampleMainNoLast(cVar, this.b));
        }
    }

    static abstract class SampleMainObserver<T> extends AtomicReference<T> implements b, v<T> {
        private static final long serialVersionUID = -3517602651313910099L;
        final v<? super T> downstream;
        final AtomicReference<b> other = new AtomicReference<>();
        final t<?> sampler;
        b upstream;

        /* access modifiers changed from: package-private */
        public abstract void completion();

        /* access modifiers changed from: package-private */
        public abstract void run();

        SampleMainObserver(v<? super T> vVar, t<?> tVar) {
            this.downstream = vVar;
            this.sampler = tVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
                if (this.other.get() == null) {
                    this.sampler.subscribe(new a(this));
                }
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            lazySet(t);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.other);
            this.downstream.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            DisposableHelper.dispose(this.other);
            completion();
        }

        /* access modifiers changed from: package-private */
        public boolean setOther(b bVar) {
            return DisposableHelper.setOnce(this.other, bVar);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this.other);
            this.upstream.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.other.get() == DisposableHelper.DISPOSED;
        }

        public void error(Throwable th) {
            this.upstream.dispose();
            this.downstream.onError(th);
        }

        public void complete() {
            this.upstream.dispose();
            completion();
        }

        /* access modifiers changed from: package-private */
        public void emit() {
            T andSet = getAndSet(null);
            if (andSet != null) {
                this.downstream.onNext(andSet);
            }
        }
    }

    static final class a<T> implements v<Object> {
        final SampleMainObserver<T> a;

        a(SampleMainObserver<T> sampleMainObserver) {
            this.a = sampleMainObserver;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            this.a.setOther(bVar);
        }

        @Override // io.reactivex.v
        public void onNext(Object obj) {
            this.a.run();
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.a.error(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.a.complete();
        }
    }

    static final class SampleMainNoLast<T> extends SampleMainObserver<T> {
        private static final long serialVersionUID = -3029755663834015785L;

        SampleMainNoLast(v<? super T> vVar, t<?> tVar) {
            super(vVar, tVar);
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.observable.ObservableSampleWithObservable.SampleMainObserver
        public void completion() {
            this.downstream.onComplete();
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.observable.ObservableSampleWithObservable.SampleMainObserver
        public void run() {
            emit();
        }
    }

    static final class SampleMainEmitLast<T> extends SampleMainObserver<T> {
        private static final long serialVersionUID = -3029755663834015785L;
        volatile boolean done;
        final AtomicInteger wip = new AtomicInteger();

        SampleMainEmitLast(v<? super T> vVar, t<?> tVar) {
            super(vVar, tVar);
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.observable.ObservableSampleWithObservable.SampleMainObserver
        public void completion() {
            this.done = true;
            if (this.wip.getAndIncrement() == 0) {
                emit();
                this.downstream.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.observable.ObservableSampleWithObservable.SampleMainObserver
        public void run() {
            if (this.wip.getAndIncrement() == 0) {
                do {
                    boolean z = this.done;
                    emit();
                    if (z) {
                        this.downstream.onComplete();
                        return;
                    }
                } while (this.wip.decrementAndGet() != 0);
            }
        }
    }
}
