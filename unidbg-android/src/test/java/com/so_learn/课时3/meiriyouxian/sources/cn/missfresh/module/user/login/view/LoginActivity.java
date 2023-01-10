package cn.missfresh.module.user.login.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import cn.missfresh.basiclib.utils.permission.c;
import cn.missfresh.module.base.base.ActionBarActivity;
import cn.missfresh.module.base.bean.BindingInfo;
import cn.missfresh.module.base.bean.LoginByWeixin;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.common.ministart.model.a;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.support.dialog.BaseTipDialog;
import cn.missfresh.module.base.support.dialog.GraphCodeDialog;
import cn.missfresh.module.base.support.dialog.TipsDialog;
import cn.missfresh.module.base.support.dialog.g;
import cn.missfresh.module.base.utils.ac;
import cn.missfresh.module.base.utils.as;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.login.presenter.MinePresenter;
import cn.missfresh.module.user.login.presenter.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import com.tencent.imsdk.BaseConstants;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class LoginActivity extends ActionBarActivity implements b.a, b {
    GraphCodeDialog j;
    private MinePresenter k;
    private View l;
    private TextView m;
    private b n;
    private TextView o;
    private boolean p;
    private ImageView v;
    private BroadcastReceiver w = new 1(this);

    /* renamed from: cn.missfresh.module.user.login.view.LoginActivity$5  reason: invalid class name */
    class AnonymousClass5 implements BaseTipDialog.a {
        final /* synthetic */ LoginActivity a;

        AnonymousClass5(LoginActivity loginActivity) {
            JniLib.cV(this, loginActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.FIELD_AUTOFILL_NUM_DATASETS));
        }

        @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog.a
        public void a(int i, Object obj) {
        }
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public boolean F_() {
        return true;
    }

    public void a(int i, String str) {
    }

    public void a(Object obj) {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.login.view.LoginActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public Activity b() {
        return this;
    }

    public void d() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.login.view.LoginActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.ActionBarActivity, cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        LoginActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    public LoginActivity() {
        AppMethodBeat.i(8436, false);
        AppMethodBeat.o(8436);
    }

    static /* synthetic */ c b(LoginActivity loginActivity) {
        AppMethodBeat.i(8516, false);
        c B = loginActivity.B();
        AppMethodBeat.o(8516);
        return B;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: cn.missfresh.module.user.login.view.LoginActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(8442, false);
        super.onCreate(bundle);
        setContentView(R.layout.user_activity_login);
        this.k = new MinePresenter(this);
        int intExtra = getIntent().getIntExtra("login_type", 1);
        this.e.setVisibility(8);
        if (intExtra == 0) {
            this.k.b();
        }
        this.l = findViewById(R.id.cl_root_view);
        this.n = new b(this);
        this.o = (TextView) findViewById(R.id.getCheckCode);
        this.o.setOnClickListener(this);
        this.v = (ImageView) findViewById(R.id.iv_protocol);
        this.v.setOnClickListener(this);
        findViewById(R.id.ll_login_back).setOnClickListener(this);
        View findViewById = findViewById(R.id.btn_login);
        findViewById.setOnClickListener(this);
        findViewById.setOnTouchListener(new 3(this, new GestureDetector((Context) this, (GestureDetector.OnGestureListener) new 2(this, findViewById))));
        EventBus.getDefault().register(this);
        EditText editText = (EditText) findViewById(R.id.phoneNumber_et);
        editText.setOnClickListener(new 4(this));
        this.a.add(editText);
        this.a.add(findViewById(R.id.checkCode_et));
        this.m = (TextView) findViewById(R.id.userProtocol);
        this.m.setVisibility(0);
        j.a((Activity) this, this.m, getResources().getString(R.string.login_user_privacy_content));
        findViewById(R.id.tv_wx_login).setOnClickListener(this);
        as.a((Activity) this, ContextCompat.getColor(this, R.color.color_f3f5f6));
        StatisticsManager.b("page_view");
        AppMethodBeat.o(8442);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        AppMethodBeat.i(8446, false);
        super.onResume();
        AppMethodBeat.o(8446);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: cn.missfresh.module.user.login.view.LoginActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void z() {
        AppMethodBeat.i(8448, false);
        Intent intent = getIntent();
        if (intent == null) {
            AppMethodBeat.o(8448);
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("login_permission_risk", false);
        if (!s() && booleanExtra) {
            TipsDialog.l().a(getString(R.string.login_invalid_no_privacy)).c("\u786e\u5b9a").a(new AnonymousClass5(this)).a((FragmentActivity) this).k();
        }
        AppMethodBeat.o(8448);
    }

    private boolean s() {
        AppMethodBeat.i(8451, false);
        boolean a = A().a();
        AppMethodBeat.o(8451);
        return a;
    }

    @Subscribe
    public void onHandleEvent(BindingInfo bindingInfo) {
        AppMethodBeat.i(8454, false);
        d.d("LoginActivity", "handlerPhoneBindResEvent...bindingInfo:" + bindingInfo);
        if (bindingInfo == null) {
            AppMethodBeat.o(8454);
        } else if (!getClass().getSimpleName().equals(bindingInfo.getFromPage())) {
            AppMethodBeat.o(8454);
        } else {
            c(true);
            if (bindingInfo.getResult() == 1) {
                this.k.a(true);
            } else if (bindingInfo.getResult() == 2) {
                e.g("");
            }
            AppMethodBeat.o(8454);
        }
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onDestroy() {
        AppMethodBeat.i(8455, false);
        super.onDestroy();
        x();
        EventBus.getDefault().unregister(this);
        this.k.d();
        this.n.c();
        AppMethodBeat.o(8455);
    }

    public void a(int i) {
        AppMethodBeat.i(8458, false);
        if (i == 0) {
            t();
            finish();
        } else if (i == 1) {
            r();
            finish();
        } else if (i == 2) {
            t();
            finish();
        }
        AppMethodBeat.o(8458);
    }

    public void r() {
        AppMethodBeat.i(8460, false);
        t();
        if (!e.ae()) {
            e.s(true);
        }
        AppMethodBeat.o(8460);
    }

    @Subscribe
    public void onHandleEvent(LoginByWeixin loginByWeixin) {
        AppMethodBeat.i(8462, false);
        if (e.ae() && !e.y()) {
            e.s(false);
            o.a(false, LoginActivity.class.getSimpleName());
        }
        AppMethodBeat.o(8462);
    }

    private void t() {
        AppMethodBeat.i(8463, false);
        new a().a();
        e.a(cn.missfresh.module.base.common.c.a.a > 0 ? cn.missfresh.module.base.common.c.a.a : e.af());
        c(true);
        this.k.c();
        AppMethodBeat.o(8463);
    }

    public void Z_() {
        AppMethodBeat.i(8466, false);
        c(true);
        cn.missfresh.ui.a.a.a("\u5fae\u4fe1\u767b\u5f55\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5");
        ac.a("\u7528\u6237\u6a21\u5757", "\u5fae\u4fe1\u767b\u5f55\u5931\u8d25", "");
        AppMethodBeat.o(8466);
    }

    public void aa_() {
        AppMethodBeat.i(8468, false);
        c(true);
        cn.missfresh.ui.a.a.a("\u60a8\u6ca1\u6709\u5b89\u88c5\u5fae\u4fe1\uff0c\u8bf7\u5b89\u88c5\u540e\u518d\u8bd5");
        ac.a("\u7528\u6237\u6a21\u5757", "\u5fae\u4fe1\u767b\u5f55\u5524\u8d77\u5fae\u4fe1\u5931\u8d25", "\u60a8\u6ca1\u6709\u5b89\u88c5\u5fae\u4fe1\uff0c\u8bf7\u5b89\u88c5\u540e\u518d\u8bd5");
        AppMethodBeat.o(8468);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: cn.missfresh.module.user.login.view.LoginActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(8471, true);
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.tv_wx_login) {
            if (!this.v.isSelected()) {
                cn.missfresh.ui.a.a.b(getResources().getString(R.string.login_please_read_add_agree_agreement));
                AppMethodBeat.o(8471);
                return;
            } else if (u()) {
                AppMethodBeat.o(8471);
                return;
            } else {
                b(true);
                v();
                this.k.b();
                StatisticsManager.b("click_wechatLogin");
            }
        } else if (id == R.id.getCheckCode) {
            this.n.a(this, false);
        } else if (id == R.id.btn_login) {
            if (u()) {
                AppMethodBeat.o(8471);
                return;
            } else {
                this.n.b();
                StatisticsManager.b("click_login");
            }
        } else if (id == R.id.ll_login_back) {
            onBackPressed();
        } else if (id == R.id.iv_protocol) {
            ImageView imageView = this.v;
            imageView.setSelected(true ^ imageView.isSelected());
            this.n.a();
            if (this.v.isSelected()) {
                StatisticsManager.b("chose_privacy_text", "sighn_in", new Object[0]);
            }
        }
        AppMethodBeat.o(8471);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.login.view.LoginActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private boolean u() {
        AppMethodBeat.i(8473, false);
        if (!s()) {
            TipsDialog.l().a(getString(R.string.login_invalid_no_permissions)).b("\u53d6\u6d88").c("\u6388\u6743\u5e76\u91cd\u65b0\u542f\u52a8").a(new AnonymousClass6(this)).a((FragmentActivity) this).k();
            AppMethodBeat.o(8473);
            return true;
        }
        AppMethodBeat.o(8473);
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.login.view.LoginActivity$6  reason: invalid class name */
    public class AnonymousClass6 implements BaseTipDialog.a {
        final /* synthetic */ LoginActivity a;

        AnonymousClass6(LoginActivity loginActivity) {
            JniLib.cV(this, loginActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.AUTOFILL_FILL_UI));
        }

        @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog.a
        public void a(int i, Object obj) {
            AppMethodBeat.i(8414, false);
            if (i == 101) {
                this.a.p = true;
                LoginActivity.b(this.a).a();
                d.c("LoginActivity", " agree permission, restart app");
            } else {
                d.c("LoginActivity", " no agree permission");
            }
            AppMethodBeat.o(8414);
        }
    }

    private void v() {
        AppMethodBeat.i(8475, false);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getWXActionLogin());
        registerReceiver(this.w, intentFilter);
        AppMethodBeat.o(8475);
    }

    private void x() {
        AppMethodBeat.i(8477, false);
        try {
            unregisterReceiver(this.w);
        } catch (Exception unused) {
        }
        AppMethodBeat.o(8477);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void J_() {
        AppMethodBeat.i(8479, false);
        super.J_();
        cn.missfresh.module.base.base.c c = cn.missfresh.module.base.manager.a.a().c();
        if (c instanceof cn.missfresh.module.base.base.c) {
            c.w_();
        }
        o.c(300);
        AppMethodBeat.o(8479);
    }

    public View a() {
        return this.l;
    }

    public void c() {
        AppMethodBeat.i(8485, false);
        b(true);
        AppMethodBeat.o(8485);
    }

    public void m_(String str) {
        AppMethodBeat.i(8487, false);
        c(true);
        cn.missfresh.ui.a.a.a(str);
        AppMethodBeat.o(8487);
    }

    public void e() {
        AppMethodBeat.i(8489, false);
        this.o.setEnabled(true);
        this.o.setTextColor(getResources().getColor(R.color.black_474346));
        this.o.setAlpha(1.0f);
        AppMethodBeat.o(8489);
    }

    public void Y_() {
        AppMethodBeat.i(8490, false);
        this.o.setEnabled(false);
        this.o.setTextColor(getResources().getColor(R.color.color_969696));
        this.o.setAlpha(0.65f);
        AppMethodBeat.o(8490);
    }

    public void h() {
        AppMethodBeat.i(8493, false);
        b(true);
        this.n.a(false, "");
        AppMethodBeat.o(8493);
    }

    public void c(String str) {
        AppMethodBeat.i(8500, false);
        c(true);
        AppMethodBeat.o(8500);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.login.view.LoginActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void d(String str) {
        AppMethodBeat.i(BaseConstants.ERR_SDK_GROUP_INVALID_INTRODUCTION, false);
        this.j = new GraphCodeDialog(this);
        this.j.a(str, new AnonymousClass7(this, str));
        AppMethodBeat.o(BaseConstants.ERR_SDK_GROUP_INVALID_INTRODUCTION);
    }

    /* renamed from: cn.missfresh.module.user.login.view.LoginActivity$7  reason: invalid class name */
    class AnonymousClass7 implements g {
        final /* synthetic */ String a;
        final /* synthetic */ LoginActivity b;

        AnonymousClass7(LoginActivity loginActivity, String str) {
            JniLib.cV(this, loginActivity, str, Integer.valueOf((int) MetricsProto.MetricsEvent.FIELD_AUTOFILL_FILTERTEXT_LEN));
        }

        @Override // cn.missfresh.module.base.support.dialog.g
        public void a(String str, String str2) {
            AppMethodBeat.i(8424, false);
            if (this.a.equals(str)) {
                this.b.n.b(str2);
            }
            AppMethodBeat.o(8424);
        }
    }

    public boolean i() {
        AppMethodBeat.i(BaseConstants.ERR_SDK_GROUP_INVALID_NAME_CARD, false);
        boolean isSelected = this.v.isSelected();
        AppMethodBeat.o(BaseConstants.ERR_SDK_GROUP_INVALID_NAME_CARD);
        return isSelected;
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onBackPressed() {
        AppMethodBeat.i(BaseConstants.ERR_SDK_GROUP_INVITE_SUPER_DENY, false);
        GraphCodeDialog graphCodeDialog = this.j;
        if (graphCodeDialog == null || !graphCodeDialog.isShowing()) {
            super.onBackPressed();
        } else {
            this.j.onBackPressed();
        }
        AppMethodBeat.o(BaseConstants.ERR_SDK_GROUP_INVITE_SUPER_DENY);
    }
}
