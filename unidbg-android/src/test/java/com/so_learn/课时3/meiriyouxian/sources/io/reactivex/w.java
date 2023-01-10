package io.reactivex;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.schedulers.f;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.concurrent.TimeUnit;

/* compiled from: Scheduler */
public abstract class w {
    static final long a = TimeUnit.MINUTES.toNanos(Long.getLong("rx2.scheduler.drift-tolerance", 15).longValue());

    public abstract c a();

    public void b() {
    }

    public long a(TimeUnit timeUnit) {
        return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public io.reactivex.disposables.b a(Runnable runnable) {
        return a(runnable, 0, TimeUnit.NANOSECONDS);
    }

    public io.reactivex.disposables.b a(Runnable runnable, long j, TimeUnit timeUnit) {
        c a2 = a();
        a aVar = new a(io.reactivex.e.a.a(runnable), a2);
        a2.a(aVar, j, timeUnit);
        return aVar;
    }

    public io.reactivex.disposables.b a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        c a2 = a();
        b bVar = new b(io.reactivex.e.a.a(runnable), a2);
        io.reactivex.disposables.b a3 = a2.a(bVar, j, j2, timeUnit);
        return a3 == EmptyDisposable.INSTANCE ? a3 : bVar;
    }

    /* compiled from: Scheduler */
    public static abstract class c implements io.reactivex.disposables.b {
        public abstract io.reactivex.disposables.b a(Runnable runnable, long j, TimeUnit timeUnit);

        public io.reactivex.disposables.b a(Runnable runnable) {
            return a(runnable, 0, TimeUnit.NANOSECONDS);
        }

        public io.reactivex.disposables.b a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            SequentialDisposable sequentialDisposable = new SequentialDisposable();
            SequentialDisposable sequentialDisposable2 = new SequentialDisposable(sequentialDisposable);
            Runnable a2 = io.reactivex.e.a.a(runnable);
            long nanos = timeUnit.toNanos(j2);
            long a3 = a(TimeUnit.NANOSECONDS);
            io.reactivex.disposables.b a4 = a(new a(a3 + timeUnit.toNanos(j), a2, a3, sequentialDisposable2, nanos), j, timeUnit);
            if (a4 == EmptyDisposable.INSTANCE) {
                return a4;
            }
            sequentialDisposable.replace(a4);
            return sequentialDisposable2;
        }

        public long a(TimeUnit timeUnit) {
            return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        /* compiled from: Scheduler */
        /* access modifiers changed from: package-private */
        public final class a implements Runnable {
            final Runnable a;
            final SequentialDisposable b;
            final long c;
            long d;
            long e;
            long f;

            a(long j, Runnable runnable, long j2, SequentialDisposable sequentialDisposable, long j3) {
                this.a = runnable;
                this.b = sequentialDisposable;
                this.c = j3;
                this.e = j2;
                this.f = j;
            }

            @Override // java.lang.Runnable
            public void run() {
                long j;
                this.a.run();
                if (!this.b.isDisposed()) {
                    long a = c.this.a(TimeUnit.NANOSECONDS);
                    long j2 = this.e;
                    if (w.a + a < j2 || a >= j2 + this.c + w.a) {
                        long j3 = this.c;
                        long j4 = a + j3;
                        long j5 = this.d + 1;
                        this.d = j5;
                        this.f = j4 - (j3 * j5);
                        j = j4;
                    } else {
                        long j6 = this.f;
                        long j7 = this.d + 1;
                        this.d = j7;
                        j = j6 + (j7 * this.c);
                    }
                    this.e = a;
                    this.b.replace(c.this.a(this, j - a, TimeUnit.NANOSECONDS));
                }
            }
        }
    }

    /* compiled from: Scheduler */
    static final class b implements io.reactivex.disposables.b, Runnable {
        final Runnable a;
        final c b;
        volatile boolean c;

        b(Runnable runnable, c cVar) {
            this.a = runnable;
            this.b = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.c) {
                try {
                    this.a.run();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    this.b.dispose();
                    throw ExceptionHelper.a(th);
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.c = true;
            this.b.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.c;
        }
    }

    /* compiled from: Scheduler */
    /* access modifiers changed from: package-private */
    public static final class a implements io.reactivex.disposables.b, Runnable {
        final Runnable a;
        final c b;
        Thread c;

        a(Runnable runnable, c cVar) {
            this.a = runnable;
            this.b = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.c = Thread.currentThread();
            Thread thread = null;
            try {
                this.a.run();
            } finally {
                dispose();
                this.c = thread;
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.c == Thread.currentThread()) {
                c cVar = this.b;
                if (cVar instanceof f) {
                    ((f) cVar).b();
                    return;
                }
            }
            this.b.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.b.isDisposed();
        }
    }
}
