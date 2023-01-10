package cn.missfresh.flutter.flutter_map;

import androidx.lifecycle.Lifecycle;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.embedding.engine.plugins.lifecycle.FlutterLifecycleAdapter;

/* compiled from: TmapPlugin */
public class e implements FlutterPlugin, ActivityAware {
    private Lifecycle a;

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        AppMethodBeat.i(21510, false);
        flutterPluginBinding.getPlatformViewRegistry().registerViewFactory("t_map_view", new c(flutterPluginBinding.getBinaryMessenger(), new AnonymousClass1()));
        AppMethodBeat.o(21510);
    }

    /* compiled from: TmapPlugin */
    /* renamed from: cn.missfresh.flutter.flutter_map.e$1  reason: invalid class name */
    class AnonymousClass1 implements a {
        AnonymousClass1() {
        }

        public Lifecycle a() {
            AppMethodBeat.i(21433, false);
            Lifecycle lifecycle = e.this.a;
            AppMethodBeat.o(21433);
            return lifecycle;
        }
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        AppMethodBeat.i(21515, false);
        d.d("lhj", "onDetachedFromEngine");
        flutterPluginBinding.getFlutterEngine().getPlatformViewsController().onDetachedFromJNI();
        AppMethodBeat.o(21515);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        AppMethodBeat.i(21517, false);
        this.a = FlutterLifecycleAdapter.getActivityLifecycle(activityPluginBinding);
        AppMethodBeat.o(21517);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        AppMethodBeat.i(21518, false);
        onDetachedFromActivity();
        AppMethodBeat.o(21518);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        AppMethodBeat.i(21519, false);
        onAttachedToActivity(activityPluginBinding);
        AppMethodBeat.o(21519);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        this.a = null;
    }
}
