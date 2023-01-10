package cn.missfresh.module.user.login.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import cn.missfresh.module.base.bean.BindingInfo;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.utils.as;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.login.presenter.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import cn.missfresh.utils.a.d;
import com.umeng.analytics.pro.ai;
import de.greenrobot.event.EventBus;

public class BindingPhoneNumActivity extends AbstBindingNumActivity implements b.a {
    private View k;
    private b l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private View v;

    public void a(int i, String str) {
    }

    public void a(Object obj) {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.login.view.BindingPhoneNumActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public Activity b() {
        return this;
    }

    public void d(String str) {
    }

    public boolean i() {
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.login.view.BindingPhoneNumActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.user.login.view.AbstBindingNumActivity, cn.missfresh.module.base.base.ActionBarActivity, cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        BindingPhoneNumActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.login.view.BindingPhoneNumActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.user.login.view.AbstBindingNumActivity, cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(8287, false);
        super.onCreate(bundle);
        setContentView(R.layout.user_activity_binding_phone_num);
        s();
        this.e.setVisibility(8);
        this.k = findViewById(R.id.phone_login_root);
        this.l = new b(this);
        this.m = (TextView) findViewById(R.id.getCheckCode);
        this.m.setOnClickListener(this);
        this.n = (TextView) findViewById(R.id.btn_login);
        this.n.setOnClickListener(this);
        this.v = findViewById(R.id.ll_bind_phone_back);
        this.v.setOnClickListener(this);
        this.o = (TextView) findViewById(R.id.phoneNumber_et);
        this.p = (TextView) findViewById(R.id.checkCode_et);
        this.a.add(findViewById(R.id.phoneNumber_et));
        this.a.add(findViewById(R.id.checkCode_et));
        as.a((Activity) this, ContextCompat.getColor(this, R.color.color_f3f5f6));
        StatisticsManager.ac("exposure", ai.e, "iphoneNum_binding_app");
        AppMethodBeat.o(8287);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.user.login.view.AbstBindingNumActivity, cn.missfresh.module.base.base.BaseFragmentActivity
    public void onDestroy() {
        AppMethodBeat.i(8288, false);
        super.onDestroy();
        this.l.c();
        AppMethodBeat.o(8288);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: cn.missfresh.module.user.login.view.BindingPhoneNumActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(8291, true);
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.getCheckCode) {
            this.l.a(this, true);
        } else if (id == R.id.btn_login) {
            this.j.a("0", this.o.getText().toString(), this.p.getText().toString());
        } else if (id == R.id.ll_bind_phone_back) {
            w();
        }
        AppMethodBeat.o(8291);
    }

    private void w() {
        AppMethodBeat.i(8295, false);
        e.g("");
        EventBus.getDefault().post(new a());
        EventBus.getDefault().post(new BindingInfo(2, v(), t()));
        finish();
        AppMethodBeat.o(8295);
    }

    @Override // cn.missfresh.module.user.login.view.AbstBindingNumActivity
    public void a(String str) {
        AppMethodBeat.i(8298, false);
        d.d(this.b, "onBindingSuccess");
        e.h(true);
        super.a(str);
        AppMethodBeat.o(8298);
    }

    public View a() {
        return this.k;
    }

    public void c() {
        AppMethodBeat.i(8305, false);
        K_();
        AppMethodBeat.o(8305);
    }

    public void d() {
        AppMethodBeat.i(8307, false);
        l();
        AppMethodBeat.o(8307);
    }

    public void m_(String str) {
        AppMethodBeat.i(8308, false);
        l();
        a.a(str);
        AppMethodBeat.o(8308);
    }

    public void e() {
        AppMethodBeat.i(8310, false);
        this.m.setEnabled(true);
        this.m.setTextColor(getResources().getColor(R.color.black_474346));
        this.m.setAlpha(1.0f);
        AppMethodBeat.o(8310);
    }

    public void Y_() {
        AppMethodBeat.i(8312, false);
        this.m.setEnabled(false);
        this.m.setTextColor(getResources().getColor(R.color.color_969696));
        this.m.setAlpha(0.65f);
        AppMethodBeat.o(8312);
    }

    public void h() {
        AppMethodBeat.i(8316, false);
        b(true);
        this.l.a(true, "");
        AppMethodBeat.o(8316);
    }

    public void c(String str) {
        AppMethodBeat.i(8319, false);
        c(true);
        AppMethodBeat.o(8319);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onBackPressed() {
        AppMethodBeat.i(8330, false);
        w();
        AppMethodBeat.o(8330);
    }
}
