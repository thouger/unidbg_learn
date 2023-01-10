package io.reactivex.internal.schedulers;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.a;
import io.reactivex.w;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* compiled from: NewThreadWorker */
public class f extends w.c implements b {
    volatile boolean a;
    private final ScheduledExecutorService b;

    public f(ThreadFactory threadFactory) {
        this.b = i.a(threadFactory);
    }

    @Override // io.reactivex.w.c
    public b a(Runnable runnable) {
        return a(runnable, 0, null);
    }

    @Override // io.reactivex.w.c
    public b a(Runnable runnable, long j, TimeUnit timeUnit) {
        if (this.a) {
            return EmptyDisposable.INSTANCE;
        }
        return a(runnable, j, timeUnit, (a) null);
    }

    public b b(Runnable runnable, long j, TimeUnit timeUnit) {
        Future<?> future;
        ScheduledDirectTask scheduledDirectTask = new ScheduledDirectTask(io.reactivex.e.a.a(runnable));
        if (j <= 0) {
            try {
                future = this.b.submit(scheduledDirectTask);
            } catch (RejectedExecutionException e) {
                io.reactivex.e.a.a(e);
                return EmptyDisposable.INSTANCE;
            }
        } else {
            future = this.b.schedule(scheduledDirectTask, j, timeUnit);
        }
        scheduledDirectTask.setFuture(future);
        return scheduledDirectTask;
    }

    public b b(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        Future<?> future;
        Runnable a = io.reactivex.e.a.a(runnable);
        if (j2 <= 0) {
            c cVar = new c(a, this.b);
            if (j <= 0) {
                try {
                    future = this.b.submit(cVar);
                } catch (RejectedExecutionException e) {
                    io.reactivex.e.a.a(e);
                    return EmptyDisposable.INSTANCE;
                }
            } else {
                future = this.b.schedule(cVar, j, timeUnit);
            }
            cVar.a(future);
            return cVar;
        }
        ScheduledDirectPeriodicTask scheduledDirectPeriodicTask = new ScheduledDirectPeriodicTask(a);
        try {
            scheduledDirectPeriodicTask.setFuture(this.b.scheduleAtFixedRate(scheduledDirectPeriodicTask, j, j2, timeUnit));
            return scheduledDirectPeriodicTask;
        } catch (RejectedExecutionException e2) {
            io.reactivex.e.a.a(e2);
            return EmptyDisposable.INSTANCE;
        }
    }

    public ScheduledRunnable a(Runnable runnable, long j, TimeUnit timeUnit, a aVar) {
        Future<?> future;
        ScheduledRunnable scheduledRunnable = new ScheduledRunnable(io.reactivex.e.a.a(runnable), aVar);
        if (aVar != null && !aVar.a(scheduledRunnable)) {
            return scheduledRunnable;
        }
        if (j <= 0) {
            try {
                future = this.b.submit((Callable) scheduledRunnable);
            } catch (RejectedExecutionException e) {
                if (aVar != null) {
                    aVar.b(scheduledRunnable);
                }
                io.reactivex.e.a.a(e);
            }
        } else {
            future = this.b.schedule((Callable) scheduledRunnable, j, timeUnit);
        }
        scheduledRunnable.setFuture(future);
        return scheduledRunnable;
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        if (!this.a) {
            this.a = true;
            this.b.shutdownNow();
        }
    }

    public void b() {
        if (!this.a) {
            this.a = true;
            this.b.shutdown();
        }
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.a;
    }
}
