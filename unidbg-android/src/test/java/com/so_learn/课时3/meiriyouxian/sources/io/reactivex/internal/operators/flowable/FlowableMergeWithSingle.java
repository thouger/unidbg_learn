package io.reactivex.internal.operators.flowable;

import io.reactivex.ab;
import io.reactivex.e.a;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableMergeWithSingle<T> extends a<T, T> {
    final ab<? extends T> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        MergeWithObserver mergeWithObserver = new MergeWithObserver(cVar);
        cVar.onSubscribe(mergeWithObserver);
        this.b.a((j) mergeWithObserver);
        this.c.a(mergeWithObserver.otherObserver);
    }

    static final class MergeWithObserver<T> extends AtomicInteger implements j<T>, d {
        static final int OTHER_STATE_CONSUMED_OR_EMPTY = 2;
        static final int OTHER_STATE_HAS_VALUE = 1;
        private static final long serialVersionUID = -4592979584110982903L;
        volatile boolean cancelled;
        int consumed;
        final c<? super T> downstream;
        long emitted;
        final AtomicThrowable error = new AtomicThrowable();
        final int limit;
        volatile boolean mainDone;
        final AtomicReference<d> mainSubscription = new AtomicReference<>();
        final OtherObserver<T> otherObserver = new OtherObserver<>(this);
        volatile int otherState;
        final int prefetch = g.a();
        volatile io.reactivex.internal.a.g<T> queue;
        final AtomicLong requested = new AtomicLong();
        T singleItem;

        MergeWithObserver(c<? super T> cVar) {
            this.downstream = cVar;
            int i = this.prefetch;
            this.limit = i - (i >> 2);
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.setOnce(this.mainSubscription, dVar, (long) this.prefetch);
        }

        public void onNext(T t) {
            if (compareAndSet(0, 1)) {
                long j = this.emitted;
                if (this.requested.get() != j) {
                    io.reactivex.internal.a.g<T> gVar = this.queue;
                    if (gVar == null || gVar.isEmpty()) {
                        this.emitted = j + 1;
                        this.downstream.onNext(t);
                        int i = this.consumed + 1;
                        if (i == this.limit) {
                            this.consumed = 0;
                            this.mainSubscription.get().request((long) i);
                        } else {
                            this.consumed = i;
                        }
                    } else {
                        gVar.offer(t);
                    }
                } else {
                    getOrCreateQueue().offer(t);
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else {
                getOrCreateQueue().offer(t);
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        public void onError(Throwable th) {
            if (this.error.addThrowable(th)) {
                DisposableHelper.dispose(this.otherObserver);
                drain();
                return;
            }
            a.a(th);
        }

        public void onComplete() {
            this.mainDone = true;
            drain();
        }

        public void request(long j) {
            b.a(this.requested, j);
            drain();
        }

        public void cancel() {
            this.cancelled = true;
            SubscriptionHelper.cancel(this.mainSubscription);
            DisposableHelper.dispose(this.otherObserver);
            if (getAndIncrement() == 0) {
                this.queue = null;
                this.singleItem = null;
            }
        }

        /* access modifiers changed from: package-private */
        public void otherSuccess(T t) {
            if (compareAndSet(0, 1)) {
                long j = this.emitted;
                if (this.requested.get() != j) {
                    this.emitted = j + 1;
                    this.downstream.onNext(t);
                    this.otherState = 2;
                } else {
                    this.singleItem = t;
                    this.otherState = 1;
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            } else {
                this.singleItem = t;
                this.otherState = 1;
                if (getAndIncrement() != 0) {
                    return;
                }
            }
            drainLoop();
        }

        /* access modifiers changed from: package-private */
        public void otherError(Throwable th) {
            if (this.error.addThrowable(th)) {
                SubscriptionHelper.cancel(this.mainSubscription);
                drain();
                return;
            }
            a.a(th);
        }

        /* access modifiers changed from: package-private */
        public io.reactivex.internal.a.g<T> getOrCreateQueue() {
            io.reactivex.internal.a.g<T> gVar = this.queue;
            if (gVar != null) {
                return gVar;
            }
            SpscArrayQueue spscArrayQueue = new SpscArrayQueue(g.a());
            this.queue = spscArrayQueue;
            return spscArrayQueue;
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* access modifiers changed from: package-private */
        public void drainLoop() {
            int i;
            c<? super T> cVar = this.downstream;
            long j = this.emitted;
            int i2 = this.consumed;
            int i3 = this.limit;
            int i4 = 1;
            long j2 = j;
            int i5 = 1;
            while (true) {
                long j3 = this.requested.get();
                while (true) {
                    i = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
                    if (i == 0) {
                        break;
                    } else if (this.cancelled) {
                        this.singleItem = null;
                        this.queue = null;
                        return;
                    } else if (this.error.get() != null) {
                        this.singleItem = null;
                        this.queue = null;
                        cVar.onError(this.error.terminate());
                        return;
                    } else {
                        int i6 = this.otherState;
                        if (i6 == i4) {
                            T t = this.singleItem;
                            this.singleItem = null;
                            this.otherState = 2;
                            cVar.onNext(t);
                            j2++;
                        } else {
                            boolean z = this.mainDone;
                            io.reactivex.internal.a.g<T> gVar = this.queue;
                            Object poll = gVar != null ? gVar.poll() : null;
                            boolean z2 = poll == null;
                            if (z && z2 && i6 == 2) {
                                this.queue = null;
                                cVar.onComplete();
                                return;
                            } else if (z2) {
                                break;
                            } else {
                                cVar.onNext(poll);
                                j2++;
                                i2++;
                                if (i2 == i3) {
                                    this.mainSubscription.get().request((long) i3);
                                    i2 = 0;
                                }
                                i4 = 1;
                            }
                        }
                    }
                }
                if (i == 0) {
                    if (this.cancelled) {
                        this.singleItem = null;
                        this.queue = null;
                        return;
                    } else if (this.error.get() != null) {
                        this.singleItem = null;
                        this.queue = null;
                        cVar.onError(this.error.terminate());
                        return;
                    } else {
                        boolean z3 = this.mainDone;
                        io.reactivex.internal.a.g<T> gVar2 = this.queue;
                        boolean z4 = gVar2 == null || gVar2.isEmpty();
                        if (z3 && z4 && this.otherState == 2) {
                            this.queue = null;
                            cVar.onComplete();
                            return;
                        }
                    }
                }
                this.emitted = j2;
                this.consumed = i2;
                i5 = addAndGet(-i5);
                if (i5 != 0) {
                    i4 = 1;
                } else {
                    return;
                }
            }
        }

        static final class OtherObserver<T> extends AtomicReference<io.reactivex.disposables.b> implements z<T> {
            private static final long serialVersionUID = -2935427570954647017L;
            final MergeWithObserver<T> parent;

            OtherObserver(MergeWithObserver<T> mergeWithObserver) {
                this.parent = mergeWithObserver;
            }

            @Override // io.reactivex.z
            public void onSubscribe(io.reactivex.disposables.b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            @Override // io.reactivex.z
            public void onSuccess(T t) {
                this.parent.otherSuccess(t);
            }

            @Override // io.reactivex.z
            public void onError(Throwable th) {
                this.parent.otherError(th);
            }
        }
    }
}
