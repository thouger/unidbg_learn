package io.flutter.plugins.videoplayer;

import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.videoplayer.VideoPlayerPlugin;

/* compiled from: lambda */
/* renamed from: io.flutter.plugins.videoplayer.-$$Lambda$EwJYt-rzRQQxrdKhXt12brhygJE  reason: invalid class name */
public final /* synthetic */ class $$Lambda$EwJYtrzRQQxrdKhXt12brhygJE implements VideoPlayerPlugin.KeyForAssetFn {
    private final /* synthetic */ PluginRegistry.Registrar f$0;

    public /* synthetic */ $$Lambda$EwJYtrzRQQxrdKhXt12brhygJE(PluginRegistry.Registrar registrar) {
        this.f$0 = registrar;
    }

    @Override // io.flutter.plugins.videoplayer.VideoPlayerPlugin.KeyForAssetFn
    public final String get(String str) {
        return this.f$0.lookupKeyForAsset(str);
    }
}
