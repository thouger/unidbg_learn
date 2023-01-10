package io.reactivex;

import io.reactivex.c.g;
import io.reactivex.disposables.b;
import io.reactivex.f.a;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.observers.ConsumerSingleObserver;
import io.reactivex.internal.operators.single.SingleObserveOn;
import io.reactivex.internal.operators.single.SingleTimer;
import io.reactivex.internal.operators.single.SingleToObservable;
import java.util.concurrent.TimeUnit;

/* compiled from: Single */
public abstract class x<T> implements ab<T> {
    /* access modifiers changed from: protected */
    public abstract void b(z<? super T> zVar);

    public static x<Long> a(long j, TimeUnit timeUnit) {
        return a(j, timeUnit, a.a());
    }

    public static x<Long> a(long j, TimeUnit timeUnit, w wVar) {
        io.reactivex.internal.functions.a.a(timeUnit, "unit is null");
        io.reactivex.internal.functions.a.a(wVar, "scheduler is null");
        return io.reactivex.e.a.a(new SingleTimer(j, timeUnit, wVar));
    }

    public final x<T> a(w wVar) {
        io.reactivex.internal.functions.a.a(wVar, "scheduler is null");
        return io.reactivex.e.a.a(new SingleObserveOn(this, wVar));
    }

    public final b a(g<? super T> gVar) {
        return a(gVar, Functions.f);
    }

    public final b a(g<? super T> gVar, g<? super Throwable> gVar2) {
        io.reactivex.internal.functions.a.a(gVar, "onSuccess is null");
        io.reactivex.internal.functions.a.a(gVar2, "onError is null");
        ConsumerSingleObserver consumerSingleObserver = new ConsumerSingleObserver(gVar, gVar2);
        a(consumerSingleObserver);
        return consumerSingleObserver;
    }

    @Override // io.reactivex.ab
    public final void a(z<? super T> zVar) {
        io.reactivex.internal.functions.a.a(zVar, "observer is null");
        z a = io.reactivex.e.a.a(this, zVar);
        io.reactivex.internal.functions.a.a(a, "The RxJavaPlugins.onSubscribe hook returned a null SingleObserver. Please check the handler provided to RxJavaPlugins.setOnSingleSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
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

    public final q<T> ag_() {
        if (this instanceof io.reactivex.internal.a.b) {
            return ((io.reactivex.internal.a.b) this).a();
        }
        return io.reactivex.e.a.a(new SingleToObservable(this));
    }
}
