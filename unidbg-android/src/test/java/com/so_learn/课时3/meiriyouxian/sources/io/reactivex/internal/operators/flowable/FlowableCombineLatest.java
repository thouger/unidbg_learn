package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.g;
import io.reactivex.internal.operators.flowable.f;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.j;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableCombineLatest<T, R> extends g<R> {
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
            try {
                Iterator it2 = (Iterator) io.reactivex.internal.functions.a.a(this.c.iterator(), "The iterator returned is null");
                i = 0;
                while (it2.hasNext()) {
                    try {
                        try {
                            b<? extends T> bVar = (b) io.reactivex.internal.functions.a.a(it2.next(), "The publisher returned by the iterator is null");
                            if (i == bVarArr.length) {
                                b<? extends T>[] bVarArr2 = new b[((i >> 2) + i)];
                                System.arraycopy(bVarArr, 0, bVarArr2, 0, i);
                                bVarArr = bVarArr2;
                            }
                            bVarArr[i] = bVar;
                            i++;
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            EmptySubscription.error(th, cVar);
                            return;
                        }
                    } catch (Throwable th2) {
                        io.reactivex.exceptions.a.b(th2);
                        EmptySubscription.error(th2, cVar);
                        return;
                    }
                }
            } catch (Throwable th3) {
                io.reactivex.exceptions.a.b(th3);
                EmptySubscription.error(th3, cVar);
                return;
            }
        } else {
            i = bVarArr.length;
        }
        if (i == 0) {
            EmptySubscription.complete(cVar);
        } else if (i == 1) {
            bVarArr[0].subscribe(new f.b(cVar, new a()));
        } else {
            CombineLatestCoordinator combineLatestCoordinator = new CombineLatestCoordinator(cVar, this.d, i, this.e, this.f);
            cVar.onSubscribe(combineLatestCoordinator);
            combineLatestCoordinator.subscribe(bVarArr, i);
        }
    }

    static final class CombineLatestCoordinator<T, R> extends BasicIntQueueSubscription<R> {
        private static final long serialVersionUID = -5082275438355852221L;
        volatile boolean cancelled;
        final h<? super Object[], ? extends R> combiner;
        int completedSources;
        final boolean delayErrors;
        volatile boolean done;
        final c<? super R> downstream;
        final AtomicReference<Throwable> error;
        final Object[] latest;
        int nonEmptySources;
        boolean outputFused;
        final io.reactivex.internal.queue.a<Object> queue;
        final AtomicLong requested;
        final CombineLatestInnerSubscriber<T>[] subscribers;

        CombineLatestCoordinator(c<? super R> cVar, h<? super Object[], ? extends R> hVar, int i, int i2, boolean z) {
            this.downstream = cVar;
            this.combiner = hVar;
            CombineLatestInnerSubscriber<T>[] combineLatestInnerSubscriberArr = new CombineLatestInnerSubscriber[i];
            for (int i3 = 0; i3 < i; i3++) {
                combineLatestInnerSubscriberArr[i3] = new CombineLatestInnerSubscriber<>(this, i3, i2);
            }
            this.subscribers = combineLatestInnerSubscriberArr;
            this.latest = new Object[i];
            this.queue = new io.reactivex.internal.queue.a<>(i2);
            this.requested = new AtomicLong();
            this.error = new AtomicReference<>();
            this.delayErrors = z;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            cancelAll();
        }

        /* access modifiers changed from: package-private */
        public void subscribe(b<? extends T>[] bVarArr, int i) {
            c[] cVarArr = this.subscribers;
            for (int i2 = 0; i2 < i && !this.done && !this.cancelled; i2++) {
                bVarArr[i2].subscribe(cVarArr[i2]);
            }
        }

        /* access modifiers changed from: package-private */
        public void innerValue(int i, T t) {
            boolean z;
            synchronized (this) {
                Object[] objArr = this.latest;
                int i2 = this.nonEmptySources;
                if (objArr[i] == null) {
                    i2++;
                    this.nonEmptySources = i2;
                }
                objArr[i] = t;
                if (objArr.length == i2) {
                    this.queue.a(this.subscribers[i], (CombineLatestInnerSubscriber<T>) objArr.clone());
                    z = false;
                } else {
                    z = true;
                }
            }
            if (z) {
                this.subscribers[i].requestOne();
            } else {
                drain();
            }
        }

        /* access modifiers changed from: package-private */
        public void innerComplete(int i) {
            synchronized (this) {
                Object[] objArr = this.latest;
                if (objArr[i] != null) {
                    int i2 = this.completedSources + 1;
                    if (i2 == objArr.length) {
                        this.done = true;
                    } else {
                        this.completedSources = i2;
                        return;
                    }
                } else {
                    this.done = true;
                }
                drain();
            }
        }

        /* access modifiers changed from: package-private */
        public void innerError(int i, Throwable th) {
            if (!ExceptionHelper.a(this.error, th)) {
                io.reactivex.e.a.a(th);
            } else if (!this.delayErrors) {
                cancelAll();
                this.done = true;
                drain();
            } else {
                innerComplete(i);
            }
        }

        /* access modifiers changed from: package-private */
        public void drainOutput() {
            c<? super R> cVar = this.downstream;
            io.reactivex.internal.queue.a<Object> aVar = this.queue;
            int i = 1;
            while (!this.cancelled) {
                Throwable th = this.error.get();
                if (th != null) {
                    aVar.clear();
                    cVar.onError(th);
                    return;
                }
                boolean z = this.done;
                boolean isEmpty = aVar.isEmpty();
                if (!isEmpty) {
                    cVar.onNext((Object) null);
                }
                if (!z || !isEmpty) {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    cVar.onComplete();
                    return;
                }
            }
            aVar.clear();
        }

        /* access modifiers changed from: package-private */
        public void drainAsync() {
            int i;
            c<? super R> cVar = this.downstream;
            io.reactivex.internal.queue.a<Object> aVar = this.queue;
            int i2 = 1;
            do {
                long j = this.requested.get();
                long j2 = 0;
                while (true) {
                    i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                    if (i == 0) {
                        break;
                    }
                    boolean z = this.done;
                    Object poll = aVar.poll();
                    boolean z2 = poll == null;
                    if (!checkTerminated(z, z2, cVar, aVar)) {
                        if (z2) {
                            break;
                        }
                        try {
                            cVar.onNext(io.reactivex.internal.functions.a.a(this.combiner.apply((Object[]) aVar.poll()), "The combiner returned a null value"));
                            ((CombineLatestInnerSubscriber) poll).requestOne();
                            j2++;
                        } catch (Throwable th) {
                            io.reactivex.exceptions.a.b(th);
                            cancelAll();
                            ExceptionHelper.a(this.error, th);
                            cVar.onError(ExceptionHelper.a(this.error));
                            return;
                        }
                    } else {
                        return;
                    }
                }
                if (i != 0 || !checkTerminated(this.done, aVar.isEmpty(), cVar, aVar)) {
                    if (!(j2 == 0 || j == Long.MAX_VALUE)) {
                        this.requested.addAndGet(-j2);
                    }
                    i2 = addAndGet(-i2);
                } else {
                    return;
                }
            } while (i2 != 0);
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                if (this.outputFused) {
                    drainOutput();
                } else {
                    drainAsync();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminated(boolean z, boolean z2, c<?> cVar, io.reactivex.internal.queue.a<?> aVar) {
            if (this.cancelled) {
                cancelAll();
                aVar.clear();
                return true;
            } else if (!z) {
                return false;
            } else {
                if (!this.delayErrors) {
                    Throwable a = ExceptionHelper.a(this.error);
                    if (a != null && a != ExceptionHelper.a) {
                        cancelAll();
                        aVar.clear();
                        cVar.onError(a);
                        return true;
                    } else if (!z2) {
                        return false;
                    } else {
                        cancelAll();
                        cVar.onComplete();
                        return true;
                    }
                } else if (!z2) {
                    return false;
                } else {
                    cancelAll();
                    Throwable a2 = ExceptionHelper.a(this.error);
                    if (a2 == null || a2 == ExceptionHelper.a) {
                        cVar.onComplete();
                    } else {
                        cVar.onError(a2);
                    }
                    return true;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void cancelAll() {
            for (CombineLatestInnerSubscriber<T> combineLatestInnerSubscriber : this.subscribers) {
                combineLatestInnerSubscriber.cancel();
            }
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            boolean z = false;
            if ((i & 4) != 0) {
                return 0;
            }
            int i2 = i & 2;
            if (i2 != 0) {
                z = true;
            }
            this.outputFused = z;
            return i2;
        }

        @Override // io.reactivex.internal.a.h
        public R poll() throws Exception {
            Object poll = this.queue.poll();
            if (poll == null) {
                return null;
            }
            R r = (R) io.reactivex.internal.functions.a.a(this.combiner.apply((Object[]) this.queue.poll()), "The combiner returned a null value");
            ((CombineLatestInnerSubscriber) poll).requestOne();
            return r;
        }

        @Override // io.reactivex.internal.a.h
        public void clear() {
            this.queue.clear();
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class CombineLatestInnerSubscriber<T> extends AtomicReference<d> implements j<T> {
        private static final long serialVersionUID = -8730235182291002949L;
        final int index;
        final int limit;
        final CombineLatestCoordinator<T, ?> parent;
        final int prefetch;
        int produced;

        CombineLatestInnerSubscriber(CombineLatestCoordinator<T, ?> combineLatestCoordinator, int i, int i2) {
            this.parent = combineLatestCoordinator;
            this.index = i;
            this.prefetch = i2;
            this.limit = i2 - (i2 >> 2);
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.setOnce(this, dVar, (long) this.prefetch);
        }

        public void onNext(T t) {
            this.parent.innerValue(this.index, t);
        }

        public void onError(Throwable th) {
            this.parent.innerError(this.index, th);
        }

        public void onComplete() {
            this.parent.innerComplete(this.index);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        public void requestOne() {
            int i = this.produced + 1;
            if (i == this.limit) {
                this.produced = 0;
                get().request((long) i);
                return;
            }
            this.produced = i;
        }
    }

    final class a implements h<T, R> {
        a() {
        }

        @Override // io.reactivex.c.h
        public R apply(T t) throws Exception {
            return (R) FlowableCombineLatest.this.d.apply(new Object[]{t});
        }
    }
}
