package io.flutter.plugins.videoplayer;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.b;
import com.google.android.exoplayer2.g;
import com.google.android.exoplayer2.q;
import com.google.android.exoplayer2.r;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.k;
import com.google.android.exoplayer2.upstream.m;
import com.google.android.exoplayer2.y;
import com.google.android.exoplayer2.z;
import com.huawei.hms.support.api.entity.core.JosStatusCodes;
import io.flutter.plugin.common.EventChannel;
import io.flutter.view.TextureRegistry;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/* access modifiers changed from: package-private */
public final class VideoPlayer {
    private static final String FORMAT_DASH = "dash";
    private static final String FORMAT_HLS = "hls";
    private static final String FORMAT_OTHER = "other";
    private static final String FORMAT_SS = "ss";
    private final EventChannel eventChannel;
    private QueuingEventSink eventSink = new QueuingEventSink();
    private y exoPlayer;
    private boolean isInitialized = false;
    private final VideoPlayerOptions options;
    private Surface surface;
    private final TextureRegistry.SurfaceTextureEntry textureEntry;

    VideoPlayer(Context context, EventChannel eventChannel, TextureRegistry.SurfaceTextureEntry surfaceTextureEntry, String str, String str2, VideoPlayerOptions videoPlayerOptions) {
        f.a aVar;
        this.eventChannel = eventChannel;
        this.textureEntry = surfaceTextureEntry;
        this.options = videoPlayerOptions;
        this.exoPlayer = g.a(context, new DefaultTrackSelector());
        Uri parse = Uri.parse(str);
        if (isHTTP(parse)) {
            aVar = new m("ExoPlayer", null, JosStatusCodes.RTN_CODE_COMMON_ERROR, JosStatusCodes.RTN_CODE_COMMON_ERROR, true);
        } else {
            aVar = new k(context, "ExoPlayer");
        }
        this.exoPlayer.a(buildMediaSource(parse, aVar, str2, context));
        setupVideoPlayer(eventChannel, surfaceTextureEntry);
    }

    private static boolean isHTTP(Uri uri) {
        if (uri == null || uri.getScheme() == null) {
            return false;
        }
        String scheme = uri.getScheme();
        if (scheme.equals("http") || scheme.equals("https")) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.exoplayer2.source.m buildMediaSource(android.net.Uri r8, com.google.android.exoplayer2.upstream.f.a r9, java.lang.String r10, android.content.Context r11) {
        /*
            r7 = this;
            r0 = 0
            r1 = -1
            r2 = 3
            r3 = 2
            r4 = 1
            if (r10 != 0) goto L_0x0011
            java.lang.String r10 = r8.getLastPathSegment()
            int r0 = com.google.android.exoplayer2.util.z.k(r10)
            goto L_0x0065
        L_0x0011:
            int r5 = r10.hashCode()
            r6 = 3680(0xe60, float:5.157E-42)
            if (r5 == r6) goto L_0x004a
            r6 = 103407(0x193ef, float:1.44904E-40)
            if (r5 == r6) goto L_0x003f
            r6 = 3075986(0x2eef92, float:4.310374E-39)
            if (r5 == r6) goto L_0x0034
            r6 = 106069776(0x6527f10, float:3.958996E-35)
            if (r5 == r6) goto L_0x0029
            goto L_0x0055
        L_0x0029:
            java.lang.String r5 = "other"
            boolean r10 = r10.equals(r5)
            if (r10 == 0) goto L_0x0055
            r10 = r2
            goto L_0x0056
        L_0x0034:
            java.lang.String r5 = "dash"
            boolean r10 = r10.equals(r5)
            if (r10 == 0) goto L_0x0055
            r10 = r4
            goto L_0x0056
        L_0x003f:
            java.lang.String r5 = "hls"
            boolean r10 = r10.equals(r5)
            if (r10 == 0) goto L_0x0055
            r10 = r3
            goto L_0x0056
        L_0x004a:
            java.lang.String r5 = "ss"
            boolean r10 = r10.equals(r5)
            if (r10 == 0) goto L_0x0055
            r10 = r0
            goto L_0x0056
        L_0x0055:
            r10 = r1
        L_0x0056:
            if (r10 == 0) goto L_0x0064
            if (r10 == r4) goto L_0x0065
            if (r10 == r3) goto L_0x0062
            if (r10 == r2) goto L_0x0060
            r0 = r1
            goto L_0x0065
        L_0x0060:
            r0 = r2
            goto L_0x0065
        L_0x0062:
            r0 = r3
            goto L_0x0065
        L_0x0064:
            r0 = r4
        L_0x0065:
            r10 = 0
            if (r0 == 0) goto L_0x00b7
            if (r0 == r4) goto L_0x00a3
            if (r0 == r3) goto L_0x0099
            if (r0 != r2) goto L_0x0081
            com.google.android.exoplayer2.source.j$a r10 = new com.google.android.exoplayer2.source.j$a
            r10.<init>(r9)
            com.google.android.exoplayer2.extractor.e r9 = new com.google.android.exoplayer2.extractor.e
            r9.<init>()
            com.google.android.exoplayer2.source.j$a r9 = r10.a(r9)
            com.google.android.exoplayer2.source.j r8 = r9.b(r8)
            return r8
        L_0x0081:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Unsupported type: "
            r9.append(r10)
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x0099:
            com.google.android.exoplayer2.source.hls.j$a r10 = new com.google.android.exoplayer2.source.hls.j$a
            r10.<init>(r9)
            com.google.android.exoplayer2.source.hls.j r8 = r10.b(r8)
            return r8
        L_0x00a3:
            com.google.android.exoplayer2.source.smoothstreaming.d$a r0 = new com.google.android.exoplayer2.source.smoothstreaming.d$a
            com.google.android.exoplayer2.source.smoothstreaming.a$a r1 = new com.google.android.exoplayer2.source.smoothstreaming.a$a
            r1.<init>(r9)
            com.google.android.exoplayer2.upstream.k r2 = new com.google.android.exoplayer2.upstream.k
            r2.<init>(r11, r10, r9)
            r0.<init>(r1, r2)
            com.google.android.exoplayer2.source.smoothstreaming.d r8 = r0.b(r8)
            return r8
        L_0x00b7:
            com.google.android.exoplayer2.source.dash.c$c r0 = new com.google.android.exoplayer2.source.dash.c$c
            com.google.android.exoplayer2.source.dash.f$a r1 = new com.google.android.exoplayer2.source.dash.f$a
            r1.<init>(r9)
            com.google.android.exoplayer2.upstream.k r2 = new com.google.android.exoplayer2.upstream.k
            r2.<init>(r11, r10, r9)
            r0.<init>(r1, r2)
            com.google.android.exoplayer2.source.dash.c r8 = r0.b(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.videoplayer.VideoPlayer.buildMediaSource(android.net.Uri, com.google.android.exoplayer2.upstream.f$a, java.lang.String, android.content.Context):com.google.android.exoplayer2.source.m");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: io.flutter.plugins.videoplayer.VideoPlayer$1  reason: invalid class name */
    public class AnonymousClass1 implements EventChannel.StreamHandler {
        AnonymousClass1() {
        }

        @Override // io.flutter.plugin.common.EventChannel.StreamHandler
        public void onListen(Object obj, EventChannel.EventSink eventSink) {
            VideoPlayer.this.eventSink.setDelegate(eventSink);
        }

        @Override // io.flutter.plugin.common.EventChannel.StreamHandler
        public void onCancel(Object obj) {
            VideoPlayer.this.eventSink.setDelegate(null);
        }
    }

    private void setupVideoPlayer(EventChannel eventChannel, TextureRegistry.SurfaceTextureEntry surfaceTextureEntry) {
        eventChannel.setStreamHandler(new AnonymousClass1());
        this.surface = new Surface(surfaceTextureEntry.surfaceTexture());
        this.exoPlayer.a(this.surface);
        setAudioAttributes(this.exoPlayer, this.options.mixWithOthers);
        this.exoPlayer.a(new AnonymousClass2());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: io.flutter.plugins.videoplayer.VideoPlayer$2  reason: invalid class name */
    public class AnonymousClass2 implements r.a {
        @Override // com.google.android.exoplayer2.r.a
        public /* synthetic */ void a() {
            r.a.CC.$default$a(this);
        }

        @Override // com.google.android.exoplayer2.r.a
        public /* synthetic */ void a(q qVar) {
            r.a.CC.$default$a(this, qVar);
        }

        @Override // com.google.android.exoplayer2.r.a
        public /* synthetic */ void a(TrackGroupArray trackGroupArray, com.google.android.exoplayer2.trackselection.f fVar) {
            r.a.CC.$default$a(this, trackGroupArray, fVar);
        }

        @Override // com.google.android.exoplayer2.r.a
        public /* synthetic */ void a(z zVar, Object obj, int i) {
            r.a.CC.$default$a(this, zVar, obj, i);
        }

        @Override // com.google.android.exoplayer2.r.a
        public /* synthetic */ void a(boolean z) {
            r.a.CC.$default$a(this, z);
        }

        @Override // com.google.android.exoplayer2.r.a
        public /* synthetic */ void a_(int i) {
            r.a.CC.$default$a_(this, i);
        }

        @Override // com.google.android.exoplayer2.r.a
        public /* synthetic */ void b(int i) {
            r.a.CC.$default$b(this, i);
        }

        AnonymousClass2() {
        }

        @Override // com.google.android.exoplayer2.r.a
        public void onPlayerStateChanged(boolean z, int i) {
            if (i == 2) {
                VideoPlayer.this.sendBufferingUpdate();
            } else if (i == 3) {
                if (!VideoPlayer.this.isInitialized) {
                    VideoPlayer.this.isInitialized = true;
                    VideoPlayer.this.sendInitialized();
                }
            } else if (i == 4) {
                HashMap hashMap = new HashMap();
                hashMap.put("event", "completed");
                VideoPlayer.this.eventSink.success(hashMap);
            }
        }

        @Override // com.google.android.exoplayer2.r.a
        public void onPlayerError(ExoPlaybackException exoPlaybackException) {
            if (VideoPlayer.this.eventSink != null) {
                QueuingEventSink queuingEventSink = VideoPlayer.this.eventSink;
                queuingEventSink.error("VideoError", "Video player had error " + exoPlaybackException, null);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void sendBufferingUpdate() {
        HashMap hashMap = new HashMap();
        hashMap.put("event", "bufferingUpdate");
        hashMap.put("values", Collections.singletonList(Arrays.asList(0, Long.valueOf(this.exoPlayer.k()))));
        this.eventSink.success(hashMap);
    }

    private static void setAudioAttributes(y yVar, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            yVar.a(new b.a().a(3).a(), !z);
        } else {
            yVar.a(3);
        }
    }

    /* access modifiers changed from: package-private */
    public void play() {
        this.exoPlayer.b(true);
    }

    /* access modifiers changed from: package-private */
    public void pause() {
        this.exoPlayer.b(false);
    }

    /* access modifiers changed from: package-private */
    public void setLooping(boolean z) {
        this.exoPlayer.b(z ? 2 : 0);
    }

    /* access modifiers changed from: package-private */
    public void setVolume(double d) {
        this.exoPlayer.a((float) Math.max(0.0d, Math.min(1.0d, d)));
    }

    /* access modifiers changed from: package-private */
    public void seekTo(int i) {
        this.exoPlayer.a((long) i);
    }

    /* access modifiers changed from: package-private */
    public long getPosition() {
        return this.exoPlayer.j();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendInitialized() {
        if (this.isInitialized) {
            HashMap hashMap = new HashMap();
            hashMap.put("event", "initialized");
            hashMap.put("duration", Long.valueOf(this.exoPlayer.i()));
            if (this.exoPlayer.c() != null) {
                Format c = this.exoPlayer.c();
                int i = c.l;
                int i2 = c.m;
                int i3 = c.o;
                if (i3 == 90 || i3 == 270) {
                    i = this.exoPlayer.c().m;
                    i2 = this.exoPlayer.c().l;
                }
                hashMap.put("width", Integer.valueOf(i));
                hashMap.put("height", Integer.valueOf(i2));
            }
            this.eventSink.success(hashMap);
        }
    }

    /* access modifiers changed from: package-private */
    public void dispose() {
        if (this.isInitialized) {
            this.exoPlayer.a();
        }
        this.textureEntry.release();
        this.eventChannel.setStreamHandler(null);
        Surface surface = this.surface;
        if (surface != null) {
            surface.release();
        }
        y yVar = this.exoPlayer;
        if (yVar != null) {
            yVar.g();
        }
    }
}
