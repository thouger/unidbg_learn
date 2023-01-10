package io.reactivex;

import io.reactivex.c.g;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.operators.maybe.MaybeCallbackObserver;
import io.reactivex.internal.operators.maybe.MaybeSwitchIfEmpty;
import io.reactivex.internal.operators.maybe.b;

/* compiled from: Maybe */
public abstract class k<T> implements o<T> {
    /* access modifiers changed from: protected */
    public abstract void b(m<? super T> mVar);

    public static <T> k<T> a(T t) {
        a.a((Object) t, "item is null");
        return io.reactivex.e.a.a((k) new b(t));
    }

    public final k<T> b(T t) {
        a.a((Object) t, "defaultItem is null");
        return a((o) a((Object) t));
    }

    public final io.reactivex.disposables.b a(g<? super T> gVar) {
        return a(gVar, Functions.f, Functions.c);
    }

    public final io.reactivex.disposables.b a(g<? super T> gVar, g<? super Throwable> gVar2, io.reactivex.c.a aVar) {
        a.a(gVar, "onSuccess is null");
        a.a(gVar2, "onError is null");
        a.a(aVar, "onComplete is null");
        return (io.reactivex.disposables.b) c(new MaybeCallbackObserver(gVar, gVar2, aVar));
    }

    @Override // io.reactivex.o
    public final void a(m<? super T> mVar) {
        a.a(mVar, "observer is null");
        m a = io.reactivex.e.a.a(this, mVar);
        a.a(a, "The RxJavaPlugins.onSubscribe hook returned a null MaybeObserver. Please check the handler provided to RxJavaPlugins.setOnMaybeSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
        try {
            b(a);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            NullPointerException nullPointerException = new NullPointerException("subscribeActual failed");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final <E extends m<? super T>> E c(E e) {
        a((m) e);
        return e;
    }

    public final k<T> a(o<? extends T> oVar) {
        a.a(oVar, "other is null");
        return io.reactivex.e.a.a(new MaybeSwitchIfEmpty(this, oVar));
    }
}
