package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.j;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.d;

public final class FlowableTimeoutTimed<T> extends a<T, T> {
    final long c;
    final TimeUnit d;
    final w e;
    final org.a.b<? extends T> f;

    /* access modifiers changed from: package-private */
    public interface b {
        void onTimeout(long j);
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(org.a.c<? super T> cVar) {
        if (this.f == null) {
            TimeoutSubscriber timeoutSubscriber = new TimeoutSubscriber(cVar, this.c, this.d, this.e.a());
            cVar.onSubscribe(timeoutSubscriber);
            timeoutSubscriber.startTimeout(0);
            this.b.a((j) timeoutSubscriber);
            return;
        }
        TimeoutFallbackSubscriber timeoutFallbackSubscriber = new TimeoutFallbackSubscriber(cVar, this.c, this.d, this.e.a(), this.f);
        cVar.onSubscribe(timeoutFallbackSubscriber);
        timeoutFallbackSubscriber.startTimeout(0);
        this.b.a((j) timeoutFallbackSubscriber);
    }

    static final class TimeoutSubscriber<T> extends AtomicLong implements b, j<T>, d {
        private static final long serialVersionUID = 3764492702657003550L;
        final org.a.c<? super T> downstream;
        final AtomicLong requested = new AtomicLong();
        final SequentialDisposable task = new SequentialDisposable();
        final long timeout;
        final TimeUnit unit;
        final AtomicReference<d> upstream = new AtomicReference<>();
        final w.c worker;

        TimeoutSubscriber(org.a.c<? super T> cVar, long j, TimeUnit timeUnit, w.c cVar2) {
            this.downstream = cVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar2;
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
                    this.task.get().dispose();
                    this.downstream.onNext(t);
                    startTimeout(j2);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void startTimeout(long j) {
            this.task.replace(this.worker.a(new c(j, this), this.timeout, this.unit));
        }

        public void onError(Throwable th) {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onError(th);
                this.worker.dispose();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        public void onComplete() {
            if (getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onComplete();
                this.worker.dispose();
            }
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableTimeoutTimed.b
        public void onTimeout(long j) {
            if (compareAndSet(j, Long.MAX_VALUE)) {
                SubscriptionHelper.cancel(this.upstream);
                this.downstream.onError(new TimeoutException(ExceptionHelper.a(this.timeout, this.unit)));
                this.worker.dispose();
            }
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.upstream, this.requested, j);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.upstream);
            this.worker.dispose();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class c implements Runnable {
        final b a;
        final long b;

        c(long j, b bVar) {
            this.b = j;
            this.a = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.onTimeout(this.b);
        }
    }

    static final class TimeoutFallbackSubscriber<T> extends SubscriptionArbiter implements b, j<T> {
        private static final long serialVersionUID = 3764492702657003550L;
        long consumed;
        final org.a.c<? super T> downstream;
        org.a.b<? extends T> fallback;
        final AtomicLong index = new AtomicLong();
        final SequentialDisposable task = new SequentialDisposable();
        final long timeout;
        final TimeUnit unit;
        final AtomicReference<d> upstream = new AtomicReference<>();
        final w.c worker;

        TimeoutFallbackSubscriber(org.a.c<? super T> cVar, long j, TimeUnit timeUnit, w.c cVar2, org.a.b<? extends T> bVar) {
            super(true);
            this.downstream = cVar;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = cVar2;
            this.fallback = bVar;
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
                    this.task.get().dispose();
                    this.consumed++;
                    this.downstream.onNext(t);
                    startTimeout(j2);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void startTimeout(long j) {
            this.task.replace(this.worker.a(new c(j, this), this.timeout, this.unit));
        }

        public void onError(Throwable th) {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onError(th);
                this.worker.dispose();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        public void onComplete() {
            if (this.index.getAndSet(Long.MAX_VALUE) != Long.MAX_VALUE) {
                this.task.dispose();
                this.downstream.onComplete();
                this.worker.dispose();
            }
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableTimeoutTimed.b
        public void onTimeout(long j) {
            if (this.index.compareAndSet(j, Long.MAX_VALUE)) {
                SubscriptionHelper.cancel(this.upstream);
                long j2 = this.consumed;
                if (j2 != 0) {
                    produced(j2);
                }
                org.a.b<? extends T> bVar = this.fallback;
                this.fallback = null;
                bVar.subscribe(new a(this.downstream, this));
                this.worker.dispose();
            }
        }

        @Override // io.reactivex.internal.subscriptions.SubscriptionArbiter
        public void cancel() {
            super.cancel();
            this.worker.dispose();
        }
    }

    static final class a<T> implements j<T> {
        final org.a.c<? super T> a;
        final SubscriptionArbiter b;

        a(org.a.c<? super T> cVar, SubscriptionArbiter subscriptionArbiter) {
            this.a = cVar;
            this.b = subscriptionArbiter;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            this.b.setSubscription(dVar);
        }

        public void onNext(T t) {
            this.a.onNext(t);
        }

        public void onError(Throwable th) {
            this.a.onError(th);
        }

        public void onComplete() {
            this.a.onComplete();
        }
    }
}
