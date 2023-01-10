package io.reactivex;

import io.reactivex.c.c;
import io.reactivex.c.h;
import io.reactivex.c.i;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.observers.LambdaObserver;
import io.reactivex.internal.operators.flowable.FlowableOnBackpressureError;
import io.reactivex.internal.operators.observable.ObservableBuffer;
import io.reactivex.internal.operators.observable.ObservableCombineLatest;
import io.reactivex.internal.operators.observable.ObservableConcatMap;
import io.reactivex.internal.operators.observable.ObservableCreate;
import io.reactivex.internal.operators.observable.ObservableDebounceTimed;
import io.reactivex.internal.operators.observable.ObservableFlatMap;
import io.reactivex.internal.operators.observable.ObservableInterval;
import io.reactivex.internal.operators.observable.ObservableObserveOn;
import io.reactivex.internal.operators.observable.ObservablePublish;
import io.reactivex.internal.operators.observable.ObservableRange;
import io.reactivex.internal.operators.observable.ObservableRetryWhen;
import io.reactivex.internal.operators.observable.ObservableSampleTimed;
import io.reactivex.internal.operators.observable.ObservableScalarXMap;
import io.reactivex.internal.operators.observable.ObservableSubscribeOn;
import io.reactivex.internal.operators.observable.ObservableThrottleFirstTimed;
import io.reactivex.internal.operators.observable.ObservableTimeoutTimed;
import io.reactivex.internal.operators.observable.ObservableTimer;
import io.reactivex.internal.operators.observable.ObservableZip;
import io.reactivex.internal.operators.observable.b;
import io.reactivex.internal.operators.observable.d;
import io.reactivex.internal.operators.observable.e;
import io.reactivex.internal.operators.observable.f;
import io.reactivex.internal.operators.observable.g;
import io.reactivex.internal.operators.observable.j;
import io.reactivex.internal.operators.observable.k;
import io.reactivex.internal.operators.observable.l;
import io.reactivex.internal.operators.observable.n;
import io.reactivex.internal.operators.observable.o;
import io.reactivex.internal.operators.observable.p;
import io.reactivex.internal.operators.observable.r;
import io.reactivex.internal.operators.observable.t;
import io.reactivex.internal.operators.observable.u;
import io.reactivex.internal.operators.observable.v;
import io.reactivex.internal.operators.observable.w;
import io.reactivex.internal.operators.observable.x;
import io.reactivex.internal.util.ArrayListSupplier;
import io.reactivex.internal.util.ErrorMode;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* compiled from: Observable */
public abstract class q<T> implements t<T> {
    /* access modifiers changed from: protected */
    public abstract void a(v<? super T> vVar);

    public static int c() {
        return g.a();
    }

    public static <T, R> q<R> a(h<? super Object[], ? extends R> hVar, int i, t<? extends T>... tVarArr) {
        return a(tVarArr, hVar, i);
    }

    public static <T, R> q<R> a(t<? extends T>[] tVarArr, h<? super Object[], ? extends R> hVar, int i) {
        a.a(tVarArr, "sources is null");
        if (tVarArr.length == 0) {
            return d();
        }
        a.a(hVar, "combiner is null");
        a.a(i, "bufferSize");
        return io.reactivex.e.a.a(new ObservableCombineLatest(tVarArr, null, hVar, i << 1, false));
    }

    public static <T1, T2, R> q<R> a(t<? extends T1> tVar, t<? extends T2> tVar2, c<? super T1, ? super T2, ? extends R> cVar) {
        a.a(tVar, "source1 is null");
        a.a(tVar2, "source2 is null");
        return a(Functions.a((c) cVar), c(), tVar, tVar2);
    }

    public static <T> q<T> a(t<? extends t<? extends T>> tVar) {
        return a(tVar, c());
    }

    public static <T> q<T> a(t<? extends t<? extends T>> tVar, int i) {
        a.a(tVar, "sources is null");
        a.a(i, "prefetch");
        return io.reactivex.e.a.a(new ObservableConcatMap(tVar, Functions.a(), i, ErrorMode.IMMEDIATE));
    }

    public static <T> q<T> a(t<? extends T>... tVarArr) {
        if (tVarArr.length == 0) {
            return d();
        }
        if (tVarArr.length == 1) {
            return b((t) tVarArr[0]);
        }
        return io.reactivex.e.a.a(new ObservableConcatMap(a((Object[]) tVarArr), Functions.a(), c(), ErrorMode.BOUNDARY));
    }

    public static <T> q<T> a(s<T> sVar) {
        a.a(sVar, "source is null");
        return io.reactivex.e.a.a(new ObservableCreate(sVar));
    }

    public static <T> q<T> a(Callable<? extends t<? extends T>> callable) {
        a.a(callable, "supplier is null");
        return io.reactivex.e.a.a(new b(callable));
    }

    public static <T> q<T> d() {
        return io.reactivex.e.a.a(g.a);
    }

    public static <T> q<T> b(Callable<? extends Throwable> callable) {
        a.a(callable, "errorSupplier is null");
        return io.reactivex.e.a.a(new io.reactivex.internal.operators.observable.h(callable));
    }

    public static <T> q<T> a(Throwable th) {
        a.a(th, "exception is null");
        return b((Callable<? extends Throwable>) Functions.a(th));
    }

    public static <T> q<T> a(T... tArr) {
        a.a(tArr, "items is null");
        if (tArr.length == 0) {
            return d();
        }
        if (tArr.length == 1) {
            return a((Object) tArr[0]);
        }
        return io.reactivex.e.a.a(new j(tArr));
    }

    public static <T> q<T> a(Iterable<? extends T> iterable) {
        a.a(iterable, "source is null");
        return io.reactivex.e.a.a(new k(iterable));
    }

    public static q<Long> a(long j, long j2, TimeUnit timeUnit) {
        return a(j, j2, timeUnit, io.reactivex.f.a.a());
    }

    public static q<Long> a(long j, long j2, TimeUnit timeUnit, w wVar) {
        a.a(timeUnit, "unit is null");
        a.a(wVar, "scheduler is null");
        return io.reactivex.e.a.a(new ObservableInterval(Math.max(0L, j), Math.max(0L, j2), timeUnit, wVar));
    }

    public static <T> q<T> a(T t) {
        a.a((Object) t, "item is null");
        return io.reactivex.e.a.a((q) new o(t));
    }

    public static <T> q<T> a(t<? extends T> tVar, t<? extends T> tVar2) {
        a.a(tVar, "source1 is null");
        a.a(tVar2, "source2 is null");
        return a((Object[]) new t[]{tVar, tVar2}).a(Functions.a(), false, 2);
    }

    public static q<Integer> a(int i, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("count >= 0 required but it was " + i2);
        } else if (i2 == 0) {
            return d();
        } else {
            if (i2 == 1) {
                return a(Integer.valueOf(i));
            }
            if (((long) i) + ((long) (i2 - 1)) <= 2147483647L) {
                return io.reactivex.e.a.a(new ObservableRange(i, i2));
            }
            throw new IllegalArgumentException("Integer overflow");
        }
    }

    public static q<Long> a(long j, TimeUnit timeUnit) {
        return a(j, timeUnit, io.reactivex.f.a.a());
    }

    public static q<Long> a(long j, TimeUnit timeUnit, w wVar) {
        a.a(timeUnit, "unit is null");
        a.a(wVar, "scheduler is null");
        return io.reactivex.e.a.a(new ObservableTimer(Math.max(j, 0L), timeUnit, wVar));
    }

    public static <T> q<T> b(t<T> tVar) {
        a.a(tVar, "source is null");
        if (tVar instanceof q) {
            return io.reactivex.e.a.a((q) tVar);
        }
        return io.reactivex.e.a.a(new l(tVar));
    }

    public static <T1, T2, R> q<R> b(t<? extends T1> tVar, t<? extends T2> tVar2, c<? super T1, ? super T2, ? extends R> cVar) {
        a.a(tVar, "source1 is null");
        a.a(tVar2, "source2 is null");
        return a(Functions.a((c) cVar), false, c(), tVar, tVar2);
    }

    public static <T1, T2, T3, R> q<R> a(t<? extends T1> tVar, t<? extends T2> tVar2, t<? extends T3> tVar3, i<? super T1, ? super T2, ? super T3, ? extends R> iVar) {
        a.a(tVar, "source1 is null");
        a.a(tVar2, "source2 is null");
        a.a(tVar3, "source3 is null");
        return a(Functions.a((i) iVar), false, c(), tVar, tVar2, tVar3);
    }

    public static <T, R> q<R> a(h<? super Object[], ? extends R> hVar, boolean z, int i, t<? extends T>... tVarArr) {
        if (tVarArr.length == 0) {
            return d();
        }
        a.a(hVar, "zipper is null");
        a.a(i, "bufferSize");
        return io.reactivex.e.a.a(new ObservableZip(tVarArr, null, hVar, i, z));
    }

    public final q<List<T>> a(int i) {
        return b(i, i);
    }

    public final q<List<T>> b(int i, int i2) {
        return a(i, i2, ArrayListSupplier.asCallable());
    }

    public final <U extends Collection<? super T>> q<U> a(int i, int i2, Callable<U> callable) {
        a.a(i, "count");
        a.a(i2, "skip");
        a.a(callable, "bufferSupplier is null");
        return io.reactivex.e.a.a(new ObservableBuffer(this, i, i2, callable));
    }

    public final <U> q<U> a(Class<U> cls) {
        a.a(cls, "clazz is null");
        return b(Functions.a((Class) cls));
    }

    public final <R> q<R> a(u<? super T, ? extends R> uVar) {
        return b(((u) a.a(uVar, "composer is null")).a(this));
    }

    public final q<T> b(long j, TimeUnit timeUnit) {
        return b(j, timeUnit, io.reactivex.f.a.a());
    }

    public final q<T> b(long j, TimeUnit timeUnit, w wVar) {
        a.a(timeUnit, "unit is null");
        a.a(wVar, "scheduler is null");
        return io.reactivex.e.a.a(new ObservableDebounceTimed(this, j, timeUnit, wVar));
    }

    public final q<T> b(T t) {
        a.a((Object) t, "defaultItem is null");
        return d(a((Object) t));
    }

    public final q<T> c(long j, TimeUnit timeUnit) {
        return a(j, timeUnit, io.reactivex.f.a.a(), false);
    }

    public final q<T> a(long j, TimeUnit timeUnit, w wVar, boolean z) {
        a.a(timeUnit, "unit is null");
        a.a(wVar, "scheduler is null");
        return io.reactivex.e.a.a(new io.reactivex.internal.operators.observable.c(this, j, timeUnit, wVar, z));
    }

    public final q<T> a(io.reactivex.c.g<? super T> gVar) {
        a.a(gVar, "onAfterNext is null");
        return io.reactivex.e.a.a(new d(this, gVar));
    }

    public final q<T> a(io.reactivex.c.a aVar) {
        return a(Functions.b(), Functions.b(), aVar, Functions.c);
    }

    private q<T> a(io.reactivex.c.g<? super T> gVar, io.reactivex.c.g<? super Throwable> gVar2, io.reactivex.c.a aVar, io.reactivex.c.a aVar2) {
        a.a(gVar, "onNext is null");
        a.a(gVar2, "onError is null");
        a.a(aVar, "onComplete is null");
        a.a(aVar2, "onAfterTerminate is null");
        return io.reactivex.e.a.a(new e(this, gVar, gVar2, aVar, aVar2));
    }

    public final q<T> b(io.reactivex.c.g<? super Throwable> gVar) {
        return a(Functions.b(), gVar, Functions.c, Functions.c);
    }

    public final q<T> a(io.reactivex.c.g<? super io.reactivex.disposables.b> gVar, io.reactivex.c.a aVar) {
        a.a(gVar, "onSubscribe is null");
        a.a(aVar, "onDispose is null");
        return io.reactivex.e.a.a(new f(this, gVar, aVar));
    }

    public final q<T> c(io.reactivex.c.g<? super T> gVar) {
        return a(gVar, Functions.b(), Functions.c, Functions.c);
    }

    public final q<T> d(io.reactivex.c.g<? super io.reactivex.disposables.b> gVar) {
        return a(gVar, Functions.c);
    }

    public final q<T> a(io.reactivex.c.k<? super T> kVar) {
        a.a(kVar, "predicate is null");
        return io.reactivex.e.a.a(new io.reactivex.internal.operators.observable.i(this, kVar));
    }

    public final <R> q<R> a(h<? super T, ? extends t<? extends R>> hVar) {
        return a((h) hVar, false);
    }

    public final <R> q<R> a(h<? super T, ? extends t<? extends R>> hVar, boolean z) {
        return a(hVar, z, Integer.MAX_VALUE);
    }

    public final <R> q<R> a(h<? super T, ? extends t<? extends R>> hVar, boolean z, int i) {
        return a(hVar, z, i, c());
    }

    public final <R> q<R> a(h<? super T, ? extends t<? extends R>> hVar, boolean z, int i, int i2) {
        a.a(hVar, "mapper is null");
        a.a(i, "maxConcurrency");
        a.a(i2, "bufferSize");
        if (!(this instanceof io.reactivex.internal.a.f)) {
            return io.reactivex.e.a.a(new ObservableFlatMap(this, hVar, z, i, i2));
        }
        Object call = ((io.reactivex.internal.a.f) this).call();
        if (call == null) {
            return d();
        }
        return ObservableScalarXMap.a(call, hVar);
    }

    public final a e() {
        return io.reactivex.e.a.a(new n(this));
    }

    public final <R> q<R> b(h<? super T, ? extends R> hVar) {
        a.a(hVar, "mapper is null");
        return io.reactivex.e.a.a(new p(this, hVar));
    }

    public final q<T> a(w wVar) {
        return a(wVar, false, c());
    }

    public final q<T> a(w wVar, boolean z, int i) {
        a.a(wVar, "scheduler is null");
        a.a(i, "bufferSize");
        return io.reactivex.e.a.a(new ObservableObserveOn(this, wVar, z, i));
    }

    public final <U> q<U> b(Class<U> cls) {
        a.a(cls, "clazz is null");
        return a(Functions.b((Class) cls)).a((Class) cls);
    }

    public final q<T> c(h<? super Throwable, ? extends t<? extends T>> hVar) {
        a.a(hVar, "resumeFunction is null");
        return io.reactivex.e.a.a(new io.reactivex.internal.operators.observable.q(this, hVar, false));
    }

    public final q<T> c(t<? extends T> tVar) {
        a.a(tVar, "next is null");
        return c(Functions.b(tVar));
    }

    public final q<T> d(h<? super Throwable, ? extends T> hVar) {
        a.a(hVar, "valueSupplier is null");
        return io.reactivex.e.a.a(new r(this, hVar));
    }

    public final io.reactivex.d.a<T> f() {
        return ObservablePublish.e(this);
    }

    public final k<T> a(c<T, T, T> cVar) {
        a.a(cVar, "reducer is null");
        return io.reactivex.e.a.a(new t(this, cVar));
    }

    public final q<T> e(h<? super q<Throwable>, ? extends t<?>> hVar) {
        a.a(hVar, "handler is null");
        return io.reactivex.e.a.a(new ObservableRetryWhen(this, hVar));
    }

    public final void b(v<? super T> vVar) {
        a.a(vVar, "observer is null");
        if (vVar instanceof io.reactivex.observers.b) {
            subscribe(vVar);
        } else {
            subscribe(new io.reactivex.observers.b(vVar));
        }
    }

    public final q<T> d(long j, TimeUnit timeUnit) {
        return c(j, timeUnit, io.reactivex.f.a.a());
    }

    public final q<T> c(long j, TimeUnit timeUnit, w wVar) {
        a.a(timeUnit, "unit is null");
        a.a(wVar, "scheduler is null");
        return io.reactivex.e.a.a(new ObservableSampleTimed(this, j, timeUnit, wVar, false));
    }

    public final q<T> g() {
        return f().b();
    }

    public final k<T> h() {
        return io.reactivex.e.a.a(new u(this));
    }

    public final x<T> i() {
        return io.reactivex.e.a.a(new v(this, null));
    }

    public final q<T> c(T t) {
        a.a((Object) t, "item is null");
        return a(a((Object) t), this);
    }

    public final io.reactivex.disposables.b e(io.reactivex.c.g<? super T> gVar) {
        return a(gVar, Functions.f, Functions.c, Functions.b());
    }

    public final io.reactivex.disposables.b a(io.reactivex.c.g<? super T> gVar, io.reactivex.c.g<? super Throwable> gVar2) {
        return a(gVar, gVar2, Functions.c, Functions.b());
    }

    public final io.reactivex.disposables.b a(io.reactivex.c.g<? super T> gVar, io.reactivex.c.g<? super Throwable> gVar2, io.reactivex.c.a aVar, io.reactivex.c.g<? super io.reactivex.disposables.b> gVar3) {
        a.a(gVar, "onNext is null");
        a.a(gVar2, "onError is null");
        a.a(aVar, "onComplete is null");
        a.a(gVar3, "onSubscribe is null");
        LambdaObserver lambdaObserver = new LambdaObserver(gVar, gVar2, aVar, gVar3);
        subscribe(lambdaObserver);
        return lambdaObserver;
    }

    @Override // io.reactivex.t
    public final void subscribe(v<? super T> vVar) {
        a.a(vVar, "observer is null");
        try {
            v a = io.reactivex.e.a.a(this, vVar);
            a.a(a, "The RxJavaPlugins.onSubscribe hook returned a null Observer. Please change the handler provided to RxJavaPlugins.setOnObservableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            a(a);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            io.reactivex.e.a.a(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final q<T> b(w wVar) {
        a.a(wVar, "scheduler is null");
        return io.reactivex.e.a.a(new ObservableSubscribeOn(this, wVar));
    }

    public final q<T> d(t<? extends T> tVar) {
        a.a(tVar, "other is null");
        return io.reactivex.e.a.a(new w(this, tVar));
    }

    public final q<T> b(io.reactivex.c.k<? super T> kVar) {
        a.a(kVar, "stopPredicate is null");
        return io.reactivex.e.a.a(new x(this, kVar));
    }

    public final q<T> e(long j, TimeUnit timeUnit) {
        return d(j, timeUnit, io.reactivex.f.a.a());
    }

    public final q<T> d(long j, TimeUnit timeUnit, w wVar) {
        a.a(timeUnit, "unit is null");
        a.a(wVar, "scheduler is null");
        return io.reactivex.e.a.a(new ObservableThrottleFirstTimed(this, j, timeUnit, wVar));
    }

    public final q<T> f(long j, TimeUnit timeUnit) {
        return b(j, timeUnit);
    }

    public final q<T> g(long j, TimeUnit timeUnit) {
        return a(j, timeUnit, (t) null, io.reactivex.f.a.a());
    }

    private q<T> a(long j, TimeUnit timeUnit, t<? extends T> tVar, w wVar) {
        a.a(timeUnit, "timeUnit is null");
        a.a(wVar, "scheduler is null");
        return io.reactivex.e.a.a(new ObservableTimeoutTimed(this, j, timeUnit, wVar, tVar));
    }

    public final g<T> a(BackpressureStrategy backpressureStrategy) {
        io.reactivex.internal.operators.flowable.d dVar = new io.reactivex.internal.operators.flowable.d(this);
        int i = AnonymousClass1.a[backpressureStrategy.ordinal()];
        if (i == 1) {
            return dVar.d();
        }
        if (i == 2) {
            return dVar.e();
        }
        if (i == 3) {
            return dVar;
        }
        if (i != 4) {
            return dVar.c();
        }
        return io.reactivex.e.a.a(new FlowableOnBackpressureError(dVar));
    }

    /* compiled from: Observable */
    /* renamed from: io.reactivex.q$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[BackpressureStrategy.values().length];

        static {
            try {
                a[BackpressureStrategy.DROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[BackpressureStrategy.LATEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[BackpressureStrategy.MISSING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[BackpressureStrategy.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public final <U, R> q<R> a(t<? extends U> tVar, c<? super T, ? super U, ? extends R> cVar) {
        a.a(tVar, "other is null");
        return b(this, tVar, cVar);
    }
}
