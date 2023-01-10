package cn.missfresh.module.user.login.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.missfresh.module.base.payment.pwd.view.BindPayPhoneActivity;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.login.presenter.MinePresenter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;
import java.util.HashMap;

public class VerifyPhoneActivity extends BindPayPhoneActivity implements a {
    private MinePresenter j;

    public void Z_() {
    }

    public void a(int i) {
    }

    public void aa_() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.login.view.VerifyPhoneActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.payment.pwd.view.BindPayPhoneActivity, cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        VerifyPhoneActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    public static void b(Activity activity, String str) {
        AppMethodBeat.i(8690, false);
        Intent intent = new Intent(activity, VerifyPhoneActivity.class);
        intent.putExtra("phone_num", str);
        activity.startActivityForResult(intent, 20);
        AppMethodBeat.o(8690);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.payment.pwd.view.BindPayPhoneActivity, cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(8692, false);
        super.onCreate(bundle);
        this.j = new MinePresenter(this);
        findViewById(R.id.bind_pay_notice_tv).setVisibility(0);
        AppMethodBeat.o(8692);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.BindPayPhoneActivity, cn.missfresh.module.base.payment.pwd.view.a
    public void c(String str) {
        AppMethodBeat.i(8694, false);
        t();
        this.a.b(x(), str);
        AppMethodBeat.o(8694);
    }

    @Override // cn.missfresh.module.base.payment.pwd.view.BindPayPhoneActivity
    public void b(String str, String str2) {
        AppMethodBeat.i(8698, false);
        K_();
        HashMap hashMap = new HashMap();
        hashMap.put("smsCode", str2);
        hashMap.put("sceneType", "4");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("param", hashMap);
        this.j.a(hashMap2);
        AppMethodBeat.o(8698);
    }

    @Override // cn.missfresh.module.user.login.view.a
    public void a(Object obj) {
        AppMethodBeat.i(8699, false);
        l();
        a.a().a("/user/change_phone_num").navigation();
        AppMethodBeat.o(8699);
    }

    @Override // cn.missfresh.module.user.login.view.a
    public void a(int i, String str) {
        AppMethodBeat.i(8700, false);
        l();
        cn.missfresh.ui.a.a.a(str);
        AppMethodBeat.o(8700);
    }
}
