package cn.missfresh.module.base.common.d;

import cn.missfresh.module.base.network.c;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.base.payment.recharge.api.RechargApiManager;
import cn.missfresh.module.base.payment.recharge.bean.StoreCardBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import java.util.List;

/* compiled from: StoreCardPresenter */
public class d implements cn.missfresh.module.mvp.a.a {
    private String a = getClass().getSimpleName();
    private a b;
    private io.reactivex.disposables.a c;

    /* compiled from: StoreCardPresenter */
    public interface a {
        void a(String str);

        void a(List<StoreCardBean> list);
    }

    public d(a aVar) {
        AppMethodBeat.i(12270, false);
        this.b = aVar;
        AppMethodBeat.o(12270);
    }

    public void a() {
        AppMethodBeat.i(12271, false);
        RechargApiManager.getRechargApi().requestStoreCards().b(io.reactivex.f.a.b()).a(io.reactivex.a.b.a.a()).subscribe(new AnonymousClass1(this));
        AppMethodBeat.o(12271);
    }

    /* compiled from: StoreCardPresenter */
    /* renamed from: cn.missfresh.module.base.common.d.d$1  reason: invalid class name */
    class AnonymousClass1 extends i<List<StoreCardBean>> {
        @Override // io.reactivex.v
        public void onComplete() {
        }

        AnonymousClass1(cn.missfresh.module.mvp.a.a aVar) {
            super(aVar);
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(12269, false);
            a((List) obj);
            AppMethodBeat.o(12269);
        }

        public void a(List<StoreCardBean> list) {
            AppMethodBeat.i(12266, false);
            if (d.this.b == null) {
                AppMethodBeat.o(12266);
            } else if (!b.a(list)) {
                d.this.b.a(list);
                AppMethodBeat.o(12266);
            } else {
                a(-1, "");
                AppMethodBeat.o(12266);
            }
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
            AppMethodBeat.i(12268, false);
            if (d.this.b != null) {
                d.this.b.a("\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25\u8bf7\u7a0d\u540e\u91cd\u8bd5");
            }
            AppMethodBeat.o(12268);
        }
    }

    public void b() {
        AppMethodBeat.i(12272, false);
        c.a(this.a);
        io.reactivex.disposables.a aVar = this.c;
        if (aVar != null) {
            aVar.dispose();
        }
        AppMethodBeat.o(12272);
    }

    public void a(io.reactivex.disposables.b bVar) {
        AppMethodBeat.i(12273, false);
        c();
        io.reactivex.disposables.a aVar = this.c;
        if (aVar != null) {
            aVar.a(bVar);
        }
        AppMethodBeat.o(12273);
    }

    private void c() {
        AppMethodBeat.i(12275, false);
        if (this.c == null) {
            this.c = new io.reactivex.disposables.a();
        }
        AppMethodBeat.o(12275);
    }
}
