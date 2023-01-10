package cn.missfresh.module.base.shoppingcart_settle.view;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.helper.GoodsAnimHelper;
import cn.missfresh.module.base.common.resourcemanager.bean.SkipBean;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.shoppingcart_settle.IShoppingCartSettleService;
import cn.missfresh.module.base.shoppingcart_settle.bean.BalanceArea;
import cn.missfresh.module.base.shoppingcart_settle.bean.ShoppingCartCloseBean;
import cn.missfresh.module.base.shoppingcart_settle.widget.ShoppingCartDiscountPopupView;
import cn.missfresh.module.base.support.dialog.BaseTipDialog;
import cn.missfresh.module.base.support.dialog.TipsDialog;
import cn.missfresh.module.base.utils.ac;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.base.widget.BadgeView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.fastjson.JSON;
import com.umeng.analytics.pro.ai;
import com.umeng.message.proguard.l;

public class MiniShoppingCartView extends ConstraintLayout implements View.OnClickListener {
    private TextView a;
    private Group b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private ImageView g;
    private View h;
    private IShoppingCartSettleService i;
    private ShoppingCartDiscountPopupView j;
    private boolean k;
    private BalanceArea.DiscountDetailBean l;
    private BalanceArea m;
    private BadgeView n;
    private GoodsAnimHelper o;
    private TipsDialog p;
    private a q;
    private String r;
    private int s;
    private Observer<BalanceArea> t;
    private Observer<Integer> u;

    public interface a {
        void a(boolean z, boolean z2);
    }

    private void c() {
    }

    static /* synthetic */ void a(MiniShoppingCartView miniShoppingCartView, BalanceArea balanceArea) {
        AppMethodBeat.i(19576, false);
        miniShoppingCartView.setData(balanceArea);
        AppMethodBeat.o(19576);
    }

    static /* synthetic */ void a(MiniShoppingCartView miniShoppingCartView, boolean z) {
        AppMethodBeat.i(19581, false);
        miniShoppingCartView.a(z);
        AppMethodBeat.o(19581);
    }

    public MiniShoppingCartView(Context context) {
        this(context, null);
    }

    public MiniShoppingCartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(19535, false);
        this.s = 1;
        this.t = new AnonymousClass2();
        this.u = new AnonymousClass3();
        LayoutInflater.from(context).inflate(R.layout.layout_mini_shopping_cart_view, this);
        b();
        AppMethodBeat.o(19535);
    }

    private void b() {
        AppMethodBeat.i(19536, false);
        this.a = (TextView) findViewById(R.id.tv_send_notice);
        this.b = (Group) findViewById(R.id.g_send_notice);
        this.c = (TextView) findViewById(R.id.tv_title);
        this.d = (TextView) findViewById(R.id.tv_sub_title);
        this.f = (TextView) findViewById(R.id.tv_go_pay);
        this.g = (ImageView) findViewById(R.id.iv_cart);
        this.j = (ShoppingCartDiscountPopupView) findViewById(R.id.scd_view);
        this.h = findViewById(R.id.ll_discount_detail);
        this.e = (TextView) findViewById(R.id.tv_sub_title_detail);
        this.n = (BadgeView) findViewById(R.id.shop_cart_count);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.o = new GoodsAnimHelper(0, 0);
        setVisibility(0);
        AppMethodBeat.o(19536);
    }

    public void setIvRedPacket(ImageView imageView) {
        AppMethodBeat.i(19538, false);
        this.j.setIvRedPacket(imageView);
        AppMethodBeat.o(19538);
    }

    public void setIvCount(int i) {
        int i2 = 0;
        AppMethodBeat.i(19540, false);
        this.n.setText(String.valueOf(i));
        BadgeView badgeView = this.n;
        if (i <= 0) {
            i2 = 8;
        }
        badgeView.setVisibility(i2);
        AppMethodBeat.o(19540);
    }

    public void setNeedLoginState(boolean z) {
        this.k = z;
    }

    public void setOnSettleChangeListener(a aVar) {
        this.q = aVar;
    }

    public void a(LifecycleOwner lifecycleOwner, String str) {
        AppMethodBeat.i(19546, false);
        this.r = str;
        if (this.i == null) {
            this.i = (IShoppingCartSettleService) com.alibaba.android.arouter.b.a.a().a("/base/shopping_cart_settle_service").navigation();
            this.i.a().observe(lifecycleOwner, this.t);
            if ("category".equals(str)) {
                this.i.b().observe(lifecycleOwner, this.u);
            }
        }
        AppMethodBeat.o(19546);
    }

    public void setClassifySpanCount(int i) {
        this.s = i;
    }

    public void setAnimAntView(View view) {
        AppMethodBeat.i(19548, false);
        if (view == null) {
            AppMethodBeat.o(19548);
            return;
        }
        view.post(new AnonymousClass1(view));
        AppMethodBeat.o(19548);
    }

    /* renamed from: cn.missfresh.module.base.shoppingcart_settle.view.MiniShoppingCartView$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ View a;

        AnonymousClass1(View view) {
            this.a = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(19502, false);
            int[] iArr = new int[2];
            this.a.getLocationOnScreen(iArr);
            d.d("MiniShoppingCartView", "x:" + iArr[0] + " y:" + iArr[1]);
            MiniShoppingCartView.this.o.a(iArr);
            AppMethodBeat.o(19502);
        }
    }

    public void a(Activity activity, ImageView imageView) {
        AppMethodBeat.i(19551, false);
        if (imageView == null) {
            AppMethodBeat.o(19551);
            return;
        }
        this.o.a(activity, imageView);
        AppMethodBeat.o(19551);
    }

    private void a(boolean z, boolean z2) {
        AppMethodBeat.i(19556, false);
        a aVar = this.q;
        if (aVar != null) {
            aVar.a(z, z2);
        }
        AppMethodBeat.o(19556);
    }

    private void setData(BalanceArea balanceArea) {
        AppMethodBeat.i(19559, false);
        this.m = balanceArea;
        ShoppingCartDiscountPopupView shoppingCartDiscountPopupView = this.j;
        if (shoppingCartDiscountPopupView != null && shoppingCartDiscountPopupView.c()) {
            this.j.a();
        }
        if (balanceArea == null) {
            AppMethodBeat.o(19559);
        } else if (balanceArea.getBalanceArea() == null) {
            AppMethodBeat.o(19559);
        } else {
            this.l = balanceArea.getBalanceArea().getDiscountDetail();
            setVisibility(0);
            a(true, balanceArea.getMakeOrderArea() != null && !b.a(balanceArea.getMakeOrderArea().getText()));
            c();
            BalanceArea.BalanceAreaBean balanceArea2 = balanceArea.getBalanceArea();
            String a2 = at.a(balanceArea2.getPayAmount());
            String payTips = balanceArea2.getPayTips();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) "\u5408\u8ba1\u00a5:");
            spannableStringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.ff1395)), 0, 4, 33);
            spannableStringBuilder.append((CharSequence) a2);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.ff1395)), 4, a2.length() + 4, 33);
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(16, true), 4, a2.length() + 4, 33);
            spannableStringBuilder.setSpan(new StyleSpan(1), 0, 4 + a2.length(), 33);
            if (!b.a(payTips)) {
                String str = l.s + payTips + l.t;
                spannableStringBuilder.append((CharSequence) str);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.color_969696)), ("\u5408\u8ba1\u00a5:" + a2).length(), ("\u5408\u8ba1\u00a5:" + a2 + str).length(), 33);
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(10, true), ("\u5408\u8ba1\u00a5:" + a2).length(), ("\u5408\u8ba1\u00a5:" + a2 + str).length(), 33);
            }
            this.c.setText(spannableStringBuilder);
            String str2 = "";
            String discountAmount = !b.a(balanceArea2.getDiscountAmount()) ? balanceArea2.getDiscountAmount() : str2;
            if (balanceArea2.getDiscountDetail() != null && !b.a(balanceArea2.getDiscountDetail().getTitle())) {
                str2 = balanceArea2.getDiscountDetail().getTitle();
            }
            if (!b.a(discountAmount) || !b.a(str2)) {
                this.h.setVisibility(0);
                if (!b.a(discountAmount)) {
                    this.d.setVisibility(0);
                    aw.a(this.d, discountAmount, getResources().getColor(R.color.red), new String[]{"#_$", "#_$"}, true);
                } else {
                    this.d.setVisibility(8);
                }
                if (!b.a(str2)) {
                    this.e.setVisibility(0);
                    aw.a(this.e, str2, getResources().getColor(R.color.red), new String[]{"#_$", "#_$"}, true);
                } else {
                    this.e.setVisibility(8);
                }
            } else {
                this.h.setVisibility(8);
            }
            if (b.a(discountAmount)) {
                this.d.setVisibility(8);
            } else {
                this.d.setVisibility(0);
                aw.a(this.d, discountAmount, getResources().getColor(R.color.red), new String[]{"#_$", "#_$"}, true);
            }
            if (balanceArea2.getBalanceCount() > 0) {
                this.f.setText("\u53bb\u7ed3\u7b97(" + balanceArea2.getBalanceCount() + l.t);
                this.f.setSelected(true);
                this.f.setActivated(true);
            } else {
                this.f.setText("\u53bb\u7ed3\u7b97");
                this.f.setSelected(false);
                this.f.setActivated(false);
            }
            if (balanceArea.getMakeOrderArea() == null || b.a(balanceArea.getMakeOrderArea().getText())) {
                this.b.setVisibility(8);
            } else {
                this.b.setVisibility(0);
                aw.a(this.a, balanceArea.getMakeOrderArea().getText(), getResources().getColor(R.color.color_ff4891), new String[]{"#_$", "#_$"}, true);
            }
            AppMethodBeat.o(19559);
        }
    }

    /* renamed from: cn.missfresh.module.base.shoppingcart_settle.view.MiniShoppingCartView$2  reason: invalid class name */
    class AnonymousClass2 implements Observer<BalanceArea> {
        AnonymousClass2() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(19509, false);
            a((BalanceArea) obj);
            AppMethodBeat.o(19509);
        }

        public void a(BalanceArea balanceArea) {
            AppMethodBeat.i(19508, false);
            d.d("MiniShoppingCartView", "onChanged");
            MiniShoppingCartView.a(MiniShoppingCartView.this, balanceArea);
            AppMethodBeat.o(19508);
        }
    }

    /* renamed from: cn.missfresh.module.base.shoppingcart_settle.view.MiniShoppingCartView$3  reason: invalid class name */
    class AnonymousClass3 implements Observer<Integer> {
        AnonymousClass3() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(19516, false);
            a((Integer) obj);
            AppMethodBeat.o(19516);
        }

        public void a(Integer num) {
            AppMethodBeat.i(19515, false);
            cn.missfresh.module.base.shoppingcart_settle.a.a.a(MiniShoppingCartView.this.s, (num == null || num.intValue() <= 0) ? 2 : 1);
            AppMethodBeat.o(19515);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(19562, false);
        if (view.getId() == R.id.tv_sub_title_detail) {
            d();
        } else if (view.getId() == R.id.tv_go_pay) {
            a(2);
            if (!this.f.isActivated()) {
                AppMethodBeat.onClick(this, view);
                AppMethodBeat.o(19562);
                return;
            }
            e();
        } else if (view.getId() == R.id.iv_cart || view.getId() == R.id.tv_title || view.getId() == R.id.tv_sub_title) {
            a(1);
            j.c("/order/shoppingcart_activity").withInt("fromType", 1).navigation();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(19562);
    }

    private void d() {
        AppMethodBeat.i(19564, false);
        this.j.setData(this.l);
        if (!this.j.c()) {
            this.j.b();
        }
        a(3);
        AppMethodBeat.o(19564);
    }

    private void a(int i) {
        AppMethodBeat.i(19566, false);
        int i2 = 1;
        if ("home".equals(this.r)) {
            if (!this.f.isActivated()) {
                i2 = 2;
            }
            cn.missfresh.module.base.shoppingcart_settle.a.a.b(i2, i);
        } else if ("category".equals(this.r)) {
            int i3 = this.s;
            if (!this.f.isActivated()) {
                i2 = 2;
            }
            cn.missfresh.module.base.shoppingcart_settle.a.a.a(i3, i2, i);
        } else if ("search_result".equals(this.r)) {
            if (!this.f.isActivated()) {
                i2 = 2;
            }
            cn.missfresh.module.base.shoppingcart_settle.a.a.c(i2, i);
        }
        AppMethodBeat.o(19566);
    }

    private void e() {
        AppMethodBeat.i(19567, false);
        StatisticsManager.h("click_settleAccount", new Object[0]);
        if (!e.o()) {
            o.a(1);
            AppMethodBeat.o(19567);
        } else if (!e.y()) {
            o.a(false, MiniShoppingCartView.class.getSimpleName());
            AppMethodBeat.o(19567);
        } else if (this.m == null || !a((FragmentActivity) getContext(), this.m.getPopWindow())) {
            a(false);
            AppMethodBeat.o(19567);
        } else {
            AppMethodBeat.o(19567);
        }
    }

    private boolean a(FragmentActivity fragmentActivity, ShoppingCartCloseBean shoppingCartCloseBean) {
        AppMethodBeat.i(19568, false);
        if (shoppingCartCloseBean == null) {
            AppMethodBeat.o(19568);
            return false;
        }
        TipsDialog tipsDialog = this.p;
        if (tipsDialog != null) {
            tipsDialog.dismissAllowingStateLoss();
        }
        int windowType = shoppingCartCloseBean.getWindowType();
        this.p = TipsDialog.l().a(shoppingCartCloseBean.getText()).a(false).b(windowType == 1 ? shoppingCartCloseBean.getConfirmText() : "").c(shoppingCartCloseBean.getCancelText()).a(ContextCompat.getColor(fragmentActivity, R.color.color_474245)).b(1).a(new AnonymousClass4(windowType)).a(fragmentActivity);
        this.p.k();
        StatisticsManager.h("exposure_ClosePop", ai.e, SkipBean.Type.cart, "type", Integer.valueOf(windowType));
        AppMethodBeat.o(19568);
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.shoppingcart_settle.view.MiniShoppingCartView$4  reason: invalid class name */
    public class AnonymousClass4 implements BaseTipDialog.a {
        final /* synthetic */ int a;

        AnonymousClass4(int i) {
            this.a = i;
        }

        /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x003b: APUT  
          (r14v3 java.lang.Object[])
          (5 ??[int, float, short, byte, char])
          (wrap: java.lang.Integer : 0x0037: INVOKE  (r15v8 java.lang.Integer) = (r9v1 int) type: STATIC call: java.lang.Integer.valueOf(int):java.lang.Integer)
         */
        @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog.a
        public void a(int i, Object obj) {
            AppMethodBeat.i(19522, false);
            int i2 = 3;
            if (i != 100) {
                Object[] objArr = new Object[6];
                objArr[0] = ai.e;
                objArr[1] = SkipBean.Type.cart;
                objArr[2] = "type";
                objArr[3] = Integer.valueOf(this.a);
                objArr[4] = "buttontype";
                if (this.a == 1) {
                    i2 = 2;
                }
                objArr[5] = Integer.valueOf(i2);
                StatisticsManager.h("click_ClosePop_Button", objArr);
                AppMethodBeat.o(19522);
                return;
            }
            MiniShoppingCartView.a(MiniShoppingCartView.this, true);
            StatisticsManager.h("click_ClosePop_Button", ai.e, SkipBean.Type.cart, "type", Integer.valueOf(this.a), "buttontype", 1);
            AppMethodBeat.o(19522);
        }
    }

    private void a(boolean z) {
        int i = 0;
        AppMethodBeat.i(19571, false);
        BalanceArea balanceArea = this.m;
        if (balanceArea == null) {
            cn.missfresh.ui.a.a.a("\u6ca1\u6709\u53ef\u7ed3\u7b97\u7684\u5546\u54c1,\u8bf7\u91cd\u8bd5");
            ac.a("settle", "goToOrderconfirm", "balanceArea == null");
            AppMethodBeat.o(19571);
        } else if (balanceArea.getBalanceArea() == null || this.m.getBalanceArea().getBalanceCount() <= 0 || b.a(this.m.getProducts())) {
            cn.missfresh.ui.a.a.a("\u6ca1\u6709\u53ef\u7ed3\u7b97\u7684\u5546\u54c1,\u8bf7\u91cd\u8bd5");
            ac.a("settle", "goToOrderconfirm", "\u6570\u636e\u6821\u9a8c\u4e0d\u901a\u8fc7");
            AppMethodBeat.o(19571);
        } else {
            String str = "search";
            Postcard withString = j.d("orderFill").withString("fromOrderConfirmType", "settle").withString("from", "settle").withString("page_from", "search_result".equals(this.r) ? str : this.r);
            if (!"search_result".equals(this.r)) {
                str = this.r;
            }
            Postcard withInt = withString.withString("settleFrom", str).withInt("saleEvent", z ? 1 : 0);
            BalanceArea balanceArea2 = this.m;
            Postcard withString2 = withInt.withString("productsJson", (balanceArea2 == null || b.a(balanceArea2.getProducts())) ? "" : JSON.toJSONString(this.m.getProducts()));
            BalanceArea balanceArea3 = this.m;
            if (!(balanceArea3 == null || balanceArea3.getBalanceArea() == null)) {
                i = this.m.getBalanceArea().getBalanceCount();
            }
            withString2.withInt("balanceCount", i).navigation();
            AppMethodBeat.o(19571);
        }
    }

    public void a() {
        AppMethodBeat.i(19572, false);
        if (this.i != null && getVisibility() == 0) {
            this.i.a(this.r);
        }
        AppMethodBeat.o(19572);
    }
}
