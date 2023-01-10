package com.google.common.util.concurrent;

import com.google.common.base.m;
import com.google.common.util.concurrent.a;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: ImmediateFuture */
abstract class o<V> implements p<V> {
    private static final Logger a = Logger.getLogger(o.class.getName());

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return false;
    }

    @Override // java.util.concurrent.Future
    public abstract V get() throws ExecutionException;

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return true;
    }

    o() {
    }

    @Override // com.google.common.util.concurrent.p
    public void a(Runnable runnable, Executor executor) {
        m.a(runnable, "Runnable was null.");
        m.a(executor, "Executor was null.");
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = a;
            Level level = Level.SEVERE;
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e);
        }
    }

    @Override // java.util.concurrent.Future
    public V get(long j, TimeUnit timeUnit) throws ExecutionException {
        m.a(timeUnit);
        return (V) get();
    }

    /* compiled from: ImmediateFuture */
    /* access modifiers changed from: package-private */
    public static class b<V> extends o<V> {
        static final b<Object> a = new b<>(null);
        private final V b;

        b(V v) {
            this.b = v;
        }

        @Override // com.google.common.util.concurrent.o, java.util.concurrent.Future
        public V get() {
            return this.b;
        }

        @Override // java.lang.Object
        public String toString() {
            return super.toString() + "[status=SUCCESS, result=[" + ((Object) this.b) + "]]";
        }
    }

    /* compiled from: ImmediateFuture */
    /* access modifiers changed from: package-private */
    public static final class a<V> extends a.i<V> {
        a(Throwable th) {
            a(th);
        }
    }
}
