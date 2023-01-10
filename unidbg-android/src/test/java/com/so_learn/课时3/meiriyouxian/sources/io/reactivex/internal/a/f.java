package io.reactivex.internal.a;

import java.util.concurrent.Callable;

/* compiled from: ScalarCallable */
public interface f<T> extends Callable<T> {
    @Override // java.util.concurrent.Callable
    T call();
}
