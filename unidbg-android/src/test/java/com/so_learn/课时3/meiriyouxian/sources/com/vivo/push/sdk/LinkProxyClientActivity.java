package com.vivo.push.sdk;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.sdk.service.LinkProxyActivity;

public class LinkProxyClientActivity extends LinkProxyActivity {
    @Override // com.vivo.push.sdk.service.LinkProxyActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }
}
