package io.reactivex;

/* compiled from: Completable */
public abstract class a implements e {
    /* access modifiers changed from: protected */
    public abstract void b(c cVar);

    private static NullPointerException a(Throwable th) {
        NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        nullPointerException.initCause(th);
        return nullPointerException;
    }

    @Override // io.reactivex.e
    public final void a(c cVar) {
        io.reactivex.internal.functions.a.a(cVar, "observer is null");
        try {
            c a = io.reactivex.e.a.a(this, cVar);
            io.reactivex.internal.functions.a.a(a, "The RxJavaPlugins.onSubscribe hook returned a null CompletableObserver. Please check the handler provided to RxJavaPlugins.setOnCompletableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            b(a);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            io.reactivex.e.a.a(th);
            throw a(th);
        }
    }
}
