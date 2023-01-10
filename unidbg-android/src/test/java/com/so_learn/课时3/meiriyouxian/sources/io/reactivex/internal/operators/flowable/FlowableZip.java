package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.e.a;
import io.reactivex.g;
import io.reactivex.internal.a.e;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.j;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableZip<T, R> extends g<R> {
    final b<? extends T>[] b;
    final Iterable<? extends b<? extends T>> c;
    final h<? super Object[], ? extends R> d;
    final int e;
    final boolean f;

    @Override // io.reactivex.g
    public void a(c<? super R> cVar) {
        int i;
        b<? extends T>[] bVarArr = this.b;
        if (bVarArr == null) {
            bVarArr = new b[8];
            i = 0;
            for (b<? extends T> bVar : this.c) {
                if (i == bVarArr.length) {
                    b<? extends T>[] bVarArr2 = new b[((i >> 2) + i)];
                    System.arraycopy(bVarArr, 0, bVarArr2, 0, i);
                    bVarArr = bVarArr2;
                }
                bVarArr[i] = bVar;
                i++;
            }
        } else {
            i = bVarArr.length;
        }
        if (i == 0) {
            EmptySubscription.complete(cVar);
            return;
        }
        ZipCoordinator zipCoordinator = new ZipCoordinator(cVar, this.d, i, this.e, this.f);
        cVar.onSubscribe(zipCoordinator);
        zipCoordinator.subscribe(bVarArr, i);
    }

    static final class ZipCoordinator<T, R> extends AtomicInteger implements d {
        private static final long serialVersionUID = -2434867452883857743L;
        volatile boolean cancelled;
        final Object[] current;
        final boolean delayErrors;
        final c<? super R> downstream;
        final AtomicThrowable errors;
        final AtomicLong requested;
        final ZipSubscriber<T, R>[] subscribers;
        final h<? super Object[], ? extends R> zipper;

        ZipCoordinator(c<? super R> cVar, h<? super Object[], ? extends R> hVar, int i, int i2, boolean z) {
            this.downstream = cVar;
            this.zipper = hVar;
            this.delayErrors = z;
            ZipSubscriber<T, R>[] zipSubscriberArr = new ZipSubscriber[i];
            for (int i3 = 0; i3 < i; i3++) {
                zipSubscriberArr[i3] = new ZipSubscriber<>(this, i2);
            }
            this.current = new Object[i];
            this.subscribers = zipSubscriberArr;
            this.requested = new AtomicLong();
            this.errors = new AtomicThrowable();
        }

        /* access modifiers changed from: package-private */
        public void subscribe(b<? extends T>[] bVarArr, int i) {
            c[] cVarArr = this.subscribers;
            for (int i2 = 0; i2 < i && !this.cancelled; i2++) {
                if (this.delayErrors || this.errors.get() == null) {
                    bVarArr[i2].subscribe(cVarArr[i2]);
                } else {
                    return;
                }
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
            }
        }

        /* access modifiers changed from: package-private */
        public void error(ZipSubscriber<T, R> zipSubscriber, Throwable th) {
            if (this.errors.addThrowable(th)) {
                zipSubscriber.done = true;
                drain();
                return;
            }
            a.a(th);
        }

        /* access modifiers changed from: package-private */
        public void cancelAll() {
            for (ZipSubscriber<T, R> zipSubscriber : this.subscribers) {
                zipSubscriber.cancel();
            }
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            int i;
            if (getAndIncrement() == 0) {
                c<? super R> cVar = this.downstream;
                ZipSubscriber<T, R>[] zipSubscriberArr = this.subscribers;
                int length = zipSubscriberArr.length;
                Object[] objArr = this.current;
                int i2 = 1;
                do {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (true) {
                        i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
                        if (i == 0) {
                            break;
                        } else if (this.cancelled) {
                            return;
                        } else {
                            if (this.delayErrors || this.errors.get() == null) {
                                boolean z = false;
                                for (int i3 = 0; i3 < length; i3++) {
                                    ZipSubscriber<T, R> zipSubscriber = zipSubscriberArr[i3];
                                    if (objArr[i3] == null) {
                                        try {
                                            boolean z2 = zipSubscriber.done;
                                            io.reactivex.internal.a.h<T> hVar = zipSubscriber.queue;
                                            Object poll = hVar != null ? hVar.poll() : null;
                                            boolean z3 = poll == null;
                                            if (!z2 || !z3) {
                                                if (!z3) {
                                                    objArr[i3] = poll;
                                                }
                                                z = true;
                                            } else {
                                                cancelAll();
                                                if (this.errors.get() != null) {
                                                    cVar.onError(this.errors.terminate());
                                                    return;
                                                } else {
                                                    cVar.onComplete();
                                                    return;
                                                }
                                            }
                                        } catch (Throwable th) {
                                            io.reactivex.exceptions.a.b(th);
                                            this.errors.addThrowable(th);
                                            if (!this.delayErrors) {
                                                cancelAll();
                                                cVar.onError(this.errors.terminate());
                                                return;
                                            }
                                        }
                                    }
                                }
                                if (z) {
                                    break;
                                }
                                try {
                                    cVar.onNext(io.reactivex.internal.functions.a.a(this.zipper.apply(objArr.clone()), "The zipper returned a null value"));
                                    j2++;
                                    Arrays.fill(objArr, (Object) null);
                                } catch (Throwable th2) {
                                    io.reactivex.exceptions.a.b(th2);
                                    cancelAll();
                                    this.errors.addThrowable(th2);
                                    cVar.onError(this.errors.terminate());
                                    return;
                                }
                            } else {
                                cancelAll();
                                cVar.onError(this.errors.terminate());
                                return;
                            }
                        }
                    }
                    if (i == 0) {
                        if (this.cancelled) {
                            return;
                        }
                        if (this.delayErrors || this.errors.get() == null) {
                            for (int i4 = 0; i4 < length; i4++) {
                                ZipSubscriber<T, R> zipSubscriber2 = zipSubscriberArr[i4];
                                if (objArr[i4] == null) {
                                    try {
                                        boolean z4 = zipSubscriber2.done;
                                        io.reactivex.internal.a.h<T> hVar2 = zipSubscriber2.queue;
                                        Object poll2 = hVar2 != null ? hVar2.poll() : null;
                                        boolean z5 = poll2 == null;
                                        if (z4 && z5) {
                                            cancelAll();
                                            if (this.errors.get() != null) {
                                                cVar.onError(this.errors.terminate());
                                                return;
                                            } else {
                                                cVar.onComplete();
                                                return;
                                            }
                                        } else if (!z5) {
                                            objArr[i4] = poll2;
                                        }
                                    } catch (Throwable th3) {
                                        io.reactivex.exceptions.a.b(th3);
                                        this.errors.addThrowable(th3);
                                        if (!this.delayErrors) {
                                            cancelAll();
                                            cVar.onError(this.errors.terminate());
                                            return;
                                        }
                                    }
                                }
                            }
                        } else {
                            cancelAll();
                            cVar.onError(this.errors.terminate());
                            return;
                        }
                    }
                    if (j2 != 0) {
                        for (ZipSubscriber<T, R> zipSubscriber3 : zipSubscriberArr) {
                            zipSubscriber3.request(j2);
                        }
                        if (j != Long.MAX_VALUE) {
                            this.requested.addAndGet(-j2);
                        }
                    }
                    i2 = addAndGet(-i2);
                } while (i2 != 0);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ZipSubscriber<T, R> extends AtomicReference<d> implements j<T>, d {
        private static final long serialVersionUID = -4627193790118206028L;
        volatile boolean done;
        final int limit;
        final ZipCoordinator<T, R> parent;
        final int prefetch;
        long produced;
        io.reactivex.internal.a.h<T> queue;
        int sourceMode;

        ZipSubscriber(ZipCoordinator<T, R> zipCoordinator, int i) {
            this.parent = zipCoordinator;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.setOnce(this, dVar)) {
                if (dVar instanceof e) {
                    e eVar = (e) dVar;
                    int requestFusion = eVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = eVar;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = eVar;
                        dVar.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                dVar.request((long) this.prefetch);
            }
        }

        public void onNext(T t) {
            if (this.sourceMode != 2) {
                this.queue.offer(t);
            }
            this.parent.drain();
        }

        public void onError(Throwable th) {
            this.parent.error(this, th);
        }

        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        public void request(long j) {
            if (this.sourceMode != 1) {
                long j2 = this.produced + j;
                if (j2 >= ((long) this.limit)) {
                    this.produced = 0;
                    get().request(j2);
                    return;
                }
                this.produced = j2;
            }
        }
    }
}
