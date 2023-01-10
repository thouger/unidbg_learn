package io.flutter.plugins.imagepicker;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaCodec;
import android.net.Uri;
import io.flutter.plugin.common.MethodCall;
import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
public class ImagePickerCache {
    private static final String FLUTTER_IMAGE_PICKER_IMAGE_PATH_KEY = "flutter_image_picker_image_path";
    private static final String MAP_KEY_ERROR_CODE = "errorCode";
    private static final String MAP_KEY_ERROR_MESSAGE = "errorMessage";
    static final String MAP_KEY_IMAGE_QUALITY = "imageQuality";
    static final String MAP_KEY_MAX_HEIGHT = "maxHeight";
    static final String MAP_KEY_MAX_WIDTH = "maxWidth";
    static final String MAP_KEY_PATH = "path";
    private static final String MAP_KEY_TYPE = "type";
    static final String SHARED_PREFERENCES_NAME = "flutter_image_picker_shared_preference";
    private static final String SHARED_PREFERENCE_ERROR_CODE_KEY = "flutter_image_picker_error_code";
    private static final String SHARED_PREFERENCE_ERROR_MESSAGE_KEY = "flutter_image_picker_error_message";
    private static final String SHARED_PREFERENCE_IMAGE_QUALITY_KEY = "flutter_image_picker_image_quality";
    private static final String SHARED_PREFERENCE_MAX_HEIGHT_KEY = "flutter_image_picker_max_height";
    private static final String SHARED_PREFERENCE_MAX_WIDTH_KEY = "flutter_image_picker_max_width";
    private static final String SHARED_PREFERENCE_PENDING_IMAGE_URI_PATH_KEY = "flutter_image_picker_pending_image_uri";
    private static final String SHARED_PREFERENCE_TYPE_KEY = "flutter_image_picker_type";
    private SharedPreferences prefs;

    ImagePickerCache(Context context) {
        this.prefs = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
    }

    /* access modifiers changed from: package-private */
    public void saveTypeWithMethodCallName(String str) {
        if (str.equals("pickImage")) {
            setType("image");
        } else if (str.equals("pickVideo")) {
            setType(MediaCodec.MetricsConstants.MODE_VIDEO);
        }
    }

    private void setType(String str) {
        this.prefs.edit().putString(SHARED_PREFERENCE_TYPE_KEY, str).apply();
    }

    /* access modifiers changed from: package-private */
    public void saveDimensionWithMethodCall(MethodCall methodCall) {
        int i;
        Double d = (Double) methodCall.argument(MAP_KEY_MAX_WIDTH);
        Double d2 = (Double) methodCall.argument(MAP_KEY_MAX_HEIGHT);
        if (methodCall.argument(MAP_KEY_IMAGE_QUALITY) == null) {
            i = 100;
        } else {
            i = ((Integer) methodCall.argument(MAP_KEY_IMAGE_QUALITY)).intValue();
        }
        setMaxDimension(d, d2, i);
    }

    private void setMaxDimension(Double d, Double d2, int i) {
        SharedPreferences.Editor edit = this.prefs.edit();
        if (d != null) {
            edit.putLong(SHARED_PREFERENCE_MAX_WIDTH_KEY, Double.doubleToRawLongBits(d.doubleValue()));
        }
        if (d2 != null) {
            edit.putLong(SHARED_PREFERENCE_MAX_HEIGHT_KEY, Double.doubleToRawLongBits(d2.doubleValue()));
        }
        if (i <= -1 || i >= 101) {
            edit.putInt(SHARED_PREFERENCE_IMAGE_QUALITY_KEY, 100);
        } else {
            edit.putInt(SHARED_PREFERENCE_IMAGE_QUALITY_KEY, i);
        }
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public void savePendingCameraMediaUriPath(Uri uri) {
        this.prefs.edit().putString(SHARED_PREFERENCE_PENDING_IMAGE_URI_PATH_KEY, uri.getPath()).apply();
    }

    /* access modifiers changed from: package-private */
    public String retrievePendingCameraMediaUriPath() {
        return this.prefs.getString(SHARED_PREFERENCE_PENDING_IMAGE_URI_PATH_KEY, "");
    }

    /* access modifiers changed from: package-private */
    public void saveResult(String str, String str2, String str3) {
        SharedPreferences.Editor edit = this.prefs.edit();
        if (str != null) {
            edit.putString(FLUTTER_IMAGE_PICKER_IMAGE_PATH_KEY, str);
        }
        if (str2 != null) {
            edit.putString(SHARED_PREFERENCE_ERROR_CODE_KEY, str2);
        }
        if (str3 != null) {
            edit.putString(SHARED_PREFERENCE_ERROR_MESSAGE_KEY, str3);
        }
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.prefs.edit().clear().apply();
    }

    /* access modifiers changed from: package-private */
    public Map<String, Object> getCacheMap() {
        boolean z;
        HashMap hashMap = new HashMap();
        if (this.prefs.contains(FLUTTER_IMAGE_PICKER_IMAGE_PATH_KEY)) {
            hashMap.put(MAP_KEY_PATH, this.prefs.getString(FLUTTER_IMAGE_PICKER_IMAGE_PATH_KEY, ""));
            z = true;
        } else {
            z = false;
        }
        if (this.prefs.contains(SHARED_PREFERENCE_ERROR_CODE_KEY)) {
            hashMap.put("errorCode", this.prefs.getString(SHARED_PREFERENCE_ERROR_CODE_KEY, ""));
            if (this.prefs.contains(SHARED_PREFERENCE_ERROR_MESSAGE_KEY)) {
                hashMap.put("errorMessage", this.prefs.getString(SHARED_PREFERENCE_ERROR_MESSAGE_KEY, ""));
            }
            z = true;
        }
        if (z) {
            if (this.prefs.contains(SHARED_PREFERENCE_TYPE_KEY)) {
                hashMap.put("type", this.prefs.getString(SHARED_PREFERENCE_TYPE_KEY, ""));
            }
            if (this.prefs.contains(SHARED_PREFERENCE_MAX_WIDTH_KEY)) {
                hashMap.put(MAP_KEY_MAX_WIDTH, Double.valueOf(Double.longBitsToDouble(this.prefs.getLong(SHARED_PREFERENCE_MAX_WIDTH_KEY, 0))));
            }
            if (this.prefs.contains(SHARED_PREFERENCE_MAX_HEIGHT_KEY)) {
                hashMap.put(MAP_KEY_MAX_HEIGHT, Double.valueOf(Double.longBitsToDouble(this.prefs.getLong(SHARED_PREFERENCE_MAX_HEIGHT_KEY, 0))));
            }
            if (this.prefs.contains(SHARED_PREFERENCE_IMAGE_QUALITY_KEY)) {
                hashMap.put(MAP_KEY_IMAGE_QUALITY, Integer.valueOf(this.prefs.getInt(SHARED_PREFERENCE_IMAGE_QUALITY_KEY, 100)));
            } else {
                hashMap.put(MAP_KEY_IMAGE_QUALITY, 100);
            }
        }
        return hashMap;
    }
}
