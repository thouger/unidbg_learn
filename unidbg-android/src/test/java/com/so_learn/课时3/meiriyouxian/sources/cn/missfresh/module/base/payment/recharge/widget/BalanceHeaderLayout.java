package cn.missfresh.module.base.payment.recharge.widget;

import android.content.Context;
import android.net.wifi.WifiEnterpriseConfig;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.TimedRemoteCaller;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.common.listener.b;
import cn.missfresh.module.base.payment.recharge.a.a;
import cn.missfresh.module.base.payment.recharge.bean.Balance;
import cn.missfresh.module.base.support.dialog.e;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.widget.PriceTextView;
import cn.missfresh.module.base.widget.banner.view.ConvenientBanner;
import cn.missfresh.module.base.widget.banner.view.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import java.util.List;

public class BalanceHeaderLayout extends LinearLayout implements View.OnClickListener {
    private ConvenientBanner a;
    private PriceTextView b;
    private TextView c;
    private View d;
    private View e;
    private TextView f;
    private TextView g;
    private TextView h;
    private ImageView i;
    private a j;
    private String k;
    private View l;
    private PriceTextView m;
    private PriceTextView n;

    /* renamed from: cn.missfresh.module.base.payment.recharge.widget.BalanceHeaderLayout$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ BalanceHeaderLayout a;

        AnonymousClass1(BalanceHeaderLayout balanceHeaderLayout) {
            JniLib.cV(this, balanceHeaderLayout, Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_ENTRY_POINT_ZERO_TOUCH));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JniLib.cV(this, view, Integer.valueOf((int) MetricsProto.MetricsEvent.BLUETOOTH_DIALOG_FRAGMENT));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.payment.recharge.widget.BalanceHeaderLayout$3  reason: invalid class name */
    public class AnonymousClass3 implements b {
        final /* synthetic */ List a;
        final /* synthetic */ BalanceHeaderLayout b;

        AnonymousClass3(BalanceHeaderLayout balanceHeaderLayout, List list) {
            JniLib.cV(this, balanceHeaderLayout, list, Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_ENTRY_POINT_TRUSTED_SOURCE));
        }

        @Override // cn.missfresh.module.base.common.listener.b
        public void a(int i) {
            JniLib.cV(this, Integer.valueOf(i), Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_ENTRY_POINT_ADB));
        }
    }

    public void a() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_COPY_ACCOUNT_TASK_MS));
    }

    public void b() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_CREATE_PROFILE_TASK_MS));
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onFinishInflate() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_START_PROFILE_TASK_MS));
    }

    public BalanceHeaderLayout(Context context) {
        super(context);
    }

    public BalanceHeaderLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BalanceHeaderLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void c() {
        AppMethodBeat.i(18034, false);
        this.a = (ConvenientBanner) findViewById(R.id.cv_balance_banner);
        this.b = (PriceTextView) findViewById(R.id.tv_my_balance);
        this.c = (TextView) findViewById(R.id.tv_cashBack_explain);
        this.d = findViewById(R.id.v_balance_header_divider1);
        this.e = findViewById(R.id.v_balance_header_divider2);
        this.b.setSpannablePriceWithRMB(0);
        new AnonymousClass1(this);
        this.g = (TextView) findViewById(R.id.banlance_gift_explain);
        this.f = (TextView) findViewById(R.id.banlance_store_gift);
        this.h = (TextView) findViewById(R.id.tv_on_the_way_hint);
        this.i = (ImageView) findViewById(R.id.tv_on_the_way);
        findViewById(R.id.tv_balance_description).setOnClickListener(this);
        findViewById(R.id.iv_balance_description_icon).setOnClickListener(this);
        this.l = findViewById(R.id.tv_on_the_way_parent);
        this.m = (PriceTextView) findViewById(R.id.tv_recharge_balance);
        this.n = (PriceTextView) findViewById(R.id.tv_gif_card_balance);
        AppMethodBeat.o(18034);
    }

    public void setOnItemClickListener(a aVar) {
        this.j = aVar;
    }

    public void setBalance(Balance balance) {
        AppMethodBeat.i(18038, false);
        if (balance != null) {
            setVisibility(0);
            this.b.setSpannablePriceWithRMB(balance.money);
            String string = getResources().getString(R.string.product_price_yuan);
            if (balance.arrivingAmount != 0) {
                this.l.setVisibility(0);
                this.h.setText("\u5728\u9014 " + at.a(balance.arrivingAmount) + " \u5143");
                this.c.setText(getContext().getString(R.string.cash_arrive_str) + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + String.format(string, at.a(balance.arrivedAmount)));
            } else {
                this.l.setVisibility(8);
                String str = getContext().getString(R.string.cash_arrive_str) + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + String.format(string, at.a(balance.arrivedAmount));
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(40), 0, str.length(), 33);
                this.c.setText(spannableStringBuilder);
            }
            if (balance.banner == null) {
                this.a.setVisibility(8);
                this.f.setVisibility(8);
            } else {
                if (!cn.missfresh.utils.b.a(balance.bannerTitle)) {
                    this.f.setVisibility(0);
                    this.f.setText(balance.bannerTitle);
                } else {
                    this.f.setVisibility(8);
                }
                this.a.setVisibility(0);
                a(balance.banner);
            }
            if (!cn.missfresh.utils.b.a(balance.giftExplainTitle)) {
                this.g.setVisibility(0);
                this.g.setText(balance.giftExplainTitle);
            } else {
                this.g.setVisibility(8);
            }
            this.k = balance.recharge_explain;
            this.m.setPrice(balance.recharge_balance_new);
            this.n.setPrice(balance.card_balance);
        } else {
            this.f.setVisibility(8);
            this.g.setVisibility(8);
            this.a.setVisibility(8);
        }
        AppMethodBeat.o(18038);
    }

    private void a(List<BannerEntity> list) {
        AppMethodBeat.i(18047, false);
        if (!cn.missfresh.utils.b.a(list)) {
            aw.a(this.a, list.get(0).getWidth(), list.get(0).getHeight());
            this.a.setVisibility(0);
            this.a = this.a.a(new AnonymousClass2(this), list, new AnonymousClass3(this, list));
            if (list.size() > 1) {
                this.a.a(new int[]{R.drawable.shape_circle_c6_5dp, R.drawable.shape_circle_red_5dp});
                this.a.setManualPageable(true);
                this.a.a(TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS);
            } else {
                this.a.a(false);
                this.a.setManualPageable(false);
                this.a.c();
            }
        } else {
            this.a.setVisibility(8);
        }
        AppMethodBeat.o(18047);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.payment.recharge.widget.BalanceHeaderLayout$2  reason: invalid class name */
    public class AnonymousClass2 implements c<cn.missfresh.module.base.widget.banner.view.b> {
        final /* synthetic */ BalanceHeaderLayout a;

        AnonymousClass2(BalanceHeaderLayout balanceHeaderLayout) {
            JniLib.cV(this, balanceHeaderLayout, Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_ENTRY_POINT_QR_CODE));
        }

        public cn.missfresh.module.base.widget.banner.view.b a() {
            return (cn.missfresh.module.base.widget.banner.view.b) JniLib.cL(this, Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_ENTRY_POINT_NFC));
        }

        @Override // cn.missfresh.module.base.widget.banner.view.c
        public /* synthetic */ Object b() {
            AppMethodBeat.i(18023, false);
            cn.missfresh.module.base.widget.banner.view.b a = a();
            AppMethodBeat.o(18023);
            return a;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(18049, false);
        int id = view.getId();
        if ((id == R.id.tv_balance_description || id == R.id.iv_balance_description_icon) && !cn.missfresh.utils.b.a(this.k)) {
            e.a(getContext(), this.k);
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(18049);
    }
}
