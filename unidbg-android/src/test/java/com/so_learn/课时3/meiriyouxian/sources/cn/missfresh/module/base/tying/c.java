package cn.missfresh.module.base.tying;

import android.content.Context;
import cn.missfresh.module.base.api.TyingProductApiManager;
import cn.missfresh.module.base.bean.EventAppAddress;
import cn.missfresh.module.base.common.api.RequestParam;
import cn.missfresh.module.base.common.livedata.MFStickyLiveData;
import cn.missfresh.module.base.common.providers.ILocationService;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.base.tying.bean.TyingProductsBean;
import cn.missfresh.module.mvp.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import io.reactivex.c.h;
import java.util.HashMap;

/* compiled from: TyingProductService */
public class c implements ITyingProductService, a {
    private MFStickyLiveData<TyingProductsBean> a = new MFStickyLiveData<>();
    private io.reactivex.disposables.a b;
    private b c;
    private b d;
    private ILocationService e = ((ILocationService) com.alibaba.android.arouter.b.a.a().a("/user/location_service").navigation());

    public c() {
        AppMethodBeat.i(22985, false);
        AppMethodBeat.o(22985);
    }

    @Override // cn.missfresh.module.base.tying.ITyingProductService
    public void a(String str, String str2, int i, int i2, int i3, String str3) {
        AppMethodBeat.i(22986, false);
        if (this.e.d() == null || this.e.d().a() == null || ((EventAppAddress) this.e.d().a()).userLocationBean == null || ((EventAppAddress) this.e.d().a()).userLocationBean.type == 0) {
            if (i3 == 0) {
                if (!this.c.a(str, str2, i, i2)) {
                    AppMethodBeat.o(22986);
                    return;
                }
            } else if (1 != i3) {
                AppMethodBeat.o(22986);
                return;
            } else if (!this.d.a(str, str2, i, i2)) {
                AppMethodBeat.o(22986);
                return;
            }
            RequestParam requestParam = new RequestParam();
            HashMap hashMap = new HashMap();
            hashMap.put("sku", str2);
            hashMap.put("requestId", str3);
            requestParam.setParam(hashMap);
            TyingProductApiManager.getTyingProductApi().getTyingProduct(requestParam).a(cn.missfresh.basiclib.net.e.a.a).b((h) new AnonymousClass2()).subscribe(new AnonymousClass1(this, i3, str, str2, i, str3));
            AppMethodBeat.o(22986);
            return;
        }
        AppMethodBeat.o(22986);
    }

    /* compiled from: TyingProductService */
    /* renamed from: cn.missfresh.module.base.tying.c$2  reason: invalid class name */
    class AnonymousClass2 implements h<TyingProductsBean, TyingProductsBean> {
        public TyingProductsBean a(TyingProductsBean tyingProductsBean) throws Exception {
            return tyingProductsBean;
        }

        AnonymousClass2() {
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(22984, false);
            TyingProductsBean a = a((TyingProductsBean) obj);
            AppMethodBeat.o(22984);
            return a;
        }
    }

    /* compiled from: TyingProductService */
    /* renamed from: cn.missfresh.module.base.tying.c$1  reason: invalid class name */
    class AnonymousClass1 extends i<TyingProductsBean> {
        final /* synthetic */ int a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ int d;
        final /* synthetic */ String e;

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
        }

        @Override // io.reactivex.v
        public void onComplete() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass1(a aVar, int i, String str, String str2, int i2, String str3) {
            super(aVar);
            this.a = i;
            this.b = str;
            this.c = str2;
            this.d = i2;
            this.e = str3;
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(22983, false);
            a((TyingProductsBean) obj);
            AppMethodBeat.o(22983);
        }

        public void a(TyingProductsBean tyingProductsBean) {
            AppMethodBeat.i(22982, false);
            if (tyingProductsBean == null || b.a(tyingProductsBean.getProductList()) || tyingProductsBean.getProductList().size() < 3) {
                int i = this.a;
                if (i == 0) {
                    c.this.c.a(this.b, this.c, this.d);
                } else if (1 == i) {
                    c.this.d.a(this.b, this.c, this.d);
                }
                AppMethodBeat.o(22982);
                return;
            }
            tyingProductsBean.setChannel(this.b);
            tyingProductsBean.setPosition(this.d);
            tyingProductsBean.setTyingSku(this.c);
            tyingProductsBean.setRecommendRequestId(this.e);
            c.this.a.a((MFStickyLiveData) tyingProductsBean);
            AppMethodBeat.o(22982);
        }
    }

    @Override // cn.missfresh.module.base.tying.ITyingProductService
    public void a() {
        AppMethodBeat.i(22988, false);
        this.c.a();
        AppMethodBeat.o(22988);
    }

    @Override // cn.missfresh.module.base.tying.ITyingProductService
    public void a(String str) {
        AppMethodBeat.i(22989, false);
        this.d.a(str);
        AppMethodBeat.o(22989);
    }

    @Override // cn.missfresh.module.base.tying.ITyingProductService
    public void b() {
        AppMethodBeat.i(22991, false);
        this.c.a();
        this.d.a();
        AppMethodBeat.o(22991);
    }

    @Override // cn.missfresh.module.base.tying.ITyingProductService
    public MFStickyLiveData<TyingProductsBean> c() {
        return this.a;
    }

    public void a(io.reactivex.disposables.b bVar) {
        AppMethodBeat.i(22992, false);
        if (bVar == null) {
            AppMethodBeat.o(22992);
            return;
        }
        if (this.b == null) {
            this.b = new io.reactivex.disposables.a();
        }
        this.b.a(bVar);
        AppMethodBeat.o(22992);
    }

    public void init(Context context) {
        AppMethodBeat.i(22994, false);
        this.c = new b();
        this.d = new b();
        AppMethodBeat.o(22994);
    }
}
