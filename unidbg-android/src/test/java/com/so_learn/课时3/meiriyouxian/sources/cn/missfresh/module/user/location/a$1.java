package cn.missfresh.module.user.location;

import cn.missfresh.map.c;
import cn.missfresh.module.base.common.a.a;
import cn.missfresh.module.user.location.bean.TencentLocationBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import io.reactivex.disposables.b;
import io.reactivex.v;

/* compiled from: DefaultAddressManager */
class a$1 implements v<c> {
    final /* synthetic */ a a;

    @Override // io.reactivex.v
    public void onComplete() {
    }

    a$1(a aVar) {
        this.a = aVar;
    }

    @Override // io.reactivex.v
    public /* synthetic */ void onNext(Object obj) {
        AppMethodBeat.i(7282, false);
        a((c) obj);
        AppMethodBeat.o(7282);
    }

    @Override // io.reactivex.v
    public void onSubscribe(b bVar) {
        AppMethodBeat.i(7276, false);
        a.a(this.a, bVar);
        AppMethodBeat.o(7276);
    }

    public void a(c cVar) {
        AppMethodBeat.i(7277, false);
        a.a(this.a, System.currentTimeMillis() - a.a(this.a));
        if (cVar != null) {
            if (a.b()) {
                d.d("DefaultAddressManager", "no need request tencent poi");
                a.a(this.a, 1, "\u76f4\u63a5\u4f7f\u7528\u5b9a\u4f4d\u4fe1\u606f\u4e0d\u8bf7\u6c42\u7535\u5b50\u56f4\u680f");
                a.a(this.a, cVar.d(), cVar.b() + "", cVar.a() + "", cVar.e(), cVar.c());
            } else {
                d.d("DefaultAddressManager", "request tencent poi");
                a.a(this.a, cVar.b(), cVar.a(), cVar.d());
            }
            a.a(this.a, 3, "cityCode:" + cVar.c() + ",Latitude:" + cVar.b() + ",Longitude:" + cVar.a());
        } else {
            a.a(this.a, new TencentLocationBean());
            a.b(this.a).setState(0);
            a.c(this.a);
            d.d("DefaultAddressManager", "requestTencentLocation:fail");
        }
        AppMethodBeat.o(7277);
    }

    @Override // io.reactivex.v
    public void onError(Throwable th) {
        String str;
        AppMethodBeat.i(7279, false);
        a.a(this.a, new TencentLocationBean());
        a.b(this.a).setState(0);
        a.c(this.a);
        if (a.d(this.a) != null) {
            str = "desc:" + a.d(this.a).c() + ",name" + a.d(this.a).a() + ",status" + a.d(this.a).b();
        } else {
            str = "";
        }
        a.a(this.a, 4, "onError : " + th.toString() + ",locationState:" + str);
        AppMethodBeat.o(7279);
    }
}
