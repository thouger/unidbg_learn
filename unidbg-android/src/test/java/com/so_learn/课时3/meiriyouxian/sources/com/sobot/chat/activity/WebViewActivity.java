package com.sobot.chat.activity;

import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.missfresh.buttomline.abtest.uitl.FileOpt;
import com.android.internal.telephony.RILConstants;
import com.bangcle.andjni.JniLib;
import com.sobot.chat.activity.base.SobotBaseActivity;
import com.sobot.chat.c;
import com.sobot.chat.utils.ac;
import com.sobot.chat.utils.ae;
import com.sobot.chat.utils.d;
import com.sobot.chat.utils.m;
import com.sobot.chat.utils.q;

public class WebViewActivity extends SobotBaseActivity implements View.OnClickListener {
    private WebView a;
    private ProgressBar d;
    private RelativeLayout e;
    private Button f;
    private TextView g;
    private TextView h;
    private String i = "";
    private LinearLayout j;
    private ImageView k;
    private ImageView l;
    private ImageView m;
    private ImageView n;
    private boolean o = true;
    private ValueCallback<Uri> p;
    private ValueCallback<Uri[]> q;

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void c() {
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public int a() {
        return c("sobot_activity_webview");
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void a(Bundle bundle) {
        if (bundle != null) {
            this.i = bundle.getString("url");
            this.o = ac.a(this.i);
        } else if (getIntent() != null && !TextUtils.isEmpty(getIntent().getStringExtra("url"))) {
            this.i = getIntent().getStringExtra("url");
            this.o = ac.a(this.i);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void b() {
        setTitle("");
        b(b("sobot_btn_back_selector"), "", true);
        this.a = (WebView) findViewById(a("sobot_mWebView"));
        this.d = (ProgressBar) findViewById(a("sobot_loadProgress"));
        this.e = (RelativeLayout) findViewById(a("sobot_rl_net_error"));
        this.j = (LinearLayout) findViewById(a("sobot_webview_toolsbar"));
        this.f = (Button) findViewById(a("sobot_btn_reconnect"));
        this.f.setText(q.f(this, "sobot_reunicon"));
        this.f.setOnClickListener(this);
        this.h = (TextView) findViewById(a("sobot_textReConnect"));
        this.h.setText(q.f(this, "sobot_network_unavailable"));
        this.g = (TextView) findViewById(a("sobot_txt_loading"));
        this.k = (ImageView) findViewById(a("sobot_webview_goback"));
        this.l = (ImageView) findViewById(a("sobot_webview_forward"));
        this.m = (ImageView) findViewById(a("sobot_webview_reload"));
        this.n = (ImageView) findViewById(a("sobot_webview_copy"));
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.k.setEnabled(false);
        this.l.setEnabled(false);
        displayInNotch(this.a);
        d();
        t();
        if (this.o) {
            this.a.loadUrl(this.i);
            this.n.setVisibility(0);
        } else {
            this.i = "<!DOCTYPE html>\n<html>\n    <head>\n        <meta charset=\"utf-8\">\n        <title></title>\n        <style>\n            img{\n                width: auto;\n                height:auto;\n                max-height: 100%;\n                max-width: 100%;\n            }\n        </style>\n    </head>\n    <body>" + this.i + "  </body>\n</html>";
            this.a.loadDataWithBaseURL("about:blank", this.i, ClipDescription.MIMETYPE_TEXT_HTML, FileOpt.ENCODE_STR, null);
        }
        m.d("webViewActivity---" + this.i);
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity
    public void a(View view) {
        finish();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.f) {
            if (!TextUtils.isEmpty(this.i)) {
                d();
            }
        } else if (view == this.l) {
            this.a.goForward();
        } else if (view == this.k) {
            this.a.goBack();
        } else if (view == this.m) {
            this.a.reload();
        } else if (view == this.n) {
            g(this.i);
        }
    }

    private void g(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (Build.VERSION.SDK_INT >= 11) {
                m.d("API\u662f\u5927\u4e8e11");
                ClipboardManager clipboardManager = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setText(str);
                clipboardManager.getText();
            } else {
                m.d("API\u662f\u5c0f\u4e8e11");
                android.text.ClipboardManager clipboardManager2 = (android.text.ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager2.setText(str);
                clipboardManager2.getText();
            }
            ae.a(getApplicationContext(), d.a(this, "sobot_ctrl_v_success"));
        }
    }

    private void d() {
        if (d.a(getApplicationContext())) {
            this.a.setVisibility(0);
            this.j.setVisibility(0);
            this.e.setVisibility(8);
            return;
        }
        this.a.setVisibility(8);
        this.j.setVisibility(8);
        this.e.setVisibility(0);
    }

    private void t() {
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                this.a.removeJavascriptInterface("searchBoxJavaBridge_");
            } catch (Exception unused) {
            }
        }
        this.a.setDownloadListener(new AnonymousClass1(this));
        this.a.removeJavascriptInterface("searchBoxJavaBridge_");
        this.a.getSettings().setDefaultFontSize(16);
        this.a.getSettings().setTextZoom(100);
        this.a.getSettings().setAllowFileAccess(false);
        this.a.getSettings().setJavaScriptEnabled(true);
        this.a.getSettings().setCacheMode(-1);
        this.a.getSettings().setDomStorageEnabled(true);
        this.a.getSettings().setLoadsImagesAutomatically(true);
        this.a.getSettings().setBlockNetworkImage(false);
        this.a.getSettings().setSavePassword(false);
        if (Build.VERSION.SDK_INT >= 21) {
            this.a.getSettings().setMixedContentMode(0);
        }
        this.a.getSettings().setDatabaseEnabled(true);
        this.a.getSettings().setAppCacheEnabled(true);
        this.a.setWebViewClient(new AnonymousClass2());
        this.a.setWebChromeClient(new AnonymousClass3());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.WebViewActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements DownloadListener {
        final /* synthetic */ WebViewActivity a;

        AnonymousClass1(WebViewActivity webViewActivity) {
            JniLib.cV(this, webViewActivity, Integer.valueOf((int) RILConstants.RIL_UNSOL_RADIO_CAPABILITY));
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.setData(Uri.parse(str));
            this.a.startActivity(intent);
            this.a.finish();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.WebViewActivity$2  reason: invalid class name */
    public class AnonymousClass2 extends WebViewClient {
        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return false;
        }

        AnonymousClass2() {
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            WebViewActivity.this.k.setEnabled(WebViewActivity.this.a.canGoBack());
            WebViewActivity.this.l.setEnabled(WebViewActivity.this.a.canGoForward());
            if (WebViewActivity.this.o && !WebViewActivity.this.i.replace("http://", "").replace("https://", "").equals(webView.getTitle()) && c.t) {
                WebViewActivity.this.setTitle(webView.getTitle());
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.activity.WebViewActivity$3  reason: invalid class name */
    public class AnonymousClass3 extends WebChromeClient {
        AnonymousClass3() {
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            m.d("\u7f51\u9875--title---\uff1a" + str);
            if (WebViewActivity.this.o && !WebViewActivity.this.i.replace("http://", "").replace("https://", "").equals(str) && c.t) {
                WebViewActivity.this.setTitle(str);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            if (i > 0 && i < 100) {
                WebViewActivity.this.d.setVisibility(0);
                WebViewActivity.this.d.setProgress(i);
            } else if (i == 100) {
                WebViewActivity.this.d.setVisibility(8);
            }
        }

        @Override // android.webkit.WebChromeClient
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            WebViewActivity.this.q = valueCallback;
            WebViewActivity.this.u();
            return true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        WebView webView = this.a;
        if (webView != null) {
            webView.onResume();
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        WebView webView = this.a;
        if (webView != null) {
            webView.onPause();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    @Override // com.sobot.chat.activity.base.SobotBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        WebView webView = this.a;
        if (webView != null) {
            webView.removeAllViews();
            ViewGroup viewGroup = (ViewGroup) this.a.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.a);
            }
            this.a.destroy();
        }
        super.onDestroy();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        WebView webView = this.a;
        if (webView == null || !webView.canGoBack()) {
            super.onBackPressed();
            finish();
            return;
        }
        this.a.goBack();
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString("url", this.i);
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void u() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Image Chooser"), 273);
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 273) {
            return;
        }
        if (this.p != null || this.q != null) {
            if (i2 != -1) {
                ValueCallback<Uri> valueCallback = this.p;
                if (valueCallback != null) {
                    valueCallback.onReceiveValue(null);
                    this.p = null;
                }
                ValueCallback<Uri[]> valueCallback2 = this.q;
                if (valueCallback2 != null) {
                    valueCallback2.onReceiveValue(null);
                    this.q = null;
                }
            }
            if (i2 == -1) {
                Uri data = (i == 273 && intent != null) ? intent.getData() : null;
                ValueCallback<Uri> valueCallback3 = this.p;
                if (valueCallback3 != null) {
                    valueCallback3.onReceiveValue(data);
                    this.p = null;
                }
                ValueCallback<Uri[]> valueCallback4 = this.q;
                if (valueCallback4 != null) {
                    valueCallback4.onReceiveValue(new Uri[]{data});
                    this.q = null;
                }
            }
        }
    }
}
