package io.reactivex.internal.schedulers;

import io.reactivex.c.h;
import io.reactivex.g;
import io.reactivex.processors.UnicastProcessor;
import io.reactivex.w;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class SchedulerWhen extends w implements io.reactivex.disposables.b {
    static final io.reactivex.disposables.b b = new d();
    static final io.reactivex.disposables.b c = io.reactivex.disposables.c.a();
    private final w d;
    private final io.reactivex.processors.a<g<io.reactivex.a>> e;
    private io.reactivex.disposables.b f;

    @Override // io.reactivex.disposables.b
    public void dispose() {
        this.f.dispose();
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.f.isDisposed();
    }

    @Override // io.reactivex.w
    public w.c a() {
        w.c a2 = this.d.a();
        io.reactivex.processors.a<T> g = UnicastProcessor.h().g();
        g b2 = g.b(new a(a2));
        c cVar = new c(g, a2);
        this.e.onNext(b2);
        return cVar;
    }

    /* access modifiers changed from: package-private */
    public static abstract class ScheduledAction extends AtomicReference<io.reactivex.disposables.b> implements io.reactivex.disposables.b {
        /* access modifiers changed from: protected */
        public abstract io.reactivex.disposables.b callActual(w.c cVar, io.reactivex.c cVar2);

        ScheduledAction() {
            super(SchedulerWhen.b);
        }

        /* access modifiers changed from: package-private */
        public void call(w.c cVar, io.reactivex.c cVar2) {
            io.reactivex.disposables.b bVar = get();
            if (bVar != SchedulerWhen.c && bVar == SchedulerWhen.b) {
                io.reactivex.disposables.b callActual = callActual(cVar, cVar2);
                if (!compareAndSet(SchedulerWhen.b, callActual)) {
                    callActual.dispose();
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get().isDisposed();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            io.reactivex.disposables.b bVar;
            io.reactivex.disposables.b bVar2 = SchedulerWhen.c;
            do {
                bVar = get();
                if (bVar == SchedulerWhen.c) {
                    return;
                }
            } while (!compareAndSet(bVar, bVar2));
            if (bVar != SchedulerWhen.b) {
                bVar.dispose();
            }
        }
    }

    static class ImmediateAction extends ScheduledAction {
        private final Runnable action;

        ImmediateAction(Runnable runnable) {
            this.action = runnable;
        }

        /* access modifiers changed from: protected */
        @Override // io.reactivex.internal.schedulers.SchedulerWhen.ScheduledAction
        public io.reactivex.disposables.b callActual(w.c cVar, io.reactivex.c cVar2) {
            return cVar.a(new b(this.action, cVar2));
        }
    }

    static class DelayedAction extends ScheduledAction {
        private final Runnable action;
        private final long delayTime;
        private final TimeUnit unit;

        DelayedAction(Runnable runnable, long j, TimeUnit timeUnit) {
            this.action = runnable;
            this.delayTime = j;
            this.unit = timeUnit;
        }

        /* access modifiers changed from: protected */
        @Override // io.reactivex.internal.schedulers.SchedulerWhen.ScheduledAction
        public io.reactivex.disposables.b callActual(w.c cVar, io.reactivex.c cVar2) {
            return cVar.a(new b(this.action, cVar2), this.delayTime, this.unit);
        }
    }

    static class b implements Runnable {
        final io.reactivex.c a;
        final Runnable b;

        b(Runnable runnable, io.reactivex.c cVar) {
            this.b = runnable;
            this.a = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.b.run();
            } finally {
                this.a.onComplete();
            }
        }
    }

    static final class a implements h<ScheduledAction, io.reactivex.a> {
        final w.c a;

        a(w.c cVar) {
            this.a = cVar;
        }

        /* renamed from: a */
        public io.reactivex.a apply(ScheduledAction scheduledAction) {
            return new C0211a(scheduledAction);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: io.reactivex.internal.schedulers.SchedulerWhen$a$a  reason: collision with other inner class name */
        public final class C0211a extends io.reactivex.a {
            final ScheduledAction a;

            C0211a(ScheduledAction scheduledAction) {
                this.a = scheduledAction;
            }

            /* access modifiers changed from: protected */
            @Override // io.reactivex.a
            public void b(io.reactivex.c cVar) {
                cVar.onSubscribe(this.a);
                this.a.call(a.this.a, cVar);
            }
        }
    }

    static final class c extends w.c {
        private final AtomicBoolean a = new AtomicBoolean();
        private final io.reactivex.processors.a<ScheduledAction> b;
        private final w.c c;

        c(io.reactivex.processors.a<ScheduledAction> aVar, w.c cVar) {
            this.b = aVar;
            this.c = cVar;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (this.a.compareAndSet(false, true)) {
                this.b.onComplete();
                this.c.dispose();
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.a.get();
        }

        @Override // io.reactivex.w.c
        public io.reactivex.disposables.b a(Runnable runnable, long j, TimeUnit timeUnit) {
            DelayedAction delayedAction = new DelayedAction(runnable, j, timeUnit);
            this.b.onNext(delayedAction);
            return delayedAction;
        }

        @Override // io.reactivex.w.c
        public io.reactivex.disposables.b a(Runnable runnable) {
            ImmediateAction immediateAction = new ImmediateAction(runnable);
            this.b.onNext(immediateAction);
            return immediateAction;
        }
    }

    static final class d implements io.reactivex.disposables.b {
        @Override // io.reactivex.disposables.b
        public void dispose() {
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return false;
        }

        d() {
        }
    }
}
