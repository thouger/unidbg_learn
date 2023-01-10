package com.befovy.fijkplayer;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.WindowManager;
import cn.missfresh.buttomline.logtrace.bean.ConstantKey;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.befovy.fijkplayer.c;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.view.TextureRegistry;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import tv.danmaku.ijk.media.player.IjkLibLoader;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* compiled from: FijkPlugin */
public class b implements AudioManager.OnAudioFocusChangeListener, a, c.b, FlutterPlugin, ActivityAware, MethodChannel.MethodCallHandler {
    private static int a = 2;
    private final SparseArray<FijkPlayer> b = new SparseArray<>();
    private final f c = new f();
    private WeakReference<Activity> d;
    private WeakReference<Context> e;
    private PluginRegistry.Registrar f;
    private FlutterPlugin.FlutterPluginBinding g;
    private int h = 0;
    private int i = 0;
    private int j = 3;
    private float k = 0.0625f;
    private boolean l = false;
    private EventChannel m;
    private Object n;
    private boolean o = false;

    public b() {
        AppMethodBeat.i(283, false);
        AppMethodBeat.o(283);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        AppMethodBeat.i(286, false);
        MethodChannel methodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "befovy.com/fijk");
        a(flutterPluginBinding);
        methodChannel.setMethodCallHandler(this);
        FijkPlayer fijkPlayer = new FijkPlayer(this, true);
        fijkPlayer.c();
        fijkPlayer.d();
        AudioManager i = i();
        if (i != null) {
            this.k = Math.max(1.0f / ((float) i.getStreamMaxVolume(3)), this.k);
        }
        AppMethodBeat.o(286);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.e = null;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        AppMethodBeat.i(288, false);
        this.d = new WeakReference<>(activityPluginBinding.getActivity());
        if (this.d.get() instanceof c.a) {
            ((c.a) this.d.get()).a(this);
        }
        AppMethodBeat.o(288);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        this.d = null;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        AppMethodBeat.i(289, false);
        this.d = new WeakReference<>(activityPluginBinding.getActivity());
        if (this.d.get() instanceof c.a) {
            ((c.a) this.d.get()).a(this);
        }
        AppMethodBeat.o(289);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        this.d = null;
    }

    @Override // com.befovy.fijkplayer.a
    public TextureRegistry.SurfaceTextureEntry a() {
        AppMethodBeat.i(293, false);
        FlutterPlugin.FlutterPluginBinding flutterPluginBinding = this.g;
        if (flutterPluginBinding != null) {
            TextureRegistry.SurfaceTextureEntry createSurfaceTexture = flutterPluginBinding.getTextureRegistry().createSurfaceTexture();
            AppMethodBeat.o(293);
            return createSurfaceTexture;
        }
        PluginRegistry.Registrar registrar = this.f;
        if (registrar != null) {
            TextureRegistry.SurfaceTextureEntry createSurfaceTexture2 = registrar.textures().createSurfaceTexture();
            AppMethodBeat.o(293);
            return createSurfaceTexture2;
        }
        AppMethodBeat.o(293);
        return null;
    }

    @Override // com.befovy.fijkplayer.a
    public BinaryMessenger b() {
        AppMethodBeat.i(295, false);
        FlutterPlugin.FlutterPluginBinding flutterPluginBinding = this.g;
        if (flutterPluginBinding != null) {
            BinaryMessenger binaryMessenger = flutterPluginBinding.getBinaryMessenger();
            AppMethodBeat.o(295);
            return binaryMessenger;
        }
        PluginRegistry.Registrar registrar = this.f;
        if (registrar != null) {
            BinaryMessenger messenger = registrar.messenger();
            AppMethodBeat.o(295);
            return messenger;
        }
        AppMethodBeat.o(295);
        return null;
    }

    @Override // com.befovy.fijkplayer.a
    public Context c() {
        AppMethodBeat.i(297, false);
        WeakReference<Context> weakReference = this.e;
        if (weakReference != null) {
            Context context = weakReference.get();
            AppMethodBeat.o(297);
            return context;
        }
        AppMethodBeat.o(297);
        return null;
    }

    private Activity d() {
        AppMethodBeat.i(299, false);
        PluginRegistry.Registrar registrar = this.f;
        if (registrar != null) {
            Activity activity = registrar.activity();
            AppMethodBeat.o(299);
            return activity;
        }
        WeakReference<Activity> weakReference = this.d;
        if (weakReference != null) {
            Activity activity2 = weakReference.get();
            AppMethodBeat.o(299);
            return activity2;
        }
        AppMethodBeat.o(299);
        return null;
    }

    @Override // com.befovy.fijkplayer.a
    public String a(String str, String str2) {
        String str3;
        AppMethodBeat.i(301, false);
        if (this.g != null) {
            str3 = TextUtils.isEmpty(str2) ? this.g.getFlutterAssets().getAssetFilePathByName(str) : this.g.getFlutterAssets().getAssetFilePathByName(str, str2);
        } else if (this.f != null) {
            str3 = TextUtils.isEmpty(str2) ? this.f.lookupKeyForAsset(str) : this.f.lookupKeyForAsset(str, str2);
        } else {
            str3 = null;
        }
        AppMethodBeat.o(301);
        return str3;
    }

    private void a(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        AppMethodBeat.i(305, false);
        this.g = flutterPluginBinding;
        this.e = new WeakReference<>(flutterPluginBinding.getApplicationContext());
        a(flutterPluginBinding.getBinaryMessenger());
        AppMethodBeat.o(305);
    }

    private void a(BinaryMessenger binaryMessenger) {
        AppMethodBeat.i(308, false);
        EventChannel eventChannel = this.m;
        if (eventChannel != null) {
            eventChannel.setStreamHandler(null);
            this.c.a((EventChannel.EventSink) null);
        }
        this.m = new EventChannel(binaryMessenger, "befovy.com/fijk/event");
        this.m.setStreamHandler(new AnonymousClass1());
        AudioManager i = i();
        if (i != null) {
            this.k = Math.max(1.0f / ((float) i.getStreamMaxVolume(3)), this.k);
        }
        AppMethodBeat.o(308);
    }

    /* compiled from: FijkPlugin */
    /* access modifiers changed from: package-private */
    /* renamed from: com.befovy.fijkplayer.b$1  reason: invalid class name */
    public class AnonymousClass1 implements EventChannel.StreamHandler {
        AnonymousClass1() {
        }

        @Override // io.flutter.plugin.common.EventChannel.StreamHandler
        public void onListen(Object obj, EventChannel.EventSink eventSink) {
            AppMethodBeat.i(MetricsProto.MetricsEvent.OVERVIEW_DISMISS_ALL, false);
            b.this.c.a(eventSink);
            AppMethodBeat.o(MetricsProto.MetricsEvent.OVERVIEW_DISMISS_ALL);
        }

        @Override // io.flutter.plugin.common.EventChannel.StreamHandler
        public void onCancel(Object obj) {
            AppMethodBeat.i(361, false);
            b.this.c.a((EventChannel.EventSink) null);
            AppMethodBeat.o(361);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        char c;
        Boolean bool;
        Double d;
        Double d2;
        Double d3;
        boolean z = false;
        AppMethodBeat.i(311, false);
        String str = methodCall.method;
        boolean z2 = true;
        switch (str.hashCode()) {
            case -2128294168:
                if (str.equals("volumeSet")) {
                    c = 18;
                    break;
                }
                c = '\uffff';
                break;
            case -1869769899:
                if (str.equals("volumeUp")) {
                    c = 15;
                    break;
                }
                c = '\uffff';
                break;
            case -1553046820:
                if (str.equals("volumeDown")) {
                    c = 14;
                    break;
                }
                c = '\uffff';
                break;
            case -1552773037:
                if (str.equals("volumeMute")) {
                    c = 16;
                    break;
                }
                c = '\uffff';
                break;
            case -1504647535:
                if (str.equals("requestAudioFocus")) {
                    c = '\f';
                    break;
                }
                c = '\uffff';
                break;
            case -1234022968:
                if (str.equals("releasePlayer")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case -1013170331:
                if (str.equals("onLoad")) {
                    c = 20;
                    break;
                }
                c = '\uffff';
                break;
            case -606886359:
                if (str.equals("systemVolume")) {
                    c = 17;
                    break;
                }
                c = '\uffff';
                break;
            case -461681955:
                if (str.equals("setOrientationAuto")) {
                    c = 7;
                    break;
                }
                c = '\uffff';
                break;
            case 3237136:
                if (str.equals("init")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case 600760777:
                if (str.equals("setOrientationPortrait")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case 648162385:
                if (str.equals(ConstantKey.BRIGHTNESS)) {
                    c = '\n';
                    break;
                }
                c = '\uffff';
                break;
            case 1124545107:
                if (str.equals("setBrightness")) {
                    c = 11;
                    break;
                }
                c = '\uffff';
                break;
            case 1246158090:
                if (str.equals("volUiMode")) {
                    c = 19;
                    break;
                }
                c = '\uffff';
                break;
            case 1385449135:
                if (str.equals("getPlatformVersion")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            case 1404610057:
                if (str.equals("releaseAudioFocus")) {
                    c = '\r';
                    break;
                }
                c = '\uffff';
                break;
            case 1442020093:
                if (str.equals("createPlayer")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 1557968318:
                if (str.equals("onUnload")) {
                    c = 21;
                    break;
                }
                c = '\uffff';
                break;
            case 1577713133:
                if (str.equals("setScreenOn")) {
                    c = '\b';
                    break;
                }
                c = '\uffff';
                break;
            case 1611994419:
                if (str.equals("isScreenKeptOn")) {
                    c = '\t';
                    break;
                }
                c = '\uffff';
                break;
            case 1741918797:
                if (str.equals("setOrientationLandscape")) {
                    c = 6;
                    break;
                }
                c = '\uffff';
                break;
            case 1995731616:
                if (str.equals("logLevel")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        switch (c) {
            case 0:
                result.success("Android " + Build.VERSION.RELEASE);
                break;
            case 1:
                Log.i("FLUTTER", "call init:" + methodCall.arguments.toString());
                result.success(null);
                break;
            case 2:
                FijkPlayer fijkPlayer = new FijkPlayer(this, false);
                int a2 = fijkPlayer.a();
                this.b.append(a2, fijkPlayer);
                result.success(Integer.valueOf(a2));
                break;
            case 3:
                Integer num = (Integer) methodCall.argument(ConstantKey.PID);
                int intValue = num != null ? num.intValue() : -1;
                FijkPlayer fijkPlayer2 = (FijkPlayer) this.b.get(intValue);
                if (fijkPlayer2 != null) {
                    fijkPlayer2.d();
                    this.b.delete(intValue);
                }
                result.success(null);
                break;
            case 4:
                int i = 500;
                Integer num2 = (Integer) methodCall.argument("level");
                if (num2 != null) {
                    i = num2.intValue();
                }
                int min = Math.min(Math.max(i / 100, 0), 8);
                IjkMediaPlayer.loadLibrariesOnce((IjkLibLoader) null);
                IjkMediaPlayer.native_setLogLevel(min);
                result.success(null);
                break;
            case 5:
                Activity d4 = d();
                if (d4 == null || d4.getResources().getConfiguration().orientation != 2) {
                    z2 = false;
                } else if (Build.VERSION.SDK_INT >= 18) {
                    d4.setRequestedOrientation(12);
                } else {
                    d4.setRequestedOrientation(7);
                }
                result.success(Boolean.valueOf(z2));
                break;
            case 6:
                Activity d5 = d();
                if (d5 == null || d5.getResources().getConfiguration().orientation != 1) {
                    z2 = false;
                } else if (Build.VERSION.SDK_INT >= 18) {
                    d5.setRequestedOrientation(11);
                } else {
                    d5.setRequestedOrientation(6);
                }
                result.success(Boolean.valueOf(z2));
                break;
            case 7:
                Activity d6 = d();
                if (d6 != null) {
                    if (Build.VERSION.SDK_INT >= 18) {
                        d6.setRequestedOrientation(13);
                    } else {
                        d6.setRequestedOrientation(10);
                    }
                }
                result.success(null);
                break;
            case '\b':
                if (methodCall.hasArgument(Camera.Parameters.FLASH_MODE_ON) && (bool = (Boolean) methodCall.argument(Camera.Parameters.FLASH_MODE_ON)) != null) {
                    z = bool.booleanValue();
                }
                a(z);
                result.success(null);
                break;
            case '\t':
                result.success(Boolean.valueOf(e()));
                break;
            case '\n':
                result.success(Float.valueOf(f()));
                break;
            case 11:
                if (methodCall.hasArgument(ConstantKey.BRIGHTNESS) && (d = (Double) methodCall.argument(ConstantKey.BRIGHTNESS)) != null) {
                    a(d.floatValue());
                    break;
                }
            case '\f':
                b(true);
                result.success(null);
                break;
            case '\r':
                b(false);
                result.success(null);
                break;
            case 14:
                float f = this.k;
                if (methodCall.hasArgument("step") && (d2 = (Double) methodCall.argument("step")) != null) {
                    f = d2.floatValue();
                }
                result.success(Float.valueOf(c(f)));
                break;
            case 15:
                float f2 = this.k;
                if (methodCall.hasArgument("step") && (d3 = (Double) methodCall.argument("step")) != null) {
                    f2 = d3.floatValue();
                }
                result.success(Float.valueOf(b(f2)));
                break;
            case 16:
                result.success(Float.valueOf(l()));
                break;
            case 17:
                result.success(Float.valueOf(j()));
                break;
            case 18:
                float j = j();
                Double d7 = (Double) methodCall.argument("vol");
                if (d7 != null) {
                    j = d(d7.floatValue());
                }
                result.success(Float.valueOf(j));
                break;
            case 19:
                Integer num3 = (Integer) methodCall.argument("mode");
                if (num3 != null) {
                    this.j = num3.intValue();
                }
                result.success(null);
                break;
            case 20:
                this.l = true;
                result.success(null);
                break;
            case 21:
                this.l = false;
                result.success(null);
                break;
            default:
                Log.w("FLUTTER", "onMethod Call, name: " + methodCall.method);
                result.notImplemented();
                break;
        }
        AppMethodBeat.o(311);
    }

    @Override // com.befovy.fijkplayer.a
    public void a(int i) {
        this.i += i;
    }

    @Override // com.befovy.fijkplayer.a
    public void b(int i) {
        this.h += i;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i) {
        AppMethodBeat.i(316, false);
        if (i == -2 || i == -1) {
            this.o = false;
            this.n = null;
        }
        Log.i("FIJKPLAYER", "onAudioFocusChange: " + i);
        AppMethodBeat.o(316);
    }

    @Override // com.befovy.fijkplayer.a
    public void a(boolean z) {
        AppMethodBeat.i(319, false);
        Activity d = d();
        if (d == null || d.getWindow() == null) {
            AppMethodBeat.o(319);
            return;
        }
        if (z) {
            d.getWindow().addFlags(128);
        } else {
            d.getWindow().clearFlags(128);
        }
        AppMethodBeat.o(319);
    }

    private boolean e() {
        boolean z = false;
        AppMethodBeat.i(321, false);
        Activity d = d();
        if (d == null || d.getWindow() == null) {
            AppMethodBeat.o(321);
            return false;
        }
        if ((d.getWindow().getAttributes().flags & 128) != 0) {
            z = true;
        }
        AppMethodBeat.o(321);
        return z;
    }

    private float f() {
        AppMethodBeat.i(323, false);
        Activity d = d();
        if (d == null || d.getWindow() == null) {
            AppMethodBeat.o(323);
            return 0.0f;
        }
        float f = d.getWindow().getAttributes().screenBrightness;
        if (f < 0.0f) {
            Context c = c();
            Log.w("FIJKPLAYER", "window attribute brightness less than 0");
            if (c != null) {
                try {
                    f = ((float) Settings.System.getInt(c.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS)) / 255.0f;
                } catch (Settings.SettingNotFoundException unused) {
                    Log.e("FIJKPLAYER", "System brightness settings not found");
                    f = 1.0f;
                }
            }
        }
        AppMethodBeat.o(323);
        return f;
    }

    private void a(float f) {
        AppMethodBeat.i(325, false);
        Activity d = d();
        if (d == null || d.getWindow() == null) {
            AppMethodBeat.o(325);
            return;
        }
        WindowManager.LayoutParams attributes = d.getWindow().getAttributes();
        attributes.screenBrightness = f;
        d.getWindow().setAttributes(attributes);
        AppMethodBeat.o(325);
    }

    private void g() {
        AppMethodBeat.i(326, false);
        AudioManager i = i();
        if (i == null) {
            AppMethodBeat.o(326);
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            AudioFocusRequest build = new AudioFocusRequest.Builder(1).setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(3).build()).setAcceptsDelayedFocusGain(true).setOnAudioFocusChangeListener(this).build();
            this.n = build;
            i.requestAudioFocus(build);
        } else {
            i.requestAudioFocus(this, 3, 1);
        }
        this.o = true;
        AppMethodBeat.o(326);
    }

    private void h() {
        AppMethodBeat.i(327, false);
        AudioManager i = i();
        if (i == null) {
            AppMethodBeat.o(327);
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            Object obj = this.n;
            if (obj != null) {
                i.abandonAudioFocusRequest((AudioFocusRequest) obj);
                this.n = null;
            }
        } else {
            i.abandonAudioFocus(this);
        }
        this.o = false;
        AppMethodBeat.o(327);
    }

    @Override // com.befovy.fijkplayer.a
    public void b(boolean z) {
        AppMethodBeat.i(330, false);
        StringBuilder sb = new StringBuilder();
        sb.append("audioFocus ");
        sb.append(z ? "request" : "release");
        sb.append(" state:");
        sb.append(this.o);
        Log.i("FIJKPLAYER", sb.toString());
        if (z && !this.o) {
            g();
        } else if (this.o) {
            h();
        }
        AppMethodBeat.o(330);
    }

    private AudioManager i() {
        AppMethodBeat.i(332, false);
        Context c = c();
        if (c != null) {
            AudioManager audioManager = (AudioManager) c.getSystemService("audio");
            AppMethodBeat.o(332);
            return audioManager;
        }
        Log.e("FIJKPLAYER", "context null, can't get AudioManager");
        AppMethodBeat.o(332);
        return null;
    }

    private float j() {
        AppMethodBeat.i(334, false);
        AudioManager i = i();
        if (i != null) {
            float streamVolume = ((float) i.getStreamVolume(3)) / ((float) i.getStreamMaxVolume(3));
            AppMethodBeat.o(334);
            return streamVolume;
        }
        AppMethodBeat.o(334);
        return 0.0f;
    }

    private void k() {
        boolean z = false;
        AppMethodBeat.i(336, false);
        if (this.l) {
            if ((m() & 1) > 0) {
                z = true;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("event", "volume");
            hashMap.put("sui", Boolean.valueOf(z));
            hashMap.put("vol", Float.valueOf(j()));
            this.c.success(hashMap);
        }
        AppMethodBeat.o(336);
    }

    private float b(float f) {
        AppMethodBeat.i(338, false);
        float d = d(j() + f);
        AppMethodBeat.o(338);
        return d;
    }

    private float c(float f) {
        AppMethodBeat.i(340, false);
        float d = d(j() - f);
        AppMethodBeat.o(340);
        return d;
    }

    private float l() {
        AppMethodBeat.i(342, false);
        d(0.0f);
        AppMethodBeat.o(342);
        return 0.0f;
    }

    private int m() {
        int i = this.j;
        if (i == 3) {
            return 1;
        }
        if (i == 1 && this.i == 0) {
            return 1;
        }
        if (this.j == 0 && this.h == 0) {
            return 1;
        }
        return 0;
    }

    private float d(float f) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.PHYSICAL_KEYBOARDS, false);
        int m = m();
        AudioManager i = i();
        if (i != null) {
            int streamMaxVolume = i.getStreamMaxVolume(3);
            float f2 = (float) streamMaxVolume;
            int max = Math.max(Math.min((int) (f * f2), streamMaxVolume), 0);
            i.setStreamVolume(3, max, m);
            k();
            float f3 = ((float) max) / f2;
            AppMethodBeat.o(MetricsProto.MetricsEvent.PHYSICAL_KEYBOARDS);
            return f3;
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.PHYSICAL_KEYBOARDS);
        return f;
    }
}
