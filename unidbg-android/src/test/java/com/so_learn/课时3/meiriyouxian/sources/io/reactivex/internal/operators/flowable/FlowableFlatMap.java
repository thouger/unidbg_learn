package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.a.e;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.j;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableFlatMap<T, U> extends a<T, U> {
    final h<? super T, ? extends b<? extends U>> c;
    final boolean d;
    final int e;
    final int f;

    public FlowableFlatMap(g<T> gVar, h<? super T, ? extends b<? extends U>> hVar, boolean z, int i, int i2) {
        super(gVar);
        this.c = hVar;
        this.d = z;
        this.e = i;
        this.f = i2;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super U> cVar) {
        if (!h.a(this.b, cVar, this.c)) {
            this.b.a((j) a(cVar, this.c, this.d, this.e, this.f));
        }
    }

    public static <T, U> j<T> a(c<? super U> cVar, h<? super T, ? extends b<? extends U>> hVar, boolean z, int i, int i2) {
        return new MergeSubscriber(cVar, hVar, z, i, i2);
    }

    /* access modifiers changed from: package-private */
    public static final class MergeSubscriber<T, U> extends AtomicInteger implements j<T>, d {
        static final InnerSubscriber<?, ?>[] CANCELLED = new InnerSubscriber[0];
        static final InnerSubscriber<?, ?>[] EMPTY = new InnerSubscriber[0];
        private static final long serialVersionUID = -2117620485640801370L;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final c<? super U> downstream;
        final AtomicThrowable errs = new AtomicThrowable();
        long lastId;
        int lastIndex;
        final h<? super T, ? extends b<? extends U>> mapper;
        final int maxConcurrency;
        volatile io.reactivex.internal.a.g<U> queue;
        final AtomicLong requested = new AtomicLong();
        int scalarEmitted;
        final int scalarLimit;
        final AtomicReference<InnerSubscriber<?, ?>[]> subscribers = new AtomicReference<>();
        long uniqueId;
        d upstream;

        MergeSubscriber(c<? super U> cVar, h<? super T, ? extends b<? extends U>> hVar, boolean z, int i, int i2) {
            this.downstream = cVar;
            this.mapper = hVar;
            this.delayErrors = z;
            this.maxConcurrency = i;
            this.bufferSize = i2;
            this.scalarLimit = Math.max(1, i >> 1);
            this.subscribers.lazySet(EMPTY);
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                if (!this.cancelled) {
                    int i = this.maxConcurrency;
                    if (i == Integer.MAX_VALUE) {
                        dVar.request(Long.MAX_VALUE);
                    } else {
                        dVar.request((long) i);
                    }
                }
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    Callable callable = (b) a.a(this.mapper.apply(t), "The mapper returned a null Publisher");
                    if (callable instanceof Callable) {
                        try {
                            Object call = callable.call();
                            if (call != null) {
                                tryEmitScalar(call);
                            } else if (this.maxConcurrency != Integer.MAX_VALUE && !this.cancelled) {
                                int i = this.scalarEmitted + 1;
                                this.scalarEmitted = i;
                                int i2 = this.scalarLimit;
                                if (i == i2) {
                                    this.scalarEmitted = 0;
                                    this.upstream.request((long) i2);
                                }
                            }
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            this.errs.addThrowable(th);
                            drain();
                        }
                    } else {
                        long j = this.uniqueId;
                        this.uniqueId = 1 + j;
                        InnerSubscriber innerSubscriber = new InnerSubscriber(this, j);
                        if (addInner(innerSubscriber)) {
                            callable.subscribe(innerSubscriber);
                        }
                    }
                } catch (Throwable th2) {
                    io.reactivex.exceptions.a.b(th2);
                    this.upstream.cancel();
                    onError(th2);
                }
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.flowable.FlowableFlatMap$InnerSubscriber<?, ?>[]> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public boolean addInner(InnerSubscriber<T, U> innerSubscriber) {
            InnerSubscriber<?, ?>[] innerSubscriberArr;
            InnerSubscriber[] innerSubscriberArr2;
            do {
                innerSubscriberArr = this.subscribers.get();
                if (innerSubscriberArr == CANCELLED) {
                    innerSubscriber.dispose();
                    return false;
                }
                int length = innerSubscriberArr.length;
                innerSubscriberArr2 = new InnerSubscriber[(length + 1)];
                System.arraycopy(innerSubscriberArr, 0, innerSubscriberArr2, 0, length);
                innerSubscriberArr2[length] = innerSubscriber;
            } while (!this.subscribers.compareAndSet(innerSubscriberArr, innerSubscriberArr2));
            return true;
        }

        /* access modifiers changed from: package-private */
        public void removeInner(InnerSubscriber<T, U> innerSubscriber) {
            InnerSubscriber<?, ?>[] innerSubscriberArr;
            InnerSubscriber<?, ?>[] innerSubscriberArr2;
            do {
                innerSubscriberArr = this.subscribers.get();
                int length = innerSubscriberArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (innerSubscriberArr[i2] == innerSubscriber) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            innerSubscriberArr2 = EMPTY;
                        } else {
                            InnerSubscriber<?, ?>[] innerSubscriberArr3 = new InnerSubscriber[(length - 1)];
                            System.arraycopy(innerSubscriberArr, 0, innerSubscriberArr3, 0, i);
                            System.arraycopy(innerSubscriberArr, i + 1, innerSubscriberArr3, i, (length - i) - 1);
                            innerSubscriberArr2 = innerSubscriberArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(innerSubscriberArr, innerSubscriberArr2));
        }

        /* access modifiers changed from: package-private */
        public io.reactivex.internal.a.h<U> getMainQueue() {
            io.reactivex.internal.a.g<U> gVar = this.queue;
            if (gVar == null) {
                int i = this.maxConcurrency;
                if (i == Integer.MAX_VALUE) {
                    gVar = new io.reactivex.internal.queue.a<>(this.bufferSize);
                } else {
                    gVar = new SpscArrayQueue(i);
                }
                this.queue = gVar;
            }
            return gVar;
        }

        /* access modifiers changed from: package-private */
        public void tryEmitScalar(U u) {
            if (get() == 0 && compareAndSet(0, 1)) {
                long j = this.requested.get();
                io.reactivex.internal.a.h<U> hVar = this.queue;
                if (j == 0 || (hVar != null && !hVar.isEmpty())) {
                    if (hVar == null) {
                        hVar = getMainQueue();
                    }
                    if (!hVar.offer(u)) {
                        onError(new IllegalStateException("Scalar queue full?!"));
                        return;
                    }
                } else {
                    this.downstream.onNext(u);
                    if (j != Long.MAX_VALUE) {
                        this.requested.decrementAndGet();
                    }
                    if (this.maxConcurrency != Integer.MAX_VALUE && !this.cancelled) {
                        int i = this.scalarEmitted + 1;
                        this.scalarEmitted = i;
                        int i2 = this.scalarLimit;
                        if (i == i2) {
                            this.scalarEmitted = 0;
                            this.upstream.request((long) i2);
                        }
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else if (!getMainQueue().offer(u)) {
                onError(new IllegalStateException("Scalar queue full?!"));
                return;
            } else if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        /* access modifiers changed from: package-private */
        public io.reactivex.internal.a.h<U> getInnerQueue(InnerSubscriber<T, U> innerSubscriber) {
            io.reactivex.internal.a.h<U> hVar = innerSubscriber.queue;
            if (hVar != null) {
                return hVar;
            }
            SpscArrayQueue spscArrayQueue = new SpscArrayQueue(this.bufferSize);
            innerSubscriber.queue = spscArrayQueue;
            return spscArrayQueue;
        }

        /* access modifiers changed from: package-private */
        public void tryEmit(U u, InnerSubscriber<T, U> innerSubscriber) {
            if (get() != 0 || !compareAndSet(0, 1)) {
                io.reactivex.internal.a.h hVar = innerSubscriber.queue;
                if (hVar == null) {
                    hVar = new SpscArrayQueue(this.bufferSize);
                    innerSubscriber.queue = hVar;
                }
                if (!hVar.offer(u)) {
                    onError(new MissingBackpressureException("Inner queue full?!"));
                    return;
                } else if (getAndIncrement() != 0) {
                    return;
                }
            } else {
                long j = this.requested.get();
                io.reactivex.internal.a.h<U> hVar2 = innerSubscriber.queue;
                if (j == 0 || (hVar2 != null && !hVar2.isEmpty())) {
                    if (hVar2 == null) {
                        hVar2 = getInnerQueue(innerSubscriber);
                    }
                    if (!hVar2.offer(u)) {
                        onError(new MissingBackpressureException("Inner queue full?!"));
                        return;
                    }
                } else {
                    this.downstream.onNext(u);
                    if (j != Long.MAX_VALUE) {
                        this.requested.decrementAndGet();
                    }
                    innerSubscriber.requestMore(1);
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            drainLoop();
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.e.a.a(th);
            } else if (this.errs.addThrowable(th)) {
                this.done = true;
                drain();
            } else {
                io.reactivex.e.a.a(th);
            }
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            io.reactivex.internal.a.g<U> gVar;
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.cancel();
                disposeAll();
                if (getAndIncrement() == 0 && (gVar = this.queue) != null) {
                    gVar.clear();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* access modifiers changed from: package-private */
        public void drainLoop() {
            boolean z;
            long j;
            long j2;
            boolean z2;
            int i;
            int i2;
            long j3;
            c<? super U> cVar = this.downstream;
            int i3 = 1;
            while (!checkTerminate()) {
                io.reactivex.internal.a.g<U> gVar = this.queue;
                long j4 = this.requested.get();
                boolean z3 = j4 == Long.MAX_VALUE;
                long j5 = 0;
                long j6 = 0;
                if (gVar != null) {
                    while (true) {
                        long j7 = 0;
                        Object obj = null;
                        while (true) {
                            if (j4 == 0) {
                                break;
                            }
                            Object poll = gVar.poll();
                            if (!checkTerminate()) {
                                if (poll == null) {
                                    obj = poll;
                                    break;
                                }
                                cVar.onNext(poll);
                                j6++;
                                j7++;
                                j4--;
                                obj = poll;
                            } else {
                                return;
                            }
                        }
                        if (j7 != 0) {
                            if (z3) {
                                j4 = Long.MAX_VALUE;
                            } else {
                                j4 = this.requested.addAndGet(-j7);
                            }
                        }
                        if (j4 == 0 || obj == null) {
                            break;
                        }
                    }
                }
                boolean z4 = this.done;
                io.reactivex.internal.a.g<U> gVar2 = this.queue;
                InnerSubscriber<?, ?>[] innerSubscriberArr = this.subscribers.get();
                int length = innerSubscriberArr.length;
                if (!z4 || ((gVar2 != null && !gVar2.isEmpty()) || length != 0)) {
                    if (length != 0) {
                        long j8 = this.lastId;
                        int i4 = this.lastIndex;
                        if (length <= i4 || innerSubscriberArr[i4].id != j8) {
                            if (length <= i4) {
                                i4 = 0;
                            }
                            int i5 = i4;
                            for (int i6 = 0; i6 < length && innerSubscriberArr[i5].id != j8; i6++) {
                                i5++;
                                if (i5 == length) {
                                    i5 = 0;
                                }
                            }
                            this.lastIndex = i5;
                            this.lastId = innerSubscriberArr[i5].id;
                            i4 = i5;
                        }
                        int i7 = i4;
                        boolean z5 = false;
                        int i8 = 0;
                        while (true) {
                            if (i8 >= length) {
                                z2 = z5;
                                break;
                            } else if (!checkTerminate()) {
                                InnerSubscriber<?, ?> innerSubscriber = innerSubscriberArr[i7];
                                Object obj2 = null;
                                while (!checkTerminate()) {
                                    io.reactivex.internal.a.h<U> hVar = innerSubscriber.queue;
                                    if (hVar == null) {
                                        i = length;
                                    } else {
                                        i = length;
                                        Object obj3 = obj2;
                                        long j9 = j5;
                                        while (true) {
                                            if (j4 == j5) {
                                                break;
                                            }
                                            try {
                                                Object poll2 = hVar.poll();
                                                if (poll2 == null) {
                                                    obj3 = poll2;
                                                    j5 = 0;
                                                    break;
                                                }
                                                cVar.onNext(poll2);
                                                if (!checkTerminate()) {
                                                    j4--;
                                                    j9++;
                                                    obj3 = poll2;
                                                    j5 = 0;
                                                } else {
                                                    return;
                                                }
                                            } catch (Throwable th) {
                                                io.reactivex.exceptions.a.b(th);
                                                innerSubscriber.dispose();
                                                this.errs.addThrowable(th);
                                                if (!this.delayErrors) {
                                                    this.upstream.cancel();
                                                }
                                                if (!checkTerminate()) {
                                                    removeInner(innerSubscriber);
                                                    i8++;
                                                    z5 = true;
                                                    i2 = 1;
                                                } else {
                                                    return;
                                                }
                                            }
                                        }
                                        if (j9 != j5) {
                                            j4 = !z3 ? this.requested.addAndGet(-j9) : Long.MAX_VALUE;
                                            innerSubscriber.requestMore(j9);
                                            j3 = 0;
                                        } else {
                                            j3 = j5;
                                        }
                                        if (!(j4 == j3 || obj3 == null)) {
                                            length = i;
                                            obj2 = obj3;
                                            j5 = 0;
                                        }
                                    }
                                    boolean z6 = innerSubscriber.done;
                                    io.reactivex.internal.a.h<U> hVar2 = innerSubscriber.queue;
                                    if (z6 && (hVar2 == null || hVar2.isEmpty())) {
                                        removeInner(innerSubscriber);
                                        if (!checkTerminate()) {
                                            j6++;
                                            z5 = true;
                                        } else {
                                            return;
                                        }
                                    }
                                    if (j4 == 0) {
                                        z2 = z5;
                                        break;
                                    }
                                    i7++;
                                    if (i7 == i) {
                                        i7 = 0;
                                    }
                                    i2 = 1;
                                    i8 += i2;
                                    length = i;
                                    j5 = 0;
                                }
                                return;
                            } else {
                                return;
                            }
                        }
                        this.lastIndex = i7;
                        this.lastId = innerSubscriberArr[i7].id;
                        z = z2;
                        j2 = j6;
                        j = 0;
                    } else {
                        j = 0;
                        j2 = j6;
                        z = false;
                    }
                    if (j2 != j && !this.cancelled) {
                        this.upstream.request(j2);
                    }
                    if (z) {
                        i3 = i3;
                    } else {
                        i3 = addAndGet(-i3);
                        if (i3 == 0) {
                            return;
                        }
                    }
                } else {
                    Throwable terminate = this.errs.terminate();
                    if (terminate == ExceptionHelper.a) {
                        return;
                    }
                    if (terminate == null) {
                        cVar.onComplete();
                        return;
                    } else {
                        cVar.onError(terminate);
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminate() {
            if (this.cancelled) {
                clearScalarQueue();
                return true;
            } else if (this.delayErrors || this.errs.get() == null) {
                return false;
            } else {
                clearScalarQueue();
                Throwable terminate = this.errs.terminate();
                if (terminate != ExceptionHelper.a) {
                    this.downstream.onError(terminate);
                }
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public void clearScalarQueue() {
            io.reactivex.internal.a.g<U> gVar = this.queue;
            if (gVar != null) {
                gVar.clear();
            }
        }

        /* access modifiers changed from: package-private */
        public void disposeAll() {
            InnerSubscriber<?, ?>[] andSet;
            InnerSubscriber<?, ?>[] innerSubscriberArr = this.subscribers.get();
            InnerSubscriber<?, ?>[] innerSubscriberArr2 = CANCELLED;
            if (!(innerSubscriberArr == innerSubscriberArr2 || (andSet = this.subscribers.getAndSet(innerSubscriberArr2)) == CANCELLED)) {
                for (InnerSubscriber<?, ?> innerSubscriber : andSet) {
                    innerSubscriber.dispose();
                }
                Throwable terminate = this.errs.terminate();
                if (!(terminate == null || terminate == ExceptionHelper.a)) {
                    io.reactivex.e.a.a(terminate);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void innerError(InnerSubscriber<T, U> innerSubscriber, Throwable th) {
            if (this.errs.addThrowable(th)) {
                innerSubscriber.done = true;
                if (!this.delayErrors) {
                    this.upstream.cancel();
                    for (InnerSubscriber<?, ?> innerSubscriber2 : this.subscribers.getAndSet(CANCELLED)) {
                        innerSubscriber2.dispose();
                    }
                }
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class InnerSubscriber<T, U> extends AtomicReference<d> implements io.reactivex.disposables.b, j<U> {
        private static final long serialVersionUID = -4606175640614850599L;
        final int bufferSize;
        volatile boolean done;
        int fusionMode;
        final long id;
        final int limit = (this.bufferSize >> 2);
        final MergeSubscriber<T, U> parent;
        long produced;
        volatile io.reactivex.internal.a.h<U> queue;

        InnerSubscriber(MergeSubscriber<T, U> mergeSubscriber, long j) {
            this.id = j;
            this.parent = mergeSubscriber;
            this.bufferSize = mergeSubscriber.bufferSize;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.setOnce(this, dVar)) {
                if (dVar instanceof e) {
                    e eVar = (e) dVar;
                    int requestFusion = eVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.fusionMode = requestFusion;
                        this.queue = eVar;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.fusionMode = requestFusion;
                        this.queue = eVar;
                    }
                }
                dVar.request((long) this.bufferSize);
            }
        }

        public void onNext(U u) {
            if (this.fusionMode != 2) {
                this.parent.tryEmit(u, this);
            } else {
                this.parent.drain();
            }
        }

        public void onError(Throwable th) {
            lazySet(SubscriptionHelper.CANCELLED);
            this.parent.innerError(this, th);
        }

        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        /* access modifiers changed from: package-private */
        public void requestMore(long j) {
            if (this.fusionMode != 1) {
                long j2 = this.produced + j;
                if (j2 >= ((long) this.limit)) {
                    this.produced = 0;
                    get().request(j2);
                    return;
                }
                this.produced = j2;
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
