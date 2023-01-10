package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.c;
import com.tencent.connect.common.Constants;

public class GameRequestParam extends BrowserRequestParamBase {
    private c e;
    private String f;
    private String g;
    private String h;

    @Override // com.sina.weibo.sdk.component.BrowserRequestParamBase
    public void a(Activity activity, int i) {
    }

    public GameRequestParam(Context context) {
        super(context);
        this.c = BrowserLauncher.WIDGET;
    }

    /* access modifiers changed from: protected */
    @Override // com.sina.weibo.sdk.component.BrowserRequestParamBase
    public void a(Bundle bundle) {
        this.h = bundle.getString("source");
        this.g = bundle.getString(Constants.PARAM_ACCESS_TOKEN);
        this.f = bundle.getString("key_listener");
        if (!TextUtils.isEmpty(this.f)) {
            this.e = d.a(this.a).a(this.f);
        }
        this.b = c(this.b);
    }

    @Override // com.sina.weibo.sdk.component.BrowserRequestParamBase
    public void b(Bundle bundle) {
        bundle.putString(Constants.PARAM_ACCESS_TOKEN, this.g);
        bundle.putString("source", this.h);
        d a = d.a(this.a);
        if (this.e != null) {
            this.f = a.a();
            a.a(this.f, this.e);
            bundle.putString("key_listener", this.f);
        }
    }

    private String c(String str) {
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("version", "0031405000");
        if (!TextUtils.isEmpty(this.h)) {
            buildUpon.appendQueryParameter("source", this.h);
        }
        if (!TextUtils.isEmpty(this.g)) {
            buildUpon.appendQueryParameter(Constants.PARAM_ACCESS_TOKEN, this.g);
        }
        return buildUpon.build().toString();
    }

    public c a() {
        return this.e;
    }

    public String b() {
        return this.f;
    }
}
