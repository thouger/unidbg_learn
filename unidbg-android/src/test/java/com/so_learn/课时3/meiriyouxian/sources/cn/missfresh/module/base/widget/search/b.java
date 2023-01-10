package cn.missfresh.module.base.widget.search;

import android.os.Handler;
import android.os.Message;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.ref.WeakReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: WeakHandler */
public class b {
    final a a = new a(this.d, null);
    private final Handler.Callback b = null;
    private final HandlerC0035b c = new HandlerC0035b();
    private Lock d = new ReentrantLock();

    public b() {
        AppMethodBeat.i(24301, false);
        AppMethodBeat.o(24301);
    }

    private c b(Runnable runnable) {
        AppMethodBeat.i(24306, false);
        if (runnable != null) {
            a aVar = new a(this.d, runnable);
            this.a.a(aVar);
            c cVar = aVar.b;
            AppMethodBeat.o(24306);
            return cVar;
        }
        NullPointerException nullPointerException = new NullPointerException("Runnable can't be null");
        AppMethodBeat.o(24306);
        throw nullPointerException;
    }

    public final boolean a(Runnable runnable, long j) {
        AppMethodBeat.i(24309, false);
        boolean postDelayed = this.c.postDelayed(b(runnable), j);
        AppMethodBeat.o(24309);
        return postDelayed;
    }

    public final void a(Runnable runnable) {
        AppMethodBeat.i(24311, false);
        c a2 = this.a.a(runnable);
        if (a2 != null) {
            this.c.removeCallbacks(a2);
        }
        AppMethodBeat.o(24311);
    }

    /* compiled from: WeakHandler */
    /* renamed from: cn.missfresh.module.base.widget.search.b$b  reason: collision with other inner class name */
    private static class HandlerC0035b extends Handler {
        private final WeakReference<Handler.Callback> a = null;

        HandlerC0035b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AppMethodBeat.i(24299, false);
            WeakReference<Handler.Callback> weakReference = this.a;
            if (weakReference == null) {
                AppMethodBeat.o(24299);
                return;
            }
            Handler.Callback callback = weakReference.get();
            if (callback == null) {
                AppMethodBeat.o(24299);
                return;
            }
            callback.handleMessage(message);
            AppMethodBeat.o(24299);
        }
    }

    /* compiled from: WeakHandler */
    /* access modifiers changed from: package-private */
    public static class c implements Runnable {
        private final WeakReference<Runnable> a;
        private final WeakReference<a> b;

        c(WeakReference<Runnable> weakReference, WeakReference<a> weakReference2) {
            this.a = weakReference;
            this.b = weakReference2;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(24300, false);
            Runnable runnable = this.a.get();
            a aVar = this.b.get();
            if (aVar != null) {
                aVar.a();
            }
            if (runnable != null) {
                runnable.run();
            }
            AppMethodBeat.o(24300);
        }
    }

    /* compiled from: WeakHandler */
    /* access modifiers changed from: package-private */
    public static class a {
        final Runnable a;
        final c b;
        a c;
        a d;
        Lock e;

        public a(Lock lock, Runnable runnable) {
            AppMethodBeat.i(24295, false);
            this.a = runnable;
            this.e = lock;
            this.b = new c(new WeakReference(runnable), new WeakReference(this));
            AppMethodBeat.o(24295);
        }

        public void a(a aVar) {
            int i = 24296;
            AppMethodBeat.i(24296, false);
            this.e.lock();
            try {
                if (this.c != null) {
                    this.c.d = aVar;
                }
                aVar.c = this.c;
                this.c = aVar;
                aVar.d = this;
            } finally {
                this.e.unlock();
                AppMethodBeat.o(i);
            }
        }

        public c a(Runnable runnable) {
            int i = 24297;
            AppMethodBeat.i(24297, false);
            this.e.lock();
            try {
                for (a aVar = this.c; aVar != null; aVar = aVar.c) {
                    if (aVar.a == runnable) {
                        return aVar.a();
                    }
                }
                this.e.unlock();
                AppMethodBeat.o(24297);
                return null;
            } finally {
                this.e.unlock();
                AppMethodBeat.o(i);
            }
        }

        public c a() {
            AppMethodBeat.i(24298, false);
            this.e.lock();
            try {
                if (this.d != null) {
                    this.d.c = this.c;
                }
                if (this.c != null) {
                    this.c.d = this.d;
                }
                this.d = null;
                this.c = null;
                this.e.unlock();
                c cVar = this.b;
                AppMethodBeat.o(24298);
                return cVar;
            } catch (Throwable th) {
                this.e.unlock();
                AppMethodBeat.o(24298);
                throw th;
            }
        }
    }
}
