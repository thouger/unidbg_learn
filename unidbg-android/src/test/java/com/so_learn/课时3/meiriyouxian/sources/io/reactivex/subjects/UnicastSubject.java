package io.reactivex.subjects;

import io.reactivex.disposables.b;
import io.reactivex.internal.a.h;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.internal.queue.a;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class UnicastSubject<T> extends b<T> {
    final a<T> a;
    final AtomicReference<v<? super T>> b;
    final AtomicReference<Runnable> c;
    final boolean d;
    volatile boolean e;
    volatile boolean f;
    Throwable g;
    final AtomicBoolean h;
    final BasicIntQueueDisposable<T> i;
    boolean j;

    public static <T> UnicastSubject<T> a() {
        return new UnicastSubject<>(c(), true);
    }

    public static <T> UnicastSubject<T> a(int i, Runnable runnable) {
        return new UnicastSubject<>(i, runnable, true);
    }

    UnicastSubject(int i, boolean z) {
        this.a = new a<>(io.reactivex.internal.functions.a.a(i, "capacityHint"));
        this.c = new AtomicReference<>();
        this.d = z;
        this.b = new AtomicReference<>();
        this.h = new AtomicBoolean();
        this.i = new UnicastQueueDisposable();
    }

    UnicastSubject(int i, Runnable runnable, boolean z) {
        this.a = new a<>(io.reactivex.internal.functions.a.a(i, "capacityHint"));
        this.c = new AtomicReference<>(io.reactivex.internal.functions.a.a(runnable, "onTerminate"));
        this.d = z;
        this.b = new AtomicReference<>();
        this.h = new AtomicBoolean();
        this.i = new UnicastQueueDisposable();
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        if (this.h.get() || !this.h.compareAndSet(false, true)) {
            EmptyDisposable.error(new IllegalStateException("Only a single observer allowed."), vVar);
            return;
        }
        vVar.onSubscribe(this.i);
        this.b.lazySet(vVar);
        if (this.e) {
            this.b.lazySet(null);
        } else {
            k();
        }
    }

    /* access modifiers changed from: package-private */
    public void j() {
        Runnable runnable = this.c.get();
        if (runnable != null && this.c.compareAndSet(runnable, null)) {
            runnable.run();
        }
    }

    @Override // io.reactivex.v
    public void onSubscribe(b bVar) {
        if (this.f || this.e) {
            bVar.dispose();
        }
    }

    @Override // io.reactivex.v
    public void onNext(T t) {
        io.reactivex.internal.functions.a.a((Object) t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!this.f && !this.e) {
            this.a.offer(t);
            k();
        }
    }

    @Override // io.reactivex.v
    public void onError(Throwable th) {
        io.reactivex.internal.functions.a.a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.f || this.e) {
            io.reactivex.e.a.a(th);
            return;
        }
        this.g = th;
        this.f = true;
        j();
        k();
    }

    @Override // io.reactivex.v
    public void onComplete() {
        if (!this.f && !this.e) {
            this.f = true;
            j();
            k();
        }
    }

    /* access modifiers changed from: package-private */
    public void c(v<? super T> vVar) {
        a<T> aVar = this.a;
        boolean z = !this.d;
        boolean z2 = true;
        int i = 1;
        while (!this.e) {
            boolean z3 = this.f;
            Object poll = this.a.poll();
            boolean z4 = poll == null;
            if (z3) {
                if (z && z2) {
                    if (!a(aVar, vVar)) {
                        z2 = false;
                    } else {
                        return;
                    }
                }
                if (z4) {
                    e(vVar);
                    return;
                }
            }
            if (z4) {
                i = this.i.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            } else {
                vVar.onNext(poll);
            }
        }
        this.b.lazySet(null);
        aVar.clear();
    }

    /* access modifiers changed from: package-private */
    public void d(v<? super T> vVar) {
        a<T> aVar = this.a;
        int i = 1;
        boolean z = !this.d;
        while (!this.e) {
            boolean z2 = this.f;
            if (!z || !z2 || !a(aVar, vVar)) {
                vVar.onNext(null);
                if (z2) {
                    e(vVar);
                    return;
                }
                i = this.i.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            } else {
                return;
            }
        }
        this.b.lazySet(null);
        aVar.clear();
    }

    /* access modifiers changed from: package-private */
    public void e(v<? super T> vVar) {
        this.b.lazySet(null);
        Throwable th = this.g;
        if (th != null) {
            vVar.onError(th);
        } else {
            vVar.onComplete();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(h<T> hVar, v<? super T> vVar) {
        Throwable th = this.g;
        if (th == null) {
            return false;
        }
        this.b.lazySet(null);
        hVar.clear();
        vVar.onError(th);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void k() {
        if (this.i.getAndIncrement() == 0) {
            v<? super T> vVar = this.b.get();
            int i = 1;
            while (vVar == null) {
                i = this.i.addAndGet(-i);
                if (i != 0) {
                    vVar = this.b.get();
                } else {
                    return;
                }
            }
            if (this.j) {
                d(vVar);
            } else {
                c((v) vVar);
            }
        }
    }

    final class UnicastQueueDisposable extends BasicIntQueueDisposable<T> {
        private static final long serialVersionUID = 7926949470189395511L;

        UnicastQueueDisposable() {
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            UnicastSubject.this.j = true;
            return 2;
        }

        @Override // io.reactivex.internal.a.h
        public T poll() throws Exception {
            return (T) UnicastSubject.this.a.poll();
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return UnicastSubject.this.a.isEmpty();
        }

        @Override // io.reactivex.internal.a.h
        public void clear() {
            UnicastSubject.this.a.clear();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (!UnicastSubject.this.e) {
                UnicastSubject unicastSubject = UnicastSubject.this;
                unicastSubject.e = true;
                unicastSubject.j();
                UnicastSubject.this.b.lazySet(null);
                if (UnicastSubject.this.i.getAndIncrement() == 0) {
                    UnicastSubject.this.b.lazySet(null);
                    UnicastSubject.this.a.clear();
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return UnicastSubject.this.e;
        }
    }
}
