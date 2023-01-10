package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.a.e;
import com.sina.weibo.sdk.a.k;
import com.sina.weibo.sdk.auth.c;
import com.tencent.connect.common.Constants;

public class WidgetRequestParam extends BrowserRequestParamBase {
    private c e;
    private String f;
    private a g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;

    public interface a {
        void a(String str);
    }

    public WidgetRequestParam(Context context) {
        super(context);
        this.c = BrowserLauncher.WIDGET;
    }

    /* access modifiers changed from: protected */
    @Override // com.sina.weibo.sdk.component.BrowserRequestParamBase
    public void a(Bundle bundle) {
        this.k = bundle.getString("source");
        this.i = bundle.getString("packagename");
        this.l = bundle.getString("key_hash");
        this.j = bundle.getString(Constants.PARAM_ACCESS_TOKEN);
        this.m = bundle.getString("fuid");
        this.o = bundle.getString("q");
        this.n = bundle.getString("content");
        this.p = bundle.getString("category");
        this.f = bundle.getString("key_listener");
        if (!TextUtils.isEmpty(this.f)) {
            this.e = d.a(this.a).a(this.f);
        }
        this.h = bundle.getString("key_widget_callback");
        if (!TextUtils.isEmpty(this.h)) {
            this.g = d.a(this.a).c(this.h);
        }
        this.b = c(this.b);
    }

    @Override // com.sina.weibo.sdk.component.BrowserRequestParamBase
    public void b(Bundle bundle) {
        this.i = this.a.getPackageName();
        if (!TextUtils.isEmpty(this.i)) {
            this.l = e.a(k.a(this.a, this.i));
        }
        bundle.putString(Constants.PARAM_ACCESS_TOKEN, this.j);
        bundle.putString("source", this.k);
        bundle.putString("packagename", this.i);
        bundle.putString("key_hash", this.l);
        bundle.putString("fuid", this.m);
        bundle.putString("q", this.o);
        bundle.putString("content", this.n);
        bundle.putString("category", this.p);
        d a2 = d.a(this.a);
        if (this.e != null) {
            this.f = a2.a();
            a2.a(this.f, this.e);
            bundle.putString("key_listener", this.f);
        }
        if (this.g != null) {
            this.h = a2.a();
            a2.a(this.h, this.g);
            bundle.putString("key_widget_callback", this.h);
        }
    }

    private String c(String str) {
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("version", "0031405000");
        if (!TextUtils.isEmpty(this.k)) {
            buildUpon.appendQueryParameter("source", this.k);
        }
        if (!TextUtils.isEmpty(this.j)) {
            buildUpon.appendQueryParameter(Constants.PARAM_ACCESS_TOKEN, this.j);
        }
        String b = k.b(this.a, this.k);
        if (!TextUtils.isEmpty(b)) {
            buildUpon.appendQueryParameter("aid", b);
        }
        if (!TextUtils.isEmpty(this.i)) {
            buildUpon.appendQueryParameter("packagename", this.i);
        }
        if (!TextUtils.isEmpty(this.l)) {
            buildUpon.appendQueryParameter("key_hash", this.l);
        }
        if (!TextUtils.isEmpty(this.m)) {
            buildUpon.appendQueryParameter("fuid", this.m);
        }
        if (!TextUtils.isEmpty(this.o)) {
            buildUpon.appendQueryParameter("q", this.o);
        }
        if (!TextUtils.isEmpty(this.n)) {
            buildUpon.appendQueryParameter("content", this.n);
        }
        if (!TextUtils.isEmpty(this.p)) {
            buildUpon.appendQueryParameter("category", this.p);
        }
        return buildUpon.build().toString();
    }

    public c a() {
        return this.e;
    }

    public String b() {
        return this.f;
    }

    public a c() {
        return this.g;
    }

    public String h() {
        return this.h;
    }

    @Override // com.sina.weibo.sdk.component.BrowserRequestParamBase
    public void a(Activity activity, int i) {
        if (i == 3) {
            WeiboSdkBrowser.a(activity, this.f, this.h);
        }
    }
}
