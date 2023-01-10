package com.alipay.sdk.widget;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.ThreadedRenderer;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.widget.ImageView;
import cn.missfresh.buttomline.logtrace.bean.ConstantKey;
import com.alipay.sdk.app.b;
import com.alipay.sdk.app.c;
import com.alipay.sdk.util.l;
import com.alipay.sdk.widget.e;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.umeng.message.common.inter.ITagManager;
import java.util.Map;
import org.json.JSONObject;

public class d extends c implements e.b, e.c, e.d {
    private boolean b = true;
    private String c = "GET";
    private boolean d = false;
    private final com.alipay.sdk.g.a e;
    private boolean f;
    private e g = null;
    private f h = new f();

    @Override // android.view.ViewGroup
    public synchronized boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.d ? true : super.onInterceptTouchEvent(motionEvent);
    }

    public d(Activity activity, com.alipay.sdk.g.a aVar, String str) {
        super(activity, str);
        this.e = aVar;
        e();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003f, code lost:
        return false;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean e() {
        /*
            r6 = this;
            monitor-enter(r6)
            r0 = 0
            com.alipay.sdk.widget.e$a r1 = new com.alipay.sdk.widget.e$a     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            boolean r2 = r6.c()     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            r3 = 1
            if (r2 != 0) goto L_0x000d
            r2 = r3
            goto L_0x000e
        L_0x000d:
            r2 = r0
        L_0x000e:
            boolean r4 = r6.c()     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            if (r4 != 0) goto L_0x0016
            r4 = r3
            goto L_0x0017
        L_0x0016:
            r4 = r0
        L_0x0017:
            r1.<init>(r2, r4)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            com.alipay.sdk.widget.e r2 = new com.alipay.sdk.widget.e     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            android.app.Activity r4 = r6.a     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            com.alipay.sdk.g.a r5 = r6.e     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            r2.<init>(r4, r5, r1)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            r6.g = r2     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            com.alipay.sdk.widget.e r1 = r6.g     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            r1.setChromeProxy(r6)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            com.alipay.sdk.widget.e r1 = r6.g     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            r1.setWebClientProxy(r6)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            com.alipay.sdk.widget.e r1 = r6.g     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            r1.setWebEventProxy(r6)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            com.alipay.sdk.widget.e r1 = r6.g     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            r6.addView(r1)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
            monitor-exit(r6)
            return r3
        L_0x003b:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        L_0x003e:
            monitor-exit(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.widget.d.e():boolean");
    }

    private synchronized void f() {
        Activity activity = this.a;
        e eVar = this.g;
        if (activity != null) {
            if (eVar != null) {
                if (this.b) {
                    activity.finish();
                } else {
                    eVar.a("javascript:window.AlipayJSBridge.callListener('h5BackAction');");
                }
            }
        }
    }

    private synchronized void g() {
        WebView webView = this.g.getWebView();
        if (webView.canGoBack()) {
            webView.goBack();
        } else if (this.h == null || this.h.b()) {
            a(false);
        } else {
            h();
        }
    }

    public synchronized void a(String str, String str2, boolean z) {
        this.c = str2;
        this.g.getTitle().setText(str);
        this.b = z;
    }

    private synchronized void a(boolean z) {
        b.a(z);
        this.a.finish();
    }

    public synchronized void a(String str) {
        if ("POST".equals(this.c)) {
            this.g.a(str, (byte[]) null);
        } else {
            this.g.a(str);
        }
        a(this.g.getWebView());
    }

    @Override // com.alipay.sdk.widget.c
    public synchronized void a() {
        this.g.a();
        this.h.c();
    }

    @Override // com.alipay.sdk.widget.c
    public synchronized boolean b() {
        Activity activity = this.a;
        if (activity == null) {
            return true;
        }
        if (c()) {
            e eVar = this.g;
            if (eVar == null || eVar.getWebView() == null) {
                activity.finish();
                return true;
            }
            if (!eVar.getWebView().canGoBack()) {
                b.a(b.c());
                activity.finish();
            } else if (d()) {
                c b = c.b(c.NETWORK_ERROR.a());
                b.a(b.a(b.a(), b.b(), ""));
                activity.finish();
            }
            return true;
        }
        if (!this.d) {
            f();
        }
        return true;
    }

    @Override // com.alipay.sdk.widget.e.b
    public synchronized boolean a(e eVar, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (str2.startsWith("<head>") && str2.contains("sdk_result_code:")) {
            this.a.runOnUiThread(new AnonymousClass1());
        }
        jsPromptResult.cancel();
        return true;
    }

    /* renamed from: com.alipay.sdk.widget.d$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.a.finish();
        }
    }

    @Override // com.alipay.sdk.widget.e.b
    public synchronized void a(e eVar, String str) {
        if (!str.startsWith("http") && !eVar.getUrl().endsWith(str)) {
            this.g.getTitle().setText(str);
        }
    }

    private synchronized boolean h() {
        if (this.h.b()) {
            this.a.finish();
        } else {
            this.d = true;
            e eVar = this.g;
            this.g = this.h.a();
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 1.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(400);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new AnonymousClass2(eVar));
            eVar.setAnimation(translateAnimation);
            removeView(eVar);
            addView(this.g);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.alipay.sdk.widget.d$2  reason: invalid class name */
    public class AnonymousClass2 extends a {
        final /* synthetic */ e a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass2(e eVar) {
            super(d.this, null);
            this.a = eVar;
        }

        @Override // com.alipay.sdk.widget.d.a, android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.a.a();
            d.this.d = false;
        }
    }

    private synchronized boolean b(String str, String str2) {
        e eVar = this.g;
        try {
            this.g = new e(this.a, this.e, new e.a(!c(), !c()));
            this.g.setChromeProxy(this);
            this.g.setWebClientProxy(this);
            this.g.setWebEventProxy(this);
            if (!TextUtils.isEmpty(str2)) {
                this.g.getTitle().setText(str2);
            }
            this.d = true;
            this.h.a(eVar);
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(400);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new AnonymousClass3(eVar, str));
            this.g.setAnimation(translateAnimation);
            addView(this.g);
        } catch (Throwable unused) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.alipay.sdk.widget.d$3  reason: invalid class name */
    public class AnonymousClass3 extends a {
        final /* synthetic */ e a;
        final /* synthetic */ String b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        AnonymousClass3(e eVar, String str) {
            super(d.this, null);
            this.a = eVar;
            this.b = str;
        }

        @Override // com.alipay.sdk.widget.d.a, android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            d.this.removeView(this.a);
            d.this.g.a(this.b);
            d.this.d = false;
        }
    }

    @Override // com.alipay.sdk.widget.e.c
    public synchronized boolean b(e eVar, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Activity activity = this.a;
        if (activity == null) {
            return true;
        }
        if (l.a(this.e, str, activity)) {
            return true;
        }
        if (str.startsWith("alipayjsbridge://")) {
            b(str.substring(17));
        } else if (TextUtils.equals(str, "sdklite://h5quit")) {
            a(false);
        } else if (str.startsWith("http://") || str.startsWith("https://")) {
            this.g.a(str);
        } else {
            try {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                activity.startActivity(intent);
            } catch (Throwable th) {
                com.alipay.sdk.app.a.a.a(this.e, "biz", th);
            }
        }
        return true;
    }

    @Override // com.alipay.sdk.widget.e.c
    public synchronized boolean c(e eVar, String str) {
        com.alipay.sdk.g.a aVar = this.e;
        com.alipay.sdk.app.a.a.b(aVar, "biz", "h5ld", SystemClock.elapsedRealtime() + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + l.e(str));
        return false;
    }

    @Override // com.alipay.sdk.widget.e.c
    public synchronized boolean d(e eVar, String str) {
        com.alipay.sdk.g.a aVar = this.e;
        com.alipay.sdk.app.a.a.b(aVar, "biz", "h5ldd", SystemClock.elapsedRealtime() + HiAnalyticsConstant.REPORT_VAL_SEPARATOR + l.e(str));
        eVar.a("javascript:window.prompt('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[h5JsCallbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n;window.AlipayJSBridge.callListener('h5PageFinished');");
        eVar.getRefreshButton().setVisibility(0);
        return true;
    }

    @Override // com.alipay.sdk.widget.e.c
    public synchronized boolean a(e eVar, int i, String str, String str2) {
        this.f = true;
        com.alipay.sdk.g.a aVar = this.e;
        com.alipay.sdk.app.a.a.a(aVar, ConstantKey.NET, "SSLError", "onReceivedError:" + str2);
        eVar.getRefreshButton().setVisibility(0);
        return false;
    }

    @Override // com.alipay.sdk.widget.e.c
    public synchronized boolean a(e eVar, SslErrorHandler sslErrorHandler, SslError sslError) {
        Activity activity = this.a;
        if (activity == null) {
            return true;
        }
        com.alipay.sdk.g.a aVar = this.e;
        com.alipay.sdk.app.a.a.a(aVar, ConstantKey.NET, "SSLError", "2-" + sslError);
        activity.runOnUiThread(new AnonymousClass4(activity, sslErrorHandler));
        return true;
    }

    /* renamed from: com.alipay.sdk.widget.d$4  reason: invalid class name */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ Activity a;
        final /* synthetic */ SslErrorHandler b;

        AnonymousClass4(Activity activity, SslErrorHandler sslErrorHandler) {
            this.a = activity;
            this.b = sslErrorHandler;
        }

        /* renamed from: com.alipay.sdk.widget.d$4$1  reason: invalid class name */
        class AnonymousClass1 implements DialogInterface.OnClickListener {
            AnonymousClass1() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                AnonymousClass4.this.b.cancel();
                com.alipay.sdk.app.a.a.a(d.this.e, ConstantKey.NET, "SSLDenied", "2");
                b.a(b.c());
                AnonymousClass4.this.a.finish();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            b.a(this.a, "\u5b89\u5168\u8b66\u544a", "\u5b89\u5168\u8fde\u63a5\u8bc1\u4e66\u6821\u9a8c\u65e0\u6548\uff0c\u5c06\u65e0\u6cd5\u4fdd\u8bc1\u8bbf\u95ee\u6570\u636e\u7684\u5b89\u5168\u6027\uff0c\u8bf7\u5b89\u88c5\u652f\u4ed8\u5b9d\u540e\u91cd\u8bd5\u3002", "\u786e\u5b9a", new AnonymousClass1(), null, null);
        }
    }

    public boolean d() {
        return this.f;
    }

    private synchronized void b(String str) {
        Map<String, String> a2 = l.a(this.e, str);
        if (str.startsWith("callNativeFunc")) {
            a(a2.get("func"), a2.get("cbId"), a2.get("data"));
        } else if (str.startsWith("onBack")) {
            g();
        } else if (str.startsWith("setTitle") && a2.containsKey("title")) {
            this.g.getTitle().setText(a2.get("title"));
        } else if (str.startsWith("onRefresh")) {
            this.g.getWebView().reload();
        } else if (str.startsWith("showBackButton") && a2.containsKey("bshow")) {
            this.g.getBackButton().setVisibility(TextUtils.equals(ITagManager.STATUS_TRUE, a2.get("bshow")) ? 0 : 4);
        } else if (str.startsWith("onExit")) {
            b.a(a2.get("result"));
            a(TextUtils.equals(ITagManager.STATUS_TRUE, a2.get("bsucc")));
        } else if (str.startsWith("onLoadJs")) {
            this.g.a("javascript:(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[h5JsCallbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n");
        }
    }

    private synchronized void a(String str, String str2, String str3) {
        e eVar = this.g;
        if (eVar != null) {
            JSONObject c = l.c(str3);
            if ("title".equals(str) && c.has("title")) {
                eVar.getTitle().setText(c.optString("title", ""));
            } else if ("refresh".equals(str)) {
                eVar.getWebView().reload();
            } else if ("back".equals(str)) {
                g();
            } else {
                int i = 0;
                if ("exit".equals(str)) {
                    b.a(c.optString("result", null));
                    a(c.optBoolean("success", false));
                } else if ("backButton".equals(str)) {
                    boolean optBoolean = c.optBoolean(ThreadedRenderer.OVERDRAW_PROPERTY_SHOW, true);
                    ImageView backButton = eVar.getBackButton();
                    if (!optBoolean) {
                        i = 4;
                    }
                    backButton.setVisibility(i);
                } else if ("refreshButton".equals(str)) {
                    boolean optBoolean2 = c.optBoolean(ThreadedRenderer.OVERDRAW_PROPERTY_SHOW, true);
                    ImageView refreshButton = eVar.getRefreshButton();
                    if (!optBoolean2) {
                        i = 4;
                    }
                    refreshButton.setVisibility(i);
                } else if ("pushWindow".equals(str) && c.optString("url", null) != null) {
                    b(c.optString("url"), c.optString("title", ""));
                } else if ("sdkInfo".equals(str)) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("sdk_version", "15.7.9");
                        jSONObject.put("app_name", this.e.b());
                        jSONObject.put("app_version", this.e.c());
                    } catch (Throwable th) {
                        com.alipay.sdk.app.a.a.a(this.e, "biz", "jInfoErr", th);
                    }
                    eVar.a(String.format("window.AlipayJSBridge.callBackFromNativeFunc('%s','%s');", str2, jSONObject.toString().replace("'", "")));
                }
            }
        }
    }

    @Override // com.alipay.sdk.widget.e.d
    public synchronized void a(e eVar) {
        f();
    }

    @Override // com.alipay.sdk.widget.e.d
    public synchronized void b(e eVar) {
        eVar.getWebView().reload();
        eVar.getRefreshButton().setVisibility(4);
    }

    private abstract class a implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        private a() {
        }

        /* synthetic */ a(d dVar, AnonymousClass1 r2) {
            this();
        }
    }
}
