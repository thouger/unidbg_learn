package io.reactivex.internal.util;

import java.util.concurrent.atomic.AtomicReference;

public final class AtomicThrowable extends AtomicReference<Throwable> {
    private static final long serialVersionUID = 3949248817947090603L;

    public boolean addThrowable(Throwable th) {
        return ExceptionHelper.a(this, th);
    }

    public Throwable terminate() {
        return ExceptionHelper.a(this);
    }

    public boolean isTerminated() {
        return get() == ExceptionHelper.a;
    }
}
