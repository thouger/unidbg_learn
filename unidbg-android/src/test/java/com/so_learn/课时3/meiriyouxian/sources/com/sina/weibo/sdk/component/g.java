package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.sina.weibo.sdk.a.k;
import com.sina.weibo.sdk.auth.c;
import com.sina.weibo.sdk.component.WidgetRequestParam;

/* compiled from: WidgetWeiboWebViewClient */
/* access modifiers changed from: package-private */
public class g extends f {
    private Activity b;
    private WidgetRequestParam c;
    private c d;
    private WidgetRequestParam.a e;

    public g(Activity activity, WidgetRequestParam widgetRequestParam) {
        this.b = activity;
        this.c = widgetRequestParam;
        this.e = widgetRequestParam.c();
        this.d = widgetRequestParam.a();
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
        c cVar;
        if (this.a != null) {
            this.a.a(webView, str);
        }
        boolean startsWith = str.startsWith("sinaweibo://browser/close");
        if (!str.startsWith("sinaweibo://browser/close") && !str.startsWith("sinaweibo://browser/datatransfer")) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        Bundle b = k.b(str);
        if (!b.isEmpty() && (cVar = this.d) != null) {
            cVar.a(b);
        }
        WidgetRequestParam.a aVar = this.e;
        if (aVar != null) {
            aVar.a(str);
        }
        if (!startsWith) {
            return true;
        }
        WeiboSdkBrowser.a(this.b, this.c.b(), this.c.h());
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
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (this.a != null) {
            this.a.a(webView, sslErrorHandler, sslError);
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }
}
