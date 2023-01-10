package io.reactivex.a.a;

import io.reactivex.c.h;
import io.reactivex.w;
import java.util.concurrent.Callable;

/* compiled from: RxAndroidPlugins */
public final class a {
    private static volatile h<Callable<w>, w> a;
    private static volatile h<w, w> b;

    public static w a(Callable<w> callable) {
        if (callable != null) {
            h<Callable<w>, w> hVar = a;
            if (hVar == null) {
                return b(callable);
            }
            return a(hVar, callable);
        }
        throw new NullPointerException("scheduler == null");
    }

    public static w a(w wVar) {
        if (wVar != null) {
            h<w, w> hVar = b;
            if (hVar == null) {
                return wVar;
            }
            return (w) a(hVar, wVar);
        }
        throw new NullPointerException("scheduler == null");
    }

    static w b(Callable<w> callable) {
        try {
            w call = callable.call();
            if (call != null) {
                return call;
            }
            throw new NullPointerException("Scheduler Callable returned null");
        } catch (Throwable th) {
            throw io.reactivex.exceptions.a.a(th);
        }
    }

    static w a(h<Callable<w>, w> hVar, Callable<w> callable) {
        w wVar = (w) a((h<Callable<w>, Object>) hVar, callable);
        if (wVar != null) {
            return wVar;
        }
        throw new NullPointerException("Scheduler Callable returned null");
    }

    static <T, R> R a(h<T, R> hVar, T t) {
        try {
            return (R) hVar.apply(t);
        } catch (Throwable th) {
            throw io.reactivex.exceptions.a.a(th);
        }
    }
}
