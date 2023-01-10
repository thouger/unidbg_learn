package cn.missfresh.module.base.shoppingcart_settle;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import cn.missfresh.module.base.bean.LoginEvent;
import cn.missfresh.module.base.bean.UserAddress;
import cn.missfresh.module.base.common.api.RequestParam;
import cn.missfresh.module.base.common.providers.ILoginService;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.module.base.manager.c;
import cn.missfresh.module.base.network.h;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.base.shoppingcart_settle.api.ShoppingCartSettleApiManager;
import cn.missfresh.module.base.shoppingcart_settle.bean.BalanceArea;
import cn.missfresh.module.base.shoppingcart_settle.bean.SettleRequestBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import io.reactivex.disposables.b;
import io.reactivex.q;
import io.reactivex.r;
import io.reactivex.s;
import io.reactivex.v;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: ShoppingCartSettleService */
public class a implements IShoppingCartSettleService, cn.missfresh.module.mvp.a.a {
    r<BalanceArea> a;
    Observer<LoginEvent> b = new AnonymousClass6();
    private final String c = "ShoppingCartSettleService";
    private MutableLiveData<BalanceArea> d = new MutableLiveData<>();
    private MutableLiveData<Integer> e = new MutableLiveData<>();
    private r<SettleRequestBean> f;
    private int g = 0;
    private boolean h;
    private io.reactivex.disposables.a i;
    private BalanceArea j;
    private boolean k;
    private boolean l = true;
    private ILoginService m;
    private v n = new AnonymousClass1();
    private v o = new AnonymousClass2();

    public a() {
        AppMethodBeat.i(19201, false);
        AppMethodBeat.o(19201);
    }

    static /* synthetic */ void a(a aVar, int i) {
        AppMethodBeat.i(19259, false);
        aVar.a(i);
        AppMethodBeat.o(19259);
    }

    static /* synthetic */ void a(a aVar, String str, int i) {
        AppMethodBeat.i(19255, false);
        aVar.b(str, i);
        AppMethodBeat.o(19255);
    }

    /* compiled from: ShoppingCartSettleService */
    /* renamed from: cn.missfresh.module.base.shoppingcart_settle.a$1  reason: invalid class name */
    class AnonymousClass1 implements v<SettleRequestBean> {
        @Override // io.reactivex.v
        public void onComplete() {
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
        }

        AnonymousClass1() {
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(19115, false);
            a((SettleRequestBean) obj);
            AppMethodBeat.o(19115);
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            AppMethodBeat.i(19106, false);
            a.this.a(bVar);
            AppMethodBeat.o(19106);
        }

        public void a(SettleRequestBean settleRequestBean) {
            AppMethodBeat.i(19108, false);
            if (settleRequestBean == null) {
                AppMethodBeat.o(19108);
                return;
            }
            a.this.a(settleRequestBean.from, settleRequestBean.index);
            AppMethodBeat.o(19108);
        }
    }

    /* compiled from: ShoppingCartSettleService */
    /* renamed from: cn.missfresh.module.base.shoppingcart_settle.a$2  reason: invalid class name */
    class AnonymousClass2 implements v<BalanceArea> {
        @Override // io.reactivex.v
        public void onComplete() {
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
        }

        AnonymousClass2() {
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(19134, false);
            a((BalanceArea) obj);
            AppMethodBeat.o(19134);
        }

        public void a(BalanceArea balanceArea) {
            AppMethodBeat.i(19127, false);
            if (balanceArea == null) {
                AppMethodBeat.o(19127);
                return;
            }
            a.this.d.setValue(balanceArea);
            AppMethodBeat.o(19127);
        }
    }

    @Override // cn.missfresh.module.base.shoppingcart_settle.IShoppingCartSettleService
    public void a(String str) {
        AppMethodBeat.i(19204, false);
        d.d("ShoppingCartSettleService", "\u67e5\u8be2\u6761\u4ef6\u6ee1\u8db3 \u67e5\u8be2\u7ed3\u7b97\u6761");
        this.l = false;
        this.g++;
        a(str, this.g);
        AppMethodBeat.o(19204);
    }

    public void a(String str, int i) {
        int i2;
        AppMethodBeat.i(19209, false);
        UserAddress k = c.q().k();
        if (k == null) {
            i2 = -1;
        } else {
            i2 = k.id;
        }
        RequestParam<Map> requestParam = new RequestParam<>();
        HashMap hashMap = new HashMap();
        hashMap.put("bizLine", "as");
        hashMap.put("addressId", Integer.valueOf(i2));
        hashMap.put("from", str);
        hashMap.put("requestId", Integer.valueOf(i));
        requestParam.setParam(hashMap);
        ShoppingCartSettleApiManager.getShoppingCartSettleApi().requestShoppingCartSettle(requestParam).a(new h(1)).subscribe(new AnonymousClass3(this, str));
        AppMethodBeat.o(19209);
    }

    /* compiled from: ShoppingCartSettleService */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.shoppingcart_settle.a$3  reason: invalid class name */
    public class AnonymousClass3 extends i<BalanceArea> {
        final /* synthetic */ String a;

        @Override // io.reactivex.v
        public void onComplete() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass3(cn.missfresh.module.mvp.a.a aVar, String str) {
            super(aVar);
            this.a = str;
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(19156, false);
            a((BalanceArea) obj);
            AppMethodBeat.o(19156);
        }

        public void a(BalanceArea balanceArea) {
            int i = 0;
            AppMethodBeat.i(19146, false);
            if (balanceArea == null) {
                AppMethodBeat.o(19146);
            } else if (a.this.j == null) {
                if (a.this.l) {
                    a.this.l = false;
                }
                a.this.j = balanceArea;
                a.this.d.setValue(balanceArea);
                a aVar = a.this;
                String str = this.a;
                if (balanceArea.getBalanceArea() != null) {
                    i = balanceArea.getBalanceArea().getBalanceCount();
                }
                a.a(aVar, str, i);
                AppMethodBeat.o(19146);
            } else {
                if (balanceArea.getRequestId() > a.this.j.getRequestId()) {
                    if (a.this.l) {
                        a.this.l = false;
                    }
                    a.this.j = balanceArea;
                    a.this.a.onNext(balanceArea);
                }
                a aVar2 = a.this;
                String str2 = this.a;
                if (balanceArea.getBalanceArea() != null) {
                    i = balanceArea.getBalanceArea().getBalanceCount();
                }
                a.a(aVar2, str2, i);
                AppMethodBeat.o(19146);
            }
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
            AppMethodBeat.i(19152, false);
            if (i == 1001) {
                a.a(a.this, 1001);
            } else if (i == 1002) {
                a.this.k = true;
                a.this.j = null;
                a.a(a.this, 1002);
            }
            d.d("ShoppingCartSettleService", "\u7279\u4fd7code \u5c06\u4e0d\u5c55\u793a\u7ed3\u7b97\u6761 code:" + i);
            AppMethodBeat.o(19152);
        }
    }

    private void b(String str, int i) {
        AppMethodBeat.i(19213, false);
        int i2 = 1;
        if ("index".equals(str) || "home".equals(str)) {
            if (i <= 0) {
                i2 = 2;
            }
            cn.missfresh.module.base.shoppingcart_settle.a.a.a(i2);
        } else if ("category".equals(str)) {
            this.e.setValue(Integer.valueOf(i));
        } else if ("search".equals(str) || "search_result".equals(str)) {
            if (i <= 0) {
                i2 = 2;
            }
            cn.missfresh.module.base.shoppingcart_settle.a.a.b(i2);
        }
        AppMethodBeat.o(19213);
    }

    private void a(int i) {
        AppMethodBeat.i(19216, false);
        BalanceArea balanceArea = new BalanceArea();
        balanceArea.setCode(i);
        this.d.setValue(balanceArea);
        AppMethodBeat.o(19216);
    }

    @Override // cn.missfresh.module.base.shoppingcart_settle.IShoppingCartSettleService
    public void a(String str, String str2, int i) {
        AppMethodBeat.i(19219, false);
        if (this.k) {
            d.d("ShoppingCartSettleService", "\u7ed3\u7b97\u8fd4\u56de\u8fc71002 \u6240\u4ee5\u4e0d\u518d\u8bf7\u6c42\u7ed3\u7b97\u6761");
            AppMethodBeat.o(19219);
        } else if ("index".equals(str2) || "category".equals(str2) || "search".equals(str2) || this.h) {
            this.h = true;
            this.g++;
            this.f.onNext(new SettleRequestBean(this.g, str2));
            AppMethodBeat.o(19219);
        } else {
            AppMethodBeat.o(19219);
        }
    }

    @Override // cn.missfresh.module.base.shoppingcart_settle.IShoppingCartSettleService
    public LiveData<BalanceArea> a() {
        return this.d;
    }

    @Override // cn.missfresh.module.base.shoppingcart_settle.IShoppingCartSettleService
    public LiveData<Integer> b() {
        return this.e;
    }

    public void a(b bVar) {
        AppMethodBeat.i(19227, false);
        if (bVar == null) {
            AppMethodBeat.o(19227);
            return;
        }
        if (this.i == null) {
            this.i = new io.reactivex.disposables.a();
        }
        this.i.a(bVar);
        AppMethodBeat.o(19227);
    }

    @Override // cn.missfresh.module.base.shoppingcart_settle.IShoppingCartSettleService
    public void c() {
        AppMethodBeat.i(19233, false);
        this.k = false;
        this.l = true;
        this.d.setValue(null);
        this.j = null;
        AppMethodBeat.o(19233);
    }

    public void init(Context context) {
        AppMethodBeat.i(19237, false);
        d.d("ShoppingCartSettleService", "init service");
        q.a((s) new AnonymousClass4()).f(300, TimeUnit.MILLISECONDS).subscribe(this.n);
        q.a((s) new AnonymousClass5()).f(300, TimeUnit.MILLISECONDS).a(cn.missfresh.basiclib.net.e.a.a).subscribe(this.o);
        this.m = o.a();
        ILoginService iLoginService = this.m;
        if (iLoginService == null) {
            AppMethodBeat.o(19237);
            return;
        }
        iLoginService.a().a(this.b);
        AppMethodBeat.o(19237);
    }

    /* compiled from: ShoppingCartSettleService */
    /* renamed from: cn.missfresh.module.base.shoppingcart_settle.a$4  reason: invalid class name */
    class AnonymousClass4 implements s<SettleRequestBean> {
        AnonymousClass4() {
        }

        @Override // io.reactivex.s
        public void a(r<SettleRequestBean> rVar) throws Exception {
            AppMethodBeat.i(19167, false);
            a.this.f = rVar;
            AppMethodBeat.o(19167);
        }
    }

    /* compiled from: ShoppingCartSettleService */
    /* renamed from: cn.missfresh.module.base.shoppingcart_settle.a$5  reason: invalid class name */
    class AnonymousClass5 implements s<BalanceArea> {
        AnonymousClass5() {
        }

        @Override // io.reactivex.s
        public void a(r<BalanceArea> rVar) throws Exception {
            a.this.a = rVar;
        }
    }

    /* compiled from: ShoppingCartSettleService */
    /* renamed from: cn.missfresh.module.base.shoppingcart_settle.a$6  reason: invalid class name */
    class AnonymousClass6 implements Observer<LoginEvent> {
        AnonymousClass6() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(19192, false);
            a((LoginEvent) obj);
            AppMethodBeat.o(19192);
        }

        public void a(LoginEvent loginEvent) {
            AppMethodBeat.i(19189, false);
            if (loginEvent == null || a.this.d.getValue() == 0) {
                AppMethodBeat.o(19189);
                return;
            }
            if (200 == loginEvent.getType()) {
                a.this.k = false;
                d.d("ShoppingCartSettleService", "\u767b\u5f55\u6210\u529f \u67e5\u8be2\u7ed3\u7b97\u6761");
                a.this.a("index");
            } else if (400 == loginEvent.getType()) {
                a.this.k = false;
                d.d("ShoppingCartSettleService", "\u9000\u51fa\u767b\u5f55 \u67e5\u8be2\u7ed3\u7b97\u6761");
                a.this.a("index");
            }
            AppMethodBeat.o(19189);
        }
    }
}
