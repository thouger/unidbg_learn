package com.cmic.sso.wy.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.cmic.sso.wy.utils.x;

/* compiled from: ServerClauseDialog */
public class a extends Dialog {
    private WebView a;
    private String b;
    private String c;

    public a(Context context, int i, String str, String str2) {
        super(context, i);
        this.c = str;
        this.b = str2;
        a();
    }

    /* access modifiers changed from: protected */
    public void a() {
        requestWindowFeature(1);
        getWindow().setFeatureDrawableAlpha(0, 0);
        com.cmic.sso.wy.a a = com.cmic.sso.wy.b.a.a((Context) null).a();
        if (Build.VERSION.SDK_INT >= 21 && a.a() != 0) {
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().clearFlags(67108864);
            getWindow().setStatusBarColor(a.a());
            getWindow().setNavigationBarColor(a.a());
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (a.b()) {
                getWindow().getDecorView().setSystemUiVisibility(8192);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(0);
            }
        }
        setContentView(b());
        if (Build.VERSION.SDK_INT < 17) {
            this.a.removeJavascriptInterface("searchBoxJavaBridge_");
            this.a.removeJavascriptInterface(Context.ACCESSIBILITY_SERVICE);
            this.a.removeJavascriptInterface("accessibilityTraversal");
        }
        this.a.setWebViewClient(new AnonymousClass1());
        this.a.loadUrl(this.b);
    }

    /* compiled from: ServerClauseDialog */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.widget.a$1  reason: invalid class name */
    public class AnonymousClass1 extends WebViewClient {
        AnonymousClass1() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            a.this.a.loadUrl(str);
            return true;
        }
    }

    private ViewGroup b() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        int e = com.cmic.sso.wy.b.a.a((Context) null).a().e();
        String str = TextUtils.isEmpty(this.c) ? "\u670d\u52a1\u6761\u6b3e" : this.c;
        if (e != -1) {
            linearLayout.addView(x.a(getContext(), getLayoutInflater().inflate(e, (ViewGroup) linearLayout, false), 1118481, 0, str, (View.OnClickListener) null));
        } else {
            linearLayout.addView(x.a(getContext(), (View) null, 1118481, 2236962, str, new AnonymousClass2()));
        }
        this.a = new WebView(getContext());
        this.a.getSettings().setJavaScriptEnabled(true);
        linearLayout.addView(this.a, new LinearLayout.LayoutParams(-1, -1));
        return linearLayout;
    }

    /* compiled from: ServerClauseDialog */
    /* access modifiers changed from: package-private */
    /* renamed from: com.cmic.sso.wy.widget.a$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a.this.a.stopLoading();
            a.this.cancel();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        this.a.stopLoading();
    }
}
