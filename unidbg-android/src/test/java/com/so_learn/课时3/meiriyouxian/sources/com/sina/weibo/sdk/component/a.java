package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.provider.Telephony;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.sina.weibo.sdk.a.k;
import com.sina.weibo.sdk.auth.c;
import com.sina.weibo.sdk.exception.WeiboAuthException;

/* compiled from: AuthWeiboWebViewClient */
/* access modifiers changed from: package-private */
public class a extends f {
    private Activity b;
    private AuthRequestParam c;
    private c d;
    private boolean e = false;

    public a(Activity activity, AuthRequestParam authRequestParam) {
        this.b = activity;
        this.c = authRequestParam;
        this.d = this.c.b();
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.a != null) {
            this.a.a(webView, str, bitmap);
        }
        if (!str.startsWith(this.c.a().b()) || this.e) {
            super.onPageStarted(webView, str, bitmap);
            return;
        }
        this.e = true;
        a(str);
        webView.stopLoading();
        WeiboSdkBrowser.a(this.b, this.c.c(), (String) null);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.a != null) {
            this.a.a(webView, str);
        }
        if (str.startsWith("sms:")) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.putExtra("address", str.replace("sms:", ""));
            intent.setType("vnd.android-dir/mms-sms");
            this.b.startActivity(intent);
            return true;
        } else if (!str.startsWith("sinaweibo://browser/close")) {
            return super.shouldOverrideUrlLoading(webView, str);
        } else {
            c cVar = this.d;
            if (cVar != null) {
                cVar.a();
            }
            WeiboSdkBrowser.a(this.b, this.c.c(), (String) null);
            return true;
        }
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

    private void a(String str) {
        Bundle a = k.a(str);
        String string = a.getString("error");
        String string2 = a.getString(Telephony.TextBasedSmsColumns.ERROR_CODE);
        String string3 = a.getString("error_description");
        if (string == null && string2 == null) {
            c cVar = this.d;
            if (cVar != null) {
                cVar.a(a);
                return;
            }
            return;
        }
        c cVar2 = this.d;
        if (cVar2 != null) {
            cVar2.a(new WeiboAuthException(string2, string, string3));
        }
    }
}
