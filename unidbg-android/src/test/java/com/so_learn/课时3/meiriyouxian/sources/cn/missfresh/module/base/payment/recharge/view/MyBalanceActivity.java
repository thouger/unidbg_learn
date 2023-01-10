package cn.missfresh.module.base.payment.recharge.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Downloads;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.bean.BindingInfo;
import cn.missfresh.module.base.bean.StoreInfo;
import cn.missfresh.module.base.manager.RedDotManager;
import cn.missfresh.module.base.payment.recharge.a.a;
import cn.missfresh.module.base.payment.recharge.b.b;
import cn.missfresh.module.base.payment.recharge.b.c;
import cn.missfresh.module.base.payment.recharge.b.e;
import cn.missfresh.module.base.payment.recharge.bean.RechargeCard;
import cn.missfresh.module.base.payment.recharge.widget.BalanceHeaderLayout;
import cn.missfresh.module.base.widget.BindWxAndTelLayout;
import cn.missfresh.module.base.widget.MultiStateLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import de.greenrobot.event.Subscribe;
import in.srain.cube.views.ptr.interfaces.PtrHandler;
import in.srain.cube.views.ptr.widget.PtrFrameLayout;
import in.srain.cube.views.ptr.widget.PtrMissFreshFrameLayout;

public class MyBalanceActivity extends BaseFragmentActivity implements TextWatcher, a, e.a, a, MultiStateLayout.d {
    private MultiStateLayout a;
    private PtrMissFreshFrameLayout j;
    private BalanceHeaderLayout k;
    private TextView l;
    private BindWxAndTelLayout m;
    private EditText n;
    private View o;
    private c p;
    private b v;
    private LinearLayout w;
    private LinearLayout x;

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.payment.recharge.view.MyBalanceActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements PtrHandler {
        final /* synthetic */ MyBalanceActivity a;

        AnonymousClass1(MyBalanceActivity myBalanceActivity) {
            JniLib.cV(this, myBalanceActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SUPPORT_DAIL_TOLLFREE));
        }

        @Override // in.srain.cube.views.ptr.interfaces.PtrHandler
        public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
            return JniLib.cZ(this, ptrFrameLayout, view, view2, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SUPPORT_DISCLAIMER_CANCEL));
        }

        @Override // in.srain.cube.views.ptr.interfaces.PtrHandler
        public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
            JniLib.cV(this, ptrFrameLayout, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SUPPORT_DISCLAIMER_OK));
        }
    }

    private void D() {
        JniLib.cV(this, 510);
    }

    private void w() {
        JniLib.cV(this, 511);
    }

    private void x() {
        JniLib.cV(this, 512);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void J_() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SUPPORT_VIEW_TRAVEL_ABROAD_DIALOG));
    }

    @Override // cn.missfresh.module.base.payment.recharge.b.e.a
    public StoreInfo a() {
        return (StoreInfo) JniLib.cL(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SUPPORT_DIAL_TOLLED));
    }

    @Override // cn.missfresh.module.base.payment.recharge.a.a
    public void a(int i, BannerEntity bannerEntity) {
        JniLib.cV(this, Integer.valueOf(i), bannerEntity, 488);
    }

    @Override // cn.missfresh.module.base.payment.recharge.b.e.a
    public void a(RechargeCard rechargeCard) {
        JniLib.cV(this, rechargeCard, 489);
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.a
    public void a(String str, String str2, String str3) {
        JniLib.cV(this, str, str2, str3, 490);
    }

    public void a(boolean z, String str) {
        JniLib.cV(this, Boolean.valueOf(z), str, 491);
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        JniLib.cV(this, editable, 492);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        JniLib.cV(this, charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), 493);
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.a
    public void c(String str) {
        JniLib.cV(this, str, 494);
    }

    @Override // cn.missfresh.module.base.payment.recharge.b.e.a
    public void c_(String str) {
        JniLib.cV(this, str, 495);
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.a
    public void d(String str) {
        JniLib.cV(this, str, 496);
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.a
    public void d(boolean z) {
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.a
    public void e(boolean z) {
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.a
    public void f(boolean z) {
    }

    @Override // cn.missfresh.module.base.widget.MultiStateLayout.d
    public void f_() {
        JniLib.cV(this, Integer.valueOf((int) Downloads.Impl.STATUS_TOO_MANY_REDIRECTS));
    }

    @Override // cn.missfresh.module.base.payment.recharge.b.e.a
    public void g_() {
        JniLib.cV(this, 498);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        JniLib.cV(this, Integer.valueOf(i), Integer.valueOf(i2), intent, 499);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onDestroy() {
        JniLib.cV(this, 500);
    }

    @Subscribe
    public void onHandleEvent(BindingInfo bindingInfo) {
        JniLib.cV(this, bindingInfo, 501);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onPause() {
        JniLib.cV(this, 502);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onStart() {
        JniLib.cV(this, 503);
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        JniLib.cV(this, charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), 504);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), 505);
    }

    public String q() {
        return (String) JniLib.cL(this, 506);
    }

    public void r() {
        JniLib.cV(this, 507);
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.a
    public void u() {
        JniLib.cV(this, 508);
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.a
    public void v() {
        JniLib.cV(this, 509);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(17542, false);
        super.onCreate(bundle);
        setContentView(R.layout.activity_my_balance_new);
        s();
        w();
        RedDotManager.a().a = 0;
        RedDotManager.a().b();
        AppMethodBeat.o(17542);
    }

    private void s() {
        AppMethodBeat.i(17544, false);
        this.e.setLeftButtonVisibility(0);
        this.e.setCenterTxt("\u6211\u7684\u4f59\u989d");
        this.e.setCenterVisibility(0);
        this.e.setRightButtonVisibility(0);
        this.e.setRightButtonJustText("\u8d26\u5355\u8bb0\u5f55");
        this.e.setRightButtonOnClickListener(this);
        this.a = (MultiStateLayout) findViewById(R.id.multi_state_layout_new);
        this.a.setOnRefreshListener(this);
        this.j = (PtrMissFreshFrameLayout) findViewById(R.id.ptr_balance);
        this.j.setPtrHandler(new AnonymousClass1(this));
        this.j.disableWhenHorizontalMove(true);
        this.w = (LinearLayout) findViewById(R.id.balance_content_ll);
        t();
        this.n = (EditText) findViewById(R.id.et_vip_exchange);
        this.o = findViewById(R.id.iv_vip_clear_input);
        this.m = (BindWxAndTelLayout) findViewById(R.id.cv_bind_wx_layout);
        this.l = (TextView) findViewById(R.id.balance_gift_explainId);
        this.x = (LinearLayout) findViewById(R.id.but_to_recharge_ll);
        findViewById(R.id.btn_vip_exchange).setOnClickListener(this);
        findViewById(R.id.btn_recharge).setOnClickListener(this);
        this.n.addTextChangedListener(this);
        this.o.setOnClickListener(this);
        AppMethodBeat.o(17544);
    }

    private void t() {
        AppMethodBeat.i(17545, false);
        View inflate = getLayoutInflater().inflate(R.layout.layout_banlance_header, (ViewGroup) null);
        this.k = (BalanceHeaderLayout) inflate.findViewById(R.id.cv_balance_header);
        this.k.setOnItemClickListener(this);
        this.w.addView(inflate, 0);
        AppMethodBeat.o(17545);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: cn.missfresh.module.base.payment.recharge.view.MyBalanceActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(17560, true);
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.btn_vip_exchange) {
            String trim = this.n.getText().toString().trim();
            if (cn.missfresh.utils.b.a(trim)) {
                cn.missfresh.ui.a.a.a("\u8bf7\u8f93\u5165\u5151\u6362\u7801");
                AppMethodBeat.o(17560);
                return;
            }
            this.p.b(trim);
        } else if (id == R.id.iv_vip_clear_input) {
            this.n.setText("");
        } else if (id == R.id.rl_title_bar_right_button) {
            startActivity(new Intent((Context) this, (Class<?>) BalanceBillingRecordActivity.class));
        } else if (id == R.id.btn_recharge) {
            a((RechargeCard) null);
        }
        AppMethodBeat.o(17560);
    }
}
