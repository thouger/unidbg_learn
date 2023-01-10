package cn.missfresh.module.base.payment.recharge.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.payment.recharge.adapter.a;
import cn.missfresh.module.base.widget.LoadMoreListView;
import cn.missfresh.module.base.widget.MultiStateLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.bangcle.andjni.JniLib;
import com.taobao.accs.common.Constants;
import in.srain.cube.views.ptr.interfaces.PtrHandler;
import in.srain.cube.views.ptr.widget.PtrFrameLayout;
import in.srain.cube.views.ptr.widget.PtrMissFreshFrameLayout;

public class BalanceBillingRecordActivity extends BaseFragmentActivity implements a, LoadMoreListView.a, MultiStateLayout.d {
    private a a;
    private LoadMoreListView j;
    private cn.missfresh.module.base.payment.recharge.b.a k;
    private MultiStateLayout l;
    private PtrMissFreshFrameLayout m;

    /* renamed from: cn.missfresh.module.base.payment.recharge.view.BalanceBillingRecordActivity$1  reason: invalid class name */
    class AnonymousClass1 implements PtrHandler {
        final /* synthetic */ BalanceBillingRecordActivity a;

        AnonymousClass1(BalanceBillingRecordActivity balanceBillingRecordActivity) {
            JniLib.cV(this, balanceBillingRecordActivity, 434);
        }

        @Override // in.srain.cube.views.ptr.interfaces.PtrHandler
        public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
            return JniLib.cZ(this, ptrFrameLayout, view, view2, 432);
        }

        @Override // in.srain.cube.views.ptr.interfaces.PtrHandler
        public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
            JniLib.cV(this, ptrFrameLayout, 433);
        }
    }

    private void w() {
        JniLib.cV(this, 448);
    }

    @Override // cn.missfresh.module.base.widget.LoadMoreListView.a
    public void a(LoadMoreListView loadMoreListView) {
        JniLib.cV(this, loadMoreListView, 435);
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.a
    public void a(String str, String str2, String str3) {
        JniLib.cV(this, str, str2, str3, 436);
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.a
    public void c(String str) {
        JniLib.cV(this, str, 437);
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.a
    public void d(String str) {
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.a
    public void d(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), 438);
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.a
    public void e(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), 439);
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.a
    public void f(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), 440);
    }

    @Override // cn.missfresh.module.base.widget.MultiStateLayout.d
    public void f_() {
        JniLib.cV(this, 441);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), 442);
    }

    public void r() {
        JniLib.cV(this, Integer.valueOf((int) Constants.PORT));
    }

    public void s() {
        JniLib.cV(this, 444);
    }

    public void t() {
        JniLib.cV(this, 445);
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.a
    public void u() {
        JniLib.cV(this, 446);
    }

    @Override // cn.missfresh.module.base.payment.recharge.view.a
    public void v() {
        JniLib.cV(this, 447);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: cn.missfresh.module.base.payment.recharge.view.BalanceBillingRecordActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(17329, false);
        super.onCreate(bundle);
        setContentView(R.layout.activity_balance_billing_record);
        this.e.setCenterVisibility(0);
        this.e.setLeftButtonVisibility(0);
        this.e.setCenterTxt("\u8d26\u5355\u8bb0\u5f55");
        this.j = (LoadMoreListView) findViewById(R.id.lv_my_balance);
        this.j.setOnLoadMoreListener(this);
        this.k = new cn.missfresh.module.base.payment.recharge.b.a(this);
        this.a = new a(this, this.k);
        this.j.setAdapter((ListAdapter) this.a);
        this.k.b();
        this.l = (MultiStateLayout) findViewById(R.id.multi_state_layout_new);
        this.l.setOnRefreshListener(this);
        this.m = (PtrMissFreshFrameLayout) findViewById(R.id.ptr_balance);
        this.m.setPtrHandler(new AnonymousClass1(this));
        this.m.disableWhenHorizontalMove(true);
        AppMethodBeat.o(17329);
    }
}
