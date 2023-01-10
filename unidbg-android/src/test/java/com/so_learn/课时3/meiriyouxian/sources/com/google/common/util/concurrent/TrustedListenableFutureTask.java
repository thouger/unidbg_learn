package com.google.common.util.concurrent;

import com.google.common.base.m;
import com.google.common.util.concurrent.h;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/* access modifiers changed from: package-private */
public class TrustedListenableFutureTask<V> extends h.a<V> implements RunnableFuture<V> {
    private volatile InterruptibleTask<?> a;

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public void run() {
        InterruptibleTask<?> interruptibleTask = this.a;
        if (interruptibleTask != null) {
            interruptibleTask.run();
        }
        this.a = null;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.a
    public void c() {
        InterruptibleTask<?> interruptibleTask;
        super.c();
        if (b() && (interruptibleTask = this.a) != null) {
            interruptibleTask.interruptTask();
        }
        this.a = null;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.a
    public String e() {
        InterruptibleTask<?> interruptibleTask = this.a;
        if (interruptibleTask == null) {
            return super.e();
        }
        return "task=[" + interruptibleTask + "]";
    }

    private final class TrustedFutureInterruptibleTask extends InterruptibleTask<V> {
        private final Callable<V> callable;

        TrustedFutureInterruptibleTask(Callable<V> callable) {
            this.callable = (Callable) m.a(callable);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final boolean isDone() {
            return TrustedListenableFutureTask.this.isDone();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.InterruptibleTask
        public V runInterruptibly() throws Exception {
            return this.callable.call();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.InterruptibleTask
        public void afterRanInterruptibly(V v, Throwable th) {
            if (th == null) {
                TrustedListenableFutureTask.this.a((TrustedListenableFutureTask) v);
            } else {
                TrustedListenableFutureTask.this.a(th);
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.InterruptibleTask
        public String toPendingString() {
            return this.callable.toString();
        }
    }

    private final class TrustedFutureInterruptibleAsyncTask extends InterruptibleTask<p<V>> {
        private final f<V> callable;

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.InterruptibleTask
        public /* bridge */ /* synthetic */ void afterRanInterruptibly(Object obj, Throwable th) {
            afterRanInterruptibly((p) ((p) obj), th);
        }

        TrustedFutureInterruptibleAsyncTask(f<V> fVar) {
            this.callable = (f) m.a(fVar);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.InterruptibleTask
        public final boolean isDone() {
            return TrustedListenableFutureTask.this.isDone();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.InterruptibleTask
        public p<V> runInterruptibly() throws Exception {
            return (p) m.a(this.callable.a(), "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.callable);
        }

        /* access modifiers changed from: package-private */
        public void afterRanInterruptibly(p<V> pVar, Throwable th) {
            if (th == null) {
                TrustedListenableFutureTask.this.a((p) pVar);
            } else {
                TrustedListenableFutureTask.this.a(th);
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.InterruptibleTask
        public String toPendingString() {
            return this.callable.toString();
        }
    }
}
