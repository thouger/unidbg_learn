package com.alipay.sdk.app;

import android.content.Intent;
import android.net.Uri;
import com.alipay.sdk.g.a;

public class H5OpenAuthActivity extends H5PayActivity {
    private boolean a = false;

    @Override // com.alipay.sdk.app.H5PayActivity
    public void a() {
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        try {
            a a = a.C0066a.a(intent);
            if (a == null) {
                finish();
                return;
            }
            try {
                super.startActivity(intent);
                Uri data = intent != null ? intent.getData() : null;
                if (data != null && data.toString().startsWith("alipays://platformapi/startapp")) {
                    finish();
                }
            } catch (Throwable th) {
                com.alipay.sdk.app.a.a.a(a, "biz", "StartActivityEx", th, (intent == null || intent.getData() == null) ? "null" : intent.getData().toString());
                this.a = true;
                throw th;
            }
        } catch (Throwable unused) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.alipay.sdk.app.H5PayActivity, android.app.Activity
    public void onDestroy() {
        if (this.a) {
            try {
                a a = a.C0066a.a(getIntent());
                if (a != null) {
                    com.alipay.sdk.app.a.a.b(this, a, "", a.a);
                }
            } catch (Throwable unused) {
            }
        }
        super.onDestroy();
    }
}
