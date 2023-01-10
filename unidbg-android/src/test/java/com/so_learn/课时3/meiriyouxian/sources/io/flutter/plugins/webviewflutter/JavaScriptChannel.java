package io.flutter.plugins.webviewflutter;

import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import io.flutter.plugin.common.MethodChannel;
import java.util.HashMap;

class JavaScriptChannel {
    private final String javaScriptChannelName;
    private final MethodChannel methodChannel;
    private final Handler platformThreadHandler;

    JavaScriptChannel(MethodChannel methodChannel, String str, Handler handler) {
        this.methodChannel = methodChannel;
        this.javaScriptChannelName = str;
        this.platformThreadHandler = handler;
    }

    /* renamed from: io.flutter.plugins.webviewflutter.JavaScriptChannel$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ String val$message;

        AnonymousClass1(String str) {
            this.val$message = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            HashMap hashMap = new HashMap();
            hashMap.put("channel", JavaScriptChannel.this.javaScriptChannelName);
            hashMap.put("message", this.val$message);
            JavaScriptChannel.this.methodChannel.invokeMethod("javascriptChannelMessage", hashMap);
        }
    }

    @JavascriptInterface
    public void postMessage(String str) {
        AnonymousClass1 r0 = new AnonymousClass1(str);
        if (this.platformThreadHandler.getLooper() == Looper.myLooper()) {
            r0.run();
        } else {
            this.platformThreadHandler.post(r0);
        }
    }
}
