package io.reactivex.processors;

import io.reactivex.internal.queue.a;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class UnicastProcessor<T> extends a<T> {
    final a<T> b;
    final AtomicReference<Runnable> c;
    final boolean d;
    volatile boolean e;
    Throwable f;
    final AtomicReference<c<? super T>> g;
    volatile boolean h;
    final AtomicBoolean i;
    final BasicIntQueueSubscription<T> j;
    final AtomicLong k;
    boolean l;

    public static <T> UnicastProcessor<T> h() {
        return new UnicastProcessor<>(a());
    }

    public static <T> UnicastProcessor<T> b(int i) {
        return new UnicastProcessor<>(i);
    }

    public static <T> UnicastProcessor<T> a(int i, Runnable runnable) {
        io.reactivex.internal.functions.a.a(runnable, "onTerminate");
        return new UnicastProcessor<>(i, runnable);
    }

    UnicastProcessor(int i) {
        this(i, null, true);
    }

    UnicastProcessor(int i, Runnable runnable) {
        this(i, runnable, true);
    }

    UnicastProcessor(int i, Runnable runnable, boolean z) {
        this.b = new a<>(io.reactivex.internal.functions.a.a(i, "capacityHint"));
        this.c = new AtomicReference<>(runnable);
        this.d = z;
        this.g = new AtomicReference<>();
        this.i = new AtomicBoolean();
        this.j = new UnicastQueueSubscription();
        this.k = new AtomicLong();
    }

    /* access modifiers changed from: package-private */
    public void i() {
        Runnable andSet = this.c.getAndSet(null);
        if (andSet != null) {
            andSet.run();
        }
    }

    /* access modifiers changed from: package-private */
    public void b(c<? super T> cVar) {
        int i;
        long j;
        a<T> aVar = this.b;
        boolean z = true;
        boolean z2 = !this.d;
        int i2 = 1;
        while (true) {
            long j2 = this.k.get();
            long j3 = 0;
            while (true) {
                i = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
                if (i == 0) {
                    j = j3;
                    break;
                }
                boolean z3 = this.e;
                Object poll = aVar.poll();
                boolean z4 = poll == null ? z : false;
                j = j3;
                if (!a(z2, z3, z4, cVar, aVar)) {
                    if (z4) {
                        break;
                    }
                    cVar.onNext(poll);
                    j3 = 1 + j;
                    z = true;
                } else {
                    return;
                }
            }
            if (i != 0 || !a(z2, this.e, aVar.isEmpty(), cVar, aVar)) {
                if (!(j == 0 || j2 == Long.MAX_VALUE)) {
                    this.k.addAndGet(-j);
                }
                i2 = this.j.addAndGet(-i2);
                if (i2 != 0) {
                    z = true;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void c(c<? super T> cVar) {
        a<T> aVar = this.b;
        int i = 1;
        boolean z = !this.d;
        while (!this.h) {
            boolean z2 = this.e;
            if (!z || !z2 || this.f == null) {
                cVar.onNext((Object) null);
                if (z2) {
                    this.g.lazySet(null);
                    Throwable th = this.f;
                    if (th != null) {
                        cVar.onError(th);
                        return;
                    } else {
                        cVar.onComplete();
                        return;
                    }
                } else {
                    i = this.j.addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            } else {
                aVar.clear();
                this.g.lazySet(null);
                cVar.onError(this.f);
                return;
            }
        }
        aVar.clear();
        this.g.lazySet(null);
    }

    /* access modifiers changed from: package-private */
    public void j() {
        if (this.j.getAndIncrement() == 0) {
            int i = 1;
            c<? super T> cVar = this.g.get();
            while (cVar == null) {
                i = this.j.addAndGet(-i);
                if (i != 0) {
                    cVar = this.g.get();
                } else {
                    return;
                }
            }
            if (this.l) {
                c(cVar);
            } else {
                b(cVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(boolean z, boolean z2, boolean z3, c<? super T> cVar, a<T> aVar) {
        if (this.h) {
            aVar.clear();
            this.g.lazySet(null);
            return true;
        } else if (!z2) {
            return false;
        } else {
            if (z && this.f != null) {
                aVar.clear();
                this.g.lazySet(null);
                cVar.onError(this.f);
                return true;
            } else if (!z3) {
                return false;
            } else {
                Throwable th = this.f;
                this.g.lazySet(null);
                if (th != null) {
                    cVar.onError(th);
                } else {
                    cVar.onComplete();
                }
                return true;
            }
        }
    }

    @Override // io.reactivex.j
    public void onSubscribe(d dVar) {
        if (this.e || this.h) {
            dVar.cancel();
        } else {
            dVar.request(Long.MAX_VALUE);
        }
    }

    public void onNext(T t) {
        io.reactivex.internal.functions.a.a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!this.e && !this.h) {
            this.b.offer(t);
            j();
        }
    }

    public void onError(Throwable th) {
        io.reactivex.internal.functions.a.a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.e || this.h) {
            io.reactivex.e.a.a(th);
            return;
        }
        this.f = th;
        this.e = true;
        i();
        j();
    }

    public void onComplete() {
        if (!this.e && !this.h) {
            this.e = true;
            i();
            j();
        }
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        if (this.i.get() || !this.i.compareAndSet(false, true)) {
            EmptySubscription.error(new IllegalStateException("This processor allows only a single Subscriber"), cVar);
            return;
        }
        cVar.onSubscribe(this.j);
        this.g.set(cVar);
        if (this.h) {
            this.g.lazySet(null);
        } else {
            j();
        }
    }

    final class UnicastQueueSubscription extends BasicIntQueueSubscription<T> {
        private static final long serialVersionUID = -4896760517184205454L;

        UnicastQueueSubscription() {
        }

        @Override // io.reactivex.internal.a.h
        public T poll() {
            return (T) UnicastProcessor.this.b.poll();
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return UnicastProcessor.this.b.isEmpty();
        }

        @Override // io.reactivex.internal.a.h
        public void clear() {
            UnicastProcessor.this.b.clear();
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            UnicastProcessor.this.l = true;
            return 2;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(UnicastProcessor.this.k, j);
                UnicastProcessor.this.j();
            }
        }

        public void cancel() {
            if (!UnicastProcessor.this.h) {
                UnicastProcessor unicastProcessor = UnicastProcessor.this;
                unicastProcessor.h = true;
                unicastProcessor.i();
                if (!UnicastProcessor.this.l && UnicastProcessor.this.j.getAndIncrement() == 0) {
                    UnicastProcessor.this.b.clear();
                    UnicastProcessor.this.g.lazySet(null);
                }
            }
        }
    }
}
