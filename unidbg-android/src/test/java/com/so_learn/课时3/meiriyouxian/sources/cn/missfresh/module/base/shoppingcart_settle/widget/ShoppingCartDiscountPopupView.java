package cn.missfresh.module.base.shoppingcart_settle.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.shoppingcart_settle.adapter.ShoppingCartDiscountDetailAdapter;
import cn.missfresh.module.base.shoppingcart_settle.bean.BalanceArea;
import cn.missfresh.module.base.support.view.MaxHeightRecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.f;
import com.android.internal.logging.nano.MetricsProto;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.f.a;
import io.reactivex.q;
import io.reactivex.v;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDiscountPopupView extends FrameLayout {
    private final String a;
    private Animation b;
    private Animation c;
    private Animation d;
    private Animation e;
    private View f;
    private LinearLayout g;
    private boolean h;
    private TextView i;
    private ShoppingCartDiscountDetailAdapter j;
    private TextView k;
    private MaxHeightRecyclerView l;
    private TextView m;
    private TextView n;
    private View o;
    private ImageView p;
    private BalanceArea.DiscountDetailBean q;
    private b r;

    private void e() {
    }

    static /* synthetic */ List a(ShoppingCartDiscountPopupView shoppingCartDiscountPopupView, BalanceArea.DiscountDetailBean discountDetailBean) {
        AppMethodBeat.i(19666, false);
        List<BalanceArea.DiscountItemsBean> a = shoppingCartDiscountPopupView.a(discountDetailBean);
        AppMethodBeat.o(19666);
        return a;
    }

    public ShoppingCartDiscountPopupView(Context context) {
        this(context, null);
    }

    public ShoppingCartDiscountPopupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(19627, false);
        this.a = "ShoppingCartDiscountPopupView";
        a(context);
        AppMethodBeat.o(19627);
    }

    private void a(Context context) {
        AppMethodBeat.i(19630, false);
        View inflate = LayoutInflater.from(context).inflate(R.layout.base_shoppingcart_dialog_discount_bottom, (ViewGroup) null);
        this.f = inflate.findViewById(R.id.v_mask);
        this.g = (LinearLayout) inflate.findViewById(R.id.ll_content);
        this.i = (TextView) inflate.findViewById(R.id.title);
        this.k = (TextView) inflate.findViewById(R.id.tv_total_favourable);
        this.j = new ShoppingCartDiscountDetailAdapter();
        this.l = (MaxHeightRecyclerView) inflate.findViewById(R.id.recyclerView);
        this.l.setMaxHeight(f.c(context, MetricsProto.MetricsEvent.SUW_ACCESSIBILITY_DISPLAY_SIZE));
        this.m = (TextView) inflate.findViewById(R.id.tv_total_price);
        this.o = inflate.findViewById(R.id.iv_notice);
        this.n = (TextView) inflate.findViewById(R.id.tv_sub_notice_title);
        inflate.findViewById(R.id.iv_colse).setOnClickListener(new AnonymousClass1());
        this.f.setOnClickListener(new AnonymousClass2());
        this.l.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.l.setAdapter(this.j);
        addView(inflate);
        this.b = AnimationUtils.loadAnimation(context, R.anim.settle_sc_discount_popup_in);
        this.c = AnimationUtils.loadAnimation(context, R.anim.settle_sc_discount_popup_out);
        this.d = AnimationUtils.loadAnimation(context, R.anim.settle_sc_discount_mask_in);
        this.e = AnimationUtils.loadAnimation(context, R.anim.settle_sc_discount_mask_out);
        e();
        setVisibility(8);
        AppMethodBeat.o(19630);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.shoppingcart_settle.widget.ShoppingCartDiscountPopupView$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(19589, false);
            ShoppingCartDiscountPopupView.this.a();
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(19589);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.shoppingcart_settle.widget.ShoppingCartDiscountPopupView$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(19594, false);
            ShoppingCartDiscountPopupView.this.a();
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(19594);
        }
    }

    public void setIvRedPacket(ImageView imageView) {
        this.p = imageView;
    }

    public void setData(BalanceArea.DiscountDetailBean discountDetailBean) {
        AppMethodBeat.i(19635, false);
        this.q = discountDetailBean;
        f();
        AppMethodBeat.o(19635);
    }

    public void a(String str) {
        AppMethodBeat.i(19638, false);
        if (TextUtils.isEmpty(str)) {
            this.i.setVisibility(8);
        } else {
            this.i.setVisibility(0);
            this.i.setText(str);
        }
        AppMethodBeat.o(19638);
    }

    private void setSubTitle(String str) {
        AppMethodBeat.i(19641, false);
        if (!cn.missfresh.utils.b.a(str)) {
            this.o.setVisibility(0);
            this.n.setVisibility(0);
            this.n.setText(str);
        } else {
            this.o.setVisibility(8);
            this.n.setVisibility(8);
        }
        AppMethodBeat.o(19641);
    }

    private void f() {
        AppMethodBeat.i(19643, false);
        BalanceArea.DiscountDetailBean discountDetailBean = this.q;
        if (discountDetailBean != null) {
            a(discountDetailBean.getTitle());
            this.k.setText(this.q.getTotalTitle());
            this.m.setText(this.q.getTotalAmount());
            setSubTitle(this.q.getSubTitle());
            q.a(this.q).b((h) new AnonymousClass4()).b(a.b()).a(io.reactivex.a.b.a.a()).subscribe(new AnonymousClass3());
        }
        AppMethodBeat.o(19643);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.shoppingcart_settle.widget.ShoppingCartDiscountPopupView$4  reason: invalid class name */
    public class AnonymousClass4 implements h<BalanceArea.DiscountDetailBean, List<BalanceArea.DiscountItemsBean>> {
        AnonymousClass4() {
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(19618, false);
            List<BalanceArea.DiscountItemsBean> a = a((BalanceArea.DiscountDetailBean) obj);
            AppMethodBeat.o(19618);
            return a;
        }

        public List<BalanceArea.DiscountItemsBean> a(BalanceArea.DiscountDetailBean discountDetailBean) throws Exception {
            AppMethodBeat.i(19616, false);
            List<BalanceArea.DiscountItemsBean> a = ShoppingCartDiscountPopupView.a(ShoppingCartDiscountPopupView.this, discountDetailBean);
            AppMethodBeat.o(19616);
            return a;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.shoppingcart_settle.widget.ShoppingCartDiscountPopupView$3  reason: invalid class name */
    public class AnonymousClass3 implements v<List<BalanceArea.DiscountItemsBean>> {
        AnonymousClass3() {
        }

        @Override // io.reactivex.v
        public /* synthetic */ void onNext(Object obj) {
            AppMethodBeat.i(19610, false);
            a((List) obj);
            AppMethodBeat.o(19610);
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            AppMethodBeat.i(19601, false);
            ShoppingCartDiscountPopupView.this.d();
            ShoppingCartDiscountPopupView.this.r = bVar;
            AppMethodBeat.o(19601);
        }

        public void a(List<BalanceArea.DiscountItemsBean> list) {
            AppMethodBeat.i(19603, false);
            ShoppingCartDiscountPopupView.this.j.a(list);
            AppMethodBeat.o(19603);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            AppMethodBeat.i(19606, false);
            th.printStackTrace();
            d.b("ShoppingCartDiscountPopupView", th.getMessage());
            AppMethodBeat.o(19606);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            AppMethodBeat.i(19608, false);
            d.b("ShoppingCartDiscountPopupView", "onComplete");
            AppMethodBeat.o(19608);
        }
    }

    public void a() {
        AppMethodBeat.i(19645, false);
        d();
        this.h = false;
        this.g.setVisibility(8);
        this.g.startAnimation(this.c);
        this.f.setVisibility(8);
        this.f.startAnimation(this.e);
        ImageView imageView = this.p;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        AppMethodBeat.o(19645);
    }

    public void b() {
        AppMethodBeat.i(19650, false);
        f();
        this.h = true;
        setVisibility(0);
        this.g.setVisibility(0);
        this.g.startAnimation(this.b);
        this.f.setVisibility(0);
        this.f.startAnimation(this.d);
        ImageView imageView = this.p;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        AppMethodBeat.o(19650);
    }

    public boolean c() {
        return this.h;
    }

    public void d() {
        AppMethodBeat.i(19654, false);
        b bVar = this.r;
        if (bVar != null && !bVar.isDisposed()) {
            this.r.dispose();
        }
        AppMethodBeat.o(19654);
    }

    private List<BalanceArea.DiscountItemsBean> a(BalanceArea.DiscountDetailBean discountDetailBean) {
        AppMethodBeat.i(19658, false);
        ArrayList arrayList = new ArrayList();
        if (discountDetailBean == null) {
            AppMethodBeat.o(19658);
            return arrayList;
        }
        List<BalanceArea.DiscountGroupsBean> discountGroups = discountDetailBean.getDiscountGroups();
        if (cn.missfresh.utils.b.a(discountGroups)) {
            AppMethodBeat.o(19658);
            return arrayList;
        }
        for (BalanceArea.DiscountGroupsBean discountGroupsBean : discountGroups) {
            if (!cn.missfresh.utils.b.a(discountGroupsBean.getGroupTitle())) {
                BalanceArea.DiscountItemsBean discountItemsBean = new BalanceArea.DiscountItemsBean();
                discountItemsBean.setGroupTitle(discountGroupsBean.getGroupTitle());
                discountItemsBean.setGroupSubTitle(discountGroupsBean.getGroupSubTitle());
                discountItemsBean.setType(1);
                arrayList.add(discountItemsBean);
            }
            if (!cn.missfresh.utils.b.a(discountGroupsBean.getDiscountItems())) {
                arrayList.addAll(discountGroupsBean.getDiscountItems());
                ((BalanceArea.DiscountItemsBean) arrayList.get(arrayList.size() - 1)).setShowDivider(true);
            }
        }
        AppMethodBeat.o(19658);
        return arrayList;
    }
}
