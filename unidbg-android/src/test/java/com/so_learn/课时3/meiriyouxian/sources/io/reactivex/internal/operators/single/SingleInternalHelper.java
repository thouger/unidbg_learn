package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.c.h;
import io.reactivex.q;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import org.a.b;

public final class SingleInternalHelper {

    enum NoSuchElementCallable implements Callable<NoSuchElementException> {
        INSTANCE;

        @Override // java.util.concurrent.Callable
        public NoSuchElementException call() throws Exception {
            return new NoSuchElementException();
        }
    }

    enum ToFlowable implements h<ab, b> {
        INSTANCE;

        public b apply(ab abVar) {
            return new SingleToFlowable(abVar);
        }
    }

    enum ToObservable implements h<ab, q> {
        INSTANCE;

        public q apply(ab abVar) {
            return new SingleToObservable(abVar);
        }
    }
}
