package io.reactivex.internal.operators.observable;

import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.operators.observable.ObservableTimeoutTimed;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableTimeout<T, U, V> extends a<T, T> {
    final t<U> b;
    final h<? super T, ? extends t<V>> c;
    final t<? extends T> d;

    /* access modifiers changed from: package-private */
    public interface a extends ObservableTimeoutTimed.b {
        void onTimeoutError(long j, Throwable th);
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        t<? extends T> tVar = this.d;
        if (tVar == null) {
            TimeoutObserver timeoutObserver = new TimeoutObserver(vVar, this.c);
            vVar.onSubscribe(timeoutObserver);
            timeoutObserver.startFirstTimeout(this.b);
            this.a.subscribe(timeoutObserver);
            return;
        }
        TimeoutFallbackObserver timeoutFallbackObserver = new TimeoutFallbackObserver(vVar, this.c, tVar);
        vVar.onSubscribe(timeoutFallbackObserver);
        timeoutFallbackObserver.startFirstTimeout(this.b);
        this.a.subscribe(timeoutFallbackObserver);
    }

    static final class TimeoutObserver<T> extends AtomicLong implements b, a, v<T> {
        private static final long serialVersionUID = 3764492702657003550L;
        final v<? super T> downstream;
        final h<? super T, ? extends t<?>> itemTimeoutIndicator;
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicReference<b> upstream = new AtomicReference<>();

        TimeoutObserver(v<? super T> vVar, h<? super T, ? extends t<?>> hVar) {
            this.downstream = vVar;
            this.itemTimeoutIndicator = hVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            long j = get();
            if (j != Long.MAX_VALUE) {
                long j2 = 1 + j;
                if (compareAndSet(j, j2)) {
                    b bVar = this.task.get();
                    if (bVar != null) {
                        bVar.dispose();
                    }
                    this.downstream.onNext(t);
                    try {
                        t tVar = (t) io.reactivex.internal.functions.a.a(this.itemTimeoutIndicator.apply(t), "The itemTimeoutIndicator returned a null ObservableSource.");
                        TimeoutConsumer timeoutConsumer = new TimeoutConsumer(j2, this);
                        if (this.task.replace(timeoutConsumer)) {
                            tVar.subscribe(timeoutConsumer);
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.upstream.get().dispose();
                        getAndSet(Long.MAX_VALUE);
                        this.downstream.onError(th);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void startFirstTimeout(t<?> tVar) {
            if (tVar != null) {
                TimeoutConsumer timeoutConsumer = new TimeoutConsumer(0, this);
                if (this.task.replace(timeoutConsumer)) {
                    tVar.subscribe(timeoutConsumer);
                }
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onError(th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onComplete();
            }
        }

        @Override // io.reactivex.internal.operators.observable.ObservableTimeoutTimed.b
        public void onTimeout(long j) {
            if (compareAndSet(j, Long.MAX_VALUE)) {
                DisposableHelper.dispose(this.upstream);
                this.downstream.onError(new TimeoutException());
            }
        }

        @Override // io.reactivex.internal.operators.observable.ObservableTimeout.a
        public void onTimeoutError(long j, Throwable th) {
            if (compareAndSet(j, Long.MAX_VALUE)) {
                DisposableHelper.dispose(this.upstream);
                this.downstream.onError(th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            this.task.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
        }
    }

    static final class TimeoutFallbackObserver<T> extends AtomicReference<b> implements b, a, v<T> {
        private static final long serialVersionUID = -7508389464265974549L;
        final v<? super T> downstream;
        t<? extends T> fallback;
        final AtomicLong index;
        final h<? super T, ? extends t<?>> itemTimeoutIndicator;
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicReference<b> upstream;

        TimeoutFallbackObserver(v<? super T> vVar, h<? super T, ? extends t<?>> hVar, t<? extends T> tVar) {
            this.downstream = vVar;
            this.itemTimeoutIndicator = hVar;
            this.fallback = tVar;
            this.index = new AtomicLong();
            this.upstream = new AtomicReference<>();
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.upstream, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            long j = this.index.get();
            if (j != Long.MAX_VALUE) {
                long j2 = 1 + j;
                if (this.index.compareAndSet(j, j2)) {
                    b bVar = this.task.get();
                    if (bVar != null) {
                        bVar.dispose();
                    }
                    this.downstream.onNext(t);
                    try {
                        t tVar = (t) io.reactivex.internal.functions.a.a(this.itemTimeoutIndicator.apply(t), "The itemTimeoutIndicator returned a null ObservableSource.");
                        TimeoutConsumer timeoutConsumer = new TimeoutConsumer(j2, this);
                        if (this.task.replace(timeoutConsumer)) {
                            tVar.subscribe(timeoutConsumer);
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.upstream.get().dispose();
                        this.index.getAndSet(Long.MAX_VALUE);
                        this.downstream.onError(th);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void startFirstTimeout(t<?> tVar) {
            if (tVar != null) {
                TimeoutConsumer timeoutConsumer = new TimeoutConsumer(0, this);
                if (this.task.replace(timeoutConsumer)) {
                    tVar.subscribe(timeoutConsumer);
                }
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onError(th);
                this.task.dispose();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onComplete();
                this.task.dispose();
            }
        }

        @Override // io.reactivex.internal.operators.observable.ObservableTimeoutTimed.b
        public void onTimeout(long j) {
            if (this.index.compareAndSet(j, Long.MAX_VALUE)) {
                DisposableHelper.dispose(this.upstream);
                t<? extends T> tVar = this.fallback;
                this.fallback = null;
                tVar.subscribe(new ObservableTimeoutTimed.a(this.downstream, this));
            }
        }

        @Override // io.reactivex.internal.operators.observable.ObservableTimeout.a
        public void onTimeoutError(long j, Throwable th) {
            if (this.index.compareAndSet(j, Long.MAX_VALUE)) {
                DisposableHelper.dispose(this);
                this.downstream.onError(th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this);
            this.task.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }

    /* access modifiers changed from: package-private */
    public static final class TimeoutConsumer extends AtomicReference<b> implements b, v<Object> {
        private static final long serialVersionUID = 8708641127342403073L;
        final long idx;
        final a parent;

        TimeoutConsumer(long j, a aVar) {
            this.idx = j;
            this.parent = aVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: io.reactivex.internal.operators.observable.ObservableTimeout$TimeoutConsumer */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.v
        public void onNext(Object obj) {
            b bVar = (b) get();
            if (bVar != DisposableHelper.DISPOSED) {
                bVar.dispose();
                lazySet(DisposableHelper.DISPOSED);
                this.parent.onTimeout(this.idx);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: io.reactivex.internal.operators.observable.ObservableTimeout$TimeoutConsumer */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (get() != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.onTimeoutError(this.idx, th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: io.reactivex.internal.operators.observable.ObservableTimeout$TimeoutConsumer */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.v
        public void onComplete() {
            if (get() != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.onTimeout(this.idx);
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
