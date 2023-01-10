package com.google.common.util.concurrent;

import com.google.common.util.concurrent.a;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: FluentFuture */
public abstract class h<V> extends m<V> {

    /* compiled from: FluentFuture */
    /* access modifiers changed from: package-private */
    public static abstract class a<V> extends h<V> implements a.h<V> {
        a() {
        }

        @Override // com.google.common.util.concurrent.a, java.util.concurrent.Future
        public final V get() throws InterruptedException, ExecutionException {
            return (V) h.super.get();
        }

        @Override // com.google.common.util.concurrent.a, java.util.concurrent.Future
        public final V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return (V) h.super.get(j, timeUnit);
        }

        @Override // com.google.common.util.concurrent.a, java.util.concurrent.Future
        public final boolean isDone() {
            return h.super.isDone();
        }

        @Override // com.google.common.util.concurrent.a, java.util.concurrent.Future
        public final boolean isCancelled() {
            return h.super.isCancelled();
        }

        @Override // com.google.common.util.concurrent.a, com.google.common.util.concurrent.p
        public final void a(Runnable runnable, Executor executor) {
            h.super.a(runnable, executor);
        }

        @Override // com.google.common.util.concurrent.a, java.util.concurrent.Future
        public final boolean cancel(boolean z) {
            return h.super.cancel(z);
        }
    }

    h() {
    }
}
