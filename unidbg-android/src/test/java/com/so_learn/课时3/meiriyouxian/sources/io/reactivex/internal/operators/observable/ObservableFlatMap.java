package io.reactivex.internal.operators.observable;

import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.a.c;
import io.reactivex.internal.a.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.t;
import io.reactivex.v;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableFlatMap<T, U> extends a<T, U> {
    final h<? super T, ? extends t<? extends U>> b;
    final boolean c;
    final int d;
    final int e;

    public ObservableFlatMap(t<T> tVar, h<? super T, ? extends t<? extends U>> hVar, boolean z, int i, int i2) {
        super(tVar);
        this.b = hVar;
        this.c = z;
        this.d = i;
        this.e = i2;
    }

    @Override // io.reactivex.q
    public void a(v<? super U> vVar) {
        if (!ObservableScalarXMap.a(this.a, vVar, this.b)) {
            this.a.subscribe(new MergeObserver(vVar, this.b, this.c, this.d, this.e));
        }
    }

    static final class MergeObserver<T, U> extends AtomicInteger implements b, v<T> {
        static final InnerObserver<?, ?>[] CANCELLED = new InnerObserver[0];
        static final InnerObserver<?, ?>[] EMPTY = new InnerObserver[0];
        private static final long serialVersionUID = -2117620485640801370L;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final v<? super U> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        long lastId;
        int lastIndex;
        final h<? super T, ? extends t<? extends U>> mapper;
        final int maxConcurrency;
        final AtomicReference<InnerObserver<?, ?>[]> observers;
        volatile g<U> queue;
        Queue<t<? extends U>> sources;
        long uniqueId;
        b upstream;
        int wip;

        MergeObserver(v<? super U> vVar, h<? super T, ? extends t<? extends U>> hVar, boolean z, int i, int i2) {
            this.downstream = vVar;
            this.mapper = hVar;
            this.delayErrors = z;
            this.maxConcurrency = i;
            this.bufferSize = i2;
            if (i != Integer.MAX_VALUE) {
                this.sources = new ArrayDeque(i);
            }
            this.observers = new AtomicReference<>(EMPTY);
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            if (!this.done) {
                try {
                    t<? extends U> tVar = (t) a.a(this.mapper.apply(t), "The mapper returned a null ObservableSource");
                    if (this.maxConcurrency != Integer.MAX_VALUE) {
                        synchronized (this) {
                            if (this.wip == this.maxConcurrency) {
                                this.sources.offer(tVar);
                                return;
                            }
                            this.wip++;
                        }
                    }
                    subscribeInner(tVar);
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.upstream.dispose();
                    onError(th);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void subscribeInner(t<? extends U> tVar) {
            t<? extends U> poll;
            while (tVar instanceof Callable) {
                if (tryEmitScalar((Callable) tVar) && this.maxConcurrency != Integer.MAX_VALUE) {
                    boolean z = false;
                    synchronized (this) {
                        poll = this.sources.poll();
                        if (poll == null) {
                            this.wip--;
                            z = true;
                        }
                    }
                    if (z) {
                        drain();
                        return;
                    }
                    tVar = poll;
                } else {
                    return;
                }
            }
            long j = this.uniqueId;
            this.uniqueId = 1 + j;
            InnerObserver innerObserver = new InnerObserver(this, j);
            if (addInner(innerObserver)) {
                tVar.subscribe(innerObserver);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservableFlatMap$InnerObserver<?, ?>[]> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public boolean addInner(InnerObserver<T, U> innerObserver) {
            InnerObserver<?, ?>[] innerObserverArr;
            InnerObserver[] innerObserverArr2;
            do {
                innerObserverArr = this.observers.get();
                if (innerObserverArr == CANCELLED) {
                    innerObserver.dispose();
                    return false;
                }
                int length = innerObserverArr.length;
                innerObserverArr2 = new InnerObserver[(length + 1)];
                System.arraycopy(innerObserverArr, 0, innerObserverArr2, 0, length);
                innerObserverArr2[length] = innerObserver;
            } while (!this.observers.compareAndSet(innerObserverArr, innerObserverArr2));
            return true;
        }

        /* access modifiers changed from: package-private */
        public void removeInner(InnerObserver<T, U> innerObserver) {
            InnerObserver<?, ?>[] innerObserverArr;
            InnerObserver<?, ?>[] innerObserverArr2;
            do {
                innerObserverArr = this.observers.get();
                int length = innerObserverArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (innerObserverArr[i2] == innerObserver) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            innerObserverArr2 = EMPTY;
                        } else {
                            InnerObserver<?, ?>[] innerObserverArr3 = new InnerObserver[(length - 1)];
                            System.arraycopy(innerObserverArr, 0, innerObserverArr3, 0, i);
                            System.arraycopy(innerObserverArr, i + 1, innerObserverArr3, i, (length - i) - 1);
                            innerObserverArr2 = innerObserverArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.observers.compareAndSet(innerObserverArr, innerObserverArr2));
        }

        /* access modifiers changed from: package-private */
        public boolean tryEmitScalar(Callable<? extends U> callable) {
            try {
                Object call = callable.call();
                if (call == null) {
                    return true;
                }
                if (get() != 0 || !compareAndSet(0, 1)) {
                    g<U> gVar = this.queue;
                    if (gVar == null) {
                        int i = this.maxConcurrency;
                        if (i == Integer.MAX_VALUE) {
                            gVar = new io.reactivex.internal.queue.a<>(this.bufferSize);
                        } else {
                            gVar = new SpscArrayQueue(i);
                        }
                        this.queue = gVar;
                    }
                    if (!gVar.offer(call)) {
                        onError(new IllegalStateException("Scalar queue full?!"));
                        return true;
                    } else if (getAndIncrement() != 0) {
                        return false;
                    }
                } else {
                    this.downstream.onNext(call);
                    if (decrementAndGet() == 0) {
                        return true;
                    }
                }
                drainLoop();
                return true;
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.errors.addThrowable(th);
                drain();
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public void tryEmit(U u, InnerObserver<T, U> innerObserver) {
            if (get() != 0 || !compareAndSet(0, 1)) {
                io.reactivex.internal.a.h hVar = innerObserver.queue;
                if (hVar == null) {
                    hVar = new io.reactivex.internal.queue.a(this.bufferSize);
                    innerObserver.queue = hVar;
                }
                hVar.offer(u);
                if (getAndIncrement() != 0) {
                    return;
                }
            } else {
                this.downstream.onNext(u);
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            drainLoop();
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.e.a.a(th);
            } else if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
            } else {
                io.reactivex.e.a.a(th);
            }
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            Throwable terminate;
            if (!this.cancelled) {
                this.cancelled = true;
                if (disposeAll() && (terminate = this.errors.terminate()) != null && terminate != ExceptionHelper.a) {
                    io.reactivex.e.a.a(terminate);
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.cancelled;
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
            int i2;
            v<? super U> vVar = this.downstream;
            int i3 = 1;
            while (!checkTerminate()) {
                g<U> gVar = this.queue;
                if (gVar != null) {
                    while (!checkTerminate()) {
                        Object poll = gVar.poll();
                        if (poll != null) {
                            vVar.onNext(poll);
                        }
                    }
                    return;
                }
                boolean z = this.done;
                g<U> gVar2 = this.queue;
                InnerObserver<?, ?>[] innerObserverArr = this.observers.get();
                int length = innerObserverArr.length;
                if (this.maxConcurrency != Integer.MAX_VALUE) {
                    synchronized (this) {
                        i = this.sources.size();
                    }
                } else {
                    i = 0;
                }
                if (!z || !((gVar2 == null || gVar2.isEmpty()) && length == 0 && i == 0)) {
                    if (length != 0) {
                        long j = this.lastId;
                        int i4 = this.lastIndex;
                        if (length <= i4 || innerObserverArr[i4].id != j) {
                            if (length <= i4) {
                                i4 = 0;
                            }
                            int i5 = i4;
                            for (int i6 = 0; i6 < length && innerObserverArr[i5].id != j; i6++) {
                                i5++;
                                if (i5 == length) {
                                    i5 = 0;
                                }
                            }
                            this.lastIndex = i5;
                            this.lastId = innerObserverArr[i5].id;
                            i4 = i5;
                        }
                        i2 = 0;
                        for (int i7 = 0; i7 < length; i7++) {
                            if (!checkTerminate()) {
                                InnerObserver<?, ?> innerObserver = innerObserverArr[i4];
                                io.reactivex.internal.a.h<U> hVar = innerObserver.queue;
                                if (hVar != null) {
                                    while (true) {
                                        try {
                                            Object poll2 = hVar.poll();
                                            if (poll2 == null) {
                                                break;
                                            }
                                            vVar.onNext(poll2);
                                            if (checkTerminate()) {
                                                return;
                                            }
                                        } catch (Throwable th) {
                                            io.reactivex.exceptions.a.b(th);
                                            innerObserver.dispose();
                                            this.errors.addThrowable(th);
                                            if (!checkTerminate()) {
                                                removeInner(innerObserver);
                                                i2++;
                                                i4++;
                                                if (i4 != length) {
                                                }
                                            } else {
                                                return;
                                            }
                                        }
                                    }
                                }
                                boolean z2 = innerObserver.done;
                                io.reactivex.internal.a.h<U> hVar2 = innerObserver.queue;
                                if (z2 && (hVar2 == null || hVar2.isEmpty())) {
                                    removeInner(innerObserver);
                                    if (!checkTerminate()) {
                                        i2++;
                                    } else {
                                        return;
                                    }
                                }
                                i4++;
                                if (i4 != length) {
                                }
                                i4 = 0;
                            } else {
                                return;
                            }
                        }
                        this.lastIndex = i4;
                        this.lastId = innerObserverArr[i4].id;
                    } else {
                        i2 = 0;
                    }
                    if (i2 == 0) {
                        i3 = addAndGet(-i3);
                        if (i3 == 0) {
                            return;
                        }
                    } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                        while (true) {
                            int i8 = i2 - 1;
                            if (i2 == 0) {
                                continue;
                                break;
                            }
                            synchronized (this) {
                                t<? extends U> poll3 = this.sources.poll();
                                if (poll3 == null) {
                                    this.wip--;
                                } else {
                                    subscribeInner(poll3);
                                }
                            }
                            i2 = i8;
                        }
                    } else {
                        continue;
                    }
                } else {
                    Throwable terminate = this.errors.terminate();
                    if (terminate == ExceptionHelper.a) {
                        return;
                    }
                    if (terminate == null) {
                        vVar.onComplete();
                        return;
                    } else {
                        vVar.onError(terminate);
                        return;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminate() {
            if (this.cancelled) {
                return true;
            }
            Throwable th = this.errors.get();
            if (this.delayErrors || th == null) {
                return false;
            }
            disposeAll();
            Throwable terminate = this.errors.terminate();
            if (terminate != ExceptionHelper.a) {
                this.downstream.onError(terminate);
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean disposeAll() {
            InnerObserver<?, ?>[] andSet;
            this.upstream.dispose();
            InnerObserver<?, ?>[] innerObserverArr = this.observers.get();
            InnerObserver<?, ?>[] innerObserverArr2 = CANCELLED;
            if (innerObserverArr == innerObserverArr2 || (andSet = this.observers.getAndSet(innerObserverArr2)) == CANCELLED) {
                return false;
            }
            for (InnerObserver<?, ?> innerObserver : andSet) {
                innerObserver.dispose();
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class InnerObserver<T, U> extends AtomicReference<b> implements v<U> {
        private static final long serialVersionUID = -4606175640614850599L;
        volatile boolean done;
        int fusionMode;
        final long id;
        final MergeObserver<T, U> parent;
        volatile io.reactivex.internal.a.h<U> queue;

        InnerObserver(MergeObserver<T, U> mergeObserver, long j) {
            this.id = j;
            this.parent = mergeObserver;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar) && (bVar instanceof c)) {
                c cVar = (c) bVar;
                int requestFusion = cVar.requestFusion(7);
                if (requestFusion == 1) {
                    this.fusionMode = requestFusion;
                    this.queue = cVar;
                    this.done = true;
                    this.parent.drain();
                } else if (requestFusion == 2) {
                    this.fusionMode = requestFusion;
                    this.queue = cVar;
                }
            }
        }

        @Override // io.reactivex.v
        public void onNext(U u) {
            if (this.fusionMode == 0) {
                this.parent.tryEmit(u, this);
            } else {
                this.parent.drain();
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.parent.errors.addThrowable(th)) {
                if (!this.parent.delayErrors) {
                    this.parent.disposeAll();
                }
                this.done = true;
                this.parent.drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
