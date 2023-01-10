package cn.missfresh.basiclib.net.error;

import cn.missfresh.basiclib.net.a.a;
import cn.missfresh.basiclib.net.bean.ErrorBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ResultDispatch */
public class b {
    public static <T> void a(a<T> aVar, T t) {
        AppMethodBeat.i(3710, false);
        if (aVar == null) {
            AppMethodBeat.o(3710);
            return;
        }
        aVar.onSuccess(t);
        AppMethodBeat.o(3710);
    }

    public static void a(a aVar, Throwable th) {
        AppMethodBeat.i(3714, false);
        if (aVar == null) {
            AppMethodBeat.o(3714);
            return;
        }
        ErrorBean a = a.a(th);
        aVar.onFail(a.getCode(), a.getMessage());
        aVar.onComplete();
        AppMethodBeat.o(3714);
    }

    public static void a(a aVar) {
        AppMethodBeat.i(3717, false);
        if (aVar == null) {
            AppMethodBeat.o(3717);
            return;
        }
        aVar.onComplete();
        AppMethodBeat.o(3717);
    }
}
