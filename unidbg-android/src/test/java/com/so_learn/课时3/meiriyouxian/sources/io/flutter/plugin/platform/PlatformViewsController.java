package io.flutter.plugin.platform;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.umeng.message.proguard.l;
import io.flutter.Log;
import io.flutter.embedding.android.AndroidTouchProcessor;
import io.flutter.embedding.android.FlutterImageView;
import io.flutter.embedding.android.FlutterView;
import io.flutter.embedding.android.MotionEventTracker;
import io.flutter.embedding.engine.FlutterOverlaySurface;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.embedding.engine.mutatorsstack.FlutterMutatorView;
import io.flutter.embedding.engine.mutatorsstack.FlutterMutatorsStack;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
import io.flutter.embedding.engine.systemchannels.PlatformViewsChannel;
import io.flutter.plugin.editing.TextInputPlugin;
import io.flutter.view.AccessibilityBridge;
import io.flutter.view.TextureRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PlatformViewsController implements PlatformViewsAccessibilityDelegate {
    private static final String TAG = "PlatformViewsController";
    private final AccessibilityEventsDelegate accessibilityEventsDelegate = new AccessibilityEventsDelegate();
    private AndroidTouchProcessor androidTouchProcessor;
    private final PlatformViewsChannel.PlatformViewsHandler channelHandler = new AnonymousClass1();
    private Context context;
    private final HashMap<Context, View> contextToPlatformView = new HashMap<>();
    private HashSet<Integer> currentFrameUsedOverlayLayerIds = new HashSet<>();
    private HashSet<Integer> currentFrameUsedPlatformViewIds = new HashSet<>();
    private View flutterView;
    private boolean flutterViewConvertedToImageView = false;
    private final MotionEventTracker motionEventTracker = MotionEventTracker.getInstance();
    private int nextOverlayLayerId = 0;
    private final SparseArray<FlutterImageView> overlayLayerViews = new SparseArray<>();
    private final SparseArray<FlutterMutatorView> platformViewParent = new SparseArray<>();
    private final SparseArray<PlatformView> platformViews = new SparseArray<>();
    private PlatformViewsChannel platformViewsChannel;
    private final PlatformViewRegistryImpl registry = new PlatformViewRegistryImpl();
    private TextInputPlugin textInputPlugin;
    private TextureRegistry textureRegistry;
    final HashMap<Integer, VirtualDisplayController> vdControllers = new HashMap<>();

    /* access modifiers changed from: private */
    public static boolean validateDirection(int i) {
        return i == 0 || i == 1;
    }

    public void onAttachedToJNI() {
    }

    /* renamed from: io.flutter.plugin.platform.PlatformViewsController$1  reason: invalid class name */
    class AnonymousClass1 implements PlatformViewsChannel.PlatformViewsHandler {
        AnonymousClass1() {
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public void createAndroidViewForPlatformView(PlatformViewsChannel.PlatformViewCreationRequest platformViewCreationRequest) {
            ensureValidAndroidVersion(19);
            if (PlatformViewsController.validateDirection(platformViewCreationRequest.direction)) {
                PlatformViewFactory factory = PlatformViewsController.this.registry.getFactory(platformViewCreationRequest.viewType);
                if (factory != null) {
                    Object obj = null;
                    if (platformViewCreationRequest.params != null) {
                        obj = factory.getCreateArgsCodec().decodeMessage(platformViewCreationRequest.params);
                    }
                    PlatformViewsController.this.platformViews.put(platformViewCreationRequest.viewId, factory.create(PlatformViewsController.this.context, platformViewCreationRequest.viewId, obj));
                    return;
                }
                throw new IllegalStateException("Trying to create a platform view of unregistered type: " + platformViewCreationRequest.viewType);
            }
            throw new IllegalStateException("Trying to create a view with unknown direction value: " + platformViewCreationRequest.direction + "(view id: " + platformViewCreationRequest.viewId + l.t);
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public void disposeAndroidViewForPlatformView(int i) {
            PlatformView platformView = (PlatformView) PlatformViewsController.this.platformViews.get(i);
            FlutterMutatorView flutterMutatorView = (FlutterMutatorView) PlatformViewsController.this.platformViewParent.get(i);
            if (platformView != null) {
                if (flutterMutatorView != null) {
                    flutterMutatorView.removeView(platformView.getView());
                }
                PlatformViewsController.this.platformViews.remove(i);
                platformView.dispose();
            }
            if (flutterMutatorView != null) {
                ((ViewGroup) flutterMutatorView.getParent()).removeView(flutterMutatorView);
                PlatformViewsController.this.platformViewParent.remove(i);
            }
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public long createVirtualDisplayForPlatformView(PlatformViewsChannel.PlatformViewCreationRequest platformViewCreationRequest) {
            ensureValidAndroidVersion(20);
            if (!PlatformViewsController.validateDirection(platformViewCreationRequest.direction)) {
                throw new IllegalStateException("Trying to create a view with unknown direction value: " + platformViewCreationRequest.direction + "(view id: " + platformViewCreationRequest.viewId + l.t);
            } else if (!PlatformViewsController.this.vdControllers.containsKey(Integer.valueOf(platformViewCreationRequest.viewId))) {
                PlatformViewFactory factory = PlatformViewsController.this.registry.getFactory(platformViewCreationRequest.viewType);
                if (factory != null) {
                    Object obj = null;
                    if (platformViewCreationRequest.params != null) {
                        obj = factory.getCreateArgsCodec().decodeMessage(platformViewCreationRequest.params);
                    }
                    int physicalPixels = PlatformViewsController.this.toPhysicalPixels(platformViewCreationRequest.logicalWidth);
                    int physicalPixels2 = PlatformViewsController.this.toPhysicalPixels(platformViewCreationRequest.logicalHeight);
                    PlatformViewsController.this.validateVirtualDisplayDimensions(physicalPixels, physicalPixels2);
                    TextureRegistry.SurfaceTextureEntry createSurfaceTexture = PlatformViewsController.this.textureRegistry.createSurfaceTexture();
                    VirtualDisplayController create = VirtualDisplayController.create(PlatformViewsController.this.context, PlatformViewsController.this.accessibilityEventsDelegate, factory, createSurfaceTexture, physicalPixels, physicalPixels2, platformViewCreationRequest.viewId, obj, new $$Lambda$PlatformViewsController$1$i4ojtb0pOVFKsQcwzERs83K3E8M(this, platformViewCreationRequest));
                    if (create != null) {
                        if (PlatformViewsController.this.flutterView != null) {
                            create.onFlutterViewAttached(PlatformViewsController.this.flutterView);
                        }
                        PlatformViewsController.this.vdControllers.put(Integer.valueOf(platformViewCreationRequest.viewId), create);
                        View view = create.getView();
                        view.setLayoutDirection(platformViewCreationRequest.direction);
                        PlatformViewsController.this.contextToPlatformView.put(view.getContext(), view);
                        return createSurfaceTexture.id();
                    }
                    throw new IllegalStateException("Failed creating virtual display for a " + platformViewCreationRequest.viewType + " with id: " + platformViewCreationRequest.viewId);
                }
                throw new IllegalStateException("Trying to create a platform view of unregistered type: " + platformViewCreationRequest.viewType);
            } else {
                throw new IllegalStateException("Trying to create an already created platform view, view id: " + platformViewCreationRequest.viewId);
            }
        }

        public /* synthetic */ void lambda$createVirtualDisplayForPlatformView$0$PlatformViewsController$1(PlatformViewsChannel.PlatformViewCreationRequest platformViewCreationRequest, View view, boolean z) {
            if (z) {
                PlatformViewsController.this.platformViewsChannel.invokeViewFocused(platformViewCreationRequest.viewId);
            }
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public void disposeVirtualDisplayForPlatformView(int i) {
            ensureValidAndroidVersion(20);
            VirtualDisplayController virtualDisplayController = PlatformViewsController.this.vdControllers.get(Integer.valueOf(i));
            if (virtualDisplayController != null) {
                if (PlatformViewsController.this.textInputPlugin != null) {
                    PlatformViewsController.this.textInputPlugin.clearPlatformViewClient(i);
                }
                PlatformViewsController.this.contextToPlatformView.remove(virtualDisplayController.getView().getContext());
                virtualDisplayController.dispose();
                PlatformViewsController.this.vdControllers.remove(Integer.valueOf(i));
                return;
            }
            throw new IllegalStateException("Trying to dispose a platform view with unknown id: " + i);
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public void resizePlatformView(PlatformViewsChannel.PlatformViewResizeRequest platformViewResizeRequest, Runnable runnable) {
            ensureValidAndroidVersion(20);
            VirtualDisplayController virtualDisplayController = PlatformViewsController.this.vdControllers.get(Integer.valueOf(platformViewResizeRequest.viewId));
            if (virtualDisplayController != null) {
                int physicalPixels = PlatformViewsController.this.toPhysicalPixels(platformViewResizeRequest.newLogicalWidth);
                int physicalPixels2 = PlatformViewsController.this.toPhysicalPixels(platformViewResizeRequest.newLogicalHeight);
                PlatformViewsController.this.validateVirtualDisplayDimensions(physicalPixels, physicalPixels2);
                PlatformViewsController.this.lockInputConnection(virtualDisplayController);
                virtualDisplayController.resize(physicalPixels, physicalPixels2, new AnonymousClass1(virtualDisplayController, runnable));
                return;
            }
            throw new IllegalStateException("Trying to resize a platform view with unknown id: " + platformViewResizeRequest.viewId);
        }

        /* renamed from: io.flutter.plugin.platform.PlatformViewsController$1$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ Runnable val$onComplete;
            final /* synthetic */ VirtualDisplayController val$vdController;

            AnonymousClass1(VirtualDisplayController virtualDisplayController, Runnable runnable) {
                this.val$vdController = virtualDisplayController;
                this.val$onComplete = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                PlatformViewsController.this.unlockInputConnection(this.val$vdController);
                this.val$onComplete.run();
            }
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public void onTouch(PlatformViewsChannel.PlatformViewTouch platformViewTouch) {
            int i = platformViewTouch.viewId;
            float f = PlatformViewsController.this.context.getResources().getDisplayMetrics().density;
            ensureValidAndroidVersion(20);
            if (PlatformViewsController.this.vdControllers.containsKey(Integer.valueOf(i))) {
                PlatformViewsController.this.vdControllers.get(Integer.valueOf(platformViewTouch.viewId)).dispatchTouchEvent(PlatformViewsController.this.toMotionEvent(f, platformViewTouch, true));
            } else if (PlatformViewsController.this.platformViews.get(i) != null) {
                MotionEvent motionEvent = PlatformViewsController.this.toMotionEvent(f, platformViewTouch, false);
                View view = ((PlatformView) PlatformViewsController.this.platformViews.get(platformViewTouch.viewId)).getView();
                if (view != null) {
                    view.dispatchTouchEvent(motionEvent);
                }
            } else {
                throw new IllegalStateException("Sending touch to an unknown view with id: " + i);
            }
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public void setDirection(int i, int i2) {
            if (PlatformViewsController.validateDirection(i2)) {
                ensureValidAndroidVersion(20);
                View view = PlatformViewsController.this.vdControllers.get(Integer.valueOf(i)).getView();
                if (view != null) {
                    view.setLayoutDirection(i2);
                    return;
                }
                throw new IllegalStateException("Sending touch to an unknown view with id: " + i2);
            }
            throw new IllegalStateException("Trying to set unknown direction value: " + i2 + "(view id: " + i + l.t);
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public void clearFocus(int i) {
            ensureValidAndroidVersion(20);
            PlatformViewsController.this.vdControllers.get(Integer.valueOf(i)).getView().clearFocus();
        }

        private void ensureValidAndroidVersion(int i) {
            if (Build.VERSION.SDK_INT < i) {
                throw new IllegalStateException("Trying to use platform views with API " + Build.VERSION.SDK_INT + ", required API level is: " + i);
            }
        }
    }

    public MotionEvent toMotionEvent(float f, PlatformViewsChannel.PlatformViewTouch platformViewTouch, boolean z) {
        MotionEvent pop = this.motionEventTracker.pop(MotionEventTracker.MotionEventId.from(platformViewTouch.motionEventId));
        MotionEvent.PointerProperties[] pointerPropertiesArr = (MotionEvent.PointerProperties[]) parsePointerPropertiesList(platformViewTouch.rawPointerPropertiesList).toArray(new MotionEvent.PointerProperties[platformViewTouch.pointerCount]);
        MotionEvent.PointerCoords[] pointerCoordsArr = (MotionEvent.PointerCoords[]) parsePointerCoordsList(platformViewTouch.rawPointerCoords, f).toArray(new MotionEvent.PointerCoords[platformViewTouch.pointerCount]);
        if (z || pop == null) {
            return MotionEvent.obtain(platformViewTouch.downTime.longValue(), platformViewTouch.eventTime.longValue(), platformViewTouch.action, platformViewTouch.pointerCount, pointerPropertiesArr, pointerCoordsArr, platformViewTouch.metaState, platformViewTouch.buttonState, platformViewTouch.xPrecision, platformViewTouch.yPrecision, platformViewTouch.deviceId, platformViewTouch.edgeFlags, platformViewTouch.source, platformViewTouch.flags);
        }
        return MotionEvent.obtain(pop.getDownTime(), pop.getEventTime(), pop.getAction(), platformViewTouch.pointerCount, pointerPropertiesArr, pointerCoordsArr, pop.getMetaState(), pop.getButtonState(), pop.getXPrecision(), pop.getYPrecision(), pop.getDeviceId(), pop.getEdgeFlags(), pop.getSource(), pop.getFlags());
    }

    public void attach(Context context, TextureRegistry textureRegistry, DartExecutor dartExecutor) {
        if (this.context == null) {
            this.context = context;
            this.textureRegistry = textureRegistry;
            this.platformViewsChannel = new PlatformViewsChannel(dartExecutor);
            this.platformViewsChannel.setPlatformViewsHandler(this.channelHandler);
            return;
        }
        throw new AssertionError("A PlatformViewsController can only be attached to a single output target.\nattach was called while the PlatformViewsController was already attached.");
    }

    public void detach() {
        PlatformViewsChannel platformViewsChannel = this.platformViewsChannel;
        if (platformViewsChannel != null) {
            platformViewsChannel.setPlatformViewsHandler(null);
        }
        destroyOverlaySurfaces();
        this.platformViewsChannel = null;
        this.context = null;
        this.textureRegistry = null;
    }

    public void attachToView(View view) {
        this.flutterView = view;
        for (VirtualDisplayController virtualDisplayController : this.vdControllers.values()) {
            virtualDisplayController.onFlutterViewAttached(view);
        }
    }

    public void detachFromView() {
        destroyOverlaySurfaces();
        this.flutterView = null;
        for (VirtualDisplayController virtualDisplayController : this.vdControllers.values()) {
            virtualDisplayController.onFlutterViewDetached();
        }
    }

    @Override // io.flutter.plugin.platform.PlatformViewsAccessibilityDelegate
    public void attachAccessibilityBridge(AccessibilityBridge accessibilityBridge) {
        this.accessibilityEventsDelegate.setAccessibilityBridge(accessibilityBridge);
    }

    @Override // io.flutter.plugin.platform.PlatformViewsAccessibilityDelegate
    public void detachAccessibiltyBridge() {
        this.accessibilityEventsDelegate.setAccessibilityBridge(null);
    }

    public void attachTextInputPlugin(TextInputPlugin textInputPlugin) {
        this.textInputPlugin = textInputPlugin;
    }

    public void detachTextInputPlugin() {
        this.textInputPlugin = null;
    }

    public boolean checkInputConnectionProxy(View view) {
        if (view == null || !this.contextToPlatformView.containsKey(view.getContext())) {
            return false;
        }
        View view2 = this.contextToPlatformView.get(view.getContext());
        if (view2 == view) {
            return true;
        }
        return view2.checkInputConnectionProxy(view);
    }

    public PlatformViewRegistry getRegistry() {
        return this.registry;
    }

    public void onDetachedFromJNI() {
        flushAllViews();
    }

    public void onPreEngineRestart() {
        flushAllViews();
    }

    @Override // io.flutter.plugin.platform.PlatformViewsAccessibilityDelegate
    public View getPlatformViewById(Integer num) {
        if (this.platformViews.get(num.intValue()) != null) {
            return ((PlatformView) this.platformViews.get(num.intValue())).getView();
        }
        VirtualDisplayController virtualDisplayController = this.vdControllers.get(num);
        if (virtualDisplayController == null) {
            return null;
        }
        return virtualDisplayController.getView();
    }

    @Override // io.flutter.plugin.platform.PlatformViewsAccessibilityDelegate
    public boolean usesVirtualDisplay(Integer num) {
        return this.vdControllers.containsKey(num);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void lockInputConnection(VirtualDisplayController virtualDisplayController) {
        TextInputPlugin textInputPlugin = this.textInputPlugin;
        if (textInputPlugin != null) {
            textInputPlugin.lockPlatformViewInputConnection();
            virtualDisplayController.onInputConnectionLocked();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void unlockInputConnection(VirtualDisplayController virtualDisplayController) {
        TextInputPlugin textInputPlugin = this.textInputPlugin;
        if (textInputPlugin != null) {
            textInputPlugin.unlockPlatformViewInputConnection();
            virtualDisplayController.onInputConnectionUnlocked();
        }
    }

    private static List<MotionEvent.PointerProperties> parsePointerPropertiesList(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : (List) obj) {
            arrayList.add(parsePointerProperties(obj2));
        }
        return arrayList;
    }

    private static MotionEvent.PointerProperties parsePointerProperties(Object obj) {
        List list = (List) obj;
        MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
        pointerProperties.id = ((Integer) list.get(0)).intValue();
        pointerProperties.toolType = ((Integer) list.get(1)).intValue();
        return pointerProperties;
    }

    private static List<MotionEvent.PointerCoords> parsePointerCoordsList(Object obj, float f) {
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : (List) obj) {
            arrayList.add(parsePointerCoords(obj2, f));
        }
        return arrayList;
    }

    private static MotionEvent.PointerCoords parsePointerCoords(Object obj, float f) {
        List list = (List) obj;
        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        pointerCoords.orientation = (float) ((Double) list.get(0)).doubleValue();
        pointerCoords.pressure = (float) ((Double) list.get(1)).doubleValue();
        pointerCoords.size = (float) ((Double) list.get(2)).doubleValue();
        pointerCoords.toolMajor = ((float) ((Double) list.get(3)).doubleValue()) * f;
        pointerCoords.toolMinor = ((float) ((Double) list.get(4)).doubleValue()) * f;
        pointerCoords.touchMajor = ((float) ((Double) list.get(5)).doubleValue()) * f;
        pointerCoords.touchMinor = ((float) ((Double) list.get(6)).doubleValue()) * f;
        pointerCoords.x = ((float) ((Double) list.get(7)).doubleValue()) * f;
        pointerCoords.y = ((float) ((Double) list.get(8)).doubleValue()) * f;
        return pointerCoords;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void validateVirtualDisplayDimensions(int i, int i2) {
        DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
        if (i2 > displayMetrics.heightPixels || i > displayMetrics.widthPixels) {
            Log.w(TAG, "Creating a virtual display of size: [" + i + ", " + i2 + "] may result in problems(https://github.com/flutter/flutter/issues/2897).It is larger than the device screen size: [" + displayMetrics.widthPixels + ", " + displayMetrics.heightPixels + "].");
        }
    }

    private float getDisplayDensity() {
        return this.context.getResources().getDisplayMetrics().density;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int toPhysicalPixels(double d) {
        return (int) Math.round(d * ((double) getDisplayDensity()));
    }

    private void flushAllViews() {
        for (VirtualDisplayController virtualDisplayController : this.vdControllers.values()) {
            virtualDisplayController.dispose();
        }
        this.vdControllers.clear();
        while (this.platformViews.size() > 0) {
            this.channelHandler.disposeAndroidViewForPlatformView(this.platformViews.keyAt(0));
        }
    }

    private void initializeRootImageViewIfNeeded() {
        if (!this.flutterViewConvertedToImageView) {
            ((FlutterView) this.flutterView).convertToImageView();
            this.flutterViewConvertedToImageView = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void initializePlatformViewIfNeeded(int i) {
        PlatformView platformView = (PlatformView) this.platformViews.get(i);
        if (platformView == null) {
            throw new IllegalStateException("Platform view hasn't been initialized from the platform view channel.");
        } else if (this.platformViewParent.get(i) == null) {
            if (platformView.getView() == null) {
                throw new IllegalStateException("PlatformView#getView() returned null, but an Android view reference was expected.");
            } else if (platformView.getView().getParent() == null) {
                Context context = this.context;
                FlutterMutatorView flutterMutatorView = new FlutterMutatorView(context, context.getResources().getDisplayMetrics().density, this.androidTouchProcessor);
                this.platformViewParent.put(i, flutterMutatorView);
                flutterMutatorView.addView(platformView.getView());
                ((FlutterView) this.flutterView).addView(flutterMutatorView);
            } else {
                throw new IllegalStateException("The Android view returned from PlatformView#getView() was already added to a parent view.");
            }
        }
    }

    public void attachToFlutterRenderer(FlutterRenderer flutterRenderer) {
        this.androidTouchProcessor = new AndroidTouchProcessor(flutterRenderer, true);
    }

    public void onDisplayPlatformView(int i, int i2, int i3, int i4, int i5, int i6, int i7, FlutterMutatorsStack flutterMutatorsStack) {
        initializeRootImageViewIfNeeded();
        initializePlatformViewIfNeeded(i);
        FlutterMutatorView flutterMutatorView = (FlutterMutatorView) this.platformViewParent.get(i);
        flutterMutatorView.readyToDisplay(flutterMutatorsStack, i2, i3, i4, i5);
        flutterMutatorView.setVisibility(0);
        flutterMutatorView.bringToFront();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i6, i7);
        View view = ((PlatformView) this.platformViews.get(i)).getView();
        if (view != null) {
            view.setLayoutParams(layoutParams);
            view.bringToFront();
        }
        this.currentFrameUsedPlatformViewIds.add(Integer.valueOf(i));
    }

    public void onDisplayOverlaySurface(int i, int i2, int i3, int i4, int i5) {
        if (this.overlayLayerViews.get(i) != null) {
            initializeRootImageViewIfNeeded();
            FlutterImageView flutterImageView = (FlutterImageView) this.overlayLayerViews.get(i);
            if (flutterImageView.getParent() == null) {
                ((FlutterView) this.flutterView).addView(flutterImageView);
            }
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i4, i5);
            layoutParams.leftMargin = i2;
            layoutParams.topMargin = i3;
            flutterImageView.setLayoutParams(layoutParams);
            flutterImageView.setVisibility(0);
            flutterImageView.bringToFront();
            this.currentFrameUsedOverlayLayerIds.add(Integer.valueOf(i));
            return;
        }
        throw new IllegalStateException("The overlay surface (id:" + i + ") doesn't exist");
    }

    public void onBeginFrame() {
        this.currentFrameUsedOverlayLayerIds.clear();
        this.currentFrameUsedPlatformViewIds.clear();
    }

    public void onEndFrame() {
        FlutterView flutterView = (FlutterView) this.flutterView;
        boolean z = false;
        if (!this.flutterViewConvertedToImageView || !this.currentFrameUsedPlatformViewIds.isEmpty()) {
            if (this.flutterViewConvertedToImageView && flutterView.acquireLatestImageViewFrame()) {
                z = true;
            }
            finishFrame(z);
            return;
        }
        this.flutterViewConvertedToImageView = false;
        flutterView.revertImageView(new $$Lambda$PlatformViewsController$LCdGlczcEGgq9dnX04hdHKRzsE(this));
    }

    public /* synthetic */ void lambda$onEndFrame$0$PlatformViewsController() {
        finishFrame(false);
    }

    private void finishFrame(boolean z) {
        boolean z2 = z;
        for (int i = 0; i < this.overlayLayerViews.size(); i++) {
            int keyAt = this.overlayLayerViews.keyAt(i);
            FlutterImageView flutterImageView = (FlutterImageView) this.overlayLayerViews.valueAt(i);
            if (this.currentFrameUsedOverlayLayerIds.contains(Integer.valueOf(keyAt))) {
                ((FlutterView) this.flutterView).attachOverlaySurfaceToRender(flutterImageView);
                z2 &= flutterImageView.acquireLatestImage();
            } else {
                if (!this.flutterViewConvertedToImageView) {
                    flutterImageView.detachFromRenderer();
                }
                flutterImageView.setVisibility(8);
            }
        }
        for (int i2 = 0; i2 < this.platformViewParent.size(); i2++) {
            int keyAt2 = this.platformViewParent.keyAt(i2);
            View view = (View) this.platformViewParent.get(keyAt2);
            if (!z2 || !this.currentFrameUsedPlatformViewIds.contains(Integer.valueOf(keyAt2))) {
                view.setVisibility(8);
            } else {
                view.setVisibility(0);
            }
        }
    }

    public FlutterOverlaySurface createOverlaySurface(FlutterImageView flutterImageView) {
        int i = this.nextOverlayLayerId;
        this.nextOverlayLayerId = i + 1;
        this.overlayLayerViews.put(i, flutterImageView);
        return new FlutterOverlaySurface(i, flutterImageView.getSurface());
    }

    public FlutterOverlaySurface createOverlaySurface() {
        return createOverlaySurface(new FlutterImageView(this.flutterView.getContext(), this.flutterView.getWidth(), this.flutterView.getHeight(), FlutterImageView.SurfaceKind.overlay));
    }

    public void destroyOverlaySurfaces() {
        for (int i = 0; i < this.overlayLayerViews.size(); i++) {
            this.overlayLayerViews.keyAt(i);
            FlutterImageView flutterImageView = (FlutterImageView) this.overlayLayerViews.valueAt(i);
            flutterImageView.detachFromRenderer();
            View view = this.flutterView;
            if (view != null) {
                ((FlutterView) view).removeView(flutterImageView);
            }
        }
        this.overlayLayerViews.clear();
    }
}
