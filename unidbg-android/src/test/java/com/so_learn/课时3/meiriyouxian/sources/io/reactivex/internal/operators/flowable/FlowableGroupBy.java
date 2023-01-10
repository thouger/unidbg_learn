package io.reactivex.internal.operators.flowable;

import io.reactivex.c.g;
import io.reactivex.c.h;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.EmptyComponent;
import io.reactivex.j;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableGroupBy<T, K, V> extends a<T, io.reactivex.b.b<K, V>> {
    final h<? super T, ? extends K> c;
    final h<? super T, ? extends V> d;
    final int e;
    final boolean f;
    final h<? super g<Object>, ? extends Map<K, Object>> g;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super io.reactivex.b.b<K, V>> cVar) {
        ConcurrentLinkedQueue concurrentLinkedQueue;
        Map map;
        try {
            if (this.g == null) {
                concurrentLinkedQueue = null;
                map = new ConcurrentHashMap();
            } else {
                concurrentLinkedQueue = new ConcurrentLinkedQueue();
                map = (Map) this.g.apply(new a(concurrentLinkedQueue));
            }
            this.b.a((j) new GroupBySubscriber(cVar, this.c, this.d, this.e, this.f, map, concurrentLinkedQueue));
        } catch (Exception e) {
            io.reactivex.exceptions.a.b(e);
            cVar.onSubscribe(EmptyComponent.INSTANCE);
            cVar.onError(e);
        }
    }

    public static final class GroupBySubscriber<T, K, V> extends BasicIntQueueSubscription<io.reactivex.b.b<K, V>> implements j<T> {
        static final Object NULL_KEY = new Object();
        private static final long serialVersionUID = -3688291656102519502L;
        final int bufferSize;
        final AtomicBoolean cancelled = new AtomicBoolean();
        final boolean delayError;
        boolean done;
        final c<? super io.reactivex.b.b<K, V>> downstream;
        Throwable error;
        final Queue<b<K, V>> evictedGroups;
        volatile boolean finished;
        final AtomicInteger groupCount = new AtomicInteger(1);
        final Map<Object, b<K, V>> groups;
        final h<? super T, ? extends K> keySelector;
        boolean outputFused;
        final io.reactivex.internal.queue.a<io.reactivex.b.b<K, V>> queue;
        final AtomicLong requested = new AtomicLong();
        d upstream;
        final h<? super T, ? extends V> valueSelector;

        public GroupBySubscriber(c<? super io.reactivex.b.b<K, V>> cVar, h<? super T, ? extends K> hVar, h<? super T, ? extends V> hVar2, int i, boolean z, Map<Object, b<K, V>> map, Queue<b<K, V>> queue) {
            this.downstream = cVar;
            this.keySelector = hVar;
            this.valueSelector = hVar2;
            this.bufferSize = i;
            this.delayError = z;
            this.groups = map;
            this.evictedGroups = queue;
            this.queue = new io.reactivex.internal.queue.a<>(i);
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                dVar.request((long) this.bufferSize);
            }
        }

        public void onNext(T t) {
            Object obj;
            if (!this.done) {
                io.reactivex.internal.queue.a<io.reactivex.b.b<K, V>> aVar = this.queue;
                try {
                    Object apply = this.keySelector.apply(t);
                    boolean z = false;
                    if (apply != null) {
                        obj = apply;
                    } else {
                        obj = NULL_KEY;
                    }
                    b<K, V> bVar = this.groups.get(obj);
                    if (bVar == null) {
                        if (!this.cancelled.get()) {
                            bVar = b.a(apply, this.bufferSize, (GroupBySubscriber<?, Object, T>) this, this.delayError);
                            this.groups.put(obj, bVar);
                            this.groupCount.getAndIncrement();
                            z = true;
                        } else {
                            return;
                        }
                    }
                    try {
                        bVar.b((b<K, V>) io.reactivex.internal.functions.a.a(this.valueSelector.apply(t), "The valueSelector returned null"));
                        completeEvictions();
                        if (z) {
                            aVar.offer(bVar);
                            drain();
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        this.upstream.cancel();
                        onError(th);
                    }
                } catch (Throwable th2) {
                    io.reactivex.exceptions.a.b(th2);
                    this.upstream.cancel();
                    onError(th2);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.done = true;
            for (b<K, V> bVar : this.groups.values()) {
                bVar.a(th);
            }
            this.groups.clear();
            Queue<b<K, V>> queue = this.evictedGroups;
            if (queue != null) {
                queue.clear();
            }
            this.error = th;
            this.finished = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                for (b<K, V> bVar : this.groups.values()) {
                    bVar.g();
                }
                this.groups.clear();
                Queue<b<K, V>> queue = this.evictedGroups;
                if (queue != null) {
                    queue.clear();
                }
                this.done = true;
                this.finished = true;
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
            if (this.cancelled.compareAndSet(false, true)) {
                completeEvictions();
                if (this.groupCount.decrementAndGet() == 0) {
                    this.upstream.cancel();
                }
            }
        }

        private void completeEvictions() {
            if (this.evictedGroups != null) {
                int i = 0;
                while (true) {
                    b<K, V> poll = this.evictedGroups.poll();
                    if (poll == null) {
                        break;
                    }
                    poll.g();
                    i++;
                }
                if (i != 0) {
                    this.groupCount.addAndGet(-i);
                }
            }
        }

        public void cancel(K k) {
            if (k == null) {
                k = (K) NULL_KEY;
            }
            this.groups.remove(k);
            if (this.groupCount.decrementAndGet() == 0) {
                this.upstream.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
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

        /* access modifiers changed from: package-private */
        public void drainFused() {
            Throwable th;
            io.reactivex.internal.queue.a<io.reactivex.b.b<K, V>> aVar = this.queue;
            c<? super io.reactivex.b.b<K, V>> cVar = this.downstream;
            int i = 1;
            while (!this.cancelled.get()) {
                boolean z = this.finished;
                if (!z || this.delayError || (th = this.error) == null) {
                    cVar.onNext((Object) null);
                    if (z) {
                        Throwable th2 = this.error;
                        if (th2 != null) {
                            cVar.onError(th2);
                            return;
                        } else {
                            cVar.onComplete();
                            return;
                        }
                    } else {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    }
                } else {
                    aVar.clear();
                    cVar.onError(th);
                    return;
                }
            }
            aVar.clear();
        }

        /* access modifiers changed from: package-private */
        public void drainNormal() {
            int i;
            io.reactivex.internal.queue.a<io.reactivex.b.b<K, V>> aVar = this.queue;
            c<? super io.reactivex.b.b<K, V>> cVar = this.downstream;
            int i2 = 1;
            do {
                long j = this.requested.get();
                long j2 = 0;
                while (true) {
                    i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                    if (i == 0) {
                        break;
                    }
                    boolean z = this.finished;
                    io.reactivex.b.b bVar = (io.reactivex.b.b) aVar.poll();
                    boolean z2 = bVar == null;
                    if (!checkTerminated(z, z2, cVar, aVar)) {
                        if (z2) {
                            break;
                        }
                        cVar.onNext(bVar);
                        j2++;
                    } else {
                        return;
                    }
                }
                if (i != 0 || !checkTerminated(this.finished, aVar.isEmpty(), cVar, aVar)) {
                    if (j2 != 0) {
                        if (j != Long.MAX_VALUE) {
                            this.requested.addAndGet(-j2);
                        }
                        this.upstream.request(j2);
                    }
                    i2 = addAndGet(-i2);
                } else {
                    return;
                }
            } while (i2 != 0);
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminated(boolean z, boolean z2, c<?> cVar, io.reactivex.internal.queue.a<?> aVar) {
            if (this.cancelled.get()) {
                aVar.clear();
                return true;
            } else if (this.delayError) {
                if (!z || !z2) {
                    return false;
                }
                Throwable th = this.error;
                if (th != null) {
                    cVar.onError(th);
                } else {
                    cVar.onComplete();
                }
                return true;
            } else if (!z) {
                return false;
            } else {
                Throwable th2 = this.error;
                if (th2 != null) {
                    aVar.clear();
                    cVar.onError(th2);
                    return true;
                } else if (!z2) {
                    return false;
                } else {
                    cVar.onComplete();
                    return true;
                }
            }
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
        public io.reactivex.b.b<K, V> poll() {
            return (io.reactivex.b.b) this.queue.poll();
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

    static final class a<K, V> implements g<b<K, V>> {
        final Queue<b<K, V>> a;

        a(Queue<b<K, V>> queue) {
            this.a = queue;
        }

        /* renamed from: a */
        public void accept(b<K, V> bVar) {
            this.a.offer(bVar);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class b<K, T> extends io.reactivex.b.b<K, T> {
        final State<T, K> c;

        public static <T, K> b<K, T> a(K k, int i, GroupBySubscriber<?, K, T> groupBySubscriber, boolean z) {
            return new b<>(k, new State(i, groupBySubscriber, k, z));
        }

        protected b(K k, State<T, K> state) {
            super(k);
            this.c = state;
        }

        /* access modifiers changed from: protected */
        @Override // io.reactivex.g
        public void a(c<? super T> cVar) {
            this.c.subscribe(cVar);
        }

        public void b(T t) {
            this.c.onNext(t);
        }

        public void a(Throwable th) {
            this.c.onError(th);
        }

        public void g() {
            this.c.onComplete();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class State<T, K> extends BasicIntQueueSubscription<T> implements org.a.b<T> {
        private static final long serialVersionUID = -3852313036005250360L;
        final AtomicReference<c<? super T>> actual = new AtomicReference<>();
        final AtomicBoolean cancelled = new AtomicBoolean();
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final K key;
        final AtomicBoolean once = new AtomicBoolean();
        boolean outputFused;
        final GroupBySubscriber<?, K, T> parent;
        int produced;
        final io.reactivex.internal.queue.a<T> queue;
        final AtomicLong requested = new AtomicLong();

        State(int i, GroupBySubscriber<?, K, T> groupBySubscriber, K k, boolean z) {
            this.queue = new io.reactivex.internal.queue.a<>(i);
            this.parent = groupBySubscriber;
            this.key = k;
            this.delayError = z;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            if (this.cancelled.compareAndSet(false, true)) {
                this.parent.cancel(this.key);
            }
        }

        public void subscribe(c<? super T> cVar) {
            if (this.once.compareAndSet(false, true)) {
                cVar.onSubscribe(this);
                this.actual.lazySet(cVar);
                drain();
                return;
            }
            EmptySubscription.error(new IllegalStateException("Only one Subscriber allowed!"), cVar);
        }

        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        public void onError(Throwable th) {
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
            if (getAndIncrement() == 0) {
                if (this.outputFused) {
                    drainFused();
                } else {
                    drainNormal();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void drainFused() {
            Throwable th;
            io.reactivex.internal.queue.a<T> aVar = this.queue;
            c<? super T> cVar = this.actual.get();
            int i = 1;
            while (true) {
                if (cVar != null) {
                    if (this.cancelled.get()) {
                        aVar.clear();
                        return;
                    }
                    boolean z = this.done;
                    if (!z || this.delayError || (th = this.error) == null) {
                        cVar.onNext((Object) null);
                        if (z) {
                            Throwable th2 = this.error;
                            if (th2 != null) {
                                cVar.onError(th2);
                                return;
                            } else {
                                cVar.onComplete();
                                return;
                            }
                        }
                    } else {
                        aVar.clear();
                        cVar.onError(th);
                        return;
                    }
                }
                i = addAndGet(-i);
                if (i != 0) {
                    if (cVar == null) {
                        cVar = this.actual.get();
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void drainNormal() {
            int i;
            io.reactivex.internal.queue.a<T> aVar = this.queue;
            boolean z = this.delayError;
            c<? super T> cVar = this.actual.get();
            int i2 = 1;
            while (true) {
                if (cVar != null) {
                    long j = this.requested.get();
                    long j2 = 0;
                    while (true) {
                        i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                        if (i == 0) {
                            break;
                        }
                        boolean z2 = this.done;
                        Object poll = aVar.poll();
                        boolean z3 = poll == null;
                        if (!checkTerminated(z2, z3, cVar, z)) {
                            if (z3) {
                                break;
                            }
                            cVar.onNext(poll);
                            j2++;
                        } else {
                            return;
                        }
                    }
                    if (i == 0 && checkTerminated(this.done, aVar.isEmpty(), cVar, z)) {
                        return;
                    }
                    if (j2 != 0) {
                        if (j != Long.MAX_VALUE) {
                            this.requested.addAndGet(-j2);
                        }
                        this.parent.upstream.request(j2);
                    }
                }
                i2 = addAndGet(-i2);
                if (i2 != 0) {
                    if (cVar == null) {
                        cVar = this.actual.get();
                    }
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminated(boolean z, boolean z2, c<? super T> cVar, boolean z3) {
            if (this.cancelled.get()) {
                this.queue.clear();
                return true;
            } else if (!z) {
                return false;
            } else {
                if (!z3) {
                    Throwable th = this.error;
                    if (th != null) {
                        this.queue.clear();
                        cVar.onError(th);
                        return true;
                    } else if (!z2) {
                        return false;
                    } else {
                        cVar.onComplete();
                        return true;
                    }
                } else if (!z2) {
                    return false;
                } else {
                    Throwable th2 = this.error;
                    if (th2 != null) {
                        cVar.onError(th2);
                    } else {
                        cVar.onComplete();
                    }
                    return true;
                }
            }
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
        public T poll() {
            T t = (T) this.queue.poll();
            if (t != null) {
                this.produced++;
                return t;
            }
            int i = this.produced;
            if (i == 0) {
                return null;
            }
            this.produced = 0;
            this.parent.upstream.request((long) i);
            return null;
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return this.queue.isEmpty();
        }

        @Override // io.reactivex.internal.a.h
        public void clear() {
            this.queue.clear();
        }
    }
}
