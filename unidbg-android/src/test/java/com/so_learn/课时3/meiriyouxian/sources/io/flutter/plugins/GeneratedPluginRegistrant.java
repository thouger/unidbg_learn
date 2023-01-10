package io.flutter.plugins;

import cn.missfresh.flutter.apm.flutter_apm_plugin.a;
import com.befovy.fijkplayer.b;
import com.tekartik.sqflite.c;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugins.deviceinfo.DeviceInfoPlugin;
import io.flutter.plugins.flutter_plugin_android_lifecycle.FlutterAndroidLifecyclePlugin;
import io.flutter.plugins.imagepicker.ImagePickerPlugin;
import io.flutter.plugins.pathprovider.PathProviderPlugin;
import io.flutter.plugins.sharedpreferences.SharedPreferencesPlugin;
import io.flutter.plugins.videoplayer.VideoPlayerPlugin;
import io.flutter.plugins.webviewflutter.WebViewFlutterPlugin;

public final class GeneratedPluginRegistrant {
    public static void registerWith(FlutterEngine flutterEngine) {
        flutterEngine.getPlugins().add(new DeviceInfoPlugin());
        flutterEngine.getPlugins().add(new b());
        flutterEngine.getPlugins().add(new a());
        flutterEngine.getPlugins().add(new FlutterAndroidLifecyclePlugin());
        flutterEngine.getPlugins().add(new ImagePickerPlugin());
        flutterEngine.getPlugins().add(new PathProviderPlugin());
        flutterEngine.getPlugins().add(new SharedPreferencesPlugin());
        flutterEngine.getPlugins().add(new c());
        flutterEngine.getPlugins().add(new VideoPlayerPlugin());
        flutterEngine.getPlugins().add(new WebViewFlutterPlugin());
    }
}
