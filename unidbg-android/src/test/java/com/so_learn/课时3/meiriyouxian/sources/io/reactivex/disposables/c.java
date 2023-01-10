package io.reactivex.disposables;

import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.a;

/* compiled from: Disposables */
public final class c {
    public static b a(Runnable runnable) {
        a.a(runnable, "run is null");
        return new RunnableDisposable(runnable);
    }

    public static b a() {
        return EmptyDisposable.INSTANCE;
    }
}
