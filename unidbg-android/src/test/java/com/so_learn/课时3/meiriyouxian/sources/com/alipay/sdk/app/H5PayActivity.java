package com.alipay.sdk.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import com.alipay.sdk.util.e;
import com.alipay.sdk.widget.c;
import com.bangcle.andjni.JniLib;

public class H5PayActivity extends Activity {
    private c a;
    private String b;
    private String c;
    private String d;
    private String e;
    private boolean f;
    private String g;

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.cV(this, bundle, 951);
    }

    private void b() {
        try {
            super.requestWindowFeature(1);
        } catch (Throwable th) {
            e.a(th);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        c cVar = this.a;
        if (cVar == null) {
            finish();
        } else if (cVar.c()) {
            cVar.b();
        } else {
            if (!cVar.b()) {
                super.onBackPressed();
            }
            b.a(b.c());
            finish();
        }
    }

    @Override // android.app.Activity
    public void finish() {
        a();
        super.finish();
    }

    public void a() {
        Object obj = PayTask.a;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception unused) {
            }
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        c cVar = this.a;
        if (cVar != null) {
            cVar.a();
        }
    }

    @Override // android.app.Activity
    public void setRequestedOrientation(int i) {
        try {
            super.setRequestedOrientation(i);
        } catch (Throwable unused) {
        }
    }
}
