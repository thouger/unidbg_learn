package io.reactivex.internal.schedulers;

import io.reactivex.disposables.b;
import io.reactivex.e.a;
import io.reactivex.internal.functions.Functions;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: InstantPeriodicTask */
final class c implements b, Callable<Void> {
    static final FutureTask<Void> f = new FutureTask<>(Functions.b, null);
    final Runnable a;
    final AtomicReference<Future<?>> b = new AtomicReference<>();
    final AtomicReference<Future<?>> c = new AtomicReference<>();
    final ExecutorService d;
    Thread e;

    c(Runnable runnable, ExecutorService executorService) {
        this.a = runnable;
        this.d = executorService;
    }

    /* renamed from: a */
    public Void call() throws Exception {
        this.e = Thread.currentThread();
        try {
            this.a.run();
            b(this.d.submit(this));
            this.e = null;
        } catch (Throwable th) {
            this.e = null;
            a.a(th);
        }
        return null;
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        Future<?> andSet = this.c.getAndSet(f);
        boolean z = true;
        if (!(andSet == null || andSet == f)) {
            andSet.cancel(this.e != Thread.currentThread());
        }
        Future<?> andSet2 = this.b.getAndSet(f);
        if (andSet2 != null && andSet2 != f) {
            if (this.e == Thread.currentThread()) {
                z = false;
            }
            andSet2.cancel(z);
        }
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.c.get() == f;
    }

    /* access modifiers changed from: package-private */
    public void a(Future<?> future) {
        Future<?> future2;
        do {
            future2 = this.c.get();
            if (future2 == f) {
                future.cancel(this.e != Thread.currentThread());
                return;
            }
        } while (!this.c.compareAndSet(future2, future));
    }

    /* access modifiers changed from: package-private */
    public void b(Future<?> future) {
        Future<?> future2;
        do {
            future2 = this.b.get();
            if (future2 == f) {
                future.cancel(this.e != Thread.currentThread());
                return;
            }
        } while (!this.b.compareAndSet(future2, future));
    }
}
