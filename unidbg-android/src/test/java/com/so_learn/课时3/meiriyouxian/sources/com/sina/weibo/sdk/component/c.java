package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.sina.weibo.sdk.a.k;
import com.taobao.accs.common.Constants;

/* compiled from: ShareWeiboWebViewClient */
/* access modifiers changed from: package-private */
public class c extends f {
    private Activity b;
    private ShareRequestParam c;
    private com.sina.weibo.sdk.auth.c d;

    public c(Activity activity, ShareRequestParam shareRequestParam) {
        this.b = activity;
        this.c = shareRequestParam;
        this.d = shareRequestParam.c();
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.a != null) {
            this.a.a(webView, str, bitmap);
        }
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        com.sina.weibo.sdk.auth.c cVar;
        if (this.a != null) {
            this.a.a(webView, str);
        }
        if (!str.startsWith("sinaweibo://browser/close")) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        Bundle b = k.b(str);
        if (!b.isEmpty() && (cVar = this.d) != null) {
            cVar.a(b);
        }
        String string = b.getString(Constants.KEY_HTTP_CODE);
        String string2 = b.getString("msg");
        if (TextUtils.isEmpty(string)) {
            this.c.a(this.b);
        } else if (!"0".equals(string)) {
            this.c.a(this.b, string2);
        } else {
            this.c.b(this.b);
        }
        WeiboSdkBrowser.a(this.b, this.c.h(), (String) null);
        return true;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        if (this.a != null) {
            this.a.b(webView, str);
        }
        super.onPageFinished(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        if (this.a != null) {
            this.a.a(webView, i, str, str2);
        }
        this.c.a(this.b, str);
        WeiboSdkBrowser.a(this.b, this.c.h(), (String) null);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (this.a != null) {
            this.a.a(webView, sslErrorHandler, sslError);
        }
        sslErrorHandler.cancel();
        this.c.a(this.b, "ReceivedSslError");
        WeiboSdkBrowser.a(this.b, this.c.h(), (String) null);
    }
}
