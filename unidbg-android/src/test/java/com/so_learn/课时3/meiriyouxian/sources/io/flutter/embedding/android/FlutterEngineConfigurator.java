package io.flutter.embedding.android;

import io.flutter.embedding.engine.FlutterEngine;

public interface FlutterEngineConfigurator {
    void cleanUpFlutterEngine(FlutterEngine flutterEngine);

    void configureFlutterEngine(FlutterEngine flutterEngine);
}
