package io.flutter.plugins.videoplayer;

import io.flutter.plugin.common.PluginRegistry;
import io.flutter.view.FlutterNativeView;

/* compiled from: lambda */
/* renamed from: io.flutter.plugins.videoplayer.-$$Lambda$VideoPlayerPlugin$W-nN4EDj3bf_Zgiih3xrq4EH41g  reason: invalid class name */
public final /* synthetic */ class $$Lambda$VideoPlayerPlugin$WnN4EDj3bf_Zgiih3xrq4EH41g implements PluginRegistry.ViewDestroyListener {
    private final /* synthetic */ VideoPlayerPlugin f$0;

    public /* synthetic */ $$Lambda$VideoPlayerPlugin$WnN4EDj3bf_Zgiih3xrq4EH41g(VideoPlayerPlugin videoPlayerPlugin) {
        this.f$0 = videoPlayerPlugin;
    }

    @Override // io.flutter.plugin.common.PluginRegistry.ViewDestroyListener
    public final boolean onViewDestroy(FlutterNativeView flutterNativeView) {
        return this.f$0.onDestroy();
    }
}
