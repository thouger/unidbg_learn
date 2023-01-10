package com.sina.weibo.sdk.auth.sso;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.a.f;
import com.sina.weibo.sdk.a.j;
import com.sina.weibo.sdk.a.k;
import com.sina.weibo.sdk.auth.c;
import com.sina.weibo.sdk.component.AuthRequestParam;
import com.sina.weibo.sdk.component.WeiboSdkBrowser;
import com.sina.weibo.sdk.net.e;
import com.tencent.connect.common.Constants;

/* compiled from: WebAuthHandler */
/* access modifiers changed from: package-private */
public class a {
    private static final String a = a.class.getName();
    private Context b;
    private com.sina.weibo.sdk.auth.a c;

    public a(Context context, com.sina.weibo.sdk.auth.a aVar) {
        this.b = context;
        this.c = aVar;
    }

    public com.sina.weibo.sdk.auth.a a() {
        return this.c;
    }

    public void a(c cVar) {
        a(cVar, 1);
    }

    public void a(c cVar, int i) {
        b(cVar, i);
    }

    private void b(c cVar, int i) {
        if (cVar != null) {
            e eVar = new e(this.c.a());
            eVar.a(Constants.PARAM_CLIENT_ID, this.c.a());
            eVar.a("redirect_uri", this.c.b());
            eVar.a(Constants.PARAM_SCOPE, this.c.c());
            eVar.a("response_type", com.taobao.accs.common.Constants.KEY_HTTP_CODE);
            eVar.a("version", "0031405000");
            String b = k.b(this.b, this.c.a());
            if (!TextUtils.isEmpty(b)) {
                eVar.a("aid", b);
            }
            if (1 == i) {
                eVar.a("packagename", this.c.d());
                eVar.a("key_hash", this.c.e());
            }
            String str = "https://open.weibo.cn/oauth2/authorize?" + eVar.c();
            if (!f.a(this.b)) {
                j.a(this.b, "Error", "Application requires permission to access the Internet");
                return;
            }
            AuthRequestParam authRequestParam = new AuthRequestParam(this.b);
            authRequestParam.a(this.c);
            authRequestParam.a(cVar);
            authRequestParam.a(str);
            authRequestParam.b("\u5fae\u535a\u767b\u5f55");
            Bundle d = authRequestParam.d();
            Intent intent = new Intent(this.b, WeiboSdkBrowser.class);
            intent.putExtras(d);
            this.b.startActivity(intent);
        }
    }
}
