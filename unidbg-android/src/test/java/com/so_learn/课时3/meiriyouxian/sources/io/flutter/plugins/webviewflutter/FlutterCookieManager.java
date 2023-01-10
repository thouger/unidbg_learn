package io.flutter.plugins.webviewflutter;

import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

class FlutterCookieManager implements MethodChannel.MethodCallHandler {
    private final MethodChannel methodChannel;

    FlutterCookieManager(BinaryMessenger binaryMessenger) {
        this.methodChannel = new MethodChannel(binaryMessenger, "plugins.flutter.io/cookie_manager");
        this.methodChannel.setMethodCallHandler(this);
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        if (((str.hashCode() == 928375682 && str.equals("clearCookies")) ? (char) 0 : '\uffff') != 0) {
            result.notImplemented();
        } else {
            clearCookies(result);
        }
    }

    /* access modifiers changed from: package-private */
    public void dispose() {
        this.methodChannel.setMethodCallHandler(null);
    }

    private static void clearCookies(MethodChannel.Result result) {
        CookieManager instance = CookieManager.getInstance();
        boolean hasCookies = instance.hasCookies();
        if (Build.VERSION.SDK_INT >= 21) {
            instance.removeAllCookies(new AnonymousClass1(result, hasCookies));
            return;
        }
        instance.removeAllCookie();
        result.success(Boolean.valueOf(hasCookies));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: io.flutter.plugins.webviewflutter.FlutterCookieManager$1  reason: invalid class name */
    public static class AnonymousClass1 implements ValueCallback<Boolean> {
        final /* synthetic */ boolean val$hasCookies;
        final /* synthetic */ MethodChannel.Result val$result;

        AnonymousClass1(MethodChannel.Result result, boolean z) {
            this.val$result = result;
            this.val$hasCookies = z;
        }

        public void onReceiveValue(Boolean bool) {
            this.val$result.success(Boolean.valueOf(this.val$hasCookies));
        }
    }
}
