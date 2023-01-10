package io.flutter.plugin.platform;

import android.view.View;
import io.flutter.view.AccessibilityBridge;

public interface PlatformViewsAccessibilityDelegate {
    void attachAccessibilityBridge(AccessibilityBridge accessibilityBridge);

    void detachAccessibiltyBridge();

    View getPlatformViewById(Integer num);

    boolean usesVirtualDisplay(Integer num);
}
