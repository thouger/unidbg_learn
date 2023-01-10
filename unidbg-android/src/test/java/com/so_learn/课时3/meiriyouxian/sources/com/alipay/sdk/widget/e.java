package com.alipay.sdk.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.alipay.sdk.util.i;
import com.alipay.sdk.util.l;
import java.lang.reflect.Method;

public class e extends LinearLayout {
    private static Handler g = new Handler(Looper.getMainLooper());
    private ImageView a;
    private TextView b;
    private ImageView c;
    private ProgressBar d;
    private WebView e;
    private final a f;
    private b h;
    private c i;
    private d j;
    private final com.alipay.sdk.g.a k;
    private View.OnClickListener l;
    private final float m;

    public interface b {
        void a(e eVar, String str);

        boolean a(e eVar, String str, String str2, String str3, JsPromptResult jsPromptResult);
    }

    public interface c {
        boolean a(e eVar, int i, String str, String str2);

        boolean a(e eVar, SslErrorHandler sslErrorHandler, SslError sslError);

        boolean b(e eVar, String str);

        boolean c(e eVar, String str);

        boolean d(e eVar, String str);
    }

    public interface d {
        void a(e eVar);

        void b(e eVar);
    }

    /* renamed from: com.alipay.sdk.widget.e$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d dVar = e.this.j;
            if (dVar != null) {
                view.setEnabled(false);
                e.g.postDelayed(new AnonymousClass1(view), 256);
                if (view == e.this.a) {
                    dVar.a(e.this);
                } else if (view == e.this.c) {
                    dVar.b(e.this);
                }
            }
        }

        /* renamed from: com.alipay.sdk.widget.e$1$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ View a;

            AnonymousClass1(View view) {
                this.a = view;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.a.setEnabled(true);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class a {
        private boolean a;
        private boolean b;

        a(boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
        }
    }

    public e(Context context, com.alipay.sdk.g.a aVar, a aVar2) {
        this(context, null, aVar, aVar2);
    }

    public e(Context context, AttributeSet attributeSet, com.alipay.sdk.g.a aVar, a aVar2) {
        super(context, attributeSet);
        this.l = new AnonymousClass1();
        this.f = aVar2 == null ? new a(false, false) : aVar2;
        this.k = aVar;
        this.m = context.getResources().getDisplayMetrics().density;
        setOrientation(1);
        a(context);
        b(context);
        c(context);
    }

    private void a(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundColor(-218103809);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        linearLayout.setVisibility(this.f.a ? 0 : 8);
        this.a = new ImageView(context);
        this.a.setOnClickListener(this.l);
        this.a.setScaleType(ImageView.ScaleType.CENTER);
        this.a.setImageDrawable(i.a("iVBORw0KGgoAAAANSUhEUgAAAEgAAABIBAMAAACnw650AAAAFVBMVEUAAAARjusRkOkQjuoRkeoRj+oQjunya570AAAABnRSTlMAinWeSkk7CjRNAAAAZElEQVRIx+3MOw6AIBQF0YsrMDGx1obaLeGH/S9BQgkJ82rypp4ceTN1ilvyKizmZIAyU7FML0JVYig55BBAfQ2EU4V4CpZJ+2AiSj11C6rUoTannBpRn4W6xNQjLBSI2+TN0w/+3HT2wPClrQAAAABJRU5ErkJggg==", context));
        this.a.setPadding(a(12), 0, a(12), 0);
        linearLayout.addView(this.a, new LinearLayout.LayoutParams(-2, -2));
        View view = new View(context);
        view.setBackgroundColor(-2500135);
        linearLayout.addView(view, new LinearLayout.LayoutParams(a(1), a(25)));
        this.b = new TextView(context);
        this.b.setTextColor(-15658735);
        this.b.setTextSize(17.0f);
        this.b.setMaxLines(1);
        this.b.setEllipsize(TextUtils.TruncateAt.END);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(a(17), 0, 0, 0);
        layoutParams.weight = 1.0f;
        linearLayout.addView(this.b, layoutParams);
        this.c = new ImageView(context);
        this.c.setOnClickListener(this.l);
        this.c.setScaleType(ImageView.ScaleType.CENTER);
        this.c.setImageDrawable(i.a("iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAMAAABiM0N1AAAAmVBMVEUAAAARj+oQjuoRkOsVk/AQj+oRjuoQj+oSkO3///8Rj+kRj+oQkOsTk+whm/8Qj+oRj+oQj+oSkus2p/8QjuoQj+oQj+oQj+oQj+oRj+oTkuwRj+oQj+oRj+oRj+oSkOsSkO0ZlfMbk+8XnPgQj+oRj+oQj+oQj+sSj+sRkOoSkescqv8Rj+oQj+oSj+sXku4Rj+kQjuoQjumXGBCVAAAAMnRSTlMAxPtPF8ry7CoB9npbGwe6lm0wBODazb1+aSejm5GEYjcTDwvls6uJc0g/CdWfRCF20AXrk5QAAAJqSURBVFjD7ZfXmpswEIUFphmDCxi3talurGvm/R8uYSDe5FNBwlzsxf6XmvFBmiaZ/PCdWDk9CWn61OhHCMAaXfoRAth7wx6EkMXnWyrho4yg4bDpquI8Jy78Q7eoj9cmUFijsaLM0JsD9CD0uQAa9aNdPuCFvbA7B9t/Becap8Pu6Q/2jcyH81VHc/WCHDQZXwbvtUhQ61iDlqadncU6Rp31yGkZIzOAu7AjtPpYGREzq/pY5DRFHS1siyO6HfkOKTrMjdb2qevV4zosK7MbkFY2LmYk55hL6juCIFWMOI2KGzblmho3b18EIbxL1hs6r5m2Q2WaEElwS3NW4xh6ZZJuzTtUsBKT4G0h35s4y1mNgkNoS6TZ8SKBXTZQGBNYdPTozXGYKoyLAmOasttjThT4xT6Ch+2qIjRhV9Ja3NC87Kyo5We1vCNEMW1T+j1VLZ9UhE54Q1DL52r5piJ0YxdegvWlHOwTu76uKkJX+MOTHno4YFSEbHYdhViojsLrCTg/MKnhKWaEYzvkZFM8aOkPH7iTSvoFZKD7jGEJbarkRaxQyOeWvGVIbsji152jK7TbDgRzcIuz7SGj89BFU8d30TqWeDtrILxyTkD1IXfvmHseuU3lVHDz607bw0f3xDqejm5ncd0j8VDwfoibRy8RcgTkWHBvocbDbMlJsQAkGnAOHwGy90kLmQY1Wkob07/GaCNRIzdoWK7/+6y/XkLDJCcynOGFuUrKIMuCMonNr9VpSOQoIxBgJ0SacGbzZNy4ICrkscvU2fpElYz+U3sd+aQThjfVmjNa5i15kLcojM3Gz8kP34jf4VaV3X55gNEAAAAASUVORK5CYII=", context));
        this.c.setPadding(a(12), 0, a(12), 0);
        linearLayout.addView(this.c, new LinearLayout.LayoutParams(-2, -2));
        addView(linearLayout, new LinearLayout.LayoutParams(-1, a(48)));
    }

    private void b(Context context) {
        this.d = new ProgressBar(context, null, 16973855);
        this.d.setProgressDrawable(context.getResources().getDrawable(17301612));
        this.d.setMax(100);
        this.d.setBackgroundColor(-218103809);
        addView(this.d, new LinearLayout.LayoutParams(-1, a(2)));
    }

    private void c(Context context) {
        this.e = new WebView(context);
        this.e.setVerticalScrollbarOverlay(true);
        a(this.e, context);
        WebSettings settings = this.e.getSettings();
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setUseWideViewPort(true);
        settings.setAppCacheMaxSize(5242880);
        settings.setAppCachePath(context.getCacheDir().getAbsolutePath());
        settings.setAllowFileAccess(false);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        settings.setAppCacheEnabled(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setCacheMode(1);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(false);
        this.e.setVerticalScrollbarOverlay(true);
        this.e.setDownloadListener(new AnonymousClass2(context));
        try {
            this.e.removeJavascriptInterface("searchBoxJavaBridge_");
            this.e.removeJavascriptInterface(Context.ACCESSIBILITY_SERVICE);
            this.e.removeJavascriptInterface("accessibilityTraversal");
        } catch (Exception unused) {
            Method method = this.e.getClass().getMethod("removeJavascriptInterface", new Class[0]);
            if (method != null) {
                method.invoke(this.e, "searchBoxJavaBridge_");
                method.invoke(this.e, Context.ACCESSIBILITY_SERVICE);
                method.invoke(this.e, "accessibilityTraversal");
            }
        } catch (Throwable unused2) {
        }
        c.a(this.e);
        addView(this.e, new LinearLayout.LayoutParams(-1, -1));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.alipay.sdk.widget.e$2  reason: invalid class name */
    public class AnonymousClass2 implements DownloadListener {
        final /* synthetic */ Context a;

        AnonymousClass2(Context context) {
            this.a = context;
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent.setFlags(268435456);
                this.a.startActivity(intent);
            } catch (Throwable unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(WebView webView, Context context) {
        String userAgentString = webView.getSettings().getUserAgentString();
        WebSettings settings = webView.getSettings();
        settings.setUserAgentString(userAgentString + l.c(context));
    }

    public void setChromeProxy(b bVar) {
        this.h = bVar;
        if (bVar == null) {
            this.e.setWebChromeClient(null);
        } else {
            this.e.setWebChromeClient(new AnonymousClass3());
        }
    }

    /* renamed from: com.alipay.sdk.widget.e$3  reason: invalid class name */
    class AnonymousClass3 extends WebChromeClient {
        AnonymousClass3() {
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            if (!e.this.f.b) {
                e.this.d.setVisibility(8);
            } else if (i > 90) {
                e.this.d.setVisibility(4);
            } else {
                if (e.this.d.getVisibility() == 4) {
                    e.this.d.setVisibility(0);
                }
                e.this.d.setProgress(i);
            }
        }

        @Override // android.webkit.WebChromeClient
        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            return e.this.h.a(e.this, str, str2, str3, jsPromptResult);
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            e.this.h.a(e.this, str);
        }
    }

    public void setWebClientProxy(c cVar) {
        this.i = cVar;
        if (cVar == null) {
            this.e.setWebViewClient(null);
        } else {
            this.e.setWebViewClient(new AnonymousClass4());
        }
    }

    /* renamed from: com.alipay.sdk.widget.e$4  reason: invalid class name */
    class AnonymousClass4 extends WebViewClient {
        AnonymousClass4() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!e.this.i.b(e.this, str)) {
                return super.shouldOverrideUrlLoading(webView, str);
            }
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (!e.this.i.c(e.this, str)) {
                super.onPageFinished(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            if (!e.this.i.d(e.this, str)) {
                super.onPageFinished(webView, str);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (!e.this.i.a(e.this, i, str, str2)) {
                super.onReceivedError(webView, i, str, str2);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (!e.this.i.a(e.this, sslErrorHandler, sslError)) {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            }
        }
    }

    public void setWebEventProxy(d dVar) {
        this.j = dVar;
    }

    public String getUrl() {
        return this.e.getUrl();
    }

    public void a(String str) {
        this.e.loadUrl(str);
        c.a(this.e);
    }

    public void a(String str, byte[] bArr) {
        this.e.postUrl(str, bArr);
    }

    public ImageView getBackButton() {
        return this.a;
    }

    public TextView getTitle() {
        return this.b;
    }

    public ImageView getRefreshButton() {
        return this.c;
    }

    public ProgressBar getProgressbar() {
        return this.d;
    }

    public WebView getWebView() {
        return this.e;
    }

    public void a() {
        removeAllViews();
        this.e.removeAllViews();
        this.e.setWebViewClient(null);
        this.e.setWebChromeClient(null);
        this.e.destroy();
    }

    private int a(int i) {
        return (int) (((float) i) * this.m);
    }
}
