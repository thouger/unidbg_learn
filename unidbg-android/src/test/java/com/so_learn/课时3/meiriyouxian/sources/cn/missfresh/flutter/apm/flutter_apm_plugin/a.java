package cn.missfresh.flutter.apm.flutter_apm_plugin;

import android.os.Build;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FlutterApmPlugin.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u00012\u00020\u0002:\u0001\u0011B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u00020\u00072\b\b\u0001\u0010\u000b\u001a\u00020\tH\u0016J\u001c\u0010\f\u001a\u00020\u00072\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcn/missfresh/flutter/apm/flutter_apm_plugin/FlutterApmPlugin;", "Lio/flutter/embedding/engine/plugins/FlutterPlugin;", "Lio/flutter/plugin/common/MethodChannel$MethodCallHandler;", "()V", "channel", "Lio/flutter/plugin/common/MethodChannel;", "onAttachedToEngine", "", "flutterPluginBinding", "Lio/flutter/embedding/engine/plugins/FlutterPlugin$FlutterPluginBinding;", "onDetachedFromEngine", "binding", "onMethodCall", "call", "Lio/flutter/plugin/common/MethodCall;", "result", "Lio/flutter/plugin/common/MethodChannel$Result;", "Companion", "flutter_apm_plugin_release"}, k = 1, mv = {1, 1, 15})
public final class a implements FlutterPlugin, MethodChannel.MethodCallHandler {
    public static final a a = new a((DefaultConstructorMarker) null);
    private MethodChannel b;

    static {
        AppMethodBeat.i(21748, false);
        AppMethodBeat.o(21748);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        AppMethodBeat.i(21740, false);
        Intrinsics.checkParameterIsNotNull(flutterPluginBinding, "flutterPluginBinding");
        this.b = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "flutter_apm_plugin");
        MethodChannel methodChannel = this.b;
        if (methodChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("channel");
        }
        methodChannel.setMethodCallHandler(this);
        AppMethodBeat.o(21740);
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        AppMethodBeat.i(21743, false);
        Intrinsics.checkParameterIsNotNull(methodCall, "call");
        Intrinsics.checkParameterIsNotNull(result, "result");
        if (Intrinsics.areEqual(methodCall.method, "getPlatformVersion")) {
            result.success("Android " + Build.VERSION.RELEASE);
        } else {
            result.notImplemented();
        }
        AppMethodBeat.o(21743);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        AppMethodBeat.i(21744, false);
        Intrinsics.checkParameterIsNotNull(flutterPluginBinding, "binding");
        MethodChannel methodChannel = this.b;
        if (methodChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("channel");
        }
        methodChannel.setMethodCallHandler(null);
        AppMethodBeat.o(21744);
    }
}
