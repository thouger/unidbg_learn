package io.flutter.embedding.android;

import android.content.Context;
import io.flutter.embedding.engine.FlutterEngine;

public interface FlutterEngineProvider {
    FlutterEngine provideFlutterEngine(Context context);
}
