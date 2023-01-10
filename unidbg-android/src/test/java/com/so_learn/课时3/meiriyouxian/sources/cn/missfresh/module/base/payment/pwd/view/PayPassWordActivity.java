package cn.missfresh.module.base.payment.pwd.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.common.providers.ILoginService;
import cn.missfresh.module.base.payment.pwd.b.b;
import cn.missfresh.module.base.payment.pwd.widget.GridPassWordView;
import cn.missfresh.module.base.support.dialog.f;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.android.arouter.b.a;
import com.bangcle.andjni.JniLib;

public class PayPassWordActivity extends BaseFragmentActivity implements b {
    private b a;
    private GridPassWordView j;
    private GridPassWordView k;
    private View l;
    private View m;
    private Handler n = new Handler();

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.payment.pwd.view.PayPassWordActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements GridPassWordView.a {
        final /* synthetic */ PayPassWordActivity a;

        AnonymousClass1(PayPassWordActivity payPassWordActivity) {
            JniLib.cV(this, payPassWordActivity, 282);
        }

        @Override // cn.missfresh.module.base.payment.pwd.widget.GridPassWordView.a
        public void a(String str) {
            JniLib.cV(this, str, 280);
        }

        @Override // cn.missfresh.module.base.payment.pwd.widget.GridPassWordView.a
        public void b(String str) {
            JniLib.cV(this, str, 281);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.payment.pwd.view.PayPassWordActivity$2  reason: invalid class name */
    public class AnonymousClass2 implements GridPassWordView.a {
        final /* synthetic */ PayPassWordActivity a;

        AnonymousClass2(PayPassWordActivity payPassWordActivity) {
            JniLib.cV(this, payPassWordActivity, 285);
        }

        @Override // cn.missfresh.module.base.payment.pwd.widget.GridPassWordView.a
        public void a(String str) {
            JniLib.cV(this, str, 283);
        }

        @Override // cn.missfresh.module.base.payment.pwd.widget.GridPassWordView.a
        public void b(String str) {
            JniLib.cV(this, str, 284);
        }
    }

    /* renamed from: cn.missfresh.module.base.payment.pwd.view.PayPassWordActivity$3  reason: invalid class name */
    class AnonymousClass3 implements DialogInterface.OnDismissListener {
        final /* synthetic */ PayPassWordActivity a;

        AnonymousClass3(PayPassWordActivity payPassWordActivity) {
            JniLib.cV(this, payPassWordActivity, 287);
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            JniLib.cV(this, dialogInterface, 286);
        }
    }

    public static void a(Activity activity, String str, String str2) {
        JniLib.cV(activity, str, str2, 296);
    }

    private void r() {
        JniLib.cV(this, 297);
    }

    private void t() {
        JniLib.cV(this, 298);
    }

    private boolean u() {
        return JniLib.cZ(this, 299);
    }

    private void v() {
        JniLib.cV(this, 300);
    }

    private void w() {
        JniLib.cV(this, 301);
    }

    private void x() {
        JniLib.cV(this, 302);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void J_() {
        JniLib.cV(this, 289);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.b
    public void a() {
        JniLib.cV(this, 290);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.b
    public void a_(String str) {
        JniLib.cV(this, str, 291);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.b
    public void c_() {
        JniLib.cV(this, 292);
    }

    public void d(String str) {
        JniLib.cV(this, str, 293);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onDestroy() {
        JniLib.cV(this, 294);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), 295);
    }

    public PayPassWordActivity() {
        AppMethodBeat.i(16569, false);
        AppMethodBeat.o(16569);
    }

    static /* synthetic */ boolean a(PayPassWordActivity payPassWordActivity) {
        AppMethodBeat.i(16615, false);
        boolean u = payPassWordActivity.u();
        AppMethodBeat.o(16615);
        return u;
    }

    static /* synthetic */ void b(PayPassWordActivity payPassWordActivity) {
        AppMethodBeat.i(16617, false);
        payPassWordActivity.v();
        AppMethodBeat.o(16617);
    }

    static /* synthetic */ void c(PayPassWordActivity payPassWordActivity) {
        AppMethodBeat.i(16620, false);
        payPassWordActivity.t();
        AppMethodBeat.o(16620);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(16574, false);
        super.onCreate(bundle);
        setContentView(R.layout.activity_password);
        this.e.getBottomDivider().setVisibility(8);
        r();
        s();
        AppMethodBeat.o(16574);
    }

    private void s() {
        AppMethodBeat.i(16578, false);
        this.e.setLeftButtonVisibility(0);
        this.e.setCenterVisibility(0);
        this.e.setCenterTxt("\u8bbe\u7f6e\u5bc6\u7801");
        this.j = (GridPassWordView) findViewById(R.id.cv_input_password);
        this.k = (GridPassWordView) findViewById(R.id.cv_input_password_again);
        this.m = findViewById(R.id.tv_password_error_hint);
        this.l = findViewById(R.id.btn_password_commit);
        this.l.setOnClickListener(this);
        this.j.setOnPasswordChangedListener(new AnonymousClass1(this));
        this.k.setOnPasswordChangedListener(new AnonymousClass2(this));
        AppMethodBeat.o(16578);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(16585, true);
        super.onClick(view);
        if (view.getId() == R.id.btn_password_commit) {
            this.a.c(this.j.getPassWord());
        }
        AppMethodBeat.o(16585);
    }

    private boolean d(boolean z) {
        AppMethodBeat.i(16588, false);
        this.l.setEnabled(z);
        this.l.setBackgroundResource(!z ? R.drawable.shape_corners_4_c6 : R.drawable.shape_corners_4_red_ff);
        AppMethodBeat.o(16588);
        return z;
    }

    public void c(String str) {
        AppMethodBeat.i(16608, false);
        d.d(this.b, "\u7ed1\u5b9a\u6210\u529f");
        if (!isFinishing()) {
            l();
            f fVar = new f(this.f, R.drawable.ic_binding_success, "\u8bbe\u7f6e\u6210\u529f");
            ILoginService iLoginService = (ILoginService) a.a().a("/user/login_service").navigation();
            if (iLoginService != null) {
                iLoginService.a(2);
            }
            fVar.setOnDismissListener(new AnonymousClass3(this));
            fVar.show();
            this.n.postDelayed(new AnonymousClass4(this, fVar), 2000);
        }
        AppMethodBeat.o(16608);
    }

    /* renamed from: cn.missfresh.module.base.payment.pwd.view.PayPassWordActivity$4  reason: invalid class name */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ f a;
        final /* synthetic */ PayPassWordActivity b;

        AnonymousClass4(PayPassWordActivity payPassWordActivity, f fVar) {
            JniLib.cV(this, payPassWordActivity, fVar, 288);
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(16561, false);
            this.a.dismiss();
            AppMethodBeat.o(16561);
        }
    }
}
