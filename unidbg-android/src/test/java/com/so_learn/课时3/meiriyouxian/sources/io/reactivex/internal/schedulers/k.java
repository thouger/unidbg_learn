package io.reactivex.internal.schedulers;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.w;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: TrampolineScheduler */
public final class k extends w {
    private static final k b = new k();

    public static k c() {
        return b;
    }

    @Override // io.reactivex.w
    public w.c a() {
        return new c();
    }

    k() {
    }

    @Override // io.reactivex.w
    public io.reactivex.disposables.b a(Runnable runnable) {
        io.reactivex.e.a.a(runnable).run();
        return EmptyDisposable.INSTANCE;
    }

    @Override // io.reactivex.w
    public io.reactivex.disposables.b a(Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j);
            io.reactivex.e.a.a(runnable).run();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            io.reactivex.e.a.a(e);
        }
        return EmptyDisposable.INSTANCE;
    }

    /* compiled from: TrampolineScheduler */
    static final class c extends w.c implements io.reactivex.disposables.b {
        final PriorityBlockingQueue<b> a = new PriorityBlockingQueue<>();
        final AtomicInteger b = new AtomicInteger();
        volatile boolean c;
        private final AtomicInteger d = new AtomicInteger();

        c() {
        }

        @Override // io.reactivex.w.c
        public io.reactivex.disposables.b a(Runnable runnable) {
            return a(runnable, a(TimeUnit.MILLISECONDS));
        }

        @Override // io.reactivex.w.c
        public io.reactivex.disposables.b a(Runnable runnable, long j, TimeUnit timeUnit) {
            long a2 = a(TimeUnit.MILLISECONDS) + timeUnit.toMillis(j);
            return a(new a(runnable, this, a2), a2);
        }

        /* access modifiers changed from: package-private */
        public io.reactivex.disposables.b a(Runnable runnable, long j) {
            if (this.c) {
                return EmptyDisposable.INSTANCE;
            }
            b bVar = new b(runnable, Long.valueOf(j), this.b.incrementAndGet());
            this.a.add(bVar);
            if (this.d.getAndIncrement() != 0) {
                return io.reactivex.disposables.c.a(new a(bVar));
            }
            int i = 1;
            while (!this.c) {
                b poll = this.a.poll();
                if (poll == null) {
                    i = this.d.addAndGet(-i);
                    if (i == 0) {
                        return EmptyDisposable.INSTANCE;
                    }
                } else if (!poll.d) {
                    poll.a.run();
                }
            }
            this.a.clear();
            return EmptyDisposable.INSTANCE;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.c = true;
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.c;
        }

        /* compiled from: TrampolineScheduler */
        /* access modifiers changed from: package-private */
        public final class a implements Runnable {
            final b a;

            a(b bVar) {
                this.a = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.a.d = true;
                c.this.a.remove(this.a);
            }
        }
    }

    /* compiled from: TrampolineScheduler */
    /* access modifiers changed from: package-private */
    public static final class b implements Comparable<b> {
        final Runnable a;
        final long b;
        final int c;
        volatile boolean d;

        b(Runnable runnable, Long l, int i) {
            this.a = runnable;
            this.b = l.longValue();
            this.c = i;
        }

        /* renamed from: a */
        public int compareTo(b bVar) {
            int a = io.reactivex.internal.functions.a.a(this.b, bVar.b);
            return a == 0 ? io.reactivex.internal.functions.a.a(this.c, bVar.c) : a;
        }
    }

    /* compiled from: TrampolineScheduler */
    static final class a implements Runnable {
        private final Runnable a;
        private final c b;
        private final long c;

        a(Runnable runnable, c cVar, long j) {
            this.a = runnable;
            this.b = cVar;
            this.c = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.b.c) {
                long a = this.b.a(TimeUnit.MILLISECONDS);
                long j = this.c;
                if (j > a) {
                    try {
                        Thread.sleep(j - a);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        io.reactivex.e.a.a(e);
                        return;
                    }
                }
                if (!this.b.c) {
                    this.a.run();
                }
            }
        }
    }
}
