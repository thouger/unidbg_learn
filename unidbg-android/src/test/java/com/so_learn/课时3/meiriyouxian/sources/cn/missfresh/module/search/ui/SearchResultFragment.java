package cn.missfresh.module.search.ui;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.bean.BasinessForm;
import cn.missfresh.module.base.common.adapter.MultiFormatAdapter;
import cn.missfresh.module.base.common.helper.GoodsAnimHelper;
import cn.missfresh.module.base.common.helper.b;
import cn.missfresh.module.base.common.interfaces.d;
import cn.missfresh.module.base.common.listener.a;
import cn.missfresh.module.base.common.providers.IProductDetailService;
import cn.missfresh.module.base.common.providers.IShoppingCartService2;
import cn.missfresh.module.base.common.resourcemanager.bean.SkipBean;
import cn.missfresh.module.base.common.resourcemanager.viewmodel.RMViewModel;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.helper.j;
import cn.missfresh.module.base.helper.k;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.module.base.shoppingcart_settle.view.MiniShoppingCartView;
import cn.missfresh.module.base.support.CenterLayoutManager;
import cn.missfresh.module.base.utils.aj;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.module.base.utils.x;
import cn.missfresh.module.base.widget.BadgeView;
import cn.missfresh.module.base.widget.MultiStateLayout;
import cn.missfresh.module.mvvm.BaseMVVMFragment;
import cn.missfresh.module.search.R;
import cn.missfresh.module.search.adapter.QuickFilterAdapter;
import cn.missfresh.module.search.adapter.SearchResultProductAdapter;
import cn.missfresh.module.search.adapter.bean.QuickFilterBean;
import cn.missfresh.module.search.adapter.bean.SearchBaseBean;
import cn.missfresh.module.search.adapter.bean.SearchBusinessForm;
import cn.missfresh.module.search.adapter.bean.SearchHotListBean;
import cn.missfresh.module.search.adapter.bean.SearchHotProductBean;
import cn.missfresh.module.search.adapter.bean.SearchResultBean;
import cn.missfresh.module.search.adapter.bean.SearchSellerTypePos;
import cn.missfresh.module.search.adapter.bean.SearchSloganBean;
import cn.missfresh.module.search.adapter.bean.SearchTagsBean;
import cn.missfresh.module.search.adapter.holder.SearchSloganHolder;
import cn.missfresh.module.search.adapter.holder.SearchSloganTagHolder;
import cn.missfresh.module.search.adapter.holder.SearchTagHolder;
import cn.missfresh.module.search.decoration.SearchResultDecoration;
import cn.missfresh.module.search.interfaces.c;
import cn.missfresh.module.search.viewmodule.SearchChangeModule;
import cn.missfresh.module.search.viewmodule.SearchResultModule;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.f;
import com.google.android.material.appbar.AppBarLayout;
import com.umeng.analytics.pro.ai;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SearchResultFragment extends BaseMVVMFragment {
    private RMViewModel A;
    private boolean B;
    private View C;
    private int D;
    private String E;
    private QuickFilterAdapter F;
    private RecyclerView G;
    private String H;
    private int I;
    private RecyclerView J;
    private LinearLayoutManager K;
    private MultiFormatAdapter L;
    private int M = 0;
    private List<BasinessForm> N;
    private LinearLayout O;
    private LinearLayout P;
    private TextView Q;
    private TextView R;
    private List<SearchSellerTypePos> S;
    private TextView T;
    private View.OnClickListener U = new 5(this);
    private d V = new AnonymousClass16();
    private a W = new AnonymousClass17();
    private cn.missfresh.module.search.interfaces.a X = new AnonymousClass18();
    private c Y = new AnonymousClass19();
    private MultiStateLayout.d Z = new AnonymousClass2();
    private RecyclerView a;
    private RecyclerView.OnScrollListener aa = new AnonymousClass3();
    private Observer<Integer> ab = new AnonymousClass4();
    private Observer<String> ac = new AnonymousClass6();
    private Observer<List<SearchBaseBean>> ad = new AnonymousClass7();
    private Observer<List<SearchSellerTypePos>> ae = new AnonymousClass8();
    private Observer<List<QuickFilterBean>> af = new AnonymousClass9();
    private Observer<List<BasinessForm>> ag = new AnonymousClass10();
    private Observer<Map<String, String>> ah = new AnonymousClass11();
    private Observer<List<SkipBean>> ai = new AnonymousClass12();
    private SearchResultProductAdapter b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private View h;
    private View i;
    private View j;
    private View k;
    private View l;
    private View m;
    private View n;
    private AppBarLayout o;
    private GoodsAnimHelper p;
    private BadgeView q;
    private GridLayoutManager r;
    private MultiStateLayout s;
    private SearchResultModule t;
    private SearchChangeModule u;
    private cn.missfresh.module.search.c.a v;
    private LinearLayoutManager w;
    private MiniShoppingCartView x;
    private ImageView y;
    private b z;

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.search.ui.SearchResultFragment */
    /* JADX WARN: Multi-variable type inference failed */
    public void onHiddenChanged(boolean z) {
        SearchResultFragment.super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    public String q() {
        return "search_result";
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.search.ui.SearchResultFragment */
    /* JADX WARN: Multi-variable type inference failed */
    public void setUserVisibleHint(boolean z) {
        SearchResultFragment.super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    public SearchResultFragment() {
        AppMethodBeat.i(12048, false);
        AppMethodBeat.o(12048);
    }

    static /* synthetic */ void a(SearchResultFragment searchResultFragment, int i) {
        AppMethodBeat.i(12074, false);
        searchResultFragment.b(i);
        AppMethodBeat.o(12074);
    }

    static /* synthetic */ void a(SearchResultFragment searchResultFragment, int i, int i2) {
        AppMethodBeat.i(12083, false);
        searchResultFragment.a(i, i2);
        AppMethodBeat.o(12083);
    }

    static /* synthetic */ void a(SearchResultFragment searchResultFragment, List list, String str) {
        AppMethodBeat.i(12082, false);
        searchResultFragment.a(list, str);
        AppMethodBeat.o(12082);
    }

    static /* synthetic */ void a(SearchResultFragment searchResultFragment, boolean z) {
        AppMethodBeat.i(12077, false);
        searchResultFragment.c(z);
        AppMethodBeat.o(12077);
    }

    static /* synthetic */ void b(SearchResultFragment searchResultFragment, boolean z) {
        AppMethodBeat.i(12084, false);
        searchResultFragment.b(z);
        AppMethodBeat.o(12084);
    }

    static /* synthetic */ void c(SearchResultFragment searchResultFragment, String str) {
        AppMethodBeat.i(12088, false);
        searchResultFragment.b(str);
        AppMethodBeat.o(12088);
    }

    static /* synthetic */ void d(SearchResultFragment searchResultFragment) {
        AppMethodBeat.i(12075, false);
        searchResultFragment.r();
        AppMethodBeat.o(12075);
    }

    static /* synthetic */ void e(SearchResultFragment searchResultFragment) {
        AppMethodBeat.i(12076, false);
        searchResultFragment.l();
        AppMethodBeat.o(12076);
    }

    static /* synthetic */ void p(SearchResultFragment searchResultFragment) {
        AppMethodBeat.i(12080, false);
        searchResultFragment.m();
        AppMethodBeat.o(12080);
    }

    static /* synthetic */ void u(SearchResultFragment searchResultFragment) {
        AppMethodBeat.i(12085, false);
        searchResultFragment.s();
        AppMethodBeat.o(12085);
    }

    public static SearchResultFragment a() {
        AppMethodBeat.i(12049, false);
        SearchResultFragment searchResultFragment = new SearchResultFragment();
        aj.a().a("PERFORMANCE_SEARCH");
        AppMethodBeat.o(12049);
        return searchResultFragment;
    }

    public void a(String str) {
        AppMethodBeat.i(12050, false);
        this.c.setText(str);
        SearchResultModule searchResultModule = this.t;
        searchResultModule.e = str;
        searchResultModule.h = 0;
        this.e.setSelected(false);
        SearchResultModule searchResultModule2 = this.t;
        searchResultModule2.i = false;
        searchResultModule2.j = true;
        b(-1);
        c(false);
        if (this.D == 1) {
            this.d.setSelected(true);
            this.m.setVisibility(8);
        }
        AppMethodBeat.o(12050);
    }

    public void a(int i) {
        this.D = i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.search.ui.SearchResultFragment */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void g() {
        AppMethodBeat.i(12053, false);
        this.v = (cn.missfresh.module.search.c.a) com.alibaba.android.arouter.b.a.a().a("/search/interceptor").navigation();
        this.u = (SearchChangeModule) b(SearchChangeModule.class);
        this.t = (SearchResultModule) a(SearchResultModule.class);
        this.A = (RMViewModel) b(RMViewModel.class);
        this.t.b().observe(this, this.ab);
        this.t.a().observe(this, this.ac);
        this.t.c().observe(this, this.ad);
        this.t.g().observe(this, this.ae);
        this.t.d().observe(this, this.ah);
        this.t.e().observe(this, this.af);
        this.t.f().observe(this, this.ag);
        this.A.d().observe(this, this.ai);
        AppMethodBeat.o(12053);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: cn.missfresh.module.search.ui.SearchResultFragment */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void a(View view) {
        AppMethodBeat.i(12054, false);
        view.findViewById(R.id.search_back_view).setOnClickListener(this.U);
        view.findViewById(R.id.cart_view).setOnClickListener(this.U);
        this.e.setOnClickListener(this.U);
        this.h.setOnClickListener(this.U);
        this.d.setOnClickListener(this.U);
        this.f.setOnClickListener(this.U);
        this.i.setOnClickListener(this.U);
        this.g.setOnClickListener(this.U);
        this.j.setOnClickListener(this.U);
        this.c.setOnClickListener(this.U);
        this.s.setOnRefreshListener(this.Z);
        this.T.setOnClickListener(this.U);
        IShoppingCartService2 iShoppingCartService2 = (IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation();
        if (iShoppingCartService2 != null) {
            iShoppingCartService2.b().observe(this, new AnonymousClass1());
        }
        j.a(this.J, new AnonymousClass13());
        g.a().d(0);
        AppMethodBeat.o(12054);
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$1  reason: invalid class name */
    class AnonymousClass1 implements Observer<Integer> {
        AnonymousClass1() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(11954, false);
            a((Integer) obj);
            AppMethodBeat.o(11954);
        }

        public void a(Integer num) {
            AppMethodBeat.i(11953, false);
            if (num != null) {
                SearchResultFragment.a(SearchResultFragment.this, num.intValue());
                SearchResultFragment.this.x.setIvCount(num.intValue());
            }
            AppMethodBeat.o(11953);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$13  reason: invalid class name */
    class AnonymousClass13 extends k {
        AnonymousClass13() {
        }

        @Override // cn.missfresh.module.base.helper.g
        public void a(View view, int i) {
            AppMethodBeat.i(12012, false);
            if (SearchResultFragment.this.L == null || i == SearchResultFragment.this.L.a()) {
                AppMethodBeat.o(12012);
                return;
            }
            BasinessForm a = SearchResultFragment.this.L.a(i);
            if (a == null) {
                AppMethodBeat.o(12012);
                return;
            }
            SearchResultFragment.this.M = i;
            g.a().d(a.getSellerType());
            if (a.getSellerType() == 0) {
                SearchResultFragment.this.P.setVisibility(0);
            } else {
                SearchResultFragment.this.P.setVisibility(8);
            }
            SearchResultFragment.this.e();
            SearchResultFragment.d(SearchResultFragment.this);
            SearchResultFragment.e(SearchResultFragment.this);
            StatisticsManager.p("click_bFormtype_tab", ai.e, "bFormtype_tab", "pos", Integer.valueOf(i));
            AppMethodBeat.o(12012);
        }
    }

    public void e() {
        AppMethodBeat.i(12055, false);
        MultiFormatAdapter multiFormatAdapter = this.L;
        if (multiFormatAdapter == null || this.J == null) {
            AppMethodBeat.o(12055);
            return;
        }
        multiFormatAdapter.b(this.M);
        ((LinearLayoutManager) this.J.getLayoutManager()).scrollToPositionWithOffset(this.M, 200);
        AppMethodBeat.o(12055);
    }

    public void a(List<BasinessForm> list, int i, int i2) {
        AppMethodBeat.i(12056, false);
        if (cn.missfresh.utils.b.a(list)) {
            this.O.setVisibility(8);
        } else {
            this.O.setVisibility(0);
            this.L.a(list, i, i2);
        }
        AppMethodBeat.o(12056);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: cn.missfresh.module.search.ui.SearchResultFragment */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void b(View view) {
        AppMethodBeat.i(12057, false);
        this.l = view.findViewById(R.id.cart_view);
        this.k = view.findViewById(R.id.top_layout);
        this.o = (AppBarLayout) view.findViewById(R.id.appbar_layout);
        this.m = view.findViewById(R.id.fast_layout);
        this.n = view.findViewById(R.id.fast_dot_view);
        this.a = (RecyclerView) view.findViewById(R.id.result_recycler);
        this.h = view.findViewById(R.id.composite_layout);
        this.i = view.findViewById(R.id.price_layout);
        this.j = view.findViewById(R.id.sale_layout);
        this.e = (TextView) view.findViewById(R.id.composite_tv);
        this.f = (TextView) view.findViewById(R.id.price_tv);
        this.g = (TextView) view.findViewById(R.id.sale_tv);
        this.d = (TextView) view.findViewById(R.id.fast_tv);
        this.c = (TextView) view.findViewById(R.id.search_view);
        this.q = (BadgeView) view.findViewById(R.id.cart_count_view);
        this.s = (MultiStateLayout) view.findViewById(R.id.multi_state_layout);
        this.y = (ImageView) view.findViewById(R.id.iv_red_packet);
        this.y.setOnClickListener(this.U);
        this.z = new b(getActivity(), this.y);
        this.x = (MiniShoppingCartView) view.findViewById(R.id.msc_view);
        this.x.a((LifecycleOwner) this, "search_result");
        this.x.setIvRedPacket(this.y);
        this.C = view.findViewById(R.id.v_space);
        this.x.setAnimAntView(this.C);
        this.T = (TextView) view.findViewById(R.id.tv_search);
        this.b = new SearchResultProductAdapter();
        this.b.a(this.W, this.X, this.Y);
        this.r = (GridLayoutManager) this.a.getLayoutManager();
        cn.missfresh.module.base.widget.b.a(this.r, this.b);
        this.a.addItemDecoration(new SearchResultDecoration(f.c(view.getContext(), 3), f.c(view.getContext(), 7)));
        this.a.setAdapter(this.b);
        this.a.addOnScrollListener(this.aa);
        this.G = (RecyclerView) view.findViewById(R.id.rv_quick_filter);
        this.w = new CenterLayoutManager(view.getContext(), 0, false);
        this.G.setLayoutManager(this.w);
        this.F = new QuickFilterAdapter();
        this.G.setAdapter(this.F);
        this.F.a(new AnonymousClass14());
        if (this.a.getItemAnimator() != null && (this.a.getItemAnimator() instanceof SimpleItemAnimator)) {
            ((SimpleItemAnimator) this.a.getItemAnimator()).setSupportsChangeAnimations(false);
        }
        this.J = (RecyclerView) view.findViewById(R.id.rv_multi_format);
        this.K = new CenterLayoutManager(view.getContext(), 0, false);
        this.J.setLayoutManager(this.K);
        this.L = new MultiFormatAdapter(true);
        this.J.setAdapter(this.L);
        this.O = (LinearLayout) view.findViewById(R.id.layout_multi_format);
        this.P = (LinearLayout) view.findViewById(R.id.title_layout);
        this.Q = (TextView) view.findViewById(R.id.tv_muti_format_title);
        this.R = (TextView) view.findViewById(R.id.tv_muti_format_subtitle);
        AppMethodBeat.o(12057);
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$14  reason: invalid class name */
    class AnonymousClass14 implements QuickFilterAdapter.a {
        AnonymousClass14() {
        }

        public void a(int i) {
            AppMethodBeat.i(12018, false);
            if (SearchResultFragment.this.F == null) {
                AppMethodBeat.o(12018);
                return;
            }
            SearchResultFragment.this.F.a(i, SearchResultFragment.this.t.e);
            SearchResultFragment.this.G.smoothScrollToPosition(i);
            SearchResultFragment searchResultFragment = SearchResultFragment.this;
            searchResultFragment.E = searchResultFragment.F.a();
            SearchResultFragment searchResultFragment2 = SearchResultFragment.this;
            searchResultFragment2.H = searchResultFragment2.F.b();
            SearchResultFragment searchResultFragment3 = SearchResultFragment.this;
            searchResultFragment3.I = searchResultFragment3.F.c();
            SearchResultFragment.this.a.scrollToPosition(0);
            SearchResultFragment.d(SearchResultFragment.this);
            AppMethodBeat.o(12018);
        }
    }

    /* access modifiers changed from: protected */
    public void k() {
        AppMethodBeat.i(12058, false);
        this.l.post(new 15(this));
        a((String) this.u.a().getValue());
        l();
        AppMethodBeat.o(12058);
    }

    private void b(boolean z) {
        AppMethodBeat.i(12059, false);
        ImageView imageView = this.y;
        if (imageView == null || imageView.getVisibility() != 0 || ((z && !this.B) || (!z && this.B))) {
            AppMethodBeat.o(12059);
            return;
        }
        if (z) {
            this.z.c();
            this.B = false;
        } else {
            this.z.b();
            this.B = true;
        }
        AppMethodBeat.o(12059);
    }

    /* access modifiers changed from: protected */
    public int f() {
        return R.layout.search_frament_search_result;
    }

    /* access modifiers changed from: protected */
    public void b_(boolean z) {
        AppMethodBeat.i(12060, false);
        SearchResultFragment.super.b_(z);
        if (z) {
            r.a((Activity) getActivity());
            if (this.a.getChildCount() > 0) {
                RecyclerView recyclerView = this.a;
                this.b.notifyItemRangeChanged(recyclerView.getChildAdapterPosition(recyclerView.getChildAt(0)), this.a.getChildCount());
            }
            cn.missfresh.utils.a.d.c("onFragmentVisibleChanged", "========" + z);
        }
        AppMethodBeat.o(12060);
    }

    private void l() {
        AppMethodBeat.i(12061, false);
        ArrayList arrayList = new ArrayList();
        arrayList.add("SEARCH_BEAR");
        this.A.a(g.a().l(), arrayList, "H_SEARCH");
        AppMethodBeat.o(12061);
    }

    private void m() {
        AppMethodBeat.i(12062, false);
        StatisticsManager.p("click_social", ai.e, "bear");
        if (cn.missfresh.module.base.utils.j.a() && this.A.d().getValue() != null && !cn.missfresh.utils.b.a(this.A.d().getValue())) {
            cn.missfresh.module.base.common.resourcemanager.a.a().a(this.A.d().getValue().get(0), null);
        }
        AppMethodBeat.o(12062);
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$16  reason: invalid class name */
    class AnonymousClass16 implements d {
        AnonymousClass16() {
        }

        @Override // cn.missfresh.module.base.common.interfaces.d
        public View g() {
            AppMethodBeat.i(12027, false);
            View view = SearchResultFragment.this.l;
            AppMethodBeat.o(12027);
            return view;
        }

        @Override // cn.missfresh.module.base.common.interfaces.d
        public BadgeView i() {
            AppMethodBeat.i(12028, false);
            BadgeView badgeView = SearchResultFragment.this.q;
            AppMethodBeat.o(12028);
            return badgeView;
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$17  reason: invalid class name */
    class AnonymousClass17 implements a {
        AnonymousClass17() {
        }

        @Override // cn.missfresh.module.base.common.listener.a
        public void a(ImageView imageView) {
            AppMethodBeat.i(12032, false);
            SearchResultFragment.this.a(imageView);
            AppMethodBeat.o(12032);
        }
    }

    public void a(ImageView imageView) {
        AppMethodBeat.i(12063, false);
        MiniShoppingCartView miniShoppingCartView = this.x;
        if (miniShoppingCartView == null || miniShoppingCartView.getVisibility() != 0) {
            this.p.a(getActivity(), imageView);
        } else {
            this.x.a(getActivity(), imageView);
        }
        AppMethodBeat.o(12063);
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$18  reason: invalid class name */
    class AnonymousClass18 implements cn.missfresh.module.search.interfaces.a {
        AnonymousClass18() {
        }

        public void a(int i) {
            int i2;
            AppMethodBeat.i(12036, false);
            if (i == -1) {
                AppMethodBeat.o(12036);
                return;
            }
            if (i < SearchResultFragment.this.b.a().size() && (SearchResultFragment.this.b.a().get(i) instanceof SearchResultBean)) {
                SearchResultBean searchResultBean = (SearchResultBean) SearchResultFragment.this.b.a().get(i);
                if (i > SearchResultFragment.this.t.d) {
                    cn.missfresh.module.search.a.c.b(searchResultBean.getSku(), (i - SearchResultFragment.this.t.d) - 1, SearchResultFragment.this.t.e, SearchResultFragment.this.t.a, searchResultBean.getName(), searchResultBean.getSkuCategory(), q.a(searchResultBean.getThemeTag(), searchResultBean.getProductTag()));
                    HashMap hashMap = new HashMap();
                    hashMap.put(ai.e, "recommend");
                    hashMap.put("sku", searchResultBean.getSku());
                    hashMap.put("recommend_request_id", SearchResultFragment.this.t.a);
                    hashMap.put("sku_productList_pos", Integer.valueOf((i - SearchResultFragment.this.t.d) - 1));
                    hashMap.put("requestId", StatisticsManager.a(new String[]{"/as/item/search/getResult"}));
                    if (g.a().l() != 0) {
                        hashMap.put("businessForm_type", Integer.valueOf(g.a().l()));
                    }
                    hashMap.put("page_businessForm_type", Integer.valueOf(g.a().l()));
                    StatisticsManager.onNewEventToMRYX2("add_cart", "search_result", hashMap);
                } else {
                    cn.missfresh.module.search.a.c.b(searchResultBean.getSku(), i, SearchResultFragment.this.t.e, SearchResultFragment.this.t.b, searchResultBean.getName(), searchResultBean.getSkuCategory(), q.a(searchResultBean.getThemeTag(), searchResultBean.getProductTag()), SearchResultFragment.this.H, SearchResultFragment.this.I);
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put(ai.e, "search");
                    hashMap2.put("sku", searchResultBean.getSku());
                    hashMap2.put("search_request_id", SearchResultFragment.this.t.b);
                    hashMap2.put("sku_searchList_pos", Integer.valueOf(i));
                    hashMap2.put("requestId", StatisticsManager.a(new String[]{"/as/item/search/getResult"}));
                    if (g.a().l() != 0) {
                        hashMap2.put("businessForm_type", Integer.valueOf(g.a().l()));
                    }
                    hashMap2.put("page_businessForm_type", Integer.valueOf(g.a().l()));
                    StatisticsManager.onNewEventToMRYX2("add_cart", "search_result", hashMap2);
                    i2 = 12036;
                    AppMethodBeat.o(i2);
                }
            }
            i2 = 12036;
            AppMethodBeat.o(i2);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$19  reason: invalid class name */
    class AnonymousClass19 extends c {
        AnonymousClass19() {
        }

        public void a(int i, int i2, int i3, int i4, int i5) {
            AppMethodBeat.i(12042, false);
            if (i2 != -1 && i == 4 && (SearchResultFragment.this.b.a().get(i2) instanceof SearchSloganBean)) {
                SearchResultFragment.a(SearchResultFragment.this, ((SearchSloganBean) SearchResultFragment.this.b.a().get(i2)).getShowList(i4, i5), SearchResultFragment.this.t.c);
            }
            AppMethodBeat.o(12042);
        }

        @Override // cn.missfresh.module.search.interfaces.c
        public void a(int i, int i2, int i3, int i4) {
            AppMethodBeat.i(12044, false);
            if (i2 == -1) {
                AppMethodBeat.o(12044);
                return;
            }
            if (i == 1) {
                SearchResultFragment.a(SearchResultFragment.this, i2, i3);
            } else if (i != 4) {
                if (i != 5) {
                    if (i != 6) {
                        if (i == 7 && (SearchResultFragment.this.b.a().get(i2) instanceof SearchSloganBean)) {
                            String keyWord = ((SearchSloganBean) SearchResultFragment.this.b.a().get(i2)).getKeyWord(i4);
                            StatisticsManager.p("click_similar", ai.e, "similar", "keyword", keyWord);
                            SearchResultFragment.this.a(keyWord);
                            e.s(keyWord);
                        }
                    } else if (SearchResultFragment.this.b.a().get(i2) instanceof SearchTagsBean) {
                        String str = (String) ((SearchTagsBean) SearchResultFragment.this.b.a().get(i2)).textList.get(i4);
                        StatisticsManager.p("click_suppose", ai.e, "suppose", "keyword", str);
                        SearchResultFragment.this.a(str);
                        e.s(str);
                    }
                } else if (SearchResultFragment.this.b.a().get(i2) instanceof SearchHotListBean) {
                    SearchHotListBean searchHotListBean = (SearchHotListBean) SearchResultFragment.this.b.a().get(i2);
                    if (i3 != 1) {
                        BannerEntity bannerEntity = new BannerEntity();
                        bannerEntity.setLink(searchHotListBean.getH5Url());
                        bannerEntity.setType(SkipBean.Type.URL);
                        bannerEntity.setPromotion_id(searchHotListBean.recommendRequestId);
                        bannerEntity.setName(searchHotListBean.title);
                        cn.missfresh.module.base.utils.j.a(bannerEntity);
                        if (i3 != 2) {
                            cn.missfresh.module.search.a.c.a(i2, searchHotListBean.firstCategoryCode, searchHotListBean.recommendRequestId, searchHotListBean.getRequestIdByIndex(0), SearchResultFragment.this.t.e, "");
                        } else if (i4 > -1 && i4 < searchHotListBean.getProductList().size()) {
                            cn.missfresh.module.search.a.c.a(i2, searchHotListBean.firstCategoryCode, searchHotListBean.recommendRequestId, searchHotListBean.getRequestIdByIndex(i4), SearchResultFragment.this.t.e, searchHotListBean.getSkuByIndex(i4));
                        }
                    } else if (i4 > -1 && i4 < searchHotListBean.getProductList().size()) {
                        cn.missfresh.module.search.a.c.a(i2, searchHotListBean.firstCategoryCode, searchHotListBean.recommendRequestId, SearchResultFragment.this.t.b, SearchResultFragment.this.t.e, ((SearchHotProductBean) searchHotListBean.getProductList().get(i4)).getSku(), i4);
                    }
                }
            } else if (SearchResultFragment.this.b.a().get(i2) instanceof SearchSloganBean) {
                String keyWord2 = ((SearchSloganBean) SearchResultFragment.this.b.a().get(i2)).getKeyWord(i4);
                cn.missfresh.module.search.a.c.c(SearchResultFragment.this.t.e, keyWord2, i4, SearchResultFragment.this.t.c);
                SearchResultFragment.this.a(keyWord2);
                e.s(keyWord2);
            }
            AppMethodBeat.o(12044);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$2  reason: invalid class name */
    class AnonymousClass2 implements MultiStateLayout.d {
        AnonymousClass2() {
        }

        @Override // cn.missfresh.module.base.widget.MultiStateLayout.d
        public void f_() {
            AppMethodBeat.i(11957, false);
            SearchResultFragment.d(SearchResultFragment.this);
            AppMethodBeat.o(11957);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$3  reason: invalid class name */
    class AnonymousClass3 extends RecyclerView.OnScrollListener {
        AnonymousClass3() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            boolean z = false;
            AppMethodBeat.i(11959, false);
            super.onScrollStateChanged(recyclerView, i);
            cn.missfresh.utils.a.d.c("SearchResult", "newState = " + i);
            SearchResultFragment searchResultFragment = SearchResultFragment.this;
            if (i == 0) {
                z = true;
            }
            SearchResultFragment.b(searchResultFragment, z);
            if (i == 0) {
                SearchResultFragment.u(SearchResultFragment.this);
            }
            AppMethodBeat.o(11959);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            AppMethodBeat.i(11960, false);
            super.onScrolled(recyclerView, i, i2);
            if (!cn.missfresh.utils.b.a(SearchResultFragment.this.S)) {
                int findFirstVisibleItemPosition = SearchResultFragment.this.r.findFirstVisibleItemPosition();
                int size = SearchResultFragment.this.S.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    } else if (findFirstVisibleItemPosition >= ((SearchSellerTypePos) SearchResultFragment.this.S.get(size)).position) {
                        SearchResultFragment.this.Q.setText(((SearchSellerTypePos) SearchResultFragment.this.S.get(size)).basinessForm.getTitle());
                        SearchResultFragment.this.R.setText(((SearchSellerTypePos) SearchResultFragment.this.S.get(size)).basinessForm.getSubTitle());
                        break;
                    } else {
                        size--;
                    }
                }
            }
            if (!SearchResultFragment.this.t.i && recyclerView.getScrollState() == 0) {
                SearchResultFragment.this.t.i = true;
                SearchResultFragment.u(SearchResultFragment.this);
                aj.a().a("PERFORMANCE_SEARCH", "0.0.1", "PERFORMANCE_SEARCH");
            }
            AppMethodBeat.o(11960);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$4  reason: invalid class name */
    class AnonymousClass4 implements Observer<Integer> {
        AnonymousClass4() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(11962, false);
            a((Integer) obj);
            AppMethodBeat.o(11962);
        }

        public void a(Integer num) {
            AppMethodBeat.i(11961, false);
            int intValue = num.intValue();
            if (intValue == 1) {
                SearchResultFragment.this.k.setVisibility(8);
                SearchResultFragment.this.s.b();
            } else if (intValue == 2) {
                SearchResultFragment.this.s.c();
            } else if (intValue != 3) {
                SearchResultFragment.this.s.c();
            } else {
                SearchResultFragment.this.s.d();
            }
            AppMethodBeat.o(11961);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$6  reason: invalid class name */
    class AnonymousClass6 implements Observer<String> {
        AnonymousClass6() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(11966, false);
            a((String) obj);
            AppMethodBeat.o(11966);
        }

        public void a(String str) {
            AppMethodBeat.i(11965, false);
            SearchResultFragment.c(SearchResultFragment.this, str);
            AppMethodBeat.o(11965);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$7  reason: invalid class name */
    class AnonymousClass7 implements Observer<List<SearchBaseBean>> {
        AnonymousClass7() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(11970, false);
            a((List) obj);
            AppMethodBeat.o(11970);
        }

        public void a(List<SearchBaseBean> list) {
            AppMethodBeat.i(11969, false);
            Iterator<SearchBaseBean> it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                SearchBusinessForm searchBusinessForm = (SearchBaseBean) it2.next();
                if (searchBusinessForm instanceof SearchBusinessForm) {
                    SearchBusinessForm searchBusinessForm2 = searchBusinessForm;
                    SearchResultFragment.this.Q.setText(searchBusinessForm2.title);
                    SearchResultFragment.this.R.setText(searchBusinessForm2.subTitle);
                    break;
                }
            }
            SearchResultFragment.this.b.a(list);
            AppMethodBeat.o(11969);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$8  reason: invalid class name */
    class AnonymousClass8 implements Observer<List<SearchSellerTypePos>> {
        AnonymousClass8() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(11978, false);
            a((List) obj);
            AppMethodBeat.o(11978);
        }

        public void a(List<SearchSellerTypePos> list) {
            AppMethodBeat.i(11976, false);
            SearchResultFragment.this.S = list;
            AppMethodBeat.o(11976);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$9  reason: invalid class name */
    class AnonymousClass9 implements Observer<List<QuickFilterBean>> {
        AnonymousClass9() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(11988, false);
            a((List) obj);
            AppMethodBeat.o(11988);
        }

        public void a(List<QuickFilterBean> list) {
            AppMethodBeat.i(11986, false);
            if (list == null) {
                AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) SearchResultFragment.this.o.getChildAt(1).getLayoutParams();
                layoutParams.setScrollFlags(0);
                SearchResultFragment.this.k.setLayoutParams(layoutParams);
                AppBarLayout.LayoutParams layoutParams2 = (AppBarLayout.LayoutParams) SearchResultFragment.this.o.getChildAt(2).getLayoutParams();
                layoutParams2.setScrollFlags(0);
                SearchResultFragment.this.G.setLayoutParams(layoutParams2);
                SearchResultFragment.this.G.setVisibility(8);
                SearchResultFragment.this.F.a((List) null);
                SearchResultFragment searchResultFragment = SearchResultFragment.this;
                searchResultFragment.E = searchResultFragment.F.a();
                SearchResultFragment searchResultFragment2 = SearchResultFragment.this;
                searchResultFragment2.H = searchResultFragment2.F.b();
                SearchResultFragment searchResultFragment3 = SearchResultFragment.this;
                searchResultFragment3.I = searchResultFragment3.F.c();
                AppMethodBeat.o(11986);
                return;
            }
            SearchResultFragment.this.F.a(list);
            AppBarLayout.LayoutParams layoutParams3 = (AppBarLayout.LayoutParams) SearchResultFragment.this.o.getChildAt(1).getLayoutParams();
            layoutParams3.setScrollFlags(5);
            SearchResultFragment.this.k.setLayoutParams(layoutParams3);
            AppBarLayout.LayoutParams layoutParams4 = (AppBarLayout.LayoutParams) SearchResultFragment.this.o.getChildAt(2).getLayoutParams();
            layoutParams4.setScrollFlags(7);
            SearchResultFragment.this.G.setLayoutParams(layoutParams4);
            SearchResultFragment.this.G.setVisibility(0);
            Iterator<QuickFilterBean> it2 = list.iterator();
            String str = "";
            while (it2.hasNext()) {
                str = str + Constants.ACCEPT_TIME_SEPARATOR_SP + it2.next().name;
            }
            cn.missfresh.module.search.a.c.b(SearchResultFragment.this.t.e, str.substring(1));
            AppMethodBeat.o(11986);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$10  reason: invalid class name */
    class AnonymousClass10 implements Observer<List<BasinessForm>> {
        AnonymousClass10() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(11992, false);
            a((List) obj);
            AppMethodBeat.o(11992);
        }

        public void a(List<BasinessForm> list) {
            AppMethodBeat.i(11990, false);
            SearchResultFragment.this.N = list;
            SearchResultFragment searchResultFragment = SearchResultFragment.this;
            searchResultFragment.a(list, searchResultFragment.M, 0);
            if (!cn.missfresh.utils.b.a(list)) {
                StatisticsManager.p("explosure_bFormtype_tab", ai.e, "bFormtype_tab");
            }
            if (list == null || list.size() <= 0 || list.get(0) == null || SearchResultFragment.this.M != 0 || list.get(0).getSellerType() != 0) {
                SearchResultFragment.this.P.setVisibility(8);
            } else {
                SearchResultFragment.this.P.setVisibility(0);
            }
            AppMethodBeat.o(11990);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$11  reason: invalid class name */
    class AnonymousClass11 implements Observer<Map<String, String>> {
        AnonymousClass11() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(11997, false);
            a((Map) obj);
            AppMethodBeat.o(11997);
        }

        public void a(Map<String, String> map) {
            AppMethodBeat.i(11996, false);
            if ("0".equals(map.get("filterType"))) {
                SearchResultFragment.this.k.setVisibility(8);
            } else {
                SearchResultFragment.this.k.setVisibility(0);
                String str = map.get("filterText");
                if (cn.missfresh.utils.b.a(str)) {
                    SearchResultFragment.this.m.setVisibility(8);
                } else {
                    SearchResultFragment.this.d.setText(str);
                    SearchResultFragment.this.n.setVisibility(e.aC() ? 8 : 0);
                    SearchResultFragment.this.m.setVisibility(0);
                }
                if (SearchResultFragment.this.t.j) {
                    cn.missfresh.module.search.a.c.b(cn.missfresh.utils.b.a(str) ? 2 : 1);
                }
            }
            if (SearchResultFragment.this.D == 1) {
                SearchResultFragment.this.m.setVisibility(8);
            }
            AppMethodBeat.o(11996);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$12  reason: invalid class name */
    class AnonymousClass12 implements Observer<List<SkipBean>> {
        AnonymousClass12() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(12009, false);
            a((List) obj);
            AppMethodBeat.o(12009);
        }

        public void a(List<SkipBean> list) {
            AppMethodBeat.i(12008, false);
            if (cn.missfresh.utils.b.a(list) || list.get(0) == null || TextUtils.isEmpty(list.get(0).image)) {
                SearchResultFragment.this.y.setVisibility(8);
            } else {
                StatisticsManager.p("exposure_social", ai.e, "bear");
                x.a(SearchResultFragment.this.y, list.get(0).image, (cn.missfresh.lib.image.b.a) new AnonymousClass1());
            }
            AppMethodBeat.o(12008);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.module.search.ui.SearchResultFragment$12$1  reason: invalid class name */
        public class AnonymousClass1 implements cn.missfresh.lib.image.b.a {
            AnonymousClass1() {
            }

            public boolean a(Exception exc, Object obj, boolean z) {
                AppMethodBeat.i(12000, false);
                SearchResultFragment.this.y.setVisibility(8);
                AppMethodBeat.o(12000);
                return false;
            }

            public boolean a(Object obj, Object obj2, boolean z) {
                AppMethodBeat.i(12001, false);
                SearchResultFragment.this.y.setVisibility(0);
                AppMethodBeat.o(12001);
                return false;
            }
        }
    }

    private void a(int i, int i2) {
        AppMethodBeat.i(12064, false);
        SearchResultBean searchResultBean = (SearchResultBean) this.b.a().get(i);
        if (i > this.t.d) {
            cn.missfresh.module.search.a.c.a(searchResultBean.getSku(), (i - this.t.d) - 1, this.t.e, this.t.a, searchResultBean.getName(), searchResultBean.getSkuCategory(), q.a(searchResultBean.getThemeTag(), searchResultBean.getProductTag()));
        } else {
            cn.missfresh.module.search.a.c.a(searchResultBean.getSku(), i, this.t.e, this.t.b, searchResultBean.getName(), searchResultBean.getSkuCategory(), q.a(searchResultBean.getThemeTag(), searchResultBean.getProductTag()), this.H, this.I);
        }
        if (i2 == 101) {
            com.alibaba.android.arouter.b.a.a().a("/order/shoppingcart_like").withString("sku", searchResultBean.getSku()).navigation();
        } else if (this.v.e()) {
            ((IProductDetailService) com.alibaba.android.arouter.b.a.a().a("/product/product_detail_service").navigation()).routerProductDetail(searchResultBean.getSku(), searchResultBean.getImage(), searchResultBean.getAlgoId(), 0, "");
        }
        AppMethodBeat.o(12064);
    }

    private void r() {
        int i = 0;
        AppMethodBeat.i(12065, false);
        this.s.d();
        SearchResultModule searchResultModule = this.t;
        int i2 = this.D;
        if (i2 != 0) {
            i = i2;
        } else if (this.d.isSelected()) {
            i = 1;
        }
        searchResultModule.a(i, this.E);
        AppMethodBeat.o(12065);
    }

    private void c(boolean z) {
        AppMethodBeat.i(12066, false);
        boolean z2 = true;
        this.e.setSelected(this.t.h == 0);
        this.g.setSelected(this.t.h == 1);
        TextView textView = this.f;
        if (!(this.t.h == 2 || this.t.h == 3)) {
            z2 = false;
        }
        textView.setSelected(z2);
        TextView textView2 = this.f;
        textView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, textView2.isSelected() ? this.t.h == 2 ? R.drawable.search_result_tip_price_up : R.drawable.search_result_tip_price_down : R.drawable.search_result_tip_price_def, 0);
        this.d.setSelected(z);
        this.r.scrollToPosition(0);
        r();
        AppMethodBeat.o(12066);
    }

    private void b(int i) {
        AppMethodBeat.i(12067, false);
        this.q.b();
        AppMethodBeat.o(12067);
    }

    private void s() {
        int i;
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        int i2;
        AppMethodBeat.i(12070, false);
        GridLayoutManager gridLayoutManager = this.r;
        if (gridLayoutManager != null) {
            int findFirstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
            int findLastVisibleItemPosition = this.r.findLastVisibleItemPosition();
            if (findFirstVisibleItemPosition <= -1 || findLastVisibleItemPosition <= -1 || findFirstVisibleItemPosition > findLastVisibleItemPosition) {
                AppMethodBeat.o(12070);
                return;
            }
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            ArrayList arrayList7 = new ArrayList();
            ArrayList arrayList8 = new ArrayList();
            ArrayList arrayList9 = new ArrayList();
            ArrayList arrayList10 = new ArrayList();
            ArrayList arrayList11 = new ArrayList();
            ArrayList arrayList12 = new ArrayList();
            ArrayList arrayList13 = new ArrayList();
            while (findFirstVisibleItemPosition <= findLastVisibleItemPosition && findFirstVisibleItemPosition < this.b.a().size()) {
                if (((SearchBaseBean) this.b.a().get(findFirstVisibleItemPosition)).getMultiType() == 1) {
                    SearchResultBean searchResultBean = (SearchResultBean) this.b.a().get(findFirstVisibleItemPosition);
                    if (findFirstVisibleItemPosition < this.t.d) {
                        arrayList4.add(searchResultBean.getSku());
                        arrayList10.add(Integer.valueOf(findFirstVisibleItemPosition));
                        arrayList6.add(searchResultBean.getName());
                        arrayList8.add(searchResultBean.getSkuCategory());
                        arrayList12.add(q.a(searchResultBean.getThemeTag(), searchResultBean.getProductTag()));
                    } else {
                        arrayList5.add(searchResultBean.getSku());
                        arrayList11.add(Integer.valueOf((findFirstVisibleItemPosition - this.t.d) - 1));
                        arrayList7.add(searchResultBean.getName());
                        arrayList9.add(searchResultBean.getSkuCategory());
                        arrayList13.add(q.a(searchResultBean.getThemeTag(), searchResultBean.getProductTag()));
                    }
                } else if (((SearchBaseBean) this.b.a().get(findFirstVisibleItemPosition)).getMultiType() == 4) {
                    SearchSloganHolder findViewHolderForAdapterPosition = this.a.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);
                    if (findViewHolderForAdapterPosition instanceof SearchSloganHolder) {
                        a(findViewHolderForAdapterPosition.a(), this.t.c);
                    }
                } else if (((SearchBaseBean) this.b.a().get(findFirstVisibleItemPosition)).getMultiType() == 6) {
                    SearchTagHolder findViewHolderForAdapterPosition2 = this.a.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);
                    if (findViewHolderForAdapterPosition2 instanceof SearchTagHolder) {
                        StatisticsManager.p("exposure_suppose", ai.e, "suppose", "keyword", findViewHolderForAdapterPosition2.a());
                    }
                } else if (((SearchBaseBean) this.b.a().get(findFirstVisibleItemPosition)).getMultiType() == 7) {
                    SearchSloganTagHolder findViewHolderForAdapterPosition3 = this.a.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);
                    if (findViewHolderForAdapterPosition3 instanceof SearchSloganTagHolder) {
                        StatisticsManager.p("exposure_similar", ai.e, "similar", "keyword", findViewHolderForAdapterPosition3.a());
                    }
                } else if (((SearchBaseBean) this.b.a().get(findFirstVisibleItemPosition)).getMultiType() == 5) {
                    int i3 = findFirstVisibleItemPosition > this.t.d ? (findFirstVisibleItemPosition - this.t.d) - 1 : findFirstVisibleItemPosition;
                    SearchHotListBean searchHotListBean = (SearchHotListBean) this.b.a().get(findFirstVisibleItemPosition);
                    i2 = findLastVisibleItemPosition;
                    cn.missfresh.module.search.a.c.a(i3, searchHotListBean.firstCategoryCode, searchHotListBean.recommendRequestId, searchHotListBean.getRequestIdList(), this.t.e, searchHotListBean.getSkuList(), searchHotListBean.getPosList());
                    findFirstVisibleItemPosition++;
                    findLastVisibleItemPosition = i2;
                }
                i2 = findLastVisibleItemPosition;
                findFirstVisibleItemPosition++;
                findLastVisibleItemPosition = i2;
            }
            if (arrayList4.size() != 0) {
                arrayList = arrayList13;
                arrayList3 = arrayList11;
                arrayList2 = arrayList9;
                cn.missfresh.module.search.a.c.a(arrayList4, arrayList10, this.t.e, arrayList8, arrayList6, arrayList12, this.t.b, this.H, this.I);
            } else {
                arrayList = arrayList13;
                arrayList3 = arrayList11;
                arrayList2 = arrayList9;
            }
            if (arrayList5.size() != 0) {
                cn.missfresh.module.search.a.c.a(arrayList5, arrayList3, this.t.e, arrayList2, arrayList7, arrayList, this.t.a);
            }
            i = 12070;
        } else {
            i = 12070;
        }
        AppMethodBeat.o(i);
    }

    private void a(List<String> list, String str) {
        AppMethodBeat.i(12071, false);
        if (cn.missfresh.utils.b.a(list)) {
            AppMethodBeat.o(12071);
            return;
        }
        cn.missfresh.module.search.a.c.a(this.t.e, list, str);
        AppMethodBeat.o(12071);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: cn.missfresh.module.search.ui.SearchResultFragment */
    /* JADX WARN: Multi-variable type inference failed */
    public void onResume() {
        AppMethodBeat.i(12072, false);
        SearchResultFragment.super.onResume();
        MiniShoppingCartView miniShoppingCartView = this.x;
        if (miniShoppingCartView != null) {
            miniShoppingCartView.a();
        }
        AppMethodBeat.onResume(this);
        AppMethodBeat.o(12072);
    }
}
