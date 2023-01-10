package io.flutter.app;

import com.google.android.play.core.splitcompat.SplitCompatApplication;
import io.flutter.FlutterInjector;
import io.flutter.embedding.engine.deferredcomponents.PlayStoreDeferredComponentManager;

public class FlutterPlayStoreSplitApplication extends SplitCompatApplication {
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: io.flutter.app.FlutterPlayStoreSplitApplication */
    /* JADX WARN: Multi-variable type inference failed */
    public void onCreate() {
        FlutterPlayStoreSplitApplication.super.onCreate();
        FlutterInjector.setInstance(new FlutterInjector.Builder().setDeferredComponentManager(new PlayStoreDeferredComponentManager(this, null)).build());
    }
}
