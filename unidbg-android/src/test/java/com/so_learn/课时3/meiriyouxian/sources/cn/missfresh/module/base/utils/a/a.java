package cn.missfresh.module.base.utils.a;

import android.os.SystemClock;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.reactivex.c.h;
import io.reactivex.q;
import java.util.concurrent.TimeUnit;

/* compiled from: CountDownUtils */
public class a {
    private static volatile a a;
    private static q<Long> b;

    public static a a() {
        AppMethodBeat.i(23580, false);
        if (a == null) {
            synchronized (a.class) {
                try {
                    if (a == null) {
                        a = new a();
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(23580);
                    throw th;
                }
            }
        }
        a aVar = a;
        AppMethodBeat.o(23580);
        return aVar;
    }

    private a() {
    }

    public q<Long> b() {
        AppMethodBeat.i(23581, false);
        if (b == null) {
            b = q.a(0, 100, TimeUnit.MILLISECONDS).g().b(new AnonymousClass1());
        }
        q<Long> qVar = b;
        AppMethodBeat.o(23581);
        return qVar;
    }

    /* compiled from: CountDownUtils */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.utils.a.a$1  reason: invalid class name */
    public class AnonymousClass1 implements h<Long, Long> {
        AnonymousClass1() {
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(23579, false);
            Long a = a((Long) obj);
            AppMethodBeat.o(23579);
            return a;
        }

        public Long a(Long l) throws Exception {
            AppMethodBeat.i(23578, false);
            Long valueOf = Long.valueOf(SystemClock.elapsedRealtime());
            AppMethodBeat.o(23578);
            return valueOf;
        }
    }

    public static b a(long j) {
        AppMethodBeat.i(23582, false);
        b a2 = a(100, j);
        AppMethodBeat.o(23582);
        return a2;
    }

    public static b a(long j, long j2) {
        AppMethodBeat.i(23583, false);
        b bVar = new b(j, j2);
        AppMethodBeat.o(23583);
        return bVar;
    }
}
