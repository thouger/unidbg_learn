package io.flutter.embedding.engine.loader;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.WindowManager;
import com.taobao.accs.common.Constants;
import io.flutter.Log;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.util.PathUtils;
import io.flutter.view.VsyncWaiter;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FlutterLoader {
    static final String AOT_SHARED_LIBRARY_NAME = "aot-shared-library-name";
    static final String AUTOMATICALLY_REGISTER_PLUGINS_KEY = "automatically-register-plugins";
    private static final String DEFAULT_KERNEL_BLOB = "kernel_blob.bin";
    private static final String DEFAULT_LIBRARY = "libflutter.so";
    private static final String ENABLE_SKPARAGRAPH_META_DATA_KEY = "io.flutter.embedding.android.EnableSkParagraph";
    static final String FLUTTER_ASSETS_DIR_KEY = "flutter-assets-dir";
    static final String ISOLATE_SNAPSHOT_DATA_KEY = "isolate-snapshot-data";
    private static final String OLD_GEN_HEAP_SIZE_META_DATA_KEY = "io.flutter.embedding.android.OldGenHeapSize";
    static final String SNAPSHOT_ASSET_PATH_KEY = "snapshot-asset-path";
    private static final String TAG = "FlutterLoader";
    static final String VM_SNAPSHOT_DATA_KEY = "vm-snapshot-data";
    private static FlutterLoader instance;
    private FlutterApplicationInfo flutterApplicationInfo;
    private FlutterJNI flutterJNI;
    Future<InitResult> initResultFuture;
    private long initStartTimestampMillis;
    private boolean initialized;
    private Settings settings;

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private ResourceExtractor initResources(Context context) {
        return null;
    }

    @Deprecated
    public static FlutterLoader getInstance() {
        if (instance == null) {
            instance = new FlutterLoader();
        }
        return instance;
    }

    public FlutterLoader() {
        this(new FlutterJNI());
    }

    public FlutterLoader(FlutterJNI flutterJNI) {
        this.initialized = false;
        this.flutterJNI = flutterJNI;
    }

    /* access modifiers changed from: private */
    public static class InitResult {
        final String appStoragePath;
        final String dataDirPath;
        final String engineCachesPath;

        /* synthetic */ InitResult(String str, String str2, String str3, AnonymousClass1 r4) {
            this(str, str2, str3);
        }

        private InitResult(String str, String str2, String str3) {
            this.appStoragePath = str;
            this.engineCachesPath = str2;
            this.dataDirPath = str3;
        }
    }

    public void startInitialization(Context context) {
        startInitialization(context, new Settings());
    }

    public void startInitialization(Context context, Settings settings) {
        if (this.settings == null) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Context applicationContext = context.getApplicationContext();
                this.settings = settings;
                this.initStartTimestampMillis = SystemClock.uptimeMillis();
                this.flutterApplicationInfo = ApplicationInfoLoader.load(applicationContext);
                VsyncWaiter.getInstance((WindowManager) applicationContext.getSystemService(Context.WINDOW_SERVICE)).init();
                this.initResultFuture = Executors.newSingleThreadExecutor().submit(new AnonymousClass1(applicationContext));
                return;
            }
            throw new IllegalStateException("startInitialization must be called on the main thread");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: io.flutter.embedding.engine.loader.FlutterLoader$1  reason: invalid class name */
    public class AnonymousClass1 implements Callable<InitResult> {
        final /* synthetic */ Context val$appContext;

        AnonymousClass1(Context context) {
            this.val$appContext = context;
        }

        @Override // java.util.concurrent.Callable
        public InitResult call() {
            ResourceExtractor initResources = FlutterLoader.this.initResources(this.val$appContext);
            FlutterLoader.this.flutterJNI.loadLibrary();
            Executors.newSingleThreadExecutor().execute(new AnonymousClass1());
            if (initResources != null) {
                initResources.waitForCompletion();
            }
            return new InitResult(PathUtils.getFilesDir(this.val$appContext), PathUtils.getCacheDirectory(this.val$appContext), PathUtils.getDataDirectory(this.val$appContext), null);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: io.flutter.embedding.engine.loader.FlutterLoader$1$1  reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                FlutterLoader.this.flutterJNI.prefetchDefaultFontManager();
            }
        }
    }

    public void ensureInitializationComplete(Context context, String[] strArr) {
        if (!this.initialized) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw new IllegalStateException("ensureInitializationComplete must be called on the main thread");
            } else if (this.settings != null) {
                try {
                    InitResult initResult = this.initResultFuture.get();
                    ArrayList arrayList = new ArrayList();
                    arrayList.add("--icu-symbol-prefix=_binary_icudtl_dat");
                    arrayList.add("--icu-native-lib-path=" + this.flutterApplicationInfo.nativeLibraryDir + File.separator + DEFAULT_LIBRARY);
                    if (strArr != null) {
                        Collections.addAll(arrayList, strArr);
                    }
                    arrayList.add("--aot-shared-library-name=" + this.flutterApplicationInfo.aotSharedLibraryName);
                    arrayList.add("--aot-shared-library-name=" + this.flutterApplicationInfo.nativeLibraryDir + File.separator + this.flutterApplicationInfo.aotSharedLibraryName);
                    StringBuilder sb = new StringBuilder();
                    sb.append("--cache-dir-path=");
                    sb.append(initResult.engineCachesPath);
                    arrayList.add(sb.toString());
                    if (this.flutterApplicationInfo.domainNetworkPolicy != null) {
                        arrayList.add("--domain-network-policy=" + this.flutterApplicationInfo.domainNetworkPolicy);
                    }
                    if (this.settings.getLogTag() != null) {
                        arrayList.add("--log-tag=" + this.settings.getLogTag());
                    }
                    Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
                    int i = bundle != null ? bundle.getInt(OLD_GEN_HEAP_SIZE_META_DATA_KEY) : 0;
                    if (i == 0) {
                        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
                        i = (int) ((((double) memoryInfo.totalMem) / 1000000.0d) / 2.0d);
                    }
                    arrayList.add("--old-gen-heap-size=" + i);
                    if (bundle != null && bundle.getBoolean(ENABLE_SKPARAGRAPH_META_DATA_KEY)) {
                        arrayList.add("--enable-skparagraph");
                    }
                    this.flutterJNI.init(context, (String[]) arrayList.toArray(new String[0]), null, initResult.appStoragePath, initResult.engineCachesPath, SystemClock.uptimeMillis() - this.initStartTimestampMillis);
                    this.initialized = true;
                } catch (Exception e) {
                    Log.e(TAG, "Flutter initialization failed.", e);
                    throw new RuntimeException(e);
                }
            } else {
                throw new IllegalStateException("ensureInitializationComplete must be called after startInitialization");
            }
        }
    }

    public void ensureInitializationCompleteAsync(Context context, String[] strArr, Handler handler, Runnable runnable) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ensureInitializationComplete must be called on the main thread");
        } else if (this.settings == null) {
            throw new IllegalStateException("ensureInitializationComplete must be called after startInitialization");
        } else if (this.initialized) {
            handler.post(runnable);
        } else {
            Executors.newSingleThreadExecutor().execute(new AnonymousClass2(context, strArr, handler, runnable));
        }
    }

    /* renamed from: io.flutter.embedding.engine.loader.FlutterLoader$2  reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ Context val$applicationContext;
        final /* synthetic */ String[] val$args;
        final /* synthetic */ Runnable val$callback;
        final /* synthetic */ Handler val$callbackHandler;

        AnonymousClass2(Context context, String[] strArr, Handler handler, Runnable runnable) {
            this.val$applicationContext = context;
            this.val$args = strArr;
            this.val$callbackHandler = handler;
            this.val$callback = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                FlutterLoader.this.initResultFuture.get();
                new Handler(Looper.getMainLooper()).post(new AnonymousClass1());
            } catch (Exception e) {
                Log.e(FlutterLoader.TAG, "Flutter initialization failed.", e);
                throw new RuntimeException(e);
            }
        }

        /* renamed from: io.flutter.embedding.engine.loader.FlutterLoader$2$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                FlutterLoader.this.ensureInitializationComplete(AnonymousClass2.this.val$applicationContext.getApplicationContext(), AnonymousClass2.this.val$args);
                AnonymousClass2.this.val$callbackHandler.post(AnonymousClass2.this.val$callback);
            }
        }
    }

    public boolean initialized() {
        return this.initialized;
    }

    public String findAppBundlePath() {
        return this.flutterApplicationInfo.flutterAssetsDir;
    }

    public String getLookupKeyForAsset(String str) {
        return fullAssetPathFrom(str);
    }

    public String getLookupKeyForAsset(String str, String str2) {
        return getLookupKeyForAsset(Constants.KEY_PACKAGES + File.separator + str2 + File.separator + str);
    }

    public boolean automaticallyRegisterPlugins() {
        return this.flutterApplicationInfo.automaticallyRegisterPlugins;
    }

    private String fullAssetPathFrom(String str) {
        return this.flutterApplicationInfo.flutterAssetsDir + File.separator + str;
    }

    public static class Settings {
        private String logTag;

        public String getLogTag() {
            return this.logTag;
        }

        public void setLogTag(String str) {
            this.logTag = str;
        }
    }
}
