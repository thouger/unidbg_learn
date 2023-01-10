package io.reactivex.internal.schedulers;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.schedulers.h;
import io.reactivex.w;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: ComputationScheduler */
public final class a extends w implements h {
    static final b b = new b(0, c);
    static final RxThreadFactory c = new RxThreadFactory("RxComputationThreadPool", Math.max(1, Math.min(10, Integer.getInteger("rx2.computation-priority", 5).intValue())), true);
    static final int d = a(Runtime.getRuntime().availableProcessors(), Integer.getInteger("rx2.computation-threads", 0).intValue());
    static final c e = new c(new RxThreadFactory("RxComputationShutdown"));
    final ThreadFactory f;
    final AtomicReference<b> g;

    static int a(int i, int i2) {
        return (i2 <= 0 || i2 > i) ? i : i2;
    }

    static {
        e.dispose();
        b.b();
    }

    /* compiled from: ComputationScheduler */
    /* access modifiers changed from: package-private */
    public static final class b implements h {
        final int a;
        final c[] b;
        long c;

        b(int i, ThreadFactory threadFactory) {
            this.a = i;
            this.b = new c[i];
            for (int i2 = 0; i2 < i; i2++) {
                this.b[i2] = new c(threadFactory);
            }
        }

        public c a() {
            int i = this.a;
            if (i == 0) {
                return a.e;
            }
            c[] cVarArr = this.b;
            long j = this.c;
            this.c = 1 + j;
            return cVarArr[(int) (j % ((long) i))];
        }

        public void b() {
            for (c cVar : this.b) {
                cVar.dispose();
            }
        }

        @Override // io.reactivex.internal.schedulers.h
        public void a(int i, h.a aVar) {
            int i2 = this.a;
            if (i2 == 0) {
                for (int i3 = 0; i3 < i; i3++) {
                    aVar.a(i3, a.e);
                }
                return;
            }
            int i4 = ((int) this.c) % i2;
            for (int i5 = 0; i5 < i; i5++) {
                aVar.a(i5, new C0212a(this.b[i4]));
                i4++;
                if (i4 == i2) {
                    i4 = 0;
                }
            }
            this.c = (long) i4;
        }
    }

    public a() {
        this(c);
    }

    public a(ThreadFactory threadFactory) {
        this.f = threadFactory;
        this.g = new AtomicReference<>(b);
        b();
    }

    @Override // io.reactivex.w
    public w.c a() {
        return new C0212a(this.g.get().a());
    }

    @Override // io.reactivex.internal.schedulers.h
    public void a(int i, h.a aVar) {
        io.reactivex.internal.functions.a.a(i, "number > 0 required");
        this.g.get().a(i, aVar);
    }

    @Override // io.reactivex.w
    public io.reactivex.disposables.b a(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.g.get().a().b(runnable, j, timeUnit);
    }

    @Override // io.reactivex.w
    public io.reactivex.disposables.b a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.g.get().a().b(runnable, j, j2, timeUnit);
    }

    @Override // io.reactivex.w
    public void b() {
        b bVar = new b(d, this.f);
        if (!this.g.compareAndSet(b, bVar)) {
            bVar.b();
        }
    }

    /* compiled from: ComputationScheduler */
    /* access modifiers changed from: package-private */
    /* renamed from: io.reactivex.internal.schedulers.a$a  reason: collision with other inner class name */
    public static final class C0212a extends w.c {
        volatile boolean a;
        private final io.reactivex.internal.disposables.b b = new io.reactivex.internal.disposables.b();
        private final io.reactivex.disposables.a c = new io.reactivex.disposables.a();
        private final io.reactivex.internal.disposables.b d = new io.reactivex.internal.disposables.b();
        private final c e;

        C0212a(c cVar) {
            this.e = cVar;
            this.d.a(this.b);
            this.d.a(this.c);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (!this.a) {
                this.a = true;
                this.d.dispose();
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.a;
        }

        @Override // io.reactivex.w.c
        public io.reactivex.disposables.b a(Runnable runnable) {
            if (this.a) {
                return EmptyDisposable.INSTANCE;
            }
            return this.e.a(runnable, 0, TimeUnit.MILLISECONDS, this.b);
        }

        @Override // io.reactivex.w.c
        public io.reactivex.disposables.b a(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.a) {
                return EmptyDisposable.INSTANCE;
            }
            return this.e.a(runnable, j, timeUnit, this.c);
        }
    }

    /* compiled from: ComputationScheduler */
    /* access modifiers changed from: package-private */
    public static final class c extends f {
        c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }
}
