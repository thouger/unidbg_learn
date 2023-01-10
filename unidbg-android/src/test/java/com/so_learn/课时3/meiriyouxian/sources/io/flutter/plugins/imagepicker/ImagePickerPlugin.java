package io.flutter.plugins.imagepicker;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.embedding.engine.plugins.lifecycle.FlutterLifecycleAdapter;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import java.io.File;

public class ImagePickerPlugin implements FlutterPlugin, ActivityAware, MethodChannel.MethodCallHandler {
    private static final int CAMERA_DEVICE_FRONT = 1;
    private static final int CAMERA_DEVICE_REAR = 0;
    private static final String CHANNEL = "plugins.flutter.io/image_picker";
    static final String METHOD_CALL_IMAGE = "pickImage";
    private static final String METHOD_CALL_RETRIEVE = "retrieve";
    static final String METHOD_CALL_VIDEO = "pickVideo";
    private static final int SOURCE_CAMERA = 0;
    private static final int SOURCE_GALLERY = 1;
    private Activity activity;
    private ActivityPluginBinding activityBinding;
    private Application application;
    private MethodChannel channel;
    private ImagePickerDelegate delegate;
    private Lifecycle lifecycle;
    private LifeCycleObserver observer;
    private FlutterPlugin.FlutterPluginBinding pluginBinding;

    /* access modifiers changed from: private */
    public class LifeCycleObserver implements Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {
        private final Activity thisActivity;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onCreate(LifecycleOwner lifecycleOwner) {
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onPause(LifecycleOwner lifecycleOwner) {
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onResume(LifecycleOwner lifecycleOwner) {
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onStart(LifecycleOwner lifecycleOwner) {
        }

        LifeCycleObserver(Activity activity) {
            this.thisActivity = activity;
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onStop(LifecycleOwner lifecycleOwner) {
            onActivityStopped(this.thisActivity);
        }

        @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
        public void onDestroy(LifecycleOwner lifecycleOwner) {
            onActivityDestroyed(this.thisActivity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.thisActivity == activity && activity.getApplicationContext() != null) {
                ((Application) activity.getApplicationContext()).unregisterActivityLifecycleCallbacks(this);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (this.thisActivity == activity) {
                ImagePickerPlugin.this.delegate.saveStateBeforeResult();
            }
        }
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        if (registrar.activity() != null) {
            Activity activity = registrar.activity();
            Application application = null;
            if (registrar.context() != null) {
                application = (Application) registrar.context().getApplicationContext();
            }
            new ImagePickerPlugin().setup(registrar.messenger(), application, activity, registrar, null);
        }
    }

    public ImagePickerPlugin() {
    }

    ImagePickerPlugin(ImagePickerDelegate imagePickerDelegate, Activity activity) {
        this.delegate = imagePickerDelegate;
        this.activity = activity;
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.pluginBinding = flutterPluginBinding;
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.pluginBinding = null;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        this.activityBinding = activityPluginBinding;
        setup(this.pluginBinding.getBinaryMessenger(), (Application) this.pluginBinding.getApplicationContext(), this.activityBinding.getActivity(), null, this.activityBinding);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        tearDown();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        onAttachedToActivity(activityPluginBinding);
    }

    private void setup(BinaryMessenger binaryMessenger, Application application, Activity activity, PluginRegistry.Registrar registrar, ActivityPluginBinding activityPluginBinding) {
        this.activity = activity;
        this.application = application;
        this.delegate = constructDelegate(activity);
        this.channel = new MethodChannel(binaryMessenger, CHANNEL);
        this.channel.setMethodCallHandler(this);
        this.observer = new LifeCycleObserver(activity);
        if (registrar != null) {
            application.registerActivityLifecycleCallbacks(this.observer);
            registrar.addActivityResultListener(this.delegate);
            registrar.addRequestPermissionsResultListener(this.delegate);
            return;
        }
        activityPluginBinding.addActivityResultListener(this.delegate);
        activityPluginBinding.addRequestPermissionsResultListener(this.delegate);
        this.lifecycle = FlutterLifecycleAdapter.getActivityLifecycle(activityPluginBinding);
        this.lifecycle.addObserver(this.observer);
    }

    private void tearDown() {
        this.activityBinding.removeActivityResultListener(this.delegate);
        this.activityBinding.removeRequestPermissionsResultListener(this.delegate);
        this.activityBinding = null;
        this.lifecycle.removeObserver(this.observer);
        this.lifecycle = null;
        this.delegate = null;
        this.channel.setMethodCallHandler(null);
        this.channel = null;
        this.application.unregisterActivityLifecycleCallbacks(this.observer);
        this.application = null;
    }

    private final ImagePickerDelegate constructDelegate(Activity activity) {
        ImagePickerCache imagePickerCache = new ImagePickerCache(activity);
        File externalFilesDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new ImagePickerDelegate(activity, externalFilesDir, new ImageResizer(externalFilesDir, new ExifDataCopier()), imagePickerCache);
    }

    private static class MethodResultWrapper implements MethodChannel.Result {
        private Handler handler = new Handler(Looper.getMainLooper());
        private MethodChannel.Result methodResult;

        MethodResultWrapper(MethodChannel.Result result) {
            this.methodResult = result;
        }

        /* renamed from: io.flutter.plugins.imagepicker.ImagePickerPlugin$MethodResultWrapper$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ Object val$result;

            AnonymousClass1(Object obj) {
                this.val$result = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                MethodResultWrapper.this.methodResult.success(this.val$result);
            }
        }

        @Override // io.flutter.plugin.common.MethodChannel.Result
        public void success(Object obj) {
            this.handler.post(new AnonymousClass1(obj));
        }

        /* renamed from: io.flutter.plugins.imagepicker.ImagePickerPlugin$MethodResultWrapper$2  reason: invalid class name */
        class AnonymousClass2 implements Runnable {
            final /* synthetic */ String val$errorCode;
            final /* synthetic */ Object val$errorDetails;
            final /* synthetic */ String val$errorMessage;

            AnonymousClass2(String str, String str2, Object obj) {
                this.val$errorCode = str;
                this.val$errorMessage = str2;
                this.val$errorDetails = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                MethodResultWrapper.this.methodResult.error(this.val$errorCode, this.val$errorMessage, this.val$errorDetails);
            }
        }

        @Override // io.flutter.plugin.common.MethodChannel.Result
        public void error(String str, String str2, Object obj) {
            this.handler.post(new AnonymousClass2(str, str2, obj));
        }

        /* renamed from: io.flutter.plugins.imagepicker.ImagePickerPlugin$MethodResultWrapper$3  reason: invalid class name */
        class AnonymousClass3 implements Runnable {
            AnonymousClass3() {
            }

            @Override // java.lang.Runnable
            public void run() {
                MethodResultWrapper.this.methodResult.notImplemented();
            }
        }

        @Override // io.flutter.plugin.common.MethodChannel.Result
        public void notImplemented() {
            this.handler.post(new AnonymousClass3());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ca  */
    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMethodCall(io.flutter.plugin.common.MethodCall r7, io.flutter.plugin.common.MethodChannel.Result r8) {
        /*
        // Method dump skipped, instructions count: 252
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.imagepicker.ImagePickerPlugin.onMethodCall(io.flutter.plugin.common.MethodCall, io.flutter.plugin.common.MethodChannel$Result):void");
    }
}
