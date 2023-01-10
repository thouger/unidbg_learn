package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.internal.a.e;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.j;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import org.a.c;
import org.a.d;

public final class FlowableConcatMap<T, R> extends a<T, R> {
    final h<? super T, ? extends org.a.b<? extends R>> c;
    final int d;
    final ErrorMode e;

    interface a<T> {
        void innerComplete();

        void innerError(Throwable th);

        void innerNext(T t);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: io.reactivex.internal.operators.flowable.FlowableConcatMap$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ErrorMode.values().length];

        static {
            try {
                a[ErrorMode.BOUNDARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ErrorMode.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static <T, R> c<T> a(c<? super R> cVar, h<? super T, ? extends org.a.b<? extends R>> hVar, int i, ErrorMode errorMode) {
        int i2 = AnonymousClass1.a[errorMode.ordinal()];
        if (i2 == 1) {
            return new ConcatMapDelayed(cVar, hVar, i, false);
        }
        if (i2 != 2) {
            return new ConcatMapImmediate(cVar, hVar, i);
        }
        return new ConcatMapDelayed(cVar, hVar, i, true);
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super R> cVar) {
        if (!h.a(this.b, cVar, this.c)) {
            this.b.subscribe(a(cVar, this.c, this.d, this.e));
        }
    }

    static abstract class BaseConcatMapSubscriber<T, R> extends AtomicInteger implements a<R>, j<T>, d {
        private static final long serialVersionUID = -3511336836796789179L;
        volatile boolean active;
        volatile boolean cancelled;
        int consumed;
        volatile boolean done;
        final AtomicThrowable errors = new AtomicThrowable();
        final ConcatMapInner<R> inner = new ConcatMapInner<>(this);
        final int limit;
        final h<? super T, ? extends org.a.b<? extends R>> mapper;
        final int prefetch;
        io.reactivex.internal.a.h<T> queue;
        int sourceMode;
        d upstream;

        /* access modifiers changed from: package-private */
        public abstract void drain();

        /* access modifiers changed from: package-private */
        public abstract void subscribeActual();

        BaseConcatMapSubscriber(h<? super T, ? extends org.a.b<? extends R>> hVar, int i) {
            this.mapper = hVar;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        @Override // io.reactivex.j
        public final void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                if (dVar instanceof e) {
                    e eVar = (e) dVar;
                    int requestFusion = eVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = eVar;
                        this.done = true;
                        subscribeActual();
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = eVar;
                        subscribeActual();
                        dVar.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                subscribeActual();
                dVar.request((long) this.prefetch);
            }
        }

        public final void onNext(T t) {
            if (this.sourceMode == 2 || this.queue.offer(t)) {
                drain();
                return;
            }
            this.upstream.cancel();
            onError(new IllegalStateException("Queue full?!"));
        }

        public final void onComplete() {
            this.done = true;
            drain();
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableConcatMap.a
        public final void innerComplete() {
            this.active = false;
            drain();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ConcatMapImmediate<T, R> extends BaseConcatMapSubscriber<T, R> {
        private static final long serialVersionUID = 7898995095634264146L;
        final c<? super R> downstream;
        final AtomicInteger wip = new AtomicInteger();

        ConcatMapImmediate(c<? super R> cVar, h<? super T, ? extends org.a.b<? extends R>> hVar, int i) {
            super(hVar, i);
            this.downstream = cVar;
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableConcatMap.BaseConcatMapSubscriber
        public void subscribeActual() {
            this.downstream.onSubscribe(this);
        }

        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.inner.cancel();
                if (getAndIncrement() == 0) {
                    this.downstream.onError(this.errors.terminate());
                    return;
                }
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableConcatMap.a
        public void innerNext(R r) {
            if (get() == 0 && compareAndSet(0, 1)) {
                this.downstream.onNext(r);
                if (!compareAndSet(1, 0)) {
                    this.downstream.onError(this.errors.terminate());
                }
            }
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableConcatMap.a
        public void innerError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.upstream.cancel();
                if (getAndIncrement() == 0) {
                    this.downstream.onError(this.errors.terminate());
                    return;
                }
                return;
            }
            io.reactivex.e.a.a(th);
        }

        public void request(long j) {
            this.inner.request(j);
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.inner.cancel();
                this.upstream.cancel();
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v11, resolved type: io.reactivex.c.h */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableConcatMap.BaseConcatMapSubscriber
        public void drain() {
            if (this.wip.getAndIncrement() == 0) {
                while (!this.cancelled) {
                    if (!this.active) {
                        boolean z = this.done;
                        try {
                            Object poll = this.queue.poll();
                            boolean z2 = poll == null;
                            if (z && z2) {
                                this.downstream.onComplete();
                                return;
                            } else if (!z2) {
                                try {
                                    Callable callable = (org.a.b) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null Publisher");
                                    if (this.sourceMode != 1) {
                                        int i = this.consumed + 1;
                                        if (i == this.limit) {
                                            this.consumed = 0;
                                            this.upstream.request((long) i);
                                        } else {
                                            this.consumed = i;
                                        }
                                    }
                                    if (callable instanceof Callable) {
                                        try {
                                            Object call = callable.call();
                                            if (call == null) {
                                                continue;
                                            } else if (!this.inner.isUnbounded()) {
                                                this.active = true;
                                                this.inner.setSubscription(new b(call, this.inner));
                                            } else if (get() == 0 && compareAndSet(0, 1)) {
                                                this.downstream.onNext(call);
                                                if (!compareAndSet(1, 0)) {
                                                    this.downstream.onError(this.errors.terminate());
                                                    return;
                                                }
                                            }
                                        } catch (Throwable th) {
                                            io.reactivex.exceptions.a.b(th);
                                            this.upstream.cancel();
                                            this.errors.addThrowable(th);
                                            this.downstream.onError(this.errors.terminate());
                                            return;
                                        }
                                    } else {
                                        this.active = true;
                                        callable.subscribe(this.inner);
                                    }
                                } catch (Throwable th2) {
                                    io.reactivex.exceptions.a.b(th2);
                                    this.upstream.cancel();
                                    this.errors.addThrowable(th2);
                                    this.downstream.onError(this.errors.terminate());
                                    return;
                                }
                            }
                        } catch (Throwable th3) {
                            io.reactivex.exceptions.a.b(th3);
                            this.upstream.cancel();
                            this.errors.addThrowable(th3);
                            this.downstream.onError(this.errors.terminate());
                            return;
                        }
                    }
                    if (this.wip.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class b<T> implements d {
        final c<? super T> a;
        final T b;
        boolean c;

        public void cancel() {
        }

        b(T t, c<? super T> cVar) {
            this.b = t;
            this.a = cVar;
        }

        public void request(long j) {
            if (j > 0 && !this.c) {
                this.c = true;
                c<? super T> cVar = this.a;
                cVar.onNext(this.b);
                cVar.onComplete();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ConcatMapDelayed<T, R> extends BaseConcatMapSubscriber<T, R> {
        private static final long serialVersionUID = -2945777694260521066L;
        final c<? super R> downstream;
        final boolean veryEnd;

        ConcatMapDelayed(c<? super R> cVar, h<? super T, ? extends org.a.b<? extends R>> hVar, int i, boolean z) {
            super(hVar, i);
            this.downstream = cVar;
            this.veryEnd = z;
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableConcatMap.BaseConcatMapSubscriber
        public void subscribeActual() {
            this.downstream.onSubscribe(this);
        }

        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableConcatMap.a
        public void innerNext(R r) {
            this.downstream.onNext(r);
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableConcatMap.a
        public void innerError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (!this.veryEnd) {
                    this.upstream.cancel();
                    this.done = true;
                }
                this.active = false;
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        public void request(long j) {
            this.inner.request(j);
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.inner.cancel();
                this.upstream.cancel();
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v9, resolved type: io.reactivex.c.h */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableConcatMap.BaseConcatMapSubscriber
        public void drain() {
            Object obj;
            if (getAndIncrement() == 0) {
                while (!this.cancelled) {
                    if (!this.active) {
                        boolean z = this.done;
                        if (!z || this.veryEnd || this.errors.get() == null) {
                            try {
                                Object poll = this.queue.poll();
                                boolean z2 = poll == null;
                                if (z && z2) {
                                    Throwable terminate = this.errors.terminate();
                                    if (terminate != null) {
                                        this.downstream.onError(terminate);
                                        return;
                                    } else {
                                        this.downstream.onComplete();
                                        return;
                                    }
                                } else if (!z2) {
                                    try {
                                        Callable callable = (org.a.b) io.reactivex.internal.functions.a.a(this.mapper.apply(poll), "The mapper returned a null Publisher");
                                        if (this.sourceMode != 1) {
                                            int i = this.consumed + 1;
                                            if (i == this.limit) {
                                                this.consumed = 0;
                                                this.upstream.request((long) i);
                                            } else {
                                                this.consumed = i;
                                            }
                                        }
                                        if (callable instanceof Callable) {
                                            try {
                                                obj = callable.call();
                                            } catch (Throwable th) {
                                                io.reactivex.exceptions.a.b(th);
                                                this.errors.addThrowable(th);
                                                if (!this.veryEnd) {
                                                    this.upstream.cancel();
                                                    this.downstream.onError(this.errors.terminate());
                                                    return;
                                                }
                                                obj = null;
                                            }
                                            if (obj == null) {
                                                continue;
                                            } else if (this.inner.isUnbounded()) {
                                                this.downstream.onNext(obj);
                                            } else {
                                                this.active = true;
                                                this.inner.setSubscription(new b(obj, this.inner));
                                            }
                                        } else {
                                            this.active = true;
                                            callable.subscribe(this.inner);
                                        }
                                    } catch (Throwable th2) {
                                        io.reactivex.exceptions.a.b(th2);
                                        this.upstream.cancel();
                                        this.errors.addThrowable(th2);
                                        this.downstream.onError(this.errors.terminate());
                                        return;
                                    }
                                }
                            } catch (Throwable th3) {
                                io.reactivex.exceptions.a.b(th3);
                                this.upstream.cancel();
                                this.errors.addThrowable(th3);
                                this.downstream.onError(this.errors.terminate());
                                return;
                            }
                        } else {
                            this.downstream.onError(this.errors.terminate());
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }

    static final class ConcatMapInner<R> extends SubscriptionArbiter implements j<R> {
        private static final long serialVersionUID = 897683679971470653L;
        final a<R> parent;
        long produced;

        ConcatMapInner(a<R> aVar) {
            super(false);
            this.parent = aVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            setSubscription(dVar);
        }

        public void onNext(R r) {
            this.produced++;
            this.parent.innerNext(r);
        }

        public void onError(Throwable th) {
            long j = this.produced;
            if (j != 0) {
                this.produced = 0;
                produced(j);
            }
            this.parent.innerError(th);
        }

        public void onComplete() {
            long j = this.produced;
            if (j != 0) {
                this.produced = 0;
                produced(j);
            }
            this.parent.innerComplete();
        }
    }
}
