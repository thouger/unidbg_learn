package io.flutter.embedding.engine.systemchannels;

import android.os.Build;
import android.provider.UserDictionary;
import com.umeng.message.proguard.l;
import io.flutter.Log;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.JSONMethodCodec;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class LocalizationChannel {
    private static final String TAG = "LocalizationChannel";
    public final MethodChannel channel;
    public final MethodChannel.MethodCallHandler handler = new AnonymousClass1();
    private LocalizationMessageHandler localizationMessageHandler;

    public interface LocalizationMessageHandler {
        String getStringResource(String str, String str2);
    }

    /* renamed from: io.flutter.embedding.engine.systemchannels.LocalizationChannel$1  reason: invalid class name */
    class AnonymousClass1 implements MethodChannel.MethodCallHandler {
        AnonymousClass1() {
        }

        @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
        public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
            if (LocalizationChannel.this.localizationMessageHandler != null) {
                String str = methodCall.method;
                char c = '\uffff';
                if (str.hashCode() == -259484608 && str.equals("Localization.getStringResource")) {
                    c = 0;
                }
                if (c != 0) {
                    result.notImplemented();
                    return;
                }
                JSONObject jSONObject = (JSONObject) methodCall.arguments();
                try {
                    result.success(LocalizationChannel.this.localizationMessageHandler.getStringResource(jSONObject.getString("key"), jSONObject.has(UserDictionary.Words.LOCALE) ? jSONObject.getString(UserDictionary.Words.LOCALE) : null));
                } catch (JSONException e) {
                    result.error("error", e.getMessage(), null);
                }
            }
        }
    }

    public LocalizationChannel(DartExecutor dartExecutor) {
        this.channel = new MethodChannel(dartExecutor, "flutter/localization", JSONMethodCodec.INSTANCE);
        this.channel.setMethodCallHandler(this.handler);
    }

    public void setLocalizationMessageHandler(LocalizationMessageHandler localizationMessageHandler) {
        this.localizationMessageHandler = localizationMessageHandler;
    }

    public void sendLocales(List<Locale> list) {
        Log.v(TAG, "Sending Locales to Flutter.");
        ArrayList arrayList = new ArrayList();
        for (Locale locale : list) {
            Log.v(TAG, "Locale (Language: " + locale.getLanguage() + ", Country: " + locale.getCountry() + ", Variant: " + locale.getVariant() + l.t);
            arrayList.add(locale.getLanguage());
            arrayList.add(locale.getCountry());
            arrayList.add(Build.VERSION.SDK_INT >= 21 ? locale.getScript() : "");
            arrayList.add(locale.getVariant());
        }
        this.channel.invokeMethod("setLocale", arrayList);
    }
}
