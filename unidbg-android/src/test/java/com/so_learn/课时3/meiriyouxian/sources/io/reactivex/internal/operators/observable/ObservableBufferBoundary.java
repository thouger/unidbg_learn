package io.reactivex.internal.operators.observable;

import io.reactivex.c.h;
import io.reactivex.disposables.a;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.q;
import io.reactivex.t;
import io.reactivex.v;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableBufferBoundary<T, U extends Collection<? super T>, Open, Close> extends a<T, U> {
    final Callable<U> b;
    final t<? extends Open> c;
    final h<? super Open, ? extends t<? extends Close>> d;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super U> vVar) {
        BufferBoundaryObserver bufferBoundaryObserver = new BufferBoundaryObserver(vVar, this.c, this.d, this.b);
        vVar.onSubscribe(bufferBoundaryObserver);
        this.a.subscribe(bufferBoundaryObserver);
    }

    static final class BufferBoundaryObserver<T, C extends Collection<? super T>, Open, Close> extends AtomicInteger implements b, v<T> {
        private static final long serialVersionUID = -8466418554264089604L;
        final h<? super Open, ? extends t<? extends Close>> bufferClose;
        final t<? extends Open> bufferOpen;
        final Callable<C> bufferSupplier;
        Map<Long, C> buffers = new LinkedHashMap();
        volatile boolean cancelled;
        volatile boolean done;
        final v<? super C> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        long index;
        final a observers = new a();
        final io.reactivex.internal.queue.a<C> queue = new io.reactivex.internal.queue.a<>(q.c());
        final AtomicReference<b> upstream = new AtomicReference<>();

        BufferBoundaryObserver(v<? super C> vVar, t<? extends Open> tVar, h<? super Open, ? extends t<? extends Close>> hVar, Callable<C> callable) {
            this.downstream = vVar;
            this.bufferSupplier = callable;
            this.bufferOpen = tVar;
            this.bufferClose = hVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this.upstream, bVar)) {
                BufferOpenObserver bufferOpenObserver = new BufferOpenObserver(this);
                this.observers.a(bufferOpenObserver);
                this.bufferOpen.subscribe(bufferOpenObserver);
            }
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            synchronized (this) {
                Map<Long, C> map = this.buffers;
                if (map != null) {
                    for (C c : map.values()) {
                        c.add(t);
                    }
                }
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.observers.dispose();
                synchronized (this) {
                    this.buffers = null;
                }
                this.done = true;
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.observers.dispose();
            synchronized (this) {
                Map<Long, C> map = this.buffers;
                if (map != null) {
                    for (C c : map.values()) {
                        this.queue.offer(c);
                    }
                    this.buffers = null;
                    this.done = true;
                    drain();
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (DisposableHelper.dispose(this.upstream)) {
                this.cancelled = true;
                this.observers.dispose();
                synchronized (this) {
                    this.buffers = null;
                }
                if (getAndIncrement() != 0) {
                    this.queue.clear();
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v2, resolved type: java.util.Map<java.lang.Long, C extends java.util.Collection<? super T>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public void open(Open open) {
            try {
                Collection collection = (Collection) io.reactivex.internal.functions.a.a(this.bufferSupplier.call(), "The bufferSupplier returned a null Collection");
                t tVar = (t) io.reactivex.internal.functions.a.a(this.bufferClose.apply(open), "The bufferClose returned a null ObservableSource");
                long j = this.index;
                this.index = 1 + j;
                synchronized (this) {
                    Map<Long, C> map = this.buffers;
                    if (map != 0) {
                        map.put(Long.valueOf(j), collection);
                        BufferCloseObserver bufferCloseObserver = new BufferCloseObserver(this, j);
                        this.observers.a(bufferCloseObserver);
                        tVar.subscribe(bufferCloseObserver);
                    }
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                DisposableHelper.dispose(this.upstream);
                onError(th);
            }
        }

        /* access modifiers changed from: package-private */
        public void openComplete(BufferOpenObserver<Open> bufferOpenObserver) {
            this.observers.c(bufferOpenObserver);
            if (this.observers.b() == 0) {
                DisposableHelper.dispose(this.upstream);
                this.done = true;
                drain();
            }
        }

        /* access modifiers changed from: package-private */
        public void close(BufferCloseObserver<T, C> bufferCloseObserver, long j) {
            boolean z;
            this.observers.c(bufferCloseObserver);
            if (this.observers.b() == 0) {
                DisposableHelper.dispose(this.upstream);
                z = true;
            } else {
                z = false;
            }
            synchronized (this) {
                if (this.buffers != null) {
                    this.queue.offer(this.buffers.remove(Long.valueOf(j)));
                } else {
                    return;
                }
            }
            if (z) {
                this.done = true;
            }
            drain();
        }

        /* access modifiers changed from: package-private */
        public void boundaryError(b bVar, Throwable th) {
            DisposableHelper.dispose(this.upstream);
            this.observers.c(bVar);
            onError(th);
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                v<? super C> vVar = this.downstream;
                io.reactivex.internal.queue.a<C> aVar = this.queue;
                int i = 1;
                while (!this.cancelled) {
                    boolean z = this.done;
                    if (!z || this.errors.get() == null) {
                        Collection collection = (Collection) aVar.poll();
                        boolean z2 = collection == null;
                        if (z && z2) {
                            vVar.onComplete();
                            return;
                        } else if (z2) {
                            i = addAndGet(-i);
                            if (i == 0) {
                                return;
                            }
                        } else {
                            vVar.onNext(collection);
                        }
                    } else {
                        aVar.clear();
                        vVar.onError(this.errors.terminate());
                        return;
                    }
                }
                aVar.clear();
            }
        }

        static final class BufferOpenObserver<Open> extends AtomicReference<b> implements b, v<Open> {
            private static final long serialVersionUID = -8498650778633225126L;
            final BufferBoundaryObserver<?, ?, Open, ?> parent;

            BufferOpenObserver(BufferBoundaryObserver<?, ?, Open, ?> bufferBoundaryObserver) {
                this.parent = bufferBoundaryObserver;
            }

            @Override // io.reactivex.v
            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            @Override // io.reactivex.v
            public void onNext(Open open) {
                this.parent.open(open);
            }

            @Override // io.reactivex.v
            public void onError(Throwable th) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.boundaryError(this, th);
            }

            @Override // io.reactivex.v
            public void onComplete() {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.openComplete(this);
            }

            @Override // io.reactivex.disposables.b
            public void dispose() {
                DisposableHelper.dispose(this);
            }

            @Override // io.reactivex.disposables.b
            public boolean isDisposed() {
                return get() == DisposableHelper.DISPOSED;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class BufferCloseObserver<T, C extends Collection<? super T>> extends AtomicReference<b> implements b, v<Object> {
        private static final long serialVersionUID = -8498650778633225126L;
        final long index;
        final BufferBoundaryObserver<T, C, ?, ?> parent;

        BufferCloseObserver(BufferBoundaryObserver<T, C, ?, ?> bufferBoundaryObserver, long j) {
            this.parent = bufferBoundaryObserver;
            this.index = j;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(Object obj) {
            b bVar = get();
            if (bVar != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                bVar.dispose();
                this.parent.close(this, this.index);
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            if (get() != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.boundaryError(this, th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            if (get() != DisposableHelper.DISPOSED) {
                lazySet(DisposableHelper.DISPOSED);
                this.parent.close(this, this.index);
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }
    }
}
