package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import com.sina.weibo.sdk.a.d;
import com.sina.weibo.sdk.a.f;
import com.sina.weibo.sdk.a.g;
import com.sina.weibo.sdk.a.k;
import com.sina.weibo.sdk.component.ShareRequestParam;
import com.sina.weibo.sdk.component.view.LoadingBar;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.c;
import com.sina.weibo.sdk.net.e;

public class WeiboSdkBrowser extends Activity implements b {
    private static final String a = WeiboSdkBrowser.class.getName();
    private String b;
    private String c;
    private boolean d;
    private String e;
    private boolean f;
    private TextView g;
    private TextView h;
    private WebView i;
    private LoadingBar j;
    private LinearLayout k;
    private Button l;
    private Boolean m = false;
    private BrowserRequestParamBase n;
    private f o;

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.cV(this, bundle, Integer.valueOf((int) MetricsProto.MetricsEvent.SETTINGS_ASSIST_GESTURE_TRAINING_INTRO));
    }

    private boolean a(Intent intent) {
        Bundle extras = intent.getExtras();
        this.n = a(extras);
        BrowserRequestParamBase browserRequestParamBase = this.n;
        if (browserRequestParamBase != null) {
            this.e = browserRequestParamBase.e();
            this.b = this.n.g();
        } else {
            String string = extras.getString("key_url");
            String string2 = extras.getString("key_specify_title");
            if (!TextUtils.isEmpty(string) && string.startsWith("http")) {
                this.e = string;
                this.b = string2;
            }
        }
        if (TextUtils.isEmpty(this.e)) {
            return false;
        }
        String str = a;
        d.a(str, "LOAD URL : " + this.e);
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(String str) {
        this.i.loadUrl(str);
    }

    private void c() {
        d.a(a, "Enter startShare()............");
        ShareRequestParam shareRequestParam = (ShareRequestParam) this.n;
        if (shareRequestParam.a()) {
            d.a(a, "loadUrl hasImage............");
            new AsyncWeiboRunner(this).a("http://service.weibo.com/share/mobilesdk_uppic.php", shareRequestParam.a(new e(shareRequestParam.b())), "POST", new AnonymousClass1(this, shareRequestParam));
            return;
        }
        a(this.e);
    }

    /* renamed from: com.sina.weibo.sdk.component.WeiboSdkBrowser$1  reason: invalid class name */
    class AnonymousClass1 implements c {
        final /* synthetic */ WeiboSdkBrowser a;
        private final /* synthetic */ ShareRequestParam b;

        AnonymousClass1(WeiboSdkBrowser weiboSdkBrowser, ShareRequestParam shareRequestParam) {
            JniLib.cV(this, weiboSdkBrowser, shareRequestParam, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_HANDLE_ANOMALY));
        }

        @Override // com.sina.weibo.sdk.net.c
        public void a(WeiboException weiboException) {
            String str = WeiboSdkBrowser.a;
            d.a(str, "post onWeiboException " + weiboException.getMessage());
            this.b.a(this.a, weiboException.getMessage());
            this.a.finish();
        }

        @Override // com.sina.weibo.sdk.net.c
        public void a(String str) {
            String str2 = WeiboSdkBrowser.a;
            d.a(str2, "post onComplete : " + str);
            ShareRequestParam.a a = ShareRequestParam.a.a(str);
            if (a == null || a.a() != 1 || TextUtils.isEmpty(a.b())) {
                this.b.a(this.a, "upload pic faild");
                this.a.finish();
                return;
            }
            this.a.a(this.b.c(a.b()));
        }
    }

    private void d() {
        this.i.getSettings().setJavaScriptEnabled(true);
        if (a(this.n)) {
            this.i.getSettings().setUserAgentString(k.b(this));
        }
        this.i.getSettings().setSavePassword(false);
        this.i.setWebViewClient(this.o);
        this.i.setWebChromeClient(new a(this, null));
        this.i.requestFocus();
        this.i.setScrollBarStyle(0);
        if (Build.VERSION.SDK_INT >= 11) {
            this.i.removeJavascriptInterface("searchBoxJavaBridge_");
        } else {
            a(this.i);
        }
    }

    private void e() {
        this.h.setText(this.b);
        this.g.setOnClickListener(new AnonymousClass2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sina.weibo.sdk.component.WeiboSdkBrowser$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ WeiboSdkBrowser a;

        AnonymousClass2(WeiboSdkBrowser weiboSdkBrowser) {
            JniLib.cV(this, weiboSdkBrowser, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_CAMERA_LIFT_TRIGGER));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.a.n != null) {
                this.a.n.a(this.a, 3);
            }
            this.a.finish();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void f() {
        String str;
        if (!TextUtils.isEmpty(this.c)) {
            str = this.c;
        } else {
            str = !TextUtils.isEmpty(this.b) ? this.b : "";
        }
        this.h.setText(str);
    }

    private void g() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(-1);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setId(1);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        View h = h();
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-1, g.a(this, 2)));
        textView.setBackgroundDrawable(g.b(this, "weibosdk_common_shadow_top.9.png"));
        this.j = new LoadingBar(this);
        this.j.setBackgroundColor(0);
        this.j.a(0);
        this.j.setLayoutParams(new LinearLayout.LayoutParams(-1, g.a(this, 3)));
        linearLayout.addView(h);
        linearLayout.addView(textView);
        linearLayout.addView(this.j);
        this.i = new WebView(this);
        this.i.setBackgroundColor(-1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(3, 1);
        this.i.setLayoutParams(layoutParams);
        this.k = new LinearLayout(this);
        this.k.setVisibility(8);
        this.k.setOrientation(1);
        this.k.setGravity(17);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(3, 1);
        this.k.setLayoutParams(layoutParams2);
        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(g.a(this, "weibosdk_empty_failed.png"));
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        int a2 = g.a(this, 8);
        layoutParams3.bottomMargin = a2;
        layoutParams3.rightMargin = a2;
        layoutParams3.topMargin = a2;
        layoutParams3.leftMargin = a2;
        imageView.setLayoutParams(layoutParams3);
        this.k.addView(imageView);
        TextView textView2 = new TextView(this);
        textView2.setGravity(1);
        textView2.setTextColor(-4342339);
        textView2.setTextSize(2, 14.0f);
        textView2.setText(g.a(this, "A network error occurs, please tap the button to reload", "\u7f51\u7edc\u51fa\u9519\u5566\uff0c\u8bf7\u70b9\u51fb\u6309\u94ae\u91cd\u65b0\u52a0\u8f7d", "\u7db2\u8def\u51fa\u932f\u5566\uff0c\u8acb\u9ede\u64ca\u6309\u9215\u91cd\u65b0\u8f09\u5165"));
        textView2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.k.addView(textView2);
        this.l = new Button(this);
        this.l.setGravity(17);
        this.l.setTextColor(-8882056);
        this.l.setTextSize(2, 16.0f);
        this.l.setText(g.a(this, "channel_data_error", "\u91cd\u65b0\u52a0\u8f7d", "\u91cd\u65b0\u8f09\u5165"));
        this.l.setBackgroundDrawable(g.a(this, "weibosdk_common_button_alpha.9.png", "weibosdk_common_button_alpha_highlighted.9.png"));
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(g.a(this, 142), g.a(this, 46));
        layoutParams4.topMargin = g.a(this, 10);
        this.l.setLayoutParams(layoutParams4);
        this.l.setOnClickListener(new AnonymousClass3(this));
        this.k.addView(this.l);
        relativeLayout.addView(linearLayout);
        relativeLayout.addView(this.i);
        relativeLayout.addView(this.k);
        setContentView(relativeLayout);
        e();
    }

    /* renamed from: com.sina.weibo.sdk.component.WeiboSdkBrowser$3  reason: invalid class name */
    class AnonymousClass3 implements View.OnClickListener {
        final /* synthetic */ WeiboSdkBrowser a;

        AnonymousClass3(WeiboSdkBrowser weiboSdkBrowser) {
            JniLib.cV(this, weiboSdkBrowser, Integer.valueOf((int) MetricsProto.MetricsEvent.SETTINGS_CHOOSE_LOCK_DIALOG));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            WeiboSdkBrowser weiboSdkBrowser = this.a;
            weiboSdkBrowser.a(weiboSdkBrowser.e);
            this.a.f = false;
        }
    }

    private View h() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, g.a(this, 45)));
        relativeLayout.setBackgroundDrawable(g.b(this, "weibosdk_navigationbar_background.9.png"));
        this.g = new TextView(this);
        this.g.setClickable(true);
        this.g.setTextSize(2, 17.0f);
        this.g.setTextColor(g.a(-32256, 1728020992));
        this.g.setText(g.a(this, "Close", "\u5173\u95ed", "\u5173\u95ed"));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(5);
        layoutParams.addRule(15);
        layoutParams.leftMargin = g.a(this, 10);
        layoutParams.rightMargin = g.a(this, 10);
        this.g.setLayoutParams(layoutParams);
        relativeLayout.addView(this.g);
        this.h = new TextView(this);
        this.h.setTextSize(2, 18.0f);
        this.h.setTextColor(-11382190);
        this.h.setEllipsize(TextUtils.TruncateAt.END);
        this.h.setSingleLine(true);
        this.h.setGravity(17);
        this.h.setMaxWidth(g.a(this, 160));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13);
        this.h.setLayoutParams(layoutParams2);
        relativeLayout.addView(this.h);
        return relativeLayout;
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (this.d) {
            j();
        } else {
            i();
        }
    }

    private void i() {
        f();
        this.j.setVisibility(8);
    }

    private void j() {
        this.h.setText(g.a(this, "Loading....", "\u52a0\u8f7d\u4e2d....", "\u8f09\u5165\u4e2d...."));
        this.j.setVisibility(0);
    }

    private void b(WebView webView, int i, String str, String str2) {
        if (!str2.startsWith("sinaweibo")) {
            this.f = true;
            k();
        }
    }

    private void k() {
        this.k.setVisibility(0);
        this.i.setVisibility(8);
    }

    private void l() {
        this.k.setVisibility(8);
        this.i.setVisibility(0);
    }

    private class a extends WebChromeClient {
        private a() {
        }

        /* synthetic */ a(WeiboSdkBrowser weiboSdkBrowser, a aVar) {
            this();
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            WeiboSdkBrowser.this.j.a(i);
            if (i == 100) {
                WeiboSdkBrowser.this.d = false;
                WeiboSdkBrowser.this.a();
            } else if (!WeiboSdkBrowser.this.d) {
                WeiboSdkBrowser.this.d = true;
                WeiboSdkBrowser.this.a();
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            WeiboSdkBrowser weiboSdkBrowser = WeiboSdkBrowser.this;
            if (!weiboSdkBrowser.b(weiboSdkBrowser.e) && !WeiboSdkBrowser.this.m.booleanValue()) {
                WeiboSdkBrowser.this.c = str;
                WeiboSdkBrowser.this.f();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean b(String str) {
        if (!TextUtils.isEmpty(str) && "sinaweibo".equalsIgnoreCase(Uri.parse(str).getAuthority())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        f.e(this);
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        BrowserRequestParamBase browserRequestParamBase = this.n;
        if (browserRequestParamBase != null) {
            browserRequestParamBase.a(this, 3);
        }
        finish();
        return true;
    }

    private BrowserRequestParamBase a(Bundle bundle) {
        this.m = false;
        BrowserLauncher browserLauncher = (BrowserLauncher) bundle.getSerializable("key_launcher");
        if (browserLauncher == BrowserLauncher.AUTH) {
            AuthRequestParam authRequestParam = new AuthRequestParam(this);
            authRequestParam.c(bundle);
            a(authRequestParam);
            return authRequestParam;
        } else if (browserLauncher == BrowserLauncher.SHARE) {
            ShareRequestParam shareRequestParam = new ShareRequestParam(this);
            shareRequestParam.c(bundle);
            a(shareRequestParam);
            return shareRequestParam;
        } else if (browserLauncher == BrowserLauncher.WIDGET) {
            WidgetRequestParam widgetRequestParam = new WidgetRequestParam(this);
            widgetRequestParam.c(bundle);
            a(widgetRequestParam);
            return widgetRequestParam;
        } else if (browserLauncher != BrowserLauncher.GAME) {
            return null;
        } else {
            this.m = true;
            GameRequestParam gameRequestParam = new GameRequestParam(this);
            gameRequestParam.c(bundle);
            a(gameRequestParam);
            return gameRequestParam;
        }
    }

    private boolean a(BrowserRequestParamBase browserRequestParamBase) {
        return browserRequestParamBase != null && browserRequestParamBase.f() == BrowserLauncher.SHARE;
    }

    private void a(AuthRequestParam authRequestParam) {
        this.o = new a(this, authRequestParam);
        this.o.a(this);
    }

    private void a(ShareRequestParam shareRequestParam) {
        c cVar = new c(this, shareRequestParam);
        cVar.a(this);
        this.o = cVar;
    }

    private void a(WidgetRequestParam widgetRequestParam) {
        g gVar = new g(this, widgetRequestParam);
        gVar.a(this);
        this.o = gVar;
    }

    private void a(GameRequestParam gameRequestParam) {
        e eVar = new e(this, gameRequestParam);
        eVar.a(this);
        this.o = eVar;
    }

    @Override // com.sina.weibo.sdk.component.b
    public void a(WebView webView, String str, Bitmap bitmap) {
        String str2 = a;
        d.a(str2, "onPageStarted URL: " + str);
        this.e = str;
        if (!b(str)) {
            this.c = "";
        }
    }

    @Override // com.sina.weibo.sdk.component.b
    public boolean a(WebView webView, String str) {
        String str2 = a;
        d.b(str2, "shouldOverrideUrlLoading URL: " + str);
        return false;
    }

    @Override // com.sina.weibo.sdk.component.b
    public void b(WebView webView, String str) {
        String str2 = a;
        d.a(str2, "onPageFinished URL: " + str);
        if (this.f) {
            k();
            return;
        }
        this.f = false;
        l();
    }

    @Override // com.sina.weibo.sdk.component.b
    public void a(WebView webView, int i, String str, String str2) {
        String str3 = a;
        d.a(str3, "onReceivedError: errorCode = " + i + ", description = " + str + ", failingUrl = " + str2);
        b(webView, i, str, str2);
    }

    @Override // com.sina.weibo.sdk.component.b
    public void a(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        d.a(a, "onReceivedSslErrorCallBack.........");
    }

    public static void a(Activity activity, String str, String str2) {
        d a2 = d.a(activity.getApplicationContext());
        if (!TextUtils.isEmpty(str)) {
            a2.b(str);
            activity.finish();
        }
        if (!TextUtils.isEmpty(str2)) {
            a2.d(str2);
            activity.finish();
        }
    }

    public void a(WebView webView) {
        if (Build.VERSION.SDK_INT < 11) {
            try {
                webView.getClass().getDeclaredMethod("removeJavascriptInterface", new Class[0]).invoke("searchBoxJavaBridge_", new Object[0]);
            } catch (Exception e) {
                d.c(a, e.toString());
            }
        }
    }
}
