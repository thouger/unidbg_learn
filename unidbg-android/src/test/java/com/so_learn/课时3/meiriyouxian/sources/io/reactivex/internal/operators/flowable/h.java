package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.ScalarSubscription;
import java.util.concurrent.Callable;
import org.a.b;
import org.a.c;

/* compiled from: FlowableScalarXMap */
public final class h {
    public static <T, R> boolean a(b<T> bVar, c<? super R> cVar, io.reactivex.c.h<? super T, ? extends b<? extends R>> hVar) {
        if (!(bVar instanceof Callable)) {
            return false;
        }
        try {
            Object call = ((Callable) bVar).call();
            if (call == null) {
                EmptySubscription.complete(cVar);
                return true;
            }
            try {
                Callable callable = (b) io.reactivex.internal.functions.a.a(hVar.apply(call), "The mapper returned a null Publisher");
                if (callable instanceof Callable) {
                    try {
                        Object call2 = callable.call();
                        if (call2 == null) {
                            EmptySubscription.complete(cVar);
                            return true;
                        }
                        cVar.onSubscribe(new ScalarSubscription(cVar, call2));
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        EmptySubscription.error(th, cVar);
                        return true;
                    }
                } else {
                    callable.subscribe(cVar);
                }
                return true;
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                EmptySubscription.error(th2, cVar);
                return true;
            }
        } catch (Throwable th3) {
            io.reactivex.exceptions.a.b(th3);
            EmptySubscription.error(th3, cVar);
            return true;
        }
    }

    public static <T, U> g<U> a(T t, io.reactivex.c.h<? super T, ? extends b<? extends U>> hVar) {
        return io.reactivex.e.a.a(new a(t, hVar));
    }

    /* compiled from: FlowableScalarXMap */
    /* access modifiers changed from: package-private */
    public static final class a<T, R> extends g<R> {
        final T b;
        final io.reactivex.c.h<? super T, ? extends b<? extends R>> c;

        a(T t, io.reactivex.c.h<? super T, ? extends b<? extends R>> hVar) {
            this.b = t;
            this.c = hVar;
        }

        @Override // io.reactivex.g
        public void a(c<? super R> cVar) {
            try {
                Callable callable = (b) io.reactivex.internal.functions.a.a(this.c.apply(this.b), "The mapper returned a null Publisher");
                if (callable instanceof Callable) {
                    try {
                        Object call = callable.call();
                        if (call == null) {
                            EmptySubscription.complete(cVar);
                        } else {
                            cVar.onSubscribe(new ScalarSubscription(cVar, call));
                        }
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        EmptySubscription.error(th, cVar);
                    }
                } else {
                    callable.subscribe(cVar);
                }
            } catch (Throwable th2) {
                EmptySubscription.error(th2, cVar);
            }
        }
    }
}
