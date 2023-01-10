package io.reactivex.internal.operators.maybe;

import io.reactivex.g;
import io.reactivex.internal.a.h;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.b;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.a.c;

public final class MaybeMergeArray<T> extends g<T> {
    final o<? extends T>[] b;

    /* access modifiers changed from: package-private */
    public interface a<T> extends h<T> {
        int consumerIndex();

        void drop();

        T peek();

        @Override // java.util.Queue, io.reactivex.internal.operators.maybe.MaybeMergeArray.a, io.reactivex.internal.a.h
        T poll();

        int producerIndex();
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        a aVar;
        o<? extends T>[] oVarArr = this.b;
        int length = oVarArr.length;
        if (length <= a()) {
            aVar = new MpscFillOnceSimpleQueue(length);
        } else {
            aVar = new ClqSimpleQueue();
        }
        MergeMaybeObserver mergeMaybeObserver = new MergeMaybeObserver(cVar, length, aVar);
        cVar.onSubscribe(mergeMaybeObserver);
        AtomicThrowable atomicThrowable = mergeMaybeObserver.error;
        for (o<? extends T> oVar : oVarArr) {
            if (!mergeMaybeObserver.isCancelled() && atomicThrowable.get() == null) {
                oVar.a(mergeMaybeObserver);
            } else {
                return;
            }
        }
    }

    static final class MergeMaybeObserver<T> extends BasicIntQueueSubscription<T> implements m<T> {
        private static final long serialVersionUID = -660395290758764731L;
        volatile boolean cancelled;
        long consumed;
        final c<? super T> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        boolean outputFused;
        final a<Object> queue;
        final AtomicLong requested = new AtomicLong();
        final io.reactivex.disposables.a set = new io.reactivex.disposables.a();
        final int sourceCount;

        MergeMaybeObserver(c<? super T> cVar, int i, a<Object> aVar) {
            this.downstream = cVar;
            this.sourceCount = i;
            this.queue = aVar;
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        @Override // io.reactivex.internal.a.h
        public T poll() throws Exception {
            T t;
            do {
                t = (T) this.queue.poll();
            } while (t == NotificationLite.COMPLETE);
            return t;
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return this.queue.isEmpty();
        }

        @Override // io.reactivex.internal.a.h
        public void clear() {
            this.queue.clear();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.set.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        @Override // io.reactivex.m
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            this.set.a(bVar);
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            this.queue.offer(t);
            drain();
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            if (this.error.addThrowable(th)) {
                this.set.dispose();
                this.queue.offer(NotificationLite.COMPLETE);
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.m
        public void onComplete() {
            this.queue.offer(NotificationLite.COMPLETE);
            drain();
        }

        /* access modifiers changed from: package-private */
        public boolean isCancelled() {
            return this.cancelled;
        }

        /* access modifiers changed from: package-private */
        public void drainNormal() {
            int i;
            c<? super T> cVar = this.downstream;
            a<Object> aVar = this.queue;
            long j = this.consumed;
            int i2 = 1;
            do {
                long j2 = this.requested.get();
                while (true) {
                    i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
                    if (i == 0) {
                        break;
                    } else if (this.cancelled) {
                        aVar.clear();
                        return;
                    } else if (this.error.get() != null) {
                        aVar.clear();
                        cVar.onError(this.error.terminate());
                        return;
                    } else if (aVar.consumerIndex() == this.sourceCount) {
                        cVar.onComplete();
                        return;
                    } else {
                        Object poll = aVar.poll();
                        if (poll == null) {
                            break;
                        } else if (poll != NotificationLite.COMPLETE) {
                            cVar.onNext(poll);
                            j++;
                        }
                    }
                }
                if (i == 0) {
                    if (this.error.get() != null) {
                        aVar.clear();
                        cVar.onError(this.error.terminate());
                        return;
                    }
                    while (aVar.peek() == NotificationLite.COMPLETE) {
                        aVar.drop();
                    }
                    if (aVar.consumerIndex() == this.sourceCount) {
                        cVar.onComplete();
                        return;
                    }
                }
                this.consumed = j;
                i2 = addAndGet(-i2);
            } while (i2 != 0);
        }

        /* access modifiers changed from: package-private */
        public void drainFused() {
            c<? super T> cVar = this.downstream;
            a<Object> aVar = this.queue;
            int i = 1;
            while (!this.cancelled) {
                Throwable th = this.error.get();
                if (th != null) {
                    aVar.clear();
                    cVar.onError(th);
                    return;
                }
                boolean z = aVar.producerIndex() == this.sourceCount;
                if (!aVar.isEmpty()) {
                    cVar.onNext((Object) null);
                }
                if (z) {
                    cVar.onComplete();
                    return;
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
            aVar.clear();
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                if (this.outputFused) {
                    drainFused();
                } else {
                    drainNormal();
                }
            }
        }
    }

    static final class MpscFillOnceSimpleQueue<T> extends AtomicReferenceArray<T> implements a<T> {
        private static final long serialVersionUID = -7969063454040569579L;
        int consumerIndex;
        final AtomicInteger producerIndex = new AtomicInteger();

        MpscFillOnceSimpleQueue(int i) {
            super(i);
        }

        @Override // io.reactivex.internal.a.h
        public boolean offer(T t) {
            io.reactivex.internal.functions.a.a((Object) t, "value is null");
            int andIncrement = this.producerIndex.getAndIncrement();
            if (andIncrement >= length()) {
                return false;
            }
            lazySet(andIncrement, t);
            return true;
        }

        public boolean offer(T t, T t2) {
            throw new UnsupportedOperationException();
        }

        @Override // io.reactivex.internal.operators.maybe.MaybeMergeArray.a, java.util.Queue, io.reactivex.internal.a.h
        public T poll() {
            int i = this.consumerIndex;
            if (i == length()) {
                return null;
            }
            AtomicInteger atomicInteger = this.producerIndex;
            do {
                T t = get(i);
                if (t != null) {
                    this.consumerIndex = i + 1;
                    lazySet(i, null);
                    return t;
                }
            } while (atomicInteger.get() != i);
            return null;
        }

        @Override // io.reactivex.internal.operators.maybe.MaybeMergeArray.a
        public T peek() {
            int i = this.consumerIndex;
            if (i == length()) {
                return null;
            }
            return get(i);
        }

        @Override // io.reactivex.internal.operators.maybe.MaybeMergeArray.a
        public void drop() {
            int i = this.consumerIndex;
            lazySet(i, null);
            this.consumerIndex = i + 1;
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return this.consumerIndex == producerIndex();
        }

        @Override // io.reactivex.internal.a.h
        public void clear() {
            while (poll() != null && !isEmpty()) {
            }
        }

        @Override // io.reactivex.internal.operators.maybe.MaybeMergeArray.a
        public int consumerIndex() {
            return this.consumerIndex;
        }

        @Override // io.reactivex.internal.operators.maybe.MaybeMergeArray.a
        public int producerIndex() {
            return this.producerIndex.get();
        }
    }

    static final class ClqSimpleQueue<T> extends ConcurrentLinkedQueue<T> implements a<T> {
        private static final long serialVersionUID = -4025173261791142821L;
        int consumerIndex;
        final AtomicInteger producerIndex = new AtomicInteger();

        ClqSimpleQueue() {
        }

        public boolean offer(T t, T t2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.ConcurrentLinkedQueue, java.util.Queue, io.reactivex.internal.a.h
        public boolean offer(T t) {
            this.producerIndex.getAndIncrement();
            return super.offer(t);
        }

        @Override // java.util.concurrent.ConcurrentLinkedQueue, java.util.Queue, io.reactivex.internal.operators.maybe.MaybeMergeArray.a, io.reactivex.internal.a.h
        public T poll() {
            T t = (T) super.poll();
            if (t != null) {
                this.consumerIndex++;
            }
            return t;
        }

        @Override // io.reactivex.internal.operators.maybe.MaybeMergeArray.a
        public int consumerIndex() {
            return this.consumerIndex;
        }

        @Override // io.reactivex.internal.operators.maybe.MaybeMergeArray.a
        public int producerIndex() {
            return this.producerIndex.get();
        }

        @Override // io.reactivex.internal.operators.maybe.MaybeMergeArray.a
        public void drop() {
            poll();
        }
    }
}
