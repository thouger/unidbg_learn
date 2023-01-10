package cn.missfresh.module.user.location.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import cn.missfresh.location.a;
import cn.missfresh.location_api.StickyLiveData;
import cn.missfresh.module.base.bean.ConfigurationBean;
import cn.missfresh.module.base.bean.EventAppAddress;
import cn.missfresh.module.base.bean.TencentSearchData;
import cn.missfresh.module.base.bean.UserLocationBean;
import cn.missfresh.module.base.common.providers.ILocationService;
import cn.missfresh.module.base.manager.b;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.module.base.manager.q;
import cn.missfresh.module.base.network.h;
import cn.missfresh.module.base.network.i;
import cn.missfresh.module.base.network.j;
import cn.missfresh.module.user.location.api.LocationApiManager;
import cn.missfresh.module.user.location.bean.ChromeInfo;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import io.reactivex.u;
import java.util.HashMap;
import java.util.List;

/* compiled from: LocationService */
public class c implements ILocationService {
    private a a;
    private StickyLiveData<EventAppAddress> b = new StickyLiveData<>();
    private MutableLiveData<String> c = new MutableLiveData<>();
    private MutableLiveData<List<String>> d = new MutableLiveData<>();
    private MutableLiveData<ConfigurationBean.UpgradeBean> e = new MutableLiveData<>();
    private MutableLiveData<ConfigurationBean> f = new MutableLiveData<>();
    private MutableLiveData<String> g = new MutableLiveData<>();

    public c() {
        AppMethodBeat.i(7604, false);
        AppMethodBeat.o(7604);
    }

    static /* synthetic */ void c(c cVar) {
        AppMethodBeat.i(7629, false);
        cVar.i();
        AppMethodBeat.o(7629);
    }

    @Override // cn.missfresh.module.base.common.providers.ILocationService
    public a a() {
        AppMethodBeat.i(7605, false);
        a b = this.a.b();
        AppMethodBeat.o(7605);
        return b;
    }

    @Override // cn.missfresh.module.base.common.providers.ILocationService
    public LiveData<Integer> b() {
        AppMethodBeat.i(7606, false);
        LiveData<Integer> c = this.a.c();
        AppMethodBeat.o(7606);
        return c;
    }

    @Override // cn.missfresh.module.base.common.providers.ILocationService
    public LiveData<ConfigurationBean> g() {
        return this.f;
    }

    private void i() {
        AppMethodBeat.i(7608, false);
        d.c("Wz", "DefaultAddressManager requestChromInfo");
        HashMap hashMap = new HashMap();
        hashMap.put("lat", b.b());
        hashMap.put("lng", b.c());
        hashMap.put("address", "");
        hashMap.put("clickScene", 2);
        LocationApiManager.getLocationApi().requestChromeInfo(hashMap).b(io.reactivex.f.a.b()).a(io.reactivex.f.a.b()).subscribe(new cn.missfresh.basiclib.net.c(new AnonymousClass1()));
        AppMethodBeat.o(7608);
    }

    /* compiled from: LocationService */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.location.service.c$1  reason: invalid class name */
    public class AnonymousClass1 extends j<ChromeInfo> {
        AnonymousClass1() {
        }

        @Override // cn.missfresh.basiclib.net.a.a
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(7553, false);
            a((ChromeInfo) obj);
            AppMethodBeat.o(7553);
        }

        public void a(ChromeInfo chromeInfo) {
            AppMethodBeat.i(7547, false);
            if (chromeInfo == null || chromeInfo.code != 0) {
                c.this.c.postValue("");
            } else {
                c.this.c.postValue(chromeInfo.warning_msg);
            }
            AppMethodBeat.o(7547);
        }

        @Override // cn.missfresh.module.base.network.j
        public void a(int i, String str) {
            AppMethodBeat.i(7550, false);
            c.this.c.postValue("");
            AppMethodBeat.o(7550);
        }

        @Override // cn.missfresh.module.base.network.j, cn.missfresh.basiclib.net.a.a
        public void onComplete() {
            AppMethodBeat.i(7551, false);
            super.onComplete();
            AppMethodBeat.o(7551);
        }
    }

    @Override // cn.missfresh.module.base.common.providers.ILocationService
    public void a(int i) {
        AppMethodBeat.i(7611, false);
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("lat", b.b());
        hashMap2.put("lng", b.c());
        hashMap2.put("address", "");
        hashMap2.put("clickScene", Integer.valueOf(i));
        hashMap.put("param", hashMap2);
        LocationApiManager.getLocationApi().getUserLocation(hashMap).b(io.reactivex.f.a.b()).e(new cn.missfresh.basiclib.net.c.a(3)).a(io.reactivex.f.a.b()).subscribe(new cn.missfresh.basiclib.net.c(new AnonymousClass2()));
        AppMethodBeat.o(7611);
    }

    /* compiled from: LocationService */
    /* renamed from: cn.missfresh.module.user.location.service.c$2  reason: invalid class name */
    class AnonymousClass2 extends j<UserLocationBean> {
        AnonymousClass2() {
        }

        @Override // cn.missfresh.basiclib.net.a.a
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(7566, false);
            a((UserLocationBean) obj);
            AppMethodBeat.o(7566);
        }

        public void a(UserLocationBean userLocationBean) {
            AppMethodBeat.i(7562, false);
            if (userLocationBean != null && !cn.missfresh.utils.b.a(userLocationBean.sellerInfoList)) {
                g.a().a(userLocationBean.sellerInfoList.get(0).sellerType);
                g.a().b(userLocationBean.sellerInfoList.get(0).sellerType);
                g.a().c(userLocationBean.sellerInfoList.get(0).sellerType);
            }
            c.this.b(0);
            b.a(userLocationBean);
            c.this.b.a(new EventAppAddress(userLocationBean));
            if (userLocationBean.type != 0) {
                c.c(c.this);
            }
            AppMethodBeat.o(7562);
        }

        @Override // cn.missfresh.module.base.network.j
        public void a(int i, String str) {
            AppMethodBeat.i(7564, false);
            d.c("LocationService", "code = " + i + ",message = " + str);
            c.this.b(0);
            if (i != 1006 || cn.missfresh.utils.b.a(str)) {
                c.this.b.a(new EventAppAddress(false, ""));
            } else {
                c.this.b.a(new EventAppAddress(true, str));
            }
            AppMethodBeat.o(7564);
        }
    }

    @Override // cn.missfresh.module.base.common.providers.ILocationService
    public void b(int i) {
        AppMethodBeat.i(7612, false);
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("selector", Integer.valueOf(i));
        hashMap.put("param", hashMap2);
        LocationApiManager.getLocationApi().getConfiguration(hashMap).a((u) new h()).subscribe(new cn.missfresh.basiclib.net.c(new AnonymousClass3(i)));
        AppMethodBeat.o(7612);
    }

    /* compiled from: LocationService */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.location.service.c$3  reason: invalid class name */
    public class AnonymousClass3 extends j<ConfigurationBean> {
        final /* synthetic */ int a;

        AnonymousClass3(int i) {
            this.a = i;
        }

        @Override // cn.missfresh.basiclib.net.a.a
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(7576, false);
            a((ConfigurationBean) obj);
            AppMethodBeat.o(7576);
        }

        @Override // cn.missfresh.module.base.network.j
        public void a(int i, String str) {
            AppMethodBeat.i(7570, false);
            if (this.a != 1) {
                c.this.e.postValue(new ConfigurationBean.UpgradeBean());
            }
            AppMethodBeat.o(7570);
        }

        public void a(ConfigurationBean configurationBean) {
            AppMethodBeat.i(7575, false);
            e.a(configurationBean);
            if (this.a == 0) {
                g.b = configurationBean.skipInfo;
            }
            c.this.f.setValue(configurationBean);
            if (this.a != 2) {
                c.this.d.setValue(configurationBean.searchHotWords);
            }
            if (this.a != 1) {
                if (configurationBean.versionUpgradeInfo == null) {
                    configurationBean.versionUpgradeInfo = new ConfigurationBean.UpgradeBean();
                }
                configurationBean.versionUpgradeInfo.isSuccess = true;
                c.this.e.setValue(configurationBean.versionUpgradeInfo);
            }
            AppMethodBeat.o(7575);
        }
    }

    @Override // cn.missfresh.module.base.common.providers.ILocationService
    public LiveData<ConfigurationBean.UpgradeBean> c() {
        AppMethodBeat.i(7613, false);
        MutableLiveData mutableLiveData = new MutableLiveData();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("selector", 2);
        hashMap.put("param", hashMap2);
        LocationApiManager.getLocationApi().getConfiguration(hashMap).b(io.reactivex.f.a.b()).e(new cn.missfresh.basiclib.net.c.a(3)).a(io.reactivex.f.a.b()).subscribe(new AnonymousClass4(null, mutableLiveData));
        AppMethodBeat.o(7613);
        return mutableLiveData;
    }

    /* compiled from: LocationService */
    /* renamed from: cn.missfresh.module.user.location.service.c$4  reason: invalid class name */
    class AnonymousClass4 extends i<ConfigurationBean> {
        final /* synthetic */ MutableLiveData a;

        @Override // io.reactivex.v
        public void onComplete() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass4(cn.missfresh.module.mvp.a.a aVar, MutableLiveData mutableLiveData) {
            super(aVar);
            this.a = mutableLiveData;
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(7583, false);
            a((ConfigurationBean) obj);
            AppMethodBeat.o(7583);
        }

        public void a(ConfigurationBean configurationBean) {
            AppMethodBeat.i(7579, false);
            if (configurationBean.versionUpgradeInfo == null) {
                configurationBean.versionUpgradeInfo = new ConfigurationBean.UpgradeBean();
            }
            configurationBean.versionUpgradeInfo.isSuccess = true;
            this.a.postValue(configurationBean.versionUpgradeInfo);
            AppMethodBeat.o(7579);
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.i
        public void a(int i, String str) {
            AppMethodBeat.i(7581, false);
            this.a.postValue(new ConfigurationBean.UpgradeBean());
            AppMethodBeat.o(7581);
        }
    }

    /* compiled from: LocationService */
    /* renamed from: cn.missfresh.module.user.location.service.c$5  reason: invalid class name */
    class AnonymousClass5 implements q.b {
        final /* synthetic */ ILocationService.a a;

        AnonymousClass5(ILocationService.a aVar) {
            this.a = aVar;
        }

        @Override // cn.missfresh.module.base.manager.q.b
        public void a(List<TencentSearchData> list) {
            AppMethodBeat.i(7594, false);
            if (cn.missfresh.utils.b.a(list)) {
                a(1);
                d.d("LocationService", "search poi null");
            } else {
                ILocationService.a aVar = this.a;
                if (aVar != null) {
                    aVar.a(true, "", list);
                }
                d.d("LocationService", "search poi success");
            }
            AppMethodBeat.o(7594);
        }

        @Override // cn.missfresh.module.base.manager.q.b
        public void a(int i) {
            AppMethodBeat.i(7597, false);
            ILocationService.a aVar = this.a;
            if (aVar != null) {
                aVar.a(false, String.valueOf(i), null);
            }
            AppMethodBeat.o(7597);
        }
    }

    @Override // cn.missfresh.module.base.common.providers.ILocationService
    public void a(String str, String str2, ILocationService.a aVar) {
        AppMethodBeat.i(7615, false);
        q.a(str, str2, 1000, this, 10, new AnonymousClass5(aVar));
        AppMethodBeat.o(7615);
    }

    @Override // cn.missfresh.module.base.common.providers.ILocationService
    public StickyLiveData<EventAppAddress> d() {
        return this.b;
    }

    @Override // cn.missfresh.module.base.common.providers.ILocationService
    public LiveData<String> e() {
        return this.c;
    }

    @Override // cn.missfresh.module.base.common.providers.ILocationService
    public LiveData<List<String>> f() {
        return this.d;
    }

    @Override // cn.missfresh.module.base.common.providers.ILocationService
    public MutableLiveData<String> h() {
        return this.g;
    }

    public void init(Context context) {
        AppMethodBeat.i(7624, false);
        d.d("LocationService", "init");
        this.a = new a();
        this.a.a(context);
        AppMethodBeat.o(7624);
    }
}
