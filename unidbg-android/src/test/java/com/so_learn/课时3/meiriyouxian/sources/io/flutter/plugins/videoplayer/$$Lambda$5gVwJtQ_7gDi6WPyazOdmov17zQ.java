package io.flutter.plugins.videoplayer;

import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.videoplayer.VideoPlayerPlugin;

/* compiled from: lambda */
/* renamed from: io.flutter.plugins.videoplayer.-$$Lambda$5gVwJtQ_7gDi6WPyazOdmov17zQ  reason: invalid class name */
public final /* synthetic */ class $$Lambda$5gVwJtQ_7gDi6WPyazOdmov17zQ implements VideoPlayerPlugin.KeyForAssetAndPackageName {
    private final /* synthetic */ PluginRegistry.Registrar f$0;

    public /* synthetic */ $$Lambda$5gVwJtQ_7gDi6WPyazOdmov17zQ(PluginRegistry.Registrar registrar) {
        this.f$0 = registrar;
    }

    @Override // io.flutter.plugins.videoplayer.VideoPlayerPlugin.KeyForAssetAndPackageName
    public final String get(String str, String str2) {
        return this.f$0.lookupKeyForAsset(str, str2);
    }
}
