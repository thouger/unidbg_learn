package cn.missfresh.module.user.login.view;

import android.os.Bundle;
import android.view.View;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.user.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;

public class QuickLoginNoticeActivity extends BaseFragmentActivity implements View.OnClickListener {
    private boolean a;

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onBackPressed() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.login.view.QuickLoginNoticeActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        QuickLoginNoticeActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(8679, false);
        super.onCreate(bundle);
        if (bundle != null) {
            d.b("QuickLoginNoticeActivity", "QuickLoginNoticeActivity savedInstanceState != null");
            finish();
        }
        setContentViewWithOutTitleBar(R.layout.activity_quick_login_notice);
        a();
        AppMethodBeat.o(8679);
    }

    private void a() {
        AppMethodBeat.i(8681, false);
        findViewById(R.id.left_tv).setOnClickListener(this);
        findViewById(R.id.right_tv).setOnClickListener(this);
        AppMethodBeat.o(8681);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(8683, true);
        if (view.getId() == R.id.left_tv) {
            finish();
        } else if (view.getId() == R.id.right_tv) {
            this.a = true;
            B().a();
            d.c(this.b, " agree permission, restart app");
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(8683);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        AppMethodBeat.i(8685, false);
        super.onResume();
        AppMethodBeat.o(8685);
    }
}
