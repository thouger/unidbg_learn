package io.flutter.plugin.platform;

public interface PlatformViewRegistry {
    boolean registerViewFactory(String str, PlatformViewFactory platformViewFactory);
}
