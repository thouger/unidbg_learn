package io.flutter.plugins.pathprovider;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.google.common.util.concurrent.k;
import com.google.common.util.concurrent.l;
import com.google.common.util.concurrent.u;
import com.google.common.util.concurrent.v;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.util.PathUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PathProviderPlugin implements FlutterPlugin, MethodChannel.MethodCallHandler {
    private MethodChannel channel;
    private Context context;
    private final Executor executor = Executors.newSingleThreadExecutor(new v().a("path-provider-background-%d").a(5).a());
    private final Executor uiThreadExecutor = new UiThreadExecutor(null);

    public static void registerWith(PluginRegistry.Registrar registrar) {
        PathProviderPlugin pathProviderPlugin = new PathProviderPlugin();
        pathProviderPlugin.channel = new MethodChannel(registrar.messenger(), "plugins.flutter.io/path_provider");
        pathProviderPlugin.context = registrar.context();
        pathProviderPlugin.channel.setMethodCallHandler(pathProviderPlugin);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "plugins.flutter.io/path_provider");
        this.context = flutterPluginBinding.getApplicationContext();
        this.channel.setMethodCallHandler(this);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.channel.setMethodCallHandler(null);
        this.channel = null;
    }

    private <T> void executeInBackground(Callable<T> callable, MethodChannel.Result result) {
        u h = u.h();
        l.a(h, new AnonymousClass1(result), this.uiThreadExecutor);
        this.executor.execute(new $$Lambda$PathProviderPlugin$wVOjDYe3FLc5wOqUxjIbxftTfE(h, callable));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: io.flutter.plugins.pathprovider.PathProviderPlugin$1  reason: invalid class name */
    public class AnonymousClass1 implements k<T> {
        final /* synthetic */ MethodChannel.Result val$result;

        AnonymousClass1(MethodChannel.Result result) {
            this.val$result = result;
        }

        @Override // com.google.common.util.concurrent.k
        public void onSuccess(T t) {
            this.val$result.success(t);
        }

        @Override // com.google.common.util.concurrent.k
        public void onFailure(Throwable th) {
            this.val$result.error(th.getClass().getName(), th.getMessage(), null);
        }
    }

    static /* synthetic */ void lambda$executeInBackground$0(u uVar, Callable callable) {
        try {
            uVar.a((u) callable.call());
        } catch (Throwable th) {
            uVar.a(th);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        char c;
        String str = methodCall.method;
        switch (str.hashCode()) {
            case -1832373352:
                if (str.equals("getApplicationSupportDirectory")) {
                    c = 5;
                    break;
                }
                c = '\uffff';
                break;
            case -1208689078:
                if (str.equals("getExternalCacheDirectories")) {
                    c = 3;
                    break;
                }
                c = '\uffff';
                break;
            case 299667825:
                if (str.equals("getExternalStorageDirectories")) {
                    c = 4;
                    break;
                }
                c = '\uffff';
                break;
            case 1200320591:
                if (str.equals("getApplicationDocumentsDirectory")) {
                    c = 1;
                    break;
                }
                c = '\uffff';
                break;
            case 1252916648:
                if (str.equals("getStorageDirectory")) {
                    c = 2;
                    break;
                }
                c = '\uffff';
                break;
            case 1711844626:
                if (str.equals("getTemporaryDirectory")) {
                    c = 0;
                    break;
                }
                c = '\uffff';
                break;
            default:
                c = '\uffff';
                break;
        }
        if (c == 0) {
            executeInBackground(new $$Lambda$PathProviderPlugin$2AlcW75sSIWrRCKeD3XqFCfTEFg(this), result);
        } else if (c == 1) {
            executeInBackground(new $$Lambda$PathProviderPlugin$YAqYFjJ9PjraJyroxjv4wFjrsFs(this), result);
        } else if (c == 2) {
            executeInBackground(new $$Lambda$PathProviderPlugin$Jxhlq0H6YiJYZdnmNldu4VTAaZ0(this), result);
        } else if (c == 3) {
            executeInBackground(new $$Lambda$PathProviderPlugin$0ezgzisDVZL6MIuOApAQeR0qIE(this), result);
        } else if (c == 4) {
            executeInBackground(new $$Lambda$PathProviderPlugin$AWDwT09UE2yjX1tCivV8M2dsQw(this, StorageDirectoryMapper.androidType((Integer) methodCall.argument("type"))), result);
        } else if (c != 5) {
            result.notImplemented();
        } else {
            executeInBackground(new $$Lambda$PathProviderPlugin$tarQmy0AXgKFg8B5Aioy9praxss(this), result);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: getPathProviderTemporaryDirectory */
    public String lambda$onMethodCall$1$PathProviderPlugin() {
        return this.context.getCacheDir().getPath();
    }

    /* access modifiers changed from: private */
    /* renamed from: getApplicationSupportDirectory */
    public String lambda$onMethodCall$6$PathProviderPlugin() {
        return PathUtils.getFilesDir(this.context);
    }

    /* access modifiers changed from: private */
    /* renamed from: getPathProviderApplicationDocumentsDirectory */
    public String lambda$onMethodCall$2$PathProviderPlugin() {
        return PathUtils.getDataDirectory(this.context);
    }

    /* access modifiers changed from: private */
    /* renamed from: getPathProviderStorageDirectory */
    public String lambda$onMethodCall$3$PathProviderPlugin() {
        File externalFilesDir = this.context.getExternalFilesDir(null);
        if (externalFilesDir == null) {
            return null;
        }
        return externalFilesDir.getAbsolutePath();
    }

    /* access modifiers changed from: private */
    /* renamed from: getPathProviderExternalCacheDirectories */
    public List<String> lambda$onMethodCall$4$PathProviderPlugin() {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 19) {
            File[] externalCacheDirs = this.context.getExternalCacheDirs();
            for (File file : externalCacheDirs) {
                if (file != null) {
                    arrayList.add(file.getAbsolutePath());
                }
            }
        } else {
            File externalCacheDir = this.context.getExternalCacheDir();
            if (externalCacheDir != null) {
                arrayList.add(externalCacheDir.getAbsolutePath());
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    /* renamed from: getPathProviderExternalStorageDirectories */
    public List<String> lambda$onMethodCall$5$PathProviderPlugin(String str) {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 19) {
            File[] externalFilesDirs = this.context.getExternalFilesDirs(str);
            for (File file : externalFilesDirs) {
                if (file != null) {
                    arrayList.add(file.getAbsolutePath());
                }
            }
        } else {
            File externalFilesDir = this.context.getExternalFilesDir(str);
            if (externalFilesDir != null) {
                arrayList.add(externalFilesDir.getAbsolutePath());
            }
        }
        return arrayList;
    }

    private static class UiThreadExecutor implements Executor {
        private final Handler handler;

        private UiThreadExecutor() {
            this.handler = new Handler(Looper.getMainLooper());
        }

        /* synthetic */ UiThreadExecutor(AnonymousClass1 r1) {
            this();
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.handler.post(runnable);
        }
    }
}
