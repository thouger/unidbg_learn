package cn.missfresh.module.user.mine.qucikpay.view;

import android.app.Activity;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.bean.OpenWxMianMiBean;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.manager.PayManager;
import cn.missfresh.module.base.support.dialog.LoadingLikeIosDialog;
import cn.missfresh.module.base.support.dialog.c;
import cn.missfresh.module.base.support.dialog.e;
import cn.missfresh.module.base.support.view.TitleBar;
import cn.missfresh.module.base.utils.as;
import cn.missfresh.module.base.widget.MultiStateLayout;
import cn.missfresh.module.mvvm.BaseMVVMActivity;
import cn.missfresh.module.order.orderdetails_v2.bean.OpenQuickPayResultBean;
import cn.missfresh.module.order.orderdetails_v2.bean.QuickPayListItemBean;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.mine.qucikpay.adapter.QuickPayAdapter;
import cn.missfresh.module.user.mine.qucikpay.viewmodel.QuickPayViewModel;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import cn.missfresh.utils.a.d;
import com.alipay.sdk.app.OpenAuthTask;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import io.reactivex.c.g;
import io.reactivex.disposables.b;
import java.lang.ref.WeakReference;
import java.util.List;

public class QuickPayActivity extends BaseMVVMActivity implements View.OnClickListener, PayManager.a, MultiStateLayout.d {
    private Observer<OpenQuickPayResultBean> A = new AnonymousClass3(this);
    private Observer<Boolean> B = new AnonymousClass4(this);
    private Observer<OpenQuickPayResultBean> C = new AnonymousClass5(this);
    private Observer<OpenQuickPayResultBean> D = new AnonymousClass6(this);
    private Observer<Integer> E = new AnonymousClass7(this);
    public int a = -1;
    private final String b = "QuickPayActivity";
    private MultiStateLayout c;
    private TitleBar d;
    private RecyclerView e;
    private QuickPayAdapter f;
    private QuickPayViewModel h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private PayManager m;
    private c n;
    private c o;
    private b p;
    private View v;
    private View w;
    private LoadingLikeIosDialog x;
    private BottomSheetBehavior y;
    private Observer<List<QuickPayListItemBean>> z = new AnonymousClass2(this);

    @Override // cn.missfresh.module.base.manager.PayManager.a
    public void ab_() {
    }

    @Override // cn.missfresh.module.base.manager.PayManager.a
    public void d_() {
    }

    @Override // cn.missfresh.module.base.manager.PayManager.a
    public void e_() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void onWindowFocusChanged(boolean z) {
        QuickPayActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    public QuickPayActivity() {
        AppMethodBeat.i(9112, false);
        AppMethodBeat.o(9112);
    }

    static /* synthetic */ void h(QuickPayActivity quickPayActivity) {
        AppMethodBeat.i(9175, false);
        quickPayActivity.s();
        AppMethodBeat.o(9175);
    }

    static /* synthetic */ void j(QuickPayActivity quickPayActivity) {
        AppMethodBeat.i(9181, false);
        quickPayActivity.w();
        AppMethodBeat.o(9181);
    }

    static /* synthetic */ void l(QuickPayActivity quickPayActivity) {
        AppMethodBeat.i(9186, false);
        quickPayActivity.w();
        AppMethodBeat.o(9186);
    }

    /* access modifiers changed from: protected */
    public int M_() {
        return R.layout.user_activity_quick_pay;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void f() {
        AppMethodBeat.i(9115, false);
        this.h = (QuickPayViewModel) ViewModelProviders.of((FragmentActivity) this).get(QuickPayViewModel.class);
        AppMethodBeat.o(9115);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void g() {
        AppMethodBeat.i(9119, false);
        this.m = new PayManager(this, this);
        this.h.a().f();
        this.n = e.a(this);
        this.o = e.a((Activity) this, getResources().getString(R.string.query_delete_quick_pay));
        int i = this.a;
        if (i != -1) {
            StatisticsManager.ah("exposure_setting_no_pwd", "from", Integer.valueOf(i));
        }
        AppMethodBeat.o(9119);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void k() {
        boolean z = false;
        AppMethodBeat.i(9123, false);
        this.k = this.h.a().g().getValue() != null;
        d.d("QuickPayActivity", "stickList:" + this.k);
        this.h.a().g().observe(this, this.z);
        this.i = this.h.a().i().getValue() != null;
        d.d("QuickPayActivity", "stickOpen:" + this.i);
        this.h.a().i().observe(this, this.A);
        this.h.b().observe(this, this.B);
        this.j = this.h.a().h().getValue() != null;
        this.h.a().h().observe(this, this.C);
        if (this.h.a().j().getValue() != null) {
            z = true;
        }
        this.l = z;
        this.h.a().j().observe(this, this.D);
        this.h.c().observe(this, this.E);
        r();
        AppMethodBeat.o(9123);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void a() {
        AppMethodBeat.i(9125, false);
        as.a((Activity) this, getResources().getColor(R.color.black));
        this.e = (RecyclerView) findViewById(R.id.rcv_list);
        this.e.setLayoutManager(new LinearLayoutManager(this));
        this.f = new QuickPayAdapter();
        this.e.setAdapter(this.f);
        this.c = (MultiStateLayout) findViewById(cn.missfresh.module.base.R.id.multi_state_layout_new);
        this.c.setOnRefreshListener(this);
        this.d = (TitleBar) findViewById(R.id.title_bar);
        this.d.setCenterTxt("\u514d\u5bc6\u652f\u4ed8\u8bbe\u7f6e");
        this.d.setLeftButtonVisibility(0);
        this.d.setCenterVisibility(0);
        this.d.setLeftButtonOnClickListener(new 1(this));
        findViewById(R.id.tv_close).setOnClickListener(this);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        this.v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_open_quick_state_fail, (ViewGroup) null);
        this.w = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_open_quick_state_success, (ViewGroup) null);
        this.y = BottomSheetBehavior.from(findViewById(R.id.ll_close_notice));
        this.y.setHideable(true);
        this.y.setState(5);
        AppMethodBeat.o(9125);
    }

    @Override // cn.missfresh.module.base.widget.MultiStateLayout.d
    public void f_() {
        AppMethodBeat.i(9128, false);
        this.h.a().f();
        AppMethodBeat.o(9128);
    }

    public void l() {
        c cVar;
        AppMethodBeat.i(9133, false);
        if (!isFinishing() && (cVar = this.n) != null && !cVar.c()) {
            this.n.a();
        }
        AppMethodBeat.o(9133);
    }

    public void m() {
        c cVar;
        AppMethodBeat.i(9136, false);
        if (!isFinishing() && (cVar = this.o) != null && !cVar.c()) {
            this.o.a();
        }
        AppMethodBeat.o(9136);
    }

    public void n() {
        c cVar;
        AppMethodBeat.i(9137, false);
        if (!isFinishing() && (cVar = this.n) != null && cVar.c()) {
            this.n.b();
        }
        AppMethodBeat.o(9137);
    }

    public void p() {
        c cVar;
        AppMethodBeat.i(9140, false);
        if (!isFinishing() && (cVar = this.o) != null && cVar.c()) {
            this.o.b();
        }
        AppMethodBeat.o(9140);
    }

    public void b(String str) {
        AppMethodBeat.i(9141, false);
        d.d("QuickPayActivity", "\u5f00\u901a\u5931\u8d25");
        View view = this.v;
        if (cn.missfresh.utils.e.a(str)) {
            str = "\u514d\u5bc6\u652f\u4ed8\u5f00\u901a\u5931\u8d25";
        }
        a.a(view, 17, 0, 0, str);
        AppMethodBeat.o(9141);
    }

    public void d(String str) {
        AppMethodBeat.i(9142, false);
        d.d("QuickPayActivity", "\u5f00\u901a\u6210\u529f");
        View view = this.w;
        if (cn.missfresh.utils.e.a(str)) {
            str = "\u514d\u5bc6\u652f\u4ed8\u5f00\u901a\u6210\u529f";
        }
        a.a(view, 17, 0, 0, str);
        AppMethodBeat.o(9142);
    }

    /* renamed from: cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity$2  reason: invalid class name */
    class AnonymousClass2 implements Observer<List<QuickPayListItemBean>> {
        final /* synthetic */ QuickPayActivity a;

        AnonymousClass2(QuickPayActivity quickPayActivity) {
            JniLib.cV(this, quickPayActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.AUTOFILL_DATA_SAVE_REQUEST));
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(9043, false);
            a((List) obj);
            AppMethodBeat.o(9043);
        }

        public void a(List<QuickPayListItemBean> list) {
            AppMethodBeat.i(9042, false);
            if (this.a.k) {
                this.a.k = false;
                d.d("QuickPayActivity", "\u662fstick\u6570\u636e\u4e0d\u8bbe\u7f6e");
                AppMethodBeat.o(9042);
                return;
            }
            if (cn.missfresh.utils.b.a(list)) {
                this.a.c.a();
            } else {
                this.a.f.a(list);
                this.a.c.c();
                d.d("QuickPayActivity", "\u5217\u8868\u6570\u636e\u8fd4\u56de\u8bbe\u7f6e\u6570\u636e");
            }
            AppMethodBeat.o(9042);
        }
    }

    /* renamed from: cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity$3  reason: invalid class name */
    class AnonymousClass3 implements Observer<OpenQuickPayResultBean> {
        final /* synthetic */ QuickPayActivity a;

        AnonymousClass3(QuickPayActivity quickPayActivity) {
            JniLib.cV(this, quickPayActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.METRICS_CHECKPOINT));
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(9061, false);
            a((OpenQuickPayResultBean) obj);
            AppMethodBeat.o(9061);
        }

        /* JADX WARN: Type inference failed for: r4v1, types: [cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity, android.app.Activity] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(cn.missfresh.module.order.orderdetails_v2.bean.OpenQuickPayResultBean r10) {
            /*
                r9 = this;
                r0 = 0
                r1 = 9058(0x2362, float:1.2693E-41)
                cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
                cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity r2 = r9.a
                boolean r2 = cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity.d(r2)
                java.lang.String r3 = "QuickPayActivity"
                if (r2 == 0) goto L_0x0020
                cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity r10 = r9.a
                cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity.b(r10, r0)
                java.lang.String r10 = "\u662fstick\u6570\u636e\u4e0d\u8bbe\u7f6e"
                cn.missfresh.utils.a.d.d(r3, r10)
                cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
                return
            L_0x0020:
                java.lang.String r0 = ""
                java.lang.String r2 = "\u514d\u5bc6\u652f\u4ed8"
                java.lang.String r4 = "quickPay"
                if (r10 == 0) goto L_0x00c3
                boolean r5 = r10.isSuccess
                if (r5 == 0) goto L_0x00c3
                java.lang.String r5 = r10.payType
                java.lang.String r6 = "weixin_contract_pay"
                boolean r5 = r6.equals(r5)
                if (r5 != 0) goto L_0x00b7
                java.lang.String r5 = r10.payType
                java.lang.String r6 = "weixin_contract_pay_plus"
                boolean r5 = r6.equals(r5)
                if (r5 == 0) goto L_0x0046
                goto L_0x00b7
            L_0x0046:
                java.lang.String r3 = r10.payType
                java.lang.String r5 = "alipay_contract_pay"
                boolean r3 = r5.equals(r3)
                if (r3 != 0) goto L_0x007b
                java.lang.String r3 = r10.payType
                java.lang.String r5 = "alipay_contract_pay_plus"
                boolean r3 = r5.equals(r3)
                if (r3 == 0) goto L_0x005d
                goto L_0x007b
            L_0x005d:
                java.lang.String r10 = r10.payType
                java.lang.String r3 = "credit_wxpay"
                boolean r10 = r3.equalsIgnoreCase(r10)
                if (r10 == 0) goto L_0x00d2
                cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity r10 = r9.a
                cn.missfresh.module.base.manager.PayManager r10 = cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity.e(r10)
                boolean r10 = r10.a(r0)
                if (r10 != 0) goto L_0x00d2
                java.lang.String r10 = "\u5f00\u901a\u652f\u4ed8\u5206\u5931\u8d25"
                cn.missfresh.module.base.utils.ac.a(r4, r2, r10)
                goto L_0x00d2
            L_0x007b:
                java.util.Map r0 = r10.getAliPayInfo()
                java.lang.String r3 = "sign_params"
                java.lang.Object r0 = r0.get(r3)
                java.lang.String r0 = (java.lang.String) r0
                boolean r0 = cn.missfresh.utils.b.a(r0)
                if (r0 == 0) goto L_0x0094
                java.lang.String r0 = "\u7b7e\u7ea6\u7801\u4e3a\u7a7a"
                cn.missfresh.module.base.utils.ac.a(r4, r2, r0)
            L_0x0094:
                java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference
                cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity r2 = r9.a
                r0.<init>(r2)
                cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity r2 = r9.a
                cn.missfresh.module.user.mine.qucikpay.viewmodel.QuickPayViewModel r2 = cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity.f(r2)
                cn.missfresh.module.order.orderdetails_v2.provider.IQuickPayService r3 = r2.a()
                cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity r4 = r9.a
                com.alipay.sdk.app.OpenAuthTask$BizType r5 = com.alipay.sdk.app.OpenAuthTask.BizType.Deduct
                java.util.Map r6 = r10.getAliPayInfo()
                cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity$3$1 r7 = new cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity$3$1
                r7.<init>(r9, r0, r10)
                r8 = 1
                r3.a(r4, r5, r6, r7, r8)
                goto L_0x00d2
            L_0x00b7:
                cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity r0 = r9.a
                cn.missfresh.module.base.manager.PayManager r0 = cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity.e(r0)
                java.lang.String r10 = r10.contractId
                r0.b(r10, r3)
                goto L_0x00d2
            L_0x00c3:
                cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity r3 = r9.a
                if (r10 == 0) goto L_0x00c9
                java.lang.String r0 = r10.msg
            L_0x00c9:
                r3.b(r0)
                java.lang.String r10 = "openQuickPayResultBean == null \u6216\u8005 \u4e0d\u662fsuccess"
                cn.missfresh.module.base.utils.ac.a(r4, r2, r10)
            L_0x00d2:
                cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity.AnonymousClass3.a(cn.missfresh.module.order.orderdetails_v2.bean.OpenQuickPayResultBean):void");
        }

        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity$3$1  reason: invalid class name */
        public class AnonymousClass1 implements OpenAuthTask.a {
            final /* synthetic */ WeakReference a;
            final /* synthetic */ OpenQuickPayResultBean b;
            final /* synthetic */ AnonymousClass3 c;

            AnonymousClass1(AnonymousClass3 r5, WeakReference weakReference, OpenQuickPayResultBean openQuickPayResultBean) {
                JniLib.cV(this, r5, weakReference, openQuickPayResultBean, Integer.valueOf((int) MetricsProto.MetricsEvent.AUTOFILL_SESSION_FINISHED));
            }

            @Override // com.alipay.sdk.app.OpenAuthTask.a
            public void a(int i, String str, Bundle bundle) {
                AppMethodBeat.i(9051, false);
                d.b("QuickPayActivity", "code:" + i + "  msage:" + str);
                if (this.a == null) {
                    AppMethodBeat.o(9051);
                    return;
                }
                if (i == 9000) {
                    this.c.a.l();
                    this.c.a.h.a().a(this.b.payType, true);
                } else {
                    QuickPayActivity quickPayActivity = this.c.a;
                    quickPayActivity.b("code:" + i + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + str);
                }
                AppMethodBeat.o(9051);
            }
        }
    }

    /* renamed from: cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity$4  reason: invalid class name */
    class AnonymousClass4 implements Observer<Boolean> {
        final /* synthetic */ QuickPayActivity a;

        AnonymousClass4(QuickPayActivity quickPayActivity) {
            JniLib.cV(this, quickPayActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.VR_DISPLAY_PREFERENCE));
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(9070, false);
            a((Boolean) obj);
            AppMethodBeat.o(9070);
        }

        public void a(Boolean bool) {
            AppMethodBeat.i(9067, false);
            if (bool == null) {
                this.a.x.dismiss();
                AppMethodBeat.o(9067);
                return;
            }
            if (bool.booleanValue()) {
                QuickPayActivity.h(this.a);
            } else {
                this.a.n();
            }
            AppMethodBeat.o(9067);
        }
    }

    /* renamed from: cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity$5  reason: invalid class name */
    class AnonymousClass5 implements Observer<OpenQuickPayResultBean> {
        final /* synthetic */ QuickPayActivity a;

        AnonymousClass5(QuickPayActivity quickPayActivity) {
            JniLib.cV(this, quickPayActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACCESSIBILITY_SCREEN_MAGNIFICATION_SETTINGS));
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(9080, false);
            a((OpenQuickPayResultBean) obj);
            AppMethodBeat.o(9080);
        }

        public void a(OpenQuickPayResultBean openQuickPayResultBean) {
            AppMethodBeat.i(9078, false);
            if (this.a.j) {
                this.a.j = false;
                AppMethodBeat.o(9078);
                return;
            }
            QuickPayActivity.j(this.a);
            if (openQuickPayResultBean == null) {
                AppMethodBeat.o(9078);
                return;
            }
            if (openQuickPayResultBean.isSuccess) {
                this.a.m();
                this.a.h.a().a(openQuickPayResultBean.payType, false);
            } else {
                this.a.b("\u89e3\u7ea6\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5");
            }
            AppMethodBeat.o(9078);
        }
    }

    /* renamed from: cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity$6  reason: invalid class name */
    class AnonymousClass6 implements Observer<OpenQuickPayResultBean> {
        final /* synthetic */ QuickPayActivity a;

        AnonymousClass6(QuickPayActivity quickPayActivity) {
            JniLib.cV(this, quickPayActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SETTINGS_CLEAR_INSTANT_APP));
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(9087, false);
            a((OpenQuickPayResultBean) obj);
            AppMethodBeat.o(9087);
        }

        public void a(OpenQuickPayResultBean openQuickPayResultBean) {
            AppMethodBeat.i(9086, false);
            if (this.a.l) {
                this.a.l = false;
                AppMethodBeat.o(9086);
                return;
            }
            QuickPayActivity.l(this.a);
            if (openQuickPayResultBean == null) {
                AppMethodBeat.o(9086);
                return;
            }
            if (openQuickPayResultBean.isOpen) {
                this.a.n();
            } else {
                this.a.p();
            }
            String str = "";
            if (openQuickPayResultBean.isSuccess) {
                QuickPayActivity quickPayActivity = this.a;
                if (!openQuickPayResultBean.isOpen) {
                    str = "\u89e3\u7ea6\u6210\u529f";
                }
                quickPayActivity.d(str);
                this.a.h.a().f();
                AppMethodBeat.o(9086);
                return;
            }
            if (openQuickPayResultBean.isOpen) {
                this.a.b(str);
            } else {
                this.a.b("\u89e3\u7ea6\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5");
            }
            AppMethodBeat.o(9086);
        }
    }

    /* renamed from: cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity$7  reason: invalid class name */
    class AnonymousClass7 implements Observer<Integer> {
        final /* synthetic */ QuickPayActivity a;

        AnonymousClass7(QuickPayActivity quickPayActivity) {
            JniLib.cV(this, quickPayActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.RESET_DASHBOARD));
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(9092, false);
            a((Integer) obj);
            AppMethodBeat.o(9092);
        }

        public void a(Integer num) {
            AppMethodBeat.i(9090, false);
            if (num == null || num.intValue() < 1) {
                AppMethodBeat.o(9090);
                return;
            }
            this.a.y.setState(3);
            AppMethodBeat.o(9090);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity$8  reason: invalid class name */
    public class AnonymousClass8 implements g<OpenWxMianMiBean> {
        final /* synthetic */ QuickPayActivity a;

        AnonymousClass8(QuickPayActivity quickPayActivity) {
            JniLib.cV(this, quickPayActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_QS_CLICK));
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(9099, false);
            a((OpenWxMianMiBean) obj);
            AppMethodBeat.o(9099);
        }

        public void a(OpenWxMianMiBean openWxMianMiBean) throws Exception {
            AppMethodBeat.i(9098, false);
            if (!"QuickPayActivity".equals(OpenWxMianMiBean.from)) {
                AppMethodBeat.o(9098);
                return;
            }
            if (openWxMianMiBean == null || !openWxMianMiBean.isSuccess) {
                this.a.b("\u5fae\u4fe1\u7b7e\u7ea6\u5931\u8d25");
            } else {
                this.a.l();
                this.a.h.a().a("weixin_contract_pay", true);
            }
            AppMethodBeat.o(9098);
        }
    }

    private void r() {
        AppMethodBeat.i(9144, false);
        this.p = cn.missfresh.basiclib.utils.c.a().a(OpenWxMianMiBean.class, new AnonymousClass8(this));
        AppMethodBeat.o(9144);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.mine.qucikpay.view.QuickPayActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void s() {
        AppMethodBeat.i(9147, false);
        if (this.x == null) {
            this.x = new LoadingLikeIosDialog(this, R.style.style_dialog_transparent_bg);
        }
        this.x.show();
        AppMethodBeat.o(9147);
    }

    public void onBackPressed() {
        AppMethodBeat.i(9152, false);
        QuickPayActivity.super.onBackPressed();
        AppMethodBeat.o(9152);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        AppMethodBeat.i(9154, false);
        QuickPayActivity.super.onDestroy();
        b bVar = this.p;
        if (bVar != null) {
            bVar.dispose();
        }
        n();
        p();
        LoadingLikeIosDialog loadingLikeIosDialog = this.x;
        if (loadingLikeIosDialog != null) {
            loadingLikeIosDialog.dismiss();
        }
        AppMethodBeat.o(9154);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(9157, true);
        if (view.getId() == R.id.tv_cancel) {
            this.y.setState(5);
        } else if (view.getId() == R.id.tv_close) {
            StatisticsManager.ah("click_close_no_pwd", new Object[0]);
            this.y.setState(5);
            d(true);
            this.h.d();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(9157);
    }
}
