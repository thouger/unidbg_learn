package cn.missfresh.flutter.flutter_video;

import android.app.Activity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.flutter.embedding.engine.plugins.FlutterPlugin;

/* compiled from: FlutterVideoPlayerPlugin */
public class c implements FlutterPlugin {
    private Activity a;

    public c(Activity activity) {
        this.a = activity;
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        AppMethodBeat.i(21566, false);
        flutterPluginBinding.getPlatformViewRegistry().registerViewFactory("native_player_view", new b(flutterPluginBinding.getBinaryMessenger(), this.a));
        AppMethodBeat.o(21566);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        AppMethodBeat.i(21568, false);
        flutterPluginBinding.getFlutterEngine().getPlatformViewsController().onDetachedFromJNI();
        AppMethodBeat.o(21568);
    }
}
