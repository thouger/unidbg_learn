package io.flutter.plugins.videoplayer;

import io.flutter.embedding.engine.loader.FlutterLoader;
import io.flutter.plugins.videoplayer.VideoPlayerPlugin;

/* compiled from: lambda */
/* renamed from: io.flutter.plugins.videoplayer.-$$Lambda$0QI-K3gtGNVEPhJwBzAbMmpNPxY  reason: invalid class name */
public final /* synthetic */ class $$Lambda$0QIK3gtGNVEPhJwBzAbMmpNPxY implements VideoPlayerPlugin.KeyForAssetAndPackageName {
    private final /* synthetic */ FlutterLoader f$0;

    public /* synthetic */ $$Lambda$0QIK3gtGNVEPhJwBzAbMmpNPxY(FlutterLoader flutterLoader) {
        this.f$0 = flutterLoader;
    }

    @Override // io.flutter.plugins.videoplayer.VideoPlayerPlugin.KeyForAssetAndPackageName
    public final String get(String str, String str2) {
        return this.f$0.getLookupKeyForAsset(str, str2);
    }
}
