package io.reactivex.internal.operators.parallel;

import io.reactivex.c.c;
import io.reactivex.g;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.parallel.a;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.a.d;

public final class ParallelReduceFull<T> extends g<T> {
    final a<? extends T> b;
    final c<T, T, T> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(org.a.c<? super T> cVar) {
        ParallelReduceFullMainSubscriber parallelReduceFullMainSubscriber = new ParallelReduceFullMainSubscriber(cVar, this.b.a(), this.c);
        cVar.onSubscribe(parallelReduceFullMainSubscriber);
        this.b.a((org.a.c<? super Object>[]) parallelReduceFullMainSubscriber.subscribers);
    }

    /* access modifiers changed from: package-private */
    public static final class ParallelReduceFullMainSubscriber<T> extends DeferredScalarSubscription<T> {
        private static final long serialVersionUID = -5370107872170712765L;
        final AtomicReference<SlotPair<T>> current = new AtomicReference<>();
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final c<T, T, T> reducer;
        final AtomicInteger remaining = new AtomicInteger();
        final ParallelReduceFullInnerSubscriber<T>[] subscribers;

        ParallelReduceFullMainSubscriber(org.a.c<? super T> cVar, int i, c<T, T, T> cVar2) {
            super(cVar);
            ParallelReduceFullInnerSubscriber<T>[] parallelReduceFullInnerSubscriberArr = new ParallelReduceFullInnerSubscriber[i];
            for (int i2 = 0; i2 < i; i2++) {
                parallelReduceFullInnerSubscriberArr[i2] = new ParallelReduceFullInnerSubscriber<>(this, cVar2);
            }
            this.subscribers = parallelReduceFullInnerSubscriberArr;
            this.reducer = cVar2;
            this.remaining.lazySet(i);
        }

        /* access modifiers changed from: package-private */
        public SlotPair<T> addValue(T t) {
            SlotPair<T> slotPair;
            int tryAcquireSlot;
            while (true) {
                slotPair = this.current.get();
                if (slotPair == null) {
                    slotPair = new SlotPair<>();
                    if (!this.current.compareAndSet(null, slotPair)) {
                        continue;
                    }
                }
                tryAcquireSlot = slotPair.tryAcquireSlot();
                if (tryAcquireSlot >= 0) {
                    break;
                }
                this.current.compareAndSet(slotPair, null);
            }
            if (tryAcquireSlot == 0) {
                slotPair.first = t;
            } else {
                slotPair.second = t;
            }
            if (!slotPair.releaseSlot()) {
                return null;
            }
            this.current.compareAndSet(slotPair, null);
            return slotPair;
        }

        @Override // io.reactivex.internal.subscriptions.DeferredScalarSubscription
        public void cancel() {
            for (ParallelReduceFullInnerSubscriber<T> parallelReduceFullInnerSubscriber : this.subscribers) {
                parallelReduceFullInnerSubscriber.cancel();
            }
        }

        /* access modifiers changed from: package-private */
        public void innerError(Throwable th) {
            if (this.error.compareAndSet(null, th)) {
                cancel();
                this.downstream.onError(th);
            } else if (th != this.error.get()) {
                io.reactivex.e.a.a(th);
            }
        }

        /* access modifiers changed from: package-private */
        public void innerComplete(T t) {
            if (t != null) {
                while (true) {
                    SlotPair<T> addValue = addValue(t);
                    if (addValue == null) {
                        break;
                    }
                    try {
                        t = (T) io.reactivex.internal.functions.a.a(this.reducer.apply(addValue.first, addValue.second), "The reducer returned a null value");
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        innerError(th);
                        return;
                    }
                }
            }
            if (this.remaining.decrementAndGet() == 0) {
                SlotPair<T> slotPair = this.current.get();
                this.current.lazySet(null);
                if (slotPair != null) {
                    complete(slotPair.first);
                } else {
                    this.downstream.onComplete();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ParallelReduceFullInnerSubscriber<T> extends AtomicReference<d> implements j<T> {
        private static final long serialVersionUID = -7954444275102466525L;
        boolean done;
        final ParallelReduceFullMainSubscriber<T> parent;
        final c<T, T, T> reducer;
        T value;

        ParallelReduceFullInnerSubscriber(ParallelReduceFullMainSubscriber<T> parallelReduceFullMainSubscriber, c<T, T, T> cVar) {
            this.parent = parallelReduceFullMainSubscriber;
            this.reducer = cVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.setOnce(this, dVar, Long.MAX_VALUE);
        }

        public void onNext(T t) {
            if (!this.done) {
                T t2 = this.value;
                if (t2 == null) {
                    this.value = t;
                    return;
                }
                try {
                    this.value = (T) io.reactivex.internal.functions.a.a(this.reducer.apply(t2, t), "The reducer returned a null value");
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    get().cancel();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.done = true;
            this.parent.innerError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.parent.innerComplete(this.value);
            }
        }

        /* access modifiers changed from: package-private */
        public void cancel() {
            SubscriptionHelper.cancel(this);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class SlotPair<T> extends AtomicInteger {
        private static final long serialVersionUID = 473971317683868662L;
        T first;
        final AtomicInteger releaseIndex = new AtomicInteger();
        T second;

        SlotPair() {
        }

        /* access modifiers changed from: package-private */
        public int tryAcquireSlot() {
            int i;
            do {
                i = get();
                if (i >= 2) {
                    return -1;
                }
            } while (!compareAndSet(i, i + 1));
            return i;
        }

        /* access modifiers changed from: package-private */
        public boolean releaseSlot() {
            return this.releaseIndex.incrementAndGet() == 2;
        }
    }
}
