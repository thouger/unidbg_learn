package io.reactivex.internal.operators.flowable;

import io.reactivex.b.a;
import io.reactivex.c.g;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.a.e;
import io.reactivex.internal.a.h;
import io.reactivex.internal.disposables.c;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.d;

public final class FlowablePublishAlt<T> extends a<T> implements c {
    final b<T> b;
    final int c;
    final AtomicReference<PublishConnection<T>> d;

    @Override // io.reactivex.b.a
    public void c(g<? super io.reactivex.disposables.b> gVar) {
        PublishConnection<T> publishConnection;
        while (true) {
            publishConnection = this.d.get();
            if (publishConnection != null && !publishConnection.isDisposed()) {
                break;
            }
            PublishConnection<T> publishConnection2 = new PublishConnection<>(this.d, this.c);
            if (this.d.compareAndSet(publishConnection, publishConnection2)) {
                publishConnection = publishConnection2;
                break;
            }
        }
        boolean z = true;
        if (publishConnection.connect.get() || !publishConnection.connect.compareAndSet(false, true)) {
            z = false;
        }
        try {
            gVar.accept(publishConnection);
            if (z) {
                this.b.subscribe(publishConnection);
            }
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            throw ExceptionHelper.a(th);
        }
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(org.a.c<? super T> cVar) {
        PublishConnection<T> publishConnection;
        while (true) {
            publishConnection = this.d.get();
            if (publishConnection != null) {
                break;
            }
            PublishConnection<T> publishConnection2 = new PublishConnection<>(this.d, this.c);
            if (this.d.compareAndSet(publishConnection, publishConnection2)) {
                publishConnection = publishConnection2;
                break;
            }
        }
        InnerSubscription innerSubscription = new InnerSubscription(cVar, publishConnection);
        cVar.onSubscribe(innerSubscription);
        if (!publishConnection.add(innerSubscription)) {
            Throwable th = publishConnection.error;
            if (th != null) {
                cVar.onError(th);
            } else {
                cVar.onComplete();
            }
        } else if (innerSubscription.isCancelled()) {
            publishConnection.remove(innerSubscription);
        } else {
            publishConnection.drain();
        }
    }

    @Override // io.reactivex.internal.disposables.c
    public void a(io.reactivex.disposables.b bVar) {
        this.d.compareAndSet((PublishConnection) bVar, null);
    }

    static final class PublishConnection<T> extends AtomicInteger implements io.reactivex.disposables.b, j<T> {
        static final InnerSubscription[] EMPTY = new InnerSubscription[0];
        static final InnerSubscription[] TERMINATED = new InnerSubscription[0];
        private static final long serialVersionUID = -1672047311619175801L;
        final int bufferSize;
        final AtomicBoolean connect = new AtomicBoolean();
        int consumed;
        final AtomicReference<PublishConnection<T>> current;
        volatile boolean done;
        Throwable error;
        volatile h<T> queue;
        int sourceMode;
        final AtomicReference<InnerSubscription<T>[]> subscribers;
        final AtomicReference<d> upstream = new AtomicReference<>();

        PublishConnection(AtomicReference<PublishConnection<T>> atomicReference, int i) {
            this.current = atomicReference;
            this.bufferSize = i;
            this.subscribers = new AtomicReference<>(EMPTY);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.subscribers.getAndSet(TERMINATED);
            this.current.compareAndSet(this, null);
            SubscriptionHelper.cancel(this.upstream);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.setOnce(this.upstream, dVar)) {
                if (dVar instanceof e) {
                    e eVar = (e) dVar;
                    int requestFusion = eVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = eVar;
                        this.done = true;
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = eVar;
                        dVar.request((long) this.bufferSize);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.bufferSize);
                dVar.request((long) this.bufferSize);
            }
        }

        public void onNext(T t) {
            if (this.sourceMode != 0 || this.queue.offer(t)) {
                drain();
            } else {
                onError(new MissingBackpressureException("Prefetch queue is full?!"));
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            boolean z;
            int i;
            Object obj;
            if (getAndIncrement() == 0) {
                h<T> hVar = this.queue;
                int i2 = this.consumed;
                int i3 = this.bufferSize;
                int i4 = i3 - (i3 >> 2);
                boolean z2 = true;
                boolean z3 = this.sourceMode != 1;
                int i5 = i2;
                h<T> hVar2 = hVar;
                int i6 = 1;
                while (true) {
                    if (hVar2 != null) {
                        InnerSubscription<T>[] innerSubscriptionArr = this.subscribers.get();
                        long j = Long.MAX_VALUE;
                        boolean z4 = false;
                        for (InnerSubscription<T> innerSubscription : innerSubscriptionArr) {
                            long j2 = innerSubscription.get();
                            if (j2 != Long.MIN_VALUE) {
                                j = Math.min(j2 - innerSubscription.emitted, j);
                                z4 = true;
                            }
                        }
                        if (!z4) {
                            j = 0;
                        }
                        for (long j3 = 0; j != j3; j3 = 0) {
                            boolean z5 = this.done;
                            try {
                                Object poll = hVar2.poll();
                                boolean z6 = poll == null;
                                if (!checkTerminated(z5, z6)) {
                                    if (z6) {
                                        break;
                                    }
                                    int length = innerSubscriptionArr.length;
                                    int i7 = 0;
                                    while (i7 < length) {
                                        InnerSubscription<T> innerSubscription2 = innerSubscriptionArr[i7];
                                        if (!innerSubscription2.isCancelled()) {
                                            innerSubscription2.downstream.onNext(poll);
                                            i = length;
                                            obj = poll;
                                            innerSubscription2.emitted++;
                                        } else {
                                            i = length;
                                            obj = poll;
                                        }
                                        i7++;
                                        poll = obj;
                                        length = i;
                                    }
                                    if (z3 && (i5 = i5 + 1) == i4) {
                                        this.upstream.get().request((long) i4);
                                        i5 = 0;
                                    }
                                    j--;
                                    if (innerSubscriptionArr != this.subscribers.get()) {
                                        z2 = true;
                                        break;
                                    }
                                } else {
                                    return;
                                }
                            } catch (Throwable th) {
                                io.reactivex.exceptions.a.b(th);
                                this.upstream.get().cancel();
                                hVar2.clear();
                                this.done = true;
                                signalError(th);
                                return;
                            }
                        }
                        z = true;
                        if (checkTerminated(this.done, hVar2.isEmpty())) {
                            return;
                        }
                    } else {
                        z = z2;
                    }
                    this.consumed = i5;
                    i6 = addAndGet(-i6);
                    if (i6 != 0) {
                        if (hVar2 == null) {
                            hVar2 = this.queue;
                        }
                        z2 = z;
                    } else {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminated(boolean z, boolean z2) {
            if (!z || !z2) {
                return false;
            }
            Throwable th = this.error;
            if (th != null) {
                signalError(th);
                return true;
            }
            InnerSubscription<T>[] andSet = this.subscribers.getAndSet(TERMINATED);
            for (InnerSubscription<T> innerSubscription : andSet) {
                if (!innerSubscription.isCancelled()) {
                    innerSubscription.downstream.onComplete();
                }
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public void signalError(Throwable th) {
            InnerSubscription<T>[] andSet = this.subscribers.getAndSet(TERMINATED);
            for (InnerSubscription<T> innerSubscription : andSet) {
                if (!innerSubscription.isCancelled()) {
                    innerSubscription.downstream.onError(th);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean add(InnerSubscription<T> innerSubscription) {
            InnerSubscription<T>[] innerSubscriptionArr;
            InnerSubscription<T>[] innerSubscriptionArr2;
            do {
                innerSubscriptionArr = this.subscribers.get();
                if (innerSubscriptionArr == TERMINATED) {
                    return false;
                }
                int length = innerSubscriptionArr.length;
                innerSubscriptionArr2 = new InnerSubscription[(length + 1)];
                System.arraycopy(innerSubscriptionArr, 0, innerSubscriptionArr2, 0, length);
                innerSubscriptionArr2[length] = innerSubscription;
            } while (!this.subscribers.compareAndSet(innerSubscriptionArr, innerSubscriptionArr2));
            return true;
        }

        /* access modifiers changed from: package-private */
        public void remove(InnerSubscription<T> innerSubscription) {
            InnerSubscription<T>[] innerSubscriptionArr;
            InnerSubscription<T>[] innerSubscriptionArr2;
            do {
                innerSubscriptionArr = this.subscribers.get();
                int length = innerSubscriptionArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (innerSubscriptionArr[i2] == innerSubscription) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            innerSubscriptionArr2 = EMPTY;
                        } else {
                            InnerSubscription<T>[] innerSubscriptionArr3 = new InnerSubscription[(length - 1)];
                            System.arraycopy(innerSubscriptionArr, 0, innerSubscriptionArr3, 0, i);
                            System.arraycopy(innerSubscriptionArr, i + 1, innerSubscriptionArr3, i, (length - i) - 1);
                            innerSubscriptionArr2 = innerSubscriptionArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(innerSubscriptionArr, innerSubscriptionArr2));
        }
    }

    /* access modifiers changed from: package-private */
    public static final class InnerSubscription<T> extends AtomicLong implements d {
        private static final long serialVersionUID = 2845000326761540265L;
        final org.a.c<? super T> downstream;
        long emitted;
        final PublishConnection<T> parent;

        InnerSubscription(org.a.c<? super T> cVar, PublishConnection<T> publishConnection) {
            this.downstream = cVar;
            this.parent = publishConnection;
        }

        public void request(long j) {
            io.reactivex.internal.util.b.b(this, j);
            this.parent.drain();
        }

        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.drain();
            }
        }

        public boolean isCancelled() {
            return get() == Long.MIN_VALUE;
        }
    }
}
