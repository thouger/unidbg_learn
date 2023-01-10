package io.reactivex.internal.operators.maybe;

import io.reactivex.c.h;
import io.reactivex.o;
import org.a.b;

public enum MaybeToPublisher implements h<o<Object>, b<Object>> {
    INSTANCE;

    public static <T> h<o<T>, b<T>> instance() {
        return INSTANCE;
    }

    public b<Object> apply(o<Object> oVar) throws Exception {
        return new MaybeToFlowable(oVar);
    }
}
