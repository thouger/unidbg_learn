package com.befovy.fijkplayer;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.media.MediaCodec;
import android.media.TtmlUtils;
import android.net.Uri;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.smtt.sdk.TbsListener;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.TextureRegistry;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkEventListener;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class FijkPlayer implements MethodChannel.MethodCallHandler, IMediaPlayer.OnSnapShotListener, IjkEventListener {
    private static final AtomicInteger a = new AtomicInteger(0);
    private final int b;
    private final IjkMediaPlayer c;
    private final a d;
    private final EventChannel e;
    private final MethodChannel f;
    private final f g = new f();
    private final e h = new e();
    private int i;
    private int j = -1;
    private int k = 0;
    private int l = 0;
    private TextureRegistry.SurfaceTextureEntry m;
    private SurfaceTexture n;
    private Surface o;
    private final boolean p;

    private boolean a(int i) {
        return i == 4 || i == 5 || i == 6 || i == 3;
    }

    static {
        AppMethodBeat.i(279, false);
        AppMethodBeat.o(279);
    }

    FijkPlayer(a aVar, boolean z) {
        AppMethodBeat.i(256, false);
        this.d = aVar;
        this.b = a.incrementAndGet();
        this.i = 0;
        this.p = z;
        if (z) {
            this.c = null;
            this.e = null;
            this.f = null;
        } else {
            this.c = new IjkMediaPlayer();
            this.c.addIjkEventListener(this);
            this.c.setOption(4, "enable-position-notify", 1);
            this.c.setOption(4, "start-on-prepared", 0);
            BinaryMessenger b = this.d.b();
            this.f = new MethodChannel(b, "befovy.com/fijkplayer/" + this.b);
            this.f.setMethodCallHandler(this);
            this.c.setOnSnapShotListener(this);
            BinaryMessenger b2 = this.d.b();
            this.e = new EventChannel(b2, "befovy.com/fijkplayer/event/" + this.b);
            this.e.setStreamHandler(new AnonymousClass1());
        }
        AppMethodBeat.o(256);
    }

    /* renamed from: com.befovy.fijkplayer.FijkPlayer$1  reason: invalid class name */
    class AnonymousClass1 implements EventChannel.StreamHandler {
        AnonymousClass1() {
        }

        @Override // io.flutter.plugin.common.EventChannel.StreamHandler
        public void onListen(Object obj, EventChannel.EventSink eventSink) {
            AppMethodBeat.i(TbsListener.ErrorCode.INFO_SDKINIT_IS_SYS_FORCED, false);
            FijkPlayer.this.g.a(eventSink);
            AppMethodBeat.o(TbsListener.ErrorCode.INFO_SDKINIT_IS_SYS_FORCED);
        }

        @Override // io.flutter.plugin.common.EventChannel.StreamHandler
        public void onCancel(Object obj) {
            AppMethodBeat.i(416, false);
            FijkPlayer.this.g.a((EventChannel.EventSink) null);
            AppMethodBeat.o(416);
        }
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        AppMethodBeat.i(257, false);
        if (this.p) {
            AppMethodBeat.o(257);
            return;
        }
        if (this.h.a("enable-snapshot", 0) > 0) {
            this.c.setAmcGlesRender();
            this.c.setOption(4, "overlay-format", "fcc-_es2");
        }
        AppMethodBeat.o(257);
    }

    /* access modifiers changed from: package-private */
    public long c() {
        AppMethodBeat.i(260, false);
        b();
        if (this.m == null) {
            TextureRegistry.SurfaceTextureEntry a2 = this.d.a();
            this.m = a2;
            if (a2 != null) {
                this.n = a2.surfaceTexture();
                this.o = new Surface(this.n);
            }
            if (!this.p) {
                this.c.setSurface(this.o);
            }
        }
        TextureRegistry.SurfaceTextureEntry surfaceTextureEntry = this.m;
        if (surfaceTextureEntry != null) {
            long id = surfaceTextureEntry.id();
            AppMethodBeat.o(260);
            return id;
        }
        Log.e("FIJKPLAYER", "setup surface, null SurfaceTextureEntry");
        AppMethodBeat.o(260);
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void d() {
        AppMethodBeat.i(262, false);
        if (!this.p) {
            a(700, 9, this.i, null);
            this.c.release();
        }
        TextureRegistry.SurfaceTextureEntry surfaceTextureEntry = this.m;
        if (surfaceTextureEntry != null) {
            surfaceTextureEntry.release();
            this.m = null;
        }
        SurfaceTexture surfaceTexture = this.n;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.n = null;
        }
        Surface surface = this.o;
        if (surface != null) {
            surface.release();
            this.o = null;
        }
        if (!this.p) {
            this.f.setMethodCallHandler(null);
            this.e.setStreamHandler(null);
        }
        AppMethodBeat.o(262);
    }

    private void a(int i, int i2) {
        AppMethodBeat.i(267, false);
        if (i == 4 && i2 != 4) {
            this.d.a(1);
            if (this.h.a("request-audio-focus", 0) == 1) {
                this.d.b(true);
            }
            if (this.h.a("request-screen-on", 0) == 1) {
                this.d.a(true);
            }
        } else if (i != 4 && i2 == 4) {
            this.d.a(-1);
            if (this.h.a("release-audio-focus", 0) == 1) {
                this.d.b(false);
            }
            if (this.h.a("request-screen-on", 0) == 1) {
                this.d.a(false);
            }
        }
        if (a(i) && !a(i2)) {
            this.d.b(1);
        } else if (!a(i) && a(i2)) {
            this.d.b(-1);
        }
        AppMethodBeat.o(267);
    }

    private void a(int i, int i2, int i3, Object obj) {
        int i4;
        boolean z = false;
        AppMethodBeat.i(269, false);
        HashMap hashMap = new HashMap();
        if (i == 100) {
            this.g.error(String.valueOf(i2), obj.toString(), Integer.valueOf(i3));
        } else if (i == 200) {
            hashMap.put("event", "prepared");
            hashMap.put("duration", Long.valueOf(this.c.getDuration()));
            this.g.success(hashMap);
        } else if (i == 400) {
            hashMap.put("event", "size_changed");
            int i5 = this.j;
            if (i5 == 0 || i5 == 180) {
                hashMap.put("width", Integer.valueOf(i2));
                hashMap.put("height", Integer.valueOf(i3));
                this.g.success(hashMap);
            } else if (i5 == 90 || i5 == 270) {
                hashMap.put("width", Integer.valueOf(i3));
                hashMap.put("height", Integer.valueOf(i2));
                this.g.success(hashMap);
            }
            this.k = i2;
            this.l = i3;
        } else if (i == 510) {
            hashMap.put("event", "pos");
            hashMap.put("pos", Integer.valueOf(i2));
            this.g.success(hashMap);
        } else if (i == 600) {
            hashMap.put("event", "seek_complete");
            hashMap.put("pos", Integer.valueOf(i2));
            hashMap.put("err", Integer.valueOf(i3));
            this.g.success(hashMap);
        } else if (i != 700) {
            switch (i) {
                case 402:
                case 403:
                    hashMap.put("event", "rendering_start");
                    hashMap.put("type", i == 402 ? MediaCodec.MetricsConstants.MODE_VIDEO : "audio");
                    this.g.success(hashMap);
                    break;
                case 404:
                    hashMap.put("event", "rotate");
                    hashMap.put("degree", Integer.valueOf(i2));
                    this.j = i2;
                    this.g.success(hashMap);
                    int i6 = this.k;
                    if (i6 > 0 && (i4 = this.l) > 0) {
                        a(400, i6, i4, null);
                        break;
                    }
                default:
                    switch (i) {
                        case 500:
                        case 501:
                            hashMap.put("event", "freeze");
                            if (i == 500) {
                                z = true;
                            }
                            hashMap.put("value", Boolean.valueOf(z));
                            this.g.success(hashMap);
                            break;
                        case 502:
                            hashMap.put("event", "buffering");
                            hashMap.put(TtmlUtils.TAG_HEAD, Integer.valueOf(i2));
                            hashMap.put("percent", Integer.valueOf(i3));
                            this.g.success(hashMap);
                            break;
                    }
            }
        } else {
            this.i = i2;
            hashMap.put("event", "state_change");
            hashMap.put("new", Integer.valueOf(i2));
            hashMap.put("old", Integer.valueOf(i3));
            a(i2, i3);
            this.g.success(hashMap);
        }
        AppMethodBeat.o(269);
    }

    public void onSnapShot(IMediaPlayer iMediaPlayer, Bitmap bitmap, int i, int i2) {
        AppMethodBeat.i(271, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        bitmap.recycle();
        HashMap hashMap = new HashMap();
        hashMap.put("data", byteArrayOutputStream.toByteArray());
        hashMap.put("w", Integer.valueOf(i));
        hashMap.put("h", Integer.valueOf(i2));
        this.f.invokeMethod("_onSnapshot", hashMap);
        AppMethodBeat.o(271);
    }

    public void onEvent(IjkMediaPlayer ijkMediaPlayer, int i, int i2, int i3, Object obj) {
        AppMethodBeat.i(274, false);
        if (!(i == 100 || i == 200 || i == 400 || i == 510 || i == 600 || i == 700)) {
            switch (i) {
                default:
                    switch (i) {
                    }
                case 402:
                case 403:
                case 404:
                    a(i, i2, i3, obj);
                    break;
            }
            AppMethodBeat.o(274);
        }
        a(i, i2, i3, obj);
        AppMethodBeat.o(274);
    }

    private void a(Object obj) {
        AppMethodBeat.i(276, false);
        if (obj instanceof Map) {
            Map map = (Map) obj;
            for (Object obj2 : map.keySet()) {
                Object obj3 = map.get(obj2);
                if ((obj2 instanceof Integer) && (obj3 instanceof Map)) {
                    int intValue = ((Integer) obj2).intValue();
                    Map map2 = (Map) obj3;
                    for (Object obj4 : map2.keySet()) {
                        Object obj5 = map2.get(obj4);
                        boolean z = obj4 instanceof String;
                        if (z && intValue != 0) {
                            String str = (String) obj4;
                            if (obj5 instanceof Integer) {
                                this.c.setOption(intValue, str, (long) ((Integer) obj5).intValue());
                            } else if (obj5 instanceof String) {
                                this.c.setOption(intValue, str, (String) obj5);
                            }
                        } else if (z) {
                            String str2 = (String) obj4;
                            if (obj5 instanceof Integer) {
                                this.h.a(str2, (Integer) obj5);
                            } else if (obj5 instanceof String) {
                                this.h.a(str2, (String) obj5);
                            }
                        }
                    }
                }
            }
        }
        AppMethodBeat.o(276);
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        boolean z = false;
        AppMethodBeat.i(278, false);
        if (methodCall.method.equals("setupSurface")) {
            result.success(Long.valueOf(c()));
        } else {
            long j = 0;
            if (methodCall.method.equals("setOption")) {
                Integer num = (Integer) methodCall.argument("cat");
                String str = (String) methodCall.argument("key");
                if (methodCall.hasArgument("long")) {
                    Integer num2 = (Integer) methodCall.argument("long");
                    if (num != null && num.intValue() != 0) {
                        IjkMediaPlayer ijkMediaPlayer = this.c;
                        int intValue = num.intValue();
                        if (num2 != null) {
                            j = num2.longValue();
                        }
                        ijkMediaPlayer.setOption(intValue, str, j);
                    } else if (num != null) {
                        this.h.a(str, num2);
                    }
                } else if (methodCall.hasArgument("str")) {
                    String str2 = (String) methodCall.argument("str");
                    if (num != null && num.intValue() != 0) {
                        this.c.setOption(num.intValue(), str, str2);
                    } else if (num != null) {
                        this.h.a(str, str2);
                    }
                } else {
                    Log.w("FIJKPLAYER", "error arguments for setOptions");
                }
                result.success(null);
            } else if (methodCall.method.equals("applyOptions")) {
                a(methodCall.arguments);
                result.success(null);
            } else {
                int i = 1;
                if (methodCall.method.equals("setDataSource")) {
                    Uri parse = Uri.parse((String) methodCall.argument("url"));
                    String str3 = "";
                    if ("asset".equals(parse.getScheme())) {
                        String a2 = this.d.a(parse.getPath() != null ? parse.getPath().substring(1) : str3, parse.getHost());
                        if (!TextUtils.isEmpty(a2)) {
                            parse = Uri.parse(a2);
                        }
                        z = true;
                    }
                    try {
                        Context c = this.d.c();
                        if (z && c != null) {
                            AssetManager assets = c.getAssets();
                            if (parse.getPath() != null) {
                                str3 = parse.getPath();
                            }
                            this.c.setDataSource(new g(assets.open(str3, 1)));
                        } else if (c != null) {
                            if (!TextUtils.isEmpty(parse.getScheme())) {
                                if (!ContentResolver.SCHEME_FILE.equals(parse.getScheme())) {
                                    this.c.setDataSource(this.d.c(), parse);
                                }
                            }
                            if (parse.getPath() != null) {
                                str3 = parse.getPath();
                            }
                            this.c.setDataSource(new d(new File(str3)));
                        } else {
                            Log.e("FIJKPLAYER", "context null, can't setDataSource");
                        }
                        a(700, 1, -1, null);
                        if (c == null) {
                            a(700, 8, -1, null);
                        }
                        result.success(null);
                    } catch (FileNotFoundException e) {
                        result.error("-875574348", "Local File not found:" + e.getMessage(), null);
                    } catch (IOException e2) {
                        result.error("-1162824012", "Local IOException:" + e2.getMessage(), null);
                    }
                } else if (methodCall.method.equals("prepareAsync")) {
                    b();
                    this.c.prepareAsync();
                    a(700, 2, -1, null);
                    result.success(null);
                } else if (methodCall.method.equals(Telephony.BaseMmsColumns.START)) {
                    this.c.start();
                    result.success(null);
                } else if (methodCall.method.equals("pause")) {
                    this.c.pause();
                    result.success(null);
                } else if (methodCall.method.equals("stop")) {
                    this.c.stop();
                    a(700, 7, -1, null);
                    result.success(null);
                } else if (methodCall.method.equals("reset")) {
                    this.c.reset();
                    a(700, 0, -1, null);
                    result.success(null);
                } else if (methodCall.method.equals("getCurrentPosition")) {
                    result.success(Long.valueOf(this.c.getCurrentPosition()));
                } else {
                    float f = 1.0f;
                    if (methodCall.method.equals("setVolume")) {
                        Double d = (Double) methodCall.argument("volume");
                        if (d != null) {
                            f = d.floatValue();
                        }
                        this.c.setVolume(f, f);
                        result.success(null);
                    } else if (methodCall.method.equals("seekTo")) {
                        Integer num3 = (Integer) methodCall.argument("msec");
                        if (this.i == 6) {
                            a(700, 5, -1, null);
                        }
                        IjkMediaPlayer ijkMediaPlayer2 = this.c;
                        if (num3 != null) {
                            j = num3.longValue();
                        }
                        ijkMediaPlayer2.seekTo(j);
                        result.success(null);
                    } else if (methodCall.method.equals("setLoop")) {
                        Integer num4 = (Integer) methodCall.argument("loop");
                        IjkMediaPlayer ijkMediaPlayer3 = this.c;
                        if (num4 != null) {
                            i = num4.intValue();
                        }
                        ijkMediaPlayer3.setLoopCount(i);
                        result.success(null);
                    } else if (methodCall.method.equals("setSpeed")) {
                        Double d2 = (Double) methodCall.argument("speed");
                        IjkMediaPlayer ijkMediaPlayer4 = this.c;
                        if (d2 != null) {
                            f = d2.floatValue();
                        }
                        ijkMediaPlayer4.setSpeed(f);
                        result.success(null);
                    } else if (methodCall.method.equals("snapshot")) {
                        if (this.h.a("enable-snapshot", 0) > 0) {
                            this.c.snapShot();
                        } else {
                            this.f.invokeMethod("_onSnapshot", "not support");
                        }
                        result.success(null);
                    } else {
                        result.notImplemented();
                    }
                }
            }
        }
        AppMethodBeat.o(278);
    }
}
