package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.g;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.j;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableBufferBoundary<T, U extends Collection<? super T>, Open, Close> extends a<T, U> {
    final Callable<U> c;
    final b<? extends Open> d;
    final h<? super Open, ? extends b<? extends Close>> e;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super U> cVar) {
        BufferBoundarySubscriber bufferBoundarySubscriber = new BufferBoundarySubscriber(cVar, this.d, this.e, this.c);
        cVar.onSubscribe(bufferBoundarySubscriber);
        this.b.a((j) bufferBoundarySubscriber);
    }

    static final class BufferBoundarySubscriber<T, C extends Collection<? super T>, Open, Close> extends AtomicInteger implements j<T>, d {
        private static final long serialVersionUID = -8466418554264089604L;
        final h<? super Open, ? extends b<? extends Close>> bufferClose;
        final b<? extends Open> bufferOpen;
        final Callable<C> bufferSupplier;
        Map<Long, C> buffers = new LinkedHashMap();
        volatile boolean cancelled;
        volatile boolean done;
        final c<? super C> downstream;
        long emitted;
        final AtomicThrowable errors = new AtomicThrowable();
        long index;
        final a<C> queue = new a<>(g.a());
        final AtomicLong requested = new AtomicLong();
        final io.reactivex.disposables.a subscribers = new io.reactivex.disposables.a();
        final AtomicReference<d> upstream = new AtomicReference<>();

        BufferBoundarySubscriber(c<? super C> cVar, b<? extends Open> bVar, h<? super Open, ? extends b<? extends Close>> hVar, Callable<C> callable) {
            this.downstream = cVar;
            this.bufferSupplier = callable;
            this.bufferOpen = bVar;
            this.bufferClose = hVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.setOnce(this.upstream, dVar)) {
                BufferOpenSubscriber bufferOpenSubscriber = new BufferOpenSubscriber(this);
                this.subscribers.a(bufferOpenSubscriber);
                this.bufferOpen.subscribe(bufferOpenSubscriber);
                dVar.request(Long.MAX_VALUE);
            }
        }

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

        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                this.subscribers.dispose();
                synchronized (this) {
                    this.buffers = null;
                }
                this.done = true;
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        public void onComplete() {
            this.subscribers.dispose();
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

        public void request(long j) {
            io.reactivex.internal.util.b.a(this.requested, j);
            drain();
        }

        public void cancel() {
            if (SubscriptionHelper.cancel(this.upstream)) {
                this.cancelled = true;
                this.subscribers.dispose();
                synchronized (this) {
                    this.buffers = null;
                }
                if (getAndIncrement() != 0) {
                    this.queue.clear();
                }
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r3v2, resolved type: java.util.Map<java.lang.Long, C extends java.util.Collection<? super T>> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public void open(Open open) {
            try {
                Collection collection = (Collection) io.reactivex.internal.functions.a.a(this.bufferSupplier.call(), "The bufferSupplier returned a null Collection");
                b bVar = (b) io.reactivex.internal.functions.a.a(this.bufferClose.apply(open), "The bufferClose returned a null Publisher");
                long j = this.index;
                this.index = 1 + j;
                synchronized (this) {
                    Map<Long, C> map = this.buffers;
                    if (map != 0) {
                        map.put(Long.valueOf(j), collection);
                        BufferCloseSubscriber bufferCloseSubscriber = new BufferCloseSubscriber(this, j);
                        this.subscribers.a(bufferCloseSubscriber);
                        bVar.subscribe(bufferCloseSubscriber);
                    }
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                SubscriptionHelper.cancel(this.upstream);
                onError(th);
            }
        }

        /* access modifiers changed from: package-private */
        public void openComplete(BufferOpenSubscriber<Open> bufferOpenSubscriber) {
            this.subscribers.c(bufferOpenSubscriber);
            if (this.subscribers.b() == 0) {
                SubscriptionHelper.cancel(this.upstream);
                this.done = true;
                drain();
            }
        }

        /* access modifiers changed from: package-private */
        public void close(BufferCloseSubscriber<T, C> bufferCloseSubscriber, long j) {
            boolean z;
            this.subscribers.c(bufferCloseSubscriber);
            if (this.subscribers.b() == 0) {
                SubscriptionHelper.cancel(this.upstream);
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
        public void boundaryError(io.reactivex.disposables.b bVar, Throwable th) {
            SubscriptionHelper.cancel(this.upstream);
            this.subscribers.c(bVar);
            onError(th);
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            int i;
            if (getAndIncrement() == 0) {
                long j = this.emitted;
                c<? super C> cVar = this.downstream;
                a<C> aVar = this.queue;
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
                        } else {
                            boolean z = this.done;
                            if (!z || this.errors.get() == null) {
                                Collection collection = (Collection) aVar.poll();
                                boolean z2 = collection == null;
                                if (z && z2) {
                                    cVar.onComplete();
                                    return;
                                } else if (z2) {
                                    break;
                                } else {
                                    cVar.onNext(collection);
                                    j++;
                                }
                            } else {
                                aVar.clear();
                                cVar.onError(this.errors.terminate());
                                return;
                            }
                        }
                    }
                    if (i == 0) {
                        if (this.cancelled) {
                            aVar.clear();
                            return;
                        } else if (this.done) {
                            if (this.errors.get() != null) {
                                aVar.clear();
                                cVar.onError(this.errors.terminate());
                                return;
                            } else if (aVar.isEmpty()) {
                                cVar.onComplete();
                                return;
                            }
                        }
                    }
                    this.emitted = j;
                    i2 = addAndGet(-i2);
                } while (i2 != 0);
            }
        }

        static final class BufferOpenSubscriber<Open> extends AtomicReference<d> implements io.reactivex.disposables.b, j<Open> {
            private static final long serialVersionUID = -8498650778633225126L;
            final BufferBoundarySubscriber<?, ?, Open, ?> parent;

            BufferOpenSubscriber(BufferBoundarySubscriber<?, ?, Open, ?> bufferBoundarySubscriber) {
                this.parent = bufferBoundarySubscriber;
            }

            @Override // io.reactivex.j
            public void onSubscribe(d dVar) {
                SubscriptionHelper.setOnce(this, dVar, Long.MAX_VALUE);
            }

            public void onNext(Open open) {
                this.parent.open(open);
            }

            public void onError(Throwable th) {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.boundaryError(this, th);
            }

            public void onComplete() {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.openComplete(this);
            }

            @Override // io.reactivex.disposables.b
            public void dispose() {
                SubscriptionHelper.cancel(this);
            }

            @Override // io.reactivex.disposables.b
            public boolean isDisposed() {
                return get() == SubscriptionHelper.CANCELLED;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class BufferCloseSubscriber<T, C extends Collection<? super T>> extends AtomicReference<d> implements io.reactivex.disposables.b, j<Object> {
        private static final long serialVersionUID = -8498650778633225126L;
        final long index;
        final BufferBoundarySubscriber<T, C, ?, ?> parent;

        BufferCloseSubscriber(BufferBoundarySubscriber<T, C, ?, ?> bufferBoundarySubscriber, long j) {
            this.parent = bufferBoundarySubscriber;
            this.index = j;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.setOnce(this, dVar, Long.MAX_VALUE);
        }

        public void onNext(Object obj) {
            SubscriptionHelper subscriptionHelper = (d) get();
            if (subscriptionHelper != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                subscriptionHelper.cancel();
                this.parent.close(this, this.index);
            }
        }

        public void onError(Throwable th) {
            if (get() != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.boundaryError(this, th);
                return;
            }
            io.reactivex.e.a.a(th);
        }

        public void onComplete() {
            if (get() != SubscriptionHelper.CANCELLED) {
                lazySet(SubscriptionHelper.CANCELLED);
                this.parent.close(this, this.index);
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == SubscriptionHelper.CANCELLED;
        }
    }
}
