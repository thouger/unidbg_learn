package cn.missfresh.module.user.mine.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.module.base.base.ActionBarActivity;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.module.base.manager.bean.UserInfo;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.payment.pwd.view.BindPayPhoneActivity;
import cn.missfresh.module.base.support.dialog.LoadingLikeIosDialog;
import cn.missfresh.module.base.support.dialog.f;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.module.mvp.a.a;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.login.b.a;
import cn.missfresh.module.user.login.presenter.MinePresenter;
import cn.missfresh.module.user.login.presenter.a;
import cn.missfresh.module.user.login.view.VerifyPhoneActivity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.netease.nis.quicklogin.QuickLogin;
import com.netease.nis.quicklogin.listener.QuickLoginTokenListener;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.umeng.analytics.pro.ai;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import java.util.HashMap;
import org.json.JSONObject;

public class AccountAndSafetyActivity extends ActionBarActivity implements a, a.a, a.a, cn.missfresh.module.user.login.view.a {
    private LoadingLikeIosDialog A;
    private View B;
    private io.reactivex.disposables.a C;
    private View j;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private MinePresenter o;
    private cn.missfresh.module.user.login.presenter.a p;
    private LinearLayout v;
    private String w = "";
    private BroadcastReceiver x = new 1(this);
    private boolean y = false;
    private f z;

    public void a(int i) {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.mine.view.AccountAndSafetyActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.ActionBarActivity, cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        AccountAndSafetyActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    public void r() {
    }

    public AccountAndSafetyActivity() {
        AppMethodBeat.i(9282, false);
        AppMethodBeat.o(9282);
    }

    static /* synthetic */ void d(AccountAndSafetyActivity accountAndSafetyActivity) {
        AppMethodBeat.i(9361, false);
        accountAndSafetyActivity.F();
        AppMethodBeat.o(9361);
    }

    static /* synthetic */ void e(AccountAndSafetyActivity accountAndSafetyActivity) {
        AppMethodBeat.i(9364, false);
        accountAndSafetyActivity.D();
        AppMethodBeat.o(9364);
    }

    static /* synthetic */ void f(AccountAndSafetyActivity accountAndSafetyActivity) {
        AppMethodBeat.i(9366, false);
        accountAndSafetyActivity.x();
        AppMethodBeat.o(9366);
    }

    static /* synthetic */ void g(AccountAndSafetyActivity accountAndSafetyActivity) {
        AppMethodBeat.i(9369, false);
        accountAndSafetyActivity.E();
        AppMethodBeat.o(9369);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(9285, false);
        setContentView(R.layout.activity_account_and_safety);
        super.onCreate(bundle);
        this.j = findViewById(R.id.changePhoneNum);
        this.k = (TextView) findViewById(R.id.phoneNumber);
        this.l = (TextView) findViewById(R.id.wxNumber);
        this.m = (TextView) findViewById(R.id.tv_vip_continuous_monthly);
        this.v = (LinearLayout) findViewById(R.id.ll_vip_continuous_monthly);
        this.n = (TextView) findViewById(R.id.realName);
        findViewById(R.id.btn_pay_password).setOnClickListener(this);
        findViewById(R.id.btn_quick_pay).setOnClickListener(this);
        if (!e.o() || !e.aj()) {
            findViewById(R.id.btn_zhuxiao).setVisibility(8);
        } else {
            findViewById(R.id.btn_zhuxiao).setVisibility(0);
            findViewById(R.id.btn_zhuxiao).setOnClickListener(this);
            StatisticsManager.e(getApplication(), "page_view", null);
        }
        findViewById(R.id.bindingWXNumber).setOnClickListener(this);
        this.o = new MinePresenter(this);
        this.p = new cn.missfresh.module.user.login.presenter.a(this);
        EventBus.getDefault().register(this);
        cn.missfresh.module.user.login.b.a.a(this);
        s();
        AppMethodBeat.o(9285);
    }

    private void b(UserInfo userInfo) {
        AppMethodBeat.i(9289, false);
        K_();
        if (userInfo == null) {
            userInfo = e.p();
        }
        String phone_number = userInfo.getPhone_number();
        String nick_name = userInfo.getNick_name();
        this.w = userInfo.getLog_out_url();
        if (userInfo.getIs_union_account() == 1) {
            if (b.a(phone_number)) {
                phone_number = "    ";
            }
            if (b.a(nick_name)) {
                nick_name = "   ";
            }
        } else if (!b.a(phone_number)) {
            nick_name = "";
        } else {
            nick_name = "    ";
        }
        findViewById(R.id.bindingRealName).setOnClickListener(this);
        if (userInfo.isIdentity_verified()) {
            this.n.setText("\u5df2\u8ba4\u8bc1");
            this.n.setTextColor(Color.parseColor("#4B4B4B"));
        } else {
            this.n.setText("\u5c1a\u672a\u8ba4\u8bc1");
            this.n.setTextColor(Color.parseColor("#FF2B22"));
        }
        if (!TextUtils.isEmpty(userInfo.getThird_nick_name())) {
            this.l.setText(userInfo.getThird_nick_name());
            this.y = true;
        } else if (!TextUtils.isEmpty(nick_name)) {
            this.l.setText(userInfo.getNick_name());
            this.y = true;
        } else {
            this.y = false;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("\u7acb\u5373\u5173\u8054");
            spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_ff4891)), 0, spannableStringBuilder.length(), 18);
            this.l.setText(spannableStringBuilder);
        }
        if (!TextUtils.isEmpty(phone_number)) {
            this.k.setText(at.b(userInfo.getPhone_number()));
            this.j.setOnClickListener(this);
        } else {
            SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder("\u7acb\u5373\u7ed1\u5b9a");
            spannableStringBuilder2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_ff4891)), 0, spannableStringBuilder2.length(), 18);
            this.k.setText(spannableStringBuilder2);
            this.j.setOnClickListener(new 2(this));
        }
        if ("0".equals(userInfo.getIs_open())) {
            this.v.setVisibility(0);
            if ("1".equals(userInfo.getState_autopay())) {
                this.m.setText(R.string.account_go_open);
            } else if ("2".equals(userInfo.getState_autopay())) {
                this.m.setText(R.string.account_signing);
            } else {
                this.m.setText(R.string.account_go_off);
            }
            findViewById(R.id.ll_vip_continuous_monthly).setOnClickListener(new 3(this, userInfo));
        } else {
            this.v.setVisibility(8);
        }
        AppMethodBeat.o(9289);
    }

    private void s() {
        AppMethodBeat.i(9292, false);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getWXActionLogin());
        registerReceiver(this.x, intentFilter);
        AppMethodBeat.o(9292);
    }

    private void t() {
        AppMethodBeat.i(9294, false);
        unregisterReceiver(this.x);
        AppMethodBeat.o(9294);
    }

    private void u() {
        AppMethodBeat.i(9296, false);
        K_();
        QuickLogin.getInstance(getApplicationContext(), "4d754001aa784d4584def14ab44c3f6b").getToken(e.p().getPhone_number(), new AnonymousClass4());
        AppMethodBeat.o(9296);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.mine.view.AccountAndSafetyActivity$4  reason: invalid class name */
    public class AnonymousClass4 extends QuickLoginTokenListener {
        AnonymousClass4() {
        }

        @Override // com.netease.nis.quicklogin.listener.QuickLoginListener
        public void onGetTokenSuccess(String str, String str2) {
            AppMethodBeat.i(9227, false);
            HashMap hashMap = new HashMap();
            hashMap.put("thirdToken", str);
            hashMap.put("thirdAccessToken", str2);
            HashMap hashMap2 = new HashMap();
            hashMap2.put("param", hashMap);
            String str3 = AccountAndSafetyActivity.this.b;
            d.c(str3, "\u9a8c\u8bc1\u624b\u673a\u53f7\u8bf7\u6c42\u53c2\u6570: " + hashMap);
            AccountAndSafetyActivity.this.o.a(hashMap2);
            AppMethodBeat.o(9227);
        }

        @Override // com.netease.nis.quicklogin.listener.QuickLoginListener
        public void onGetTokenError(String str, String str2) {
            AppMethodBeat.i(9229, false);
            AccountAndSafetyActivity.d(AccountAndSafetyActivity.this);
            AppMethodBeat.o(9229);
        }

        @Override // com.netease.nis.quicklogin.listener.QuickLoginTokenListener, com.netease.nis.quicklogin.listener.QuickLoginListener
        public boolean onExtendMsg(JSONObject jSONObject) {
            AppMethodBeat.i(9232, false);
            boolean onExtendMsg = super.onExtendMsg(jSONObject);
            AppMethodBeat.o(9232);
            return onExtendMsg;
        }

        @Override // com.netease.nis.quicklogin.listener.QuickLoginTokenListener, com.netease.nis.quicklogin.listener.QuickLoginListener
        public void onGetMobileNumberSuccess(String str, String str2) {
            AppMethodBeat.i(9236, false);
            super.onGetMobileNumberSuccess(str, str2);
            AppMethodBeat.o(9236);
        }

        @Override // com.netease.nis.quicklogin.listener.QuickLoginTokenListener, com.netease.nis.quicklogin.listener.QuickLoginListener
        public void onGetMobileNumberError(String str, String str2) {
            AppMethodBeat.i(9239, false);
            super.onGetMobileNumberError(str, str2);
            AccountAndSafetyActivity.d(AccountAndSafetyActivity.this);
            AppMethodBeat.o(9239);
        }

        @Override // com.netease.nis.quicklogin.listener.QuickLoginTokenListener
        public void onCancelGetToken() {
            AppMethodBeat.i(9242, false);
            super.onCancelGetToken();
            AppMethodBeat.o(9242);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: cn.missfresh.module.user.mine.view.AccountAndSafetyActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0048: APUT  
      (r6v19 java.lang.Object[])
      (3 ??[int, float, short, byte, char])
      (wrap: java.lang.Integer : 0x0044: INVOKE  (r1v11 java.lang.Integer) = (r1v10 int) type: STATIC call: java.lang.Integer.valueOf(int):java.lang.Integer)
     */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        int i = 1;
        AppMethodBeat.i(9298, true);
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.changePhoneNum) {
            u();
        } else if (id == R.id.bindingWXNumber) {
            if (this.y) {
                v();
            } else {
                K_();
                this.o.b();
            }
            Object[] objArr = new Object[4];
            objArr[0] = ai.e;
            objArr[1] = ConstantsAPI.Token.WX_TOKEN_PLATFORMID_VALUE;
            objArr[2] = "type";
            if (this.y) {
                i = 2;
            }
            objArr[3] = Integer.valueOf(i);
            StatisticsManager.ai("click_binding", objArr);
        } else if (id == R.id.btn_pay_password) {
            String phone_number = e.p().getPhone_number();
            if (!b.a(phone_number)) {
                BindPayPhoneActivity.a((Activity) this, phone_number);
            } else {
                o.a(false, getClass().getSimpleName());
            }
        } else if (id == R.id.btn_zhuxiao) {
            if (!TextUtils.isEmpty(this.w)) {
                com.alibaba.android.arouter.b.a.a().a("/promotion/new_h5event").withString("h5_url", this.w).navigation();
            }
        } else if (id == R.id.bindingRealName) {
            com.alibaba.android.arouter.b.a.a().a("/promotion/new_h5event").withString("h5_url", "https://as-vip.missfresh.cn/frontend/user/approve.html").navigation();
        } else if (id == R.id.btn_quick_pay) {
            com.alibaba.android.arouter.b.a.a().a("/user/quick_pay_list").withInt("from", 0).navigation();
        }
        AppMethodBeat.o(9298);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r11v0, resolved type: cn.missfresh.module.user.mine.view.AccountAndSafetyActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void v() {
        AppMethodBeat.i(9301, false);
        if (this.z == null) {
            this.z = new f(this, "\u89e3\u9664\u4e0e\u5fae\u4fe1\u8d26\u53f7\u7684\u7ed1\u5b9a\uff1f", "\u89e3\u7ed1\u540e\u60a8\u65e0\u6cd5\u4f7f\u7528\u8be5\u5fae\u4fe1\u8d26\u53f7\u767b\u5f55\u6bcf\u65e5\u4f18\u9c9c\uff0c\u540c\u65f6\u5728\u5fae\u4fe1\u8d2d\u7269\u65f6\u9700\u8981\u91cd\u65b0\u767b\u5f55\u3002", "\u89e3\u9664\u7ed1\u5b9a", "\u53d6\u6d88", new 5(this), new 6(this), "newFlag");
        }
        if (!this.z.isShowing() && !isFinishing()) {
            this.z.show();
        }
        AppMethodBeat.o(9301);
    }

    private void x() {
        AppMethodBeat.i(9304, false);
        if (isFinishing()) {
            AppMethodBeat.o(9304);
            return;
        }
        if (this.B == null) {
            this.B = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_open_quick_state_success, (ViewGroup) null);
        }
        cn.missfresh.ui.a.a.a(this.B, 17, 0, 0, "\u89e3\u9664\u7ed1\u5b9a\u6210\u529f");
        AppMethodBeat.o(9304);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.mine.view.AccountAndSafetyActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void D() {
        AppMethodBeat.i(9307, false);
        if (this.A == null) {
            this.A = new LoadingLikeIosDialog(this, R.style.style_dialog_transparent_bg);
        }
        if (!this.A.isShowing() && !isFinishing()) {
            this.A.show();
        }
        AppMethodBeat.o(9307);
    }

    private void E() {
        AppMethodBeat.i(9310, false);
        LoadingLikeIosDialog loadingLikeIosDialog = this.A;
        if (loadingLikeIosDialog != null && loadingLikeIosDialog.isShowing()) {
            this.A.dismiss();
        }
        AppMethodBeat.o(9310);
    }

    @Subscribe
    public void onHandleEvent(cn.missfresh.module.user.login.a.a aVar) {
        AppMethodBeat.i(9313, false);
        r();
        AppMethodBeat.o(9313);
    }

    public void Z_() {
        AppMethodBeat.i(9319, false);
        l();
        d.b(this.b, "loginWxFail");
        cn.missfresh.ui.a.a.a("\u5fae\u4fe1\u767b\u5f55\u5931\u8d25\uff0c\u8bf7\u91cd\u8bd5");
        AppMethodBeat.o(9319);
    }

    public void aa_() {
        AppMethodBeat.i(9321, false);
        l();
        d.b(this.b, "wxNotInstall");
        cn.missfresh.ui.a.a.a("\u60a8\u6ca1\u6709\u5b89\u88c5\u5fae\u4fe1\uff0c\u8bf7\u5b89\u88c5\u540e\u518d\u8bd5");
        AppMethodBeat.o(9321);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onDestroy() {
        AppMethodBeat.i(9324, false);
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        t();
        this.o.d();
        f fVar = this.z;
        if (fVar != null && fVar.isShowing()) {
            this.z.dismiss();
        }
        this.z = null;
        E();
        AppMethodBeat.o(9324);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        AppMethodBeat.i(9328, false);
        super.onResume();
        cn.missfresh.module.user.login.b.a.a().a(2);
        AppMethodBeat.o(9328);
    }

    public void a(String str) {
        AppMethodBeat.i(9330, false);
        l();
        AppMethodBeat.o(9330);
    }

    public void b(String str) {
        AppMethodBeat.i(9333, false);
        l();
        cn.missfresh.ui.a.a.a(str);
        AppMethodBeat.o(9333);
    }

    public void a(UserInfo userInfo) {
        AppMethodBeat.i(9336, false);
        b(userInfo);
        l();
        AppMethodBeat.o(9336);
    }

    public void a() {
        AppMethodBeat.i(9339, false);
        b((UserInfo) null);
        l();
        AppMethodBeat.o(9339);
    }

    @Override // cn.missfresh.module.user.login.view.a
    public void a(Object obj) {
        AppMethodBeat.i(9343, false);
        l();
        com.alibaba.android.arouter.b.a.a().a("/user/change_phone_num").navigation();
        cn.missfresh.ui.a.a.a(getString(R.string.user_safe_notice));
        AppMethodBeat.o(9343);
    }

    @Override // cn.missfresh.module.user.login.view.a
    public void a(int i, String str) {
        AppMethodBeat.i(9348, false);
        F();
        AppMethodBeat.o(9348);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: cn.missfresh.module.user.mine.view.AccountAndSafetyActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void F() {
        AppMethodBeat.i(9350, false);
        c(true);
        VerifyPhoneActivity.b((Activity) this, e.p().getPhone_number());
        AppMethodBeat.o(9350);
    }

    public void a(io.reactivex.disposables.b bVar) {
        AppMethodBeat.i(9352, false);
        if (bVar == null) {
            AppMethodBeat.o(9352);
            return;
        }
        if (this.C == null) {
            this.C = new io.reactivex.disposables.a();
        }
        this.C.a(bVar);
        AppMethodBeat.o(9352);
    }
}
