package cn.missfresh.module.user.address.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.basiclib.utils.permission.c;
import cn.missfresh.location_api.bean.Location;
import cn.missfresh.module.base.bean.TencentSearchData;
import cn.missfresh.module.base.bean.UserAddress;
import cn.missfresh.module.base.common.providers.ILocationService;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.utils.as;
import cn.missfresh.module.mvp.mvp.interfaces.IPresenter;
import cn.missfresh.module.mvp.mvp.module.BaseMVPActivity;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.address.adapter.CommonAdapter;
import cn.missfresh.module.user.address.adapter.HeaderRecyclerAndFooterWrapperAdapter;
import cn.missfresh.module.user.address.adapter.MeituanAdapter;
import cn.missfresh.module.user.address.adapter.SearchCityAddressAdapter;
import cn.missfresh.module.user.address.adapter.b;
import cn.missfresh.module.user.address.adapter.viewholder.ViewHolder;
import cn.missfresh.module.user.address.bean.MeituanHeaderBean;
import cn.missfresh.module.user.address.bean.MeituanTopHeaderBean;
import cn.missfresh.module.user.address.bean.SupporCityBean;
import cn.missfresh.module.user.address.presenter.SupportCityOptPresenter;
import cn.missfresh.module.user.address.view.LocationLayout;
import cn.missfresh.module.user.address.widget.indexlib.IndexBar.bean.BaseIndexPinyinBean;
import cn.missfresh.module.user.address.widget.indexlib.IndexBar.widget.IndexBar;
import cn.missfresh.module.user.address.widget.indexlib.suspension.SuspensionDecoration;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import com.tencent.imsdk.BaseConstants;
import java.util.ArrayList;
import java.util.List;

public class SupportCityOptActivity extends BaseMVPActivity<SupportCityOptPresenter> implements SearchCityAddressAdapter.a, i, SuspensionDecoration.a {
    private String A;
    private IndexBar B;
    private TextView C;
    private LocationLayout D;
    private ImageView E;
    private boolean F = false;
    private ILocationService G;
    private boolean H;
    private c I;
    private Context a;
    private EditText b;
    private TextView c;
    private LinearLayout d;
    private RecyclerView e;
    private FrameLayout f;
    private RecyclerView g;
    private MeituanAdapter h;
    private HeaderRecyclerAndFooterWrapperAdapter i;
    private SearchCityAddressAdapter j;
    private LinearLayoutManager k;
    private LinearLayoutManager l;
    private List<BaseIndexPinyinBean> m;
    private List<MeituanHeaderBean> n;
    private List<SupporCityBean> o;
    private SuspensionDecoration p;

    @Override // cn.missfresh.module.user.address.view.i
    public void K() {
    }

    @Override // cn.missfresh.module.user.address.view.i
    public void a(int i, String str) {
    }

    @Override // cn.missfresh.module.user.address.view.i
    public void c(List<TencentSearchData> list) {
    }

    @Override // cn.missfresh.module.user.address.view.i
    public void n() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.address.view.SupportCityOptActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void onWindowFocusChanged(boolean z) {
        SupportCityOptActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ IPresenter f() {
        AppMethodBeat.i(5987, false);
        SupportCityOptPresenter l = l();
        AppMethodBeat.o(5987);
        return l;
    }

    static /* synthetic */ void b(SupportCityOptActivity supportCityOptActivity, boolean z) {
        AppMethodBeat.i(BaseConstants.ERR_IN_PROGESS, false);
        supportCityOptActivity.a(z);
        AppMethodBeat.o(BaseConstants.ERR_IN_PROGESS);
    }

    static /* synthetic */ void j(SupportCityOptActivity supportCityOptActivity) {
        AppMethodBeat.i(BaseConstants.ERR_HTTP_REQ_FAILED, false);
        supportCityOptActivity.N();
        AppMethodBeat.o(BaseConstants.ERR_HTTP_REQ_FAILED);
    }

    static /* synthetic */ void k(SupportCityOptActivity supportCityOptActivity) {
        AppMethodBeat.i(BaseConstants.ERR_SDK_NOT_INITIALIZED, false);
        supportCityOptActivity.P();
        AppMethodBeat.o(BaseConstants.ERR_SDK_NOT_INITIALIZED);
    }

    /* access modifiers changed from: protected */
    public void a() {
        AppMethodBeat.i(5928, false);
        this.v.a();
        AppMethodBeat.o(5928);
    }

    /* access modifiers changed from: protected */
    public SupportCityOptPresenter l() {
        AppMethodBeat.i(5930, false);
        SupportCityOptPresenter supportCityOptPresenter = new SupportCityOptPresenter(this);
        AppMethodBeat.o(5930);
        return supportCityOptPresenter;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: cn.missfresh.module.user.address.view.SupportCityOptActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void g() {
        AppMethodBeat.i(5933, false);
        this.a = this;
        if (getIntent() != null) {
            this.A = getIntent().getStringExtra("current_name");
        }
        this.c.setOnClickListener(new 1(this));
        this.b.addTextChangedListener(new 2(this));
        this.E.setOnClickListener(new 3(this));
        RecyclerView recyclerView = this.g;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.k = linearLayoutManager;
        recyclerView.setLayoutManager(linearLayoutManager);
        this.m = new ArrayList();
        this.n = new ArrayList();
        this.n.add(new MeituanHeaderBean(new ArrayList(), "\u6781\u901f\u8fbe", ""));
        this.m.addAll(this.n);
        this.h = new MeituanAdapter(this, R.layout.meituan_item_select_city, this.o);
        this.h.a((b) new AnonymousClass4(this));
        this.i = new AnonymousClass5(this.h);
        this.i.a(0, R.layout.meituan_item_header_top, new MeituanTopHeaderBean(""));
        this.i.a(1, R.layout.meituan_item_header, this.n.get(0));
        this.g.setAdapter(this.i);
        RecyclerView recyclerView2 = this.g;
        SuspensionDecoration f = new SuspensionDecoration(this, this.m).a((int) TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics())).b(this.a.getResources().getColor(R.color.color_F8F8F8)).d((int) TypedValue.applyDimension(2, 12.0f, getResources().getDisplayMetrics())).c(this.a.getResources().getColor(R.color.black_474245)).f(this.i.a() - this.n.size());
        this.p = f;
        recyclerView2.addItemDecoration(f);
        this.p.a((SuspensionDecoration.a) this);
        this.B.a(this.C).a(true).a(this.k).a(this.i.a() - this.n.size());
        RecyclerView recyclerView3 = this.e;
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        this.l = linearLayoutManager2;
        recyclerView3.setLayoutManager(linearLayoutManager2);
        this.j = new SearchCityAddressAdapter(this);
        this.j.a((SearchCityAddressAdapter.a) this);
        this.e.setAdapter(this.j);
        AppMethodBeat.o(5933);
    }

    /* renamed from: cn.missfresh.module.user.address.view.SupportCityOptActivity$4  reason: invalid class name */
    class AnonymousClass4 implements b {
        final /* synthetic */ SupportCityOptActivity a;

        AnonymousClass4(SupportCityOptActivity supportCityOptActivity) {
            JniLib.cV(this, supportCityOptActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_INSTANT_APP_RESOLUTION_PHASE_ONE));
        }

        public boolean b(ViewGroup viewGroup, View view, Object obj, int i) {
            return false;
        }

        public void a(ViewGroup viewGroup, View view, Object obj, int i) {
            AppMethodBeat.i(5707, false);
            StatisticsManager.w("click_city", "pos", Integer.valueOf(i + 1), "cityType", "auto");
            if (i >= this.a.o.size()) {
                AppMethodBeat.o(5707);
                return;
            }
            SupportCityOptActivity supportCityOptActivity = this.a;
            supportCityOptActivity.a((SupporCityBean) supportCityOptActivity.o.get(i));
            AppMethodBeat.o(5707);
        }
    }

    /* renamed from: cn.missfresh.module.user.address.view.SupportCityOptActivity$5  reason: invalid class name */
    class AnonymousClass5 extends HeaderRecyclerAndFooterWrapperAdapter {
        AnonymousClass5(RecyclerView.Adapter adapter) {
            super(adapter);
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.user.address.adapter.HeaderRecyclerAndFooterWrapperAdapter
        public void a(ViewHolder viewHolder, int i, int i2, Object obj) {
            AppMethodBeat.i(5901, false);
            if (i2 == R.layout.meituan_item_header) {
                RecyclerView recyclerView = (RecyclerView) viewHolder.a(R.id.rvCity);
                recyclerView.setAdapter(new AnonymousClass1(SupportCityOptActivity.this.a, R.layout.meituan_item_header_item, ((MeituanHeaderBean) obj).getCityList()));
                recyclerView.setLayoutManager(new GridLayoutManager(SupportCityOptActivity.this.a, 4));
            } else if (i2 == R.layout.meituan_item_header_top) {
                MeituanTopHeaderBean meituanTopHeaderBean = (MeituanTopHeaderBean) obj;
                SupportCityOptActivity.this.D = viewHolder.a(R.id.cv_location);
                if (cn.missfresh.utils.b.a(meituanTopHeaderBean.getTxt())) {
                    SupportCityOptActivity.this.D.c();
                    if (!SupportCityOptActivity.this.F) {
                        StatisticsManager.w("page_view", "isValid", "0");
                        SupportCityOptActivity.this.F = true;
                    }
                } else {
                    if (!SupportCityOptActivity.this.F) {
                        StatisticsManager.w("page_view", "isValid", "1");
                        SupportCityOptActivity.this.F = true;
                    }
                    SupportCityOptActivity.this.D.a(meituanTopHeaderBean.getTxt());
                }
                SupportCityOptActivity.this.D.setOnLocationClickListener(new AnonymousClass2(this));
                SupportCityOptActivity.b(SupportCityOptActivity.this, false);
            }
            AppMethodBeat.o(5901);
        }

        /* renamed from: cn.missfresh.module.user.address.view.SupportCityOptActivity$5$1  reason: invalid class name */
        class AnonymousClass1 extends CommonAdapter<SupporCityBean> {
            AnonymousClass1(Context context, int i, List list) {
                super(context, i, list);
            }

            @Override // cn.missfresh.module.user.address.adapter.CommonAdapter
            public /* synthetic */ void a(ViewHolder viewHolder, Object obj) {
                AppMethodBeat.i(5720, false);
                a(viewHolder, (SupporCityBean) obj);
                AppMethodBeat.o(5720);
            }

            public void a(ViewHolder viewHolder, SupporCityBean supporCityBean) {
                AppMethodBeat.i(5716, false);
                viewHolder.a(R.id.tvName, supporCityBean.getName());
                viewHolder.a().setOnClickListener(new 1(this, supporCityBean));
                AppMethodBeat.o(5716);
            }
        }

        /* renamed from: cn.missfresh.module.user.address.view.SupportCityOptActivity$5$2  reason: invalid class name */
        class AnonymousClass2 implements LocationLayout.a {
            final /* synthetic */ AnonymousClass5 a;

            AnonymousClass2(AnonymousClass5 r5) {
                JniLib.cV(this, r5, 901);
            }

            public void a() {
                AppMethodBeat.i(5725, false);
                SupportCityOptActivity.j(SupportCityOptActivity.this);
                AppMethodBeat.o(5725);
            }

            public void b() {
                AppMethodBeat.i(5730, false);
                SupportCityOptActivity.k(SupportCityOptActivity.this);
                AppMethodBeat.o(5730);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.address.view.SupportCityOptActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void k() {
        AppMethodBeat.i(5936, false);
        this.z.setVisibility(8);
        as.a((Activity) this);
        this.b = (EditText) findViewById(R.id.et_search_address_input);
        this.c = (TextView) findViewById(R.id.tv_cancel_select);
        this.d = (LinearLayout) findViewById(R.id.ll_search_result);
        this.e = (RecyclerView) findViewById(R.id.rl_search_result);
        this.f = (FrameLayout) findViewById(R.id.fl_recommend_result);
        this.g = (RecyclerView) findViewById(R.id.rv);
        this.C = (TextView) findViewById(R.id.tvSideBarHint);
        this.B = findViewById(R.id.indexBar);
        this.E = (ImageView) findViewById(R.id.searchDeleteView);
        AppMethodBeat.o(5936);
    }

    /* access modifiers changed from: protected */
    public int M_() {
        return R.layout.user_activity_support_city_opt;
    }

    @Override // cn.missfresh.module.user.address.view.i
    public void a(List<SupporCityBean> list) {
        AppMethodBeat.i(5942, false);
        MeituanHeaderBean meituanHeaderBean = this.n.get(0);
        if (this.v != null) {
            meituanHeaderBean.setSuspensionTag(this.v.c());
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        meituanHeaderBean.setCityList(arrayList);
        this.i.notifyItemRangeChanged(1, 1);
        AppMethodBeat.o(5942);
    }

    @Override // cn.missfresh.module.user.address.view.i
    public void b(List<SupporCityBean> list) {
        int i = 0;
        AppMethodBeat.i(5945, false);
        this.o = new ArrayList();
        this.o.addAll(list);
        this.j.a(this.o);
        this.B.getDataHelper().c(this.o);
        this.h.a(this.o);
        this.i.notifyDataSetChanged();
        if (!cn.missfresh.utils.b.a(this.m)) {
            i = this.m.size();
        }
        this.m.addAll(this.o);
        this.B.a(this.m).invalidate();
        List<BaseIndexPinyinBean> list2 = this.m;
        this.v.a((list2 == null || list2.size() <= i) ? "" : this.m.get(i).getSuspensionTag());
        this.p.a(this.m);
        AppMethodBeat.o(5945);
    }

    @Override // cn.missfresh.module.user.address.view.i
    public void b(String str) {
        AppMethodBeat.i(5952, false);
        if (!isFinishing()) {
            this.D.a(str);
        }
        AppMethodBeat.o(5952);
    }

    public void a(SupporCityBean supporCityBean) {
        AppMethodBeat.i(5956, false);
        if (supporCityBean != null) {
            Intent intent = new Intent();
            intent.putExtra("city_name", supporCityBean.getName());
            intent.putExtra("city_code", supporCityBean.getCode());
            intent.putExtra("is_chrome_city", supporCityBean.getIsChromeCity());
            setResult(1, intent);
            finish();
        }
        AppMethodBeat.o(5956);
    }

    public void a(SupporCityBean supporCityBean, int i) {
        AppMethodBeat.i(5958, false);
        StatisticsManager.w("click_city", "pos", Integer.valueOf(i + 1), "cityType", "search");
        a(supporCityBean);
        AppMethodBeat.o(5958);
    }

    private void a(boolean z) {
        AppMethodBeat.i(5963, false);
        if (this.I == null) {
            this.I = new c();
        }
        if (this.I.a(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION) || z) {
            t_();
        }
        AppMethodBeat.o(5963);
    }

    private void N() {
        AppMethodBeat.i(5966, false);
        if (this.I == null) {
            this.I = new c();
        }
        if (this.D.f()) {
            a.a("\u6b63\u5728\u5b9a\u4f4d\u4e2d");
            AppMethodBeat.o(5966);
            return;
        }
        O();
        this.H = true;
        this.v.a(this.G);
        AppMethodBeat.o(5966);
    }

    public void l_(String str) {
        AppMethodBeat.i(5968, false);
        L();
        AppMethodBeat.o(5968);
    }

    public void t_() {
        AppMethodBeat.i(5971, false);
        if (this.D.f()) {
            a.a("\u6b63\u5728\u5b9a\u4f4d\u4e2d");
        } else if (this.D.g()) {
            List<TencentSearchData> d = this.v.d();
            if (!cn.missfresh.utils.b.a(d)) {
                a(d.get(0));
            } else {
                L();
            }
        } else {
            O();
            this.H = true;
            this.v.a(this.G);
        }
        AppMethodBeat.o(5971);
    }

    @Override // cn.missfresh.module.user.address.view.i
    public void L() {
        AppMethodBeat.i(5972, false);
        this.D.c();
        AppMethodBeat.o(5972);
    }

    @Override // cn.missfresh.module.user.address.view.i
    public void M() {
        AppMethodBeat.i(5974, false);
        this.D.b();
        AppMethodBeat.o(5974);
    }

    public void a(TencentSearchData tencentSearchData) {
        AppMethodBeat.i(5976, false);
        if (tencentSearchData != null) {
            Intent intent = new Intent();
            intent.putExtra("city_name", tencentSearchData.ad_info.city);
            intent.putExtra("city_code", tencentSearchData.ad_info.adcode);
            intent.putExtra("is_chrome_city", 0);
            setResult(1, intent);
            finish();
        }
        AppMethodBeat.o(5976);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.address.view.SupportCityOptActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void O() {
        AppMethodBeat.i(5979, false);
        if (this.G == null) {
            this.G = (ILocationService) com.alibaba.android.arouter.b.a.a().a("/user/location_service").navigation();
            this.G.a().b().a(this, new AnonymousClass6(this));
            this.G.b().observe(this, new AnonymousClass7(this));
        }
        AppMethodBeat.o(5979);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.address.view.SupportCityOptActivity$6  reason: invalid class name */
    public class AnonymousClass6 implements Observer<Location> {
        final /* synthetic */ SupportCityOptActivity a;

        AnonymousClass6(SupportCityOptActivity supportCityOptActivity) {
            JniLib.cV(this, supportCityOptActivity, 902);
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(5910, false);
            a((Location) obj);
            AppMethodBeat.o(5910);
        }

        public void a(Location location) {
            AppMethodBeat.i(5907, false);
            if (!this.a.H) {
                AppMethodBeat.o(5907);
                return;
            }
            this.a.H = false;
            this.a.v.a(location, this.a.G);
            AppMethodBeat.o(5907);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.address.view.SupportCityOptActivity$7  reason: invalid class name */
    public class AnonymousClass7 implements Observer<Integer> {
        final /* synthetic */ SupportCityOptActivity a;

        AnonymousClass7(SupportCityOptActivity supportCityOptActivity) {
            JniLib.cV(this, supportCityOptActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.FIELD_INSTANT_APP_LAUNCH_TOKEN));
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(5917, false);
            a((Integer) obj);
            AppMethodBeat.o(5917);
        }

        public void a(Integer num) {
            AppMethodBeat.i(5915, false);
            this.a.L();
            if (num != null && num.intValue() == 1) {
                this.a.t_();
            }
            AppMethodBeat.o(5915);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        AppMethodBeat.i(5981, false);
        SupportCityOptActivity.super.onDestroy();
        ILocationService iLocationService = this.G;
        if (iLocationService != null) {
            iLocationService.a().c();
        }
        AppMethodBeat.o(5981);
    }

    public String a(int i, int i2, String str) {
        AppMethodBeat.i(5984, false);
        if (cn.missfresh.utils.b.a(str)) {
            AppMethodBeat.o(5984);
            return str;
        } else if (cn.missfresh.utils.b.a(this.v.e()) || !str.equals(this.v.f())) {
            AppMethodBeat.o(5984);
            return str;
        } else {
            String e = this.v.e();
            AppMethodBeat.o(5984);
            return e;
        }
    }

    private void P() {
        AppMethodBeat.i(5985, false);
        if (this.D.f()) {
            a.a("\u6b63\u5728\u5b9a\u4f4d\u4e2d");
            AppMethodBeat.o(5985);
        } else if (this.D.g()) {
            List<TencentSearchData> d = this.v.d();
            if (!cn.missfresh.utils.b.a(d)) {
                cn.missfresh.module.base.manager.c.q().b((UserAddress) null);
                a(d.get(0));
            } else {
                L();
            }
            AppMethodBeat.o(5985);
        } else {
            AppMethodBeat.o(5985);
        }
    }
}
