package com.sobot.chat.activity.base;

import android.os.Bundle;
import com.sobot.chat.api.model.Information;
import java.io.Serializable;

public abstract class SobotBaseHelpCenterActivity extends SobotBaseActivity {
    protected Bundle a;
    protected Information d;

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void a(Bundle bundle) {
        if (bundle == null) {
            this.a = getIntent().getBundleExtra("sobot_bundle_information");
        } else {
            this.a = bundle.getBundle("sobot_bundle_information");
        }
        Bundle bundle2 = this.a;
        if (bundle2 != null) {
            Serializable serializable = bundle2.getSerializable("sobot_bundle_info");
            if (serializable instanceof Information) {
                this.d = (Information) serializable;
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBundle("sobot_bundle_information", this.a);
        super.onSaveInstanceState(bundle);
    }
}
