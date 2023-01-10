package com.sina.weibo.sdk.auth;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.a.k;
import com.taobao.accs.common.Constants;

/* compiled from: AuthInfo */
public class a {
    private String a = "";
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";

    public a(Context context, String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = context.getPackageName();
        this.e = k.a(context, this.d);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public Bundle f() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_APP_KEY, this.a);
        bundle.putString("redirectUri", this.b);
        bundle.putString(com.tencent.connect.common.Constants.PARAM_SCOPE, this.c);
        bundle.putString("packagename", this.d);
        bundle.putString("key_hash", this.e);
        return bundle;
    }

    public static a a(Context context, Bundle bundle) {
        return new a(context, bundle.getString(Constants.KEY_APP_KEY), bundle.getString("redirectUri"), bundle.getString(com.tencent.connect.common.Constants.PARAM_SCOPE));
    }
}
