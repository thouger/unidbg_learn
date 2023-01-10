package cn.missfresh.module.base.utils.a;

import android.os.SystemClock;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.reactivex.a.b.a;
import io.reactivex.c.h;
import io.reactivex.c.k;
import io.reactivex.q;
import io.reactivex.t;
import io.reactivex.u;
import java.util.concurrent.TimeUnit;

/* compiled from: CountdownTransformer */
/* access modifiers changed from: package-private */
public class b implements u<Long, Long> {
    private long a;
    private long b;
    private long c;

    b(long j, long j2) {
        AppMethodBeat.i(23588, false);
        this.a = j;
        this.b = j2;
        this.c = SystemClock.elapsedRealtime() + j2;
        AppMethodBeat.o(23588);
    }

    @Override // io.reactivex.u
    public t<Long> a(q<Long> qVar) {
        AppMethodBeat.i(23589, false);
        q a = qVar.d(this.a, TimeUnit.MILLISECONDS).b(new AnonymousClass2()).b((k) new AnonymousClass1()).a(a.a());
        AppMethodBeat.o(23589);
        return a;
    }

    /* compiled from: CountdownTransformer */
    /* renamed from: cn.missfresh.module.base.utils.a.b$2  reason: invalid class name */
    class AnonymousClass2 implements h<Long, Long> {
        AnonymousClass2() {
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(23587, false);
            Long a = a((Long) obj);
            AppMethodBeat.o(23587);
            return a;
        }

        public Long a(Long l) throws Exception {
            AppMethodBeat.i(23586, false);
            if (b.this.c - l.longValue() <= 0) {
                AppMethodBeat.o(23586);
                return 0L;
            }
            Long valueOf = Long.valueOf(b.this.c - l.longValue());
            AppMethodBeat.o(23586);
            return valueOf;
        }
    }

    /* compiled from: CountdownTransformer */
    /* renamed from: cn.missfresh.module.base.utils.a.b$1  reason: invalid class name */
    class AnonymousClass1 implements k<Long> {
        AnonymousClass1() {
        }

        @Override // io.reactivex.c.k
        public /* synthetic */ boolean test(Object obj) throws Exception {
            AppMethodBeat.i(23585, false);
            boolean a = a((Long) obj);
            AppMethodBeat.o(23585);
            return a;
        }

        public boolean a(Long l) throws Exception {
            boolean z = false;
            AppMethodBeat.i(23584, false);
            if (b.this.b <= 0) {
                AppMethodBeat.o(23584);
                return true;
            }
            if (l.longValue() <= 0) {
                z = true;
            }
            AppMethodBeat.o(23584);
            return z;
        }
    }
}
