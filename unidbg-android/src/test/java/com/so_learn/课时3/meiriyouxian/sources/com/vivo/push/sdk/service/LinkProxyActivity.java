package com.vivo.push.sdk.service;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.vivo.push.util.n;
import com.vivo.push.util.y;
import java.util.List;

public class LinkProxyActivity extends Activity {
    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        PackageManager packageManager;
        List<ResolveInfo> queryIntentServices;
        boolean z = false;
        AppMethodBeat.i(980, false);
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            n.d("LinkProxyActivity", "enter RequestPermissionsActivity onCreate, intent is null, finish");
            finish();
            AppMethodBeat.o(980);
            return;
        }
        try {
            Window window = getWindow();
            window.setGravity(8388659);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.x = 0;
            attributes.y = 0;
            attributes.height = 1;
            attributes.width = 1;
            window.setAttributes(attributes);
        } catch (Throwable th) {
            n.b("LinkProxyActivity", "enter onCreate error ", th);
        }
        String packageName = getPackageName();
        n.d("LinkProxyActivity", hashCode() + " enter onCreate " + packageName);
        if (!"com.vivo.abe".equals(packageName)) {
            try {
                if (intent.getExtras() != null) {
                    Intent intent2 = (Intent) intent.getExtras().get("previous_intent");
                    if (!(intent2 == null || (packageManager = getPackageManager()) == null || (queryIntentServices = packageManager.queryIntentServices(intent2, MetricsProto.MetricsEvent.DIALOG_WIFI_P2P_CANCEL_CONNECT)) == null)) {
                        if (!queryIntentServices.isEmpty()) {
                            ResolveInfo resolveInfo = queryIntentServices.get(0);
                            if (!(resolveInfo == null || resolveInfo.serviceInfo == null || !resolveInfo.serviceInfo.exported)) {
                                z = true;
                            }
                        }
                    }
                    if (z) {
                        startService(intent2);
                    } else {
                        n.b("LinkProxyActivity", "service's exported is " + z);
                    }
                }
            } catch (Exception e) {
                n.a("LinkProxyActivity", e.toString(), e);
            }
        } else if (intent == null) {
            try {
                n.d("LinkProxyActivity", "adapterToService intent is null");
            } catch (Exception e2) {
                n.a("LinkProxyActivity", e2.toString(), e2);
            }
        } else if (intent.getExtras() == null) {
            n.d("LinkProxyActivity", "adapterToService getExtras() is null");
        } else {
            Intent intent3 = (Intent) intent.getExtras().get("previous_intent");
            if (intent3 == null) {
                n.d("LinkProxyActivity", "adapterToService proxyIntent is null");
            } else {
                y.a(this, intent3);
            }
        }
        finish();
        AppMethodBeat.o(980);
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        AppMethodBeat.i(983, false);
        super.onDestroy();
        n.d("LinkProxyActivity", hashCode() + " onDestory " + getPackageName());
        AppMethodBeat.o(983);
    }
}
