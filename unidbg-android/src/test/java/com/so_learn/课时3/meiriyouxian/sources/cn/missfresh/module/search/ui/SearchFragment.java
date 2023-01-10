package cn.missfresh.module.search.ui;

import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.common.helper.GoodsAnimHelper;
import cn.missfresh.module.base.common.interfaces.s;
import cn.missfresh.module.base.common.listener.a;
import cn.missfresh.module.base.common.providers.IProductDetailService;
import cn.missfresh.module.base.common.providers.IShoppingCartService2;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.shoppingcart_settle.view.MiniShoppingCartView;
import cn.missfresh.module.base.support.view.MaxHeightLinearLayout;
import cn.missfresh.module.base.utils.ap;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.base.widget.BadgeView;
import cn.missfresh.module.mvvm.BaseMVVMFragment;
import cn.missfresh.module.search.R;
import cn.missfresh.module.search.a.c;
import cn.missfresh.module.search.adapter.AssociatedAdapter;
import cn.missfresh.module.search.adapter.SearchSimpleHotSaleAdapter;
import cn.missfresh.module.search.adapter.bean.AssociatedBean;
import cn.missfresh.module.search.adapter.bean.SearchHotBean;
import cn.missfresh.module.search.viewmodule.SearchChangeModule;
import cn.missfresh.module.search.viewmodule.SearchModule;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.recyclerview.MFPagedScrollListener;
import com.google.android.flexbox.FlexboxLayout;
import io.reactivex.c.g;
import io.reactivex.disposables.b;
import io.reactivex.q;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchFragment extends BaseMVVMFragment implements a {
    private BadgeView A;
    private cn.missfresh.module.search.c.a B;
    private GoodsAnimHelper C;
    private SearchModule D;
    private SearchChangeModule E;
    private HorizontalScrollView F;
    private MiniShoppingCartView G;
    private View H;
    private Observer<List<Object>> I = new AnonymousClass13();
    private Observer<List<SearchHotBean>> J = new AnonymousClass14();
    private Observer<List<String>> K = new AnonymousClass15();
    private Observer<SearchHotBean.SearchHotListBean> L = new AnonymousClass17();
    private Observer<Boolean> M = new AnonymousClass2();
    private Observer<Boolean> N = new AnonymousClass3();
    private View.OnClickListener O = new 12(this);
    private s P = new AnonymousClass4();
    private TextWatcher Q = new 5(this);
    private TextView.OnEditorActionListener R = new 6(this);
    private View.OnClickListener S = new 16(this);
    private MFPagedScrollListener T = new AnonymousClass7();
    private FlexboxLayout a;
    private FlexboxLayout b;
    private MaxHeightLinearLayout c;
    private MaxHeightLinearLayout d;
    private EditText e;
    private View f;
    private ImageView g;
    private ImageView h;
    private ImageView i;
    private TextView j;
    private Group k;
    private TextView l;
    private TextView m;
    private View n;
    private View o;
    private RecyclerView p;
    private RecyclerView q;
    private RecyclerView r;
    private TextView s;
    private TextView t;
    private SearchSimpleHotSaleAdapter u;
    private SearchSimpleHotSaleAdapter v;
    private AssociatedAdapter w;
    private b x;
    private View y;
    private ImageView z;

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.search.ui.SearchFragment */
    /* JADX WARN: Multi-variable type inference failed */
    public void onHiddenChanged(boolean z) {
        SearchFragment.super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    public String q() {
        return "search";
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.search.ui.SearchFragment */
    /* JADX WARN: Multi-variable type inference failed */
    public void setUserVisibleHint(boolean z) {
        SearchFragment.super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    public SearchFragment() {
        AppMethodBeat.i(11895, false);
        AppMethodBeat.o(11895);
    }

    static /* synthetic */ TextView a(SearchFragment searchFragment, String str, ViewGroup viewGroup, boolean z, boolean z2) {
        AppMethodBeat.i(11941, false);
        TextView a = searchFragment.a(str, viewGroup, z, z2);
        AppMethodBeat.o(11941);
        return a;
    }

    static /* synthetic */ void a(SearchFragment searchFragment, int i) {
        AppMethodBeat.i(11933, false);
        searchFragment.a(i);
        AppMethodBeat.o(11933);
    }

    static /* synthetic */ void a(SearchFragment searchFragment, String str, String str2) {
        AppMethodBeat.i(11935, false);
        searchFragment.a(str, str2);
        AppMethodBeat.o(11935);
    }

    static /* synthetic */ void a(SearchFragment searchFragment, boolean z) {
        AppMethodBeat.i(11943, false);
        searchFragment.b(z);
        AppMethodBeat.o(11943);
    }

    static /* synthetic */ void b(SearchFragment searchFragment, boolean z) {
        AppMethodBeat.i(11944, false);
        searchFragment.c(z);
        AppMethodBeat.o(11944);
    }

    static /* synthetic */ void v(SearchFragment searchFragment) {
        AppMethodBeat.i(11950, false);
        searchFragment.e();
        AppMethodBeat.o(11950);
    }

    public static SearchFragment a() {
        AppMethodBeat.i(11897, false);
        SearchFragment searchFragment = new SearchFragment();
        AppMethodBeat.o(11897);
        return searchFragment;
    }

    public void a(String str) {
        AppMethodBeat.i(11899, false);
        EditText editText = this.e;
        if (editText == null) {
            AppMethodBeat.o(11899);
            return;
        }
        editText.setText(str);
        EditText editText2 = this.e;
        editText2.setSelection(editText2.getText().length());
        this.D.f();
        this.e.post(new 1(this));
        AppMethodBeat.o(11899);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.search.ui.SearchFragment */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void g() {
        AppMethodBeat.i(11901, false);
        this.E = (SearchChangeModule) b(SearchChangeModule.class);
        this.D = (SearchModule) b(SearchModule.class);
        this.D.a().observe(this, this.J);
        this.D.b().observe(this, this.I);
        this.D.c().observe(this, this.K);
        this.D.e().observe(this, this.L);
        this.D.d().observe(this, this.M);
        this.D.g().observe(this, this.N);
        this.B = (cn.missfresh.module.search.c.a) com.alibaba.android.arouter.b.a.a().a("/search/interceptor").navigation();
        IShoppingCartService2 iShoppingCartService2 = (IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation();
        if (iShoppingCartService2 != null) {
            iShoppingCartService2.b().observe(this, new AnonymousClass8());
        }
        AppMethodBeat.o(11901);
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchFragment$8  reason: invalid class name */
    class AnonymousClass8 implements Observer<Integer> {
        AnonymousClass8() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(11855, false);
            a((Integer) obj);
            AppMethodBeat.o(11855);
        }

        public void a(Integer num) {
            AppMethodBeat.i(11853, false);
            if (num != null) {
                SearchFragment.a(SearchFragment.this, num.intValue());
            }
            AppMethodBeat.o(11853);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchFragment$9  reason: invalid class name */
    class AnonymousClass9 implements ap.a {
        AnonymousClass9() {
        }

        @Override // cn.missfresh.module.base.utils.ap.a
        public void a(View view) {
            AppMethodBeat.i(11859, true);
            String obj = SearchFragment.this.e.getText().toString();
            if (!TextUtils.isEmpty(obj)) {
                SearchFragment.a(SearchFragment.this, obj, "search");
            } else {
                String charSequence = SearchFragment.this.e.getHint().toString();
                if (TextUtils.equals(SearchFragment.this.getString(R.string.search_hint), charSequence)) {
                    cn.missfresh.ui.a.a.a(SearchFragment.this.getString(R.string.toast_search_empty));
                } else {
                    SearchFragment.a(SearchFragment.this, charSequence, "index");
                }
            }
            AppMethodBeat.o(11859);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: cn.missfresh.module.search.ui.SearchFragment */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void a(View view) {
        AppMethodBeat.i(11903, false);
        ap.a(new AnonymousClass9(), view.findViewById(R.id.tv_search));
        IShoppingCartService2 iShoppingCartService2 = (IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation();
        if (iShoppingCartService2 != null) {
            iShoppingCartService2.b().observe(this, new AnonymousClass10());
        }
        this.e.addTextChangedListener(this.Q);
        this.e.setOnEditorActionListener(this.R);
        this.h.setOnClickListener(this.S);
        this.i.setOnClickListener(this.S);
        this.g.setOnClickListener(this.S);
        this.s.setOnClickListener(this.S);
        this.t.setOnClickListener(this.S);
        this.z.setOnClickListener(this.S);
        AppMethodBeat.o(11903);
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchFragment$10  reason: invalid class name */
    class AnonymousClass10 implements Observer<Integer> {
        AnonymousClass10() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(11864, false);
            a((Integer) obj);
            AppMethodBeat.o(11864);
        }

        public void a(Integer num) {
            AppMethodBeat.i(11863, false);
            if (num != null) {
                SearchFragment.a(SearchFragment.this, num.intValue());
                SearchFragment.this.G.setIvCount(num.intValue());
            }
            AppMethodBeat.o(11863);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: cn.missfresh.module.search.ui.SearchFragment */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void b(View view) {
        AppMethodBeat.i(11906, false);
        this.h = (ImageView) view.findViewById(R.id.search_clear_view);
        this.e = (EditText) view.findViewById(R.id.search_view);
        this.a = (FlexboxLayout) view.findViewById(R.id.history_layout);
        this.i = (ImageView) view.findViewById(R.id.clear_his_view);
        this.b = (FlexboxLayout) view.findViewById(R.id.hot_layout);
        this.k = (Group) view.findViewById(R.id.his_group);
        this.p = (RecyclerView) view.findViewById(R.id.associational_recycler);
        this.j = (TextView) view.findViewById(R.id.hot_tip_tv);
        this.g = (ImageView) view.findViewById(R.id.search_back_view);
        this.q = (RecyclerView) view.findViewById(R.id.rcv_list_often_buy);
        this.r = (RecyclerView) view.findViewById(R.id.rcv_list_hot_sale);
        this.l = (TextView) view.findViewById(R.id.offten_buy_title);
        this.m = (TextView) view.findViewById(R.id.hot_sale_title);
        this.n = view.findViewById(R.id.ll_often_buy);
        this.o = view.findViewById(R.id.ll_hot_sale);
        this.s = (TextView) view.findViewById(R.id.offten_buy_sub_title);
        this.t = (TextView) view.findViewById(R.id.hot_sale_sub_title);
        this.c = (MaxHeightLinearLayout) view.findViewById(R.id.mll_hot);
        this.d = (MaxHeightLinearLayout) view.findViewById(R.id.mll_history);
        this.F = (HorizontalScrollView) view.findViewById(R.id.offten_buy_hot_sale);
        this.y = view.findViewById(R.id.rl_cart);
        this.A = (BadgeView) view.findViewById(R.id.cart_count_view);
        this.f = view.findViewById(R.id.tv_search);
        this.z = (ImageView) view.findViewById(R.id.cart_view);
        this.G = (MiniShoppingCartView) view.findViewById(R.id.msc_view);
        this.G.a((LifecycleOwner) this, "search");
        this.H = view.findViewById(R.id.v_space);
        this.G.setAnimAntView(this.H);
        this.w = new AssociatedAdapter(this.P);
        this.p.setAdapter(this.w);
        this.q.setLayoutManager(new LinearLayoutManager(getContext()));
        this.q.setHasFixedSize(true);
        this.u = new SearchSimpleHotSaleAdapter(this, (cn.missfresh.module.search.interfaces.b) null, false);
        this.q.setAdapter(this.u);
        this.q.addOnScrollListener(this.T);
        this.r.setLayoutManager(new LinearLayoutManager(getContext()));
        this.r.setHasFixedSize(true);
        this.v = new SearchSimpleHotSaleAdapter(this, (cn.missfresh.module.search.interfaces.b) null, true);
        this.r.setAdapter(this.v);
        this.r.addOnScrollListener(this.T);
        AppMethodBeat.o(11906);
    }

    /* access modifiers changed from: protected */
    public void k() {
        AppMethodBeat.i(11908, false);
        String str = (String) this.E.a().getValue();
        EditText editText = this.e;
        if (cn.missfresh.utils.b.a(str)) {
            str = "\u8bf7\u8f93\u5165\u5173\u952e\u8bcd";
        }
        editText.setHint(str);
        this.D.f();
        this.D.i();
        a("");
        AppMethodBeat.o(11908);
    }

    /* access modifiers changed from: protected */
    public int f() {
        return R.layout.search_fragment_input;
    }

    private void a(String str, String str2) {
        AppMethodBeat.i(11915, false);
        if ("*#super#*".equals(str)) {
            e.a(true);
        }
        this.D.a(str);
        c.a(str, str2);
        this.E.a(str);
        this.E.c();
        AppMethodBeat.o(11915);
    }

    private TextView a(String str, ViewGroup viewGroup, boolean z, boolean z2) {
        int i = 0;
        AppMethodBeat.i(11917, false);
        TextView textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_view_search_tip, viewGroup, false);
        textView.setText(str);
        textView.setTag(str);
        textView.setTag(R.id.search_tag_key_hot, Boolean.valueOf(z2));
        textView.setSelected(z);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, z ? R.drawable.ic_search_hot : 0, 0);
        if (z) {
            i = 2;
        }
        textView.setCompoundDrawablePadding(aw.b(i));
        textView.setTextColor(getResources().getColor(z ? R.color.color_ff4891 : R.color.color_474245));
        textView.setBackgroundResource(z ? R.drawable.shape_corners_15_white_ff1395 : R.drawable.shape_corners_15_f8f8f8);
        textView.setOnClickListener(this.O);
        AppMethodBeat.o(11917);
        return textView;
    }

    private void e() {
        AppMethodBeat.i(11918, false);
        if (TextUtils.isEmpty(this.e.getText().toString())) {
            this.w.b();
            this.p.setVisibility(8);
            AppMethodBeat.o(11918);
            return;
        }
        b bVar = this.x;
        if (bVar != null && !bVar.isDisposed()) {
            this.x.dispose();
        }
        this.x = q.a(this.e.getText().toString()).c(400, TimeUnit.MILLISECONDS).e(new AnonymousClass11());
        AppMethodBeat.o(11918);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.search.ui.SearchFragment$11  reason: invalid class name */
    public class AnonymousClass11 implements g<String> {
        AnonymousClass11() {
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(11868, false);
            a((String) obj);
            AppMethodBeat.o(11868);
        }

        public void a(String str) throws Exception {
            AppMethodBeat.i(11867, false);
            SearchFragment.this.D.b(SearchFragment.this.e.getText().toString());
            AppMethodBeat.o(11867);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchFragment$13  reason: invalid class name */
    class AnonymousClass13 implements Observer<List<Object>> {
        AnonymousClass13() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(11875, false);
            a((List) obj);
            AppMethodBeat.o(11875);
        }

        public void a(List<Object> list) {
            int i = 0;
            AppMethodBeat.i(11873, false);
            if (TextUtils.isEmpty(SearchFragment.this.e.getText().toString())) {
                list.clear();
            }
            RecyclerView recyclerView = SearchFragment.this.p;
            if (cn.missfresh.utils.b.a(list)) {
                i = 8;
            }
            recyclerView.setVisibility(i);
            SearchFragment.this.w.a(list);
            ArrayList arrayList = new ArrayList();
            if (list != null) {
                for (Object obj : list) {
                    if (obj instanceof AssociatedBean) {
                        arrayList.add(((AssociatedBean) obj).sku);
                    } else {
                        arrayList.add(String.valueOf(obj));
                    }
                }
            }
            c.a(SearchFragment.this.e.getText().toString(), SearchFragment.this.D.a, SearchFragment.this.D.b ? "1" : "2", arrayList);
            AppMethodBeat.o(11873);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchFragment$14  reason: invalid class name */
    class AnonymousClass14 implements Observer<List<SearchHotBean>> {
        AnonymousClass14() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(11879, false);
            a((List) obj);
            AppMethodBeat.o(11879);
        }

        public void a(List<SearchHotBean> list) {
            String str;
            AppMethodBeat.i(11877, false);
            SearchFragment.this.j.setVisibility(cn.missfresh.utils.b.a(list) ? 8 : 0);
            SearchFragment.this.b.removeAllViews();
            int i = 0;
            while (list != null && i < list.size()) {
                SearchFragment.this.b.addView(SearchFragment.a(SearchFragment.this, list.get(i).value, SearchFragment.this.b, list.get(i).beautify == 1, true));
                i++;
            }
            String str2 = "";
            if (SearchFragment.this.getArguments() == null) {
                str = str2;
            } else {
                str = SearchFragment.this.getArguments().getString("hot_word");
            }
            String j = SearchFragment.this.D.j();
            if (SearchFragment.this.k.getVisibility() == 0) {
                str2 = (String) SearchFragment.this.a.getTag();
            }
            c.a(str, j, str2);
            AppMethodBeat.o(11877);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchFragment$15  reason: invalid class name */
    class AnonymousClass15 implements Observer<List<String>> {
        AnonymousClass15() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(11884, false);
            a((List) obj);
            AppMethodBeat.o(11884);
        }

        public void a(List<String> list) {
            AppMethodBeat.i(11882, false);
            SearchFragment.this.a.removeAllViews();
            if (cn.missfresh.utils.b.a(list)) {
                SearchFragment.this.k.setVisibility(8);
            } else {
                SearchFragment.this.k.setVisibility(0);
                SearchFragment.this.a.setTag(at.a(list, ','));
                SearchFragment.this.a.removeAllViews();
                for (int i = 0; i < list.size(); i++) {
                    SearchFragment.this.a.addView(SearchFragment.a(SearchFragment.this, list.get(i), SearchFragment.this.a, false, false));
                }
            }
            AppMethodBeat.o(11882);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchFragment$17  reason: invalid class name */
    class AnonymousClass17 implements Observer<SearchHotBean.SearchHotListBean> {
        AnonymousClass17() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(11888, false);
            a((SearchHotBean.SearchHotListBean) obj);
            AppMethodBeat.o(11888);
        }

        public void a(SearchHotBean.SearchHotListBean searchHotListBean) {
            int i;
            int i2;
            AppMethodBeat.i(11887, false);
            if (searchHotListBean == null || !SearchFragment.this.B.j()) {
                SearchFragment.a(SearchFragment.this, false);
                SearchFragment.b(SearchFragment.this, false);
                SearchFragment.this.F.setVisibility(8);
                SearchFragment.this.u.a((List) null, "", "", 0);
                SearchFragment.this.v.a((List) null, "", "", 0);
                SearchFragment.this.G.setVisibility(8);
                AppMethodBeat.o(11887);
                return;
            }
            if (searchHotListBean.getAlwaysBuy() == null || searchHotListBean.getAlwaysBuy().getProductList() == null || searchHotListBean.getAlwaysBuy().getProductList().size() == 0) {
                SearchFragment.a(SearchFragment.this, false);
                i = 0;
            } else {
                i = searchHotListBean.getAlwaysBuy().getProductList().size();
            }
            if (searchHotListBean.getHotSales() == null || searchHotListBean.getHotSales().getProductList() == null || searchHotListBean.getHotSales().getProductList().size() == 0) {
                SearchFragment.b(SearchFragment.this, false);
                i2 = 0;
            } else {
                i2 = searchHotListBean.getHotSales().getProductList().size();
            }
            if (i != 0) {
                SearchFragment.this.F.setVisibility(0);
                SearchFragment.a(SearchFragment.this, true);
                SearchFragment.this.l.setText(searchHotListBean.getAlwaysBuy().getTitle());
                SearchFragment.this.s.setText(searchHotListBean.getAlwaysBuy().getSubTitle());
                cn.missfresh.module.base.oftenbuy.c.a.a();
                SearchFragment.this.u.a(searchHotListBean.getAlwaysBuy().getProductList(), searchHotListBean.getAlwaysBuy().getFavourite_id(), searchHotListBean.getAlwaysBuy().getRecommendRequestId(), i2);
            }
            if (i2 != 0) {
                SearchFragment.this.F.setVisibility(0);
                SearchFragment.b(SearchFragment.this, true);
                SearchFragment.this.m.setText(searchHotListBean.getHotSales().getTitle());
                SearchFragment.this.t.setText(searchHotListBean.getHotSales().getSubTitle());
                SearchFragment.this.v.a(searchHotListBean.getHotSales().getProductList(), searchHotListBean.getHotSales().getFirstCategoryCode(), searchHotListBean.getHotSales().getRecommendRequestId(), i);
            }
            if (i == 0 || i2 != 0) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) SearchFragment.this.n.getLayoutParams();
                layoutParams.setMargins(aw.b(10), aw.b(10), aw.b(0), aw.b(50));
                SearchFragment.this.n.setLayoutParams(layoutParams);
            } else {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) SearchFragment.this.n.getLayoutParams();
                layoutParams2.setMargins(aw.b(10), aw.b(10), aw.b(10), aw.b(50));
                SearchFragment.this.n.setLayoutParams(layoutParams2);
            }
            AppMethodBeat.o(11887);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchFragment$2  reason: invalid class name */
    class AnonymousClass2 implements Observer<Boolean> {
        AnonymousClass2() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(11823, false);
            a((Boolean) obj);
            AppMethodBeat.o(11823);
        }

        public void a(Boolean bool) {
            AppMethodBeat.i(11821, false);
            SearchFragment.this.c.setMaxHeight(((int) SearchFragment.this.getContext().getResources().getDimension(R.dimen.margin_78)) + 1);
            AppMethodBeat.o(11821);
        }
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchFragment$3  reason: invalid class name */
    class AnonymousClass3 implements Observer<Boolean> {
        AnonymousClass3() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(11827, false);
            a((Boolean) obj);
            AppMethodBeat.o(11827);
        }

        public void a(Boolean bool) {
            AppMethodBeat.i(11825, false);
            SearchFragment.this.d.setMaxHeight(((int) SearchFragment.this.getContext().getResources().getDimension(R.dimen.margin_122)) + 1);
            AppMethodBeat.o(11825);
        }
    }

    private void b(boolean z) {
        int i = 0;
        AppMethodBeat.i(11920, false);
        View view = this.n;
        if (!z) {
            i = 8;
        }
        view.setVisibility(i);
        AppMethodBeat.o(11920);
    }

    private void c(boolean z) {
        int i = 0;
        AppMethodBeat.i(11923, false);
        View view = this.o;
        if (!z) {
            i = 8;
        }
        view.setVisibility(i);
        AppMethodBeat.o(11923);
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchFragment$4  reason: invalid class name */
    class AnonymousClass4 extends s {
        AnonymousClass4() {
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.common.interfaces.s
        public void b(int i, int i2, Object obj) {
            AppMethodBeat.i(11832, false);
            if (obj == null || SearchFragment.this.getActivity() == null || SearchFragment.this.w == null || SearchFragment.this.w.a(i) == null) {
                AppMethodBeat.o(11832);
                return;
            }
            Object a = SearchFragment.this.w.a(i);
            if (a instanceof AssociatedBean) {
                c.a(SearchFragment.this.e.getText().toString(), ((AssociatedBean) SearchFragment.this.w.a().get(i)).sku, i, SearchFragment.this.D.a);
                ((IProductDetailService) com.alibaba.android.arouter.b.a.a().a("/product/product_detail_service").navigation()).routerProductDetail(((AssociatedBean) a).sku, "", "", 0, "");
            } else if (a instanceof String) {
                c.a(SearchFragment.this.e.getText().toString(), i, SearchFragment.this.w.a().get(i), SearchFragment.this.D.a);
                String str = (String) obj;
                SearchFragment.this.E.a(str);
                SearchFragment.this.D.a(str);
                SearchFragment.this.E.c();
            }
            AppMethodBeat.o(11832);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: cn.missfresh.module.search.ui.SearchFragment */
    /* JADX WARN: Multi-variable type inference failed */
    public void onResume() {
        AppMethodBeat.i(11925, false);
        SearchFragment.super.onResume();
        SearchSimpleHotSaleAdapter searchSimpleHotSaleAdapter = this.u;
        if (searchSimpleHotSaleAdapter != null) {
            searchSimpleHotSaleAdapter.notifyDataSetChanged();
            a(j.d());
        }
        SearchSimpleHotSaleAdapter searchSimpleHotSaleAdapter2 = this.v;
        if (searchSimpleHotSaleAdapter2 != null) {
            searchSimpleHotSaleAdapter2.notifyDataSetChanged();
            a(j.d());
        }
        MiniShoppingCartView miniShoppingCartView = this.G;
        if (miniShoppingCartView != null) {
            miniShoppingCartView.a();
        }
        AppMethodBeat.onResume(this);
        AppMethodBeat.o(11925);
    }

    /* renamed from: cn.missfresh.module.search.ui.SearchFragment$7  reason: invalid class name */
    class AnonymousClass7 extends MFPagedScrollListener {
        @Override // cn.missfresh.ui.recyclerview.MFPagedScrollListener
        public void a() {
        }

        @Override // cn.missfresh.ui.recyclerview.MFPagedScrollListener
        public int b() {
            return 0;
        }

        AnonymousClass7() {
        }

        @Override // cn.missfresh.ui.recyclerview.MFPagedScrollListener
        public void a(List<Integer> list) {
            AppMethodBeat.i(11850, false);
            SearchFragment.this.D.a(list);
            SearchFragment.this.D.b(list);
            AppMethodBeat.o(11850);
        }
    }

    @Override // cn.missfresh.module.base.common.listener.a
    public void a(ImageView imageView) {
        AppMethodBeat.i(11927, false);
        MiniShoppingCartView miniShoppingCartView = this.G;
        if (miniShoppingCartView == null || miniShoppingCartView.getVisibility() != 0) {
            this.C.a(getActivity(), imageView);
        } else {
            this.G.a(getActivity(), imageView);
        }
        AppMethodBeat.o(11927);
    }

    private void a(int i) {
        AppMethodBeat.i(11929, false);
        if (!this.B.j()) {
            AppMethodBeat.o(11929);
            return;
        }
        if (i < 0) {
            i = j.d();
        }
        if (i > 0) {
            this.A.setText(String.valueOf(i));
            this.A.a();
        } else {
            this.A.b();
        }
        AppMethodBeat.o(11929);
    }
}
