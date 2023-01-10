package com.sina.weibo.sdk.auth;

import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;

/* compiled from: Oauth2AccessToken */
public class b {
    private String a = "";
    private String b = "";
    private String c = "";
    private long d = 0;
    private String e = "";

    public static b a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        b bVar = new b();
        bVar.a(a(bundle, "uid", ""));
        bVar.b(a(bundle, Constants.PARAM_ACCESS_TOKEN, ""));
        bVar.d(a(bundle, Constants.PARAM_EXPIRES_IN, ""));
        bVar.c(a(bundle, "refresh_token", ""));
        bVar.e(a(bundle, "phone_num", ""));
        return bVar;
    }

    public boolean a() {
        return !TextUtils.isEmpty(this.b);
    }

    public String toString() {
        return "uid: " + this.a + ", " + Constants.PARAM_ACCESS_TOKEN + ": " + this.b + ", refresh_token: " + this.c + ", phone_num: " + this.e + ", " + Constants.PARAM_EXPIRES_IN + ": " + Long.toString(this.d);
    }

    public String b() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public String c() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String d() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public long e() {
        return this.d;
    }

    public void a(long j) {
        this.d = j;
    }

    public void d(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals("0")) {
            a(System.currentTimeMillis() + (Long.parseLong(str) * 1000));
        }
    }

    private static String a(Bundle bundle, String str, String str2) {
        if (bundle == null) {
            return str2;
        }
        String string = bundle.getString(str);
        return string != null ? string : str2;
    }

    private void e(String str) {
        this.e = str;
    }
}
