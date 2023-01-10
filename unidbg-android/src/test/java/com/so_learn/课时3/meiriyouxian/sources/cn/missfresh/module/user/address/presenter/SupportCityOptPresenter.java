package cn.missfresh.module.user.address.presenter;

import android.os.CountDownTimer;
import android.util.TimedRemoteCaller;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import cn.missfresh.location_api.bean.Location;
import cn.missfresh.module.base.bean.TencentSearchData;
import cn.missfresh.module.base.common.providers.ILocationService;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.manager.c;
import cn.missfresh.module.base.manager.q;
import cn.missfresh.module.mvp.mvp.impl.MVPPresenter;
import cn.missfresh.module.mvp.mvp.interfaces.IModel;
import cn.missfresh.module.user.address.model.SupportCityOptModel;
import cn.missfresh.module.user.address.view.i;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import java.util.List;

public class SupportCityOptPresenter extends MVPPresenter<i> {
    public String a;
    private final String b = getClass().getSimpleName();
    private SupportCityOptModel c = new SupportCityOptModel();
    private boolean d = false;
    private boolean e = false;
    private CountDownTimer f = new 1(this, (long) TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS, 1000);
    private final int g = 10000;

    public SupportCityOptPresenter(i iVar) {
        super(iVar);
        AppMethodBeat.i(4865, false);
        AppMethodBeat.o(4865);
    }

    /* renamed from: cn.missfresh.module.user.address.presenter.SupportCityOptPresenter$2  reason: invalid class name */
    class AnonymousClass2 implements IModel.a {
        AnonymousClass2() {
        }

        public void a() {
            AppMethodBeat.i(4834, false);
            i i = SupportCityOptPresenter.this.i();
            if (i != null) {
                i.a(SupportCityOptPresenter.this.c.a);
                i.b(SupportCityOptPresenter.this.c.b);
            }
            AppMethodBeat.o(4834);
        }

        public void a(int i, String str) {
            AppMethodBeat.i(4838, false);
            i i2 = SupportCityOptPresenter.this.i();
            if (i2 != null) {
                i2.a(i, str);
            }
            AppMethodBeat.o(4838);
        }

        public void b() {
            AppMethodBeat.i(4841, false);
            i i = SupportCityOptPresenter.this.i();
            if (i != null) {
                i.n();
            }
            AppMethodBeat.o(4841);
        }

        public Lifecycle c() {
            AppMethodBeat.i(4846, false);
            i i = SupportCityOptPresenter.this.i();
            if (i != null) {
                Lifecycle viewLifecycle = i.getViewLifecycle();
                AppMethodBeat.o(4846);
                return viewLifecycle;
            }
            AppMethodBeat.o(4846);
            return null;
        }
    }

    public void a() {
        AppMethodBeat.i(4867, false);
        this.c.a((IModel.a) new AnonymousClass2());
        AppMethodBeat.o(4867);
    }

    public void a(ILocationService iLocationService) {
        AppMethodBeat.i(4869, false);
        if (iLocationService == null || iLocationService.a() == null) {
            a.a("data error");
            AppMethodBeat.o(4869);
            return;
        }
        i i = i();
        if (i != null) {
            i.M();
        }
        iLocationService.a().a(10000);
        AppMethodBeat.o(4869);
    }

    public void a(Location location, ILocationService iLocationService) {
        AppMethodBeat.i(4871, false);
        if (this.d || location == null) {
            i i = i();
            if (i != null) {
                i.L();
            }
            AppMethodBeat.o(4871);
            return;
        }
        if (Location.ERROR_OK == location.getCode()) {
            a(String.valueOf(location.getLat()), String.valueOf(location.getLng()));
            StatisticsManager.a();
        } else {
            i i2 = i();
            if (i2 != null) {
                i2.L();
            }
        }
        AppMethodBeat.o(4871);
    }

    public void b() {
        AppMethodBeat.i(4873, false);
        this.f.cancel();
        AppMethodBeat.o(4873);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.address.presenter.SupportCityOptPresenter$3  reason: invalid class name */
    public class AnonymousClass3 implements q.b {
        AnonymousClass3() {
        }

        @Override // cn.missfresh.module.base.manager.q.b
        public void a(List<TencentSearchData> list) {
            AppMethodBeat.i(4856, false);
            String str = SupportCityOptPresenter.this.b;
            d.c(str, " requestLocationName locationSucceed " + SupportCityOptPresenter.this.e + " location time out " + SupportCityOptPresenter.this.d);
            if (SupportCityOptPresenter.this.d) {
                AppMethodBeat.o(4856);
                return;
            }
            SupportCityOptPresenter.this.e = true;
            SupportCityOptPresenter.this.b();
            if (b.a(list)) {
                a(1);
            } else {
                TencentSearchData tencentSearchData = list.get(0);
                if (b.a(c.h()) && tencentSearchData.ad_info != null && !b.a(tencentSearchData.ad_info.adcode)) {
                    cn.missfresh.module.base.manager.b.d(tencentSearchData.ad_info.adcode);
                }
                i i = SupportCityOptPresenter.this.i();
                if (i != null) {
                    i.b(tencentSearchData.ad_info.city);
                }
                SupportCityOptPresenter.this.c.a(list);
                if (i != null) {
                    i.c(list);
                }
            }
            AppMethodBeat.o(4856);
        }

        @Override // cn.missfresh.module.base.manager.q.b
        public void a(int i) {
            i i2;
            AppMethodBeat.i(4859, false);
            if (SupportCityOptPresenter.this.h() && (i2 = SupportCityOptPresenter.this.i()) != null) {
                i2.L();
                i2.K();
            }
            AppMethodBeat.o(4859);
        }
    }

    private void a(String str, String str2) {
        AppMethodBeat.i(4875, false);
        q.a(str, str2, 1000, this, 10, new AnonymousClass3());
        AppMethodBeat.o(4875);
    }

    public String c() {
        AppMethodBeat.i(4876, false);
        SupportCityOptModel supportCityOptModel = this.c;
        if (supportCityOptModel != null) {
            String a = supportCityOptModel.a();
            AppMethodBeat.o(4876);
            return a;
        }
        AppMethodBeat.o(4876);
        return "";
    }

    public List<TencentSearchData> d() {
        AppMethodBeat.i(4877, false);
        List<TencentSearchData> b = this.c.b();
        AppMethodBeat.o(4877);
        return b;
    }

    public String e() {
        return this.c.c;
    }

    public void a(String str) {
        this.a = str;
    }

    public String f() {
        return this.a;
    }

    public void onDestroy(LifecycleOwner lifecycleOwner) {
        AppMethodBeat.i(4881, false);
        b();
        cn.missfresh.module.base.network.c.a(this);
        SupportCityOptPresenter.super.onDestroy(lifecycleOwner);
        AppMethodBeat.o(4881);
    }
}
