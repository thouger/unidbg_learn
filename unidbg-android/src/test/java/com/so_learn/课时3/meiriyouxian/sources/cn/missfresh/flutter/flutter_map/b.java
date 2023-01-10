package cn.missfresh.flutter.flutter_map;

import androidx.lifecycle.Lifecycle;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.embedding.engine.plugins.lifecycle.FlutterLifecycleAdapter;

/* compiled from: TUserAddressMapPlug */
public class b implements FlutterPlugin, ActivityAware {
    private Lifecycle a;

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        AppMethodBeat.i(21401, false);
        flutterPluginBinding.getPlatformViewRegistry().registerViewFactory("address_native_map_view", new d(flutterPluginBinding.getBinaryMessenger(), new AnonymousClass1()));
        AppMethodBeat.o(21401);
    }

    /* compiled from: TUserAddressMapPlug */
    /* renamed from: cn.missfresh.flutter.flutter_map.b$1  reason: invalid class name */
    class AnonymousClass1 implements a {
        AnonymousClass1() {
        }

        public Lifecycle a() {
            AppMethodBeat.i(21372, false);
            Lifecycle lifecycle = b.this.a;
            AppMethodBeat.o(21372);
            return lifecycle;
        }
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        AppMethodBeat.i(21403, false);
        flutterPluginBinding.getFlutterEngine().getPlatformViewsController().onDetachedFromJNI();
        AppMethodBeat.o(21403);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        AppMethodBeat.i(21406, false);
        this.a = FlutterLifecycleAdapter.getActivityLifecycle(activityPluginBinding);
        AppMethodBeat.o(21406);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        AppMethodBeat.i(21407, false);
        onDetachedFromActivity();
        AppMethodBeat.o(21407);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        AppMethodBeat.i(21409, false);
        onAttachedToActivity(activityPluginBinding);
        AppMethodBeat.o(21409);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        this.a = null;
    }
}
