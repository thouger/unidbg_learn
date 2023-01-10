package cn.missfresh.picture.internal.ui;

import android.os.Bundle;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;

public class SelectedPreviewActivity extends BasePreviewActivity {
    /* access modifiers changed from: protected */
    @Override // cn.missfresh.picture.internal.ui.BasePreviewActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.cV(this, bundle, Integer.valueOf((int) MetricsProto.MetricsEvent.AUTOFILL_FORGED_COMPONENT_ATTEMPT));
    }

    @Override // cn.missfresh.picture.internal.ui.BasePreviewActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }
}
