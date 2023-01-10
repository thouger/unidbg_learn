package com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* compiled from: ListenableFuture */
public interface p<V> extends Future<V> {
    void a(Runnable runnable, Executor executor);
}
