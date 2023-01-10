package cn.missfresh.basiclib.net;

import cn.missfresh.basiclib.net.error.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.reactivex.observers.a;

/* compiled from: ResultObserver */
public class c<T> extends a<T> {
    private cn.missfresh.basiclib.net.a.a<T> a;

    public c(cn.missfresh.basiclib.net.a.a<T> aVar) {
        this.a = aVar;
    }

    @Override // io.reactivex.v
    public void onNext(T t) {
        AppMethodBeat.i(3333, false);
        b.a((cn.missfresh.basiclib.net.a.a) this.a, (Object) t);
        AppMethodBeat.o(3333);
    }

    @Override // io.reactivex.v
    public void onError(Throwable th) {
        AppMethodBeat.i(3338, false);
        b.a((cn.missfresh.basiclib.net.a.a) this.a, th);
        AppMethodBeat.o(3338);
    }

    @Override // io.reactivex.v
    public void onComplete() {
        AppMethodBeat.i(3341, false);
        b.a(this.a);
        AppMethodBeat.o(3341);
    }
}
