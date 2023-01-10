package cn.missfresh.module.user.address.view;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import cn.missfresh.basiclib.utils.permission.c;
import cn.missfresh.location_api.bean.Location;
import cn.missfresh.module.base.base.BaseModuleFragment;
import cn.missfresh.module.base.bean.TencentSearchData;
import cn.missfresh.module.base.bean.UserAddress;
import cn.missfresh.module.base.common.providers.ILocationService;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.address.bean.SelectAddressResult;
import cn.missfresh.module.user.address.presenter.SelectsAddressPresenter;
import cn.missfresh.module.user.address.view.HistoryAddressesLayout;
import cn.missfresh.module.user.address.view.HomeSelectLocationLayout;
import cn.missfresh.module.user.address.view.NearByAddressesLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import cn.missfresh.utils.b;
import java.util.List;

public class SelectsAddressModuleFragment extends BaseModuleFragment implements HistoryAddressesLayout.a, HomeSelectLocationLayout.a, NearByAddressesLayout.a, e {
    private HomeSelectLocationLayout c;
    private TextView d;
    private HistoryAddressesLayout e;
    private TextView f;
    private TextView g;
    private NearByAddressesLayout h;
    private SelectsAddressPresenter i;
    private g j;
    private c k;
    private ILocationService l;
    private boolean m;

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.address.view.SelectsAddressModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void onHiddenChanged(boolean z) {
        SelectsAddressModuleFragment.super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.address.view.SelectsAddressModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void onResume() {
        SelectsAddressModuleFragment.super.onResume();
        AppMethodBeat.onResume(this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.address.view.SelectsAddressModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void setUserVisibleHint(boolean z) {
        SelectsAddressModuleFragment.super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void onAttach(Context context) {
        AppMethodBeat.i(5559, false);
        super.onAttach(context);
        if (context != null && (context instanceof g)) {
            this.j = (g) context;
        }
        AppMethodBeat.o(5559);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AppMethodBeat.i(5561, false);
        View inflate = layoutInflater.inflate(R.layout.user_fragment_select_address, (ViewGroup) null, false);
        AppMethodBeat.o(5561);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        AppMethodBeat.i(5563, false);
        super.onViewCreated(view, bundle);
        if (view == null) {
            AppMethodBeat.o(5563);
            return;
        }
        a(view);
        l();
        k();
        AppMethodBeat.o(5563);
    }

    private void k() {
        AppMethodBeat.i(5565, false);
        if (this.k == null) {
            this.k = new c();
        }
        if (this.k.a(Manifest.permission.ACCESS_FINE_LOCATION)) {
            g();
        } else {
            d();
        }
        AppMethodBeat.o(5565);
    }

    private void a(View view) {
        AppMethodBeat.i(5569, false);
        this.g = (TextView) view.findViewById(R.id.tv_send_address);
        this.c = view.findViewById(R.id.cv_location);
        this.d = (TextView) view.findViewById(R.id.tv_history_addresses_title);
        this.e = view.findViewById(R.id.cv_history_addresses);
        this.f = (TextView) view.findViewById(R.id.tv_near_by_addresses_title);
        this.h = view.findViewById(R.id.cv_near_by_addresses);
        this.e.setOnItemClickListener(this);
        this.h.setOnItemClickListener(this);
        this.c.setOnLocationClickListener(this);
        AppMethodBeat.o(5569);
    }

    private void l() {
        AppMethodBeat.i(5572, false);
        this.i = new SelectsAddressPresenter(this);
        this.i.e();
        this.i.c();
        String i = cn.missfresh.module.base.manager.c.i();
        if (TextUtils.isEmpty(i)) {
            this.g.setVisibility(8);
        } else {
            this.g.setVisibility(0);
            this.g.setText(String.format(getResources().getString(R.string.user_send_location), i));
        }
        AppMethodBeat.o(5572);
    }

    public void a(List<UserAddress> list) {
        AppMethodBeat.i(5574, false);
        if (o()) {
            if (b.a(list)) {
                this.d.setVisibility(8);
            } else {
                if (getActivity() instanceof SelectsHomeAddressActivity) {
                    ((SelectsHomeAddressActivity) getActivity()).d(true);
                }
                this.d.setVisibility(0);
            }
            this.e.setupView(list);
        }
        AppMethodBeat.o(5574);
    }

    public void b(List<TencentSearchData> list) {
        AppMethodBeat.i(5575, false);
        if (o()) {
            if (b.a(list)) {
                this.f.setVisibility(8);
            } else {
                this.f.setVisibility(0);
            }
            this.h.setupView(list);
        }
        AppMethodBeat.o(5575);
    }

    public void c() {
        AppMethodBeat.i(5577, false);
        this.h.removeAllViews();
        AppMethodBeat.o(5577);
    }

    public void a(String str) {
        AppMethodBeat.i(5579, false);
        if (o()) {
            StatisticsManager.s("page_view", "isValid", "1");
            this.c.a(str);
        }
        AppMethodBeat.o(5579);
    }

    public void d() {
        AppMethodBeat.i(5581, false);
        StatisticsManager.s("page_view", "isValid", "0");
        this.c.c();
        this.i.h();
        AppMethodBeat.o(5581);
    }

    public void e() {
        AppMethodBeat.i(5583, false);
        this.c.b();
        AppMethodBeat.o(5583);
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0028: APUT  (r2v1 java.lang.Object[]), (3 ??[int, float, short, byte, char]), (r6v5 java.lang.String) */
    public void a(UserAddress userAddress, int i) {
        AppMethodBeat.i(5587, false);
        Object[] objArr = new Object[6];
        objArr[0] = "pos";
        objArr[1] = Integer.valueOf(i + 1);
        objArr[2] = "isDefault";
        objArr[3] = userAddress.defaultAddress ? "1" : "0";
        objArr[4] = "tag";
        objArr[5] = userAddress.getRealTag();
        StatisticsManager.s("click_myAddress", objArr);
        cn.missfresh.module.base.manager.c.q().b(userAddress);
        a(userAddress);
        AppMethodBeat.o(5587);
    }

    public void a(TencentSearchData tencentSearchData, int i) {
        AppMethodBeat.i(5588, false);
        StatisticsManager.s("click_nearAddress", "pos", Integer.valueOf(i + 1));
        cn.missfresh.module.base.manager.c.q().b((UserAddress) null);
        a(tencentSearchData);
        AppMethodBeat.o(5588);
    }

    public void a(TencentSearchData tencentSearchData) {
        AppMethodBeat.i(5590, false);
        SelectAddressResult selectAddressResult = new SelectAddressResult();
        selectAddressResult.mRegionCode = tencentSearchData.ad_info.adcode;
        selectAddressResult.mCityName = tencentSearchData.ad_info.city;
        selectAddressResult.mAreaName = tencentSearchData.title;
        selectAddressResult.mLatitude = String.valueOf(tencentSearchData.location.lat);
        selectAddressResult.mLongitude = String.valueOf(tencentSearchData.location.lng);
        a(selectAddressResult, 16);
        AppMethodBeat.o(5590);
    }

    public void a() {
        AppMethodBeat.i(5592, false);
        if (getActivity() == null) {
            AppMethodBeat.o(5592);
            return;
        }
        this.i.a(false);
        if (!this.c.f()) {
            m();
            this.m = true;
            this.i.a(this.l);
        } else {
            a.a("\u6b63\u5728\u5b9a\u4f4d\u4e2d");
        }
        AppMethodBeat.o(5592);
    }

    public void b() {
        AppMethodBeat.i(5593, false);
        if (this.c.f()) {
            a.a("\u6b63\u5728\u5b9a\u4f4d\u4e2d");
            AppMethodBeat.o(5593);
        } else if (this.c.g()) {
            List d = this.i.d();
            if (!b.a(d)) {
                cn.missfresh.module.base.manager.c.q().b((UserAddress) null);
                a((TencentSearchData) d.get(0));
            } else {
                d();
            }
            AppMethodBeat.o(5593);
        } else {
            AppMethodBeat.o(5593);
        }
    }

    private void a(UserAddress userAddress) {
        AppMethodBeat.i(5594, false);
        if (b.a(userAddress.latLng)) {
            a.a("\u5f53\u524d\u5730\u5740\u4e0d\u53ef\u4f7f\u7528!");
            AppMethodBeat.o(5594);
            return;
        }
        UserAddress.LatAndLng latAndLng = userAddress.getLatAndLng();
        if (latAndLng == null || !userAddress.isAvailable()) {
            a.a("\u5f53\u524d\u5730\u5740\u4e0d\u53ef\u4f7f\u7528!");
            AppMethodBeat.o(5594);
            return;
        }
        SelectAddressResult selectAddressResult = new SelectAddressResult();
        selectAddressResult.mRegionCode = userAddress.area_code;
        selectAddressResult.mCityName = userAddress.city;
        selectAddressResult.mAreaName = userAddress.address_1;
        selectAddressResult.mLatitude = latAndLng.lat;
        selectAddressResult.mLongitude = latAndLng.lng;
        a(selectAddressResult, 16);
        AppMethodBeat.o(5594);
    }

    public void a(SelectAddressResult selectAddressResult, int i) {
        AppMethodBeat.i(5596, false);
        g gVar = this.j;
        if (gVar != null) {
            gVar.a(selectAddressResult, i);
        }
        AppMethodBeat.o(5596);
    }

    public void onDestroy() {
        AppMethodBeat.i(5598, false);
        super.onDestroy();
        this.i.f();
        this.i.g();
        ILocationService iLocationService = this.l;
        if (iLocationService != null) {
            iLocationService.a().c();
        }
        AppMethodBeat.o(5598);
    }

    public void g() {
        AppMethodBeat.i(5599, false);
        this.i.a(false);
        if (this.c.f()) {
            a.a("\u6b63\u5728\u5b9a\u4f4d\u4e2d");
        } else if (this.c.g()) {
            List d = this.i.d();
            if (!b.a(d)) {
                cn.missfresh.module.base.manager.c.q().b((UserAddress) null);
                a((TencentSearchData) d.get(0));
            } else {
                d();
            }
        } else {
            m();
            this.m = true;
            this.i.a(this.l);
        }
        AppMethodBeat.o(5599);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.address.view.SelectsAddressModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    private void m() {
        AppMethodBeat.i(5600, false);
        if (this.l == null) {
            this.l = (ILocationService) com.alibaba.android.arouter.b.a.a().a("/user/location_service").navigation();
            this.l.a().b().a(this, new AnonymousClass1());
            this.l.b().observe(this, new AnonymousClass2());
        }
        AppMethodBeat.o(5600);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.address.view.SelectsAddressModuleFragment$1  reason: invalid class name */
    public class AnonymousClass1 implements Observer<Location> {
        AnonymousClass1() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(5546, false);
            a((Location) obj);
            AppMethodBeat.o(5546);
        }

        public void a(Location location) {
            AppMethodBeat.i(5544, false);
            if (!SelectsAddressModuleFragment.this.m) {
                AppMethodBeat.o(5544);
                return;
            }
            SelectsAddressModuleFragment.this.m = false;
            SelectsAddressModuleFragment.this.i.a(location, SelectsAddressModuleFragment.this.l);
            AppMethodBeat.o(5544);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.address.view.SelectsAddressModuleFragment$2  reason: invalid class name */
    public class AnonymousClass2 implements Observer<Integer> {
        AnonymousClass2() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(5551, false);
            a((Integer) obj);
            AppMethodBeat.o(5551);
        }

        public void a(Integer num) {
            AppMethodBeat.i(5548, false);
            if (num == null || num.intValue() != 1) {
                SelectsAddressModuleFragment.this.d();
            } else {
                SelectsAddressModuleFragment.this.g();
            }
            AppMethodBeat.o(5548);
        }
    }
}
