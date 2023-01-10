package com.google.common.util.concurrent;

import com.google.common.base.m;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: MoreExecutors */
public final class t {
    public static Executor a() {
        return DirectExecutor.INSTANCE;
    }

    static Executor a(Executor executor, a<?> aVar) {
        m.a(executor);
        m.a(aVar);
        if (executor == a()) {
            return executor;
        }
        return new AnonymousClass1(executor, aVar);
    }

    /* compiled from: MoreExecutors */
    /* renamed from: com.google.common.util.concurrent.t$1  reason: invalid class name */
    static class AnonymousClass1 implements Executor {
        boolean a = true;
        final /* synthetic */ Executor b;
        final /* synthetic */ a c;

        AnonymousClass1(Executor executor, a aVar) {
            this.b = executor;
            this.c = aVar;
        }

        /* compiled from: MoreExecutors */
        /* renamed from: com.google.common.util.concurrent.t$1$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ Runnable a;

            AnonymousClass1(Runnable runnable) {
                this.a = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                AnonymousClass1.this.a = false;
                this.a.run();
            }
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            try {
                this.b.execute(new AnonymousClass1(runnable));
            } catch (RejectedExecutionException e) {
                if (this.a) {
                    this.c.a((Throwable) e);
                }
            }
        }
    }
}
