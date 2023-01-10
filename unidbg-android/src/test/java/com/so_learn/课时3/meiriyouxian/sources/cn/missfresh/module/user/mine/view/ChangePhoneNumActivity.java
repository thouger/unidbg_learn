package cn.missfresh.module.user.mine.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cn.missfresh.module.base.support.dialog.f;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.login.presenter.b;
import cn.missfresh.module.user.login.view.AbstBindingNumActivity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;

public class ChangePhoneNumActivity extends AbstBindingNumActivity implements b.a {
    private TextView k;
    private TextView l;
    private TextView m;
    private b n;
    private View o;

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.mine.view.ChangePhoneNumActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public Activity b() {
        return this;
    }

    public void d(String str) {
    }

    public boolean i() {
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.mine.view.ChangePhoneNumActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.user.login.view.AbstBindingNumActivity, cn.missfresh.module.base.base.ActionBarActivity, cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        ChangePhoneNumActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    static /* synthetic */ void a(ChangePhoneNumActivity changePhoneNumActivity) {
        AppMethodBeat.i(9442, false);
        changePhoneNumActivity.w();
        AppMethodBeat.o(9442);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.user.login.view.AbstBindingNumActivity, cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(9393, false);
        setContentView(R.layout.activity_change_phone_num);
        super.onCreate(bundle);
        this.e.getBottomDivider().setVisibility(8);
        this.e.setLeftButtonVisibility(0);
        this.e.setCenterTxt(getTitle().toString());
        this.e.setCenterVisibility(0);
        this.o = findViewById(R.id.phone_login_root);
        this.k = (TextView) findViewById(R.id.getCheckCode);
        this.n = new b(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.l = (TextView) findViewById(R.id.phoneNumber_et);
        this.m = (TextView) findViewById(R.id.checkCode_et);
        this.a.add(findViewById(R.id.phoneNumber_et));
        this.a.add(findViewById(R.id.checkCode_et));
        AppMethodBeat.o(9393);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.user.login.view.AbstBindingNumActivity, cn.missfresh.module.base.base.BaseFragmentActivity
    public void onDestroy() {
        AppMethodBeat.i(9396, false);
        super.onDestroy();
        this.n.c();
        AppMethodBeat.o(9396);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(9399, true);
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.btn_login) {
            String charSequence = this.l.getText().toString();
            String charSequence2 = this.m.getText().toString();
            K_();
            this.j.a("2", charSequence, charSequence2);
        } else if (id == R.id.getCheckCode) {
            this.n.a(this.l.getText().toString());
        }
        AppMethodBeat.o(9399);
    }

    @Override // cn.missfresh.module.user.login.view.AbstBindingNumActivity
    public void r() {
        AppMethodBeat.i(9402, false);
        a.a().a("/user/account_security").navigation();
        AppMethodBeat.o(9402);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.mine.view.ChangePhoneNumActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void w() {
        AppMethodBeat.i(9405, false);
        this.n.a(this, true, true);
        AppMethodBeat.o(9405);
    }

    public View a() {
        return this.o;
    }

    public void c() {
        AppMethodBeat.i(9411, false);
        K_();
        AppMethodBeat.o(9411);
    }

    public void d() {
        AppMethodBeat.i(9414, false);
        l();
        AppMethodBeat.o(9414);
    }

    public void m_(String str) {
        AppMethodBeat.i(9416, false);
        l();
        cn.missfresh.ui.a.a.a(str);
        AppMethodBeat.o(9416);
    }

    public void e() {
        AppMethodBeat.i(9418, false);
        this.k.setEnabled(true);
        this.k.setTextColor(getResources().getColor(R.color.red_ff));
        this.k.setBackgroundResource(R.drawable.shape_corners_4_red_solid_white);
        AppMethodBeat.o(9418);
    }

    public void Y_() {
        AppMethodBeat.i(9421, false);
        this.k.setEnabled(false);
        this.k.setTextColor(getResources().getColor(R.color.gray_b8));
        this.k.setBackgroundResource(R.drawable.shape_corners_5_4b_no_solid);
        AppMethodBeat.o(9421);
    }

    public void h() {
        AppMethodBeat.i(9425, false);
        this.n.a(true, "");
        b(true);
        AppMethodBeat.o(9425);
    }

    public void c(String str) {
        AppMethodBeat.i(9429, false);
        c(true);
        AppMethodBeat.o(9429);
    }

    public void a(Object obj) {
        AppMethodBeat.i(9436, false);
        new f(this.f, String.valueOf(obj), "\u7ee7\u7eed", "\u53d6\u6d88", new 1(this), new 2(this)).show();
        AppMethodBeat.o(9436);
    }

    public void a(int i, String str) {
        AppMethodBeat.i(9439, false);
        w();
        AppMethodBeat.o(9439);
    }
}
