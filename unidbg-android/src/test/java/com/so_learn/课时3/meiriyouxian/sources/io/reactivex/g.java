package io.reactivex;

import io.reactivex.c.h;
import io.reactivex.e.a;
import io.reactivex.internal.a.f;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.operators.flowable.FlowableFlatMap;
import io.reactivex.internal.operators.flowable.FlowableFromIterable;
import io.reactivex.internal.operators.flowable.FlowableInternalHelper;
import io.reactivex.internal.operators.flowable.FlowableObserveOn;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureBuffer;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureDrop;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureLatest;
import io.reactivex.internal.operators.flowable.FlowableOnErrorNext;
import io.reactivex.internal.operators.flowable.e;
import io.reactivex.internal.operators.flowable.i;
import io.reactivex.internal.subscribers.LambdaSubscriber;
import io.reactivex.internal.subscribers.StrictSubscriber;
import java.util.List;
import org.a.b;
import org.a.c;
import org.a.d;

/* compiled from: Flowable */
public abstract class g<T> implements b<T> {
    static final int a = Math.max(1, Integer.getInteger("rx2.buffer-size", 128).intValue());

    /* access modifiers changed from: protected */
    public abstract void a(c<? super T> cVar);

    public static int a() {
        return a;
    }

    public static <T> g<T> b() {
        return a.a(io.reactivex.internal.operators.flowable.c.b);
    }

    public static <T> g<T> a(Iterable<? extends T> iterable) {
        io.reactivex.internal.functions.a.a(iterable, "source is null");
        return a.a(new FlowableFromIterable(iterable));
    }

    public static <T> g<T> a(T t) {
        io.reactivex.internal.functions.a.a((Object) t, "item is null");
        return a.a((g) new e(t));
    }

    private g<T> a(io.reactivex.c.g<? super T> gVar, io.reactivex.c.g<? super Throwable> gVar2, io.reactivex.c.a aVar, io.reactivex.c.a aVar2) {
        io.reactivex.internal.functions.a.a(gVar, "onNext is null");
        io.reactivex.internal.functions.a.a(gVar2, "onError is null");
        io.reactivex.internal.functions.a.a(aVar, "onComplete is null");
        io.reactivex.internal.functions.a.a(aVar2, "onAfterTerminate is null");
        return a.a(new io.reactivex.internal.operators.flowable.b(this, gVar, gVar2, aVar, aVar2));
    }

    public final g<T> a(io.reactivex.c.g<? super Throwable> gVar) {
        return a(Functions.b(), gVar, Functions.c, Functions.c);
    }

    public final <R> g<R> a(h<? super T, ? extends b<? extends R>> hVar) {
        return a((h) hVar, false, a(), a());
    }

    public final <R> g<R> a(h<? super T, ? extends b<? extends R>> hVar, boolean z, int i, int i2) {
        io.reactivex.internal.functions.a.a(hVar, "mapper is null");
        io.reactivex.internal.functions.a.a(i, "maxConcurrency");
        io.reactivex.internal.functions.a.a(i2, "bufferSize");
        if (!(this instanceof f)) {
            return a.a(new FlowableFlatMap(this, hVar, z, i, i2));
        }
        Object call = ((f) this).call();
        if (call == null) {
            return b();
        }
        return io.reactivex.internal.operators.flowable.h.a(call, hVar);
    }

    public final <R> g<R> b(h<? super T, ? extends R> hVar) {
        io.reactivex.internal.functions.a.a(hVar, "mapper is null");
        return a.a(new io.reactivex.internal.operators.flowable.f(this, hVar));
    }

    public final g<T> a(w wVar) {
        return a(wVar, false, a());
    }

    public final g<T> a(w wVar, boolean z, int i) {
        io.reactivex.internal.functions.a.a(wVar, "scheduler is null");
        io.reactivex.internal.functions.a.a(i, "bufferSize");
        return a.a(new FlowableObserveOn(this, wVar, z, i));
    }

    public final g<T> c() {
        return a(a(), false, true);
    }

    public final g<T> a(int i, boolean z, boolean z2) {
        io.reactivex.internal.functions.a.a(i, "capacity");
        return a.a(new FlowableOnBackpressureBuffer(this, i, z2, z, Functions.c));
    }

    public final g<T> d() {
        return a.a(new FlowableOnBackpressureDrop(this));
    }

    public final g<T> e() {
        return a.a(new FlowableOnBackpressureLatest(this));
    }

    public final g<T> c(h<? super Throwable, ? extends b<? extends T>> hVar) {
        io.reactivex.internal.functions.a.a(hVar, "resumeFunction is null");
        return a.a(new FlowableOnErrorNext(this, hVar, false));
    }

    public final g<T> a(b<? extends T> bVar) {
        io.reactivex.internal.functions.a.a(bVar, "next is null");
        return c(Functions.b(bVar));
    }

    public final io.reactivex.parallel.a<T> a(int i) {
        io.reactivex.internal.functions.a.a(i, "parallelism");
        return io.reactivex.parallel.a.a((b) this, i);
    }

    public final io.reactivex.disposables.b b(io.reactivex.c.g<? super T> gVar) {
        return a(gVar, Functions.f, Functions.c, FlowableInternalHelper.RequestMax.INSTANCE);
    }

    public final io.reactivex.disposables.b a(io.reactivex.c.g<? super T> gVar, io.reactivex.c.g<? super Throwable> gVar2, io.reactivex.c.a aVar, io.reactivex.c.g<? super d> gVar3) {
        io.reactivex.internal.functions.a.a(gVar, "onNext is null");
        io.reactivex.internal.functions.a.a(gVar2, "onError is null");
        io.reactivex.internal.functions.a.a(aVar, "onComplete is null");
        io.reactivex.internal.functions.a.a(gVar3, "onSubscribe is null");
        LambdaSubscriber lambdaSubscriber = new LambdaSubscriber(gVar, gVar2, aVar, gVar3);
        a((j) lambdaSubscriber);
        return lambdaSubscriber;
    }

    public final void subscribe(c<? super T> cVar) {
        if (cVar instanceof j) {
            a((j) cVar);
            return;
        }
        io.reactivex.internal.functions.a.a(cVar, "s is null");
        a((j) new StrictSubscriber(cVar));
    }

    public final void a(j<? super T> jVar) {
        io.reactivex.internal.functions.a.a(jVar, "s is null");
        try {
            c a2 = a.a((g) this, (c) jVar);
            io.reactivex.internal.functions.a.a(a2, "The RxJavaPlugins.onSubscribe hook returned a null FlowableSubscriber. Please check the handler provided to RxJavaPlugins.setOnFlowableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            a(a2);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            a.a(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final x<List<T>> f() {
        return a.a(new i(this));
    }
}
