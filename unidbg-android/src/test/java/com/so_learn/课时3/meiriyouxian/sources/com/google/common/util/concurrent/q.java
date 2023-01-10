package com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

/* compiled from: ListenableFutureTask */
public class q<V> extends FutureTask<V> implements p<V> {
    private final g a = new g();

    public static <V> q<V> a(Callable<V> callable) {
        return new q<>(callable);
    }

    q(Callable<V> callable) {
        super(callable);
    }

    @Override // com.google.common.util.concurrent.p
    public void a(Runnable runnable, Executor executor) {
        this.a.a(runnable, executor);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.FutureTask
    public void done() {
        this.a.a();
    }
}
