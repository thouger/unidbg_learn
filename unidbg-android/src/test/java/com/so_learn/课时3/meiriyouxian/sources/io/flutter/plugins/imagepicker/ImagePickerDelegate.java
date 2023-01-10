package io.flutter.plugins.imagepicker;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class ImagePickerDelegate implements PluginRegistry.ActivityResultListener, PluginRegistry.RequestPermissionsResultListener {
    static final int REQUEST_CAMERA_IMAGE_PERMISSION = 2345;
    static final int REQUEST_CAMERA_VIDEO_PERMISSION = 2355;
    static final int REQUEST_CODE_CHOOSE_IMAGE_FROM_GALLERY = 2342;
    static final int REQUEST_CODE_CHOOSE_VIDEO_FROM_GALLERY = 2352;
    static final int REQUEST_CODE_TAKE_IMAGE_WITH_CAMERA = 2343;
    static final int REQUEST_CODE_TAKE_VIDEO_WITH_CAMERA = 2353;
    static final int REQUEST_EXTERNAL_IMAGE_STORAGE_PERMISSION = 2344;
    static final int REQUEST_EXTERNAL_VIDEO_STORAGE_PERMISSION = 2354;
    private final Activity activity;
    private final ImagePickerCache cache;
    private CameraDevice cameraDevice;
    private final File externalFilesDirectory;
    final String fileProviderName;
    private final FileUriResolver fileUriResolver;
    private final FileUtils fileUtils;
    private final ImageResizer imageResizer;
    private final IntentResolver intentResolver;
    private MethodCall methodCall;
    private Uri pendingCameraMediaUri;
    private MethodChannel.Result pendingResult;
    private final PermissionManager permissionManager;

    /* access modifiers changed from: package-private */
    public interface FileUriResolver {
        void getFullImagePath(Uri uri, OnPathReadyListener onPathReadyListener);

        Uri resolveFileProviderUriForFile(String str, File file);
    }

    /* access modifiers changed from: package-private */
    public interface IntentResolver {
        boolean resolveActivity(Intent intent);
    }

    /* access modifiers changed from: package-private */
    public interface OnPathReadyListener {
        void onPathReady(String str);
    }

    /* access modifiers changed from: package-private */
    public interface PermissionManager {
        void askForPermission(String str, int i);

        boolean isPermissionGranted(String str);

        boolean needRequestCameraPermission();
    }

    public ImagePickerDelegate(Activity activity, File file, ImageResizer imageResizer, ImagePickerCache imagePickerCache) {
        this(activity, file, imageResizer, null, null, imagePickerCache, new AnonymousClass1(activity), new AnonymousClass2(activity), new AnonymousClass3(activity), new FileUtils());
    }

    /* renamed from: io.flutter.plugins.imagepicker.ImagePickerDelegate$1  reason: invalid class name */
    class AnonymousClass1 implements PermissionManager {
        final /* synthetic */ Activity val$activity;

        AnonymousClass1(Activity activity) {
            this.val$activity = activity;
        }

        @Override // io.flutter.plugins.imagepicker.ImagePickerDelegate.PermissionManager
        public boolean isPermissionGranted(String str) {
            return ActivityCompat.checkSelfPermission(this.val$activity, str) == 0;
        }

        @Override // io.flutter.plugins.imagepicker.ImagePickerDelegate.PermissionManager
        public void askForPermission(String str, int i) {
            ActivityCompat.requestPermissions(this.val$activity, new String[]{str}, i);
        }

        @Override // io.flutter.plugins.imagepicker.ImagePickerDelegate.PermissionManager
        public boolean needRequestCameraPermission() {
            return ImagePickerUtils.needRequestCameraPermission(this.val$activity);
        }
    }

    /* renamed from: io.flutter.plugins.imagepicker.ImagePickerDelegate$2  reason: invalid class name */
    class AnonymousClass2 implements IntentResolver {
        final /* synthetic */ Activity val$activity;

        AnonymousClass2(Activity activity) {
            this.val$activity = activity;
        }

        @Override // io.flutter.plugins.imagepicker.ImagePickerDelegate.IntentResolver
        public boolean resolveActivity(Intent intent) {
            return intent.resolveActivity(this.val$activity.getPackageManager()) != null;
        }
    }

    /* renamed from: io.flutter.plugins.imagepicker.ImagePickerDelegate$3  reason: invalid class name */
    class AnonymousClass3 implements FileUriResolver {
        final /* synthetic */ Activity val$activity;

        AnonymousClass3(Activity activity) {
            this.val$activity = activity;
        }

        @Override // io.flutter.plugins.imagepicker.ImagePickerDelegate.FileUriResolver
        public Uri resolveFileProviderUriForFile(String str, File file) {
            return FileProvider.getUriForFile(this.val$activity, str, file);
        }

        /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0010: APUT  (r1v1 java.lang.String[]), (0 ??[int, short, byte, char]), (r4v1 java.lang.String) */
        @Override // io.flutter.plugins.imagepicker.ImagePickerDelegate.FileUriResolver
        public void getFullImagePath(Uri uri, OnPathReadyListener onPathReadyListener) {
            Activity activity = this.val$activity;
            String[] strArr = new String[1];
            strArr[0] = uri != null ? uri.getPath() : "";
            MediaScannerConnection.scanFile(activity, strArr, null, new AnonymousClass1(onPathReadyListener));
        }

        /* renamed from: io.flutter.plugins.imagepicker.ImagePickerDelegate$3$1  reason: invalid class name */
        class AnonymousClass1 implements MediaScannerConnection.OnScanCompletedListener {
            final /* synthetic */ OnPathReadyListener val$listener;

            AnonymousClass1(OnPathReadyListener onPathReadyListener) {
                this.val$listener = onPathReadyListener;
            }

            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
            public void onScanCompleted(String str, Uri uri) {
                this.val$listener.onPathReady(str);
            }
        }
    }

    ImagePickerDelegate(Activity activity, File file, ImageResizer imageResizer, MethodChannel.Result result, MethodCall methodCall, ImagePickerCache imagePickerCache, PermissionManager permissionManager, IntentResolver intentResolver, FileUriResolver fileUriResolver, FileUtils fileUtils) {
        this.activity = activity;
        this.externalFilesDirectory = file;
        this.imageResizer = imageResizer;
        this.fileProviderName = activity.getPackageName() + ".flutter.image_provider";
        this.pendingResult = result;
        this.methodCall = methodCall;
        this.permissionManager = permissionManager;
        this.intentResolver = intentResolver;
        this.fileUriResolver = fileUriResolver;
        this.fileUtils = fileUtils;
        this.cache = imagePickerCache;
    }

    /* access modifiers changed from: package-private */
    public void setCameraDevice(CameraDevice cameraDevice) {
        this.cameraDevice = cameraDevice;
    }

    /* access modifiers changed from: package-private */
    public CameraDevice getCameraDevice() {
        return this.cameraDevice;
    }

    /* access modifiers changed from: package-private */
    public void saveStateBeforeResult() {
        MethodCall methodCall = this.methodCall;
        if (methodCall != null) {
            this.cache.saveTypeWithMethodCallName(methodCall.method);
            this.cache.saveDimensionWithMethodCall(this.methodCall);
            Uri uri = this.pendingCameraMediaUri;
            if (uri != null) {
                this.cache.savePendingCameraMediaUriPath(uri);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void retrieveLostImage(MethodChannel.Result result) {
        int i;
        Map<String, Object> cacheMap = this.cache.getCacheMap();
        ImagePickerCache imagePickerCache = this.cache;
        String str = (String) cacheMap.get("path");
        if (str != null) {
            ImagePickerCache imagePickerCache2 = this.cache;
            Double d = (Double) cacheMap.get("maxWidth");
            ImagePickerCache imagePickerCache3 = this.cache;
            Double d2 = (Double) cacheMap.get("maxHeight");
            ImagePickerCache imagePickerCache4 = this.cache;
            if (cacheMap.get("imageQuality") == null) {
                i = 100;
            } else {
                ImagePickerCache imagePickerCache5 = this.cache;
                i = ((Integer) cacheMap.get("imageQuality")).intValue();
            }
            String resizeImageIfNeeded = this.imageResizer.resizeImageIfNeeded(str, d, d2, Integer.valueOf(i));
            ImagePickerCache imagePickerCache6 = this.cache;
            cacheMap.put("path", resizeImageIfNeeded);
        }
        if (cacheMap.isEmpty()) {
            result.success(null);
        } else {
            result.success(cacheMap);
        }
        this.cache.clear();
    }

    public void chooseVideoFromGallery(MethodCall methodCall, MethodChannel.Result result) {
        if (!setPendingMethodCallAndResult(methodCall, result)) {
            finishWithAlreadyActiveError(result);
        } else if (!this.permissionManager.isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            this.permissionManager.askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_EXTERNAL_VIDEO_STORAGE_PERMISSION);
        } else {
            launchPickVideoFromGalleryIntent();
        }
    }

    private void launchPickVideoFromGalleryIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        this.activity.startActivityForResult(intent, REQUEST_CODE_CHOOSE_VIDEO_FROM_GALLERY);
    }

    public void takeVideoWithCamera(MethodCall methodCall, MethodChannel.Result result) {
        if (!setPendingMethodCallAndResult(methodCall, result)) {
            finishWithAlreadyActiveError(result);
        } else if (!needRequestCameraPermission() || this.permissionManager.isPermissionGranted(Manifest.permission.CAMERA)) {
            launchTakeVideoWithCameraIntent();
        } else {
            this.permissionManager.askForPermission(Manifest.permission.CAMERA, REQUEST_CAMERA_VIDEO_PERMISSION);
        }
    }

    private void launchTakeVideoWithCameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        MethodCall methodCall = this.methodCall;
        if (!(methodCall == null || methodCall.argument("maxDuration") == null)) {
            intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, ((Integer) this.methodCall.argument("maxDuration")).intValue());
        }
        if (this.cameraDevice == CameraDevice.FRONT) {
            useFrontCamera(intent);
        }
        if (!this.intentResolver.resolveActivity(intent)) {
            finishWithError("no_available_camera", "No cameras available for taking pictures.");
            return;
        }
        File createTemporaryWritableVideoFile = createTemporaryWritableVideoFile();
        this.pendingCameraMediaUri = Uri.parse("file:" + createTemporaryWritableVideoFile.getAbsolutePath());
        Uri resolveFileProviderUriForFile = this.fileUriResolver.resolveFileProviderUriForFile(this.fileProviderName, createTemporaryWritableVideoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, resolveFileProviderUriForFile);
        grantUriPermissions(intent, resolveFileProviderUriForFile);
        this.activity.startActivityForResult(intent, REQUEST_CODE_TAKE_VIDEO_WITH_CAMERA);
    }

    public void chooseImageFromGallery(MethodCall methodCall, MethodChannel.Result result) {
        if (!setPendingMethodCallAndResult(methodCall, result)) {
            finishWithAlreadyActiveError(result);
        } else if (!this.permissionManager.isPermissionGranted(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            this.permissionManager.askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, REQUEST_EXTERNAL_IMAGE_STORAGE_PERMISSION);
        } else {
            launchPickImageFromGalleryIntent();
        }
    }

    private void launchPickImageFromGalleryIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        this.activity.startActivityForResult(intent, REQUEST_CODE_CHOOSE_IMAGE_FROM_GALLERY);
    }

    public void takeImageWithCamera(MethodCall methodCall, MethodChannel.Result result) {
        if (!setPendingMethodCallAndResult(methodCall, result)) {
            finishWithAlreadyActiveError(result);
        } else if (!needRequestCameraPermission() || this.permissionManager.isPermissionGranted(Manifest.permission.CAMERA)) {
            launchTakeImageWithCameraIntent();
        } else {
            this.permissionManager.askForPermission(Manifest.permission.CAMERA, REQUEST_CAMERA_IMAGE_PERMISSION);
        }
    }

    private boolean needRequestCameraPermission() {
        PermissionManager permissionManager = this.permissionManager;
        if (permissionManager == null) {
            return false;
        }
        return permissionManager.needRequestCameraPermission();
    }

    private void launchTakeImageWithCameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (this.cameraDevice == CameraDevice.FRONT) {
            useFrontCamera(intent);
        }
        if (!this.intentResolver.resolveActivity(intent)) {
            finishWithError("no_available_camera", "No cameras available for taking pictures.");
            return;
        }
        File createTemporaryWritableImageFile = createTemporaryWritableImageFile();
        this.pendingCameraMediaUri = Uri.parse("file:" + createTemporaryWritableImageFile.getAbsolutePath());
        Uri resolveFileProviderUriForFile = this.fileUriResolver.resolveFileProviderUriForFile(this.fileProviderName, createTemporaryWritableImageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, resolveFileProviderUriForFile);
        grantUriPermissions(intent, resolveFileProviderUriForFile);
        this.activity.startActivityForResult(intent, REQUEST_CODE_TAKE_IMAGE_WITH_CAMERA);
    }

    private File createTemporaryWritableImageFile() {
        return createTemporaryWritableFile(".jpg");
    }

    private File createTemporaryWritableVideoFile() {
        return createTemporaryWritableFile(".mp4");
    }

    private File createTemporaryWritableFile(String str) {
        try {
            return File.createTempFile(UUID.randomUUID().toString(), str, this.externalFilesDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void grantUriPermissions(Intent intent, Uri uri) {
        for (ResolveInfo resolveInfo : this.activity.getPackageManager().queryIntentActivities(intent, 65536)) {
            this.activity.grantUriPermission(resolveInfo.activityInfo.packageName, uri, 3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003c, code lost:
        if (r6 != io.flutter.plugins.imagepicker.ImagePickerDelegate.REQUEST_CAMERA_VIDEO_PERMISSION) goto L_0x0052;
     */
    @Override // io.flutter.plugin.common.PluginRegistry.RequestPermissionsResultListener
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onRequestPermissionsResult(int r6, java.lang.String[] r7, int[] r8) {
        /*
            r5 = this;
            int r7 = r8.length
            r0 = 1
            r1 = 0
            if (r7 <= 0) goto L_0x000b
            r7 = r8[r1]
            if (r7 != 0) goto L_0x000b
            r7 = r0
            goto L_0x000c
        L_0x000b:
            r7 = r1
        L_0x000c:
            r8 = 2355(0x933, float:3.3E-42)
            r2 = 2354(0x932, float:3.299E-42)
            r3 = 2345(0x929, float:3.286E-42)
            r4 = 2344(0x928, float:3.285E-42)
            if (r6 == r4) goto L_0x002f
            if (r6 == r3) goto L_0x0029
            if (r6 == r2) goto L_0x0023
            if (r6 == r8) goto L_0x001d
            return r1
        L_0x001d:
            if (r7 == 0) goto L_0x0034
            r5.launchTakeVideoWithCameraIntent()
            goto L_0x0034
        L_0x0023:
            if (r7 == 0) goto L_0x0034
            r5.launchPickVideoFromGalleryIntent()
            goto L_0x0034
        L_0x0029:
            if (r7 == 0) goto L_0x0034
            r5.launchTakeImageWithCameraIntent()
            goto L_0x0034
        L_0x002f:
            if (r7 == 0) goto L_0x0034
            r5.launchPickImageFromGalleryIntent()
        L_0x0034:
            if (r7 != 0) goto L_0x0052
            if (r6 == r4) goto L_0x0049
            if (r6 == r3) goto L_0x003f
            if (r6 == r2) goto L_0x0049
            if (r6 == r8) goto L_0x003f
            goto L_0x0052
        L_0x003f:
            java.lang.String r6 = "camera_access_denied"
            java.lang.String r7 = "The user did not allow camera access."
            r5.finishWithError(r6, r7)
            goto L_0x0052
        L_0x0049:
            java.lang.String r6 = "photo_access_denied"
            java.lang.String r7 = "The user did not allow photo access."
            r5.finishWithError(r6, r7)
        L_0x0052:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.imagepicker.ImagePickerDelegate.onRequestPermissionsResult(int, java.lang.String[], int[]):boolean");
    }

    @Override // io.flutter.plugin.common.PluginRegistry.ActivityResultListener
    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (i == REQUEST_CODE_CHOOSE_IMAGE_FROM_GALLERY) {
            handleChooseImageResult(i2, intent);
            return true;
        } else if (i == REQUEST_CODE_TAKE_IMAGE_WITH_CAMERA) {
            handleCaptureImageResult(i2);
            return true;
        } else if (i == REQUEST_CODE_CHOOSE_VIDEO_FROM_GALLERY) {
            handleChooseVideoResult(i2, intent);
            return true;
        } else if (i != REQUEST_CODE_TAKE_VIDEO_WITH_CAMERA) {
            return false;
        } else {
            handleCaptureVideoResult(i2);
            return true;
        }
    }

    private void handleChooseImageResult(int i, Intent intent) {
        if (i != -1 || intent == null) {
            finishWithSuccess(null);
        } else {
            handleImageResult(this.fileUtils.getPathFromUri(this.activity, intent.getData()), false);
        }
    }

    private void handleChooseVideoResult(int i, Intent intent) {
        if (i != -1 || intent == null) {
            finishWithSuccess(null);
        } else {
            handleVideoResult(this.fileUtils.getPathFromUri(this.activity, intent.getData()));
        }
    }

    private void handleCaptureImageResult(int i) {
        if (i == -1) {
            FileUriResolver fileUriResolver = this.fileUriResolver;
            Uri uri = this.pendingCameraMediaUri;
            if (uri == null) {
                uri = Uri.parse(this.cache.retrievePendingCameraMediaUriPath());
            }
            fileUriResolver.getFullImagePath(uri, new AnonymousClass4());
            return;
        }
        finishWithSuccess(null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: io.flutter.plugins.imagepicker.ImagePickerDelegate$4  reason: invalid class name */
    public class AnonymousClass4 implements OnPathReadyListener {
        AnonymousClass4() {
        }

        @Override // io.flutter.plugins.imagepicker.ImagePickerDelegate.OnPathReadyListener
        public void onPathReady(String str) {
            ImagePickerDelegate.this.handleImageResult(str, true);
        }
    }

    private void handleCaptureVideoResult(int i) {
        if (i == -1) {
            FileUriResolver fileUriResolver = this.fileUriResolver;
            Uri uri = this.pendingCameraMediaUri;
            if (uri == null) {
                uri = Uri.parse(this.cache.retrievePendingCameraMediaUriPath());
            }
            fileUriResolver.getFullImagePath(uri, new AnonymousClass5());
            return;
        }
        finishWithSuccess(null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: io.flutter.plugins.imagepicker.ImagePickerDelegate$5  reason: invalid class name */
    public class AnonymousClass5 implements OnPathReadyListener {
        AnonymousClass5() {
        }

        @Override // io.flutter.plugins.imagepicker.ImagePickerDelegate.OnPathReadyListener
        public void onPathReady(String str) {
            ImagePickerDelegate.this.handleVideoResult(str);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handleImageResult(String str, boolean z) {
        MethodCall methodCall = this.methodCall;
        if (methodCall != null) {
            String resizeImageIfNeeded = this.imageResizer.resizeImageIfNeeded(str, (Double) methodCall.argument("maxWidth"), (Double) this.methodCall.argument("maxHeight"), (Integer) this.methodCall.argument("imageQuality"));
            finishWithSuccess(resizeImageIfNeeded);
            if (resizeImageIfNeeded != null && !resizeImageIfNeeded.equals(str) && z) {
                new File(str).delete();
                return;
            }
            return;
        }
        finishWithSuccess(str);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handleVideoResult(String str) {
        finishWithSuccess(str);
    }

    private boolean setPendingMethodCallAndResult(MethodCall methodCall, MethodChannel.Result result) {
        if (this.pendingResult != null) {
            return false;
        }
        this.methodCall = methodCall;
        this.pendingResult = result;
        this.cache.clear();
        return true;
    }

    private void finishWithSuccess(String str) {
        MethodChannel.Result result = this.pendingResult;
        if (result == null) {
            this.cache.saveResult(str, null, null);
            return;
        }
        result.success(str);
        clearMethodCallAndResult();
    }

    private void finishWithAlreadyActiveError(MethodChannel.Result result) {
        result.error("already_active", "Image picker is already active", null);
    }

    private void finishWithError(String str, String str2) {
        MethodChannel.Result result = this.pendingResult;
        if (result == null) {
            this.cache.saveResult(null, str, str2);
            return;
        }
        result.error(str, str2, null);
        clearMethodCallAndResult();
    }

    private void clearMethodCallAndResult() {
        this.methodCall = null;
        this.pendingResult = null;
    }

    private void useFrontCamera(Intent intent) {
        if (Build.VERSION.SDK_INT >= 22) {
            intent.putExtra("android.intent.extras.CAMERA_FACING", 0);
            if (Build.VERSION.SDK_INT >= 26) {
                intent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
                return;
            }
            return;
        }
        intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
    }
}
