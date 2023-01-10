package cn.missfresh.module.user.address.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import cn.missfresh.location_api.bean.Location;
import cn.missfresh.map.MFMapView;
import cn.missfresh.map.h;
import cn.missfresh.map.k;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.bean.TencentSearchData;
import cn.missfresh.module.base.common.providers.ILocationService;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.manager.c;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.module.base.widget.LoadMoreListView;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.address.adapter.a;
import cn.missfresh.module.user.address.bean.SelectAddressResult;
import cn.missfresh.module.user.address.presenter.b;
import cn.missfresh.module.user.address.view.SearchesAddressModuleFragment;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.umeng.analytics.pro.ai;
import java.io.Serializable;

public class SearchDetailAddressActivity extends BaseFragmentActivity implements TextWatcher, AdapterView.OnItemClickListener, h, LoadMoreListView.a, SearchesAddressModuleFragment.a, b, g {
    private a A;
    private double B;
    private double C;
    private double D = 0.0d;
    private double E = 0.0d;
    private int F = 1;
    private String G = "";
    private String H = "";
    private Marker I;
    private String J = "";
    private String K = "";
    private TextView L;
    private TextView M;
    private TextView N;
    private ILocationService O;
    private boolean P;
    private EditText a;
    private String j = "";
    private String k = "";
    private SearchesAddressModuleFragment l;
    private b m;
    private LinearLayout n;
    private LinearLayout o;
    private LinearLayout p;
    private MFMapView v = null;
    private View w;
    private View x;
    private ProgressBar y;
    private LoadMoreListView z;

    public void a() {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.address.view.SearchDetailAddressActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        SearchDetailAddressActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(5399, false);
        super.onCreate(bundle);
        getWindow().setSoftInputMode(2);
        setContentView(R.layout.user_activity_search_detail_address);
        s();
        this.v.onCreate(bundle);
        r();
        I();
        K();
        AppMethodBeat.o(5399);
    }

    /* access modifiers changed from: protected */
    public void r() {
        AppMethodBeat.i(5400, false);
        this.e.setLeftButtonVisibility(0);
        this.e.setCenterTxt("\u65b0\u589e\u6536\u8d27\u5730\u5740");
        this.e.setCenterVisibility(0);
        this.L = (TextView) findViewById(R.id.tv_city_name);
        this.M = (TextView) findViewById(R.id.tv_search_current_city);
        this.a = (EditText) findViewById(R.id.et_search_address_input);
        this.a.addTextChangedListener(this);
        this.a.setOnClickListener(this);
        this.n = (LinearLayout) findViewById(R.id.search_detail_adderss_addresstype);
        this.o = (LinearLayout) findViewById(R.id.search_detail_adderss_maptype);
        this.p = (LinearLayout) findViewById(R.id.search_detail_address_maptype_ll);
        this.w = findViewById(R.id.btn_my_location);
        this.y = (ProgressBar) findViewById(R.id.address_get_pro);
        this.x = findViewById(R.id.searchDeleteView);
        this.z = (LoadMoreListView) findViewById(R.id.listview);
        this.z.setOnLoadMoreListener(this);
        this.z.setOnItemClickListener(this);
        this.w.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.v.setOnMapCameraChangeListener(this);
        this.L.setOnClickListener(this);
        this.M.setOnClickListener(this);
        this.N = (TextView) findViewById(R.id.tv_cancel);
        this.N.setOnClickListener(this);
        AppMethodBeat.o(5400);
    }

    private void I() {
        AppMethodBeat.i(5402, false);
        Intent intent = getIntent();
        if (intent != null) {
            this.j = intent.getStringExtra("region_code");
            if (intent.hasExtra("city_name")) {
                this.k = intent.getStringExtra("city_name");
            }
            try {
                this.F = intent.getIntExtra("EXTRA_SHOW_TYPE", 1);
                this.B = intent.getDoubleExtra("EXTRA_CUR_LAT", 0.0d);
                this.C = intent.getDoubleExtra("EXTRA_CUR_LNG", 0.0d);
                this.J = this.B + "";
                this.K = this.C + "";
                this.D = intent.getDoubleExtra("EXTRA_SER_LAT", 0.0d);
                this.E = intent.getDoubleExtra("EXTRA_SER_LNG", 0.0d);
                this.G = intent.getStringExtra("EXTRA_ERAM_NAME");
                this.H = intent.getStringExtra("EXTRA_LOCATION_CITY");
            } catch (NumberFormatException e) {
                d.a(this.b, (Exception) e);
            }
        }
        AppMethodBeat.o(5402);
    }

    private void J() {
        AppMethodBeat.i(5405, false);
        ((InputMethodManager) this.a.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(0, 2);
        AppMethodBeat.o(5405);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r12v0, resolved type: cn.missfresh.module.user.address.view.SearchDetailAddressActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v6, types: [cn.missfresh.module.user.address.view.SearchesAddressModuleFragment, androidx.fragment.app.Fragment] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void K() {
        /*
        // Method dump skipped, instructions count: 310
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.user.address.view.SearchDetailAddressActivity.K():void");
    }

    public void a(cn.missfresh.map.b bVar) {
        AppMethodBeat.i(5410, false);
        this.v.setZoom(16);
        this.v.a(bVar);
        AppMethodBeat.o(5410);
    }

    public void b(cn.missfresh.map.b bVar) {
        AppMethodBeat.i(5411, false);
        k kVar = new k();
        kVar.a(bVar.b());
        kVar.b(bVar.c());
        this.v.setCenter(kVar);
        this.v.setZoom(16);
        AppMethodBeat.o(5411);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r17v0, resolved type: cn.missfresh.module.user.address.view.SearchDetailAddressActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x015b: APUT  (r4v11 java.lang.Object[]), (5 ??[int, float, short, byte, char]), (r3v8 java.lang.String) */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(5412, true);
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.search_detail_address_maptype_ll) {
            E();
            v();
        } else if (id == R.id.btn_my_location) {
            L();
            this.P = true;
            this.m.a(this.O);
        } else {
            String str = "";
            if (id == R.id.searchDeleteView) {
                this.a.setText(str);
                afterTextChanged(this.a.getText());
            } else if (id == R.id.et_search_address_input) {
                d.b(this.b, "have click edittext");
            } else if (id == R.id.tv_city_name) {
                StatisticsManager.u("click_city", new Object[0]);
                com.alibaba.android.arouter.b.a.a().a("/user/select_city").withString("current_name", this.L.getText().toString()).navigation(this, 17);
            } else if (id == R.id.tv_search_current_city) {
                StatisticsManager.u("click_city", new Object[0]);
                com.alibaba.android.arouter.b.a.a().a("/user/select_city").withString("current_name", this.M.getText().toString()).navigation(this, 17);
            } else if (id == R.id.tv_cancel) {
                String obj = this.a.getText().toString();
                this.a.setText(str);
                J();
                if (at.a((CharSequence) this.H, (CharSequence) this.L.getText().toString())) {
                    c(new cn.missfresh.map.b(this.B, this.C));
                    b(new cn.missfresh.map.b(this.B, this.C));
                    t();
                    this.m.a();
                    this.m.a(this.k, this.B + str, this.C + str, this.J, this.K);
                }
                Object[] objArr = new Object[8];
                objArr[0] = ai.e;
                objArr[1] = "cancel_place_suggestion";
                objArr[2] = "keyword";
                objArr[3] = obj;
                objArr[4] = "user_lat";
                objArr[5] = !cn.missfresh.utils.b.a(c.q().u()) ? cn.missfresh.module.base.utils.k.a(c.q().u().getBytes()) : str;
                objArr[6] = "user_lan";
                if (!cn.missfresh.utils.b.a(c.q().v())) {
                    str = cn.missfresh.module.base.utils.k.a(c.q().v().getBytes());
                }
                objArr[7] = str;
                StatisticsManager.u("click_cancel_place_suggestion", objArr);
            }
        }
        AppMethodBeat.o(5412);
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        AppMethodBeat.i(5416, false);
        d.d(this.b, this.j);
        if (editable.toString().length() == 0) {
            E();
        } else {
            k();
        }
        this.l.a(this.j, this.a.getText().toString(), this.J, this.K);
        AppMethodBeat.o(5416);
    }

    public void a(SelectAddressResult selectAddressResult, int i) {
        AppMethodBeat.i(5419, false);
        new Intent().putExtra("search_result", (Serializable) selectAddressResult);
        cn.missfresh.basiclib.utils.c.a().a(new cn.missfresh.module.user.a.b(selectAddressResult));
        finish();
        AppMethodBeat.o(5419);
    }

    public void s() {
        AppMethodBeat.i(5421, false);
        this.v = (MFMapView) findViewById(R.id.mapview);
        this.v.setScaleControlsEnabled(false);
        AppMethodBeat.o(5421);
    }

    public void t() {
        AppMethodBeat.i(5424, false);
        w();
        if (this.o.getVisibility() != 0) {
            this.o.setVisibility(0);
        }
        d.d(this.b, "show poi layout");
        AppMethodBeat.o(5424);
    }

    public void u() {
        AppMethodBeat.i(5425, false);
        if (this.o.getVisibility() != 8) {
            this.o.setVisibility(8);
        }
        AppMethodBeat.o(5425);
    }

    public void v() {
        AppMethodBeat.i(5426, false);
        if (this.n.getVisibility() != 0) {
            this.n.setVisibility(0);
        }
        u();
        d.d(this.b, "show suggest layout");
        AppMethodBeat.o(5426);
    }

    public void w() {
        AppMethodBeat.i(5427, false);
        this.n.setVisibility(8);
        if (this.a.hasFocus()) {
            n();
        }
        AppMethodBeat.o(5427);
    }

    public void b() {
        AppMethodBeat.i(5429, false);
        this.A.notifyDataSetChanged();
        this.z.a();
        AppMethodBeat.o(5429);
    }

    public void c() {
        AppMethodBeat.i(5430, false);
        this.z.d();
        AppMethodBeat.o(5430);
    }

    public void d() {
        AppMethodBeat.i(5432, false);
        this.z.a();
        AppMethodBeat.o(5432);
    }

    public void e() {
        AppMethodBeat.i(5434, false);
        this.z.smoothScrollToPosition(0);
        AppMethodBeat.o(5434);
    }

    public void x() {
        AppMethodBeat.i(5435, false);
        cn.missfresh.ui.a.a.a("\u5730\u5740\u9009\u62e9\u5931\u8d25\uff01\u8bf7\u91cd\u8bd5");
        AppMethodBeat.o(5435);
    }

    public void V_() {
        AppMethodBeat.i(5436, false);
        this.A.notifyDataSetChanged();
        this.z.c();
        AppMethodBeat.o(5436);
    }

    public void h() {
        AppMethodBeat.i(5438, false);
        D();
        AppMethodBeat.o(5438);
    }

    public void a(double d, double d2, String str) {
        AppMethodBeat.i(5439, false);
        c(new cn.missfresh.map.b(d, d2));
        a(new cn.missfresh.map.b(d, d2));
        AppMethodBeat.o(5439);
    }

    public void i() {
        AppMethodBeat.i(5441, false);
        cn.missfresh.ui.a.a.a(getString(R.string.get_location_error));
        j();
        AppMethodBeat.o(5441);
    }

    public void j() {
        AppMethodBeat.i(5443, false);
        if (this.y.getVisibility() == 0) {
            this.y.setVisibility(8);
        }
        if (this.w.getVisibility() == 8) {
            this.w.setVisibility(0);
        }
        AppMethodBeat.o(5443);
    }

    public void D() {
        AppMethodBeat.i(5444, false);
        if (this.w.getVisibility() == 0) {
            this.w.setVisibility(8);
        }
        if (this.y.getVisibility() == 8) {
            this.y.setVisibility(0);
        }
        AppMethodBeat.o(5444);
    }

    public void c(cn.missfresh.map.b bVar) {
        AppMethodBeat.i(5445, false);
        Marker marker = this.I;
        if (marker == null) {
            this.I = this.v.getMap().addMarker(new MarkerOptions().position(new LatLng(bVar.b(), bVar.c())).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_map_my)));
        } else {
            marker.setPosition(new LatLng(bVar.b(), bVar.c()));
        }
        AppMethodBeat.o(5445);
    }

    public void E() {
        AppMethodBeat.i(5446, false);
        if (this.x.getVisibility() != 8) {
            this.x.setVisibility(8);
            this.N.setVisibility(8);
        }
        AppMethodBeat.o(5446);
    }

    public void k() {
        AppMethodBeat.i(5448, false);
        if (this.x.getVisibility() != 0) {
            this.x.setVisibility(0);
            this.N.setVisibility(0);
        }
        AppMethodBeat.o(5448);
    }

    public void F() {
        AppMethodBeat.i(5450, false);
        try {
            if (this.a != null) {
                this.a.requestFocus();
                ((InputMethodManager) this.a.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(0, 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(5450);
    }

    public void W_() {
        AppMethodBeat.i(5453, false);
        a aVar = this.A;
        if (aVar != null) {
            aVar.notifyDataSetChanged();
        }
        AppMethodBeat.o(5453);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onDestroy() {
        AppMethodBeat.i(5456, false);
        this.v.onDestroy();
        this.m.d();
        ILocationService iLocationService = this.O;
        if (iLocationService != null) {
            iLocationService.a().c();
        }
        super.onDestroy();
        AppMethodBeat.o(5456);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onPause() {
        AppMethodBeat.i(5458, false);
        this.v.onPause();
        super.onPause();
        AppMethodBeat.o(5458);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        AppMethodBeat.i(5460, false);
        this.v.onResume();
        super.onResume();
        AppMethodBeat.o(5460);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onStop() {
        AppMethodBeat.i(5462, false);
        this.v.onStop();
        super.onStop();
        AppMethodBeat.o(5462);
    }

    @Override // cn.missfresh.module.base.widget.LoadMoreListView.a
    public void a(LoadMoreListView loadMoreListView) {
        AppMethodBeat.i(5465, false);
        b bVar = this.m;
        if (bVar == null) {
            AppMethodBeat.o(5465);
            return;
        }
        if (bVar.c()) {
            this.m.a(this.k, this.B + "", this.C + "", this.J, this.K);
        } else {
            V_();
        }
        AppMethodBeat.o(5465);
    }

    public void a(k kVar) {
        AppMethodBeat.i(5468, false);
        this.B = this.v.getMap().getMapCenter().getLatitude();
        this.C = this.v.getMap().getMapCenter().getLongitude();
        this.m.a();
        this.m.a(this.k, this.B + "", this.C + "", this.J, this.K);
        StatisticsManager.u("drag_map", new Object[0]);
        AppMethodBeat.o(5468);
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0118: APUT  (r5v4 java.lang.Object[]), (9 ??[int, float, short, byte, char]), (r13v8 java.lang.String) */
    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        AppMethodBeat.i(5473, false);
        try {
            if (this.m != null) {
                TencentSearchData tencentSearchData = (TencentSearchData) this.m.b().get(i);
                SelectAddressResult selectAddressResult = new SelectAddressResult();
                selectAddressResult.mAddress = tencentSearchData.address;
                String str = "";
                selectAddressResult.mCityName = tencentSearchData.ad_info != null ? tencentSearchData.ad_info.city : str;
                selectAddressResult.mAreaName = tencentSearchData.title;
                float f = 0.0f;
                selectAddressResult.mLatitude = String.valueOf(tencentSearchData.location != null ? tencentSearchData.location.lat : 0.0f);
                if (tencentSearchData.location != null) {
                    f = tencentSearchData.location.lng;
                }
                selectAddressResult.mLongitude = String.valueOf(f);
                selectAddressResult.mRegionCode = String.valueOf(tencentSearchData.ad_info != null ? tencentSearchData.ad_info.adcode : 0);
                selectAddressResult.mDistrict = tencentSearchData.ad_info != null ? tencentSearchData.ad_info.district : str;
                selectAddressResult.mProvinceName = tencentSearchData.ad_info != null ? tencentSearchData.ad_info.province : str;
                selectAddressResult.mPoiId = tencentSearchData.id;
                a(selectAddressResult, 18);
                String str2 = this.b;
                d.d(str2, "mRegionCode:" + selectAddressResult.mRegionCode);
                StatisticsManager.u("click_searchAddress", "pos", Integer.valueOf(i + 1), "addrType", "auto");
                Object[] objArr = new Object[20];
                objArr[0] = ai.e;
                objArr[1] = "geocoder";
                objArr[2] = "pos";
                objArr[3] = Integer.valueOf(i);
                objArr[4] = "poi_id";
                objArr[5] = tencentSearchData.id;
                objArr[6] = "poi_name";
                objArr[7] = tencentSearchData.title;
                objArr[8] = "user_lat";
                objArr[9] = !cn.missfresh.utils.b.a(c.q().u()) ? cn.missfresh.module.base.utils.k.a(c.q().u().getBytes()) : str;
                objArr[10] = "user_lan";
                objArr[11] = !cn.missfresh.utils.b.a(c.q().v()) ? cn.missfresh.module.base.utils.k.a(c.q().v().getBytes()) : str;
                objArr[12] = "pos_lat";
                objArr[13] = selectAddressResult.mLatitude != null ? cn.missfresh.module.base.utils.k.a(selectAddressResult.mLatitude.getBytes()) : str;
                objArr[14] = "pos_lan";
                if (selectAddressResult.mLongitude != null) {
                    str = cn.missfresh.module.base.utils.k.a(selectAddressResult.mLongitude.getBytes());
                }
                objArr[15] = str;
                objArr[16] = "poi_count";
                objArr[17] = Integer.valueOf((this.z.getLastVisiblePosition() - this.z.getFirstVisiblePosition()) + 1);
                objArr[18] = "city";
                objArr[19] = selectAddressResult.mCityName;
                StatisticsManager.v("click_geocoder", objArr);
            } else {
                x();
            }
        } catch (Exception e) {
            e.printStackTrace();
            x();
        }
        AppMethodBeat.o(5473);
    }

    public void G() {
        AppMethodBeat.i(5475, false);
        F();
        AppMethodBeat.o(5475);
    }

    public String H() {
        AppMethodBeat.i(5477, false);
        EditText editText = this.a;
        String obj = editText != null ? editText.getText().toString() : "";
        AppMethodBeat.o(5477);
        return obj;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        AppMethodBeat.i(5478, false);
        super.onActivityResult(i, i2, intent);
        if (i == 17 && 1 == i2 && intent != null) {
            String stringExtra = intent.getStringExtra("city_name");
            this.j = stringExtra;
            this.M.setText(stringExtra);
            this.L.setText(stringExtra);
            String str = this.H;
            if (str == null || !str.equals(this.L.getText().toString())) {
                E();
                v();
            } else {
                c(new cn.missfresh.map.b(this.B, this.C));
                b(new cn.missfresh.map.b(this.B, this.C));
                t();
                this.m.a();
                this.m.a(this.k, this.B + "", this.C + "", this.J, this.K);
            }
            intent.getStringExtra("city_code");
            this.l.a(this.j, this.a.getText().toString(), this.J, this.K);
            this.l.b(this.j);
        }
        AppMethodBeat.o(5478);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.address.view.SearchDetailAddressActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void L() {
        AppMethodBeat.i(5479, false);
        if (this.O == null) {
            this.O = (ILocationService) com.alibaba.android.arouter.b.a.a().a("/user/location_service").navigation();
            this.O.a().b().a(this, new AnonymousClass1(this));
        }
        AppMethodBeat.o(5479);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.address.view.SearchDetailAddressActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements Observer<Location> {
        final /* synthetic */ SearchDetailAddressActivity a;

        AnonymousClass1(SearchDetailAddressActivity searchDetailAddressActivity) {
            JniLib.cV(this, searchDetailAddressActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_APPOP_DENIED_WRITE_SETTINGS));
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(5389, false);
            a((Location) obj);
            AppMethodBeat.o(5389);
        }

        public void a(Location location) {
            AppMethodBeat.i(5388, false);
            if (!this.a.P) {
                AppMethodBeat.o(5388);
                return;
            }
            this.a.P = false;
            this.a.m.a(location);
            AppMethodBeat.o(5388);
        }
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0055: APUT  (r2v1 java.lang.Object[]), (5 ??[int, float, short, byte, char]), (r0v10 java.lang.String) */
    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void J_() {
        AppMethodBeat.i(5481, false);
        super.J_();
        Object[] objArr = new Object[8];
        objArr[0] = ai.e;
        objArr[1] = "exit_place_suggestion";
        objArr[2] = "keyword";
        objArr[3] = this.a.getText().toString();
        objArr[4] = "user_lat";
        String str = "";
        objArr[5] = !cn.missfresh.utils.b.a(c.q().u()) ? cn.missfresh.module.base.utils.k.a(c.q().u().getBytes()) : str;
        objArr[6] = "user_lan";
        if (!cn.missfresh.utils.b.a(c.q().v())) {
            str = cn.missfresh.module.base.utils.k.a(c.q().v().getBytes());
        }
        objArr[7] = str;
        StatisticsManager.u("click_exit_place_suggestion", objArr);
        AppMethodBeat.o(5481);
    }
}
