package com.google.common.util.concurrent;

import com.google.common.base.g;
import com.google.common.base.i;
import com.google.common.base.m;
import com.google.common.util.concurrent.o;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* compiled from: Futures */
public final class l extends n {
    public static <V> p<V> a(V v) {
        if (v == null) {
            return o.b.a;
        }
        return new o.b(v);
    }

    public static <V> p<V> a(Throwable th) {
        m.a(th);
        return new o.a(th);
    }

    public static <I, O> p<O> a(p<I> pVar, g<? super I, ? extends O> gVar, Executor executor) {
        return c.a(pVar, gVar, executor);
    }

    public static <V> void a(p<V> pVar, k<? super V> kVar, Executor executor) {
        m.a(kVar);
        pVar.a(new a(pVar, kVar), executor);
    }

    /* compiled from: Futures */
    private static final class a<V> implements Runnable {
        final Future<V> a;
        final k<? super V> b;

        a(Future<V> future, k<? super V> kVar) {
            this.a = future;
            this.b = kVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.b.onSuccess(l.a((Future<Object>) this.a));
            } catch (ExecutionException e) {
                this.b.onFailure(e.getCause());
            } catch (Error | RuntimeException e2) {
                this.b.onFailure(e2);
            }
        }

        @Override // java.lang.Object
        public String toString() {
            return i.a(this).a(this.b).toString();
        }
    }

    public static <V> V a(Future<V> future) throws ExecutionException {
        m.b(future.isDone(), "Future was expected to be done: %s", future);
        return (V) w.a(future);
    }
}
