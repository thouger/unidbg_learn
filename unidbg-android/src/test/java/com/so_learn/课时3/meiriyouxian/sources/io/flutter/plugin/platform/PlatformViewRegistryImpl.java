package io.flutter.plugin.platform;

import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
public class PlatformViewRegistryImpl implements PlatformViewRegistry {
    private final Map<String, PlatformViewFactory> viewFactories = new HashMap();

    PlatformViewRegistryImpl() {
    }

    @Override // io.flutter.plugin.platform.PlatformViewRegistry
    public boolean registerViewFactory(String str, PlatformViewFactory platformViewFactory) {
        if (this.viewFactories.containsKey(str)) {
            return false;
        }
        this.viewFactories.put(str, platformViewFactory);
        return true;
    }

    /* access modifiers changed from: package-private */
    public PlatformViewFactory getFactory(String str) {
        return this.viewFactories.get(str);
    }
}
