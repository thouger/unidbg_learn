package io.reactivex.internal.operators.observable;

import io.reactivex.c.h;

public final class ObservableInternalHelper {

    enum MapToInt implements h<Object, Object> {
        INSTANCE;

        @Override // io.reactivex.c.h
        public Object apply(Object obj) throws Exception {
            return 0;
        }
    }
}
