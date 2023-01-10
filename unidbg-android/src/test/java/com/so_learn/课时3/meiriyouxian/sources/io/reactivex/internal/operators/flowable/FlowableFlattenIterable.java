package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.exceptions.a;
import io.reactivex.internal.a.e;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableFlattenIterable<T, R> extends a<T, R> {
    final h<? super T, ? extends Iterable<? extends R>> c;
    final int d;

    @Override // io.reactivex.g
    public void a(c<? super R> cVar) {
        if (this.b instanceof Callable) {
            try {
                Object call = ((Callable) this.b).call();
                if (call == null) {
                    EmptySubscription.complete(cVar);
                    return;
                }
                try {
                    FlowableFromIterable.a(cVar, ((Iterable) this.c.apply(call)).iterator());
                } catch (Throwable th) {
                    a.b(th);
                    EmptySubscription.error(th, cVar);
                }
            } catch (Throwable th2) {
                a.b(th2);
                EmptySubscription.error(th2, cVar);
            }
        } else {
            this.b.a((j) new FlattenIterableSubscriber(cVar, this.c, this.d));
        }
    }

    static final class FlattenIterableSubscriber<T, R> extends BasicIntQueueSubscription<R> implements j<T> {
        private static final long serialVersionUID = -3096000382929934955L;
        volatile boolean cancelled;
        int consumed;
        Iterator<? extends R> current;
        volatile boolean done;
        final c<? super R> downstream;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        int fusionMode;
        final int limit;
        final h<? super T, ? extends Iterable<? extends R>> mapper;
        final int prefetch;
        io.reactivex.internal.a.h<T> queue;
        final AtomicLong requested = new AtomicLong();
        d upstream;

        FlattenIterableSubscriber(c<? super R> cVar, h<? super T, ? extends Iterable<? extends R>> hVar, int i) {
            this.downstream = cVar;
            this.mapper = hVar;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                if (dVar instanceof e) {
                    e eVar = (e) dVar;
                    int requestFusion = eVar.requestFusion(3);
                    if (requestFusion == 1) {
                        this.fusionMode = requestFusion;
                        this.queue = eVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        return;
                    } else if (requestFusion == 2) {
                        this.fusionMode = requestFusion;
                        this.queue = eVar;
                        this.downstream.onSubscribe(this);
                        dVar.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                this.downstream.onSubscribe(this);
                dVar.request((long) this.prefetch);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.fusionMode != 0 || this.queue.offer(t)) {
                    drain();
                } else {
                    onError(new MissingBackpressureException("Queue is full?!"));
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done || !ExceptionHelper.a(this.error, th)) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
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
                this.upstream.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:69:0x0122, code lost:
            if (r6 == null) goto L_0x012d;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drain() {
            /*
            // Method dump skipped, instructions count: 304
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableFlattenIterable.FlattenIterableSubscriber.drain():void");
        }

        /* access modifiers changed from: package-private */
        public void consumedOne(boolean z) {
            if (z) {
                int i = this.consumed + 1;
                if (i == this.limit) {
                    this.consumed = 0;
                    this.upstream.request((long) i);
                    return;
                }
                this.consumed = i;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminated(boolean z, boolean z2, c<?> cVar, io.reactivex.internal.a.h<?> hVar) {
            if (this.cancelled) {
                this.current = null;
                hVar.clear();
                return true;
            } else if (!z) {
                return false;
            } else {
                if (this.error.get() != null) {
                    Throwable a = ExceptionHelper.a(this.error);
                    this.current = null;
                    hVar.clear();
                    cVar.onError(a);
                    return true;
                } else if (!z2) {
                    return false;
                } else {
                    cVar.onComplete();
                    return true;
                }
            }
        }

        @Override // io.reactivex.internal.a.h
        public void clear() {
            this.current = null;
            this.queue.clear();
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return this.current == null && this.queue.isEmpty();
        }

        @Override // io.reactivex.internal.a.h
        public R poll() throws Exception {
            Iterator<T> it2 = this.current;
            while (true) {
                if (it2 == null) {
                    Object poll = this.queue.poll();
                    if (poll != null) {
                        it2 = ((Iterable) this.mapper.apply(poll)).iterator();
                        if (it2.hasNext()) {
                            this.current = it2;
                            break;
                        }
                        it2 = null;
                    } else {
                        return null;
                    }
                } else {
                    break;
                }
            }
            R r = (R) io.reactivex.internal.functions.a.a(it2.next(), "The iterator returned a null value");
            if (!it2.hasNext()) {
                this.current = null;
            }
            return r;
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            return ((i & 1) == 0 || this.fusionMode != 1) ? 0 : 1;
        }
    }
}
