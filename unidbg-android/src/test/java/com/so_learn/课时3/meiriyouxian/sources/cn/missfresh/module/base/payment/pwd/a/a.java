package cn.missfresh.module.base.payment.pwd.a;

import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.network.m;
import cn.missfresh.module.base.payment.pwd.view.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bangcle.andjni.JniLib;
import java.util.Map;
import okhttp3.Request;

/* compiled from: PassWordInteractor */
public class a {
    public a() {
        JniLib.cV(this, 233);
    }

    public void a() {
        JniLib.cV(this, 231);
    }

    public void a(c cVar) {
        JniLib.cV(this, cVar, 232);
    }

    /* compiled from: PassWordInteractor */
    /* renamed from: cn.missfresh.module.base.payment.pwd.a.a$1  reason: invalid class name */
    class AnonymousClass1 extends m {
        final /* synthetic */ c a;
        final /* synthetic */ boolean b;

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            JniLib.cV(this, Integer.valueOf(i), 227);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            JniLib.cV(this, str, 228);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            JniLib.cV(this, request, exc, 229);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void b() {
            JniLib.cV(this, 230);
        }

        AnonymousClass1(c cVar, boolean z) {
            this.a = cVar;
            this.b = z;
        }
    }

    public void a(c cVar, boolean z) {
        AppMethodBeat.i(16371, false);
        cn.missfresh.module.base.network.c.a(this, i.ak, (Map<String, String>) null, new AnonymousClass1(cVar, z));
        AppMethodBeat.o(16371);
    }
}
