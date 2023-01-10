package io.reactivex.internal.operators.observable;

import io.reactivex.c.h;
import io.reactivex.d.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.t;
import io.reactivex.v;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableGroupBy<T, K, V> extends a<T, b<K, V>> {
    final h<? super T, ? extends K> b;
    final h<? super T, ? extends V> c;
    final int d;
    final boolean e;

    @Override // io.reactivex.q
    public void a(v<? super b<K, V>> vVar) {
        this.a.subscribe(new GroupByObserver(vVar, this.b, this.c, this.d, this.e));
    }

    public static final class GroupByObserver<T, K, V> extends AtomicInteger implements io.reactivex.disposables.b, v<T> {
        static final Object NULL_KEY = new Object();
        private static final long serialVersionUID = -3688291656102519502L;
        final int bufferSize;
        final AtomicBoolean cancelled = new AtomicBoolean();
        final boolean delayError;
        final v<? super b<K, V>> downstream;
        final Map<Object, a<K, V>> groups;
        final h<? super T, ? extends K> keySelector;
        io.reactivex.disposables.b upstream;
        final h<? super T, ? extends V> valueSelector;

        public GroupByObserver(v<? super b<K, V>> vVar, h<? super T, ? extends K> hVar, h<? super T, ? extends V> hVar2, int i, boolean z) {
            this.downstream = vVar;
            this.keySelector = hVar;
            this.valueSelector = hVar2;
            this.bufferSize = i;
            this.delayError = z;
            this.groups = new ConcurrentHashMap();
            lazySet(1);
        }

        @Override // io.reactivex.v
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            Object obj;
            try {
                Object apply = this.keySelector.apply(t);
                if (apply != null) {
                    obj = apply;
                } else {
                    obj = NULL_KEY;
                }
                a<K, V> aVar = this.groups.get(obj);
                if (aVar == null) {
                    if (!this.cancelled.get()) {
                        aVar = a.a(apply, this.bufferSize, (GroupByObserver<?, Object, T>) this, this.delayError);
                        this.groups.put(obj, aVar);
                        getAndIncrement();
                        this.downstream.onNext(aVar);
                    } else {
                        return;
                    }
                }
                try {
                    aVar.d((a<K, V>) io.reactivex.internal.functions.a.a(this.valueSelector.apply(t), "The value supplied is null"));
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.upstream.dispose();
                    onError(th);
                }
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                this.upstream.dispose();
                onError(th2);
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            ArrayList<a> arrayList = new ArrayList(this.groups.values());
            this.groups.clear();
            for (a aVar : arrayList) {
                aVar.b(th);
            }
            this.downstream.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            ArrayList<a> arrayList = new ArrayList(this.groups.values());
            this.groups.clear();
            for (a aVar : arrayList) {
                aVar.a();
            }
            this.downstream.onComplete();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.cancelled.compareAndSet(false, true) && decrementAndGet() == 0) {
                this.upstream.dispose();
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.cancelled.get();
        }

        public void cancel(K k) {
            if (k == null) {
                k = (K) NULL_KEY;
            }
            this.groups.remove(k);
            if (decrementAndGet() == 0) {
                this.upstream.dispose();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class a<K, T> extends b<K, T> {
        final State<T, K> a;

        public static <T, K> a<K, T> a(K k, int i, GroupByObserver<?, K, T> groupByObserver, boolean z) {
            return new a<>(k, new State(i, groupByObserver, k, z));
        }

        protected a(K k, State<T, K> state) {
            super(k);
            this.a = state;
        }

        /* access modifiers changed from: protected */
        @Override // io.reactivex.q
        public void a(v<? super T> vVar) {
            this.a.subscribe(vVar);
        }

        public void d(T t) {
            this.a.onNext(t);
        }

        public void b(Throwable th) {
            this.a.onError(th);
        }

        public void a() {
            this.a.onComplete();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class State<T, K> extends AtomicInteger implements io.reactivex.disposables.b, t<T> {
        private static final long serialVersionUID = -3852313036005250360L;
        final AtomicReference<v<? super T>> actual = new AtomicReference<>();
        final AtomicBoolean cancelled = new AtomicBoolean();
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final K key;
        final AtomicBoolean once = new AtomicBoolean();
        final GroupByObserver<?, K, T> parent;
        final io.reactivex.internal.queue.a<T> queue;

        State(int i, GroupByObserver<?, K, T> groupByObserver, K k, boolean z) {
            this.queue = new io.reactivex.internal.queue.a<>(i);
            this.parent = groupByObserver;
            this.key = k;
            this.delayError = z;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.cancelled.compareAndSet(false, true) && getAndIncrement() == 0) {
                this.actual.lazySet(null);
                this.parent.cancel(this.key);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.cancelled.get();
        }

        @Override // io.reactivex.t
        public void subscribe(v<? super T> vVar) {
            if (this.once.compareAndSet(false, true)) {
                vVar.onSubscribe(this);
                this.actual.lazySet(vVar);
                if (this.cancelled.get()) {
                    this.actual.lazySet(null);
                } else {
                    drain();
                }
            } else {
                EmptyDisposable.error(new IllegalStateException("Only one Observer allowed!"), vVar);
            }
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
                io.reactivex.internal.queue.a<T> aVar = this.queue;
                boolean z = this.delayError;
                v<? super T> vVar = this.actual.get();
                int i = 1;
                while (true) {
                    if (vVar != null) {
                        while (true) {
                            boolean z2 = this.done;
                            Object poll = aVar.poll();
                            boolean z3 = poll == null;
                            if (!checkTerminated(z2, z3, vVar, z)) {
                                if (z3) {
                                    break;
                                }
                                vVar.onNext(poll);
                            } else {
                                return;
                            }
                        }
                    }
                    i = addAndGet(-i);
                    if (i != 0) {
                        if (vVar == null) {
                            vVar = this.actual.get();
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminated(boolean z, boolean z2, v<? super T> vVar, boolean z3) {
            if (this.cancelled.get()) {
                this.queue.clear();
                this.parent.cancel(this.key);
                this.actual.lazySet(null);
                return true;
            } else if (!z) {
                return false;
            } else {
                if (!z3) {
                    Throwable th = this.error;
                    if (th != null) {
                        this.queue.clear();
                        this.actual.lazySet(null);
                        vVar.onError(th);
                        return true;
                    } else if (!z2) {
                        return false;
                    } else {
                        this.actual.lazySet(null);
                        vVar.onComplete();
                        return true;
                    }
                } else if (!z2) {
                    return false;
                } else {
                    Throwable th2 = this.error;
                    this.actual.lazySet(null);
                    if (th2 != null) {
                        vVar.onError(th2);
                    } else {
                        vVar.onComplete();
                    }
                    return true;
                }
            }
        }
    }
}
