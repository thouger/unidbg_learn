package cn.missfresh.module.base.payment.recharge.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.manager.PayManager;
import cn.missfresh.module.base.payment.recharge.view.CardGiftClaimActivity;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.widget.PriceTextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;

public class CardPurchaseActivity extends BaseFragmentActivity implements PayManager.a {
    private static String a = "EXTRA_IMAGE_URL";
    private static String j = "EXTRA_CARD_PRICE";
    private ImageView k;
    private ImageView l;
    private PayManager m;
    private View n;
    private View o;
    private ImageView p;
    private PriceTextView v;
    private TextView w;
    private a x;

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.payment.recharge.view.CardPurchaseActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ CardPurchaseActivity a;

        AnonymousClass1(CardPurchaseActivity cardPurchaseActivity) {
            JniLib.cV(this, cardPurchaseActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DELETION_HELPER_APPS_DELETION_FAIL));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JniLib.cV(this, view, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DELETION_HELPER_REMOVE_CANCEL));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.payment.recharge.view.CardPurchaseActivity$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ CardPurchaseActivity a;

        AnonymousClass2(CardPurchaseActivity cardPurchaseActivity) {
            JniLib.cV(this, cardPurchaseActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DELETION_HELPER_PHOTOS_VIDEOS_DELETION_FAIL));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JniLib.cV(this, view, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DELETION_HELPER_DOWNLOADS_DELETION_FAIL));
        }
    }

    public static void a(Context context, String str, int i) {
        JniLib.cV(context, str, Integer.valueOf(i), 480);
    }

    private void c(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SUPPORT_PHONE));
    }

    private void s() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SUPPORT_CHAT));
    }

    @Override // cn.missfresh.module.base.manager.PayManager.a
    public void ab_() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.SUPPORT_FRAGMENT));
    }

    @Override // cn.missfresh.module.base.manager.PayManager.a
    public void d_() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SELECT_SUMMARY));
    }

    @Override // cn.missfresh.module.base.manager.PayManager.a
    public void e_() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SELECT_SUPPORT_FRAGMENT));
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onDestroy() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SUPPORT_TIPS_AND_TRICKS));
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SUPPORT_HELP_AND_FEEDBACK));
    }

    static /* synthetic */ void a(CardPurchaseActivity cardPurchaseActivity) {
        AppMethodBeat.i(17528, false);
        cardPurchaseActivity.u();
        AppMethodBeat.o(17528);
    }

    static /* synthetic */ void b(CardPurchaseActivity cardPurchaseActivity) {
        AppMethodBeat.i(17530, false);
        cardPurchaseActivity.t();
        AppMethodBeat.o(17530);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(17490, false);
        super.onCreate(bundle);
        setContentView(R.layout.activity_card_purchase_layout);
        r();
        s();
        AppMethodBeat.o(17490);
    }

    private void r() {
        AppMethodBeat.i(17493, false);
        e_("\u50a8\u503c\u5361\u8d2d\u4e70");
        this.e.setCenterVisibility(0);
        this.e.setLeftButtonVisibility(0);
        this.p = (ImageView) findViewById(R.id.purchaseCardImage);
        this.k = (ImageView) findViewById(R.id.cb_order_pay_wx);
        this.l = (ImageView) findViewById(R.id.cb_order_pay_ali);
        this.v = (PriceTextView) findViewById(R.id.cardPrice);
        this.w = (TextView) findViewById(R.id.toPay);
        this.w.setOnClickListener(this);
        this.n = findViewById(R.id.aliPay);
        this.o = findViewById(R.id.wxPay);
        this.n.setOnClickListener(new AnonymousClass1(this));
        this.o.setOnClickListener(new AnonymousClass2(this));
        ViewGroup.LayoutParams layoutParams = this.p.getLayoutParams();
        layoutParams.width = aw.a(this.f) - 72;
        layoutParams.height = (layoutParams.width * 100) / 158;
        this.p.setLayoutParams(layoutParams);
        View findViewById = findViewById(R.id.buttonShadow);
        ViewGroup.LayoutParams layoutParams2 = findViewById.getLayoutParams();
        layoutParams2.width = layoutParams.width;
        findViewById.setLayoutParams(layoutParams2);
        AppMethodBeat.o(17493);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(17497, true);
        super.onClick(view);
        if (view.getId() == R.id.toPay) {
            CardGiftClaimActivity.a(this.f, CardGiftClaimActivity.Type.Gift, this.x.a(), this.x.b());
        }
        AppMethodBeat.o(17497);
    }

    private void t() {
        AppMethodBeat.i(17500, false);
        this.k.setImageResource(R.drawable.ic_address_selected);
        this.l.setImageResource(R.drawable.ic_address_normal);
        c("wxpay");
        AppMethodBeat.o(17500);
    }

    private void u() {
        AppMethodBeat.i(17503, false);
        this.k.setImageResource(R.drawable.ic_address_normal);
        this.l.setImageResource(R.drawable.ic_address_selected);
        c("alipay");
        AppMethodBeat.o(17503);
    }

    private class a {
        final /* synthetic */ CardPurchaseActivity a;
        private String b;
        private int c;
        private String d;

        private a(CardPurchaseActivity cardPurchaseActivity) {
            JniLib.cV(this, cardPurchaseActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.DASHBOARD_CONTAINER));
        }

        /* synthetic */ a(CardPurchaseActivity cardPurchaseActivity, AnonymousClass1 r2) {
            this(cardPurchaseActivity);
        }

        public void a(String str) {
            this.d = str;
        }

        public String a() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }

        public int b() {
            return this.c;
        }

        public void a(int i) {
            this.c = i;
        }
    }
}
