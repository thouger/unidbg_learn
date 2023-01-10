package cn.missfresh.module.base.payment.recharge.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.common.listener.b;
import cn.missfresh.module.base.payment.recharge.b.d;
import cn.missfresh.module.base.payment.recharge.bean.StoreCardBean;
import cn.missfresh.module.base.payment.recharge.widget.ScalePageTransformer;
import cn.missfresh.module.base.payment.recharge.widget.StoredPaysLayout;
import cn.missfresh.module.base.payment.recharge.widget.ThirdPaysLayout;
import cn.missfresh.module.base.payment.recharge.widget.a;
import cn.missfresh.module.base.support.dialog.BaseTipDialog;
import cn.missfresh.module.base.support.dialog.e;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.base.widget.MultiStateLayout;
import cn.missfresh.module.base.widget.banner.view.ConvenientBanner;
import cn.missfresh.module.base.widget.banner.view.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import com.google.android.material.tabs.TabLayout;
import java.util.Iterator;
import java.util.List;

public class RechargeActivity extends BaseFragmentActivity implements ViewPager.OnPageChangeListener, b, StoredPaysLayout.a, ThirdPaysLayout.a, MultiStateLayout.d, TabLayout.OnTabSelectedListener {
    private ThirdPaysLayout a;
    private d j;
    private Button k;
    private ConvenientBanner l;
    private TabLayout m;
    private TextView n;
    private StoredPaysLayout o;
    private int p;
    private String v;
    private View w;
    private View x;
    private MultiStateLayout y;

    /* renamed from: cn.missfresh.module.base.payment.recharge.view.RechargeActivity$2  reason: invalid class name */
    class AnonymousClass2 implements b {
        final /* synthetic */ RechargeActivity a;

        AnonymousClass2(RechargeActivity rechargeActivity) {
            JniLib.cV(this, rechargeActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_APN_EDITOR_ERROR));
        }

        @Override // cn.missfresh.module.base.common.listener.b
        public void a(int i) {
        }
    }

    /* renamed from: cn.missfresh.module.base.payment.recharge.view.RechargeActivity$3  reason: invalid class name */
    class AnonymousClass3 implements BaseTipDialog.a {
        final /* synthetic */ RechargeActivity a;

        AnonymousClass3(RechargeActivity rechargeActivity) {
            JniLib.cV(this, rechargeActivity, 532);
        }

        @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog.a
        public void a(int i, Object obj) {
            JniLib.cV(this, Integer.valueOf(i), obj, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_OWNER_INFO_SETTINGS));
        }
    }

    public static void a(Activity activity, String str, String str2, String str3, int i) {
        JniLib.cV(activity, str, str2, str3, Integer.valueOf(i), Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_UNIFY_SOUND_SETTINGS));
    }

    private void a(List<StoreCardBean> list) {
        JniLib.cV(this, list, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_ZEN_ACCESS_GRANT));
    }

    private void c(List<StoreCardBean> list) {
        JniLib.cV(this, list, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_ZEN_ACCESS_REVOKE));
    }

    private void s() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_ZEN_TIMEPICKER));
    }

    private void t() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_SERVICE_ACCESS_WARNING));
    }

    private void u() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_APP_INFO_ACTION));
    }

    private void v() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_VOLUME_FORGET));
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.b
    public void b(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_USER_CREDENTIAL));
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.b
    public void c(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_REMOVE_USER));
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.b
    public void d(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_CONFIRM_AUTO_SYNC_CHANGE));
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.b
    public void d_(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_RUNNIGN_SERVICE));
    }

    @Override // cn.missfresh.module.base.payment.recharge.widget.ThirdPaysLayout.a
    public void e(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_NO_HOME));
    }

    @Override // cn.missfresh.module.base.widget.MultiStateLayout.d
    public void f_() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_BLUETOOTH_RENAME));
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.b
    public void h_() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_BLUETOOTH_PAIRED_DEVICE_PROFILE));
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.b
    public void i_() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_HIGH_POWER_DETAILS));
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.b
    public void j_() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_KEYBOARD_LAYOUT));
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.b
    public void k_() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_WPS_SETUP));
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.b
    public void l_() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_WIFI_SCAN_MODE));
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.b
    public void m_() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_WIFI_SKIP));
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.b
    public void n_() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_LEGACY_VPN_CONFIG));
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.b
    public void o_() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_VPN_APP_CONFIG));
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onDestroy() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_VPN_CANNOT_CONNECT));
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        JniLib.cV(this, Integer.valueOf(i), Float.valueOf(f), Integer.valueOf(i2), Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_VPN_REPLACE_EXISTING));
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        JniLib.cV(this, Integer.valueOf(i), Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_BILLING_CYCLE));
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabSelected(TabLayout.Tab tab) {
        JniLib.cV(this, tab, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_BILLING_BYTE_LIMIT));
    }

    @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_BILLING_CONFIRM_LIMIT));
    }

    public String q() {
        return (String) JniLib.cL(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_DISABLE_NOTIFICATION_ACCESS));
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(17663, false);
        super.onCreate(bundle);
        setContentView(R.layout.activity_recharge);
        s();
        r();
        t();
        AppMethodBeat.o(17663);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: cn.missfresh.module.base.payment.recharge.view.RechargeActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void r() {
        AppMethodBeat.i(17664, false);
        this.e.setLeftButtonVisibility(0);
        this.e.setCenterTxt("\u4f59\u989d\u5145\u503c");
        this.e.setCenterVisibility(0);
        this.y = (MultiStateLayout) findViewById(R.id.multi_state_layout);
        this.y.setOnRefreshListener(this);
        this.a = (ThirdPaysLayout) findViewById(R.id.cv_third_pay);
        this.k = (Button) findViewById(R.id.btn_recharge);
        this.w = findViewById(R.id.btn_recharge_description);
        this.w.setOnClickListener(this);
        this.x = findViewById(R.id.btn_recharge_description_icon);
        this.x.setOnClickListener(this);
        this.a.setOnSelectPayWayListener(this);
        this.k.setOnClickListener(this);
        this.l = (ConvenientBanner) findViewById(R.id.cv_integral_banner);
        this.l.setTransformer(new ScalePageTransformer());
        this.m = (TabLayout) findViewById(R.id.tb_integral_price_indicator);
        this.m.setTabMode(0);
        this.m.addOnTabSelectedListener(this);
        this.l.a(this);
        this.o = (StoredPaysLayout) findViewById(R.id.ll_store_pays);
        this.o.setOnSelectAccountListener(this);
        this.n = (TextView) findViewById(R.id.tv_stored_desc);
        j.b((Activity) this, this.n, getResources().getString(R.string.user_stored_content));
        if (TextUtils.isEmpty(this.v)) {
            this.w.setVisibility(8);
            this.x.setVisibility(8);
        } else {
            this.w.setVisibility(0);
            this.x.setVisibility(0);
        }
        AppMethodBeat.o(17664);
    }

    private void b(List<StoreCardBean> list) {
        AppMethodBeat.i(17674, false);
        Iterator<StoreCardBean> it2 = list.iterator();
        while (it2.hasNext()) {
            TabLayout.Tab newTab = this.m.newTab();
            View inflate = getLayoutInflater().inflate(R.layout.item_recharge_price, (ViewGroup) null, false);
            ((TextView) inflate.findViewById(R.id.tv_channel_title)).setText(at.a(it2.next().getBalanceAmount()) + "\u5143");
            newTab.setCustomView(inflate);
            this.m.addTab(newTab);
        }
        AppMethodBeat.o(17674);
    }

    /* renamed from: cn.missfresh.module.base.payment.recharge.view.RechargeActivity$1  reason: invalid class name */
    class AnonymousClass1 implements c<a> {
        final /* synthetic */ RechargeActivity a;

        AnonymousClass1(RechargeActivity rechargeActivity) {
            JniLib.cV(this, rechargeActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_CUSTOM_LIST_CONFIRMATION));
        }

        public a a() {
            return (a) JniLib.cL(this, 528);
        }

        @Override // cn.missfresh.module.base.widget.banner.view.c
        public /* synthetic */ Object b() {
            AppMethodBeat.i(17648, false);
            a a = a();
            AppMethodBeat.o(17648);
            return a;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: cn.missfresh.module.base.payment.recharge.view.RechargeActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(17677, true);
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.btn_recharge) {
            this.j.r();
        } else if ((id == R.id.btn_recharge_description || id == R.id.btn_recharge_description_icon) && !cn.missfresh.utils.b.a(this.j.p())) {
            e.a((Context) this, this.j.p());
        }
        AppMethodBeat.o(17677);
    }

    private void a(StoreCardBean storeCardBean) {
        AppMethodBeat.i(17704, false);
        this.k.setText(String.format(getString(R.string.need_pay_amount), at.c(storeCardBean.getPayAmount())));
        AppMethodBeat.o(17704);
    }

    @Override // cn.missfresh.module.base.payment.recharge.widget.StoredPaysLayout.a
    public void a(int i) {
        AppMethodBeat.i(17710, false);
        if (i == StoredPaysLayout.a) {
            this.o.setOtherAccount(getString(R.string.other_account));
            this.j.c("");
        } else if (i == StoredPaysLayout.b) {
            v();
        }
        this.j.a(i);
        AppMethodBeat.o(17710);
    }
}
