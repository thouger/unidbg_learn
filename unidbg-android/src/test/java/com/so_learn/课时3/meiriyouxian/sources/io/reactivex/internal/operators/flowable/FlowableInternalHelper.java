package io.reactivex.internal.operators.flowable;

import io.reactivex.c.g;
import org.a.d;

public final class FlowableInternalHelper {

    public enum RequestMax implements g<d> {
        INSTANCE;

        public void accept(d dVar) throws Exception {
            dVar.request(Long.MAX_VALUE);
        }
    }
}
