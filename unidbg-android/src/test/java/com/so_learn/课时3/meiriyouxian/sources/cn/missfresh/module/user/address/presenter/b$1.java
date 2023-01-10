package cn.missfresh.module.user.address.presenter;

import cn.missfresh.module.base.bean.TencentSearchData;
import cn.missfresh.module.base.manager.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import java.util.List;

/* compiled from: SearchAddressDetailPresenter */
class b$1 implements q.a {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ b c;

    b$1(b bVar, String str, String str2) {
        this.c = bVar;
        this.a = str;
        this.b = str2;
    }

    @Override // cn.missfresh.module.base.manager.q.a
    public void a() {
        AppMethodBeat.i(4565, false);
        b.a(this.c).c();
        AppMethodBeat.o(4565);
    }

    @Override // cn.missfresh.module.base.manager.q.a
    public void a(String str, List<TencentSearchData> list, int i) {
        AppMethodBeat.i(4569, false);
        if (b.b(this.c)) {
            d.d(b.c(this.c), "isNewPosition");
            b.d(this.c).c();
            b.a(this.c).W_();
            b.d(this.c).a(1);
            b.a(this.c).e();
            this.c.a(this.a, this.b);
        } else {
            b.a(this.c, this.a, this.b);
        }
        b.a(this.c).j();
        b.d(this.c).a(list);
        if (list.size() < q.a) {
            b.a(this.c).V_();
        } else {
            b.a(this.c).b();
        }
        b.a(this.c, false);
        b.d(this.c).b(i);
        b.d(this.c).a(b.d(this.c).d() + 1);
        AppMethodBeat.o(4569);
    }

    @Override // cn.missfresh.module.base.manager.q.a
    public void a(String str) {
        AppMethodBeat.i(4570, false);
        if (b.b(this.c)) {
            b.a(this.c, false);
        }
        if (b.a(this.c, str)) {
            b.d(this.c).c();
            b.a(this.c).W_();
        }
        b.a(this.c, this.a, this.b);
        b.a(this.c).j();
        b.a(this.c).d();
        AppMethodBeat.o(4570);
    }
}
