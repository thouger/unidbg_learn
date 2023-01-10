package com.google.common.util.concurrent;

import com.google.common.base.m;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: ExecutionList */
public final class g {
    private static final Logger a = Logger.getLogger(g.class.getName());
    private a b;
    private boolean c;

    public void a(Runnable runnable, Executor executor) {
        m.a(runnable, "Runnable was null.");
        m.a(executor, "Executor was null.");
        synchronized (this) {
            if (!this.c) {
                this.b = new a(runnable, executor, this.b);
            } else {
                b(runnable, executor);
            }
        }
    }

    public void a() {
        a aVar;
        synchronized (this) {
            if (!this.c) {
                this.c = true;
                aVar = this.b;
                this.b = null;
            } else {
                return;
            }
        }
        a aVar2 = aVar;
        a aVar3 = null;
        while (aVar2 != null) {
            a aVar4 = aVar2.c;
            aVar2.c = aVar3;
            aVar3 = aVar2;
            aVar2 = aVar4;
        }
        while (aVar3 != null) {
            b(aVar3.a, aVar3.b);
            aVar3 = aVar3.c;
        }
    }

    private static void b(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = a;
            Level level = Level.SEVERE;
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e);
        }
    }

    /* compiled from: ExecutionList */
    /* access modifiers changed from: private */
    public static final class a {
        final Runnable a;
        final Executor b;
        a c;

        a(Runnable runnable, Executor executor, a aVar) {
            this.a = runnable;
            this.b = executor;
            this.c = aVar;
        }
    }
}
