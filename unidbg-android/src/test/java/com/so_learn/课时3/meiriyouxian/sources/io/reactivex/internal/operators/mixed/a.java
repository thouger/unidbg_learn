package io.reactivex.internal.operators.mixed;

import io.reactivex.ab;
import io.reactivex.c;
import io.reactivex.c.h;
import io.reactivex.e;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.operators.maybe.MaybeToObservable;
import io.reactivex.internal.operators.single.SingleToObservable;
import io.reactivex.o;
import io.reactivex.v;
import java.util.concurrent.Callable;

/* compiled from: ScalarXMapZHelper */
final class a {
    static <T> boolean a(Object obj, h<? super T, ? extends e> hVar, c cVar) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        e eVar = null;
        try {
            Object call = ((Callable) obj).call();
            if (call != null) {
                eVar = (e) io.reactivex.internal.functions.a.a(hVar.apply(call), "The mapper returned a null CompletableSource");
            }
            if (eVar == null) {
                EmptyDisposable.complete(cVar);
            } else {
                eVar.a(cVar);
            }
            return true;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, cVar);
            return true;
        }
    }

    static <T, R> boolean a(Object obj, h<? super T, ? extends o<? extends R>> hVar, v<? super R> vVar) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        o oVar = null;
        try {
            Object call = ((Callable) obj).call();
            if (call != null) {
                oVar = (o) io.reactivex.internal.functions.a.a(hVar.apply(call), "The mapper returned a null MaybeSource");
            }
            if (oVar == null) {
                EmptyDisposable.complete(vVar);
            } else {
                oVar.a(MaybeToObservable.c((v) vVar));
            }
            return true;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, vVar);
            return true;
        }
    }

    static <T, R> boolean b(Object obj, h<? super T, ? extends ab<? extends R>> hVar, v<? super R> vVar) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        ab abVar = null;
        try {
            Object call = ((Callable) obj).call();
            if (call != null) {
                abVar = (ab) io.reactivex.internal.functions.a.a(hVar.apply(call), "The mapper returned a null SingleSource");
            }
            if (abVar == null) {
                EmptyDisposable.complete(vVar);
            } else {
                abVar.a(SingleToObservable.c((v) vVar));
            }
            return true;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, vVar);
            return true;
        }
    }
}
