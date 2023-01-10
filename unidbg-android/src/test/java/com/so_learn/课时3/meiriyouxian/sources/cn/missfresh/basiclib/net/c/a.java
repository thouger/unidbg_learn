package cn.missfresh.basiclib.net.c;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.reactivex.c.c;
import io.reactivex.c.h;
import io.reactivex.q;
import io.reactivex.t;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/* compiled from: RetryWithDelay */
public class a implements h<q<? extends Throwable>, q<?>> {
    private int a;
    private int b;

    @Override // io.reactivex.c.h
    public /* synthetic */ Object apply(Object obj) throws Exception {
        AppMethodBeat.i(3768, false);
        q<?> a = a((q) obj);
        AppMethodBeat.o(3768);
        return a;
    }

    public a() {
        this(3, 2);
    }

    public a(int i) {
        this(i, 2);
    }

    public a(int i, int i2) {
        AppMethodBeat.i(3763, false);
        this.a = 0;
        this.b = 0;
        this.a = i;
        this.b = i2 >= 2 ? i2 : 2;
        AppMethodBeat.o(3763);
    }

    /* compiled from: RetryWithDelay */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.basiclib.net.c.a$2  reason: invalid class name */
    public class AnonymousClass2 implements c<Throwable, Integer, C0017a> {
        AnonymousClass2() {
        }

        @Override // io.reactivex.c.c
        public /* synthetic */ Object apply(Object obj, Object obj2) throws Exception {
            AppMethodBeat.i(3738, false);
            C0017a a = a((Throwable) obj, (Integer) obj2);
            AppMethodBeat.o(3738);
            return a;
        }

        public C0017a a(Throwable th, Integer num) throws Exception {
            AppMethodBeat.i(3736, false);
            if ((th instanceof UnknownHostException) || !(th instanceof IOException)) {
                a aVar = a.this;
                C0017a aVar2 = new C0017a(th, aVar.a + 1);
                AppMethodBeat.o(3736);
                return aVar2;
            }
            C0017a aVar3 = new C0017a(th, num.intValue());
            AppMethodBeat.o(3736);
            return aVar3;
        }
    }

    public q<?> a(q<? extends Throwable> qVar) throws Exception {
        AppMethodBeat.i(3765, false);
        q<?> a = qVar.a(q.a(1, this.a), new AnonymousClass2()).a((h) new AnonymousClass1());
        AppMethodBeat.o(3765);
        return a;
    }

    /* compiled from: RetryWithDelay */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.basiclib.net.c.a$1  reason: invalid class name */
    public class AnonymousClass1 implements h<C0017a, t<?>> {
        AnonymousClass1() {
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(3731, false);
            t<?> a = a((C0017a) obj);
            AppMethodBeat.o(3731);
            return a;
        }

        public t<?> a(C0017a aVar) throws Exception {
            AppMethodBeat.i(3727, false);
            if (aVar.b() >= a.this.a) {
                q a = q.a(aVar.a());
                AppMethodBeat.o(3727);
                return a;
            }
            q<Long> a2 = q.a((long) Math.pow((double) a.this.b, (double) aVar.b()), TimeUnit.SECONDS);
            AppMethodBeat.o(3727);
            return a2;
        }
    }

    /* compiled from: RetryWithDelay */
    /* access modifiers changed from: private */
    /* renamed from: cn.missfresh.basiclib.net.c.a$a  reason: collision with other inner class name */
    public class C0017a {
        Throwable a;
        int b;

        public C0017a(Throwable th, int i) {
            this.a = th;
            this.b = i;
        }

        public Throwable a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }
    }
}
