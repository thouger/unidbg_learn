package cn.missfresh.module.user.login.view;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import cn.missfresh.module.base.bean.BindingInfo;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.module.base.support.dialog.LoadingLikeIosDialog;
import cn.missfresh.module.base.utils.ac;
import cn.missfresh.module.mvvm.BaseMVVMActivity;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.login.presenter.MinePresenter;
import cn.missfresh.module.user.login.viewmodel.QuickLoginViewModel;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.f;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import com.netease.nis.quicklogin.QuickLogin;
import com.netease.nis.quicklogin.helper.UnifyUiConfig;
import com.netease.nis.quicklogin.listener.ClickEventListener;
import com.netease.nis.quicklogin.listener.LoginListener;
import com.netease.nis.quicklogin.listener.QuickLoginPreMobileListener;
import com.netease.nis.quicklogin.listener.QuickLoginTokenListener;
import com.umeng.analytics.pro.ai;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import java.lang.ref.WeakReference;

public class QuickLoginActivity extends BaseMVVMActivity implements b {
    private QuickLogin a;
    private final String b = "\u6211\u5df2\u8ba4\u771f\u9605\u8bfb\u3001\u7406\u89e3\u5e76\u540c\u610f";
    private final String c = "\u300a\u6bcf\u65e5\u4f18\u9c9c\u7528\u6237\u534f\u8bae\u300b";
    private final String d = "\u300a\u6bcf\u65e5\u4f18\u9c9c\u9690\u79c1\u653f\u7b56\u300b";
    private final String e = "";
    private MinePresenter f;
    private LoadingLikeIosDialog h;
    private int i;
    private QuickLoginViewModel j;
    private b k;

    /* access modifiers changed from: protected */
    public void a() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        JniLib.cV(this, bundle, Integer.valueOf((int) MetricsProto.MetricsEvent.FIELD_AUTOFILL_NUM_VIEWS_FILLED));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.login.view.QuickLoginActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void onWindowFocusChanged(boolean z) {
        QuickLoginActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    static /* synthetic */ void a(QuickLoginActivity quickLoginActivity, String str, String str2) {
        AppMethodBeat.i(8659, false);
        quickLoginActivity.c(str, str2);
        AppMethodBeat.o(8659);
    }

    static /* synthetic */ int b(QuickLoginActivity quickLoginActivity) {
        int i = quickLoginActivity.i;
        quickLoginActivity.i = i + 1;
        return i;
    }

    static /* synthetic */ void b(QuickLoginActivity quickLoginActivity, String str, String str2) {
        AppMethodBeat.i(8661, false);
        quickLoginActivity.d(str, str2);
        AppMethodBeat.o(8661);
    }

    static /* synthetic */ boolean d(QuickLoginActivity quickLoginActivity) {
        AppMethodBeat.i(8666, false);
        boolean t = quickLoginActivity.t();
        AppMethodBeat.o(8666);
        return t;
    }

    static /* synthetic */ void g(QuickLoginActivity quickLoginActivity) {
        AppMethodBeat.i(8672, false);
        quickLoginActivity.r();
        AppMethodBeat.o(8672);
    }

    /* access modifiers changed from: protected */
    public int M_() {
        return R.layout.user_activity_quick_login;
    }

    /* access modifiers changed from: protected */
    public void f() {
        AppMethodBeat.i(8618, false);
        this.j = (QuickLoginViewModel) a(QuickLoginViewModel.class);
        AppMethodBeat.o(8618);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.login.view.QuickLoginActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void g() {
        AppMethodBeat.i(8620, false);
        cn.missfresh.utils.a.d.b("QuickLoginActivity", "initData");
        this.a = QuickLogin.getInstance(getApplicationContext(), "04aa8f606f9b460ea267c48928c15378");
        this.a.setUnifyUiConfig(a(getApplicationContext()));
        this.f = new MinePresenter(this);
        this.h = new LoadingLikeIosDialog(this, R.style.style_dialog_transparent_bg);
        this.a.setPrefetchNumberTimeout(PathInterpolatorCompat.MAX_NUM_POINTS);
        this.a.setFetchNumberTimeout(PathInterpolatorCompat.MAX_NUM_POINTS);
        this.k = new b(this);
        EventBus.getDefault().register(this);
        l();
        AppMethodBeat.o(8620);
    }

    /* renamed from: cn.missfresh.module.user.login.view.QuickLoginActivity$1  reason: invalid class name */
    class AnonymousClass1 implements Observer<Integer> {
        final /* synthetic */ QuickLoginActivity a;

        AnonymousClass1(QuickLoginActivity quickLoginActivity) {
            JniLib.cV(this, quickLoginActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.AUTOFILL_AUTHENTICATED));
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(8530, false);
            a((Integer) obj);
            AppMethodBeat.o(8530);
        }

        public void a(Integer num) {
            AppMethodBeat.i(8527, false);
            this.a.h.dismiss();
            if (num.intValue() != 1) {
                o.b(1);
                this.a.finish();
            } else {
                StatisticsManager.ag("finish_yjdl_login", ai.e, "yjdl_login");
            }
            AppMethodBeat.o(8527);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.login.view.QuickLoginActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void k() {
        AppMethodBeat.i(8621, false);
        this.j.a().observe(this, new AnonymousClass1(this));
        AppMethodBeat.o(8621);
    }

    public void l() {
        AppMethodBeat.i(8622, false);
        this.h.show();
        this.a.prefetchMobileNumber(new a(this));
        AppMethodBeat.o(8622);
    }

    private void c(String str, String str2) {
        AppMethodBeat.i(8624, false);
        cn.missfresh.utils.a.d.b("QuickLoginActivity", "onGetMobileNumberSuccess");
        this.a.onePass(new e(this));
        StatisticsManager.ag("exposure_yjdl_login", ai.e, "yjdl_login");
        AppMethodBeat.o(8624);
    }

    private void d(String str, String str2) {
        AppMethodBeat.i(8626, false);
        ac.a("\u7528\u6237\u6a21\u5757", "\u4e00\u952e\u767b\u5f55\u5931\u8d25", "onGetMobileNumberError");
        this.h.dismiss();
        finish();
        o.b(1);
        AppMethodBeat.o(8626);
    }

    public void a(String str, String str2) {
        AppMethodBeat.i(8627, false);
        this.j.a(str, str2);
        AppMethodBeat.o(8627);
    }

    public void b(String str, String str2) {
        AppMethodBeat.i(8630, false);
        ac.a("\u7528\u6237\u6a21\u5757", "\u4e00\u952e\u767b\u5f55\u5931\u8d25", "onGetTokenError");
        this.h.dismiss();
        cn.missfresh.ui.a.a.a("\u7b2c\u4e09\u65b9\u63a5\u53e3\u8c03\u7528\u5931\u8d25\uff0c\u5df2\u5e2e\u60a8\u5207\u6362\u767b\u5f55\u65b9\u5f0f");
        o.b(1);
        this.a.quitActivity();
        finish();
        AppMethodBeat.o(8630);
    }

    public void m() {
        AppMethodBeat.i(8632, false);
        finish();
        AppMethodBeat.o(8632);
    }

    private UnifyUiConfig a(Context context) {
        AppMethodBeat.i(8635, false);
        View inflate = LayoutInflater.from(context).inflate(R.layout.user_layout_one_pass_other_login, (ViewGroup) null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        layoutParams.addRule(14);
        layoutParams.bottomMargin = f.c(context, 100);
        inflate.setLayoutParams(layoutParams);
        inflate.findViewById(R.id.iv_wx_login).setOnClickListener(new MyClickListener(this));
        inflate.findViewById(R.id.iv_phone_login).setOnClickListener(new MyClickListener(this));
        TextView textView = new TextView(context);
        textView.setTextSize(12.0f);
        textView.setTextColor(ContextCompat.getColor(context, R.color.color_969696));
        textView.setText(context.getResources().getString(R.string.user_one_pass_notice));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(12);
        layoutParams2.addRule(14);
        layoutParams2.bottomMargin = f.c(context, 60);
        layoutParams2.leftMargin = f.c(context, 16);
        layoutParams.rightMargin = f.c(context, 16);
        textView.setLayoutParams(layoutParams2);
        UnifyUiConfig build = new UnifyUiConfig.Builder().setStatusBarColor(ContextCompat.getColor(context, R.color.white_f5)).setStatusBarDarkColor(true).setNavigationBackgroundColor(ContextCompat.getColor(context, R.color.white)).setNavigationTitle(context.getResources().getString(R.string.user_login)).setLogoIconName("ic_round_logo").setLogoTopYOffset(37).setMaskNumberSize(22).setMaskNumberColor(ContextCompat.getColor(context, R.color.color_474245)).setMaskNumberTopYOffset(121).setSloganTopYOffset(151).setSloganSize(12).setSloganColor(ContextCompat.getColor(context, R.color.color_969696)).setLoginBtnBackgroundRes("user_shape_8_ff4891").setLoginBtnText(context.getResources().getString(R.string.user_one_pass)).setLoginBtnHeight(44).setLoginBtnTopYOffset(203).setLoginBtnTextDpSize(16).setLoginBtnWidth(520).setPrivacyTopYOffset(260).setPrivacyTextColor(ContextCompat.getColor(context, R.color.color_969696)).setPrivacyProtocolColor(ContextCompat.getColor(context, R.color.color_ff4891)).setPrivacyDpSize(11).setPrivacyTextStart("\u6211\u5df2\u8ba4\u771f\u9605\u8bfb\u3001\u7406\u89e3\u5e76\u540c\u610f").setPrivacyMarginRight(32).setPrivacyXOffset(32).setPrivacyState(false).setCheckedImageName("ic_address_selected").setUnCheckedImageName("ic_address_normal").setProtocolText("\u300a\u6bcf\u65e5\u4f18\u9c9c\u7528\u6237\u534f\u8bae\u300b").setProtocolLink(i.bW).setProtocol2Text("\u300a\u6bcf\u65e5\u4f18\u9c9c\u9690\u79c1\u653f\u7b56\u300b").setProtocol2Link(i.bY).setPrivacyTextEnd("").setLoginListener(new d(this)).setClickEventListener(new c(this)).addCustomView(textView, "notice", 0, null).addCustomView(inflate, "other_login", 0, null).build(context);
        AppMethodBeat.o(8635);
        return build;
    }

    public void a(int i) {
        AppMethodBeat.i(8638, false);
        if (i == 0) {
            p();
            this.a.quitActivity();
        } else if (i == 1) {
            n();
            this.a.quitActivity();
            finish();
        } else if (i == 2) {
            p();
            this.a.quitActivity();
            finish();
        }
        AppMethodBeat.o(8638);
    }

    public void n() {
        AppMethodBeat.i(8640, false);
        this.a.quitActivity();
        p();
        AppMethodBeat.o(8640);
    }

    public void Z_() {
        AppMethodBeat.i(8641, false);
        cn.missfresh.ui.a.a.a("\u5fae\u4fe1\u767b\u5f55\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5");
        ac.a("\u7528\u6237\u6a21\u5757", "\u5fae\u4fe1\u767b\u5f55\u5931\u8d25", "loginWxFail");
        AppMethodBeat.o(8641);
    }

    public void aa_() {
        AppMethodBeat.i(8642, false);
        cn.missfresh.ui.a.a.a("\u60a8\u6ca1\u6709\u5b89\u88c5\u5fae\u4fe1\uff0c\u8bf7\u5b89\u88c5\u540e\u518d\u8bd5");
        ac.a("\u7528\u6237\u6a21\u5757", "\u5fae\u4fe1\u767b\u5f55\u5524\u8d77\u5fae\u4fe1\u5931\u8d25", "\u60a8\u6ca1\u6709\u5b89\u88c5\u5fae\u4fe1\uff0c\u8bf7\u5b89\u88c5\u540e\u518d\u8bd5");
        AppMethodBeat.o(8642);
    }

    private void p() {
        AppMethodBeat.i(8644, false);
        new cn.missfresh.module.base.common.ministart.model.a().a();
        cn.missfresh.module.base.manager.e.a(cn.missfresh.module.base.common.c.a.a > 0 ? cn.missfresh.module.base.common.c.a.a : cn.missfresh.module.base.manager.e.af());
        finish();
        AppMethodBeat.o(8644);
    }

    private void r() {
        AppMethodBeat.i(8646, false);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getWXActionLogin());
        registerReceiver(this.k, intentFilter);
        AppMethodBeat.o(8646);
    }

    private void s() {
        AppMethodBeat.i(8648, false);
        try {
            unregisterReceiver(this.k);
        } catch (Exception unused) {
        }
        AppMethodBeat.o(8648);
    }

    private boolean t() {
        AppMethodBeat.i(8650, false);
        if (!u()) {
            com.alibaba.android.arouter.b.a.a().a("/user/onpass_login_notice").navigation();
            AppMethodBeat.o(8650);
            return true;
        }
        AppMethodBeat.o(8650);
        return false;
    }

    private boolean u() {
        AppMethodBeat.i(8651, false);
        boolean a2 = A().a();
        AppMethodBeat.o(8651);
        return a2;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        AppMethodBeat.i(8652, false);
        QuickLoginActivity.super.onDestroy();
        cn.missfresh.utils.a.d.b("QuickLoginActivity", "QuickLoginActivity onDestroy");
        EventBus.getDefault().unregister(this);
        s();
        this.h.dismiss();
        this.f.d();
        AppMethodBeat.o(8652);
    }

    @Subscribe
    public void onHandleEvent(BindingInfo bindingInfo) {
        AppMethodBeat.i(8656, false);
        cn.missfresh.utils.a.d.d("QuickLoginActivity", "handlerPhoneBindResEvent...bindingInfo:" + bindingInfo);
        if (bindingInfo == null) {
            AppMethodBeat.o(8656);
            return;
        }
        if (bindingInfo.getResult() == 2) {
            cn.missfresh.module.base.manager.e.g("");
            this.a.quitActivity();
            finish();
        }
        AppMethodBeat.o(8656);
    }

    /* access modifiers changed from: private */
    public static class a extends QuickLoginPreMobileListener {
        private WeakReference<QuickLoginActivity> a;

        public a(QuickLoginActivity quickLoginActivity) {
            AppMethodBeat.i(8534, false);
            this.a = new WeakReference<>(quickLoginActivity);
            AppMethodBeat.o(8534);
        }

        @Override // com.netease.nis.quicklogin.listener.QuickLoginListener
        public void onGetMobileNumberSuccess(String str, String str2) {
            AppMethodBeat.i(8537, false);
            WeakReference<QuickLoginActivity> weakReference = this.a;
            if (!(weakReference == null || weakReference.get() == null)) {
                QuickLoginActivity.a(this.a.get(), str, str2);
            }
            AppMethodBeat.o(8537);
        }

        @Override // com.netease.nis.quicklogin.listener.QuickLoginListener
        public void onGetMobileNumberError(String str, String str2) {
            AppMethodBeat.i(8541, false);
            cn.missfresh.module.user.login.d.a.a(str, str2);
            ac.a("\u7528\u6237\u6a21\u5757", "\u4e00\u952e\u767b\u5f55\u5931\u8d25", "onGetMobileNumberError");
            WeakReference<QuickLoginActivity> weakReference = this.a;
            if (!(weakReference == null || weakReference.get() == null)) {
                QuickLoginActivity.b(this.a.get(), str, str2);
            }
            AppMethodBeat.o(8541);
        }
    }

    /* access modifiers changed from: private */
    public static class e extends QuickLoginTokenListener {
        private WeakReference<QuickLoginActivity> a;

        public e(QuickLoginActivity quickLoginActivity) {
            AppMethodBeat.i(8600, false);
            this.a = new WeakReference<>(quickLoginActivity);
            AppMethodBeat.o(8600);
        }

        @Override // com.netease.nis.quicklogin.listener.QuickLoginListener
        public void onGetTokenSuccess(String str, String str2) {
            AppMethodBeat.i(8603, false);
            WeakReference<QuickLoginActivity> weakReference = this.a;
            if (!(weakReference == null || weakReference.get() == null)) {
                this.a.get().a(str, str2);
            }
            AppMethodBeat.o(8603);
        }

        @Override // com.netease.nis.quicklogin.listener.QuickLoginListener
        public void onGetTokenError(String str, String str2) {
            AppMethodBeat.i(8606, false);
            cn.missfresh.module.user.login.d.a.b(str, str2);
            WeakReference<QuickLoginActivity> weakReference = this.a;
            if (!(weakReference == null || weakReference.get() == null)) {
                this.a.get().b(str, str2);
            }
            AppMethodBeat.o(8606);
        }

        @Override // com.netease.nis.quicklogin.listener.QuickLoginTokenListener
        public void onCancelGetToken() {
            AppMethodBeat.i(8608, false);
            super.onCancelGetToken();
            WeakReference<QuickLoginActivity> weakReference = this.a;
            if (!(weakReference == null || weakReference.get() == null)) {
                this.a.get().m();
            }
            AppMethodBeat.o(8608);
        }
    }

    /* access modifiers changed from: private */
    public static class c implements ClickEventListener {
        private WeakReference<QuickLoginActivity> a;

        public c(QuickLoginActivity quickLoginActivity) {
            JniLib.cV(this, quickLoginActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.FIELD_AUTOFILL_NUM_VALUES));
        }

        @Override // com.netease.nis.quicklogin.listener.ClickEventListener
        public void onClick(int i) {
            AppMethodBeat.i(8549, true);
            WeakReference<QuickLoginActivity> weakReference = this.a;
            if (weakReference == null || weakReference.get() == null) {
                AppMethodBeat.o(8549);
            } else if (i == 3) {
                this.a.get().finish();
                AppMethodBeat.o(8549);
            } else {
                if (i == 2) {
                    QuickLoginActivity.b(this.a.get());
                } else if (i == 4) {
                    StatisticsManager.ag("click_yjdl_login", ai.e, "yjdl_login");
                }
                AppMethodBeat.o(8549);
            }
        }
    }

    /* access modifiers changed from: private */
    public static class d extends LoginListener {
        private WeakReference<QuickLoginActivity> a;

        public d(QuickLoginActivity quickLoginActivity) {
            AppMethodBeat.i(8591, false);
            this.a = new WeakReference<>(quickLoginActivity);
            AppMethodBeat.o(8591);
        }

        @Override // com.netease.nis.quicklogin.listener.LoginListener
        public boolean onDisagreePrivacy() {
            AppMethodBeat.i(8594, false);
            WeakReference<QuickLoginActivity> weakReference = this.a;
            if (weakReference == null || weakReference.get() == null) {
                AppMethodBeat.o(8594);
                return true;
            }
            cn.missfresh.ui.a.a.a(this.a.get().getString(R.string.login_please_read_add_agree_agreement));
            AppMethodBeat.o(8594);
            return true;
        }
    }
}
