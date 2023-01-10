package io.flutter.plugins.webviewflutter;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.PluginRegistry;

public class WebViewFlutterPlugin implements FlutterPlugin {
    private FlutterCookieManager flutterCookieManager;

    public static void registerWith(PluginRegistry.Registrar registrar) {
        registrar.platformViewRegistry().registerViewFactory("plugins.flutter.io/webview", new WebViewFactory(registrar.messenger(), registrar.view()));
        new FlutterCookieManager(registrar.messenger());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        BinaryMessenger binaryMessenger = flutterPluginBinding.getBinaryMessenger();
        flutterPluginBinding.getFlutterEngine().getPlatformViewsController().getRegistry().registerViewFactory("plugins.flutter.io/webview", new WebViewFactory(binaryMessenger, null));
        this.flutterCookieManager = new FlutterCookieManager(binaryMessenger);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        FlutterCookieManager flutterCookieManager = this.flutterCookieManager;
        if (flutterCookieManager != null) {
            flutterCookieManager.dispose();
            this.flutterCookieManager = null;
        }
    }
}
