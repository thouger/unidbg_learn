package io.flutter.embedding.engine;

import android.content.Context;
import io.flutter.FlutterInjector;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.embedding.engine.loader.FlutterLoader;
import java.util.ArrayList;
import java.util.List;

public class FlutterEngineGroup {
    final List<FlutterEngine> activeEngines;

    public FlutterEngineGroup(Context context) {
        this(context, null);
    }

    public FlutterEngineGroup(Context context, String[] strArr) {
        this.activeEngines = new ArrayList();
        FlutterLoader flutterLoader = FlutterInjector.instance().flutterLoader();
        if (!flutterLoader.initialized()) {
            flutterLoader.startInitialization(context.getApplicationContext());
            flutterLoader.ensureInitializationComplete(context, strArr);
        }
    }

    public FlutterEngine createAndRunDefaultEngine(Context context) {
        return createAndRunEngine(context, null);
    }

    public FlutterEngine createAndRunEngine(Context context, DartExecutor.DartEntrypoint dartEntrypoint) {
        FlutterEngine flutterEngine;
        if (dartEntrypoint == null) {
            dartEntrypoint = DartExecutor.DartEntrypoint.createDefault();
        }
        if (this.activeEngines.size() == 0) {
            flutterEngine = createEngine(context);
            flutterEngine.getDartExecutor().executeDartEntrypoint(dartEntrypoint);
        } else {
            flutterEngine = this.activeEngines.get(0).spawn(context, dartEntrypoint);
        }
        this.activeEngines.add(flutterEngine);
        flutterEngine.addEngineLifecycleListener(new AnonymousClass1(flutterEngine));
        return flutterEngine;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: io.flutter.embedding.engine.FlutterEngineGroup$1  reason: invalid class name */
    public class AnonymousClass1 implements FlutterEngine.EngineLifecycleListener {
        final /* synthetic */ FlutterEngine val$engineToCleanUpOnDestroy;

        @Override // io.flutter.embedding.engine.FlutterEngine.EngineLifecycleListener
        public void onPreEngineRestart() {
        }

        AnonymousClass1(FlutterEngine flutterEngine) {
            this.val$engineToCleanUpOnDestroy = flutterEngine;
        }

        @Override // io.flutter.embedding.engine.FlutterEngine.EngineLifecycleListener
        public void onEngineWillDestroy() {
            FlutterEngineGroup.this.activeEngines.remove(this.val$engineToCleanUpOnDestroy);
        }
    }

    /* access modifiers changed from: package-private */
    public FlutterEngine createEngine(Context context) {
        return new FlutterEngine(context);
    }
}
