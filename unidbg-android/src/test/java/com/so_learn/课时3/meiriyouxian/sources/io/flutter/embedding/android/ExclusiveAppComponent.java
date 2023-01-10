package io.flutter.embedding.android;

public interface ExclusiveAppComponent<T> {
    void detachFromFlutterEngine();

    T getAppComponent();
}
