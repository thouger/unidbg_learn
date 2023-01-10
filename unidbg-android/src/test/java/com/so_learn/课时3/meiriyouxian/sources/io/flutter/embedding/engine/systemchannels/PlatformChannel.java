package io.flutter.embedding.engine.systemchannels;

import android.content.ClipDescription;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.JSONMethodCodec;
import io.flutter.plugin.common.MethodChannel;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlatformChannel {
    private static final String TAG = "PlatformChannel";
    public final MethodChannel channel;
    final MethodChannel.MethodCallHandler parsingMethodCallHandler = new AnonymousClass1();
    private PlatformMessageHandler platformMessageHandler;

    public interface PlatformMessageHandler {
        boolean clipboardHasStrings();

        CharSequence getClipboardData(ClipboardContentFormat clipboardContentFormat);

        void playSystemSound(SoundType soundType);

        void popSystemNavigator();

        void restoreSystemUiOverlays();

        void setApplicationSwitcherDescription(AppSwitcherDescription appSwitcherDescription);

        void setClipboardData(String str);

        void setPreferredOrientations(int i);

        void setSystemUiOverlayStyle(SystemChromeStyle systemChromeStyle);

        void showSystemOverlays(List<SystemUiOverlay> list);

        void vibrateHapticFeedback(HapticFeedbackType hapticFeedbackType);
    }

    /* renamed from: io.flutter.embedding.engine.systemchannels.PlatformChannel$1  reason: invalid class name */
    class AnonymousClass1 implements MethodChannel.MethodCallHandler {
        AnonymousClass1() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:55:0x0119  */
        /* JADX WARNING: Removed duplicated region for block: B:56:0x0126  */
        @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onMethodCall(io.flutter.plugin.common.MethodCall r6, io.flutter.plugin.common.MethodChannel.Result r7) {
            /*
            // Method dump skipped, instructions count: 614
            */
            throw new UnsupportedOperationException("Method not decompiled: io.flutter.embedding.engine.systemchannels.PlatformChannel.AnonymousClass1.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
        }
    }

    public PlatformChannel(DartExecutor dartExecutor) {
        this.channel = new MethodChannel(dartExecutor, "flutter/platform", JSONMethodCodec.INSTANCE);
        this.channel.setMethodCallHandler(this.parsingMethodCallHandler);
    }

    public void setPlatformMessageHandler(PlatformMessageHandler platformMessageHandler) {
        this.platformMessageHandler = platformMessageHandler;
    }

    /* access modifiers changed from: private */
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* access modifiers changed from: public */
    private int decodeOrientations(JSONArray jSONArray) throws JSONException, NoSuchFieldException {
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < jSONArray.length(); i++) {
            int i2 = AnonymousClass2.$SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$DeviceOrientation[DeviceOrientation.fromValue(jSONArray.getString(i)).ordinal()];
            if (i2 == 1) {
                z |= true;
            } else if (i2 == 2) {
                z |= true;
            } else if (i2 == 3) {
                z |= true;
            } else if (i2 == 4) {
                z |= true;
            }
            if (!z2) {
                z2 = z;
            }
        }
        switch (z) {
            case false:
                return -1;
            case true:
                return 1;
            case true:
                return 0;
            case true:
            case true:
            case true:
            case true:
            case true:
            case true:
            case true:
                if (z2) {
                    return 1;
                }
                if (z2) {
                    return 0;
                }
                if (z2) {
                    return 9;
                }
                if (z2) {
                    return 8;
                }
                break;
            case true:
                return 9;
            case true:
                return 12;
            case true:
                return 8;
            case true:
                return 11;
            case true:
                return 2;
            case true:
                return 13;
        }
        return 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private AppSwitcherDescription decodeAppSwitcherDescription(JSONObject jSONObject) throws JSONException {
        int i = jSONObject.getInt("primaryColor");
        if (i != 0) {
            i |= -16777216;
        }
        return new AppSwitcherDescription(i, jSONObject.getString("label"));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private List<SystemUiOverlay> decodeSystemUiOverlays(JSONArray jSONArray) throws JSONException, NoSuchFieldException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            int i2 = AnonymousClass2.$SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiOverlay[SystemUiOverlay.fromValue(jSONArray.getString(i)).ordinal()];
            if (i2 == 1) {
                arrayList.add(SystemUiOverlay.TOP_OVERLAYS);
            } else if (i2 == 2) {
                arrayList.add(SystemUiOverlay.BOTTOM_OVERLAYS);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: io.flutter.embedding.engine.systemchannels.PlatformChannel$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$DeviceOrientation = new int[DeviceOrientation.values().length];
        static final /* synthetic */ int[] $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiOverlay = new int[SystemUiOverlay.values().length];

        static {
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiOverlay[SystemUiOverlay.TOP_OVERLAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$SystemUiOverlay[SystemUiOverlay.BOTTOM_OVERLAYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$DeviceOrientation[DeviceOrientation.PORTRAIT_UP.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$DeviceOrientation[DeviceOrientation.PORTRAIT_DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$DeviceOrientation[DeviceOrientation.LANDSCAPE_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$flutter$embedding$engine$systemchannels$PlatformChannel$DeviceOrientation[DeviceOrientation.LANDSCAPE_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private SystemChromeStyle decodeSystemChromeStyle(JSONObject jSONObject) throws JSONException, NoSuchFieldException {
        Integer num = null;
        Brightness fromValue = !jSONObject.isNull("systemNavigationBarIconBrightness") ? Brightness.fromValue(jSONObject.getString("systemNavigationBarIconBrightness")) : null;
        Integer valueOf = !jSONObject.isNull("systemNavigationBarColor") ? Integer.valueOf(jSONObject.getInt("systemNavigationBarColor")) : null;
        Brightness fromValue2 = !jSONObject.isNull("statusBarIconBrightness") ? Brightness.fromValue(jSONObject.getString("statusBarIconBrightness")) : null;
        Integer valueOf2 = !jSONObject.isNull("statusBarColor") ? Integer.valueOf(jSONObject.getInt("statusBarColor")) : null;
        if (!jSONObject.isNull("systemNavigationBarDividerColor")) {
            num = Integer.valueOf(jSONObject.getInt("systemNavigationBarDividerColor"));
        }
        return new SystemChromeStyle(valueOf2, fromValue2, valueOf, fromValue, num);
    }

    public enum SoundType {
        CLICK("SystemSoundType.click"),
        ALERT("SystemSoundType.alert");
        
        private final String encodedName;

        static SoundType fromValue(String str) throws NoSuchFieldException {
            SoundType[] values = values();
            for (SoundType soundType : values) {
                if (soundType.encodedName.equals(str)) {
                    return soundType;
                }
            }
            throw new NoSuchFieldException("No such SoundType: " + str);
        }

        private SoundType(String str) {
            this.encodedName = str;
        }
    }

    public enum HapticFeedbackType {
        STANDARD(null),
        LIGHT_IMPACT("HapticFeedbackType.lightImpact"),
        MEDIUM_IMPACT("HapticFeedbackType.mediumImpact"),
        HEAVY_IMPACT("HapticFeedbackType.heavyImpact"),
        SELECTION_CLICK("HapticFeedbackType.selectionClick");
        
        private final String encodedName;

        static HapticFeedbackType fromValue(String str) throws NoSuchFieldException {
            String str2;
            HapticFeedbackType[] values = values();
            for (HapticFeedbackType hapticFeedbackType : values) {
                if ((hapticFeedbackType.encodedName == null && str == null) || ((str2 = hapticFeedbackType.encodedName) != null && str2.equals(str))) {
                    return hapticFeedbackType;
                }
            }
            throw new NoSuchFieldException("No such HapticFeedbackType: " + str);
        }

        private HapticFeedbackType(String str) {
            this.encodedName = str;
        }
    }

    public enum DeviceOrientation {
        PORTRAIT_UP("DeviceOrientation.portraitUp"),
        PORTRAIT_DOWN("DeviceOrientation.portraitDown"),
        LANDSCAPE_LEFT("DeviceOrientation.landscapeLeft"),
        LANDSCAPE_RIGHT("DeviceOrientation.landscapeRight");
        
        private String encodedName;

        static DeviceOrientation fromValue(String str) throws NoSuchFieldException {
            DeviceOrientation[] values = values();
            for (DeviceOrientation deviceOrientation : values) {
                if (deviceOrientation.encodedName.equals(str)) {
                    return deviceOrientation;
                }
            }
            throw new NoSuchFieldException("No such DeviceOrientation: " + str);
        }

        private DeviceOrientation(String str) {
            this.encodedName = str;
        }
    }

    public enum SystemUiOverlay {
        TOP_OVERLAYS("SystemUiOverlay.top"),
        BOTTOM_OVERLAYS("SystemUiOverlay.bottom");
        
        private String encodedName;

        static SystemUiOverlay fromValue(String str) throws NoSuchFieldException {
            SystemUiOverlay[] values = values();
            for (SystemUiOverlay systemUiOverlay : values) {
                if (systemUiOverlay.encodedName.equals(str)) {
                    return systemUiOverlay;
                }
            }
            throw new NoSuchFieldException("No such SystemUiOverlay: " + str);
        }

        private SystemUiOverlay(String str) {
            this.encodedName = str;
        }
    }

    public static class AppSwitcherDescription {
        public final int color;
        public final String label;

        public AppSwitcherDescription(int i, String str) {
            this.color = i;
            this.label = str;
        }
    }

    public static class SystemChromeStyle {
        public final Integer statusBarColor;
        public final Brightness statusBarIconBrightness;
        public final Integer systemNavigationBarColor;
        public final Integer systemNavigationBarDividerColor;
        public final Brightness systemNavigationBarIconBrightness;

        public SystemChromeStyle(Integer num, Brightness brightness, Integer num2, Brightness brightness2, Integer num3) {
            this.statusBarColor = num;
            this.statusBarIconBrightness = brightness;
            this.systemNavigationBarColor = num2;
            this.systemNavigationBarIconBrightness = brightness2;
            this.systemNavigationBarDividerColor = num3;
        }
    }

    public enum Brightness {
        LIGHT("Brightness.light"),
        DARK("Brightness.dark");
        
        private String encodedName;

        static Brightness fromValue(String str) throws NoSuchFieldException {
            Brightness[] values = values();
            for (Brightness brightness : values) {
                if (brightness.encodedName.equals(str)) {
                    return brightness;
                }
            }
            throw new NoSuchFieldException("No such Brightness: " + str);
        }

        private Brightness(String str) {
            this.encodedName = str;
        }
    }

    public enum ClipboardContentFormat {
        PLAIN_TEXT(ClipDescription.MIMETYPE_TEXT_PLAIN);
        
        private String encodedName;

        static ClipboardContentFormat fromValue(String str) throws NoSuchFieldException {
            ClipboardContentFormat[] values = values();
            for (ClipboardContentFormat clipboardContentFormat : values) {
                if (clipboardContentFormat.encodedName.equals(str)) {
                    return clipboardContentFormat;
                }
            }
            throw new NoSuchFieldException("No such ClipboardContentFormat: " + str);
        }

        private ClipboardContentFormat(String str) {
            this.encodedName = str;
        }
    }
}
