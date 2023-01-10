package io.flutter.plugins.videoplayer;

import android.provider.BrowserContract;
import com.taobao.accs.common.Constants;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;
import java.util.HashMap;

public class Messages {

    public static class TextureMessage {
        private Long textureId;

        public Long getTextureId() {
            return this.textureId;
        }

        public void setTextureId(Long l) {
            this.textureId = l;
        }

        /* access modifiers changed from: package-private */
        public HashMap toMap() {
            HashMap hashMap = new HashMap();
            hashMap.put("textureId", this.textureId);
            return hashMap;
        }

        static TextureMessage fromMap(HashMap hashMap) {
            long j;
            TextureMessage textureMessage = new TextureMessage();
            if (hashMap.get("textureId") instanceof Integer) {
                j = (long) ((Integer) hashMap.get("textureId")).intValue();
            } else {
                j = ((Long) hashMap.get("textureId")).longValue();
            }
            textureMessage.textureId = Long.valueOf(j);
            return textureMessage;
        }
    }

    public static class CreateMessage {
        private String asset;
        private String formatHint;
        private String packageName;
        private String uri;

        public String getAsset() {
            return this.asset;
        }

        public void setAsset(String str) {
            this.asset = str;
        }

        public String getUri() {
            return this.uri;
        }

        public void setUri(String str) {
            this.uri = str;
        }

        public String getPackageName() {
            return this.packageName;
        }

        public void setPackageName(String str) {
            this.packageName = str;
        }

        public String getFormatHint() {
            return this.formatHint;
        }

        public void setFormatHint(String str) {
            this.formatHint = str;
        }

        /* access modifiers changed from: package-private */
        public HashMap toMap() {
            HashMap hashMap = new HashMap();
            hashMap.put("asset", this.asset);
            hashMap.put("uri", this.uri);
            hashMap.put("packageName", this.packageName);
            hashMap.put("formatHint", this.formatHint);
            return hashMap;
        }

        static CreateMessage fromMap(HashMap hashMap) {
            CreateMessage createMessage = new CreateMessage();
            createMessage.asset = (String) hashMap.get("asset");
            createMessage.uri = (String) hashMap.get("uri");
            createMessage.packageName = (String) hashMap.get("packageName");
            createMessage.formatHint = (String) hashMap.get("formatHint");
            return createMessage;
        }
    }

    public static class LoopingMessage {
        private Boolean isLooping;
        private Long textureId;

        public Long getTextureId() {
            return this.textureId;
        }

        public void setTextureId(Long l) {
            this.textureId = l;
        }

        public Boolean getIsLooping() {
            return this.isLooping;
        }

        public void setIsLooping(Boolean bool) {
            this.isLooping = bool;
        }

        /* access modifiers changed from: package-private */
        public HashMap toMap() {
            HashMap hashMap = new HashMap();
            hashMap.put("textureId", this.textureId);
            hashMap.put("isLooping", this.isLooping);
            return hashMap;
        }

        static LoopingMessage fromMap(HashMap hashMap) {
            long j;
            LoopingMessage loopingMessage = new LoopingMessage();
            if (hashMap.get("textureId") instanceof Integer) {
                j = (long) ((Integer) hashMap.get("textureId")).intValue();
            } else {
                j = ((Long) hashMap.get("textureId")).longValue();
            }
            loopingMessage.textureId = Long.valueOf(j);
            loopingMessage.isLooping = (Boolean) hashMap.get("isLooping");
            return loopingMessage;
        }
    }

    public static class VolumeMessage {
        private Long textureId;
        private Double volume;

        public Long getTextureId() {
            return this.textureId;
        }

        public void setTextureId(Long l) {
            this.textureId = l;
        }

        public Double getVolume() {
            return this.volume;
        }

        public void setVolume(Double d) {
            this.volume = d;
        }

        /* access modifiers changed from: package-private */
        public HashMap toMap() {
            HashMap hashMap = new HashMap();
            hashMap.put("textureId", this.textureId);
            hashMap.put("volume", this.volume);
            return hashMap;
        }

        static VolumeMessage fromMap(HashMap hashMap) {
            long j;
            VolumeMessage volumeMessage = new VolumeMessage();
            if (hashMap.get("textureId") instanceof Integer) {
                j = (long) ((Integer) hashMap.get("textureId")).intValue();
            } else {
                j = ((Long) hashMap.get("textureId")).longValue();
            }
            volumeMessage.textureId = Long.valueOf(j);
            volumeMessage.volume = (Double) hashMap.get("volume");
            return volumeMessage;
        }
    }

    public static class PositionMessage {
        private Long position;
        private Long textureId;

        public Long getTextureId() {
            return this.textureId;
        }

        public void setTextureId(Long l) {
            this.textureId = l;
        }

        public Long getPosition() {
            return this.position;
        }

        public void setPosition(Long l) {
            this.position = l;
        }

        /* access modifiers changed from: package-private */
        public HashMap toMap() {
            HashMap hashMap = new HashMap();
            hashMap.put("textureId", this.textureId);
            hashMap.put(BrowserContract.Bookmarks.POSITION, this.position);
            return hashMap;
        }

        static PositionMessage fromMap(HashMap hashMap) {
            long j;
            long j2;
            PositionMessage positionMessage = new PositionMessage();
            if (hashMap.get("textureId") instanceof Integer) {
                j = (long) ((Integer) hashMap.get("textureId")).intValue();
            } else {
                j = ((Long) hashMap.get("textureId")).longValue();
            }
            positionMessage.textureId = Long.valueOf(j);
            if (hashMap.get(BrowserContract.Bookmarks.POSITION) instanceof Integer) {
                j2 = (long) ((Integer) hashMap.get(BrowserContract.Bookmarks.POSITION)).intValue();
            } else {
                j2 = ((Long) hashMap.get(BrowserContract.Bookmarks.POSITION)).longValue();
            }
            positionMessage.position = Long.valueOf(j2);
            return positionMessage;
        }
    }

    public static class MixWithOthersMessage {
        private Boolean mixWithOthers;

        public Boolean getMixWithOthers() {
            return this.mixWithOthers;
        }

        public void setMixWithOthers(Boolean bool) {
            this.mixWithOthers = bool;
        }

        /* access modifiers changed from: package-private */
        public HashMap toMap() {
            HashMap hashMap = new HashMap();
            hashMap.put("mixWithOthers", this.mixWithOthers);
            return hashMap;
        }

        static MixWithOthersMessage fromMap(HashMap hashMap) {
            MixWithOthersMessage mixWithOthersMessage = new MixWithOthersMessage();
            mixWithOthersMessage.mixWithOthers = (Boolean) hashMap.get("mixWithOthers");
            return mixWithOthersMessage;
        }
    }

    public interface VideoPlayerApi {
        TextureMessage create(CreateMessage createMessage);

        void dispose(TextureMessage textureMessage);

        void initialize();

        void pause(TextureMessage textureMessage);

        void play(TextureMessage textureMessage);

        PositionMessage position(TextureMessage textureMessage);

        void seekTo(PositionMessage positionMessage);

        void setLooping(LoopingMessage loopingMessage);

        void setMixWithOthers(MixWithOthersMessage mixWithOthersMessage);

        void setVolume(VolumeMessage volumeMessage);

        /* renamed from: io.flutter.plugins.videoplayer.Messages$VideoPlayerApi$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void setup(BinaryMessenger binaryMessenger, VideoPlayerApi videoPlayerApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.VideoPlayerApi.initialize", new StandardMessageCodec());
                if (videoPlayerApi != null) {
                    basicMessageChannel.setMessageHandler(new AnonymousClass1(videoPlayerApi));
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel2 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.VideoPlayerApi.create", new StandardMessageCodec());
                if (videoPlayerApi != null) {
                    basicMessageChannel2.setMessageHandler(new AnonymousClass2(videoPlayerApi));
                } else {
                    basicMessageChannel2.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel3 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.VideoPlayerApi.dispose", new StandardMessageCodec());
                if (videoPlayerApi != null) {
                    basicMessageChannel3.setMessageHandler(new AnonymousClass3(videoPlayerApi));
                } else {
                    basicMessageChannel3.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel4 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.VideoPlayerApi.setLooping", new StandardMessageCodec());
                if (videoPlayerApi != null) {
                    basicMessageChannel4.setMessageHandler(new AnonymousClass4(videoPlayerApi));
                } else {
                    basicMessageChannel4.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel5 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.VideoPlayerApi.setVolume", new StandardMessageCodec());
                if (videoPlayerApi != null) {
                    basicMessageChannel5.setMessageHandler(new AnonymousClass5(videoPlayerApi));
                } else {
                    basicMessageChannel5.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel6 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.VideoPlayerApi.play", new StandardMessageCodec());
                if (videoPlayerApi != null) {
                    basicMessageChannel6.setMessageHandler(new AnonymousClass6(videoPlayerApi));
                } else {
                    basicMessageChannel6.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel7 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.VideoPlayerApi.position", new StandardMessageCodec());
                if (videoPlayerApi != null) {
                    basicMessageChannel7.setMessageHandler(new AnonymousClass7(videoPlayerApi));
                } else {
                    basicMessageChannel7.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel8 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.VideoPlayerApi.seekTo", new StandardMessageCodec());
                if (videoPlayerApi != null) {
                    basicMessageChannel8.setMessageHandler(new AnonymousClass8(videoPlayerApi));
                } else {
                    basicMessageChannel8.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel9 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.VideoPlayerApi.pause", new StandardMessageCodec());
                if (videoPlayerApi != null) {
                    basicMessageChannel9.setMessageHandler(new AnonymousClass9(videoPlayerApi));
                } else {
                    basicMessageChannel9.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel10 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.VideoPlayerApi.setMixWithOthers", new StandardMessageCodec());
                if (videoPlayerApi != null) {
                    basicMessageChannel10.setMessageHandler(new AnonymousClass10(videoPlayerApi));
                } else {
                    basicMessageChannel10.setMessageHandler(null);
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: io.flutter.plugins.videoplayer.Messages$VideoPlayerApi$1  reason: invalid class name */
        public static class AnonymousClass1 implements BasicMessageChannel.MessageHandler<Object> {
            final /* synthetic */ VideoPlayerApi val$api;

            AnonymousClass1(VideoPlayerApi videoPlayerApi) {
                this.val$api = videoPlayerApi;
            }

            @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
            public void onMessage(Object obj, BasicMessageChannel.Reply<Object> reply) {
                HashMap hashMap = new HashMap();
                try {
                    this.val$api.initialize();
                    hashMap.put("result", null);
                } catch (Exception e) {
                    hashMap.put("error", Messages.wrapError(e));
                }
                reply.reply(hashMap);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: io.flutter.plugins.videoplayer.Messages$VideoPlayerApi$2  reason: invalid class name */
        public static class AnonymousClass2 implements BasicMessageChannel.MessageHandler<Object> {
            final /* synthetic */ VideoPlayerApi val$api;

            AnonymousClass2(VideoPlayerApi videoPlayerApi) {
                this.val$api = videoPlayerApi;
            }

            @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
            public void onMessage(Object obj, BasicMessageChannel.Reply<Object> reply) {
                CreateMessage fromMap = CreateMessage.fromMap((HashMap) obj);
                HashMap hashMap = new HashMap();
                try {
                    hashMap.put("result", this.val$api.create(fromMap).toMap());
                } catch (Exception e) {
                    hashMap.put("error", Messages.wrapError(e));
                }
                reply.reply(hashMap);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: io.flutter.plugins.videoplayer.Messages$VideoPlayerApi$3  reason: invalid class name */
        public static class AnonymousClass3 implements BasicMessageChannel.MessageHandler<Object> {
            final /* synthetic */ VideoPlayerApi val$api;

            AnonymousClass3(VideoPlayerApi videoPlayerApi) {
                this.val$api = videoPlayerApi;
            }

            @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
            public void onMessage(Object obj, BasicMessageChannel.Reply<Object> reply) {
                TextureMessage fromMap = TextureMessage.fromMap((HashMap) obj);
                HashMap hashMap = new HashMap();
                try {
                    this.val$api.dispose(fromMap);
                    hashMap.put("result", null);
                } catch (Exception e) {
                    hashMap.put("error", Messages.wrapError(e));
                }
                reply.reply(hashMap);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: io.flutter.plugins.videoplayer.Messages$VideoPlayerApi$4  reason: invalid class name */
        public static class AnonymousClass4 implements BasicMessageChannel.MessageHandler<Object> {
            final /* synthetic */ VideoPlayerApi val$api;

            AnonymousClass4(VideoPlayerApi videoPlayerApi) {
                this.val$api = videoPlayerApi;
            }

            @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
            public void onMessage(Object obj, BasicMessageChannel.Reply<Object> reply) {
                LoopingMessage fromMap = LoopingMessage.fromMap((HashMap) obj);
                HashMap hashMap = new HashMap();
                try {
                    this.val$api.setLooping(fromMap);
                    hashMap.put("result", null);
                } catch (Exception e) {
                    hashMap.put("error", Messages.wrapError(e));
                }
                reply.reply(hashMap);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: io.flutter.plugins.videoplayer.Messages$VideoPlayerApi$5  reason: invalid class name */
        public static class AnonymousClass5 implements BasicMessageChannel.MessageHandler<Object> {
            final /* synthetic */ VideoPlayerApi val$api;

            AnonymousClass5(VideoPlayerApi videoPlayerApi) {
                this.val$api = videoPlayerApi;
            }

            @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
            public void onMessage(Object obj, BasicMessageChannel.Reply<Object> reply) {
                VolumeMessage fromMap = VolumeMessage.fromMap((HashMap) obj);
                HashMap hashMap = new HashMap();
                try {
                    this.val$api.setVolume(fromMap);
                    hashMap.put("result", null);
                } catch (Exception e) {
                    hashMap.put("error", Messages.wrapError(e));
                }
                reply.reply(hashMap);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: io.flutter.plugins.videoplayer.Messages$VideoPlayerApi$6  reason: invalid class name */
        public static class AnonymousClass6 implements BasicMessageChannel.MessageHandler<Object> {
            final /* synthetic */ VideoPlayerApi val$api;

            AnonymousClass6(VideoPlayerApi videoPlayerApi) {
                this.val$api = videoPlayerApi;
            }

            @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
            public void onMessage(Object obj, BasicMessageChannel.Reply<Object> reply) {
                TextureMessage fromMap = TextureMessage.fromMap((HashMap) obj);
                HashMap hashMap = new HashMap();
                try {
                    this.val$api.play(fromMap);
                    hashMap.put("result", null);
                } catch (Exception e) {
                    hashMap.put("error", Messages.wrapError(e));
                }
                reply.reply(hashMap);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: io.flutter.plugins.videoplayer.Messages$VideoPlayerApi$7  reason: invalid class name */
        public static class AnonymousClass7 implements BasicMessageChannel.MessageHandler<Object> {
            final /* synthetic */ VideoPlayerApi val$api;

            AnonymousClass7(VideoPlayerApi videoPlayerApi) {
                this.val$api = videoPlayerApi;
            }

            @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
            public void onMessage(Object obj, BasicMessageChannel.Reply<Object> reply) {
                TextureMessage fromMap = TextureMessage.fromMap((HashMap) obj);
                HashMap hashMap = new HashMap();
                try {
                    hashMap.put("result", this.val$api.position(fromMap).toMap());
                } catch (Exception e) {
                    hashMap.put("error", Messages.wrapError(e));
                }
                reply.reply(hashMap);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: io.flutter.plugins.videoplayer.Messages$VideoPlayerApi$8  reason: invalid class name */
        public static class AnonymousClass8 implements BasicMessageChannel.MessageHandler<Object> {
            final /* synthetic */ VideoPlayerApi val$api;

            AnonymousClass8(VideoPlayerApi videoPlayerApi) {
                this.val$api = videoPlayerApi;
            }

            @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
            public void onMessage(Object obj, BasicMessageChannel.Reply<Object> reply) {
                PositionMessage fromMap = PositionMessage.fromMap((HashMap) obj);
                HashMap hashMap = new HashMap();
                try {
                    this.val$api.seekTo(fromMap);
                    hashMap.put("result", null);
                } catch (Exception e) {
                    hashMap.put("error", Messages.wrapError(e));
                }
                reply.reply(hashMap);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: io.flutter.plugins.videoplayer.Messages$VideoPlayerApi$9  reason: invalid class name */
        public static class AnonymousClass9 implements BasicMessageChannel.MessageHandler<Object> {
            final /* synthetic */ VideoPlayerApi val$api;

            AnonymousClass9(VideoPlayerApi videoPlayerApi) {
                this.val$api = videoPlayerApi;
            }

            @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
            public void onMessage(Object obj, BasicMessageChannel.Reply<Object> reply) {
                TextureMessage fromMap = TextureMessage.fromMap((HashMap) obj);
                HashMap hashMap = new HashMap();
                try {
                    this.val$api.pause(fromMap);
                    hashMap.put("result", null);
                } catch (Exception e) {
                    hashMap.put("error", Messages.wrapError(e));
                }
                reply.reply(hashMap);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: io.flutter.plugins.videoplayer.Messages$VideoPlayerApi$10  reason: invalid class name */
        public static class AnonymousClass10 implements BasicMessageChannel.MessageHandler<Object> {
            final /* synthetic */ VideoPlayerApi val$api;

            AnonymousClass10(VideoPlayerApi videoPlayerApi) {
                this.val$api = videoPlayerApi;
            }

            @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
            public void onMessage(Object obj, BasicMessageChannel.Reply<Object> reply) {
                MixWithOthersMessage fromMap = MixWithOthersMessage.fromMap((HashMap) obj);
                HashMap hashMap = new HashMap();
                try {
                    this.val$api.setMixWithOthers(fromMap);
                    hashMap.put("result", null);
                } catch (Exception e) {
                    hashMap.put("error", Messages.wrapError(e));
                }
                reply.reply(hashMap);
            }
        }
    }

    /* access modifiers changed from: private */
    public static HashMap wrapError(Exception exc) {
        HashMap hashMap = new HashMap();
        hashMap.put("message", exc.toString());
        hashMap.put(Constants.KEY_HTTP_CODE, null);
        hashMap.put("details", null);
        return hashMap;
    }
}
