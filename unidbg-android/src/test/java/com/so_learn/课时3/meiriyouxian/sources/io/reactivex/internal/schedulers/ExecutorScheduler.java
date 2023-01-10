package io.reactivex.internal.schedulers;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.w;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ExecutorScheduler extends w {
    static final w d = io.reactivex.f.a.d();
    final boolean b;
    final Executor c;

    @Override // io.reactivex.w
    public w.c a() {
        return new ExecutorWorker(this.c, this.b);
    }

    @Override // io.reactivex.w
    public b a(Runnable runnable) {
        Runnable a2 = io.reactivex.e.a.a(runnable);
        try {
            if (this.c instanceof ExecutorService) {
                ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(a2);
                scheduledDirectTask.setFuture(((ExecutorService) this.c).submit(scheduledDirectTask));
                return scheduledDirectTask;
            } else if (this.b) {
                ExecutorWorker.InterruptibleRunnable interruptibleRunnable = new ExecutorWorker.InterruptibleRunnable(a2, null);
                this.c.execute(interruptibleRunnable);
                return interruptibleRunnable;
            } else {
                ExecutorWorker.BooleanRunnable booleanRunnable = new ExecutorWorker.BooleanRunnable(a2);
                this.c.execute(booleanRunnable);
                return booleanRunnable;
            }
        } catch (RejectedExecutionException e) {
            io.reactivex.e.a.a(e);
            return EmptyDisposable.INSTANCE;
        }
    }

    @Override // io.reactivex.w
    public b a(Runnable runnable, long j, TimeUnit timeUnit) {
        Runnable a2 = io.reactivex.e.a.a(runnable);
        if (this.c instanceof ScheduledExecutorService) {
            try {
                ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(a2);
                scheduledDirectTask.setFuture(((ScheduledExecutorService) this.c).schedule(scheduledDirectTask, j, timeUnit));
                return scheduledDirectTask;
            } catch (RejectedExecutionException e) {
                io.reactivex.e.a.a(e);
                return EmptyDisposable.INSTANCE;
            }
        } else {
            DelayedRunnable delayedRunnable = new DelayedRunnable(a2);
            delayedRunnable.timed.replace(d.a(new a(delayedRunnable), j, timeUnit));
            return delayedRunnable;
        }
    }

    @Override // io.reactivex.w
    public b a(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        if (!(this.c instanceof ScheduledExecutorService)) {
            return super.a(runnable, j, j2, timeUnit);
        }
        try {
            ScheduledDirectPeriodicTask scheduledDirectPeriodicTask = new ScheduledDirectPeriodicTask(io.reactivex.e.a.a(runnable));
            scheduledDirectPeriodicTask.setFuture(((ScheduledExecutorService) this.c).scheduleAtFixedRate(scheduledDirectPeriodicTask, j, j2, timeUnit));
            return scheduledDirectPeriodicTask;
        } catch (RejectedExecutionException e) {
            io.reactivex.e.a.a(e);
            return EmptyDisposable.INSTANCE;
        }
    }

    public static final class ExecutorWorker extends w.c implements Runnable {
        final boolean a;
        final Executor b;
        final MpscLinkedQueue<Runnable> c;
        volatile boolean d;
        final AtomicInteger e = new AtomicInteger();
        final io.reactivex.disposables.a f = new io.reactivex.disposables.a();

        public ExecutorWorker(Executor executor, boolean z) {
            this.b = executor;
            this.c = new MpscLinkedQueue<>();
            this.a = z;
        }

        @Override // io.reactivex.w.c
        public b a(Runnable runnable) {
            b bVar;
            if (this.d) {
                return EmptyDisposable.INSTANCE;
            }
            Runnable a2 = io.reactivex.e.a.a(runnable);
            if (this.a) {
                bVar = new InterruptibleRunnable(a2, this.f);
                this.f.a(bVar);
            } else {
                bVar = new BooleanRunnable(a2);
            }
            this.c.offer(bVar);
            if (this.e.getAndIncrement() == 0) {
                try {
                    this.b.execute(this);
                } catch (RejectedExecutionException e) {
                    this.d = true;
                    this.c.clear();
                    io.reactivex.e.a.a(e);
                    return EmptyDisposable.INSTANCE;
                }
            }
            return bVar;
        }

        @Override // io.reactivex.w.c
        public b a(Runnable runnable, long j, TimeUnit timeUnit) {
            if (j <= 0) {
                return a(runnable);
            }
            if (this.d) {
                return EmptyDisposable.INSTANCE;
            }
            SequentialDisposable sequentialDisposable = new SequentialDisposable();
            SequentialDisposable sequentialDisposable2 = new SequentialDisposable(sequentialDisposable);
            ScheduledRunnable scheduledRunnable = new ScheduledRunnable(new a(sequentialDisposable2, io.reactivex.e.a.a(runnable)), this.f);
            this.f.a(scheduledRunnable);
            Executor executor = this.b;
            if (executor instanceof ScheduledExecutorService) {
                try {
                    scheduledRunnable.setFuture(((ScheduledExecutorService) executor).schedule((Callable) scheduledRunnable, j, timeUnit));
                } catch (RejectedExecutionException e) {
                    this.d = true;
                    io.reactivex.e.a.a(e);
                    return EmptyDisposable.INSTANCE;
                }
            } else {
                scheduledRunnable.setFuture(new b(ExecutorScheduler.d.a(scheduledRunnable, j, timeUnit)));
            }
            sequentialDisposable.replace(scheduledRunnable);
            return sequentialDisposable2;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (!this.d) {
                this.d = true;
                this.f.dispose();
                if (this.e.getAndIncrement() == 0) {
                    this.c.clear();
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.d;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
            r1 = r3.e.addAndGet(-r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
            if (r1 != 0) goto L_0x0003;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0015, code lost:
            if (r3.d == false) goto L_0x001b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
            r0.clear();
         */
        @Override // java.lang.Runnable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r3 = this;
                io.reactivex.internal.queue.MpscLinkedQueue<java.lang.Runnable> r0 = r3.c
                r1 = 1
            L_0x0003:
                boolean r2 = r3.d
                if (r2 == 0) goto L_0x000b
                r0.clear()
                return
            L_0x000b:
                java.lang.Object r2 = r0.poll()
                java.lang.Runnable r2 = (java.lang.Runnable) r2
                if (r2 != 0) goto L_0x0025
                boolean r2 = r3.d
                if (r2 == 0) goto L_0x001b
                r0.clear()
                return
            L_0x001b:
                java.util.concurrent.atomic.AtomicInteger r2 = r3.e
                int r1 = -r1
                int r1 = r2.addAndGet(r1)
                if (r1 != 0) goto L_0x0003
                return
            L_0x0025:
                r2.run()
                boolean r2 = r3.d
                if (r2 == 0) goto L_0x000b
                r0.clear()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.schedulers.ExecutorScheduler.ExecutorWorker.run():void");
        }

        /* access modifiers changed from: package-private */
        public static final class BooleanRunnable extends AtomicBoolean implements b, Runnable {
            private static final long serialVersionUID = -2421395018820541164L;
            final Runnable actual;

            BooleanRunnable(Runnable runnable) {
                this.actual = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (!get()) {
                    boolean z = true;
                    try {
                        this.actual.run();
                    } finally {
                        lazySet(z);
                    }
                }
            }

            @Override // io.reactivex.disposables.b
            public void dispose() {
                lazySet(true);
            }

            @Override // io.reactivex.disposables.b
            public boolean isDisposed() {
                return get();
            }
        }

        final class a implements Runnable {
            private final SequentialDisposable b;
            private final Runnable c;

            a(SequentialDisposable sequentialDisposable, Runnable runnable) {
                this.b = sequentialDisposable;
                this.c = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.b.replace(ExecutorWorker.this.a(this.c));
            }
        }

        /* access modifiers changed from: package-private */
        public static final class InterruptibleRunnable extends AtomicInteger implements b, Runnable {
            static final int FINISHED = 2;
            static final int INTERRUPTED = 4;
            static final int INTERRUPTING = 3;
            static final int READY = 0;
            static final int RUNNING = 1;
            private static final long serialVersionUID = -3603436687413320876L;
            final Runnable run;
            final io.reactivex.internal.disposables.a tasks;
            volatile Thread thread;

            InterruptibleRunnable(Runnable runnable, io.reactivex.internal.disposables.a aVar) {
                this.run = runnable;
                this.tasks = aVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (get() == 0) {
                    this.thread = Thread.currentThread();
                    if (compareAndSet(0, 1)) {
                        try {
                            this.run.run();
                            this.thread = null;
                            if (compareAndSet(1, 2)) {
                                cleanup();
                                return;
                            }
                            while (get() == 3) {
                                Thread.yield();
                            }
                            Thread.interrupted();
                        } catch (Throwable th) {
                            this.thread = null;
                            if (!compareAndSet(1, 2)) {
                                while (get() == 3) {
                                    Thread.yield();
                                }
                                Thread.interrupted();
                            } else {
                                cleanup();
                            }
                            throw th;
                        }
                    } else {
                        this.thread = null;
                    }
                }
            }

            @Override // io.reactivex.disposables.b
            public void dispose() {
                while (true) {
                    int i = get();
                    if (i < 2) {
                        if (i == 0) {
                            if (compareAndSet(0, 4)) {
                                cleanup();
                                return;
                            }
                        } else if (compareAndSet(1, 3)) {
                            Thread thread = this.thread;
                            if (thread != null) {
                                thread.interrupt();
                                this.thread = null;
                            }
                            set(4);
                            cleanup();
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }

            /* access modifiers changed from: package-private */
            public void cleanup() {
                io.reactivex.internal.disposables.a aVar = this.tasks;
                if (aVar != null) {
                    aVar.c(this);
                }
            }

            @Override // io.reactivex.disposables.b
            public boolean isDisposed() {
                return get() >= 2;
            }
        }
    }

    static final class DelayedRunnable extends AtomicReference<Runnable> implements b, Runnable {
        private static final long serialVersionUID = -4101336210206799084L;
        final SequentialDisposable direct = new SequentialDisposable();
        final SequentialDisposable timed = new SequentialDisposable();

        DelayedRunnable(Runnable runnable) {
            super(runnable);
        }

        @Override // java.lang.Runnable
        public void run() {
            Runnable runnable = get();
            if (runnable != null) {
                boolean z = false;
                try {
                    runnable.run();
                } finally {
                    lazySet(z);
                    this.timed.lazySet(DisposableHelper.DISPOSED);
                    this.direct.lazySet(DisposableHelper.DISPOSED);
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == null;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (getAndSet(null) != null) {
                this.timed.dispose();
                this.direct.dispose();
            }
        }

        public Runnable getWrappedRunnable() {
            Runnable runnable = get();
            return runnable != null ? runnable : Functions.b;
        }
    }

    final class a implements Runnable {
        private final DelayedRunnable b;

        a(DelayedRunnable delayedRunnable) {
            this.b = delayedRunnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.b.direct.replace(ExecutorScheduler.this.a(this.b));
        }
    }
}
