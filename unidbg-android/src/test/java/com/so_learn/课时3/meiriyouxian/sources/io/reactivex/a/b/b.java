package io.reactivex.a.b;

import android.os.Handler;
import android.os.Message;
import io.reactivex.disposables.c;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;

/* compiled from: HandlerScheduler */
final class b extends w {
    private final Handler b;
    private final boolean c;

    b(Handler handler, boolean z) {
        this.b = handler;
        this.c = z;
    }

    @Override // io.reactivex.w
    public io.reactivex.disposables.b a(Runnable runnable, long j, TimeUnit timeUnit) {
        if (runnable == null) {
            throw new NullPointerException("run == null");
        } else if (timeUnit != null) {
            RunnableC0204b bVar = new RunnableC0204b(this.b, io.reactivex.e.a.a(runnable));
            Message obtain = Message.obtain(this.b, bVar);
            if (this.c) {
                obtain.setAsynchronous(true);
            }
            this.b.sendMessageDelayed(obtain, timeUnit.toMillis(j));
            return bVar;
        } else {
            throw new NullPointerException("unit == null");
        }
    }

    @Override // io.reactivex.w
    public w.c a() {
        return new a(this.b, this.c);
    }

    /* compiled from: HandlerScheduler */
    private static final class a extends w.c {
        private final Handler a;
        private final boolean b;
        private volatile boolean c;

        a(Handler handler, boolean z) {
            this.a = handler;
            this.b = z;
        }

        @Override // io.reactivex.w.c
        public io.reactivex.disposables.b a(Runnable runnable, long j, TimeUnit timeUnit) {
            if (runnable == null) {
                throw new NullPointerException("run == null");
            } else if (timeUnit == null) {
                throw new NullPointerException("unit == null");
            } else if (this.c) {
                return c.a();
            } else {
                RunnableC0204b bVar = new RunnableC0204b(this.a, io.reactivex.e.a.a(runnable));
                Message obtain = Message.obtain(this.a, bVar);
                obtain.obj = this;
                if (this.b) {
                    obtain.setAsynchronous(true);
                }
                this.a.sendMessageDelayed(obtain, timeUnit.toMillis(j));
                if (!this.c) {
                    return bVar;
                }
                this.a.removeCallbacks(bVar);
                return c.a();
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.c = true;
            this.a.removeCallbacksAndMessages(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.c;
        }
    }

    /* compiled from: HandlerScheduler */
    /* renamed from: io.reactivex.a.b.b$b  reason: collision with other inner class name */
    private static final class RunnableC0204b implements io.reactivex.disposables.b, Runnable {
        private final Handler a;
        private final Runnable b;
        private volatile boolean c;

        RunnableC0204b(Handler handler, Runnable runnable) {
            this.a = handler;
            this.b = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.b.run();
            } catch (Throwable th) {
                io.reactivex.e.a.a(th);
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.a.removeCallbacks(this);
            this.c = true;
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.c;
        }
    }
}
