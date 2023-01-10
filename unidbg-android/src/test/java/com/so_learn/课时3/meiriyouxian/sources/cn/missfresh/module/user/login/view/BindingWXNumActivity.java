package cn.missfresh.module.user.login.view;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.support.modelsupport.event.bean.EventBindingWxBean;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.login.presenter.MinePresenter;
import cn.missfresh.module.user.login.view.BindingPhoneNumActivity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.android.arouter.b.a;
import de.greenrobot.event.EventBus;

public class BindingWXNumActivity extends AbstBindingNumActivity implements b {
    private MinePresenter k;
    private BroadcastReceiver l = new 1(this);

    public void a(int i) {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.login.view.BindingWXNumActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.user.login.view.AbstBindingNumActivity, cn.missfresh.module.base.base.ActionBarActivity, cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        BindingWXNumActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    public BindingWXNumActivity() {
        AppMethodBeat.i(8346, false);
        AppMethodBeat.o(8346);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.user.login.view.AbstBindingNumActivity, cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(8350, false);
        super.onCreate(bundle);
        setContentView(R.layout.user_activity_binding_wxnum);
        s();
        this.e.setCenterTxt(getTitle().toString());
        this.e.setCenterVisibility(0);
        this.e.setRightButtonJustText("\u53d6\u6d88");
        this.e.setRightButtonVisibility(0);
        this.e.setRightButtonOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
        this.k = new MinePresenter(this);
        x();
        AppMethodBeat.o(8350);
    }

    @Override // cn.missfresh.module.user.login.view.AbstBindingNumActivity
    public void a(String str) {
        AppMethodBeat.i(8352, false);
        e.h(true);
        d.c(this.b, " on bind success ");
        EventBus.getDefault().post(new EventBindingWxBean());
        super.a(str);
        AppMethodBeat.o(8352);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.user.login.view.AbstBindingNumActivity, cn.missfresh.module.base.base.BaseFragmentActivity
    public void onDestroy() {
        AppMethodBeat.i(8354, false);
        super.onDestroy();
        D();
        this.k.d();
        AppMethodBeat.o(8354);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(8357, true);
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.btn_login) {
            K_();
            this.k.b();
        } else if (id == R.id.rl_title_bar_right_button) {
            EventBus.getDefault().post(new BindingPhoneNumActivity.a());
            EventBus.getDefault().post(new EventBindingWxBean());
            finish();
        }
        AppMethodBeat.o(8357);
    }

    private void x() {
        AppMethodBeat.i(8362, false);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getWXActionLogin());
        registerReceiver(this.l, intentFilter);
        AppMethodBeat.o(8362);
    }

    private void D() {
        AppMethodBeat.i(8364, false);
        unregisterReceiver(this.l);
        AppMethodBeat.o(8364);
    }

    public void Z_() {
        AppMethodBeat.i(8368, false);
        d.d(this.b, "wxBinding failed.");
        l();
        AppMethodBeat.o(8368);
    }

    public void aa_() {
        AppMethodBeat.i(8370, false);
        d.d(this.b, "wxNotInstall.");
        l();
        cn.missfresh.ui.a.a.a("\u60a8\u6ca1\u6709\u5b89\u88c5\u5fae\u4fe1\uff0c\u8bf7\u5b89\u88c5\u540e\u518d\u8bd5");
        AppMethodBeat.o(8370);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        AppMethodBeat.i(8371, false);
        super.onResume();
        l();
        AppMethodBeat.o(8371);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void J_() {
        AppMethodBeat.i(8372, false);
        super.J_();
        EventBus.getDefault().post(new EventBindingWxBean());
        AppMethodBeat.o(8372);
    }
}
