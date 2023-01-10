package cn.missfresh.module.base.network;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bangcle.andjni.JniLib;
import io.reactivex.c.c;
import io.reactivex.c.h;
import io.reactivex.q;
import io.reactivex.t;
import java.util.concurrent.TimeUnit;

/* compiled from: NormalRetryWithDelay */
public class n implements h<q<? extends Throwable>, q<?>> {
    private int a;
    private int b;

    public n() {
        JniLib.cV(this, 186);
    }

    public n(int i, int i2) {
        JniLib.cV(this, Integer.valueOf(i), Integer.valueOf(i2), 187);
    }

    public q<?> a(q<? extends Throwable> qVar) throws Exception {
        return (q) JniLib.cL(this, qVar, 185);
    }

    @Override // io.reactivex.c.h
    public /* synthetic */ Object apply(Object obj) throws Exception {
        AppMethodBeat.i(15688, false);
        q<?> a2 = a((q) obj);
        AppMethodBeat.o(15688);
        return a2;
    }

    /* compiled from: NormalRetryWithDelay */
    /* renamed from: cn.missfresh.module.base.network.n$2  reason: invalid class name */
    class AnonymousClass2 implements c<Throwable, Integer, a> {
        final /* synthetic */ n a;

        AnonymousClass2(n nVar) {
            JniLib.cV(this, nVar, 183);
        }

        public a a(Throwable th, Integer num) throws Exception {
            return (a) JniLib.cL(this, th, num, 182);
        }

        @Override // io.reactivex.c.c
        public /* synthetic */ Object apply(Object obj, Object obj2) throws Exception {
            AppMethodBeat.i(15660, false);
            a a = a((Throwable) obj, (Integer) obj2);
            AppMethodBeat.o(15660);
            return a;
        }
    }

    /* compiled from: NormalRetryWithDelay */
    /* renamed from: cn.missfresh.module.base.network.n$1  reason: invalid class name */
    class AnonymousClass1 implements h<a, t<?>> {
        final /* synthetic */ n a;

        AnonymousClass1(n nVar) {
            JniLib.cV(this, nVar, 181);
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(15653, false);
            t<?> a = a((a) obj);
            AppMethodBeat.o(15653);
            return a;
        }

        public t<?> a(a aVar) throws Exception {
            AppMethodBeat.i(15650, false);
            if (aVar.b() >= this.a.a) {
                q a = q.a(aVar.a());
                AppMethodBeat.o(15650);
                return a;
            }
            q<Long> a2 = q.a((long) this.a.b, TimeUnit.SECONDS);
            AppMethodBeat.o(15650);
            return a2;
        }
    }

    /* compiled from: NormalRetryWithDelay */
    /* access modifiers changed from: private */
    public class a {
        Throwable a;
        int b;
        final /* synthetic */ n c;

        public a(n nVar, Throwable th, int i) {
            JniLib.cV(this, nVar, th, Integer.valueOf(i), 184);
        }

        public Throwable a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }
    }
}
