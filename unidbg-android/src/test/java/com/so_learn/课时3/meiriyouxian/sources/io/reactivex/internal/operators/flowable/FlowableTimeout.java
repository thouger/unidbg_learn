package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.operators.flowable.FlowableTimeoutTimed;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableTimeout<T, U, V> extends a<T, T> {
    final b<U> c;
    final h<? super T, ? extends b<V>> d;
    final b<? extends T> e;

    /* access modifiers changed from: package-private */
    public interface a extends FlowableTimeoutTimed.b {
        void onTimeoutError(long j, Throwable th);
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        b<? extends T> bVar = this.e;
        if (bVar == null) {
            TimeoutSubscriber timeoutSubscriber = new TimeoutSubscriber(cVar, this.d);
            cVar.onSubscribe(timeoutSubscriber);
            timeoutSubscriber.startFirstTimeout(this.c);
            this.b.a((j) timeoutSubscriber);
            return;
        }
        TimeoutFallbackSubscriber timeoutFallbackSubscriber = new TimeoutFallbackSubscriber(cVar, this.d, bVar);
        cVar.onSubscribe(timeoutFallbackSubscriber);
        timeoutFallbackSubscriber.startFirstTimeout(this.c);
        this.b.a((j) timeoutFallbackSubscriber);
    }

    static final class TimeoutSubscriber<T> extends AtomicLong implements a, j<T>, d {
        private static final long serialVersionUID = 3764492702657003550L;
        final c<? super T> downstream;
        final h<? super T, ? extends b<?>> itemTimeoutIndicator;
        final AtomicLong requested = new AtomicLong();
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicReference<d> upstream = new AtomicReference<>();

        TimeoutSubscriber(c<? super T> cVar, h<? super T, ? extends b<?>> hVar) {
            this.downstream = cVar;
            this.itemTimeoutIndicator = hVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, dVar);
        }

        public void onNext(T t) {
            long j = get();
            if (j != Long.MAX_VALUE) {
                long j2 = 1 + j;
                if (compareAndSet(j, j2)) {
                    io.reactivex.disposables.b bVar = this.task.get();
                    if (bVar != null) {
                        bVar.dispose();
                    }
                    this.downstream.onNext(t);
                    try {
                        b bVar2 = (b) io.reactivex.internal.functions.a.a(this.itemTimeoutIndicator.apply(t), "The itemTimeoutIndicator returned a null Publisher.");
                        TimeoutConsumer timeoutConsumer = new TimeoutConsumer(j2, this);
                        if (this.task.replace(timeoutConsumer)) {
                            bVar2.subscribe(timeoutConsumer);
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.upstream.get().cancel();
                        getAndSet(Long.MAX_VALUE);
                        this.downstream.onError(th);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void startFirstTimeout(b<?> bVar) {
            if (bVar != null) {
                TimeoutConsumer timeoutConsumer = new TimeoutConsumer(0, this);
                if (this.task.replace(timeoutConsumer)) {
                    bVar.subscribe(timeoutConsumer);
                }
            }
        }

        public void onError(Throwable th) {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onError(th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        public void onComplete() {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onComplete();
            }
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableTimeoutTimed.b
        public void onTimeout(long j) {
            if (compareAndSet(j, Long.MAX_VALUE)) {
                SubscriptionHelper.cancel(this.upstream);
                this.downstream.onError(new TimeoutException());
            }
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableTimeout.a
        public void onTimeoutError(long j, Throwable th) {
            if (compareAndSet(j, Long.MAX_VALUE)) {
                SubscriptionHelper.cancel(this.upstream);
                this.downstream.onError(th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.upstream, this.requested, j);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.upstream);
            this.task.dispose();
        }
    }

    static final class TimeoutFallbackSubscriber<T> extends SubscriptionArbiter implements a, j<T> {
        private static final long serialVersionUID = 3764492702657003550L;
        long consumed;
        final c<? super T> downstream;
        b<? extends T> fallback;
        final AtomicLong index;
        final h<? super T, ? extends b<?>> itemTimeoutIndicator;
        final SequentialDisposable task = new SequentialDisposable();
        final AtomicReference<d> upstream = new AtomicReference<>();

        TimeoutFallbackSubscriber(c<? super T> cVar, h<? super T, ? extends b<?>> hVar, b<? extends T> bVar) {
            super(true);
            this.downstream = cVar;
            this.itemTimeoutIndicator = hVar;
            this.fallback = bVar;
            this.index = new AtomicLong();
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.setOnce(this.upstream, dVar)) {
                setSubscription(dVar);
            }
        }

        public void onNext(T t) {
            long j = this.index.get();
            if (j != Long.MAX_VALUE) {
                long j2 = j + 1;
                if (this.index.compareAndSet(j, j2)) {
                    io.reactivex.disposables.b bVar = this.task.get();
                    if (bVar != null) {
                        bVar.dispose();
                    }
                    this.consumed++;
                    this.downstream.onNext(t);
                    try {
                        b bVar2 = (b) io.reactivex.internal.functions.a.a(this.itemTimeoutIndicator.apply(t), "The itemTimeoutIndicator returned a null Publisher.");
                        TimeoutConsumer timeoutConsumer = new TimeoutConsumer(j2, this);
                        if (this.task.replace(timeoutConsumer)) {
                            bVar2.subscribe(timeoutConsumer);
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.upstream.get().cancel();
                        this.index.getAndSet(Long.MAX_VALUE);
                        this.downstream.onError(th);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void startFirstTimeout(b<?> bVar) {
            if (bVar != null) {
                TimeoutConsumer timeoutConsumer = new TimeoutConsumer(0, this);
                if (this.task.replace(timeoutConsumer)) {
                    bVar.subscribe(timeoutConsumer);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onError(th);
                this.task.dispose();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        public void onComplete() {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onComplete();
                this.task.dispose();
            }
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableTimeoutTimed.b
        public void onTimeout(long j) {
            if (this.index.compareAndSet(j, Long.MAX_VALUE)) {
                SubscriptionHelper.cancel(this.upstream);
                b<? extends T> bVar = this.fallback;
                this.fallback = null;
                long j2 = this.consumed;
                if (j2 != 0) {
                    produced(j2);
                }
                bVar.subscribe(new FlowableTimeoutTimed.a(this.downstream, this));
            }
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableTimeout.a
        public void onTimeoutError(long j, Throwable th) {
            if (this.index.compareAndSet(j, Long.MAX_VALUE)) {
                SubscriptionHelper.cancel(this.upstream);
                this.downstream.onError(th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.internal.subscriptions.SubscriptionArbiter
        public void cancel() {
            super.cancel();
            this.task.dispose();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class TimeoutConsumer extends AtomicReference<d> implements io.reactivex.disposables.b, j<Object> {
        private static final long serialVersionUID = 8708641127342403073L;
        final long idx;
        final a parent;

        TimeoutConsumer(long j, a aVar) {
            this.idx = j;
            this.parent = aVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.setOnce(this, dVar, Long.MAX_VALUE);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: io.reactivex.internal.operators.flowable.FlowableTimeout$TimeoutConsumer */
        /* JADX WARN: Multi-variable type inference failed */
        public void onNext(Object obj) {
            SubscriptionHelper subscriptionHelper = (d) get();
            if (subscriptionHelper != SubscriptionHelper.CANCELLED) {
                subscriptionHelper.cancel();
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.onTimeout(this.idx);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: io.reactivex.internal.operators.flowable.FlowableTimeout$TimeoutConsumer */
        /* JADX WARN: Multi-variable type inference failed */
        public void onError(Throwable th) {
            if (get() != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.onTimeoutError(this.idx, th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: io.reactivex.internal.operators.flowable.FlowableTimeout$TimeoutConsumer */
        /* JADX WARN: Multi-variable type inference failed */
        public void onComplete() {
            if (get() != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.onTimeout(this.idx);
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == SubscriptionHelper.CANCELLED;
        }
    }
}
