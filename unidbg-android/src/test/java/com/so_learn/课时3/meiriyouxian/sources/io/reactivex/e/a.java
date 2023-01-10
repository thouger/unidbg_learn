package io.reactivex.e;

import io.reactivex.c.c;
import io.reactivex.c.e;
import io.reactivex.c.g;
import io.reactivex.c.h;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.k;
import io.reactivex.m;
import io.reactivex.q;
import io.reactivex.v;
import io.reactivex.w;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.Callable;

/* compiled from: RxJavaPlugins */
public final class a {
    static volatile g<? super Throwable> a;
    static volatile h<? super Runnable, ? extends Runnable> b;
    static volatile h<? super Callable<w>, ? extends w> c;
    static volatile h<? super Callable<w>, ? extends w> d;
    static volatile h<? super Callable<w>, ? extends w> e;
    static volatile h<? super Callable<w>, ? extends w> f;
    static volatile h<? super w, ? extends w> g;
    static volatile h<? super w, ? extends w> h;
    static volatile h<? super w, ? extends w> i;
    static volatile h<? super w, ? extends w> j;
    static volatile h<? super io.reactivex.g, ? extends io.reactivex.g> k;
    static volatile h<? super q, ? extends q> l;
    static volatile h<? super io.reactivex.d.a, ? extends io.reactivex.d.a> m;
    static volatile h<? super k, ? extends k> n;
    static volatile h<? super x, ? extends x> o;
    static volatile h<? super io.reactivex.a, ? extends io.reactivex.a> p;
    static volatile h<? super io.reactivex.parallel.a, ? extends io.reactivex.parallel.a> q;
    static volatile c<? super io.reactivex.g, ? super org.a.c, ? extends org.a.c> r;
    static volatile c<? super k, ? super m, ? extends m> s;
    static volatile c<? super q, ? super v, ? extends v> t;
    static volatile c<? super x, ? super z, ? extends z> u;
    static volatile c<? super io.reactivex.a, ? super io.reactivex.c, ? extends io.reactivex.c> v;
    static volatile e w;
    static volatile boolean x;

    public static boolean a() {
        return x;
    }

    public static w a(Callable<w> callable) {
        io.reactivex.internal.functions.a.a(callable, "Scheduler Callable can't be null");
        h<? super Callable<w>, ? extends w> hVar = c;
        if (hVar == null) {
            return e(callable);
        }
        return a(hVar, callable);
    }

    public static w b(Callable<w> callable) {
        io.reactivex.internal.functions.a.a(callable, "Scheduler Callable can't be null");
        h<? super Callable<w>, ? extends w> hVar = e;
        if (hVar == null) {
            return e(callable);
        }
        return a(hVar, callable);
    }

    public static w c(Callable<w> callable) {
        io.reactivex.internal.functions.a.a(callable, "Scheduler Callable can't be null");
        h<? super Callable<w>, ? extends w> hVar = f;
        if (hVar == null) {
            return e(callable);
        }
        return a(hVar, callable);
    }

    public static w d(Callable<w> callable) {
        io.reactivex.internal.functions.a.a(callable, "Scheduler Callable can't be null");
        h<? super Callable<w>, ? extends w> hVar = d;
        if (hVar == null) {
            return e(callable);
        }
        return a(hVar, callable);
    }

    public static w a(w wVar) {
        h<? super w, ? extends w> hVar = g;
        if (hVar == null) {
            return wVar;
        }
        return (w) a((h<w, Object>) hVar, wVar);
    }

    public static void a(Throwable th) {
        g<? super Throwable> gVar = a;
        if (th == null) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        } else if (!b(th)) {
            th = new UndeliverableException(th);
        }
        if (gVar != null) {
            try {
                gVar.accept(th);
                return;
            } catch (Throwable th2) {
                th2.printStackTrace();
                c(th2);
            }
        }
        th.printStackTrace();
        c(th);
    }

    static boolean b(Throwable th) {
        if (!(th instanceof OnErrorNotImplementedException) && !(th instanceof MissingBackpressureException) && !(th instanceof IllegalStateException) && !(th instanceof NullPointerException) && !(th instanceof IllegalArgumentException) && !(th instanceof CompositeException)) {
            return false;
        }
        return true;
    }

    static void c(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    public static w b(w wVar) {
        h<? super w, ? extends w> hVar = i;
        if (hVar == null) {
            return wVar;
        }
        return (w) a((h<w, Object>) hVar, wVar);
    }

    public static w c(w wVar) {
        h<? super w, ? extends w> hVar = j;
        if (hVar == null) {
            return wVar;
        }
        return (w) a((h<w, Object>) hVar, wVar);
    }

    public static Runnable a(Runnable runnable) {
        io.reactivex.internal.functions.a.a(runnable, "run is null");
        h<? super Runnable, ? extends Runnable> hVar = b;
        if (hVar == null) {
            return runnable;
        }
        return (Runnable) a((h<Runnable, Object>) hVar, runnable);
    }

    public static w d(w wVar) {
        h<? super w, ? extends w> hVar = h;
        if (hVar == null) {
            return wVar;
        }
        return (w) a((h<w, Object>) hVar, wVar);
    }

    public static <T> org.a.c<? super T> a(io.reactivex.g<T> gVar, org.a.c<? super T> cVar) {
        c<? super io.reactivex.g, ? super org.a.c, ? extends org.a.c> cVar2 = r;
        return cVar2 != null ? (org.a.c) a(cVar2, gVar, cVar) : cVar;
    }

    public static <T> v<? super T> a(q<T> qVar, v<? super T> vVar) {
        c<? super q, ? super v, ? extends v> cVar = t;
        return cVar != null ? (v) a(cVar, qVar, vVar) : vVar;
    }

    public static <T> z<? super T> a(x<T> xVar, z<? super T> zVar) {
        c<? super x, ? super z, ? extends z> cVar = u;
        return cVar != null ? (z) a(cVar, xVar, zVar) : zVar;
    }

    public static io.reactivex.c a(io.reactivex.a aVar, io.reactivex.c cVar) {
        c<? super io.reactivex.a, ? super io.reactivex.c, ? extends io.reactivex.c> cVar2 = v;
        return cVar2 != null ? (io.reactivex.c) a(cVar2, aVar, cVar) : cVar;
    }

    public static <T> m<? super T> a(k<T> kVar, m<? super T> mVar) {
        c<? super k, ? super m, ? extends m> cVar = s;
        return cVar != null ? (m) a(cVar, kVar, mVar) : mVar;
    }

    public static <T> k<T> a(k<T> kVar) {
        h<? super k, ? extends k> hVar = n;
        return hVar != null ? (k) a((h<k<T>, Object>) hVar, kVar) : kVar;
    }

    public static <T> io.reactivex.g<T> a(io.reactivex.g<T> gVar) {
        h<? super io.reactivex.g, ? extends io.reactivex.g> hVar = k;
        return hVar != null ? (io.reactivex.g) a((h<io.reactivex.g<T>, Object>) hVar, gVar) : gVar;
    }

    public static <T> q<T> a(q<T> qVar) {
        h<? super q, ? extends q> hVar = l;
        return hVar != null ? (q) a((h<q<T>, Object>) hVar, qVar) : qVar;
    }

    public static <T> io.reactivex.d.a<T> a(io.reactivex.d.a<T> aVar) {
        h<? super io.reactivex.d.a, ? extends io.reactivex.d.a> hVar = m;
        return hVar != null ? (io.reactivex.d.a) a((h<io.reactivex.d.a<T>, Object>) hVar, aVar) : aVar;
    }

    public static <T> x<T> a(x<T> xVar) {
        h<? super x, ? extends x> hVar = o;
        return hVar != null ? (x) a((h<x<T>, Object>) hVar, xVar) : xVar;
    }

    public static io.reactivex.a a(io.reactivex.a aVar) {
        h<? super io.reactivex.a, ? extends io.reactivex.a> hVar = p;
        return hVar != null ? (io.reactivex.a) a((h<io.reactivex.a, Object>) hVar, aVar) : aVar;
    }

    public static <T> io.reactivex.parallel.a<T> a(io.reactivex.parallel.a<T> aVar) {
        h<? super io.reactivex.parallel.a, ? extends io.reactivex.parallel.a> hVar = q;
        return hVar != null ? (io.reactivex.parallel.a) a((h<io.reactivex.parallel.a<T>, Object>) hVar, aVar) : aVar;
    }

    public static boolean b() {
        e eVar = w;
        if (eVar == null) {
            return false;
        }
        try {
            return eVar.getAsBoolean();
        } catch (Throwable th) {
            throw ExceptionHelper.a(th);
        }
    }

    static <T, R> R a(h<T, R> hVar, T t2) {
        try {
            return (R) hVar.apply(t2);
        } catch (Throwable th) {
            throw ExceptionHelper.a(th);
        }
    }

    static <T, U, R> R a(c<T, U, R> cVar, T t2, U u2) {
        try {
            return (R) cVar.apply(t2, u2);
        } catch (Throwable th) {
            throw ExceptionHelper.a(th);
        }
    }

    static w e(Callable<w> callable) {
        try {
            return (w) io.reactivex.internal.functions.a.a(callable.call(), "Scheduler Callable result can't be null");
        } catch (Throwable th) {
            throw ExceptionHelper.a(th);
        }
    }

    static w a(h<? super Callable<w>, ? extends w> hVar, Callable<w> callable) {
        return (w) io.reactivex.internal.functions.a.a(a((h<Callable<w>, Object>) hVar, callable), "Scheduler Callable result can't be null");
    }
}
