package io.flutter.plugins.videoplayer;

import io.flutter.embedding.engine.loader.FlutterLoader;
import io.flutter.plugins.videoplayer.VideoPlayerPlugin;

/* compiled from: lambda */
/* renamed from: io.flutter.plugins.videoplayer.-$$Lambda$8dGMholTy4-jNNZa8ZEMagMWD34  reason: invalid class name */
public final /* synthetic */ class $$Lambda$8dGMholTy4jNNZa8ZEMagMWD34 implements VideoPlayerPlugin.KeyForAssetFn {
    private final /* synthetic */ FlutterLoader f$0;

    public /* synthetic */ $$Lambda$8dGMholTy4jNNZa8ZEMagMWD34(FlutterLoader flutterLoader) {
        this.f$0 = flutterLoader;
    }

    @Override // io.flutter.plugins.videoplayer.VideoPlayerPlugin.KeyForAssetFn
    public final String get(String str) {
        return this.f$0.getLookupKeyForAsset(str);
    }
}
