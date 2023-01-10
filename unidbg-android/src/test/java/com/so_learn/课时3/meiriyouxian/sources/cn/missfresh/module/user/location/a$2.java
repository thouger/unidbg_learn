package cn.missfresh.module.user.location;

import android.os.Looper;
import cn.missfresh.map.c;
import cn.missfresh.map.d;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.reactivex.r;
import io.reactivex.s;

/* compiled from: DefaultAddressManager */
class a$2 implements s<c> {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    @Override // io.reactivex.s
    public void a(r<c> rVar) throws Exception {
        AppMethodBeat.i(7287, false);
        a.a(this.a, rVar);
        Looper.prepare();
        d.a().b().a(this.a);
        int a = d.a().b().a();
        cn.missfresh.utils.a.d.d("DefaultAddressManager", "init result:" + a);
        a.b(this.a, (long) a);
        Looper.loop();
        AppMethodBeat.o(7287);
    }
}
