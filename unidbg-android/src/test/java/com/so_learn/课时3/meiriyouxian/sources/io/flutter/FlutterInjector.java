package io.flutter;

import io.flutter.embedding.engine.deferredcomponents.DeferredComponentManager;
import io.flutter.embedding.engine.loader.FlutterLoader;

public final class FlutterInjector {
    private static boolean accessed;
    private static FlutterInjector instance;
    private DeferredComponentManager deferredComponentManager;
    private FlutterLoader flutterLoader;

    public static void setInstance(FlutterInjector flutterInjector) {
        if (!accessed) {
            instance = flutterInjector;
            return;
        }
        throw new IllegalStateException("Cannot change the FlutterInjector instance once it's been read. If you're trying to dependency inject, be sure to do so at the beginning of the program");
    }

    public static FlutterInjector instance() {
        accessed = true;
        if (instance == null) {
            instance = new Builder().build();
        }
        return instance;
    }

    public static void reset() {
        accessed = false;
        instance = null;
    }

    private FlutterInjector(FlutterLoader flutterLoader, DeferredComponentManager deferredComponentManager) {
        this.flutterLoader = flutterLoader;
        this.deferredComponentManager = deferredComponentManager;
    }

    public FlutterLoader flutterLoader() {
        return this.flutterLoader;
    }

    public DeferredComponentManager deferredComponentManager() {
        return this.deferredComponentManager;
    }

    public static final class Builder {
        private DeferredComponentManager deferredComponentManager;
        private FlutterLoader flutterLoader;

        public Builder setFlutterLoader(FlutterLoader flutterLoader) {
            this.flutterLoader = flutterLoader;
            return this;
        }

        public Builder setDeferredComponentManager(DeferredComponentManager deferredComponentManager) {
            this.deferredComponentManager = deferredComponentManager;
            return this;
        }

        private void fillDefaults() {
            if (this.flutterLoader == null) {
                this.flutterLoader = new FlutterLoader();
            }
        }

        public FlutterInjector build() {
            fillDefaults();
            return new FlutterInjector(this.flutterLoader, this.deferredComponentManager);
        }
    }
}
