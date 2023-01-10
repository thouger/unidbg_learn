package io.reactivex.internal.schedulers;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.w;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: IoScheduler */
public final class d extends w {
    static final RxThreadFactory b;
    static final RxThreadFactory c;
    static final c d = new c(new RxThreadFactory("RxCachedThreadSchedulerShutdown"));
    static final a g = new a(0, null, b);
    private static final long h = Long.getLong("rx2.io-keep-alive-time", 60).longValue();
    private static final TimeUnit i = TimeUnit.SECONDS;
    final ThreadFactory e;
    final AtomicReference<a> f;

    static {
        d.dispose();
        int max = Math.max(1, Math.min(10, Integer.getInteger("rx2.io-priority", 5).intValue()));
        b = new RxThreadFactory("RxCachedThreadScheduler", max);
        c = new RxThreadFactory("RxCachedWorkerPoolEvictor", max);
        g.d();
    }

    /* compiled from: IoScheduler */
    /* access modifiers changed from: package-private */
    public static final class a implements Runnable {
        final io.reactivex.disposables.a a;
        private final long b;
        private final ConcurrentLinkedQueue<c> c;
        private final ScheduledExecutorService d;
        private final Future<?> e;
        private final ThreadFactory f;

        a(long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
            ScheduledFuture<?> scheduledFuture;
            this.b = timeUnit != null ? timeUnit.toNanos(j) : 0;
            this.c = new ConcurrentLinkedQueue<>();
            this.a = new io.reactivex.disposables.a();
            this.f = threadFactory;
            ScheduledExecutorService scheduledExecutorService = null;
            if (timeUnit != null) {
                scheduledExecutorService = Executors.newScheduledThreadPool(1, d.c);
                long j2 = this.b;
                scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(this, j2, j2, TimeUnit.NANOSECONDS);
            } else {
                scheduledFuture = null;
            }
            this.d = scheduledExecutorService;
            this.e = scheduledFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            b();
        }

        /* access modifiers changed from: package-private */
        public c a() {
            if (this.a.isDisposed()) {
                return d.d;
            }
            while (!this.c.isEmpty()) {
                c poll = this.c.poll();
                if (poll != null) {
                    return poll;
                }
            }
            c cVar = new c(this.f);
            this.a.a(cVar);
            return cVar;
        }

        /* access modifiers changed from: package-private */
        public void a(c cVar) {
            cVar.a(c() + this.b);
            this.c.offer(cVar);
        }

        /* access modifiers changed from: package-private */
        public void b() {
            if (!this.c.isEmpty()) {
                long c = c();
                Iterator<c> it2 = this.c.iterator();
                while (it2.hasNext()) {
                    c next = it2.next();
                    if (next.a() > c) {
                        return;
                    }
                    if (this.c.remove(next)) {
                        this.a.b(next);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public long c() {
            return System.nanoTime();
        }

        /* access modifiers changed from: package-private */
        public void d() {
            this.a.dispose();
            Future<?> future = this.e;
            if (future != null) {
                future.cancel(true);
            }
            ScheduledExecutorService scheduledExecutorService = this.d;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdownNow();
            }
        }
    }

    public d() {
        this(b);
    }

    public d(ThreadFactory threadFactory) {
        this.e = threadFactory;
        this.f = new AtomicReference<>(g);
        b();
    }

    @Override // io.reactivex.w
    public void b() {
        a aVar = new a(h, i, this.e);
        if (!this.f.compareAndSet(g, aVar)) {
            aVar.d();
        }
    }

    @Override // io.reactivex.w
    public w.c a() {
        return new b(this.f.get());
    }

    /* compiled from: IoScheduler */
    static final class b extends w.c {
        final AtomicBoolean a = new AtomicBoolean();
        private final io.reactivex.disposables.a b;
        private final a c;
        private final c d;

        b(a aVar) {
            this.c = aVar;
            this.b = new io.reactivex.disposables.a();
            this.d = aVar.a();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.a.compareAndSet(false, true)) {
                this.b.dispose();
                this.c.a(this.d);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.a.get();
        }

        @Override // io.reactivex.w.c
        public io.reactivex.disposables.b a(Runnable runnable, long j, TimeUnit timeUnit) {
            if (this.b.isDisposed()) {
                return EmptyDisposable.INSTANCE;
            }
            return this.d.a(runnable, j, timeUnit, this.b);
        }
    }

    /* compiled from: IoScheduler */
    /* access modifiers changed from: package-private */
    public static final class c extends f {
        private long b = 0;

        c(ThreadFactory threadFactory) {
            super(threadFactory);
        }

        public long a() {
            return this.b;
        }

        public void a(long j) {
            this.b = j;
        }
    }
}
