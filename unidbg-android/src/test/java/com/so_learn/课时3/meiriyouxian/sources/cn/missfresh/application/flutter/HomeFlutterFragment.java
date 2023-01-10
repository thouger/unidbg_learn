package cn.missfresh.application.flutter;

import android.os.Bundle;
import android.provider.BrowserContract;
import android.view.View;
import android.widget.ImageView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import cn.missfresh.basiclib.utils.c;
import cn.missfresh.flutter.FlutterCallAndroid;
import cn.missfresh.flutter.bean.FlutterReflushShoppingCartEvent;
import cn.missfresh.flutter.feature.base.BaseFlutterFragment;
import cn.missfresh.flutter.feature.base.b;
import cn.missfresh.module.base.bean.EventHideTalentHomeMessage;
import cn.missfresh.module.base.bean.ProductsEntity;
import cn.missfresh.module.base.common.providers.IProductDetailService;
import cn.missfresh.module.base.common.providers.IShoppingCartService2;
import cn.missfresh.module.base.common.providers.ISurveyService;
import cn.missfresh.module.base.helper.b;
import cn.missfresh.module.base.helper.f;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.support.dialog.BaseTipDialog;
import cn.missfresh.module.base.support.dialog.PrivacyProtocolDialog;
import cn.missfresh.module.base.widget.imageWatcher.ImageWatcher;
import cn.missfresh.module.food.bean.RelationStatus;
import cn.missfresh.module.food.dialog.FoodAddBottomDialog;
import cn.missfresh.module.food.helper.d;
import cn.missfresh.module.food.providers.IFoodService;
import cn.missfresh.module.other.market.bean.MarketEvalBean;
import cn.missfresh.module.other.market.dialog.UnitImageDialog;
import cn.missfresh.module.other.provider.IMarketEvaluationService;
import cn.missfresh.module.user.mine.bean.PrivacyNoticeData;
import cn.missfresh.module.user.mine.model.FlutterMineModel;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.unitepoplib.UnitePopManager;
import cn.missfresh.unitepoplib.bean.DialogBean;
import cn.missfresh.unitepoplib.bean.UnitePopMsgBean;
import cn.missfresh.unitepoplib.listener.UniteDialogEventListener;
import cn.missfresh.unitepoplib.view.a;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class HomeFlutterFragment extends BaseFlutterFragment implements b, b.a {
    boolean a = false;
    private FlutterMineModel b;
    private String c;
    private DialogBean d;
    private DialogBean e;
    private PrivacyNoticeData f;
    private a g;
    private ISurveyService h;
    private PrivacyProtocolDialog i;
    private boolean j = true;
    private boolean k = true;
    private cn.missfresh.module.food.helper.b l;
    private List<ProductsEntity> m;
    private int n;
    private boolean o;
    private int p = 0;
    private IMarketEvaluationService q;
    private HashSet<Integer> r = new HashSet<>();
    private final cn.missfresh.module.base.common.listener.a s = new AnonymousClass1();
    private final BaseTipDialog.a t = new AnonymousClass4();
    private final cn.missfresh.module.food.listener.b u = new AnonymousClass5();
    private final ImageWatcher.i v = new AnonymousClass6();

    public MethodChannel.Result a(String str) {
        return null;
    }

    @Override // cn.missfresh.flutter.feature.base.BaseFlutterFragment
    public String a() {
        return "flutter://missfresh.cn/homeFragment";
    }

    public void b(String str) {
    }

    @Override // cn.missfresh.flutter.feature.base.BaseFlutterFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    public HomeFlutterFragment() {
        AppMethodBeat.i(87, false);
        AppMethodBeat.o(87);
    }

    static /* synthetic */ void a(HomeFlutterFragment homeFlutterFragment, int i) {
        AppMethodBeat.i(242, false);
        homeFlutterFragment.e(i);
        AppMethodBeat.o(242);
    }

    static /* synthetic */ void a(HomeFlutterFragment homeFlutterFragment, DialogFragment dialogFragment) {
        AppMethodBeat.i(247, false);
        homeFlutterFragment.a(dialogFragment);
        AppMethodBeat.o(247);
    }

    static /* synthetic */ FlutterCallAndroid e(HomeFlutterFragment homeFlutterFragment) {
        AppMethodBeat.i(235, false);
        FlutterCallAndroid e = homeFlutterFragment.e();
        AppMethodBeat.o(235);
        return e;
    }

    static /* synthetic */ FlutterCallAndroid f(HomeFlutterFragment homeFlutterFragment) {
        AppMethodBeat.i(237, false);
        FlutterCallAndroid e = homeFlutterFragment.e();
        AppMethodBeat.o(237);
        return e;
    }

    static /* synthetic */ boolean i(HomeFlutterFragment homeFlutterFragment) {
        AppMethodBeat.i(250, false);
        boolean f = homeFlutterFragment.f();
        AppMethodBeat.o(250);
        return f;
    }

    static /* synthetic */ void k(HomeFlutterFragment homeFlutterFragment) {
        AppMethodBeat.i(255, false);
        homeFlutterFragment.q();
        AppMethodBeat.o(255);
    }

    /* renamed from: cn.missfresh.application.flutter.HomeFlutterFragment$1  reason: invalid class name */
    class AnonymousClass1 implements cn.missfresh.module.base.common.listener.a {
        AnonymousClass1() {
        }

        @Override // cn.missfresh.module.base.common.listener.a
        public void a(ImageView imageView) {
            AppMethodBeat.i(70, false);
            if (HomeFlutterFragment.this.l.c() != null) {
                HomeFlutterFragment.this.l.c().a(HomeFlutterFragment.this.getActivity(), imageView);
            }
            AppMethodBeat.o(70);
        }
    }

    /* renamed from: cn.missfresh.application.flutter.HomeFlutterFragment$4  reason: invalid class name */
    class AnonymousClass4 implements BaseTipDialog.a {
        AnonymousClass4() {
        }

        @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog.a
        public void a(int i, Object obj) {
            AppMethodBeat.i(80, false);
            if (i == 0) {
                f.a(null);
                d.b();
            } else if (i == 1) {
                f.b();
                d.a();
            }
            AppMethodBeat.o(80);
        }
    }

    /* renamed from: cn.missfresh.application.flutter.HomeFlutterFragment$5  reason: invalid class name */
    class AnonymousClass5 extends cn.missfresh.module.food.listener.b {
        AnonymousClass5() {
        }

        public void a(int i, int i2, int i3, int i4) {
            AppMethodBeat.i(72, false);
            if (i2 == -1) {
                AppMethodBeat.o(72);
                return;
            }
            if (i3 == 106) {
                if (e.o()) {
                    ((IProductDetailService) com.alibaba.android.arouter.b.a.a().a("/product/product_detail_service").navigation()).routerProductDetail(((ProductsEntity) HomeFlutterFragment.this.m.get(i4)).getSku(), ((ProductsEntity) HomeFlutterFragment.this.m.get(i4)).getImage(), ((ProductsEntity) HomeFlutterFragment.this.m.get(i4)).getAlgoId(), (long) HomeFlutterFragment.this.n, "");
                } else {
                    o.a(1);
                }
                d.a((long) HomeFlutterFragment.this.n, ((ProductsEntity) HomeFlutterFragment.this.m.get(i4)).getSku());
            } else if (i3 == 107) {
                HomeFlutterFragment.this.o = true;
                d.b((long) HomeFlutterFragment.this.n, ((ProductsEntity) HomeFlutterFragment.this.m.get(i4)).getSku());
            }
            AppMethodBeat.o(72);
        }
    }

    /* renamed from: cn.missfresh.application.flutter.HomeFlutterFragment$6  reason: invalid class name */
    class AnonymousClass6 implements ImageWatcher.i {
        @Override // cn.missfresh.module.base.widget.imageWatcher.ImageWatcher.i
        public void a(float f) {
        }

        @Override // cn.missfresh.module.base.widget.imageWatcher.ImageWatcher.i
        public void a(ImageWatcher imageWatcher, ImageView imageView, int i, String str, float f, int i2) {
        }

        AnonymousClass6() {
        }

        @Override // cn.missfresh.module.base.widget.imageWatcher.ImageWatcher.i
        public void a(ImageWatcher imageWatcher, int i, String str, int i2) {
            AppMethodBeat.i(93, false);
            if (i2 == 4) {
                HomeFlutterFragment.this.l.d();
                if (HomeFlutterFragment.this.o) {
                    HomeFlutterFragment.e(HomeFlutterFragment.this).a().invokeMethod("talentUpdateProductList", null);
                }
            }
            AppMethodBeat.o(93);
        }
    }

    @Override // cn.missfresh.flutter.feature.base.BaseFlutterFragment, io.flutter.embedding.android.FlutterFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(94, false);
        super.onCreate(bundle);
        l();
        AppMethodBeat.o(94);
    }

    @Override // cn.missfresh.flutter.feature.base.BaseFlutterFragment
    public void a(int i) {
        AppMethodBeat.i(99, false);
        super.a(i);
        this.p = i;
        if (i != 4) {
            u();
        }
        this.k = c(i);
        AppMethodBeat.o(99);
    }

    private boolean c(int i) {
        AppMethodBeat.i(104, false);
        if (this.r.contains(Integer.valueOf(i))) {
            AppMethodBeat.o(104);
            return false;
        }
        this.r.add(Integer.valueOf(i));
        AppMethodBeat.o(104);
        return true;
    }

    @Override // cn.missfresh.flutter.feature.base.BaseFlutterFragment
    public void b(int i) {
        AppMethodBeat.i(107, false);
        super.b(i);
        this.p = i;
        AppMethodBeat.o(107);
    }

    private void l() {
        AppMethodBeat.i(111, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            c(arguments.getInt("index", 0));
        }
        n();
        if ((arguments != null && arguments.getInt("index", 0) == 4) || this.p == 4) {
            this.p = arguments.getInt("index", 0);
            this.b = (FlutterMineModel) ViewModelProviders.of(getActivity()).get(FlutterMineModel.class);
            s();
        }
        AppMethodBeat.o(111);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        AppMethodBeat.i(117, false);
        super.onViewCreated(view, bundle);
        IShoppingCartService2 iShoppingCartService2 = (IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation();
        IFoodService iFoodService = (IFoodService) com.alibaba.android.arouter.b.a.a().a("/food/service").navigation();
        if (iShoppingCartService2 != null) {
            iShoppingCartService2.b().observe(this, new AnonymousClass7());
        }
        if (iFoodService != null) {
            iFoodService.a().observe(this, new $$Lambda$HomeFlutterFragment$hpAim_wpsraylSZMjgdGOjBuN70(this));
        }
        this.l = cn.missfresh.module.food.helper.b.a(a(view)).a(this.v).a(this.s).a(this.u);
        IShoppingCartService2 iShoppingCartService22 = (IShoppingCartService2) com.alibaba.android.arouter.b.a.a().a("/order/new_shoppingcart_service").navigation();
        iShoppingCartService22.b().observe(this, new AnonymousClass8(iShoppingCartService22));
        e().a("feature_talent", (cn.missfresh.flutter.feature.base.b) this);
        AppMethodBeat.o(117);
    }

    /* renamed from: cn.missfresh.application.flutter.HomeFlutterFragment$7  reason: invalid class name */
    class AnonymousClass7 implements Observer<Integer> {
        AnonymousClass7() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(79, false);
            a((Integer) obj);
            AppMethodBeat.o(79);
        }

        public void a(Integer num) {
            AppMethodBeat.i(74, false);
            if (!(num == null || HomeFlutterFragment.this.l == null || HomeFlutterFragment.this.l.b() == null)) {
                HomeFlutterFragment.this.l.b().a(num.intValue());
                HomeFlutterFragment.this.l.b().a();
            }
            AppMethodBeat.o(74);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(RelationStatus relationStatus) {
        int i = 0;
        AppMethodBeat.i(227, false);
        HashMap hashMap = new HashMap();
        if ("like".equals(relationStatus.type)) {
            hashMap.put("cookId", Integer.valueOf(relationStatus.cookId));
            if (relationStatus.status == 0) {
                i = 1;
            }
            hashMap.put("isLike", Integer.valueOf(i));
            e().a().invokeMethod("updateTalentLikeStatus", hashMap);
        } else if ("attention".equals(relationStatus.type)) {
            hashMap.put("followUserId", Integer.valueOf(relationStatus.authorID));
            hashMap.put("relation", Integer.valueOf(relationStatus.relation));
            e().a().invokeMethod("updateTalentAttentionStatus", hashMap);
        }
        AppMethodBeat.o(227);
    }

    /* renamed from: cn.missfresh.application.flutter.HomeFlutterFragment$8  reason: invalid class name */
    class AnonymousClass8 implements Observer<Integer> {
        final /* synthetic */ IShoppingCartService2 a;

        AnonymousClass8(IShoppingCartService2 iShoppingCartService2) {
            this.a = iShoppingCartService2;
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(84, false);
            a((Integer) obj);
            AppMethodBeat.o(84);
        }

        public void a(Integer num) {
            AppMethodBeat.i(81, false);
            HomeFlutterFragment.f(HomeFlutterFragment.this).a().invokeMethod("updateLocalShopCartData", this.a.e());
            AppMethodBeat.o(81);
        }
    }

    @Override // cn.missfresh.flutter.feature.base.BaseFlutterFragment, io.flutter.embedding.android.FlutterFragment, androidx.fragment.app.Fragment
    public void onResume() {
        AppMethodBeat.i(124, false);
        super.onResume();
        cn.missfresh.utils.a.d.c("HomeFlutterFragment=>", "onResume is called");
        cn.missfresh.module.food.helper.b bVar = this.l;
        if (!(bVar == null || bVar.b() == null)) {
            this.l.b().a();
        }
        if (!isHidden()) {
            m();
            UnitePopManager.setFragmentTag(getClass().getName());
        } else {
            cn.missfresh.utils.a.d.d("HomeFlutterFragment", "onResume:  hiden" + isHidden());
        }
        r();
        AppMethodBeat.onResume(this);
        AppMethodBeat.o(124);
    }

    @Override // cn.missfresh.flutter.feature.base.BaseFlutterFragment, cn.missfresh.module.base.helper.b.a
    public boolean Q_() {
        AppMethodBeat.i(127, false);
        boolean Q_ = super.Q_();
        cn.missfresh.module.food.helper.b bVar = this.l;
        if (bVar != null) {
            Q_ = bVar.d();
        }
        if (this.o) {
            e().a().invokeMethod("talentUpdateProductList", null);
        }
        AppMethodBeat.o(127);
        return Q_;
    }

    public void a(MethodCall methodCall, MethodChannel.Result result) {
        AppMethodBeat.i(129, false);
        if ("talentHomeLookBigPic".equals(methodCall.method)) {
            b(methodCall);
        } else if ("releaseCookTypeActionSheet".equals(methodCall.method)) {
            a(methodCall);
        } else if ("talentHomeNewMessageHandle".equals(methodCall.method) && "tabBarMessageNumHide".equals((String) methodCall.argument("type"))) {
            c.a().a(new EventHideTalentHomeMessage());
        }
        AppMethodBeat.o(129);
    }

    private void a(MethodCall methodCall) {
        AppMethodBeat.i(142, false);
        FoodAddBottomDialog.a(getActivity(), (List) methodCall.argument("issueList"), this.t);
        AppMethodBeat.o(142);
    }

    private void b(MethodCall methodCall) {
        AppMethodBeat.i(146, false);
        this.n = ((Integer) methodCall.argument("cookId")).intValue();
        int intValue = ((Integer) methodCall.argument(BrowserContract.Bookmarks.POSITION)).intValue();
        int intValue2 = ((Integer) methodCall.argument("imagePosition")).intValue();
        List parseArray = JSON.parseArray((String) methodCall.argument("images"), String.class);
        this.m = JSON.parseArray((String) methodCall.argument("products"), ProductsEntity.class);
        this.l.a(parseArray, intValue2);
        if (!cn.missfresh.utils.b.a(this.m)) {
            this.l.a(this.m, intValue, (long) this.n, 0);
            ArrayList arrayList = new ArrayList(this.m.size());
            for (ProductsEntity productsEntity : this.m) {
                arrayList.add(productsEntity.getSku());
            }
            d.a((long) this.n, arrayList);
        }
        AppMethodBeat.o(146);
    }

    private void m() {
        AppMethodBeat.i(149, false);
        if (!this.a || this.j) {
            cn.missfresh.utils.a.d.d("HomeFlutterFragment", "isPrivacyChecked:" + this.a + ",ifFirstToCheckLeadOpinion:" + this.j);
        } else {
            s();
        }
        this.j = false;
        AppMethodBeat.o(149);
    }

    private void n() {
        AppMethodBeat.i(157, false);
        this.d = new DialogBean().templateId("PrivacyProtocolDialog");
        this.e = new DialogBean().templateId("popOptionDialog");
        UnitePopManager.insertDialog(p(), this.d);
        UnitePopManager.registerDialogListener(p(), new AnonymousClass9());
        AppMethodBeat.o(157);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.application.flutter.HomeFlutterFragment$9  reason: invalid class name */
    public class AnonymousClass9 implements UniteDialogEventListener<UnitePopMsgBean> {
        AnonymousClass9() {
        }

        @Override // cn.missfresh.unitepoplib.listener.UniteDialogEventListener
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(82, false);
            a((UnitePopMsgBean) obj);
            AppMethodBeat.o(82);
        }

        public void a(UnitePopMsgBean unitePopMsgBean) {
            AppMethodBeat.i(76, false);
            if (unitePopMsgBean == null) {
                AppMethodBeat.o(76);
                return;
            }
            DialogBean dialogBean = unitePopMsgBean.getDialogBean();
            if (dialogBean == null) {
                AppMethodBeat.o(76);
                return;
            }
            if (unitePopMsgBean.getType() == 1005) {
                if (!"PrivacyProtocolDialog".equalsIgnoreCase(dialogBean.getTemplateId())) {
                    "popOptionDialog".equalsIgnoreCase(dialogBean.getTemplateId());
                } else if (HomeFlutterFragment.this.f != null) {
                    HomeFlutterFragment homeFlutterFragment = HomeFlutterFragment.this;
                    homeFlutterFragment.a(homeFlutterFragment.f);
                }
            }
            if (unitePopMsgBean.getType() == 1006 && "popOptionDialog".equalsIgnoreCase(dialogBean.getTemplateId()) && HomeFlutterFragment.this.g != null && HomeFlutterFragment.this.g.isShowing()) {
                HomeFlutterFragment.this.g.dismiss();
            }
            AppMethodBeat.o(76);
        }
    }

    public void a(PrivacyNoticeData privacyNoticeData) {
        AppMethodBeat.i(165, false);
        this.i = (PrivacyProtocolDialog) getChildFragmentManager().findFragmentByTag(PrivacyProtocolDialog.class.getSimpleName());
        if (this.i == null) {
            this.i = PrivacyProtocolDialog.a(this.d, p(), privacyNoticeData.getTitle(), privacyNoticeData.getText(), privacyNoticeData.getLinkList());
            this.i.setCancelable(false);
            this.i.a(new AnonymousClass10());
            if (!this.i.isAdded()) {
                this.i.show(getChildFragmentManager(), PrivacyProtocolDialog.class.getSimpleName());
                d(7);
            }
        }
        AppMethodBeat.o(165);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.application.flutter.HomeFlutterFragment$10  reason: invalid class name */
    public class AnonymousClass10 implements cn.missfresh.module.base.common.interfaces.b {
        AnonymousClass10() {
        }

        @Override // cn.missfresh.module.base.common.interfaces.b
        public void a() {
            AppMethodBeat.i(85, false);
            HomeFlutterFragment.a(HomeFlutterFragment.this, 7);
            AppMethodBeat.o(85);
        }
    }

    private void o() {
        AppMethodBeat.i(172, false);
        if (this.h == null) {
            this.h = (ISurveyService) com.alibaba.android.arouter.b.a.a().a("/order/survey_service").navigation();
        }
        AppMethodBeat.o(172);
    }

    private void d(int i) {
        AppMethodBeat.i(175, false);
        o();
        ISurveyService iSurveyService = this.h;
        if (iSurveyService != null) {
            iSurveyService.a(2, i);
        }
        AppMethodBeat.o(175);
    }

    private void e(int i) {
        AppMethodBeat.i(179, false);
        o();
        ISurveyService iSurveyService = this.h;
        if (iSurveyService != null) {
            iSurveyService.b(2, i);
        }
        AppMethodBeat.o(179);
    }

    private String p() {
        AppMethodBeat.i(183, false);
        if (this.c == null) {
            this.c = getActivity().getClass().getName() + getClass().getName();
        }
        String str = this.c;
        AppMethodBeat.o(183);
        return str;
    }

    private void q() {
        AppMethodBeat.i(187, false);
        o();
        ISurveyService iSurveyService = this.h;
        if (iSurveyService != null) {
            iSurveyService.a(2, (Fragment) this).observe(this, new AnonymousClass2());
        }
        AppMethodBeat.o(187);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.application.flutter.HomeFlutterFragment$2  reason: invalid class name */
    public class AnonymousClass2 implements Observer<DialogFragment> {
        AnonymousClass2() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(77, false);
            a((DialogFragment) obj);
            AppMethodBeat.o(77);
        }

        public void a(DialogFragment dialogFragment) {
            AppMethodBeat.i(75, false);
            HomeFlutterFragment.a(HomeFlutterFragment.this, dialogFragment);
            AppMethodBeat.o(75);
        }
    }

    private void a(DialogFragment dialogFragment) {
        AppMethodBeat.i(190, false);
        cn.missfresh.utils.a.d.c("HomeFlutterFragment=>", "showSurveyDialog is called");
        if (dialogFragment == null || getFragmentManager() == null || dialogFragment.isAdded()) {
            AppMethodBeat.o(190);
        } else if (!f()) {
            AppMethodBeat.o(190);
        } else if (getFragmentManager().findFragmentByTag("survey") != null) {
            AppMethodBeat.o(190);
        } else {
            dialogFragment.showNow(getFragmentManager(), "survey");
            AppMethodBeat.o(190);
        }
    }

    private View a(View view) {
        AppMethodBeat.i(193, false);
        if (view.getParent() == null) {
            AppMethodBeat.o(193);
            return view;
        } else if (view.getParent().getParent() == null) {
            View view2 = (View) view.getParent();
            AppMethodBeat.o(193);
            return view2;
        } else {
            View view3 = (View) view.getParent().getParent();
            AppMethodBeat.o(193);
            return view3;
        }
    }

    public void b(PrivacyNoticeData privacyNoticeData) {
        AppMethodBeat.i(196, false);
        if (!e.o()) {
            UnitePopManager.removeDialog("PrivacyProtocolDialog");
            AppMethodBeat.o(196);
            return;
        }
        cn.missfresh.module.base.common.c.a.a = (long) privacyNoticeData.getVersion();
        a(this.f);
        AppMethodBeat.o(196);
    }

    private void r() {
        AppMethodBeat.i(199, false);
        if (!f()) {
            AppMethodBeat.o(199);
            return;
        }
        o();
        ISurveyService iSurveyService = this.h;
        if (iSurveyService != null) {
            iSurveyService.a(2, (LifecycleOwner) this);
        }
        AppMethodBeat.o(199);
    }

    @Override // cn.missfresh.flutter.feature.base.BaseFlutterFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        AppMethodBeat.i(206, false);
        super.onHiddenChanged(z);
        if (z || this.p != 4) {
            u();
            cn.missfresh.utils.a.d.d("HomeFlutterFragment", "onHiddenChanged:" + z + ",index:" + this.p + "isPrivacyChecked:" + this.a);
        } else {
            s();
        }
        AppMethodBeat.onHiddenChanged(this, z);
        AppMethodBeat.o(206);
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onHandleEvent(FlutterReflushShoppingCartEvent flutterReflushShoppingCartEvent) {
        AppMethodBeat.i(209, false);
        if (k() == null) {
            AppMethodBeat.o(209);
        } else if (k().a() == null) {
            AppMethodBeat.o(209);
        } else {
            k().a().invokeMethod("refreshShopCart", null);
            AppMethodBeat.o(209);
        }
    }

    @Override // cn.missfresh.flutter.feature.base.BaseFlutterFragment
    public void E_() {
        AppMethodBeat.i(212, false);
        cn.missfresh.utils.a.d.c("FlutterMineFragment=>", "onLoginOut is called");
        e.i(false);
        e.m("");
        e.n("");
        e().a().invokeMethod("logoutNotifiFlutter", null);
        AppMethodBeat.o(212);
    }

    @Override // cn.missfresh.flutter.feature.base.BaseFlutterFragment
    public void w_() {
        AppMethodBeat.i(215, false);
        e().a().invokeMethod("notLoginReturn", null);
        AppMethodBeat.o(215);
    }

    private void s() {
        AppMethodBeat.i(218, false);
        t();
        long af = e.af();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pageKey", "ME_PAGE");
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("isFirstInto", Boolean.valueOf(this.k));
        jSONObject2.put("intoPage", "IS_NOT_EMPTY");
        jSONObject2.put("privacyVersion", Long.valueOf(af));
        jSONObject.put("conditionValueMap", jSONObject2);
        this.q.a(jSONObject, new Object[0]);
        AppMethodBeat.o(218);
    }

    private void t() {
        AppMethodBeat.i(220, false);
        if (this.q == null) {
            this.q = (IMarketEvaluationService) com.alibaba.android.arouter.b.a.a().a("/other/market_service").navigation();
            this.q.a().a(this, new AnonymousClass3());
        }
        AppMethodBeat.o(220);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.application.flutter.HomeFlutterFragment$3  reason: invalid class name */
    public class AnonymousClass3 implements Observer<MarketEvalBean> {
        AnonymousClass3() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(86, false);
            a((MarketEvalBean) obj);
            AppMethodBeat.o(86);
        }

        public void a(MarketEvalBean marketEvalBean) {
            AppMethodBeat.i(83, false);
            if (!HomeFlutterFragment.i(HomeFlutterFragment.this) || !"ME_PAGE".equalsIgnoreCase(marketEvalBean.pageKey)) {
                AppMethodBeat.o(83);
                return;
            }
            if (!cn.missfresh.utils.b.a(marketEvalBean.componentList)) {
                MarketEvalBean.ComponentList componentList = (MarketEvalBean.ComponentList) marketEvalBean.componentList.get(0);
                if (!(componentList == null || componentList.componentContent == null)) {
                    if (marketEvalBean.actType == 21) {
                        PrivacyNoticeData privacyNoticeData = new PrivacyNoticeData();
                        MarketEvalBean.MiddleContent middleContent = componentList.componentContent.middleContent;
                        privacyNoticeData.setTitle(componentList.componentContent.topContent);
                        privacyNoticeData.setText(middleContent.middle);
                        privacyNoticeData.setLinkList(componentList.componentContent.dataMap.privacyLink);
                        privacyNoticeData.setVersion(componentList.componentContent.dataMap.privacyVersion);
                        HomeFlutterFragment.this.f = privacyNoticeData;
                        HomeFlutterFragment.this.b(privacyNoticeData);
                    } else if (marketEvalBean.actType == 19) {
                        HomeFlutterFragment.this.q.a(HomeFlutterFragment.this.getActivity(), marketEvalBean, "ME_PAGE");
                    } else if (marketEvalBean.actType == 8) {
                        UnitImageDialog findFragmentByTag = HomeFlutterFragment.this.getChildFragmentManager().findFragmentByTag(UnitImageDialog.class.getSimpleName());
                        if (findFragmentByTag == null) {
                            findFragmentByTag = UnitImageDialog.a(marketEvalBean);
                        }
                        if (!findFragmentByTag.isAdded()) {
                            findFragmentByTag.show(HomeFlutterFragment.this.getFragmentManager(), UnitImageDialog.class.getSimpleName());
                        }
                    } else {
                        HomeFlutterFragment.this.q.a(HomeFlutterFragment.this.getActivity(), marketEvalBean, "ME_PAGE");
                    }
                }
            } else {
                HomeFlutterFragment.k(HomeFlutterFragment.this);
            }
            AppMethodBeat.o(83);
        }
    }

    @Override // cn.missfresh.flutter.feature.base.BaseFlutterFragment, io.flutter.embedding.android.FlutterFragment, androidx.fragment.app.Fragment
    public void onPause() {
        AppMethodBeat.i(222, false);
        super.onPause();
        u();
        AppMethodBeat.o(222);
    }

    private void u() {
        AppMethodBeat.i(224, false);
        IMarketEvaluationService iMarketEvaluationService = this.q;
        if (iMarketEvaluationService != null) {
            iMarketEvaluationService.a().a((LifecycleOwner) this);
            this.q = null;
        }
        AppMethodBeat.o(224);
    }
}
