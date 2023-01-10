package cn.missfresh.module.user.mine.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.user.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;

public class PrivacyPolicyActivity extends BaseFragmentActivity {
    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.mine.view.PrivacyPolicyActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        PrivacyPolicyActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(9598, false);
        super.onCreate(bundle);
        setContentView(R.layout.activity_privacy_policy);
        this.e.setLeftButtonVisibility(0);
        this.e.setCenterTxt(getString(R.string.mine_privacy_policy));
        this.e.setCenterVisibility(0);
        this.e.setRightButtonVisibility(8);
        findViewById(R.id.tv_watch_privacy).setOnClickListener(this);
        findViewById(R.id.tv_recommend_setting).setOnClickListener(this);
        AppMethodBeat.o(9598);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.mine.view.PrivacyPolicyActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(9601, true);
        super.onClick(view);
        if (view.getId() == R.id.tv_watch_privacy) {
            j.b((Activity) this);
        } else if (view.getId() == R.id.tv_recommend_setting) {
            a.a().a("/user/individuation_setting").navigation();
        }
        AppMethodBeat.o(9601);
    }
}
