package io.reactivex.internal.operators.flowable;

import io.reactivex.c.e;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.internal.util.h;
import io.reactivex.j;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.a.c;
import org.a.d;

public final class FlowableBuffer<T, C extends Collection<? super T>> extends a<T, C> {
    final int c;
    final int d;
    final Callable<C> e;

    @Override // io.reactivex.g
    public void a(c<? super C> cVar) {
        int i = this.c;
        int i2 = this.d;
        if (i == i2) {
            this.b.a((j) new a(cVar, this.c, this.e));
        } else if (i2 > i) {
            this.b.a((j) new PublisherBufferSkipSubscriber(cVar, this.c, this.d, this.e));
        } else {
            this.b.a((j) new PublisherBufferOverlappingSubscriber(cVar, this.c, this.d, this.e));
        }
    }

    static final class a<T, C extends Collection<? super T>> implements j<T>, d {
        final c<? super C> a;
        final Callable<C> b;
        final int c;
        C d;
        d e;
        boolean f;
        int g;

        a(c<? super C> cVar, int i, Callable<C> callable) {
            this.a = cVar;
            this.c = i;
            this.b = callable;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                this.e.request(b.b(j, (long) this.c));
            }
        }

        public void cancel() {
            this.e.cancel();
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.e, dVar)) {
                this.e = dVar;
                this.a.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.f) {
                C c = this.d;
                if (c == null) {
                    try {
                        c = (C) ((Collection) io.reactivex.internal.functions.a.a(this.b.call(), "The bufferSupplier returned a null buffer"));
                        this.d = c;
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        cancel();
                        onError(th);
                        return;
                    }
                }
                c.add(t);
                int i = this.g + 1;
                if (i == this.c) {
                    this.g = 0;
                    this.d = null;
                    this.a.onNext(c);
                    return;
                }
                this.g = i;
            }
        }

        public void onError(Throwable th) {
            if (this.f) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.f = true;
            this.a.onError(th);
        }

        public void onComplete() {
            if (!this.f) {
                this.f = true;
                C c = this.d;
                if (c != null && !c.isEmpty()) {
                    this.a.onNext(c);
                }
                this.a.onComplete();
            }
        }
    }

    static final class PublisherBufferSkipSubscriber<T, C extends Collection<? super T>> extends AtomicInteger implements j<T>, d {
        private static final long serialVersionUID = -5616169793639412593L;
        C buffer;
        final Callable<C> bufferSupplier;
        boolean done;
        final c<? super C> downstream;
        int index;
        final int size;
        final int skip;
        d upstream;

        PublisherBufferSkipSubscriber(c<? super C> cVar, int i, int i2, Callable<C> callable) {
            this.downstream = cVar;
            this.size = i;
            this.skip = i2;
            this.bufferSupplier = callable;
        }

        public void request(long j) {
            if (!SubscriptionHelper.validate(j)) {
                return;
            }
            if (get() != 0 || !compareAndSet(0, 1)) {
                this.upstream.request(b.b((long) this.skip, j));
                return;
            }
            this.upstream.request(b.a(b.b(j, (long) this.size), b.b((long) (this.skip - this.size), j - 1)));
        }

        public void cancel() {
            this.upstream.cancel();
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                C c = this.buffer;
                int i = this.index;
                int i2 = i + 1;
                if (i == 0) {
                    try {
                        c = (C) ((Collection) io.reactivex.internal.functions.a.a(this.bufferSupplier.call(), "The bufferSupplier returned a null buffer"));
                        this.buffer = c;
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        cancel();
                        onError(th);
                        return;
                    }
                }
                if (c != null) {
                    c.add(t);
                    if (c.size() == this.size) {
                        this.buffer = null;
                        this.downstream.onNext(c);
                    }
                }
                if (i2 == this.skip) {
                    i2 = 0;
                }
                this.index = i2;
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.done = true;
            this.buffer = null;
            this.downstream.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                C c = this.buffer;
                this.buffer = null;
                if (c != null) {
                    this.downstream.onNext(c);
                }
                this.downstream.onComplete();
            }
        }
    }

    static final class PublisherBufferOverlappingSubscriber<T, C extends Collection<? super T>> extends AtomicLong implements e, j<T>, d {
        private static final long serialVersionUID = -7370244972039324525L;
        final Callable<C> bufferSupplier;
        final ArrayDeque<C> buffers = new ArrayDeque<>();
        volatile boolean cancelled;
        boolean done;
        final c<? super C> downstream;
        int index;
        final AtomicBoolean once = new AtomicBoolean();
        long produced;
        final int size;
        final int skip;
        d upstream;

        PublisherBufferOverlappingSubscriber(c<? super C> cVar, int i, int i2, Callable<C> callable) {
            this.downstream = cVar;
            this.size = i;
            this.skip = i2;
            this.bufferSupplier = callable;
        }

        @Override // io.reactivex.c.e
        public boolean getAsBoolean() {
            return this.cancelled;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j) && !h.a(j, this.downstream, this.buffers, this, this)) {
                if (this.once.get() || !this.once.compareAndSet(false, true)) {
                    this.upstream.request(b.b((long) this.skip, j));
                    return;
                }
                this.upstream.request(b.a((long) this.size, b.b((long) this.skip, j - 1)));
            }
        }

        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: java.util.ArrayDeque<C extends java.util.Collection<? super T>> */
        /* JADX WARN: Multi-variable type inference failed */
        public void onNext(T t) {
            if (!this.done) {
                ArrayDeque<C> arrayDeque = this.buffers;
                int i = this.index;
                int i2 = i + 1;
                if (i == 0) {
                    try {
                        arrayDeque.offer((Collection) io.reactivex.internal.functions.a.a(this.bufferSupplier.call(), "The bufferSupplier returned a null buffer"));
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        cancel();
                        onError(th);
                        return;
                    }
                }
                Collection collection = (Collection) arrayDeque.peek();
                if (collection != null && collection.size() + 1 == this.size) {
                    arrayDeque.poll();
                    collection.add(t);
                    this.produced++;
                    this.downstream.onNext(collection);
                }
                Iterator it2 = arrayDeque.iterator();
                while (it2.hasNext()) {
                    ((Collection) it2.next()).add(t);
                }
                if (i2 == this.skip) {
                    i2 = 0;
                }
                this.index = i2;
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.done = true;
            this.buffers.clear();
            this.downstream.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                long j = this.produced;
                if (j != 0) {
                    b.c(this, j);
                }
                h.a(this.downstream, this.buffers, this, this);
            }
        }
    }
}
