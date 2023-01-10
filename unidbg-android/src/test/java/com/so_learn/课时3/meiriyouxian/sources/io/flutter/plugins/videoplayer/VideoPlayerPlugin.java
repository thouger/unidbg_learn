package io.flutter.plugins.videoplayer;

import android.content.Context;
import android.util.Log;
import android.util.LongSparseArray;
import io.flutter.embedding.engine.loader.FlutterLoader;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.videoplayer.Messages;
import io.flutter.view.TextureRegistry;

public class VideoPlayerPlugin implements FlutterPlugin, Messages.VideoPlayerApi {
    private static final String TAG = "VideoPlayerPlugin";
    private FlutterState flutterState;
    private VideoPlayerOptions options = new VideoPlayerOptions();
    private final LongSparseArray<VideoPlayer> videoPlayers = new LongSparseArray<>();

    /* access modifiers changed from: private */
    public interface KeyForAssetAndPackageName {
        String get(String str, String str2);
    }

    /* access modifiers changed from: private */
    public interface KeyForAssetFn {
        String get(String str);
    }

    public VideoPlayerPlugin() {
    }

    private VideoPlayerPlugin(PluginRegistry.Registrar registrar) {
        Context context = registrar.context();
        BinaryMessenger messenger = registrar.messenger();
        registrar.getClass();
        $$Lambda$EwJYtrzRQQxrdKhXt12brhygJE r4 = new $$Lambda$EwJYtrzRQQxrdKhXt12brhygJE(registrar);
        registrar.getClass();
        this.flutterState = new FlutterState(context, messenger, r4, new $$Lambda$5gVwJtQ_7gDi6WPyazOdmov17zQ(registrar), registrar.textures());
        this.flutterState.startListening(this, registrar.messenger());
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        registrar.addViewDestroyListener(new $$Lambda$VideoPlayerPlugin$WnN4EDj3bf_Zgiih3xrq4EH41g(new VideoPlayerPlugin(registrar)));
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        FlutterLoader instance = FlutterLoader.getInstance();
        Context applicationContext = flutterPluginBinding.getApplicationContext();
        BinaryMessenger binaryMessenger = flutterPluginBinding.getBinaryMessenger();
        instance.getClass();
        $$Lambda$8dGMholTy4jNNZa8ZEMagMWD34 r4 = new $$Lambda$8dGMholTy4jNNZa8ZEMagMWD34(instance);
        instance.getClass();
        this.flutterState = new FlutterState(applicationContext, binaryMessenger, r4, new $$Lambda$0QIK3gtGNVEPhJwBzAbMmpNPxY(instance), flutterPluginBinding.getTextureRegistry());
        this.flutterState.startListening(this, flutterPluginBinding.getBinaryMessenger());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        if (this.flutterState == null) {
            Log.wtf(TAG, "Detached from the engine before registering to it.");
        }
        this.flutterState.stopListening(flutterPluginBinding.getBinaryMessenger());
        this.flutterState = null;
    }

    private void disposeAllPlayers() {
        for (int i = 0; i < this.videoPlayers.size(); i++) {
            ((VideoPlayer) this.videoPlayers.valueAt(i)).dispose();
        }
        this.videoPlayers.clear();
    }

    /* access modifiers changed from: private */
    public void onDestroy() {
        disposeAllPlayers();
    }

    @Override // io.flutter.plugins.videoplayer.Messages.VideoPlayerApi
    public void initialize() {
        disposeAllPlayers();
    }

    @Override // io.flutter.plugins.videoplayer.Messages.VideoPlayerApi
    public Messages.TextureMessage create(Messages.CreateMessage createMessage) {
        String str;
        TextureRegistry.SurfaceTextureEntry createSurfaceTexture = this.flutterState.textureRegistry.createSurfaceTexture();
        BinaryMessenger binaryMessenger = this.flutterState.binaryMessenger;
        EventChannel eventChannel = new EventChannel(binaryMessenger, "flutter.io/videoPlayer/videoEvents" + createSurfaceTexture.id());
        if (createMessage.getAsset() != null) {
            if (createMessage.getPackageName() != null) {
                str = this.flutterState.keyForAssetAndPackageName.get(createMessage.getAsset(), createMessage.getPackageName());
            } else {
                str = this.flutterState.keyForAsset.get(createMessage.getAsset());
            }
            Context context = this.flutterState.applicationContext;
            this.videoPlayers.put(createSurfaceTexture.id(), new VideoPlayer(context, eventChannel, createSurfaceTexture, "asset:///" + str, null, this.options));
        } else {
            this.videoPlayers.put(createSurfaceTexture.id(), new VideoPlayer(this.flutterState.applicationContext, eventChannel, createSurfaceTexture, createMessage.getUri(), createMessage.getFormatHint(), this.options));
        }
        Messages.TextureMessage textureMessage = new Messages.TextureMessage();
        textureMessage.setTextureId(Long.valueOf(createSurfaceTexture.id()));
        return textureMessage;
    }

    @Override // io.flutter.plugins.videoplayer.Messages.VideoPlayerApi
    public void dispose(Messages.TextureMessage textureMessage) {
        ((VideoPlayer) this.videoPlayers.get(textureMessage.getTextureId().longValue())).dispose();
        this.videoPlayers.remove(textureMessage.getTextureId().longValue());
    }

    @Override // io.flutter.plugins.videoplayer.Messages.VideoPlayerApi
    public void setLooping(Messages.LoopingMessage loopingMessage) {
        ((VideoPlayer) this.videoPlayers.get(loopingMessage.getTextureId().longValue())).setLooping(loopingMessage.getIsLooping().booleanValue());
    }

    @Override // io.flutter.plugins.videoplayer.Messages.VideoPlayerApi
    public void setVolume(Messages.VolumeMessage volumeMessage) {
        ((VideoPlayer) this.videoPlayers.get(volumeMessage.getTextureId().longValue())).setVolume(volumeMessage.getVolume().doubleValue());
    }

    @Override // io.flutter.plugins.videoplayer.Messages.VideoPlayerApi
    public void play(Messages.TextureMessage textureMessage) {
        ((VideoPlayer) this.videoPlayers.get(textureMessage.getTextureId().longValue())).play();
    }

    @Override // io.flutter.plugins.videoplayer.Messages.VideoPlayerApi
    public Messages.PositionMessage position(Messages.TextureMessage textureMessage) {
        VideoPlayer videoPlayer = (VideoPlayer) this.videoPlayers.get(textureMessage.getTextureId().longValue());
        Messages.PositionMessage positionMessage = new Messages.PositionMessage();
        positionMessage.setPosition(Long.valueOf(videoPlayer.getPosition()));
        videoPlayer.sendBufferingUpdate();
        return positionMessage;
    }

    @Override // io.flutter.plugins.videoplayer.Messages.VideoPlayerApi
    public void seekTo(Messages.PositionMessage positionMessage) {
        ((VideoPlayer) this.videoPlayers.get(positionMessage.getTextureId().longValue())).seekTo(positionMessage.getPosition().intValue());
    }

    @Override // io.flutter.plugins.videoplayer.Messages.VideoPlayerApi
    public void pause(Messages.TextureMessage textureMessage) {
        ((VideoPlayer) this.videoPlayers.get(textureMessage.getTextureId().longValue())).pause();
    }

    @Override // io.flutter.plugins.videoplayer.Messages.VideoPlayerApi
    public void setMixWithOthers(Messages.MixWithOthersMessage mixWithOthersMessage) {
        this.options.mixWithOthers = mixWithOthersMessage.getMixWithOthers().booleanValue();
    }

    private static final class FlutterState {
        private final Context applicationContext;
        private final BinaryMessenger binaryMessenger;
        private final KeyForAssetFn keyForAsset;
        private final KeyForAssetAndPackageName keyForAssetAndPackageName;
        private final TextureRegistry textureRegistry;

        FlutterState(Context context, BinaryMessenger binaryMessenger, KeyForAssetFn keyForAssetFn, KeyForAssetAndPackageName keyForAssetAndPackageName, TextureRegistry textureRegistry) {
            this.applicationContext = context;
            this.binaryMessenger = binaryMessenger;
            this.keyForAsset = keyForAssetFn;
            this.keyForAssetAndPackageName = keyForAssetAndPackageName;
            this.textureRegistry = textureRegistry;
        }

        /* access modifiers changed from: package-private */
        public void startListening(VideoPlayerPlugin videoPlayerPlugin, BinaryMessenger binaryMessenger) {
            Messages.VideoPlayerApi.CC.setup(binaryMessenger, videoPlayerPlugin);
        }

        /* access modifiers changed from: package-private */
        public void stopListening(BinaryMessenger binaryMessenger) {
            Messages.VideoPlayerApi.CC.setup(binaryMessenger, null);
        }
    }
}
