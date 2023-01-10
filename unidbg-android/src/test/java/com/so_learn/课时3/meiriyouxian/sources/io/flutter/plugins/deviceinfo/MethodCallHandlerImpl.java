package io.flutter.plugins.deviceinfo;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbManager;
import android.media.midi.MidiDeviceInfo;
import android.os.Build;
import android.provider.Settings;
import com.taobao.accs.common.Constants;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.Arrays;
import java.util.HashMap;

/* access modifiers changed from: package-private */
public class MethodCallHandlerImpl implements MethodChannel.MethodCallHandler {
    private static final String[] EMPTY_STRING_LIST = new String[0];
    private final ContentResolver contentResolver;
    private final PackageManager packageManager;

    MethodCallHandlerImpl(ContentResolver contentResolver, PackageManager packageManager) {
        this.contentResolver = contentResolver;
        this.packageManager = packageManager;
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        if (methodCall.method.equals("getAndroidDeviceInfo")) {
            HashMap hashMap = new HashMap();
            hashMap.put("board", Build.BOARD);
            hashMap.put("bootloader", Build.BOOTLOADER);
            hashMap.put("brand", Build.BRAND);
            hashMap.put(UsbManager.EXTRA_DEVICE, Build.DEVICE);
            hashMap.put(Context.DISPLAY_SERVICE, Build.DISPLAY);
            hashMap.put(Context.FINGERPRINT_SERVICE, Build.FINGERPRINT);
            hashMap.put("hardware", Build.HARDWARE);
            hashMap.put(Constants.KEY_HOST, Build.HOST);
            hashMap.put("id", Build.ID);
            hashMap.put(MidiDeviceInfo.PROPERTY_MANUFACTURER, Build.MANUFACTURER);
            hashMap.put(Constants.KEY_MODEL, Build.MODEL);
            hashMap.put(MidiDeviceInfo.PROPERTY_PRODUCT, Build.PRODUCT);
            if (Build.VERSION.SDK_INT >= 21) {
                hashMap.put("supported32BitAbis", Arrays.asList(Build.SUPPORTED_32_BIT_ABIS));
                hashMap.put("supported64BitAbis", Arrays.asList(Build.SUPPORTED_64_BIT_ABIS));
                hashMap.put("supportedAbis", Arrays.asList(Build.SUPPORTED_ABIS));
            } else {
                hashMap.put("supported32BitAbis", Arrays.asList(EMPTY_STRING_LIST));
                hashMap.put("supported64BitAbis", Arrays.asList(EMPTY_STRING_LIST));
                hashMap.put("supportedAbis", Arrays.asList(EMPTY_STRING_LIST));
            }
            hashMap.put("tags", Build.TAGS);
            hashMap.put("type", Build.TYPE);
            hashMap.put("isPhysicalDevice", Boolean.valueOf(!isEmulator()));
            hashMap.put("androidId", getAndroidId());
            hashMap.put("systemFeatures", Arrays.asList(getSystemFeatures()));
            HashMap hashMap2 = new HashMap();
            if (Build.VERSION.SDK_INT >= 23) {
                hashMap2.put("baseOS", Build.VERSION.BASE_OS);
                hashMap2.put("previewSdkInt", Integer.valueOf(Build.VERSION.PREVIEW_SDK_INT));
                hashMap2.put("securityPatch", Build.VERSION.SECURITY_PATCH);
            }
            hashMap2.put("codename", Build.VERSION.CODENAME);
            hashMap2.put("incremental", Build.VERSION.INCREMENTAL);
            hashMap2.put("release", Build.VERSION.RELEASE);
            hashMap2.put("sdkInt", Integer.valueOf(Build.VERSION.SDK_INT));
            hashMap.put("version", hashMap2);
            result.success(hashMap);
            return;
        }
        result.notImplemented();
    }

    private String[] getSystemFeatures() {
        FeatureInfo[] systemAvailableFeatures = this.packageManager.getSystemAvailableFeatures();
        if (systemAvailableFeatures == null) {
            return EMPTY_STRING_LIST;
        }
        String[] strArr = new String[systemAvailableFeatures.length];
        for (int i = 0; i < systemAvailableFeatures.length; i++) {
            strArr[i] = systemAvailableFeatures[i].name;
        }
        return strArr;
    }

    private String getAndroidId() {
        return Settings.Secure.getString(this.contentResolver, "android_id");
    }

    private boolean isEmulator() {
        return (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.HARDWARE.contains("goldfish") || Build.HARDWARE.contains("ranchu") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || Build.PRODUCT.contains("sdk_google") || Build.PRODUCT.contains("google_sdk") || Build.PRODUCT.contains("sdk") || Build.PRODUCT.contains("sdk_x86") || Build.PRODUCT.contains("vbox86p") || Build.PRODUCT.contains("emulator") || Build.PRODUCT.contains("simulator");
    }
}
