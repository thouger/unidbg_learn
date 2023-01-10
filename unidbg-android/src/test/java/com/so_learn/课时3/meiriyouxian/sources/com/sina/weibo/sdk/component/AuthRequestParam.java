package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.a;
import com.sina.weibo.sdk.auth.c;

public class AuthRequestParam extends BrowserRequestParamBase {
    private a e;
    private c f;
    private String g;

    public AuthRequestParam(Context context) {
        super(context);
        this.c = BrowserLauncher.AUTH;
    }

    /* access modifiers changed from: protected */
    @Override // com.sina.weibo.sdk.component.BrowserRequestParamBase
    public void a(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle("key_authinfo");
        if (bundle2 != null) {
            this.e = a.a(this.a, bundle2);
        }
        this.g = bundle.getString("key_listener");
        if (!TextUtils.isEmpty(this.g)) {
            this.f = d.a(this.a).a(this.g);
        }
    }

    @Override // com.sina.weibo.sdk.component.BrowserRequestParamBase
    public void b(Bundle bundle) {
        a aVar = this.e;
        if (aVar != null) {
            bundle.putBundle("key_authinfo", aVar.f());
        }
        if (this.f != null) {
            d a = d.a(this.a);
            this.g = a.a();
            a.a(this.g, this.f);
            bundle.putString("key_listener", this.g);
        }
    }

    @Override // com.sina.weibo.sdk.component.BrowserRequestParamBase
    public void a(Activity activity, int i) {
        if (i == 3) {
            c cVar = this.f;
            if (cVar != null) {
                cVar.a();
            }
            WeiboSdkBrowser.a(activity, this.g, (String) null);
        }
    }

    public a a() {
        return this.e;
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public c b() {
        return this.f;
    }

    public String c() {
        return this.g;
    }

    public void a(c cVar) {
        this.f = cVar;
    }
}
