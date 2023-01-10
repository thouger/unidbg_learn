package cn.missfresh.module.user.address.view;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import cn.missfresh.module.base.base.BaseModuleFragment;
import cn.missfresh.module.base.bean.TencentSuggestionAddress;
import cn.missfresh.module.base.bean.UserAddress;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.support.dialog.e;
import cn.missfresh.module.base.utils.k;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.address.adapter.c;
import cn.missfresh.module.user.address.bean.SelectAddressResult;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.umeng.analytics.pro.ai;
import java.util.List;

public class SearchesAddressModuleFragment extends BaseModuleFragment implements View.OnClickListener, AdapterView.OnItemClickListener, c {
    private ListView c;
    private c d;
    private cn.missfresh.module.user.address.presenter.c e;
    private g f;
    private h g;
    private View h;
    private View i;
    private View j;
    private TextView k;
    private String l = "";
    private String m = "";
    private int n = -1;
    private a o;
    private String p = "";
    private String q = "";
    private String r = "";
    private boolean s;

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.address.view.SearchesAddressModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void onHiddenChanged(boolean z) {
        SearchesAddressModuleFragment.super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.address.view.SearchesAddressModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void onResume() {
        SearchesAddressModuleFragment.super.onResume();
        AppMethodBeat.onResume(this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.address.view.SearchesAddressModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void setUserVisibleHint(boolean z) {
        SearchesAddressModuleFragment.super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void onAttach(Context context) {
        AppMethodBeat.i(5493, false);
        super.onAttach(context);
        if (context != null) {
            if (context instanceof g) {
                this.f = (g) context;
            }
            if (context instanceof a) {
                this.o = (a) context;
            }
            if (context instanceof h) {
                this.g = (h) context;
            }
        }
        AppMethodBeat.o(5493);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AppMethodBeat.i(5494, false);
        View inflate = layoutInflater.inflate(R.layout.user_fragment_searches_address, (ViewGroup) null, false);
        AppMethodBeat.o(5494);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        AppMethodBeat.i(5497, false);
        super.onViewCreated(view, bundle);
        if (view == null) {
            AppMethodBeat.o(5497);
            return;
        }
        a(view);
        l();
        m();
        AppMethodBeat.o(5497);
    }

    private void a(View view) {
        AppMethodBeat.i(5500, false);
        this.h = getActivity().getLayoutInflater().inflate(R.layout.layout_clear_history_footer, (ViewGroup) null);
        this.i = view.findViewById(R.id.ll_search_result_container);
        this.j = view.findViewById(R.id.search_address_notice_layout);
        this.c = (ListView) view.findViewById(R.id.lv_search_content);
        this.k = (TextView) view.findViewById(R.id.tv_first_notice);
        this.c.setOnItemClickListener(this);
        this.h.findViewById(R.id.btn_clear_search_history_address).setOnClickListener(this);
        view.findViewById(R.id.ll_search_result_container).setOnClickListener(this);
        AppMethodBeat.o(5500);
    }

    private void l() {
        AppMethodBeat.i(5501, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.s = arguments.getBoolean("is_use_history", false);
            if (arguments.containsKey("EXTRA_SEARCH_CITY")) {
                this.m = arguments.getString("EXTRA_SEARCH_CITY");
                a(this.m);
            }
            if (arguments.containsKey("EXTRA_SEARCH_CONTENT")) {
                this.l = arguments.getString("EXTRA_SEARCH_CONTENT");
            }
            if (arguments.containsKey("EXTRAL_SAERCH_SHOWSOFT")) {
                this.n = arguments.getInt("EXTRAL_SAERCH_SHOWSOFT");
            }
            if (arguments.containsKey("EXTRAL_LATITUDE")) {
                this.p = arguments.getString("EXTRAL_LATITUDE");
            }
            if (arguments.containsKey("EXTRAL_LONGITUDE")) {
                this.q = arguments.getString("EXTRAL_LONGITUDE");
            }
            if (arguments.containsKey("EXTRAL_LABLE")) {
                this.r = arguments.getString("EXTRAL_LABLE");
            }
        }
        AppMethodBeat.o(5501);
    }

    private void m() {
        a aVar;
        AppMethodBeat.i(5502, false);
        this.e = new cn.missfresh.module.user.address.presenter.c(this);
        this.d = new c(getActivity(), this.e);
        this.c.setAdapter((ListAdapter) this.d);
        this.c.setEmptyView(this.i);
        if (this.s) {
            this.e.a();
        }
        if (!b.a(this.l)) {
            a(this.m, this.l, this.p, this.q);
        }
        if (this.n == 1 && (aVar = this.o) != null) {
            aVar.G();
        }
        AppMethodBeat.o(5502);
    }

    public void a(String str, String str2, String str3, String str4) {
        AppMethodBeat.i(5504, false);
        if (str2.length() == 0) {
            k();
            AppMethodBeat.o(5504);
            return;
        }
        cn.missfresh.module.user.address.presenter.c cVar = this.e;
        if (cVar != null) {
            cVar.a(str, str2, str3, str4);
            g();
        }
        AppMethodBeat.o(5504);
    }

    public void a(boolean z) {
        AppMethodBeat.i(5505, false);
        if (!z) {
            this.c.addHeaderView(this.h);
            this.d.a(true);
            this.d.notifyDataSetChanged();
        } else {
            s();
        }
        AppMethodBeat.o(5505);
    }

    public void a() {
        AppMethodBeat.i(5508, false);
        s();
        AppMethodBeat.o(5508);
    }

    public void b() {
        AppMethodBeat.i(5510, false);
        r();
        AppMethodBeat.o(5510);
    }

    public void c() {
        AppMethodBeat.i(5512, false);
        r();
        AppMethodBeat.o(5512);
    }

    public void d() {
        AppMethodBeat.i(5514, false);
        r();
        AppMethodBeat.o(5514);
    }

    public void e() {
        AppMethodBeat.i(5516, false);
        if (this.c.getHeaderViewsCount() >= 1) {
            this.c.removeHeaderView(this.h);
        }
        this.d.a(false);
        AppMethodBeat.o(5516);
    }

    public void g() {
        AppMethodBeat.i(5519, false);
        View view = this.j;
        if (view != null && view.getVisibility() == 0) {
            this.j.setVisibility(8);
        }
        AppMethodBeat.o(5519);
    }

    public void k() {
        AppMethodBeat.i(5522, false);
        View view = this.j;
        if (view != null && view.getVisibility() == 8) {
            this.j.setVisibility(0);
        }
        AppMethodBeat.o(5522);
    }

    public void a(String str) {
        AppMethodBeat.i(5525, false);
        k();
        this.k.setText(Html.fromHtml(String.format(getResources().getString(R.string.search_addess_notice), "\"", str, "\"")));
        AppMethodBeat.o(5525);
    }

    private void r() {
        AppMethodBeat.i(5526, false);
        if (o()) {
            this.d.notifyDataSetChanged();
        }
        AppMethodBeat.o(5526);
    }

    private void s() {
        AppMethodBeat.i(5527, false);
        this.d.a(false);
        this.d.notifyDataSetChanged();
        AppMethodBeat.o(5527);
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0079: APUT  (r0v9 java.lang.Object[]), (1 ??[boolean, int, float, short, byte, char]), (r1v7 java.lang.String) */
    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x00e2: APUT  (r0v8 java.lang.Object[]), (9 ??[int, float, short, byte, char]), (r11v9 java.lang.String) */
    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        String str;
        String str2;
        AppMethodBeat.i(5529, false);
        if (this.c.getHeaderViewsCount() >= 1) {
            i--;
        }
        List c = this.e.c();
        if (i < c.size() && this.f != null) {
            cn.missfresh.module.base.manager.c.q().b((UserAddress) null);
            TencentSuggestionAddress tencentSuggestionAddress = (TencentSuggestionAddress) c.get(i);
            a(tencentSuggestionAddress);
            String str3 = "search";
            if ("add_search_address".equalsIgnoreCase(this.r)) {
                StatisticsManager.u("click_searchAddress", "pos", Integer.valueOf(i + 1), "addrType", str3);
            }
            if ("home_select_address".equalsIgnoreCase(this.r)) {
                Object[] objArr = new Object[4];
                objArr[0] = "addrType";
                if (this.d.a()) {
                    str3 = "history";
                }
                objArr[1] = str3;
                objArr[2] = "pos";
                objArr[3] = Integer.valueOf(i + 1);
                StatisticsManager.s("click_address", objArr);
            }
            Object[] objArr2 = new Object[22];
            objArr2[0] = ai.e;
            objArr2[1] = "place_suggestion";
            objArr2[2] = "pos";
            objArr2[3] = Integer.valueOf(i);
            objArr2[4] = "poi_id";
            objArr2[5] = tencentSuggestionAddress.id;
            objArr2[6] = "poi_name";
            objArr2[7] = tencentSuggestionAddress.title;
            objArr2[8] = "user_lat";
            String str4 = "";
            objArr2[9] = !b.a(cn.missfresh.module.base.manager.c.q().u()) ? k.a(cn.missfresh.module.base.manager.c.q().u().getBytes()) : str4;
            objArr2[10] = "user_lan";
            objArr2[11] = !b.a(cn.missfresh.module.base.manager.c.q().v()) ? k.a(cn.missfresh.module.base.manager.c.q().v().getBytes()) : str4;
            objArr2[12] = "pos_lat";
            if (tencentSuggestionAddress.location != null) {
                str = k.a((tencentSuggestionAddress.location.lat + str4).getBytes());
            } else {
                str = str4;
            }
            objArr2[13] = str;
            objArr2[14] = "pos_lan";
            if (tencentSuggestionAddress.location != null) {
                str2 = k.a((tencentSuggestionAddress.location.lng + str4).getBytes());
            } else {
                str2 = str4;
            }
            objArr2[15] = str2;
            objArr2[16] = "poi_count";
            objArr2[17] = Integer.valueOf((this.c.getLastVisiblePosition() - this.c.getFirstVisiblePosition()) + 1);
            objArr2[18] = "city";
            objArr2[19] = tencentSuggestionAddress.city;
            objArr2[20] = "keyword";
            a aVar = this.o;
            if (aVar != null) {
                str4 = aVar.H();
            }
            objArr2[21] = str4;
            StatisticsManager.u("click_place_suggestion", objArr2);
        }
        AppMethodBeat.o(5529);
    }

    private void a(TencentSuggestionAddress tencentSuggestionAddress) {
        AppMethodBeat.i(5531, false);
        if (tencentSuggestionAddress != null) {
            if (this.d.a()) {
                if (tencentSuggestionAddress.isLatLngAvalible()) {
                    this.f.a(b(tencentSuggestionAddress), 18);
                } else {
                    t();
                }
            } else if (tencentSuggestionAddress.location != null) {
                this.f.a(c(tencentSuggestionAddress), 18);
                if (this.s) {
                    this.e.a(tencentSuggestionAddress);
                }
            } else {
                t();
            }
        }
        String str = this.a;
        d.c(str, "\u662f\u5426\u662f\u5386\u53f2\u5730\u5740 : " + this.d.a());
        AppMethodBeat.o(5531);
    }

    private void t() {
        AppMethodBeat.i(5533, false);
        a.a("\u8be5\u5730\u5740\u4e0d\u53ef\u7528");
        AppMethodBeat.o(5533);
    }

    private SelectAddressResult b(TencentSuggestionAddress tencentSuggestionAddress) {
        AppMethodBeat.i(5535, false);
        SelectAddressResult selectAddressResult = new SelectAddressResult();
        selectAddressResult.mRegionCode = tencentSuggestionAddress.adcode;
        selectAddressResult.mCityName = tencentSuggestionAddress.city;
        selectAddressResult.mAreaName = tencentSuggestionAddress.title;
        selectAddressResult.mDistrict = tencentSuggestionAddress.district;
        selectAddressResult.mLatitude = String.valueOf(tencentSuggestionAddress.lat);
        selectAddressResult.mLongitude = String.valueOf(tencentSuggestionAddress.lng);
        selectAddressResult.mProvinceName = tencentSuggestionAddress.province;
        selectAddressResult.mPoiId = tencentSuggestionAddress.id;
        AppMethodBeat.o(5535);
        return selectAddressResult;
    }

    private SelectAddressResult c(TencentSuggestionAddress tencentSuggestionAddress) {
        AppMethodBeat.i(5536, false);
        SelectAddressResult selectAddressResult = new SelectAddressResult();
        selectAddressResult.mRegionCode = tencentSuggestionAddress.adcode;
        selectAddressResult.mCityName = tencentSuggestionAddress.city;
        selectAddressResult.mDistrict = tencentSuggestionAddress.district;
        selectAddressResult.mAreaName = tencentSuggestionAddress.title;
        selectAddressResult.mAddress = tencentSuggestionAddress.address;
        selectAddressResult.mLatitude = String.valueOf(tencentSuggestionAddress.location.lat);
        selectAddressResult.mLongitude = String.valueOf(tencentSuggestionAddress.location.lng);
        selectAddressResult.mProvinceName = tencentSuggestionAddress.province;
        selectAddressResult.mPoiId = tencentSuggestionAddress.id;
        AppMethodBeat.o(5536);
        return selectAddressResult;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        h hVar;
        AppMethodBeat.i(5537, true);
        int id = view.getId();
        if (id == R.id.btn_clear_search_history_address) {
            e.a(getActivity(), "\u5220\u9664\u5386\u53f2\u8bb0\u5f55", "\u786e\u8ba4\u5220\u9664\u5386\u53f2\u8bb0\u5f55\uff1f", "\u53d6\u6d88", "\u786e\u8ba4", new 1(this));
        } else if (id == R.id.ll_search_result_container && (hVar = this.g) != null) {
            hVar.a();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(5537);
    }

    public void onDestroy() {
        AppMethodBeat.i(5538, false);
        super.onDestroy();
        this.e.d();
        AppMethodBeat.o(5538);
    }

    public void b(String str) {
        AppMethodBeat.i(5539, false);
        a(str);
        AppMethodBeat.o(5539);
    }
}
