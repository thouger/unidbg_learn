package cn.missfresh.basiclib.utils;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.jakewharton.rxrelay2.PublishRelay;
import io.reactivex.c.g;
import io.reactivex.q;
import io.reactivex.w;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: RxBus */
public class c {
    static final g<Throwable> a = new a();
    private com.jakewharton.rxrelay2.b<Object> b;
    private com.jakewharton.rxrelay2.b<Object> c;
    private ConcurrentHashMap<Class<?>, Object> d;

    public static c a() {
        AppMethodBeat.i(4758, false);
        c cVar = b.a;
        AppMethodBeat.o(4758);
        return cVar;
    }

    /* compiled from: RxBus */
    /* access modifiers changed from: private */
    public static final class b {
        private static final c a = new c();

        static {
            AppMethodBeat.i(4751, false);
            AppMethodBeat.o(4751);
        }
    }

    private c() {
        AppMethodBeat.i(4762, false);
        this.b = PublishRelay.a().b();
        this.c = PublishRelay.a().b();
        this.d = new ConcurrentHashMap<>();
        AppMethodBeat.o(4762);
    }

    public <T> io.reactivex.disposables.b a(Class<T> cls, g<T> gVar) {
        AppMethodBeat.i(4765, false);
        io.reactivex.disposables.b a2 = a(cls, io.reactivex.a.b.a.a(), gVar);
        AppMethodBeat.o(4765);
        return a2;
    }

    public <T> io.reactivex.disposables.b a(Class<T> cls, w wVar, g<T> gVar) {
        AppMethodBeat.i(4771, false);
        io.reactivex.disposables.b a2 = a(cls, wVar, gVar, a);
        AppMethodBeat.o(4771);
        return a2;
    }

    public <T> io.reactivex.disposables.b a(Class<T> cls, w wVar, g<T> gVar, g<Throwable> gVar2) {
        AppMethodBeat.i(4774, false);
        io.reactivex.disposables.b a2 = this.b.b((Class) cls).a(wVar).a(gVar, gVar2);
        AppMethodBeat.o(4774);
        return a2;
    }

    public <T> io.reactivex.disposables.b b(Class<T> cls, g<T> gVar) {
        AppMethodBeat.i(4777, false);
        io.reactivex.disposables.b b2 = b(cls, io.reactivex.a.b.a.a(), gVar);
        AppMethodBeat.o(4777);
        return b2;
    }

    public <T> io.reactivex.disposables.b b(Class<T> cls, w wVar, g<T> gVar) {
        AppMethodBeat.i(4779, false);
        io.reactivex.disposables.b b2 = b(cls, wVar, gVar, a);
        AppMethodBeat.o(4779);
        return b2;
    }

    public <T> io.reactivex.disposables.b b(Class<T> cls, w wVar, g<T> gVar, g<Throwable> gVar2) {
        int i = 4782;
        AppMethodBeat.i(4782, false);
        synchronized (this.d) {
            try {
                Object obj = this.d.get(cls);
                if (obj != null) {
                    return this.c.b((Class) cls).c((q<U>) obj).a(wVar).a(gVar, gVar2);
                }
                io.reactivex.disposables.b a2 = this.c.b((Class) cls).a(wVar).a(gVar, gVar2);
                AppMethodBeat.o(4782);
                return a2;
            } finally {
                AppMethodBeat.o(i);
            }
        }
    }

    public void a(Object obj) {
        AppMethodBeat.i(4786, false);
        if (obj == null) {
            AppMethodBeat.o(4786);
            return;
        }
        this.b.accept(obj);
        AppMethodBeat.o(4786);
    }

    public void b(Object obj) {
        AppMethodBeat.i(4789, false);
        if (obj == null) {
            AppMethodBeat.o(4789);
            return;
        }
        synchronized (this.d) {
            try {
                this.d.put(obj.getClass(), obj);
            } catch (Throwable th) {
                AppMethodBeat.o(4789);
                throw th;
            }
        }
        this.c.accept(obj);
        AppMethodBeat.o(4789);
    }

    static {
        AppMethodBeat.i(4795, false);
        AppMethodBeat.o(4795);
    }

    /* compiled from: RxBus */
    static final class a implements g<Throwable> {
        a() {
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(4745, false);
            a((Throwable) obj);
            AppMethodBeat.o(4745);
        }

        public void a(Throwable th) throws Exception {
            AppMethodBeat.i(4742, false);
            d.b("RxBus", "throwable=" + th);
            AppMethodBeat.o(4742);
        }
    }

    public void b() {
        int i = 4791;
        AppMethodBeat.i(4791, false);
        ConcurrentHashMap<Class<?>, Object> concurrentHashMap = this.d;
        if (concurrentHashMap != null) {
            synchronized (concurrentHashMap) {
                try {
                    this.d.clear();
                } finally {
                    AppMethodBeat.o(i);
                }
            }
        }
    }
}
